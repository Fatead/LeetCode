# 08 贪心

> **本章学什么**：贪心不是一种数据结构，而是一种**决策方式**——每一步都选"当前看起来最好"的那一个，并且**永不反悔**。
> 它的难点从来不是"写出来"（贪心代码往往只有 5 行），而是**"凭什么这样选是对的"**。这一章把论证方法（交换论证、反证、归纳）讲成可以套用的模板，再配 4 类高频题型的完整套路。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 贪心思想与正确性](01-贪心思想与正确性.md) | 两个前提、交换论证、贪心 vs DP、失效反例 | ★★★☆☆ | ★★★★★ |
| [02 区间问题](02-区间问题.md) | 按左端点/右端点排序、合并、插入、区间覆盖 | ★★★★☆ | ★★★★★ |
| [03 排序后贪心](03-排序后贪心.md) | 双指针匹配、两遍遍历取 max、自定义排序关键字 | ★★★☆☆ | ★★★★☆ |
| [04 构造与数学贪心](04-构造与数学贪心.md) | 最远可达、堆贪心、位值构造、连续数字集合 | ★★★★☆ | ★★★★☆ |

**建议顺序**：01 必须先读（后面三节的"为什么对"全靠它）→ 02 区间（模板最固定，性价比最高）→ 03 → 04。

---

## 一、为什么学这一章

贪心是 LeetCode 里**"代码最短、想通最难"**的一类题：

1. **它是面试的"分水岭"题**。写出一份贪心代码只需要 30 秒，面试官真正想听的是"为什么局部最优能推出全局最优"。答不出来，这题就只算"背过"。
2. **它和 DP 是一对孪生兄弟**。绝大多数贪心题的暴力解都是 DP：`dp[i] = max(dp[j] + 1)`，贪心做的事就是**发现 DP 转移里的冗余，把它压成一次扫描**。看懂 435 无重叠区间从 `O(n^2)` DP 变成 `O(n log n)` 贪心，比刷 10 道题更有价值。
3. **区间问题几乎全在这一章**。合并区间、插入区间、会议室、箭引爆气球、视频拼接…… 这一族题的解法只有"按左端点排序"和"按右端点排序"两种，摸清区别之后就是默写。
4. **它暴露你最严重的思维漏洞**。贪心错起来毫无征兆：样例全过，第 30 个用例挂掉。能自己构造出反例，是算法能力成熟的标志。

> **一句话定位**：如果 DP 是"把所有的可能性都算一遍"，贪心就是"我有充分理由相信，只需要看一个方向"。

---

## 二、本章语言基本功（一）：贪心正确性怎么论证

这是本章区别于其他章节的"基本功"。**记住三个模板，面试时按模板说，不要现场发挥。**

### 2.1 模板 A：交换论证（Exchange Argument）—— 最常用

**适用**：能说清"每一步选了什么"，且存在一个"最优解"可以做对比。

```
① 设 OPT 是某个最优解，G 是贪心解。找到第一个不同的决策位置 i：
   OPT 选了 x，贪心选了 g（g 由贪心规则选出，是"当前最好"）。
② 构造 OPT'：把 OPT 里的 x 换成 g，证明 OPT' 依然合法且"不比 OPT 差"。
   —— 这一步是全部难点，通常靠"贪心选择的定义"直接推出。
③ 于是 OPT' 也是最优解，且与 G 在前 i 个决策上一致。重复 ①②，
   可把任意最优解逐步改造成贪心解且不损失最优性 → 贪心解最优。∎
```

**在代码里怎么用**：②里那句"g 不比 x 差"通常可以翻译成**排序关键字**——谁的"当时最好"更强，谁排前面。**这就是"为什么贪心题总要排序"的根本原因。**

> 看 [src/greedy/TwoCitySchedCost.java](../src/greedy/TwoCitySchedCost.java) 的排序关键字 `costs[0] - costs[1]`：它正是"派去 A 城比去 B 城省多少钱"。省得最多的人优先去 A，就是一个可以用交换论证证明的贪心选择。

### 2.2 模板 B：反证法 —— 适合"最早 / 最少 / 第一个"型

```
① 假设贪心解 G 不是最优的，即存在更优的 OPT。
② 从 OPT 与 G 的第一个分歧点出发推出矛盾：若 OPT 在第 i 步用了别的，
   则 OPT 必然在某个更早的位置违反了约束 / 或 G 的那一步严格更优。
③ 矛盾 → G 就是最优的。∎
```

**典型例子**：区间调度里"为什么每次选**右端点最小**的区间"——设最优解第一个区间是 `x` 而非右端点最小的 `g`。因为 `g.right <= x.right`，把 `x` 换成 `g` 后剩余空间只会变大、不会冲突，所以换掉不吃亏。

