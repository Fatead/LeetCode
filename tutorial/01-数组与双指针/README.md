# 01 数组与双指针

> **本章是全套教程的基石。** 二分、双指针、滑动窗口、前缀和这四件套，覆盖了 LeetCode 中约 30% 的题目，而且是后面每一章都会反复用到的工具。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 模板总览与题型判断](01-模板总览与题型判断.md) | 怎么从题目描述锁定模板 | ★☆☆☆☆ | — |
| [02 双指针](02-双指针.md) | 左右指针、快慢指针、对撞指针 | ★★☆☆☆ | ★★★★★ |
| [03 二分查找](03-二分查找.md) | 三种区间写法、边界查找、旋转数组 | ★★★☆☆ | ★★★★★ |
| [04 滑动窗口](04-滑动窗口.md) | 可变窗口、定长窗口、单调队列 | ★★★★☆ | ★★★★★ |
| [05 前缀和与差分](05-前缀和与差分.md) | 区间和、二维前缀和、差分数组 | ★★★☆☆ | ★★★★☆ |
| [06 排序与快速选择](06-排序与快速选择.md) | 快排、归并、TopK、逆序对 | ★★★★☆ | ★★★★☆ |
| [07 原地修改与矩阵遍历](07-原地修改与矩阵遍历.md) | 原地哈希、螺旋矩阵、矩阵旋转 | ★★☆☆☆ | ★★★☆☆ |
| [08 综合练习与题单](08-综合练习与题单.md) | 本章题单 + 二刷清单 | — | — |

---

## 一、为什么先学这一章

数组是**最简单也最常考**的数据结构，原因很实际：

1. **面试第一题大概率是数组题**。面试官需要一个"热身题"来缓解你的紧张，通常就是数组/字符串。
2. **数组题是其他专题的地基**。DP 的初始状态、回溯的路径、树的层序遍历，底层都是数组操作。
3. **数组题最能体现"从暴力到最优"的思维过程**。同一个问题从 `O(n^2)` 优化到 `O(n)` 的手段（哈希、双指针、单调性），就是算法面试的核心考察点。

---

## 二、数组的 Java 基本功

在刷题前，这几个操作必须**闭着眼能写**：

```java
// 1. 创建
int[] a = new int[5];              // 默认全 0
int[] b = {1, 2, 3};
int[][] c = new int[3][4];         // 3 行 4 列，默认全 0
int[][] d = {{1,2},{3,4}};

// 2. 长度（注意：是属性不是方法！）
int n = a.length;                  // 一维
int rows = c.length;               // 行数
int cols = c[0].length;            // 列数（前提：非空）

// 3. 排序（升序，底层是双轴快排，对基本类型是 O(n log n)）
Arrays.sort(a);                    // 一维
// 降序必须用包装类：
Integer[] boxed = {3, 1, 2};
Arrays.sort(boxed, (x, y) -> y - x);

// 4. 二维排序（按第一列升序，第一列相同时按第二列降序）
int[][] points = {{1,2},{3,4},{1,5}};
Arrays.sort(points, (p, q) -> p[0] == q[0] ? q[1] - p[1] : p[0] - q[0]);
// 或写成更安全的写法，避免减法溢出：
Arrays.sort(points, (p, q) -> p[0] == q[0] ? Integer.compare(q[1], p[1]) : Integer.compare(p[0], q[0]));

// 5. 填充与复制
Arrays.fill(a, -1);                        // 全部填 -1
int[] copy = Arrays.copyOf(b, b.length);   // 全量复制
int[] part = Arrays.copyOfRange(b, 1, 3);  // [1,3) 左闭右开
int[] clone = b.clone();                   // 另一种复制

// 6. 转 List / 转数组
List<Integer> list = new ArrayList<>();
for (int x : b) list.add(x);               // 基本类型必须手写循环
Integer[] arr = list.toArray(new Integer[0]);
int[] arr2 = list.stream().mapToInt(Integer::intValue).toArray();

// 7. 二分（自带，返回值：找到返回下标，没找到返回 -(插入点)-1）
int idx = Arrays.binarySearch(b, 3);

// 8. 最大值/最小值/求和（流式，刷题时不如写循环直观）
int max = Arrays.stream(a).max().getAsInt();
int sum = Arrays.stream(a).sum();
```

