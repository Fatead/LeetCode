# Web 安全与接口防护（后端视角）

> 后端安全面试不考"渗透测试"，考的是：**你写的接口会不会被人打穿**。
> 金融、电商、支付、SaaS、政企岗位几乎必问；普通业务岗也常问"你们怎么防越权/防重放"。
>
> 本文档覆盖：OWASP 后端视角 → 注入 → XSS/CSRF/SSRF → 认证授权 → 密码加密 → 接口防护 → 数据安全 → 测试排查。

---

## 目录

- [零、后端安全的责任边界](#零后端安全的责任边界)
- [一、OWASP Top 10 后端视角](#一owasp-top-10-后端视角)
- [二、注入类漏洞](#二注入类漏洞)
- [三、XSS / CSRF / SSRF / CORS](#三xss--csrf--ssrf--cors)
- [四、认证与授权](#四认证与授权)
- [五、密码与加密](#五密码与加密)
- [六、接口防护](#六接口防护)
- [七、数据安全](#七数据安全)
- [八、安全测试与排查](#八安全测试与排查)
- [九、高频 60 问](#九高频-60-问)
- [十、上线安全检查清单](#十上线安全检查清单)

---

## 零、后端安全的责任边界

### 0.1 面试怎么考

| 考法 | 例子 |
| --- | --- |
| **原理** | "SQL 注入的原理？预编译为什么能防？" |
| **代码** | "这段 MyBatis 代码有什么问题？" |
| **设计** | "设计一个防止重复提交和重放的支付接口" |
| **排查** | "线上发现有人批量拉取用户数据，你怎么定位和止损？" |

### 0.2 三个基本认知

```
① 所有外部输入都不可信（参数、Header、Cookie、文件、回调、MQ 消息）
② 安全要在"边界"做：网关/过滤器/参数校验层
③ 纵深防御：不依赖单一手段（校验 + 编码 + 权限 + 审计）
```

### 0.3 后端 vs 前端的安全分工

| 风险 | 主要责任方 | 后端必须做什么 |
| --- | --- | --- |
| XSS | 前端（输出编码）+ 后端（输入过滤、CSP） | 存储型 XSS 的输入处理 |
| CSRF | 后端（Token/SameSite） | 校验来源与 Token |
| SQL 注入 | **后端** | 预编译、白名单 |
| 越权 | **后端** | 每个接口做权限校验 |
| 敏感数据泄露 | **后端** | 脱敏、加密、最小返回 |

**一句话**：**前端校验是体验，后端校验是安全。**

---

## 一、OWASP Top 10 后端视角

| 风险 | 后端表现 | 关键防线 |
| --- | --- | --- |
| **失效的访问控制**（越权） | 改 ID 就能看别人订单 | 每个请求都校验"资源归属" |
| **加密机制失效** | 密码 MD5、明文存身份证 | 强哈希 + 加密存储 + HTTPS |
| **注入** | SQL/命令/LDAP/表达式注入 | 预编译 + 白名单 |
| **不安全设计** | 业务逻辑漏洞（负数金额、并发） | 威胁建模 + 状态机 |
| **安全配置错误** | 默认密码、暴露 Actuator、CORS `*` | 基线检查 |
| **易受攻击的组件** | 旧版 Fastjson/Log4j/Shiro | 依赖扫描 |
| **认证失效** | 弱密码、Session 固定、JWT 不过期 | 多因素 + 会话管理 |
| **数据完整性失效** | 反序列化、未签名更新 | 签名 + 白名单 |
| **日志监控不足** | 被拖库无告警 | 审计 + 告警 |
| **SSRF** | 内网探测、云元数据 | 白名单 + 禁跳转 |

**面试话术**："后端最常出的不是'高深漏洞'，而是**越权**和**业务逻辑漏洞**——因为它们不需要技术，只需要改个 ID 或传个负数。"

---

## 二、注入类漏洞

### 2.1 SQL 注入

#### 原理

```java
// ❌ 拼接 SQL
String sql = "SELECT * FROM user WHERE name = '" + name + "'";
// 输入 name = "' OR '1'='1"  → 返回所有用户
// 输入 name = "'; DROP TABLE user; --" → 删表
```

#### 防御：预编译（参数化查询）

```java
// ✅ JDBC PreparedStatement
String sql = "SELECT * FROM user WHERE name = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, name);
```

**为什么预编译能防**：SQL 的**结构**在编译时已经确定，参数只作为**数据**传入，不会被解析成 SQL 语法。

#### MyBatis 的坑

```xml
<!-- ✅ #{} 预编译 -->
SELECT * FROM user WHERE name = #{name}

<!-- ❌ ${} 字符串拼接，可注入 -->
SELECT * FROM user WHERE name = '${name}'
```

**必须用 `${}` 的场景**（要额外校验）：

- 动态表名、动态列名
- `ORDER BY ${column}`（**最常见的注入点**）

**正确做法**：**白名单校验**。

```java
private static final Set<String> ALLOWED = Set.of("id", "create_time", "amount");

public String buildOrderBy(String column, String direction) {
    if (!ALLOWED.contains(column)) throw new IllegalArgumentException("非法排序字段");
    if (!"asc".equalsIgnoreCase(direction) && !"desc".equalsIgnoreCase(direction)) {
        throw new IllegalArgumentException("非法排序方向");
    }
    return column + " " + direction;
}
```

#### 其他注入点

| 类型 | 例子 | 防御 |
| --- | --- | --- |
| 二次注入 | 存入时转义、取出时拼接 | 存储时也校验 + 输出时预编译 |
| 宽字节注入 | GBK 编码绕过转义 | 统一 UTF-8 + 预编译 |
| 堆叠注入 | `; DROP TABLE` | 禁止多语句 |
| 盲注 | 布尔/时间盲注 | 预编译 + WAF + 限流 |

### 2.2 命令注入

```java
// ❌ 危险
Runtime.getRuntime().exec("ping " + ip);

// ✅ 用参数数组 + 白名单
ProcessBuilder pb = new ProcessBuilder("ping", "-c", "1", ip);
// 并且校验 ip 只允许 [0-9.]
```

### 2.3 XXE（XML 外部实体注入）

```java
// ❌ 默认允许外部实体
DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

// ✅ 禁用
f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
f.setFeature("http://xml.org/sax/features/external-general-entities", false);
f.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
```

**危害**：读本地文件（`/etc/passwd`）、SSRF、DoS（Billion Laughs）。

### 2.4 反序列化

| 场景 | 风险 |
| --- | --- |
| Java 原生 `ObjectInputStream` | 反序列化链 RCE |
| Fastjson `autoType` | 历史漏洞多 |
| Jackson 多态 `@JsonTypeInfo` | 可加载任意类 |
| Shiro `rememberMe` | 反序列化 RCE |

**防御**：

- 不用原生反序列化传数据（用 JSON + 白名单字段）。
- 关闭 `autoType`。
- 升级组件（Log4j2、Fastjson、Shiro）。
- 依赖扫描（`mvn dependency-check`、`trivy`）。

### 2.5 表达式注入（SpEL/OGNL）

```java
// ❌ 用户输入进入表达式
expressionParser.parseExpression(userInput).getValue();
```

**防御**：禁止用户输入进入表达式；用 `SimpleEvaluationContext` 限制。

---

## 三、XSS / CSRF / SSRF / CORS

### 3.1 XSS（跨站脚本）

| 类型 | 说明 | 例子 |
| --- | --- | --- |
| 反射型 | URL 参数直接回显 | 搜索关键词回显 |
| 存储型 | 存库后展示给他人 | 评论、昵称 |
| DOM 型 | 前端 JS 拼接 | `innerHTML` |

**后端能做的**：

```java
// ① 输入校验（不是万能，但能挡大部分）
// ② 输出编码（由前端/模板负责，后端 API 返回 JSON 时设置正确的 Content-Type）
response.setContentType("application/json;charset=UTF-8");

// ③ 富文本白名单过滤（Jsoup）
String safe = Jsoup.clean(userHtml, Safelist.basic());
```

**关键**：

```
- 返回 JSON 时不要设置 text/html
- Cookie 加 HttpOnly（防 JS 读取）
- 配置 CSP（Content-Security-Policy）
- 富文本必须服务端白名单过滤，不能只靠前端
```

### 3.2 CSRF（跨站请求伪造）

**原理**：用户已登录 A 站，访问恶意 B 站，B 站自动向 A 站发请求（携带 Cookie）。

**防御**：

| 手段 | 说明 |
| --- | --- |
| **CSRF Token** | 表单/请求头带一次性 token，服务端校验 |
| **SameSite Cookie** | `SameSite=Lax/Strict`（现代浏览器默认 Lax） |
| **校验 Referer/Origin** | 白名单 |
| **关键操作二次验证** | 支付密码/短信 |

```java
// Spring Security 默认开启 CSRF，前后端分离时可关闭，
// 但要确保用 JWT（放在 Header 而非 Cookie）+ SameSite
```

**追问**："JWT 放在 Header 里会有 CSRF 吗？" → 不会（浏览器不会自动带自定义 Header），但会有 **XSS 后窃取 token** 的风险。

### 3.3 SSRF（服务端请求伪造）

**场景**：服务端根据用户输入的 URL 去请求（图片抓取、Webhook、PDF 渲染）。

**危害**：

```
① 探测内网（10.0.0.0/8、192.168.0.0/16）
② 云元数据（169.254.169.254）拿临时凭证
③ 攻击内网服务（Redis、ES）
④ file:// 读本地文件
```

**防御（分层）**：

```java
// ① 协议白名单：只允许 http/https
// ② 域名/IP 白名单（最有效）
// ③ 解析后校验 IP，禁止内网/回环/链路本地
// ④ 禁止跟随重定向（或每次重定向都校验）
// ⑤ 限制响应大小与超时
// ⑥ 独立出口网络（无内网访问权限）
```

**易错**：只校验字符串（`127.0.0.1` 可以写成 `2130706433`、`0x7f.0.0.1`）→ **必须解析成 IP 再校验**，且防 DNS rebinding。

### 3.4 CORS

```java
// ❌ 危险：允许任意来源 + 携带凭证
response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
response.setHeader("Access-Control-Allow-Credentials", "true");

// ✅ 白名单
if (ALLOWED_ORIGINS.contains(origin)) {
    response.setHeader("Access-Control-Allow-Origin", origin);
    response.setHeader("Access-Control-Allow-Credentials", "true");
}
```

**追问**："`Access-Control-Allow-Origin: *` 加 `Credentials: true` 会怎样？" → 浏览器会拒绝，且这是常见错误配置。

---

## 四、认证与授权

### 4.1 Session vs JWT

| 维度 | Session | JWT |
| --- | --- | --- |
| 状态 | 服务端存储 | 无状态（自包含） |
| 扩展 | 需要共享存储（Redis） | 天然水平扩展 |
| 注销 | 删 Session 即可 | **无法立即失效**（需黑名单/短 TTL） |
| 体积 | 小（只传 ID） | 大（每次携带） |
| 安全 | Cookie + CSRF 防护 | Header + XSS 风险 |

**JWT 的五个坑**：

```
① alg=none 攻击 → 服务端必须固定算法，不接受 header 里的 alg
② 密钥混淆（HS256 用 RS256 公钥）→ 明确算法与密钥类型
③ 不校验过期 → 必须校验 exp/nbf
④ 无法吊销 → 短 TTL + refresh token + 黑名单
⑤ 存 localStorage → XSS 可窃取；建议 HttpOnly Cookie 或内存
```

**JWT 结构**：`header.payload.signature`，**payload 只是 Base64，不是加密**，不能放敏感信息。

### 4.2 OAuth2 / OIDC

| 角色 | 说明 |
| --- | --- |
| Resource Owner | 用户 |
| Client | 第三方应用 |
| Authorization Server | 授权服务器 |
| Resource Server | 资源服务器 |

**四种模式**（现代推荐）：

```
授权码 + PKCE（推荐，前后端分离/移动端）
客户端凭证（服务间）
（隐式模式和密码模式已不推荐）
```

**必须校验**：

```
① redirect_uri 白名单（防开放重定向）
② state 参数（防 CSRF）
③ PKCE code_verifier（防授权码拦截）
④ scope 最小化
⑤ token 绑定 audience
```

### 4.3 越权：后端第一漏洞

| 类型 | 例子 | 防御 |
| --- | --- | --- |
| **水平越权**（IDOR） | 改 `orderId` 看别人订单 | 查询时带 `user_id` 条件 |
| **垂直越权** | 普通用户调管理员接口 | 接口级 RBAC |
| **功能越权** | 绕过前端隐藏按钮 | 后端每个接口都校验 |
| **数据越权** | 导出全量数据 | 数据权限（部门/租户） |

**正确写法**：

```java
// ❌ 只按 ID 查
Order order = orderMapper.selectById(orderId);

// ✅ 带归属条件
Order order = orderMapper.selectByIdAndUserId(orderId, currentUserId);
if (order == null) throw new NotFoundException();   // 不要返回 403，避免探测
```

**追问**："为什么返回 404 而不是 403？" → 403 会泄露"资源存在但你没权限"，404 更安全。

**权限模型**：

| 模型 | 说明 | 适用 |
| --- | --- | --- |
| RBAC | 用户-角色-权限 | 大多数后台 |
| ABAC | 属性（部门/时间/等级） | 复杂数据权限 |
| ReBAC | 关系（Zanzibar） | 文档协作、社交 |

### 4.4 认证的工程细节

| 问题 | 方案 |
| --- | --- |
| 暴力破解 | 登录失败计数 + 验证码 + 锁定 |
| 撞库 | 设备指纹 + 风控 + MFA |
| Session 固定 | 登录后重新生成 SessionId |
| 并发登录 | 单端登录/踢下线 |
| 敏感操作 | 二次验证（密码/短信/MFA） |

---

## 五、密码与加密

### 5.1 密码存储

```
❌ 明文
❌ MD5/SHA1（可彩虹表、可暴力）
❌ 只加固定盐（盐泄露后批量破解）
✅ bcrypt / scrypt / Argon2（慢哈希 + 随机盐）
```

```java
// Spring Security
PasswordEncoder encoder = new BCryptPasswordEncoder(12);   // cost=12
String hash = encoder.encode(rawPassword);
boolean ok = encoder.matches(rawPassword, hash);
```

**追问**："为什么不能用 MD5 加盐？" → 计算太快，GPU 每秒可算数十亿次；慢哈希通过增加计算成本抵抗暴力破解。

**补充**：高安全场景可加 **pepper**（全局密钥，存在 KMS，不存库）。

### 5.2 对称与非对称

| 类型 | 算法 | 用途 |
| --- | --- | --- |
| 对称 | AES-256-GCM | 数据加密（快） |
| 非对称 | RSA / ECDSA | 签名、密钥交换 |
| 哈希 | SHA-256 / bcrypt | 完整性、密码 |
| 国密 | SM2/SM3/SM4 | 政企合规 |

**AES-GCM 的正确用法**：

```java
// 每次加密用随机 IV，IV 与密文一起存
// 必须校验认证标签（防篡改）
Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
```

**易错**：ECB 模式（相同明文出相同密文）、固定 IV、不校验 tag。

### 5.3 HTTPS 与传输安全

| 点 | 说明 |
| --- | --- |
| TLS 版本 | 1.2+，推荐 1.3 |
| 证书 | 有效、不过期、域名匹配 |
| HSTS | 强制 HTTPS |
| 双向 TLS | 服务间（mTLS，Service Mesh） |
| 证书管理 | 自动续期（cert-manager/ACME） |

**追问**："HTTPS 能防抓包吗？" → 能防内容窃听，但**不能防客户端被逆向**（证书固定可提高难度）。

---

## 六、接口防护

### 6.1 开放接口的签名与防重放

**设计**：

```
参数：appId, timestamp, nonce, sign, data
签名：sign = HMAC-SHA256(secret, sortedParams + timestamp + nonce)
```

**服务端校验**：

```java
① appId 存在且启用
② timestamp 在 ±5 分钟内（防重放）
③ nonce 未使用过（Redis setnx，TTL = 窗口）
④ 重新计算 sign 并比对（常量时间比较）
⑤ 校验通过后执行业务
```

**易错**：

- 签名参数没排序
- 只对 body 签名，没覆盖 query/header
- 用 `==` 比较字符串（时序攻击，用 `MessageDigest.isEqual`）
- nonce 只存在本地（多实例失效）

### 6.2 防重复提交

| 方案 | 说明 |
| --- | --- |
| 前端防抖 | 体验，不可靠 |
| Token（一次一用） | 服务端 Redis `DEL` 原子判断 |
| 唯一索引 | 最终兜底 |
| 分布式锁 | 防并发 |
| 幂等键 | 重试场景 |

### 6.3 参数校验

```java
public record CreateOrderReq(
        @NotNull Long skuId,
        @Min(1) @Max(999) Integer quantity,
        @Size(max = 200) String remark) {}
```

**必须校验**：类型、范围、长度、枚举、格式、**业务约束**（金额不能为负、数量不能超库存）。

**追问**："前端已经校验了，后端还要吗？" → 必须。前端可被绕过（直接调接口）。

### 6.4 防爬与风控

| 手段 | 说明 |
| --- | --- |
| 限流 | IP/用户/接口维度 |
| 验证码 | 异常行为触发 |
| 设备指纹 | 识别批量请求 |
| 数据脱敏 | 列表页不返回完整手机号 |
| 蜜罐 | 发现爬虫 |

### 6.5 文件上传

```
① 后缀白名单（不是黑名单）
② 校验文件头（magic number），不只看后缀
③ 重命名（不用用户文件名）
④ 存储到对象存储/独立域名（不在应用服务器执行）
⑤ 大小限制 + 图片压缩（防图片炸弹）
⑥ 病毒扫描（可选）
```

---

## 七、数据安全

### 7.1 脱敏

| 数据 | 展示 | 存储 |
| --- | --- | --- |
| 手机号 | `138****1234` | 可加密 |
| 身份证 | `110***********1234` | 加密 |
| 银行卡 | `**** **** **** 1234` | 加密 + 令牌化 |
| 地址 | 只显示到区 | — |

**实现**：序列化时脱敏（`@JsonSerialize`）或 DTO 转换；**不要依赖前端处理**。

### 7.2 加密存储

```
- 敏感字段：AES-GCM 加密 + 密钥在 KMS
- 需要检索的字段：存哈希（如手机号 hash）用于精确匹配
- 不加密但需要审计的：访问日志
```

### 7.3 日志安全

```
❌ 打印完整身份证/银行卡/密码/token
✅ 脱敏后打印；禁止打印请求体中的敏感字段
✅ 日志访问权限控制 + 留存期限
```

**追问**："为什么日志泄露很严重？" → 日志系统往往权限更松、留存更久、被大量转发。

### 7.4 多租户隔离

```
① 数据层：tenant_id 字段 + 强制过滤（拦截器/MyBatis 插件）
② 缓存：key 带 tenant_id
③ 文件：独立目录/桶
④ 消息：消息带 tenant_id
⑤ 越权测试：用 A 租户 token 访问 B 租户资源
```

**易错**：缓存 key 忘了带租户 → 串数据（真实事故）。

### 7.5 合规

| 要求 | 说明 |
| --- | --- |
| 最小必要 | 只收集业务必需的字段 |
| 告知同意 | 隐私政策、授权 |
| 数据出境 | 评估/标准合同/安全评估 |
| 留存期限 | 到期删除/匿名化 |
| 用户权利 | 查询、更正、删除、导出 |

---

## 八、安全测试与排查

### 8.1 上线前必做的五项

```
① 越权测试：用普通账号访问管理接口 / 改 ID 访问他人资源
② 注入测试：参数里塞 ' OR '1'='1、<script>
③ 敏感信息：接口返回是否带密码/密钥/内部 IP
④ 依赖扫描：trivy / dependency-check / OWASP Dependency-Check
⑤ 配置检查：Actuator 是否暴露、CORS 是否过宽、默认密码
```

### 8.2 被攻击时的应急

```
① 止损：关接口/限流/封 IP/下线功能
② 保留证据：日志、请求样本、时间线
③ 定位入口：从异常请求反查代码路径
④ 评估影响：多少数据、多少用户
⑤ 修复 + 回归 + 复盘
⑥ 通报：按合规要求（数据泄露需上报）
```

### 8.3 SDL（安全开发生命周期）

```
需求阶段：威胁建模（STRIDE）
设计阶段：安全评审（认证/授权/加密/日志）
编码阶段：安全编码规范 + 依赖白名单
测试阶段：SAST/DAST/渗透测试
上线阶段：基线检查 + 灰度
运营阶段：监控告警 + 漏洞响应
```

---

## 九、高频 60 问

### A. 注入（12）

| # | 问题 | 难度 |
| --- | --- | --- |
| 1 | SQL 注入的原理？ | ★★ |
| 2 | 预编译为什么能防注入？ | ★★★ |
| 3 | MyBatis `#{}` 和 `${}` 的区别？ | ★★ |
| 4 | `ORDER BY` 怎么防注入？ | ★★★ |
| 5 | 二次注入是什么？ | ★★★ |
| 6 | 宽字节注入原理？ | ★★★ |
| 7 | 命令注入怎么防？ | ★★ |
| 8 | XXE 的原理与防御？ | ★★★ |
| 9 | 反序列化漏洞怎么产生的？ | ★★★ |
| 10 | Fastjson 为什么漏洞多？ | ★★★ |
| 11 | SpEL/OGNL 注入怎么防？ | ★★★ |
| 12 | 盲注怎么发现？ | ★★ |

### B. Web 漏洞（12）

| # | 问题 | 难度 |
| --- | --- | --- |
| 13 | XSS 三种类型的区别？ | ★★ |
| 14 | 后端怎么防存储型 XSS？ | ★★★ |
| 15 | CSRF 的原理？ | ★★ |
| 16 | CSRF Token 怎么实现？ | ★★ |
| 17 | SameSite 的作用？ | ★★★ |
| 18 | SSRF 的危害？ | ★★★ |
| 19 | SSRF 怎么防（分层）？ | ★★★ |
| 20 | 为什么只校验字符串不够？ | ★★★ |
| 21 | CORS 配置错误有什么风险？ | ★★★ |
| 22 | 点击劫持怎么防？ | ★★ |
| 23 | 开放重定向的风险？ | ★★ |
| 24 | 文件上传怎么防？ | ★★★ |

### C. 认证授权（14）

| # | 问题 | 难度 |
| --- | --- | --- |
| 25 | Session 和 JWT 怎么选？ | ★★★ |
| 26 | JWT 的五个坑？ | ★★★ |
| 27 | JWT 怎么实现注销？ | ★★★ |
| 28 | alg=none 攻击是什么？ | ★★★ |
| 29 | OAuth2 四种模式？ | ★★ |
| 30 | 为什么推荐授权码 + PKCE？ | ★★★ |
| 31 | state 参数防什么？ | ★★ |
| 32 | redirect_uri 为什么必须白名单？ | ★★★ |
| 33 | 水平越权和垂直越权？ | ★★ |
| 34 | 越权怎么防？ | ★★★ |
| 35 | 为什么返回 404 而不是 403？ | ★★★ |
| 36 | RBAC 和 ABAC 的区别？ | ★★ |
| 37 | 单点登录怎么实现？ | ★★★ |
| 38 | 多因素认证怎么做？ | ★★ |

### D. 密码与加密（10）

| # | 问题 | 难度 |
| --- | --- | --- |
| 39 | 密码为什么不能用 MD5？ | ★★ |
| 40 | bcrypt 的 cost 是什么？ | ★★ |
| 41 | 盐和 pepper 的区别？ | ★★★ |
| 42 | AES 的 ECB 和 GCM 区别？ | ★★★ |
| 43 | IV 为什么要随机？ | ★★★ |
| 44 | 对称和非对称怎么配合？ | ★★ |
| 45 | HTTPS 握手过程？ | ★★ |
| 46 | 证书固定是什么？ | ★★★ |
| 47 | 国密算法有哪些？ | ★★ |
| 48 | 密钥怎么管理？ | ★★★ |

### E. 接口与数据（12）

| # | 问题 | 难度 |
| --- | --- | --- |
| 49 | 接口签名怎么设计？ | ★★★ |
| 50 | 防重放怎么做？ | ★★★ |
| 51 | 防重复提交的四种方案？ | ★★ |
| 52 | 参数校验为什么后端必须做？ | ★★ |
| 53 | 敏感数据怎么脱敏？ | ★★ |
| 54 | 日志里不能打什么？ | ★★ |
| 55 | 多租户怎么隔离？ | ★★★ |
| 56 | 数据出境要注意什么？ | ★★ |
| 57 | 被拖库怎么止损？ | ★★★ |
| 58 | 依赖漏洞怎么管？ | ★★ |
| 59 | Actuator 暴露有什么风险？ | ★★★ |
| 60 | 安全开发生命周期怎么落地？ | ★★★ |

---

## 十、上线安全检查清单

### 10.1 接口层

- [ ] 每个接口都有鉴权（没有"内部接口不用鉴权"）
- [ ] 资源查询带归属条件（防水平越权）
- [ ] 管理接口有角色校验（防垂直越权）
- [ ] 所有参数都有校验（类型/范围/长度/枚举）
- [ ] 敏感接口有限流
- [ ] 错误信息不泄露内部细节（堆栈、SQL、IP）

### 10.2 数据层

- [ ] SQL 全部预编译，`${}` 仅用于白名单字段
- [ ] 密码用 bcrypt/Argon2
- [ ] 敏感字段加密存储
- [ ] 返回数据脱敏
- [ ] 日志脱敏
- [ ] 多租户隔离（含缓存 key）

### 10.3 配置层

- [ ] CORS 白名单（不是 `*`）
- [ ] Cookie `HttpOnly` + `Secure` + `SameSite`
- [ ] Actuator/Swagger 生产关闭或鉴权
- [ ] 默认密码已修改
- [ ] 依赖无已知高危漏洞
- [ ] HTTPS 强制（HSTS）

### 10.4 运维层

- [ ] 有安全告警（异常登录、批量导出、越权尝试）
- [ ] 有审计日志
- [ ] 有应急联系人/流程
- [ ] 定期渗透测试/漏洞扫描

---

## 写在最后

后端安全面试的得分点不是"知道多少漏洞名字"，而是：

1. **知道每个入口都不可信**（参数、Header、回调、文件）。
2. **知道每一层该做什么**（网关/过滤器/服务/DAO）。
3. **知道最常出的是越权和业务逻辑漏洞**，不是高深攻击。
4. **能说清一个真实场景的防护设计**（签名、防重放、脱敏、多租户）。

如果只有一小时：背熟 **SQL 预编译 + 越权防护 + JWT 五坑 + 签名防重放 + 脱敏**，这五块覆盖了 70% 的后端安全面试。
