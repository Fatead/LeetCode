# 13 模拟与设计

> **这一章学什么**：模拟题考"把题意翻译成代码"的细心，设计题考"数据结构选型"的功力，并发题考"线程同步"的理解。它们不靠巧妙算法，靠的是**代码组织能力和工程素养**——这恰恰是面试官最看重的。
> **为什么重要**：设计题（LRU、循环队列、迭代器）是面试区分度最高的题型之一，能看出你是不是真的会写工程代码。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 矩阵模拟](01-矩阵模拟.md) | 螺旋矩阵、旋转图像、对角线遍历、方向数组技巧 | ★★★☆☆ | ★★★☆☆ |
| [02 数据结构设计](02-数据结构设计.md) | 循环队列/双端队列、用栈实现队列、迭代器、随机化设计 | ★★★★☆ | ★★★★★ |
| [03 并发编程](03-并发编程.md) | 多线程交替打印、生产者消费者、同步原语选型 | ★★★★☆ | ★★★☆☆ |

---

## 一、模拟题的通用方法论

模拟题没有"算法"，但有"方法论"：

1. **先在纸上走一遍小样例**，把每一步的状态变化记下来
2. **把"每一步做什么"翻译成循环体**
3. **边界单独列清单**：空输入、单元素、首尾、越界
4. **用变量名表达意图**，比如 `top/bottom/left/right` 四个边界，比 `a/b/c/d` 清晰得多

**模拟题最常见的失分点不是不会，而是粗心**——漏了某个边界、方向数组写反、循环终止条件差一。所以模拟题尤其要先手推样例。

---

## 二、方向数组：矩阵题的通用武器

遍历矩阵的上下左右，不要写四段重复代码，用**方向数组**统一：

```java
int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 上下左右
for (int[] d : dirs) {
    int ni = i + d[0], nj = j + d[1];
    if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
        // 处理 (ni, nj)
    }
}
```

**螺旋遍历的方向切换**：

```java
int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};   // 右下左上（螺旋顺序）
int dir = 0;                                          // 当前方向
// 碰壁或碰到已访问格子时：dir = (dir + 1) % 4 顺时针转向
```

方向数组把"四个方向"的逻辑统一成一个循环，是矩阵题（岛屿、螺旋、BFS）的标准写法。

---

## 三、设计题的核心：数据结构选型

设计题的难点不在实现，在**选对数据结构**。记住这张"需求 → 结构"的映射表：

| 需求 | 选什么 | 典型题 |
| --- | --- | --- |
| O(1) 头尾增删 | 双向链表 / `ArrayDeque` | 循环双端队列、LRU |
| O(1) 查找 + 保持顺序 | 哈希表 + 双向链表 | LRU 缓存 |
| O(1) 随机取元素 | 数组 + 哈希表（值→下标） | O(1) 随机集合 |
| 动态求中位数 | 两个堆（大顶 + 小顶） | 数据流中位数 |
| 固定容量循环复用 | 数组 + 头尾指针取模 | 循环队列 |
| 栈模拟队列 / 队列模拟栈 | 两个栈 / 两个队列 | 用栈实现队列 |

> **LRU 缓存**（哈希 + 双向链表）在 [02 哈希表/04 设计数据结构](../02-哈希表/README.md) 已详细讲过，本章不重复，重点讲循环队列、迭代器、随机化设计。

---

## 四、本章学习路径

```
第 1 天  01 矩阵模拟      → 螺旋矩阵 + 旋转图像，掌握方向数组
第 2 天  02 数据结构设计   → 循环队列 + 循环双端队列（数组 + 取模）
第 3 天  02 数据结构设计   → 用栈实现队列、迭代器设计
第 4 天  02 随机化设计     → 打乱数组、按权重随机、水塘抽样
第 5 天  03 并发编程       → 交替打印 FooBar、生产者消费者
第 6 天  本章综合         → 限时做 螺旋矩阵 + 设计循环双端队列
```

---

## 五、参考代码索引

本章实现分布在 `src/queue`、`src/random`、`src/concurrency`、`src/array`、`src/listTable`：

| 知识点 | 仓库文件 |
| --- | --- |
| 循环队列 | [src/queue/MyCircularQueue.java](../../src/queue/MyCircularQueue.java) |
| 循环双端队列 | [src/queue/MyCircularDeque.java](../../src/queue/MyCircularDeque.java) |
| 用栈实现队列（CQueue） | [src/queue/CQueue.java](../../src/queue/CQueue.java) |
| 优先队列（手写堆） | [src/queue/MyPriorityQueue.java](../../src/queue/MyPriorityQueue.java) |
| 嵌套列表迭代器 | [src/listTable/NestedIterator.java](../../src/listTable/NestedIterator.java) |
| Peeking 迭代器 | [src/listTable/PeekingIterator.java](../../src/listTable/PeekingIterator.java) |
| 数据流中位数（双堆） | [src/listTable/MedianFinder.java](../../src/listTable/MedianFinder.java)、[src/listTable/MedianFinder2.java](../../src/listTable/MedianFinder2.java) |
| 按权重/位置随机 | [src/random/RandomIndex.java](../../src/random/RandomIndex.java)、[src/random/RandomNode.java](../../src/random/RandomNode.java) |
| 交替打印 FooBar | [src/concurrency/FooBar.java](../../src/concurrency/FooBar.java)、[src/concurrency/FooBar2.java](../../src/concurrency/FooBar2.java)、[src/concurrency/FooBar3.java](../../src/concurrency/FooBar3.java) |
| 按序打印 | [src/concurrency/PrintInOrder.java](../../src/concurrency/PrintInOrder.java)、[PrintInOrder2.java](../../src/concurrency/PrintInOrder2.java)、[PrintInOrder3.java](../../src/concurrency/PrintInOrder3.java) |
| 生产者消费者 | [src/concurrency/ProduceAndConsume.java](../../src/concurrency/ProduceAndConsume.java)、[ProduceAndConsume2.java](../../src/concurrency/ProduceAndConsume2.java) |
| 零偶奇打印 | [src/concurrency/ZeroEvenOdd.java](../../src/concurrency/ZeroEvenOdd.java) |
| 单例模式 | [src/designPattern/Singleton.java](../../src/designPattern/Singleton.java)、[SingletonHungry.java](../../src/designPattern/SingletonHungry.java) |
| 螺旋矩阵 / 旋转图像 | 见 `src/array` 下对应文件 |

---

**开始 →** [01 矩阵模拟](01-矩阵模拟.md)