> ⚠️ **高频坑**：`a.length` 是字段，`s.length()` 是方法。写错会编译失败，面试手写时很尴尬。

---

## 三、本章的四个核心工具（先建立直觉）

### 工具 1：双指针 —— "用两个下标代替两重循环"

```
暴力：for i, for j        →  O(n^2)
双指针：left, right 相向或同向  →  O(n)
```

使用前提：**数组有序**，或者**两个指针的移动方向有单调性**。

### 工具 2：二分查找 —— "每次砍掉一半"

```
线性扫描：O(n)
二分：O(log n)
```

使用前提：**答案具有单调性**（能写出 `check(mid)`，且 `check` 随 `mid` 单调变化）。

> 很多人以为二分只能用于有序数组，其实**二分真正的适用条件是「二段性」**：能把区间分成"满足条件"和"不满足条件"两段。后面 [03 二分查找](03-二分查找.md) 会专门讲这个思想。

### 工具 3：滑动窗口 —— "维护一个满足条件的区间"

```
暴力：枚举所有子数组 O(n^2) 或 O(n^3)
滑动窗口：右指针扩张、左指针收缩，每个元素最多进出窗口一次 O(n)
```

使用前提：**窗口的合法性随窗口扩大/缩小是单调的**（扩张可能变非法，收缩一定能恢复合法）。

### 工具 4：前缀和 —— "把区间求和变成一次减法"

```
暴力：每次查询区间和 O(n)，q 次查询 O(nq)
前缀和：预处理 O(n)，每次查询 O(1)
```