### 2.3 模板 C：归纳法 —— 适合"一遍扫描维护某个量"型

```
① 基例：i = 0 时初始化值正确。
② 归纳假设：扫描完前 i 个元素后，维护的量 X = 前 i 个元素的最优值。
③ 归纳步骤：加入第 i+1 个元素时，任何用到前 i+1 个元素的最优方案，
   要么不用它（已由归纳假设覆盖），要么必须用它（X 的更新公式正好算出了它）。∎
```

**典型例子**：跳跃游戏里维护 `maxReach`。循环不变量是"**扫描到下标 i 时，`maxReach` = 只使用前 i 个格子能到达的最远位置**"。加入第 `i+1` 格时，要么原本就到不了它（`maxReach < i+1` → 返回 false），要么从 `[0, i]` 里某格跳过来（此时 `maxReach` 已是这些格子的最大跳跃能力，取 `max` 即可）。

### 2.4 三种方法怎么选

| 题目特征 | 首选方法 | 你要说出的关键句 |
| --- | --- | --- |
| "选谁更好"可以两两比较 | **交换论证** | "把最优解里的 x 换成 g，不会变差" |
| 求"最早 / 最少 / 第一个" | **反证法** | "若存在更优解，则它在第一步就与贪心规则冲突" |
| 一遍扫描维护某个量 | **归纳法** | "循环不变量是：扫描到 i 时，X 已经是前 i 个的最优" |
| 排序关键字有明确含义 | **交换论证 + 排序** | "关键字就是两人交换后收益的差" |

> **通用话术**（可直接背）："我用交换论证。设最优解在某一步选了 x，我的贪心选了 g。由贪心规则，g 是当前最优的，所以把 x 换成 g 不会让后续可行集合变小，也不会让目标值变差。因此存在一个同样最优的解包含 g。反复替换即得贪心解最优。"

---

## 三、本章语言基本功（二）：贪心与 DP 怎么判断

### 3.1 决策表

| 判断维度 | 偏向**贪心** | 偏向**DP** |
| --- | --- | --- |
| 是否"选了就不能反悔" | 每步的最优选择**不影响**之前的选择 | 需要**回头修改**早先的选择 |
| 子问题是否重叠 | 每个子问题只被访问一次（滚动变量即可） | 大量子问题重叠，需要记忆化 / 递推表 |
| 决策空间 | 每步只有**一个**显然最优的候选 | 每步有多个候选，且"现在差的以后可能翻盘" |
| 数据规模 | `n ≤ 10^5`（要求 `O(n log n)`） | `n ≤ 10^3`~`10^4`（`O(n^2)` 可接受） |
| 典型问法 | "最少需要几个 / 最早什么时候 / 最多能安排多少" | "有多少种方案 / 最大价值（带容量、带次数限制）" |
| 反例敏感度 | 必须能构造"贪心会挂"的反例来否证 | 不需要，转移方程覆盖所有情况 |

### 3.2 判断流程（照着走）

```
Step 1  先写暴力 / DFS，看清"决策点"在哪：每一步到底在选什么？
Step 2  问：这一步的选择，会不会让"以后的选择变少"？  不会 → 大胆贪心；会 → Step 3
Step 3  问：能不能证明"当前最优选择一定包含在某个最优解里"？能 → 贪心 + 排序；不能 → DP
Step 4  仍拿不准 → 先写 DP，再看 DP 表里有没有"单调、可以被砍掉"的维度。
        435 就是标准案例：dp[i] = max(dp[j]+1) 的最优 j 永远是"右端点最小的那个"，
        于是 O(n^2) 的 DP 退化成 O(n log n) 的排序 + 贪心。
```

> **实战口诀**：**能贪心就先想贪心，想不出证明就退回 DP，绝不要"凭感觉贪"。**

### 3.3 同一道题的两种写法（仓库里真实存在）

| 题目 | DP / 暴力版本（仓库） | 贪心版本 | 复杂度对比 |
| --- | --- | --- | --- |
| 435 无重叠区间 | [src/array/MinInterval.java](../src/array/MinInterval.java)（`O(n^2)` DP，还带调试输出） | 按右端点排序后一次扫描 | `O(n^2)` → `O(n log n)` |
| 646 最长数对链 | [src/dp/LongestChain.java](../src/dp/LongestChain.java)（`O(n^2)` DP） | 同上，按右端点排序 | `O(n^2)` → `O(n log n)` |
| 45 跳跃游戏 II | [src/dp/JumpGame.java](../src/dp/JumpGame.java)（`O(n^2)` DP） | 维护最远可达 + 分层 | `O(n^2)` → `O(n)` |
| 134 加油站 | [src/dp/CompleteCircuit.java](../src/dp/CompleteCircuit.java)（`O(n^2)` 枚举起点） | 总油量 + 起点重置 | `O(n^2)` → `O(n)` |
| 860 柠檬水找零 | [src/array/LemonChange.java](../src/array/LemonChange.java) 的 `lemonadeChange`（最大堆模拟，注释写着"超时"） | 只维护 5 元和 10 元的张数 | `O(n log n)` → `O(n)` |

