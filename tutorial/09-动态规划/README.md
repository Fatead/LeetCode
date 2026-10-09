# 09 动态规划

> **这一章学什么**：动态规划（DP）是把一个大问题拆成**互相重叠的子问题**，用一张表记住子问题的答案、避免重复计算。它是 LeetCode 的"最终 BOSS"，也是面试区分度最高的专题。
> **为什么重要**：DP 覆盖了约 15% 的题目，且几乎所有 Hard 都和 DP 沾边。但 DP 不玄学——**一旦掌握"五步法"，80% 的 DP 题都能套出状态转移方程。**

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 DP 五步法与入门](01-DP五步法与入门.md) | 什么是 DP、五步解题法、爬楼梯、打家劫舍、从暴力到记忆化到递推 | ★★★☆☆ | ★★★★★ |
| [02 线性 DP](02-线性DP.md) | 最大子数组、最长递增子序列、最长公共子序列、编辑距离、打家劫舍 II/III | ★★★★☆ | ★★★★★ |
| [03 背包问题](03-背包问题.md) | 0-1 背包、完全背包、组合/排列、零钱兑换、分割等和子集 | ★★★★☆ | ★★★★☆ |
| [04 区间与其他 DP](04-区间与其他DP.md) | 区间 DP、回文、股票买卖系列、矩阵路径、树形 DP | ★★★★★ | ★★★★☆ |

---

## 一、什么是动态规划（一句话讲清）

**动态规划 = 递归 + 记忆化 = 填表。**

想象你要算斐波那契 `f(5)`：

```
          f(5)
        /      \
     f(4)      f(3)
     /   \     /   \
  f(3)  f(2) f(2) f(1)      ← f(3)、f(2) 被重复计算了多次！
  ...
```

暴力递归里 `f(3)` 被算了 2 次、`f(2)` 被算了 3 次，`O(2^n)` 爆炸。**DP 的核心洞察是：子问题的答案只和子问题本身有关，算一次存起来，下次直接查表。** 这就把指数级降到了线性。

**什么样的问题能用 DP？** 必须满足两个条件：

| 性质 | 含义 | 怎么判断 |
| --- | --- | --- |
| **最优子结构** | 大问题的最优解可由子问题的最优解组合而来 | "我这一步的选择 + 之前的最优" 能推出当前最优 |
| **无后效性 / 重叠子问题** | 子问题会被反复用到，且一旦确定就不再改变 | 画出递归树，看是否有重复节点 |

> **DP vs 贪心**：贪心每步只做局部最优、不回头（见 [08 贪心](../08-贪心/README.md)）；DP 则会"记住所有子问题的最优解"再综合决策。**贪心是 DP 的特例**——当"局部最优能保证全局最优"时，才能用贪心。

> **DP vs 回溯**：回溯枚举所有方案（见 [07 回溯](../07-回溯与搜索/README.md)），DP 只记录最优值或方案数。**当回溯出现"大量重复子树"时，加记忆化就变成了 DP。**

---

## 二、DP 五步法（全章的方法论）

拿到任何 DP 题，严格按这五步走：

```
第 1 步：确定 dp 数组的含义      —— dp[i] 到底代表"什么的最优值/方案数"
第 2 步：确定状态转移方程        —— dp[i] 怎么由 dp[i-1]、dp[i-2]... 推出来
第 3 步：确定初始化（base case） —— dp[0]、dp[1] 等边界取什么
第 4 步：确定遍历顺序            —— 从前往后还是从后往前？先遍历谁？
第 5 步：举例手推 dp 数组        —— 用小样例填一遍表，验证转移方程对不对
```

**最重要的是第 1 步——dp 数组的定义。** 定义错了，后面全错；定义对了，转移方程往往自然浮现。90% 的"做不出来"是卡在"不知道 `dp[i]` 该表示什么"。

**第 5 步最容易被跳过，但它是查错神器**：手推一遍表格，如果某个格子和你预期的不符，立刻就知道是转移方程还是初始化出了问题。

---

## 三、DP 的三种实现形态

同一个 DP 可以写成三种代码，面试时按需选择：