使用前提：**静态数组 + 大量区间查询**。如果数组会被频繁修改，就需要 [线段树/树状数组](01-模板总览与题型判断.md#五进阶工具预告)。

---

## 四、复杂度直觉：数组题的第一道门槛

做题时，**先看数据范围，再想算法**。这是省下最多时间的一条习惯：

| n 的范围 | 你可以写 | 你不能写 |
| --- | --- | --- |
| `n ≤ 100` | `O(n^3)`、`O(n^4)` | — |
| `n ≤ 5000` | `O(n^2)` | `O(n^3)` |
| `n ≤ 10^5` | `O(n log n)` | `O(n^2)` |
| `n ≤ 10^6` | `O(n)` | `O(n log n)` 也得小心 |
| `n ≤ 10^9` | `O(log n)`、`O(1)` | 任何遍历数组的算法 |

> **实战例子**：LeetCode 15「三数之和」的 `n ≤ 3000`。
> - `O(n^3)` = 2.7 × 10^10，**必然超时**
> - `O(n^2)` = 9 × 10^6，**稳过**
> 所以看到 `n ≤ 3000`，你的大脑就应该自动搜索 `O(n^2)` 的解法（排序 + 双指针）。

---

## 五、本章学习路径

```
第 1 天  01 模板总览 + 02 双指针      → 做 5 道双指针题
第 2 天  03 二分查找                  → 默写三种模板，做 6 道题
第 3 天  04 滑动窗口                  → 做 5 道题
第 4 天  05 前缀和与差分              → 做 5 道题
第 5 天  06 排序与快速选择            → 做 4 道题
第 6 天  07 原地修改与矩阵            → 做 4 道题
第 7 天  08 综合练习                  → 限时 60 分钟做 3 道 Medium
```

**每道题都必须**：先自己写 → 卡住看思路 → 看懂后关掉重写 → 记错题本。

---

## 六、参考代码索引

本章涉及的所有仓库实现：

| 知识点 | 仓库文件 |
| --- | --- |
| 二分查找基础 | [src/array/BinarySearch.java](../src/array/BinarySearch.java) |
| 搜索旋转排序数组 | [src/array/SearchRotateArray.java](../src/array/SearchRotateArray.java)、[src/array/SearchRotateArray2.java](../src/array/SearchRotateArray2.java) |
| 山峰/峰值 | [src/array/PeakElement.java](../src/array/PeakElement.java)、[src/array/PeakIndex.java](../src/array/PeakIndex.java)、[src/array/MountainArray.java](../src/array/MountainArray.java) |
| 二分边界 | [src/array/SearchRange.java](../src/array/SearchRange.java)、[src/array/SearchPos.java](../src/array/SearchPos.java)、[src/array/ClosestElement.java](../src/array/ClosestElement.java)、[src/array/NextGreatestLetter.java](../src/array/NextGreatestLetter.java) |
| 旋转最小值 | [src/array/FindMin.java](../src/array/FindMin.java)、[src/array/MinArray.java](../src/array/MinArray.java)、[src/array/SearchMinRotateArray.java](../src/array/SearchMinRotateArray.java) |
| 双指针求和 | [src/array/ThreeNum.java](../src/array/ThreeNum.java)、[src/array/FourNum.java](../src/array/FourNum.java)、[src/array/TwoArrayMedia.java](../src/array/TwoArrayMedia.java) |
| 对撞指针 | [src/array/Container.java](../src/array/Container.java)、[src/array/ValidTriangle.java](../src/array/ValidTriangle.java)、[src/array/SortedSquares.java](../src/array/SortedSquares.java) |
| 快慢指针（原地删除） | [src/array/RemoveDuplicates.java](../src/array/RemoveDuplicates.java)、[src/array/RemoveE.java](../src/array/RemoveE.java)、[src/array/MoveZeros.java](../src/array/MoveZero.java)、[src/array/CompressChars.java](../src/array/CompressChars.java) |
| 滑动窗口 | [src/slidingWindows/MinWindow.java](../src/slidingWindows/MinWindow.java)、[src/slidingWindows/LengthOfLongestSubString.java](../src/slidingWindows/LengthOfLongestSubString.java)、[src/slidingWindows/FindAnagrams.java](../src/slidingWindows/FindAnagrams.java)、[src/array/MinSubArray.java](../src/array/MinSubArray.java)、[src/array/ShortestSubArray.java](../src/array/ShortestSubArray.java)、[src/array/MaxSumTwoNoOverlap.java](../src/array/MaxSumTwoNoOverlap.java)、[src/dp/LongestSubString.java](../src/dp/LongestSubString.java) |
| 前缀和 | [src/array/SubArraySum.java](../src/array/SubArraySum.java)、[src/array/SubArraySumK.java](../src/array/SubArraySumK.java)、[src/array/PivotIndex.java](../src/array/PivotIndex.java)、[src/array/RangeSumQuery.java](../src/array/RangeSumQuery.java)、[src/array/RangeSum.java](../src/array/RangeSum.java) |
| 差分 | [src/array/CarPooling.java](../src/array/CarPooling.java)、[src/array/MinPictures.java](../src/array/MinPictures.java) |
| 快速选择 / TopK | [src/array/FindKthLargest.java](../src/array/FindKthLargest.java)、[src/array/KthLargest.java](../src/array/KthLargest.java)、[src/array/KthLargest2.java](../src/array/KthLargest2.java)、[src/array/TopKFrequent.java](../src/array/TopKFrequent.java)、[src/array/LeastNumbers.java](../src/array/LeastNumbers.java) |
| 排序应用 | [src/array/ReversePairs.java](../src/array/ReversePairs.java)、[src/array/CountSmaller.java](../src/array/CountSmaller.java)、[src/array/BubbleSwitch.java](../src/array/BubbleSwitch.java)、[src/array/PancakeSort.java](../src/array/PancakeSort.java) |
| 原地哈希 | [src/array/FindDisappear.java](../src/array/FindDisappear.java)、[src/array/FindDuplicates.java](../src/array/FindDuplicates.java)、[src/array/DuplicateNum.java](../src/array/DuplicateNum.java)、[src/array/FirstMissingPositive.java](../src/array/MissPositive.java) |
| 矩阵遍历 | [src/array/SpiralMatrix.java](../src/array/SpiralMatrix.java)、[src/array/BuildSpiralMatrix.java](../src/array/BuildSpiralMatrix.java)、[src/array/RotateImage.java](../src/array/RotateImage.java)、[src/array/DiagonalOrder.java](../src/array/DiagonalOrder.java)、[src/array/SetMatrixZero.java](../src/array/SetMatrixZero.java)、[src/array/GameOfLife.java](../src/array/GameOfLife.java) |

---

**开始 →** [01 模板总览与题型判断](01-模板总览与题型判断.md)
