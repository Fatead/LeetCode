# 05 栈与队列

> **这一章学什么**：栈（LIFO）和队列（FIFO）是最朴素的两种容器，但它们衍生出的**单调栈、单调队列、优先队列**是面试里的硬核考点。本章教你识别"题目里藏着的后进先出/先进先出结构"。
> **为什么重要**：单调栈（下一个更大元素）、单调队列（滑动窗口最大值）是中高频 Hard 的核心武器，表达式解析、设计题也常年出现。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 栈与队列基础](01-栈与队列基础.md) | 栈/队列选型、括号匹配、嵌套消除、Java API 与坑 | ★★☆☆☆ | ★★★★☆ |
| [02 单调栈](02-单调栈.md) | 下一个更大/更小元素、每日温度、柱状图最大矩形、接雨水 | ★★★★☆ | ★★★★★ |
| [03 单调队列](03-单调队列.md) | 滑动窗口最大值、单调递减队列模板 | ★★★★☆ | ★★★★☆ |
| [04 表达式与解析](04-表达式与解析.md) | 逆波兰表达式、基本计算器、字符串解码 | ★★★★☆ | ★★★☆☆ |
| [05 优先队列与设计题](05-优先队列与设计题.md) | 堆的原理、TopK、合并链表、最小栈 | ★★★★☆ | ★★★★★ |

---

## 一、栈与队列的本质

| 结构 | 规则 | 核心操作 | 典型场景 |
| --- | --- | --- | --- |
| **栈 Stack** | 后进先出 LIFO | push / pop / peek | 括号匹配、表达式、DFS、撤销 |
| **队列 Queue** | 先进先出 FIFO | offer / poll / peek | BFS、层序遍历、缓冲区 |
| **双端队列 Deque** | 两端都能进出 | offerFirst/Last... | 单调队列、滑动窗口 |
| **优先队列 PQ** | 按优先级出 | offer / poll（堆序） | TopK、Dijkstra、合并 |

**选型信号**：出现"配对/嵌套/撤销" → 栈；出现"按顺序处理/最短步数" → 队列；出现"滑动取最值" → 单调队列；出现"按大小取 TopK" → 优先队列。

---

## 二、Java 的正确用法

```java
// 栈：统一用 ArrayDeque（不要用过时的 Stack 类）
Deque<Integer> stack = new ArrayDeque<>();
stack.push(x); stack.pop(); stack.peek(); stack.isEmpty();

// 队列：LinkedList 实现 Queue
Queue<Integer> queue = new LinkedList<>();
queue.offer(x); queue.poll(); queue.peek();

// 双端队列：单调队列用它
Deque<Integer> deque = new ArrayDeque<>();
deque.offerFirst(x); deque.offerLast(x); deque.pollFirst(); deque.pollLast();

// 优先队列（堆）
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                 // 小顶堆
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);  // 大顶堆
```

> **为什么不用 `Stack` 类？** Java 的 `java.util.Stack` 继承自 `Vector`，方法都带 `synchronized`（有锁开销），且允许从中间插入破坏栈语义。**一律用 `ArrayDeque` 当栈**，更快更规范。

---

## 三、三个核心模板

### 括号匹配（栈）

```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (c == '(' || c == '[' || c == '{') stack.push(c);
    else {
        if (stack.isEmpty()) return false;
        char top = stack.pop();
        if (!match(top, c)) return false;   // 括号类型不匹配
    }
}
return stack.isEmpty();   // ★ 最后栈必须空，否则有未闭合的左括号
```

### 单调栈（下一个更大元素）

```java
Deque<Integer> stack = new ArrayDeque<>();   // 存下标，栈内对应值单调递减
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
        res[stack.pop()] = nums[i];          // 当前值是栈顶的"下一个更大"
    stack.push(i);
}
```

### 单调队列（滑动窗口最大值）

```java
Deque<Integer> deque = new ArrayDeque<>();   // 存下标，对应值单调递减
for (int i = 0; i < n; i++) {
    while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
    deque.offerLast(i);
    if (deque.peekFirst() <= i - k) deque.pollFirst();   // 队首超出窗口就弹
    if (i >= k - 1) res[i - k + 1] = nums[deque.peekFirst()];  // 队首即窗口最大值
}
```

---

## 四、本章学习路径

```
第 1 天  01 基础      → 括号匹配系列 + 嵌套消除，掌握 ArrayDeque
第 2 天  02 单调栈    → 下一个更大元素、每日温度（单调栈入门）
第 3 天  02 单调栈    → 柱状图最大矩形、接雨水（单调栈 Hard）
第 4 天  03 单调队列  → 滑动窗口最大值
第 5 天  04 表达式    → 逆波兰、基本计算器、字符串解码
第 6 天  05 优先队列  → 堆原理 + TopK + 最小栈
第 7 天  综合         → 限时做 滑动窗口最大值 + 柱状图最大矩形
```

---

## 五、参考代码索引

本章实现在 `src/stack` 和 `src/queue`：

| 知识点 | 仓库文件 |
| --- | --- |
| 有效括号 | [src/stack/ValidString.java](../../src/stack/ValidString.java) |
| 下一个更大元素 / 每日温度 | 见 `src/stack` 单调栈系列 |
| 逆波兰表达式求值 | 见 `src/stack` 下对应文件 |
| 字符串解码 | 见 `src/stack` 下对应文件 |
| 最小栈 | 见 `src/stack` 下对应文件 |
| 用栈实现队列 | [src/queue/CQueue.java](../../src/queue/CQueue.java) |
| 循环队列/双端队列 | [src/queue/MyCircularQueue.java](../../src/queue/MyCircularQueue.java)、[src/queue/MyCircularDeque.java](../../src/queue/MyCircularDeque.java) |
| 手写优先队列 | [src/queue/MyPriorityQueue.java](../../src/queue/MyPriorityQueue.java) |
| 滑动窗口最大值 | [src/listTable/MaxQueue.java](../../src/listTable/MaxQueue.java)、[MaxQueue2.java](../../src/listTable/MaxQueue2.java) |

> `src/stack` 目录有 25 个文件，可按题名搜索对应实现。

---

**开始 →** [01 栈与队列基础](01-栈与队列基础.md)