> 仓库里这些"慢版本"不是废代码——**它们是最好的证明素材**：先看 DP 怎么写，再看贪心砍掉了什么，你才真正理解贪心。

---

## 四、本章语言基本功（三）：常见贪心失效反例

**贪心最大的风险是"样例过了但逻辑错了"。** 下面这些反例必须刻进肌肉记忆。

### 4.1 反例一：找零钱（面额 1 / 3 / 4）

```
目标：凑出 6，面额 {1, 3, 4}，要求枚数最少。
贪心：6 → 拿 4，剩 2 → 拿 1，剩 1 → 拿 1，剩 0      共 3 枚 ❌
最优：6 = 3 + 3                                     共 2 枚 ✅
```

**结论**：面额**任意**时找零钱必须 DP（`dp[x] = min(dp[x-c]+1)`）。只有面额是**规范币制**（如 1 / 5 / 10 / 25）时贪心才成立。**"看着像贪心"≠"能贪心"，差别只在面额的数学性质上。**

### 4.2 反例二：区间覆盖的排序关键字选错

```
目标：用最少的区间覆盖 [0, 10]。
区间：A = [0, 6]，B = [5, 7]，C = [7, 10]

❌ 按"区间长度"从大到小：先选 A（长 6），覆盖 [0,6]；
   剩下 [6,10] 时 B [5,7] 的左端 5 < 6 可以接上，但若先选了更长的 B 而它左端 > 0，
   就会在 [0, 左端) 留下缺口 → 需要更多区间甚至失败。
✅ 按左端点排序，每次在"能接上当前覆盖右端"的区间里，选右端点最远的。
```

**结论**：区间题的排序关键字只有两个正确选项：**左端点**（合并 / 覆盖）或**右端点**（最多选几个不重叠）。**长度、面积都是错的。**

### 4.3 反例三：任务调度不能"每轮随便挑一个"

```
任务 A×3、B×3，冷却 n = 2。
❌ 不记录冷却：A B A B A B → A 之间只隔 1 个，违反冷却。
✅ 维护冷却表（仓库 LeastInterval 的做法），或用桶公式：
   (maxCount - 1) * (n + 1) + 出现次数最多的任务种数。
```

### 4.4 失效反例速查表

| 场景 | 贪心为什么会错 | 正确解法 |
| --- | --- | --- |
| 任意面额找零钱 | 局部最大面额破坏后续拼凑 | DP（完全背包） |
| 0-1 背包（按性价比排序） | 物品不可分割，会留下无法利用的空间 | DP |
| 带冷却的任务安排 | 局部选最多的会撞冷却 | 堆 + 冷却表 / 桶公式 |
| 区间覆盖按长度排序 | 长度与"能延伸多远"无关 | 按左端点排序 + 取最远右端 |
| LIS"见大就换" | 局部更换会丢掉更优前缀 | `tails[]` 数组 + 二分 |
| 股票买卖带冷冻期 | "见涨就卖"可能错过更大的下一段 | DP 状态机 |

---

## 五、本章学习路径

```
第 1 天  01 贪心思想与正确性 → 默写交换论证三段式；手推 {1,3,4} 凑 6 的反例；
                            把 435 的 DP 版和贪心版各写一遍，说出贪心砍掉了什么
第 2 天  02 区间问题（一）   → 56 / 57 / 435 / 763 四个模板按"左端点 vs 右端点"分类默写
第 3 天  02 区间问题（二）   → 452 箭引爆气球、253 会议室 II、1024 视频拼接、1326 最少水龙头
                            重点：452 与 435 的边界差别（相切算不算重叠）
第 4 天  03 排序后贪心      → 455 / 860 / 406 / 1029 / 179 / 134
第 5 天  04 构造与数学贪心  → 55 / 45 / 605 / 122 / 738 / 1296 / 767 / 621
第 6 天  限时训练           → 3 道 Medium / 60 分钟，＋弱项重做
```

**每道题的固定动作**：① 先想暴力 → ② 问"我在贪什么" → ③ 用交换论证或归纳法自证 → ④ 写出排序关键字 → ⑤ 过一遍边界（单元素、全相同、相切、溢出）。