| 形态 | 写法 | 优点 | 缺点 |
| --- | --- | --- | --- |
| **递归 + 记忆化**（自顶向下） | 递归函数 + `memo` 数组缓存 | 最接近"暴力递归"的思路，容易想 | 有递归栈开销，可能爆栈 |
| **递推填表**（自底向上） | `for` 循环从小到大填 `dp` 数组 | 无栈风险，常数小，面试主流 | 需要想清楚遍历顺序 |
| **滚动数组优化** | 只保留必要的几个状态变量 | 空间从 `O(n)` 降到 `O(1)` | 丢失中间状态，无法回溯方案 |

以斐波那契为例，三种形态：

```java
// 形态一：记忆化递归
int[] memo;
int fib(int n) {
    if (n < 2) return n;
    if (memo[n] != 0) return memo[n];        // 查表
    return memo[n] = fib(n - 1) + fib(n - 2);
}

// 形态二：递推填表
int fib(int n) {
    if (n < 2) return n;
    int[] dp = new int[n + 1];
    dp[0] = 0; dp[1] = 1;
    for (int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
    return dp[n];
}

// 形态三：滚动变量（空间 O(1)）
int fib(int n) {
    if (n < 2) return n;
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        int c = a + b;
        a = b; b = c;
    }
    return b;
}
```

**建议路径**：先用形态一想清楚状态和转移，再翻译成形态二（面试默认交这个），最后视情况优化成形态三。

---

## 四、本章学习路径

```
第 1 天  01 五步法与入门   → 爬楼梯 + 打家劫舍，重点练"定义 dp[i]"
第 2 天  02 线性 DP（上）  → 最大子数组 + 最长递增子序列（O(n²) 版）
第 3 天  02 线性 DP（下）  → 最长公共子序列 + 编辑距离（二维 DP 入门）
第 4 天  03 背包（上）      → 0-1 背包模板 + 分割等和子集
第 5 天  03 背包（下）      → 完全背包 + 零钱兑换（组合 vs 排列的遍历顺序）
第 6 天  04 区间/股票       → 股票买卖系列（状态机 DP）
第 7 天  本章综合           → 限时做 编辑距离 + 零钱兑换 + 最长回文子串
```

---

## 五、DP 易错点总览（整章通用）

| 易错点 | 后果 | 正确做法 |
| --- | --- | --- |
| `dp` 数组含义没定清楚 | 转移方程怎么推都不对 | 先用一句话写死 `dp[i]` 代表什么 |
| 初始化错误 | 第一个/第二个值就错，连锁崩 | 用第 5 步手推验证 base case |
| 遍历顺序错 | 用到还没算好的 `dp` 值 | 想清楚"当前依赖哪些格子，它们必须先算好" |
| 完全背包正序 vs 0-1 背包逆序 | 物品被重复选 / 漏选 | 0-1 背包内层逆序，完全背包内层正序 |
| 组合数 vs 排列数遍历反了 | 结果多算或少算 | 求组合先遍历物品，求排列先遍历容量 |
| 滚动数组覆盖了还要用的值 | 读到被覆盖后的脏值 | 逆序更新，或用临时变量暂存 |
| 下标 `i` 对应 `nums[i-1]` 搞混 | 数组越界或错位 | `dp` 长度和 `nums` 下标的对应关系写注释固定 |

---

## 六、参考代码索引

本章涉及的所有仓库实现（分布在 `src/dp` 和 `src/array`）：

