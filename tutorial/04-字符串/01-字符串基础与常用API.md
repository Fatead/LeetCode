# 01 字符串基础与常用 API

> **一句话理解**：字符串题很少考算法，考的是**你对 `String` 不可变性的敬畏**——凡是"循环里拼字符串"的写法，都必须换成 `StringBuilder` 或 `char[]`。

---

## 一、什么时候能用 / 什么时候不能用

字符串题没有"算法适用条件"，取而代之的是**容器选择**判定。

| 题目特征 | 该用什么 | 不该用什么 |
| --- | --- | --- |
| 只读、只做几次拼接 | `String` | — |
| 结果长度会增长（拼接、当栈、当缓冲区） | `StringBuilder` | ❌ `String +=` |
| 原地修改、力扣签名就是 `char[]` | `char[]` | ❌ 先转 `String` 再转回来 |
| 要在**头部**插入 | 末尾追加 + 最后 `reverse` | ❌ `sb.insert(0, c)`，`O(n)` |
| 要排序统计字符 | `Arrays.sort(char[])` | ❌ `TreeMap` |
| 按 ASCII 值直接当下标 | `int[26]` / `int[128]` | ❌ `HashMap<Character,Integer>` |

**判定口诀**：**"长度会变 → `StringBuilder`；原地改 → `char[]`；其余 → `String`。"**

| ❌ 不能写的写法 | 为什么 | ✅ 替代 |
| --- | --- | --- |
| `for (...) s += c;` | `O(n^2)`，`n=10^5` 必超时 | `sb.append(c)` |
| 循环里 `s = s.substring(0, s.length()-1)` | 每次都新建串 | `sb.deleteCharAt(sb.length()-1)` |
| `sb.insert(0, c)` 在循环里 | 每次搬移整块内存 | 先 append，最后 `reverse` |
| `s.replaceAll(" ", "%20")` | 走正则引擎，慢且怕元字符 | `replace`（字面量）或手写遍历 |
| `s.length` | 编译错误 | `s.length()` |
| `s1 == s2` 比内容 | 只比引用，结果随机 | `s1.equals(s2)` |

> ⚠️ `s.toCharArray()` 是**复制**，改它不影响 `s`；`new String(chars)` 也是复制。想"原地"就一开始用 `char[]` 干活，别来回转。

---

## 二、Java 通用模板：字符串题的万能骨架

所有字符串题都能塞进这五步。**先背骨架，再往格子里填逻辑**：

```java
/**
 * 字符串题万能骨架
 * ① 边界：空串 / null / 长度不足，先返回
 * ② 选容器：要增长用 StringBuilder，要原地改用 char[]，只读用 String
 * ③ 预处理：计数（数组/Map）、trim、split、toCharArray
 * ④ 主循环：一个下标 → 顺序扫描；两个下标 → 对撞/快慢；区间 → 滑动窗口
 * ⑤ 输出：别忘 toString() / 去掉多余分隔符
 */
public String solve(String s) {
    // ① 边界
    if (s == null || s.length() == 0) {
        return "";
    }

    // ② 容器：预留容量，避免扩容复制
    StringBuilder sb = new StringBuilder(s.length());

    // ③ 预处理：字母表计数（小写字母题的标准动作）
    int[] cnt = new int[26];
    for (int i = 0; i < s.length(); i++) {
        cnt[s.charAt(i) - 'a']++;
    }

    // ④ 主循环
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (cnt[c - 'a'] == 1) {          // 举例：只保留出现一次的字符
            sb.append(c);
        }
    }

    // ⑤ 输出
    return sb.toString();
}
```

### 逐条解释：边界、初始化、循环条件为什么这样写

**① 为什么空串要单独判，而不能靠循环兜住？**
因为只要方法体里出现 `s.charAt(0)` / `s.charAt(len-1)` / `s.substring(1)`，空串就会崩（下标越界）。循环只能兜住"纯线性扫描"型题目。
**规则**：出现了固定的下标访问，就先写边界判断。

**② 为什么 `new StringBuilder(s.length())` 要传容量？**
`StringBuilder` 默认容量 16，超出后**翻倍扩容并复制**。已知结果不超过原串长度时直接给 `s.length()`，一次扩容都不会发生。
**注意**：结果是**膨胀**的（如替换空格 1 变 3）要传 `s.length() * 3`。