---

## 六、参考代码索引

| 知识点 | 仓库文件 |
| --- | --- |
| 分发饼干 455 | [src/greedy/CookiesAndChildren.java](../src/greedy/CookiesAndChildren.java) |
| 分糖果 575 | [src/greedy/DistributeCandies.java](../src/greedy/DistributeCandies.java) |
| 完美数游戏 1025 | [src/greedy/DivisorGame.java](../src/greedy/DivisorGame.java) |
| 三角形最大周长 976 | [src/greedy/LargestPerimeter.java](../src/greedy/LargestPerimeter.java) |
| 三个数的最大乘积 628 | [src/greedy/MaxProduct.java](../src/greedy/MaxProduct.java) |
| 任务调度器 621 | [src/greedy/LeastInterval.java](../src/greedy/LeastInterval.java) |
| 爱生气的书店老板 1052 | [src/greedy/MaxSatisfied.java](../src/greedy/MaxSatisfied.java) |
| 最大交换 670 | [src/greedy/MaxSwap.java](../src/greedy/MaxSwap.java) |
| 种花问题 605 | [src/greedy/PlaceFlower.java](../src/greedy/PlaceFlower.java) |
| 在 D 天内送达包裹 1011 | [src/greedy/ShipWithDays.java](../src/greedy/ShipWithDays.java) |
| 两地调度 1029 | [src/greedy/TwoCitySchedCost.java](../src/greedy/TwoCitySchedCost.java) |
| 视频拼接 1024 | [src/greedy/VideoStitching.java](../src/greedy/VideoStitching.java) |
| 无重叠区间 435（DP 版） | [src/array/MinInterval.java](../src/array/MinInterval.java) |
| 最长数对链 646（DP 版） | [src/dp/LongestChain.java](../src/dp/LongestChain.java) |
| 合并区间 56 | [src/array/MergeInters.java](../src/array/MergeInters.java)、[src/sort/MergeInterval.java](../src/sort/MergeInterval.java) |
| 插入区间 57 | [src/array/InsertIntervals.java](../src/array/InsertIntervals.java)、[src/sort/InsertInterval.java](../src/sort/InsertInterval.java) |
| 跳跃游戏 55 / II 45 | [src/array/JumpGame.java](../src/array/JumpGame.java)、[src/dp/JumpGame.java](../src/dp/JumpGame.java) |
| 划分字母区间 763 | [src/hashTable/PartitionLabels.java](../src/hashTable/PartitionLabels.java) |
| 柠檬水找零 860 | [src/array/LemonChange.java](../src/array/LemonChange.java) |
| 加油站 134（暴力版） | [src/dp/CompleteCircuit.java](../src/dp/CompleteCircuit.java) |
| 最大数 179 | [src/number/MaxNum.java](../src/number/MaxNum.java) |
| 最长回文串 409 | [src/string/LongestPalindrome.java](../src/string/LongestPalindrome.java) |
| 根据身高重建队列 406 | [src/array/ReconstructQueue.java](../src/array/ReconstructQueue.java) |
| 划分数组为连续数字集合 1296 | [src/array/IsNStraightHand.java](../src/array/IsNStraightHand.java) |
| 重构字符串 767 | [src/string/ReorganizeString.java](../src/string/ReorganizeString.java) |
| 买卖股票的最佳时机 II 122 | [src/array/MaxProfit2.java](../src/array/MaxProfit2.java) |
| 单调递增的数字 738 | [src/number/MonotoneIncreaseNum.java](../src/number/MonotoneIncreaseNum.java) |
| 递增的三元子序列 334（DP 版） | [src/dp/IncreasingTriplet.java](../src/dp/IncreasingTriplet.java) |
| 交换字符串中的元素 1202 | [src/UnionFind/SmallestStringWithSwap.java](../src/UnionFind/SmallestStringWithSwap.java) |
| K 次取反后最大化的数组和 1005 | [src/array/LargestSumAfterKNegations.java](../src/array/LargestSumAfterKNegations.java) |
| 零钱兑换 DP / 买卖股票 121 | [src/dp/ChangeCoins.java](../src/dp/ChangeCoins.java)、[src/array/MaxProfit.java](../src/array/MaxProfit.java) |

**仓库里确实没有实现的题**（教程中用 `—` 标注或只给思路）：
452 箭引爆气球、252/253 会议室 I/II、376 摆动序列、1217 玩筹码、135 分发糖果、1827 最少操作次数使数组递增、1326 灌溉花园的最少水龙头。

---

**开始 →** [01 贪心思想与正确性](01-贪心思想与正确性.md)