| 知识点 | 仓库文件 |
| --- | --- |
| 爬楼梯 | [src/dp/ClimbStair.java](../../src/dp/ClimbStair.java)、[src/dp/ClimbStairs.java](../../src/dp/ClimbStairs.java) |
| 使用最小花费爬楼梯 | [src/dp/MinCostClimbingStairs.java](../../src/dp/MinCostClimbingStairs.java) |
| 不同路径（含障碍） | [src/dp/UniquePathObstacles.java](../../src/dp/UniquePathObstacles.java)、[src/dp/PathNum.java](../../src/dp/PathNum.java)、[src/dp/PathNum2.java](../../src/dp/PathNum2.java) |
| 打家劫舍 I/II/III | [src/dp/Rob1.java](../../src/dp/Rob1.java)、[src/dp/Rob2.java](../../src/dp/Rob2.java)、[src/dp/Rob3.java](../../src/dp/Rob3.java)、[src/dp/Rob4.java](../../src/dp/Rob4.java) |
| 最大子数组和 | [src/dp/MaxSubArray.java](../../src/dp/MaxSubArray.java) |
| 乘积最大子数组 | [src/dp/MaxMulSubArray.java](../../src/dp/MaxMulSubArray.java) |
| 最长递增子序列 | [src/dp/LongestIncreaseSequence.java](../../src/dp/LongestIncreaseSequence.java)、[src/dp/NumberOfLIS.java](../../src/dp/NumberOfLIS.java)、[src/dp/IncreaseSubSeq.java](../../src/dp/IncreaseSubSeq.java) |
| 最长连续递增序列 | [src/dp/MaxContinueSeq.java](../../src/dp/MaxContinueSeq.java)、[src/dp/IncreasingTriplet.java](../../src/dp/IncreasingTriplet.java) |
| 最长数对链 / 字符串链 | [src/dp/LongestChain.java](../../src/dp/LongestChain.java)、[src/dp/LongestStrChain.java](../../src/dp/LongestStrChain.java)、[src/dp/LongestWord.java](../../src/dp/LongestWord.java) |
| 最长公共子序列 | [src/dp/CommonLength.java](../../src/dp/CommonLength.java)、[src/dp/MaxUncrossedLines.java](../../src/dp/MaxUncrossedLines.java) |
| 编辑距离 | [src/dp/EditDistance.java](../../src/dp/EditDistance.java)、[src/dp/EditDistance2.java](../../src/dp/EditDistance2.java)、[src/dp/EditDis.java](../../src/dp/EditDis.java) |
| 两个字符串的删除操作 | [src/dp/DeleteWords.java](../../src/dp/DeleteWords.java)、[src/dp/MinSteps.java](../../src/dp/MinSteps.java) |
| 不同的子序列 | [src/dp/DistinctSubStr.java](../../src/dp/DistinctSubStr.java) |
| 最长回文子串/子序列 | [src/dp/LongestPalindrome.java](../../src/dp/LongestPalindrome.java)、[src/dp/LongestSubPalindrome.java](../../src/dp/LongestSubPalindrome.java) |
| 零钱兑换（最少硬币） | [src/dp/MinTotal.java](../../src/dp/MinTotal.java) |
| 零钱兑换 II（组合数） | [src/dp/ChangeCoins.java](../../src/dp/ChangeCoins.java) |
| 单词拆分 | [src/dp/BreakWords.java](../../src/dp/BreakWords.java) |
| 整数拆分 / 剪绳子 | [src/dp/IntegerBreak.java](../../src/dp/IntegerBreak.java)、[src/dp/CuttingRope.java](../../src/dp/CuttingRope.java) |
| 买卖股票系列 | [src/dp/MaxProfit.java](../../src/dp/MaxProfit.java) |
| 三角形最小路径和 | [src/dp/MinTotal.java](../../src/dp/MinTotal.java)、[src/dp/MinFallingPathSum.java](../../src/dp/MinFallingPathSum.java) |
| 最小路径和 | [src/dp/MinPathSum.java](../../src/dp/MinPathSum.java) |
| 最大正方形 | [src/dp/MaxSquare.java](../../src/dp/MaxSquare.java) |
| 解码方法 | [src/dp/Decoding.java](../../src/dp/Decoding.java) |
| 最低票价 | [src/dp/MinCostTickets.java](../../src/dp/MinCostTickets.java) |
| 最长湍流子数组 | [src/dp/MaxTurbulenceSize.java](../../src/dp/MaxTurbulenceSize.java) |
| 最长斐波那契子序列 | [src/dp/LongestFib.java](../../src/dp/LongestFib.java) |
| 最长有效括号 | [src/dp/LongestBracket.java](../../src/dp/LongestBracket.java) |

---

**开始 →** [01 DP 五步法与入门](01-DP五步法与入门.md)