**③ 为什么计数数组优于 `HashMap`？**
`cnt[c - 'a']++` 是一次数组访问；`HashMap` 是一次哈希 + 可能的装箱。比较两个计数数组只需循环 26 次，与串长无关。
**不够用时**：字符集不确定就开 `int[128]`；真 Unicode 才用 `HashMap<Character, Integer>`。

**④ `charAt` 还是 `toCharArray`？**

```java
for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); }   // 每次带一次边界检查
char[] arr = s.toCharArray();                                     // 一次性复制，之后是纯数组访问
for (int i = 0; i < arr.length; i++) { char c = arr[i]; }
```

后者更快（少 `n` 次边界检查），代价是多一份内存。**要原地修改时必须用后者。**

**⑤ 为什么"去掉多余分隔符"是高频坑？**
`StringBuilder` 拼结果最常见的是"每个元素后面都加分隔符"，最后多一个。收尾方式对比：

| 收尾写法 | 是否推荐 | 说明 |
| --- | --- | --- |
| `sb.setLength(sb.length() - 1)` | ✅ | `O(1)`，语义最精确 |
| `sb.deleteCharAt(sb.length() - 1)` | ✅ | 等价 |
| `sb.toString().trim()` | ⚠️ | 只在分隔符是空格时有效；分隔符是 `-` 就白写 |
| `sb.toString().substring(0, len-1)` | ⚠️ | 多复制一次，`sb` 为空时崩 |

---

## 三、常用 API 速查表

### 3.1 `String`：必须闭眼能写

