# 操作系统与 Linux 排查专项

> 后端面试对 OS 的要求分两档：
> **业务后端**——能讲清进程/线程/内存/IO 模型，会看常用指标；
> **基础架构/SRE**——能定位到内核行为（缺页、futex、cgroup、TCP 参数）。
>
> 本文档：核心原理 → Linux 命令 → 8 个排查场景 → 容器 → 60 问 → 速查表。

---

## 目录

- [零、面试考法](#零面试考法)
- [一、进程与线程](#一进程与线程)
- [二、内存管理](#二内存管理)
- [三、IO 与文件系统](#三io-与文件系统)
- [四、锁与并发](#四锁与并发)
- [五、Linux 命令与指标](#五linux-命令与指标)
- [六、八个典型排查场景](#六八个典型排查场景)
- [七、容器与 cgroup](#七容器与-cgroup)
- [八、高频 60 问](#八高频-60-问)
- [九、命令速查表](#九命令速查表)

---

## 零、面试考法

### 0.1 三层深度

| 层次 | 问题 | 要求 |
| --- | --- | --- |
| **概念** | 进程和线程的区别？ | 能讲清地址空间、切换成本 |
| **机制** | epoll 为什么高效？ | 能讲内核数据结构 |
| **排查** | 机器 IO 高，怎么定位？ | 能说出命令 + 指标含义 |

### 0.2 和 Java 面试的关系

```
JVM 的线程 = 内核线程（1:1）
JVM 的堆 = mmap 出来的内存
JVM 的 GC = 用户态内存管理
Java NIO = epoll
Java 锁 = futex
→ 理解 OS，才能讲清 JVM 和并发
```

---

## 一、进程与线程

### 1.1 进程 vs 线程 vs 协程

| 维度 | 进程 | 线程 | 协程 |
| --- | --- | --- | --- |
| 地址空间 | 独立 | 共享 | 共享（同线程内） |
| 切换成本 | 高（页表、TLB） | 中（寄存器、栈） | **低（用户态）** |
| 通信 | IPC | 共享内存 + 同步 | 直接共享 |
| 隔离性 | 强 | 弱（一个崩溃全挂） | 弱 |
| 调度 | 内核 | 内核 | 用户态/运行时 |

**追问**："线程切换到底切换了什么？"

```
① 保存/恢复寄存器（PC、SP、通用寄存器）
② 切换栈
③ 更新 TCB
④ 若跨进程：切换页表（CR3）、刷新 TLB
```

**这就是"进程切换比线程贵"的原因。**

### 1.2 进程的状态与创建

```
创建 → 就绪 → 运行 → 阻塞 → 就绪
                  ↓
                终止
```

**fork 的细节**：

```
fork() 创建子进程，复制页表，但用 COW（写时复制）
→ fork 很快，但子进程写内存时会触发缺页 + 复制
→ 这就是 Redis fork 的代价来源
```

**exec**：替换进程的地址空间（加载新程序），PID 不变。

**僵尸进程**：子进程退出但父进程没 `wait()` → 占用 PID 和 PCB。

**孤儿进程**：父进程先退出 → 被 init/systemd 收养。

### 1.3 调度

| 算法 | 说明 |
| --- | --- |
| 先来先服务 | 简单，平均等待长 |
| 短作业优先 | 平均等待短，可能饿死长作业 |
| 时间片轮转 | 公平，上下文切换多 |
| 优先级 | 可能饿死低优先级 |
| **CFS**（Linux 默认） | 按 `vruntime` 选最小，红黑树维护 |

**CFS 的关键**：

```
vruntime += 实际运行时间 × (nice 权重比)
→ nice 越低（优先级越高），vruntime 增长越慢
→ 总是选 vruntime 最小的进程
```

**追问**："nice 值范围？" → -20（最高）到 19（最低），默认 0。

### 1.4 进程间通信（IPC）

| 方式 | 特点 | 适用 |
| --- | --- | --- |
| 管道 pipe | 半双工、父子进程 | shell 管道 |
| 命名管道 FIFO | 无亲缘关系 | 简单通信 |
| 消息队列 | 有边界、可随机读 | 低频 |
| **共享内存** | **最快**（零拷贝） | 大数据量 |
| 信号量 | 同步 | 配合共享内存 |
| 信号 signal | 异步通知 | kill、SIGTERM |
| **Socket** | 跨主机 | 网络/本地 |

**追问**："共享内存为什么快？" → 直接映射同一块物理页，不需要内核拷贝；但需要自己做同步。

### 1.5 线程同步原语

| 原语 | 说明 | 开销 |
| --- | --- | --- |
| 互斥锁 mutex | 睡眠等待 | 中（涉及内核） |
| 自旋锁 spinlock | 忙等 | 低（无切换），但浪费 CPU |
| 读写锁 | 读共享、写独占 | 中 |
| 条件变量 | 等待/通知 | 中 |
| 信号量 | 计数 | 中 |
| **futex** | 用户态快速路径 + 内核慢路径 | 低（无竞争时不进内核） |
| RCU | 读无锁、写复制 | 读极低 |

**futex 的原理**（Java 锁的底层）：

```
无竞争：用户态 CAS 一个 int → 成功就直接拿到锁（不进内核）
有竞争：调用 futex(FUTEX_WAIT) 进入内核睡眠
释放：futex(FUTEX_WAKE) 唤醒
```

**这就是"轻量级锁"快的原因。**

---

## 二、内存管理

### 2.1 虚拟内存

```
每个进程有独立的虚拟地址空间
→ 通过页表映射到物理页
→ 提供隔离、连续地址、按需加载
```

**为什么需要虚拟内存**：

```
① 隔离：进程不能访问别人的内存
② 简化：程序不用关心物理布局
③ 超额分配：可以申请比物理内存更大的空间
④ 共享：多个进程映射同一物理页（共享库）
```

### 2.2 分页与地址翻译

```
虚拟地址 = 页号 + 页内偏移
→ 查页表（多级）→ 物理页框
→ TLB 缓存最近的翻译结果
```

**缺页中断（page fault）**：

| 类型 | 说明 |
| --- | --- |
| 次要缺页（minor） | 页在内存但没映射（如 COW） |
| 主要缺页（major） | 需要读磁盘（慢） |

**追问**："为什么机器内存没满，程序却变慢？" → 可能是 major page fault（文件页被换出/第一次读），或 NUMA 跨节点访问。

### 2.3 页面置换

| 算法 | 说明 |
| --- | --- |
| OPT | 理论最优，不可实现 |
| FIFO | 简单，有 Belady 异常 |
| LRU | 常用，但实现成本高 |
| **Clock（近似 LRU）** | 用访问位 + 环形扫描 |
| LFU | 按频率 |

**Linux 的页回收**：基于 LRU 链表（active/inactive）+ 水位（min/low/high）。

**kswapd**：后台回收线程；**直接回收**：分配内存时发现不足，同步回收（会导致卡顿）。

### 2.4 内存分配

| 方式 | 说明 |
| --- | --- |
| `brk/sbrk` | 扩展堆（小分配） |
| `mmap` | 映射文件/匿名内存（大分配，`malloc` 阈值以上） |
| 内存池 | 应用层预分配（减少系统调用） |

**追问**："`free` 显示内存少，但应用没涨？" → page cache 占用了大部分，可回收（`buff/cache`）。

### 2.5 OOM Killer

```
当内存不足且无法回收 → 内核选择"分数最高"的进程杀死
分数看：内存占用、oom_score_adj
```

```bash
cat /proc/<pid>/oom_score
echo -100 > /proc/<pid>/oom_score_adj   # 保护关键进程（-1000~1000）
dmesg -T | grep -i "killed process"
```

**容器里的 OOMKilled**：cgroup 内存超限 → 杀进程，`kubectl describe pod` 会显示 `OOMKilled`。

### 2.6 内存指标怎么读

```bash
free -h
# total used free shared buff/cache available
# 关键看 available（真正可用，含可回收缓存）

cat /proc/meminfo
# MemAvailable, Dirty, Writeback, SwapCached
```

**Java 进程 RSS 远超 Xmx 的原因**：

```
① 堆外内存（DirectByteBuffer、Netty）
② Metaspace / CodeCache
③ 线程栈（线程数 × 1MB）
④ JVM 自身 + 本地库
⑤ glibc malloc 碎片
```

---

## 三、IO 与文件系统

### 3.1 五种 IO 模型

| 模型 | 阻塞点 | 代表 |
| --- | --- | --- |
| 阻塞 IO | read/write | 传统 socket |
| 非阻塞 IO | 轮询 | 自己循环 |
| **IO 多路复用** | select/poll/epoll | Netty、Redis、Nginx |
| 信号驱动 | 内核通知 | 少用 |
| 异步 IO | 完成后回调 | io_uring |

### 3.2 epoll 的三个关键

```
① epoll_ctl 注册 fd 到红黑树（只注册一次）
② 内核用回调把就绪 fd 放入就绪链表
③ epoll_wait 直接返回就绪列表（O(1)，不用遍历全部）
```

**LT vs ET**：

| 模式 | 行为 | 编程要求 |
| --- | --- | --- |
| LT（水平触发） | 有数据就一直通知 | 可以分次读 |
| ET（边缘触发） | 状态变化才通知一次 | **必须一次读到 EAGAIN** |

**追问**："ET 为什么必须非阻塞？" → 如果只读了一次就阻塞，剩余数据不会再触发事件。

### 3.3 Page Cache

```
读文件：磁盘 → page cache → 用户缓冲区
写文件：用户缓冲区 → page cache（脏页）→ 后台回写
```

**收益**：顺序读命中缓存极快；**风险**：脏页回写导致 IO 抖动、内存被缓存占满（可回收）。

**查看**：

```bash
cat /proc/meminfo | grep -E "Cached|Dirty|Writeback"
```

### 3.4 零拷贝

| 方式 | 减少的拷贝 |
| --- | --- |
| `sendfile` | 用户态拷贝（2 次） |
| `mmap + write` | 一次拷贝（读） |
| `splice` | 内核管道 |
| io_uring | 异步 + 零拷贝 |

**追问**："Kafka 为什么用 sendfile？" → 消息不需要在用户态修改，直接文件 → socket。

### 3.5 文件描述符

```
每个进程有 fd 表，默认上限 ulimit -n（常见 1024 或 65535）
→ 每个 socket、文件、管道都占一个 fd
```

**fd 泄漏的表现**：`Too many open files`。

```bash
ls /proc/<pid>/fd | wc -l
lsof -p <pid> | wc -l
cat /proc/<pid>/limits | grep "open files"
```

### 3.6 inode 与磁盘

```
磁盘满有两种：
① 块满（df -h 100%）
② inode 满（df -i 100%）→ 小文件太多
```

```bash
df -h        # 空间
df -i        # inode
du -sh /* | sort -rh | head
lsof +L1     # 已删除但被占用（空间没释放）
```

---

## 四、锁与并发

### 4.1 内核锁

| 锁 | 场景 |
| --- | --- |
| mutex | 可睡眠的上下文 |
| spinlock | 中断/短临界区 |
| rwlock | 读多写少 |
| RCU | 读极多、写少（网络栈、路由表） |
| seqlock | 读多写少、读可重试 |

### 4.2 死锁

```
四个条件：互斥、持有并等待、不可剥夺、循环等待
```

**排查**：

```bash
# Java 层
jstack <pid> | grep -A 20 "Found one Java-level deadlock"

# 内核层（D 状态进程）
ps -eo pid,stat,wchan,cmd | awk '$2 ~ /D/'
cat /proc/<pid>/stack
```

### 4.3 内存屏障

```
编译器屏障：阻止编译器重排
CPU 屏障：LoadLoad/StoreLoad 等
→ Java 的 volatile 最终映射到这些指令
```

---

## 五、Linux 命令与指标

### 5.1 CPU

```bash
top -Hp <pid>                 # 线程级
pidstat -u -p <pid> 1         # 进程 CPU
mpstat -P ALL 1               # 每核使用率
perf top -p <pid>             # 热点函数
perf record -F 99 -p <pid> -g -- sleep 30 && perf report
```

**关键指标**：

| 指标 | 含义 | 异常 |
| --- | --- | --- |
| `us` | 用户态 | 高 → 业务计算 |
| `sy` | 内核态 | 高 → 系统调用/锁竞争 |
| `wa` | IO 等待 | 高 → 磁盘慢 |
| `si/so` | swap 换入换出 | 非 0 → 内存不足（严重） |
| `load average` | 运行+不可中断队列 | 持续 > CPU 核数 → 过载 |

**注意**：`load` 包含 D 状态（IO 等待），所以**IO 高也会让 load 高**。

### 5.2 内存

```bash
free -h
vmstat 1 5                    # si/so 是关键
smem -r                      # PSS（更准的进程内存）
cat /proc/<pid>/status | grep -E "VmRSS|VmSwap"
pmap -x <pid> | tail -20
```

### 5.3 IO

```bash
iostat -x 1 5
# 关注：r/s w/s, rkB/s wkB/s, await, %util
iotop -oP
lsof -p <pid>
```

| 指标 | 含义 | 异常 |
| --- | --- | --- |
| `await` | 平均等待时间（ms） | > 10ms 偏高 |
| `%util` | 设备忙碌比例 | 接近 100% → 饱和 |
| `svctm` | 服务时间 | 已废弃，慎用 |

### 5.4 网络

```bash
ss -s                          # 汇总
ss -lntp                       # 监听端口
ss -tan state time-wait | wc -l
netstat -s | grep -E "retrans|overflow"
tcpdump -i any -nn port 8080 -w /tmp/x.pcap
mtr -n <host>
iftop -P
```

**关键指标**：

| 指标 | 含义 |
| --- | --- |
| `Recv-Q/Send-Q` | 接收/发送队列积压 |
| `retrans` | 重传率（> 1% 异常） |
| `listen overflows` | accept 队列溢出 |
| `TIME_WAIT` | 主动关闭方等待 2MSL |
| `CLOSE_WAIT` 堆积 | 应用没 close（**fd 泄漏信号**） |

### 5.5 进程与系统调用

```bash
ps -ef --forest
ps -eo pid,ppid,stat,rss,cmd --sort=-rss | head
pstree -p <pid>
strace -f -T -p <pid>          # 跟踪系统调用（慎用，有开销）
ltrace -p <pid>
dmesg -T | tail -50            # 内核日志（OOM、IO error）
journalctl -u <service> --since "10 min ago"
```

### 5.6 文件与磁盘

```bash
df -h / df -i
du -sh /* 2>/dev/null | sort -rh | head
find / -xdev -size +1G -exec ls -lh {} \;
lsof +L1                       # 已删除仍占用
```

---

## 六、八个典型排查场景

### 6.1 CPU 使用率高

```
① top 看 us/sy/wa
② top -Hp 找线程
③ perf top / perf record 找热点函数
④ 若 sy 高 → strace 看系统调用
⑤ 若 wa 高 → iostat 看磁盘
```

**常见原因**：死循环、正则回溯、频繁 GC、锁自旋、序列化、加密。

### 6.2 内存持续上涨

```
① free -h 看 available 和 swap
② 区分：应用 RSS 涨 / page cache 涨 / slab 涨
③ 应用 RSS：/proc/<pid>/status、pmap、jmap（Java）
④ 是否有泄漏：观察是否只涨不降（GC 后仍高）
⑤ 内核态：slabtop 看 dentry/inode 缓存
```

**常见原因**：缓存无界、连接未关闭、ThreadLocal、DirectByteBuffer、JVM 堆泄漏。

### 6.3 IO 高

```
① iostat -x 1 看 await/%util
② iotop -oP 找进程
③ 区分：读多 / 写多 / fsync 多
④ 应用层：慢 SQL、日志刷盘、大文件读写
```

**常见原因**：慢查询、日志同步刷盘、大 key、备份任务、swap。

### 6.4 网络慢 / 丢包

```
① ping / mtr 看链路
② ss -s 看重传、队列
③ tcpdump 抓包分析
④ 看网卡：ethtool -S eth0（drop/error）
⑤ 应用层：RT、超时、连接池
```

**常见原因**：带宽打满、重传、DNS、TLS 握手、连接池、对端限流。

### 6.5 端口耗尽 / TIME_WAIT 过多

```
现象：Cannot assign requested address
原因：短连接 + 主动关闭方大量 TIME_WAIT（默认 60s）
处理：
① 用连接池/长连接
② net.ipv4.tcp_tw_reuse=1（谨慎）
③ 扩大本地端口范围
④ 让服务端主动关闭（TIME_WAIT 分散到服务端）
```

### 6.6 fd 泄漏

```
① ls /proc/<pid>/fd | wc -l
② 按类型统计：ls -l /proc/<pid>/fd | awk '{print $11}' | sort | uniq -c
③ 看是否有 CLOSE_WAIT 的 socket
④ 应用层：连接/流是否关闭
```

### 6.7 磁盘满 / inode 满

```
① df -h / df -i
② du 找大目录
③ lsof +L1 找已删除占用
④ 日志轮转是否生效
```

### 6.8 容器被 OOMKilled

```
① kubectl describe pod → Last State: OOMKilled
② 看 limit 和实际 RSS
③ JVM：MaxRAMPercentage、堆外内存、线程栈
④ 调大 limit 或降内存
```

---

## 七、容器与 cgroup

### 7.1 两个关键技术

```
namespace：隔离（PID/NET/MNT/UTS/IPC/USER）
cgroup：限制（CPU/内存/IO/进程数）
```

### 7.2 常见坑

| 坑 | 说明 |
| --- | --- |
| 容器内 `free` 看到宿主机内存 | 需要读 cgroup 的 `memory.max` / `memory.current` |
| `top` 看到宿主机 CPU | 同上，看 `cpu.max` |
| JVM 默认堆按宿主机算 | 用 `-XX:MaxRAMPercentage` 或 `-XX:+UseContainerSupport` |
| OOMKilled 不是 JVM OOM | 是 cgroup 杀进程，JVM 可能来不及 dump |
| CPU 限流（throttling） | `cpu.stat` 的 `nr_throttled` |

### 7.3 常用命令

```bash
cat /sys/fs/cgroup/memory.max          # v2
cat /sys/fs/cgroup/memory.current
cat /sys/fs/cgroup/cpu.max
cat /sys/fs/cgroup/cpu.stat            # nr_throttled
cat /sys/fs/cgroup/memory.events       # oom 次数

nsenter -t <pid> -n ss -lntp           # 进入容器网络命名空间
```

---

## 八、高频 60 问

### A. 进程线程（15）

| # | 问题 | 难度 |
| --- | --- | --- |
| 1 | 进程和线程的区别？ | ★★ |
| 2 | 线程切换比进程快在哪？ | ★★★ |
| 3 | 协程和线程的区别？ | ★★ |
| 4 | 进程有哪几种状态？ | ★★ |
| 5 | 僵尸进程和孤儿进程？ | ★★ |
| 6 | fork 的 COW 是什么？ | ★★★ |
| 7 | 进程间通信有几种？ | ★★ |
| 8 | 共享内存为什么快？ | ★★★ |
| 9 | 调度算法有哪些？ | ★★ |
| 10 | CFS 的 vruntime 是什么？ | ★★★ |
| 11 | 什么是上下文切换？ | ★★ |
| 12 | 线程数太多会怎样？ | ★★★ |
| 13 | 用户态和内核态怎么切换？ | ★★ |
| 14 | 系统调用为什么有开销？ | ★★★ |
| 15 | 信号和信号量的区别？ | ★★ |

### B. 内存（15）

| # | 问题 | 难度 |
| --- | --- | --- |
| 16 | 虚拟内存的作用？ | ★★ |
| 17 | 页表和多级页表？ | ★★★ |
| 18 | TLB 是什么？ | ★★ |
| 19 | 缺页中断的处理流程？ | ★★★ |
| 20 | minor 和 major page fault？ | ★★★ |
| 21 | 页面置换算法？ | ★★ |
| 22 | Linux 的页回收机制？ | ★★★ |
| 23 | malloc 和 mmap 的关系？ | ★★★ |
| 24 | OOM Killer 怎么选进程？ | ★★★ |
| 25 | free 的 available 是什么？ | ★★ |
| 26 | page cache 和 buffer cache？ | ★★ |
| 27 | 内存碎片怎么产生？ | ★★★ |
| 28 | swap 的利弊？ | ★★ |
| 29 | RSS 和 PSS 的区别？ | ★★★ |
| 30 | Java 进程 RSS 为什么大于 Xmx？ | ★★★ |

### C. IO 与网络（15）

| # | 问题 | 难度 |
| --- | --- | --- |
| 31 | 五种 IO 模型？ | ★★ |
| 32 | select/poll/epoll 的区别？ | ★★★ |
| 33 | epoll 为什么 O(1)？ | ★★★ |
| 34 | LT 和 ET 的区别？ | ★★★ |
| 35 | 零拷贝有几种实现？ | ★★★ |
| 36 | page cache 的作用？ | ★★ |
| 37 | 文件描述符是什么？ | ★★ |
| 38 | inode 是什么？ | ★★ |
| 39 | 软链接和硬链接？ | ★★ |
| 40 | IO 多路复用和异步 IO 的区别？ | ★★★ |
| 41 | io_uring 解决什么？ | ★★★ |
| 42 | 为什么磁盘顺序写快？ | ★★ |
| 43 | fsync 和 fdatasync？ | ★★★ |
| 44 | 直接 IO 的适用场景？ | ★★★ |
| 45 | TCP 半连接队列和全连接队列？ | ★★★ |

### D. 排查与容器（15）

| # | 问题 | 难度 |
| --- | --- | --- |
| 46 | CPU 高怎么排查？ | ★★ |
| 47 | 内存涨怎么排查？ | ★★ |
| 48 | IO 高怎么排查？ | ★★ |
| 49 | 网络丢包怎么排查？ | ★★★ |
| 50 | TIME_WAIT 过多怎么办？ | ★★★ |
| 51 | CLOSE_WAIT 堆积说明什么？ | ★★★ |
| 52 | fd 泄漏怎么查？ | ★★★ |
| 53 | 磁盘满和 inode 满的区别？ | ★★ |
| 54 | load average 高但 CPU 不高？ | ★★★ |
| 55 | 容器 OOMKilled 怎么排查？ | ★★★ |
| 56 | cgroup 怎么限制 CPU/内存？ | ★★★ |
| 57 | 容器里 top 不准怎么办？ | ★★ |
| 58 | 怎么进入容器的网络命名空间？ | ★★★ |
| 59 | dmesg 里能看什么？ | ★★ |
| 60 | strace 有什么风险？ | ★★★ |

---

## 九、命令速查表

| 目的 | 命令 |
| --- | --- |
| CPU 总览 | `top`、`mpstat -P ALL 1` |
| 进程 CPU | `pidstat -u -p <pid> 1`、`top -Hp <pid>` |
| 热点函数 | `perf top -p <pid>`、`perf record -g` |
| 内存总览 | `free -h`、`vmstat 1` |
| 进程内存 | `cat /proc/<pid>/status`、`pmap -x <pid>`、`smem -r` |
| IO | `iostat -x 1`、`iotop -oP` |
| 网络连接 | `ss -lntp`、`ss -s`、`ss -tan state time-wait` |
| 抓包 | `tcpdump -i any -nn port 8080` |
| 链路 | `mtr -n <host>`、`ping`、`tracepath` |
| 系统调用 | `strace -f -T -p <pid>` |
| fd | `ls /proc/<pid>/fd \| wc -l`、`lsof -p <pid>` |
| 磁盘 | `df -h`、`df -i`、`du -sh /*` |
| 已删占用 | `lsof +L1` |
| 内核日志 | `dmesg -T`、`journalctl -k` |
| 容器资源 | `cat /sys/fs/cgroup/memory.max`、`cpu.stat` |
| 进入容器 ns | `nsenter -t <pid> -n <cmd>` |

---

## 写在最后

OS 面试的得分点是**因果链**：

```
现象（CPU 高）→ 区分（us/sy/wa）→ 工具（top -Hp / perf）→ 根因（死循环/GC/锁）
```

不要只背命令，要能说出**"为什么用这个命令、看到什么指标代表什么"**。

如果只有一天：**进程/线程区别 + 虚拟内存 + epoll + 零拷贝 + CPU/内存/IO 三个排查套路**，覆盖 80% 的 OS 面试。