| 方法 | 坑 |
| --- | --- |
| `length()` | **是方法不是字段** |
| `charAt(i)` | `i ∈ [0, len)`，越界抛 `StringIndexOutOfBoundsException` |
| `substring(l, r)` | **左闭右开**；`l == r` 返回 `""`；`l > r` 抛异常 |
| `substring(l)` | `l == len` 合法，返回 `""` |
| `indexOf` / `lastIndexOf` | 找不到返回 **-1**，不是 0 |
| `contains` / `startsWith` / `endsWith` | `startsWith(prefix, from)` 可指定起点 |
| `equals` / `equalsIgnoreCase` | **必须用它，不能用 `==`** |
| `compareTo` | 返回差值，**不保证是 -1/0/1** |
| `toCharArray()` | 返回副本 |
| `toLowerCase` / `toUpperCase` | 有 Locale 重载 |
| `trim()` | 只去首尾 ≤ U+0020，**不去中间** |
| `split(regex)` | **参数是正则**，`.` `|` `*` `+` `?` `\` 都要转义 |
| `replace` vs `replaceAll` | 前者字面量、后者正则 |
| `String.valueOf(x)` | 比 `x + ""` 更快更清晰 |
| `String.join` | `String.join("-", list)` |

### 3.2 `StringBuilder`：名字和 `String` 不一样

| 方法 | 说明 |
| --- | --- |
| `append(x)` | 支持 `char`/`String`/`int`/`char[]`/`StringBuilder` |
| `append(cs, from, to)` | 追加字符数组一段，**左闭右开** |
| `charAt(i)` / `setCharAt(i, c)` | `String` 做不到的写操作 |
| `insert(i, x)` / `deleteCharAt(i)` / `delete(l, r)` | 全是 `O(n)`，别放循环里 |
| `reverse()` | 原地反转，**返回自身**，可接 `.toString()` |
| `length()` / `setLength(n)` | `setLength(0)` 是清空的最快方式 |
| `toString()` | 别忘了 |

> `new StringBuilder(s).reverse().toString()` 一行搞定整串反转——但**不能**替代"只反转一部分"，那种必须双指针。

### 3.3 `Character` 工具类

| 方法 | 说明 |
| --- | --- |
| `isLetter(c)` | ⚠️ `'中'` 也返回 `true`，不限于 `a-z A-Z` |
| `isDigit(c)` | 只认 `'0'`~`'9'` |
| `isLetterOrDigit(c)` | 回文题标配 |
| `isUpperCase` / `isLowerCase` | 大小写判断 |
| `toLowerCase` / `toUpperCase(c)` | 单字符转换 |
| `isWhitespace(c)` | 含 `\t` `\n` |

**只要 `a-z A-Z` 就手写区间**：`(c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')`。这是回文题的隐蔽坑。

---

## 四、`char[]` 互转与字母表计数通用套路

### 4.1 互转

```java
char[] a = s.toCharArray();            // 复制，O(n)
String b = new String(a);              // 复制，O(n)
String c = String.valueOf(a);          // 等价
String d = new String(a, 1, 3);        // 取 a[1..3] 共 3 个字符

Arrays.sort(a);                        // 原地字典序排序，O(n log n)
String e = new String(a);              // "ehllo" —— 最快的字符统计手段
```

### 4.2 计数数组的三种形态

```java
// 形态 1：只含小写字母 → 26（最高频）
int[] cnt = new int[26];
for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;

// 形态 2：字符集不确定 → 直接拿 ASCII 值当下标，最省心
int[] cnt128 = new int[128];
for (int i = 0; i < s.length(); i++) cnt128[s.charAt(i)]++;

// 形态 3：判异位词 —— 一边加一边减，最后全 0
boolean isAnagram(String a, String b) {
    if (a.length() != b.length()) return false;
    int[] cnt = new int[26];
    for (int i = 0; i < a.length(); i++) {
        cnt[a.charAt(i) - 'a']++;
        cnt[b.charAt(i) - 'a']--;
    }
    for (int x : cnt) {
        if (x != 0) return false;
    }
    return true;
}
```

**为什么"一边加一边减"是对的？** 异位词要求每种字符个数相同。`a` 贡献 +1、`b` 贡献 -1；长度相等保证了不会"凭空多出字符"，所以"所有桶归零"等价于"两个串的多重集合完全相同"。

### 4.3 把计数数组编码成 Map 的 key

```java
/** 异位词会得到完全相同的签名 */
private String signature(String s) {
    int[] cnt = new int[26];
    for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
    StringBuilder sb = new StringBuilder();
    for (int x : cnt) sb.append(x).append(',');   // 分隔符一定不能省
    return sb.toString();
}
```

> ⚠️ **分隔符不能省**：`{1, 12}` 和 `{11, 2}` 不加分隔符都拼成 `"112"`，签名直接撞车。

---

## 五、大小写处理与 `compareTo` 字典序

### 5.1 两种大小写转换

```java
char lower(char c)  { return (c >= 'A' && c <= 'Z') ? (char) (c + 32) : c; }  // 'A'=65, 'a'=97
char lower2(char c) { return Character.toLowerCase(c); }                       // 能处理 Unicode
```

| 维度 | 手写 `+32` | `Character.toLowerCase` |
| --- | --- | --- |
| 速度 | 最快 | 略慢（分支 + Unicode 表查询） |
| 正确性 | 只对 `A-Z` 正确 | Unicode 正确 |
| 用在哪 | 明确只含 ASCII 字母 | 字符集不确定 |

### 5.2 `compareTo` 的三条性质

```java
"apple".compareTo("banana")   // 负数  —— 'a'(97) - 'b'(98)
"abc".compareTo("abc")        // 0
"ab".compareTo("abc")         // 负数  —— 短串是长串前缀时更小
"A".compareTo("a")            // 负数  —— 'A'(65) < 'a'(97)
"10".compareTo("9")           // 负数  —— 逐字符比，不解析数字！
```

1. **返回值只保证正/零/负**，判断要写 `< 0` / `== 0` / `> 0`，不能写 `== -1`。
2. **短串是长串前缀时更小**（`"ab" < "abc"`），这条决定了字典序排序的结果。
3. **逐字符比较，不解析数字**：`"10" < "9"`。要按数值排就自己写比较器。

```java
Arrays.sort(words);                                  // String 实现了 Comparable
Arrays.sort(words, Comparator.reverseOrder());       // 逆字典序
```

> `char` 也能直接比大小（`'a' < 'b'` 就是 `97 < 98`），所以 `c >= 'a' && c <= 'z'` 合法且比 `Character.isLowerCase` 快。

---

## 六、KMP 简介（了解即可，必须知道它解决什么）

### 6.1 暴力为什么慢

```
haystack = a a a a a a a b
needle   = a a a b
                 ↑ 前 3 位匹配成功，第 4 位失败
暴力：主串指针回退到 i+1 重新比 → 主串指针来回走，O(n*m)
KMP ：主串指针永不回退，靠 next 数组决定"模式串该退到哪一位"
```

### 6.2 `next` 数组的含义

**`next[i]` = `needle[0..i]` 的「最长相等前后缀」的长度。**

```
needle = a a b a a b
下标      0 1 2 3 4 5
next      0 1 0 1 2 3
          ↑           ↑
      "aa" 的相等前后缀 "a"，长度 1
                    "aabaab" 的相等前后缀 "aab"，长度 3
```

**作用**：在模式串第 `j` 位失配时，说明 `needle[0..j-1]` 已匹配。它有一段长度 `next[j-1]` 的相同前后缀，这段前缀不必重比，直接把 `j` 退到 `next[j-1]`。

### 6.3 完整模板

```java
public int strStr(String haystack, String needle) {
    if (needle.isEmpty()) return 0;
    int n = haystack.length(), m = needle.length();
    if (m > n) return -1;

    // ① 构造 next：本质是"模式串自己匹配自己"
    int[] next = new int[m];
    for (int i = 1, j = 0; i < m; i++) {
        while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
            j = next[j - 1];                       // 失配 → 退到上一个最长前后缀
        }
        if (needle.charAt(i) == needle.charAt(j)) {
            j++;                                   // 匹配上 → 前后缀长度 +1
        }
        next[i] = j;
    }

    // ② 匹配：主串指针 i 单调递增，永不回退
    for (int i = 0, j = 0; i < n; i++) {
        while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
            j = next[j - 1];                       // 只退模式串指针
        }
        if (haystack.charAt(i) == needle.charAt(j)) {
            j++;
        }
        if (j == m) {
            return i - m + 1;                      // 匹配完成，返回起始下标
        }
    }
    return -1;
}
```

**复杂度**：时间 `O(n + m)`，空间 `O(m)`。

> **取舍**：`m < 100` 时暴力就够，直接写暴力更稳。**只有面试官明确要求线性时间时才上 KMP**，但必须能说清 `next` 的含义——这是最爱追问的点。

---

## 七、`split`、正则、`String.format` 的坑

### 7.1 `split` 的四个坑

```java
"1.2".split(".")          // ❌ ["", ""]  —— "." 匹配任意字符
"1.2".split("\\.")        // ✅ ["1", "2"]

"a,b,,".split(",")        // ["a", "b"]         —— 末尾空串被丢弃！
"a,b,,".split(",", -1)    // ["a", "b", "", ""] —— limit = -1 才保留

"".split(",").length      // 1，不是 0（结果是 [""]）

"a  b".split(" ")         // ["a", "", "b"]     —— 连续分隔符产生空串，要自己过滤
```

这四条正是 [434. 字符串中的单词数](#84-434-字符串中的单词数) 里 `split(" ")` 之后还要判 `length != 0` 的原因。

### 7.2 `replaceAll` / `matches` 的坑

```java
s.replaceAll("b", "$")        // ❌ 替换串里 $ 是反向引用，抛 IllegalArgumentException
s.replaceAll("b", "\\$")      // ✅ 或 Matcher.quoteReplacement("$")

s.replaceAll(" ", "%20")      // 能跑，但走正则引擎
s.replace(" ", "%20")         // ✅ 字面量替换，快得多

"abc".matches("b")            // false —— matches 是整体匹配
"abc".matches(".*b.*")        // true

"a1b2".replaceAll("\\d.*\\d", "-")    // 贪婪：.* 吃到最后一个数字
"a1b2".replaceAll("\\d.*?\\d", "-")   // 非贪婪：.*? 只吃到最近的
```

### 7.3 `String.format` 的坑

```java
String.format("%d", 42)             // "42"    —— %d 只吃整型
String.format("%.2f", 3.14159)      // "3.14"  —— %.2f 只吃浮点，传 int 抛异常
String.format("%,d", 1234567)       // "1,234,567" —— 受默认 Locale 影响！
String.format("%03d", 1)            // "001"

// 不依赖格式化引擎的补零写法：
String s = "000" + 1;                       // "0001"
s = s.substring(s.length() - 3);            // "001"
```

> **结论**：**刷题时不要用 `String.format`**。它的重载解析（`%d` 只吃整型、`%.2f` 只吃浮点）、Locale 依赖、`IllegalFormatConversionException` 都是纯负担；`StringBuilder` 手写永远最直白。

---

## 八、典型例题

### 8.1 709. 转换成小写字母

**题意**：把所有大写字母变小写，其余不变。**考点是不许用库函数**，所以落回 `char[]` 原地改。

```java
public String toLowerCase(String s) {
    char[] chars = s.toCharArray();                 // ① 转 char[] 才能原地改
    for (int i = 0; i < chars.length; i++) {
        if (chars[i] >= 'A' && chars[i] <= 'Z') {   // ② 只处理 ASCII 大写
            chars[i] = (char) (chars[i] + 32);      // ③ 必须强转，char+int 结果是 int
        }
    }
    return new String(chars);                       // ④ 转回 String
}
```

> 参考实现：[src/string/ToLowerCase.java](../src/string/ToLowerCase.java)

**复杂度**：时间 `O(n)`，空间 `O(n)`（`toCharArray` 是复制）。想真做到 `O(1)` 额外空间，得把签名改成 `void toLowerCase(char[] s)`。

**易错点**：`chars[i] + 32` 是 `int`，**不加 `(char)` 强转编译不过**。

*转换成小写字母是【`char[]` 原地模拟】，关键突破点是 `'A' + 32 == 'a'` 这条 ASCII 关系，边界是"只改 `A-Z`、其余原样输出"。*

### 8.2 520. 检测大写字母

**题意**：合法的用法只有三种——全大写（`USA`）、全小写（`leetcode`）、只有首字母大写（`Google`）。

**思路演进**：

```
暴力：按"首字母是否大写"分三个分支，每个分支各扫一遍 → 三份循环，容易漏
优化：先一遍扫出大写字母个数 upper，三种情况退化成三个常数比较 ✅
```

**优化的那一步消除了什么冗余**：原本要在不同分支里重复扫描；现在扫一遍拿到 `upper`，判断变成 `O(1)`。

```java
public boolean detectCapitalUse(String word) {
    int upper = 0;
    for (int i = 0; i < word.length(); i++) {
        if (Character.isUpperCase(word.charAt(i))) {
            upper++;
        }
    }
    if (upper == word.length()) return true;                      // 全大写
    if (upper == 0) return true;                                  // 全小写
    return upper == 1 && Character.isUpperCase(word.charAt(0));   // 仅首字母大写
}
```

> 参考实现：[src/string/DetectCapital.java](../src/string/DetectCapital.java)（仓库版按首字母分三个分支各扫一遍，功能正确但不如上面紧凑）

**复杂度**：时间 `O(n)`，空间 `O(1)`。

**边界**：长度为 1 时 `upper` 是 1 或 0，三个条件必命中一个 → `true`，**不需要特判**。

*检测大写字母是【计数 + 分类判定】，关键突破点是"先数大写个数，把三种情况变成三个常数比较"，边界是长度为 1 时天然返回 true。*

### 8.3 剑指 Offer 05. 替换空格

**题意**：把每个空格替换成 `%20`。

**思路演进**：

```
写法 A：s.replaceAll(" ", "%20")   → 正确，但走正则；替换串含 $ 或 \ 会出问题
写法 B：split(" ") 再 join         → ❌ 末尾连续空格会被 split 丢掉（见 7.1）
写法 C：StringBuilder 一遍遍历     → ✅ 正确、O(n)、不依赖任何库语义
写法 D：预分配 3 倍空间的 char[] 倒序倒填 → 面试官问"能不能原地"时给这个
```

```java
// 写法 C（推荐）
public String replaceSpace(String s) {
    StringBuilder sb = new StringBuilder(s.length() * 3);   // 预留 3 倍，零扩容
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == ' ') sb.append("%20");
        else sb.append(c);
    }
    return sb.toString();
}

// 写法 D（原地，O(n)）
public String replaceSpace2(String s) {
    int spaces = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == ' ') spaces++;
    }
    char[] res = new char[s.length() + spaces * 2];   // 每个空格多占 2 个字符
    int fast = s.length() - 1;
    int slow = res.length - 1;
    while (fast >= 0) {                               // 从后往前填，不需要担心覆盖未处理区
        if (s.charAt(fast) == ' ') {
            res[slow--] = '0';
            res[slow--] = '2';
            res[slow--] = '%';                        // 倒着写：%20 反过来是 0 2 %
        } else {
            res[slow--] = s.charAt(fast);
        }
        fast--;
    }
    return new String(res);
}
```

> 参考实现：[src/string/ReplaceSpace.java](../src/string/ReplaceSpace.java)（仓库版就是写法 A 的一行 `replaceAll`；面试建议写 C 并主动说明为什么不用 `replaceAll`）

**复杂度**：两者都是时间 `O(n)`、空间 `O(n)`（结果本身）。

**易错点**：写法 D 倒着写字符的顺序是 `'0'`、`'2'`、`'%'`，写成 `'%'`、`'2'`、`'0'` 会得到 `02%`。

*替换空格是【`StringBuilder` 膨胀式构建】，关键突破点是"空格 1 变 3、预留 3 倍容量"，边界是空串时 `sb.toString()` 天然返回空串。*

### 8.4 434. 字符串中的单词数

**题意**：数出"连续非空格字符"构成的单词个数。

**思路演进**：

```
写法 A：split(" ") 再过滤空串
        → "a  b".split(" ") = ["a","","b"]，必须判 length != 0；" " 得到 [] 天然正确
写法 B：单下标，只在"单词开头"计数 ✅
        → 当前字符非空格 且（i == 0 或 s[i-1] == ' '）
```

**为什么"不会漏也不会重"**：

```
s = "  Hello   World  "
下标   0 1 2 3 4 5 6 7 8 9 10
字符   ' ' ' ' H e l l o ' ' ' ' ' W ...
i=2 : 'H' 非空格，s[1]==' ' → 计数 1（Hello 开头）
i=3..6: 非空格，但前一个也是非空格 → 不计
i=9 : 'W' 非空格，s[8]==' ' → 计数 2（World 开头）

每个单词"唯一一个"满足条件的位置就是它的首字符 → 不重不漏 ✅
```

```java
public int countSegments(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
            count++;
        }
    }
    return count;
}
```

> 参考实现：[src/string/CountSegments.java](../src/string/CountSegments.java)（仓库版用 `split(" ")` + 过滤空串，功能正确；写法 B 空间 `O(1)` 且能顺带讲清 `split` 的坑）

**复杂度**：时间 `O(n)`，空间 `O(1)`（仓库版 `split` 会额外创建 `O(n)` 数组并走正则引擎）。

**边界**：`""` → 0；`"   "` → 0；`"a"` → 1。三者都靠 `i == 0` 这一个条件兜住。

*字符串中的单词数是【单指针对"单词开头"计数】，关键突破点是"只在 前一个是空格 时计数"，边界是空串与纯空格都返回 0。*

### 8.5 824. 山羊拉丁文

**题意**：按空格拆词，元音开头加 `"ma"`，否则首字母移到末尾再加 `"ma"`；第 `i` 个单词末尾追加 `i` 个 `'a'`。

```java
public String toGoatLatin(String sentence) {
    String[] words = sentence.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
        String w = words[i];
        char first = Character.toLowerCase(w.charAt(0));
        if ("aeiou".indexOf(first) >= 0) {                  // ① 元音表用 indexOf 判
            sb.append(w);
        } else {
            sb.append(w.substring(1)).append(w.charAt(0));  // ② 首字母搬到末尾
        }
        sb.append("ma");
        for (int k = 0; k <= i; k++) {
            sb.append('a');                                 // ③ 第 i+1 个单词加 i+1 个 'a'
        }
        sb.append(' ');                                     // ④ 每个单词后都加分隔符
    }
    sb.setLength(sb.length() - 1);                          // ⑤ O(1) 删掉最后一个空格
    return sb.toString();
}
```

> 参考实现：[src/string/GoatLatin.java](../src/string/GoatLatin.java)（仓库版把元音放进 `char[]` 逐个比较，等价；收尾用的是 `trim()`）

**复杂度**：时间 `O(输出长度)`，空间 `O(n)`。

**易错点**：
- 元音判断**必须忽略大小写**，否则 `"I"` 会被当作辅音。
- `w.substring(1)` 在单词长度为 1 时返回 `""`，**不崩**，正好正确。
- 收尾用 `setLength(len-1)` 而不是 `trim()`——后者只在分隔符是空格时才对。

*山羊拉丁文是【`StringBuilder` 模拟 + 分隔符收尾】，关键突破点是"用 `\"aeiou\".indexOf(c)` 做元音判断"，边界是单词长度为 1 时 `substring(1)` 返回空串。*

### 8.6 剑指 Offer 58 - II. 左旋转字符串

**题意**：把 `s` 的前 `n` 个字符移到末尾。`"abcdefg"`, `n=2` → `"cdefgab"`。

**思路演进**：

```
思路 A：return s.substring(n) + s.substring(0, n);   一行，O(n)
思路 B：三次反转（面试官爱问的"原地"版本），空间 O(1)
        reverse(0, n-1) → reverse(n, len-1) → reverse(0, len-1)
```

**思路 B 的正确性**：设 `s = AB`（`A` 长 `n`，`B` 长 `len-n`），目标 `BA`。

```
reverse(A)  →  AʳB
reverse(B)  →  AʳBʳ
reverse(整体) → (AʳBʳ)ʳ = BA   ✅
```

```java
public String reverseLeftWords(String s, int n) {
    if (s == null || s.length() == 0) return "";
    n = n % s.length();                     // ① n 可能 >= len，先取模（必须在判空之后）
    char[] chars = s.toCharArray();
    reverse(chars, 0, n - 1);               // ② 反转前 n 个
    reverse(chars, n, chars.length - 1);    // ③ 反转剩下的
    reverse(chars, 0, chars.length - 1);    // ④ 整体反转
    return new String(chars);
}

/** 反转 chars[l..r]，闭区间 */
private void reverse(char[] chars, int l, int r) {
    while (l < r) {
        char t = chars[l];
        chars[l] = chars[r];
        chars[r] = t;
        l++;
        r--;
    }
}
```

> 参考实现：[src/string/ReverseLeftWords.java](../src/string/ReverseLeftWords.java)
>
> ⚠️ **仓库版有一个真实 bug**：`return s.substring(n) + s.substring(0, n - 1);` 的第二个参数少算了 1。`s = "abcdefg"`, `n = 2` 时仓库版返回 `"cdefga"`（丢了 `'b'`），正确答案是 `"cdefgab"`。**`substring(l, r)` 的 `r` 是开区间，取前 n 个字符要写 `substring(0, n)`。** 另外 `n % s.length()` 必须放在空串判断之后，否则除零。

**复杂度**：时间 `O(n)`，空间 `O(n)`（`char[]`）；若签名允许 `char[]` 入参则是 `O(1)`。

**边界**：`n == 0` 或 `n == len` 时三次反转互相抵消 → 原串；`n > len` 取模后正确。

*左旋转字符串是【三次反转】，关键突破点是 `reverse(A)+reverse(B)+reverse(整体) == BA`，边界是 `substring(0, n)` 的左闭右开与 `n % len` 的取模位置。*

---

## 九、易错点清单

| 易错点 | 后果 | 正确做法 |
| --- | --- | --- |
| 循环里 `s += c` | `O(n^2)`，大数据超时 | `StringBuilder.append` |
| `s.length` | 编译错误 | `s.length()` |
| `substring(l, r)` 当闭区间用 | 少一个字符（如 `ReverseLeftWords`） | `r` 是**开区间**，取前 n 个写 `substring(0, n)` |
| `"1.2".split(".")` | 返回 `["", ""]`，全错 | 转义 `split("\\.")` |
| `"a,b,,".split(",")` | 末尾空串丢失 | 需要保留时 `split(",", -1)` |
| `"".split(",").length` | 是 1 不是 0 | 先判空串 |
| 用 `==` 比字符串内容 | 结果不确定，本地过线上挂 | `.equals()`；只有判 `null` 或比 `char` 才用 `==` |
| `chars[i] + 32` 直接赋给 `char` | 编译错误（`int` 不能隐式转 `char`） | `(char) (chars[i] + 32)` |
| `Character.isLetter('中')` | 返回 `true`，回文题误判 | 手写 `a-z A-Z` 区间判断 |
| 计数签名拼接不加分隔符 | `{1,12}` 与 `{11,2}` 碰撞 | 每个数后加 `,` |
| `sb.toString().trim()` 当"删最后分隔符" | 分隔符不是空格时无效 | `sb.setLength(sb.length() - 1)` |
| `replaceAll` 替换串含 `$` `\` | 抛 `IllegalArgumentException` | 字面量用 `replace`，或 `Matcher.quoteReplacement` |
| `n % s.length()` 放在判空之前 | `ArithmeticException: / by zero` | 先判空再取模 |

---

## 十、本节练习

### 入门

| 题号 | 题目 | 关键点 / 模板 | 参考实现 |
| --- | --- | --- | --- |
| 709 | 转换成小写字母 | `char[]` 原地、`+32` | [ToLowerCase.java](../src/string/ToLowerCase.java) |
| 520 | 检测大写字母 | 计数 + 三种情况 | [DetectCapital.java](../src/string/DetectCapital.java) |
| 剑指 Offer 05 | 替换空格 | `StringBuilder` 膨胀 / 原地倒填 | [ReplaceSpace.java](../src/string/ReplaceSpace.java) |
| 434 | 字符串中的单词数 | 单词开头计数 | [CountSegments.java](../src/string/CountSegments.java) |
| 58 | 最后一个单词的长度 | 从后往前扫描 / `split` | [LengthOfLastWord.java](../src/string/LengthOfLastWord.java)、[LastLength.java](../src/string/LastLength.java) |
| 824 | 山羊拉丁文 | `StringBuilder` + 元音表 | [GoatLatin.java](../src/string/GoatLatin.java) |
| 1108 | IP 地址无效化 | 字面量 `replace` | — |
| 771 | 宝石与石头 | 计数数组 / `Set` | [JewelInStone.java](../src/hashTable/JewelInStone.java) |

### 进阶

| 题号 | 题目 | 关键点 / 模板 | 参考实现 |
| --- | --- | --- | --- |
| 剑指 Offer 58 - II | 左旋转字符串 | 三次反转 / `substring` 拼接 | [ReverseLeftWords.java](../src/string/ReverseLeftWords.java) |
| 482 | 密钥格式化 | 先聚合再分组、倒着插 `-` | [LicenseKey.java](../src/string/LicenseKey.java) |
| 1023 | 驼峰式匹配 | 双指针 + 大写字母必须逐个匹配 | [CamelMatch.java](../src/string/CamelMatch.java) |
| 859 | 亲密字符串 | 差异个数 + 重复字符判断 | [BuddyStrings.java](../src/string/BuddyStrings.java) |
| 389 | 找不同 | 计数数组 / 异或求和 | [FindDifference.java](../src/string/FindDifference.java) |
| 929 | 独特的电子邮件地址 | `split` + 字符串规范化 | [UniqueEmails.java](../src/string/UniqueEmails.java) |
| 804 | 唯一摩尔斯密码词 | `StringBuilder` 拼编码 + `Set` | [UniqueMorse.java](../src/hashTable/UniqueMorse.java) |
| 10 | 正则表达式匹配 | 递归 / DP（`p` 含 `.` `*`） | [ReMatch.java](../src/string/ReMatch.java) |
| 412 | Fizz Buzz | `StringBuilder` 拼接 + 判空回退 | [FizzBuzz.java](../src/string/FizzBuzz.java) |

### 二刷清单（48 小时后重做）

- [ ] 剑指 Offer 58 - II. 左旋转字符串 —— 能默写三次反转，并说清为什么是 `substring(0, n)` 而不是 `n-1`
- [ ] 520. 检测大写字母 —— 能写出"先数大写个数"的一趟写法，不写三个分支
- [ ] 434. 字符串中的单词数 —— 能讲清 `split(" ")` 在 `"a  b"` 和 `" "` 上分别返回什么
- [ ] 剑指 Offer 05. 替换空格 —— 能写出倒序填充的 `char[]` 版本
- [ ] 709. 转换成小写字母 —— 记得写 `(char) (c + 32)` 的强转

---

**下一节 →** [02 双指针与反转](02-双指针与反转.md)
