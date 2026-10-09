# 12 位运算

> **这一章学什么**：位运算直接操作二进制位，是最快的运算方式。核心是记住几个**位操作恒等式**和**异或的性质**，就能把一类看似复杂的题变成一两行代码。
> **为什么重要**：位运算单独考得不多，但它是很多最优解的"点睛之笔"——"只出现一次的数字""两数相加不用 +"这类题，不懂位运算就做不出最优解。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 位运算基础与技巧](01-位运算基础与技巧.md) | 六大运算符、常用位操作恒等式、异或性质、状态压缩入门 | ★★★☆☆ | ★★★☆☆ |

> 位运算内容相对集中，本章用一节讲透。

---

## 一、六个位运算符（先背熟）

| 运算符 | 名称 | 规则 | 例子（4 位） |
| --- | --- | --- | --- |
| `&` | 与 | 都 1 才 1 | `1100 & 1010 = 1000` |
| `\|` | 或 | 有 1 就 1 | `1100 \| 1010 = 1110` |
| `^` | 异或 | 不同为 1 | `1100 ^ 1010 = 0110` |
| `~` | 取反 | 0 变 1，1 变 0 | `~1100 = 0011`（含符号位） |
| `<<` | 左移 | 整体左移，低位补 0 | `0011 << 1 = 0110`（相当于 ×2） |
| `>>` | 右移（算术） | 右移，高位补符号位 | `1100 >> 1 = 1110`（负数保号） |
| `>>>` | 无符号右移 | 右移，高位补 0 | 处理负数当无符号数时用 |

> **Java 特有**：`>>` 是算术右移（补符号位），`>>>` 是逻辑右移（补 0）。处理 `hashCode`、负数的二进制位时常用 `>>>`。

---

## 二、必背的位操作恒等式

这几个"套路"覆盖了位运算题的大半：

```java
// 取第 i 位（从 0 开始）
int bit = (x >> i) & 1;

// 把第 i 位设为 1
x |= (1 << i);

// 把第 i 位设为 0
x &= ~(1 << i);

// 翻转第 i 位
x ^= (1 << i);

// 判断奇偶（比 % 2 快）
boolean odd = (x & 1) == 1;

// 判断 2 的幂（只有一个 1 的二进制）
boolean isPow2 = x > 0 && (x & (x - 1)) == 0;

// 去掉最低位的 1（Brian Kernighan 算法核心）
x = x & (x - 1);

// 取出最低位的 1（lowbit，树状数组核心）
int low = x & (-x);

// 乘除 2 的幂
x << k   // = x * 2^k
x >> k   // = x / 2^k（向下取整）
```

**最值得记的一条**：`x & (x - 1)` **消去最低位的 1**。它是统计二进制中 1 的个数（汉明重量）、判断 2 的幂的核心技巧。

---

## 三、异或（`^`）的四大性质（本章最重要）

异或是位运算题的灵魂，四条性质必须刻在脑子里：

```
1. a ^ a = 0        （自己和自己异或 = 0）
2. a ^ 0 = a        （和 0 异或 = 自己）
3. 交换律、结合律    （a ^ b ^ c = c ^ b ^ a，顺序随意）
4. a ^ b ^ b = a    （异或两次同一个数 = 还原，由 1、2 推出）
```

**这四条推出一个杀手锏**：一堆数全异或起来，**成对出现的会抵消成 0，只剩下落单的那个**。

### 3.1 只出现一次的数字（136 题）

**题目**：数组里只有一个数出现一次，其余都出现两次，找出它。

```java
int singleNumber(int[] nums) {
    int res = 0;
    for (int num : nums) res ^= num;   // 成对的全抵消，剩下落单的
    return res;
}
```

一行搞定，`O(n)` 时间、`O(1)` 空间。**这就是异或"成对抵消"性质的直接应用。**

### 3.2 只出现一次的数字 III（260 题）：分组异或

**题目**：有**两个**数只出现一次，其余出现两次，找出这两个数。

**巧思**：全部异或得到 `a^b`（两个落单数的异或）。由于 `a≠b`，`a^b` 至少有一位是 1，这一位说明 `a` 和 `b` 在该位不同。**用这一位把所有数分成两组**，`a` 和 `b` 必分到不同组，组内再各自异或就得到两个答案。

```java
public int[] singleNumbers(int[] nums) {
    int xorAll = 0;
    for (int num : nums) xorAll ^= num;        // xorAll = a ^ b
    int div = 1;
    while ((div & xorAll) == 0) div <<= 1;     // 找 a、b 不同的那一位
    int a = 0, b = 0;
    for (int num : nums) {
        if ((num & div) == 0) a ^= num;        // 该位为 0 的一组
        else b ^= num;                         // 该位为 1 的一组
    }
    return new int[]{a, b};
}
```

> 参考实现：[src/BitManipulation/SingleNum.java](../../src/BitManipulation/SingleNum.java)（仓库实现与此完全一致）

*只出现一次的数字 III 是【分组异或】，关键突破点是【用 `a^b` 中为 1 的某位把数组分两组，使 a、b 分离】，边界是【`div` 从 1 开始左移找到第一个不同位】。*

---

## 四、不用加号做加法（剑指 65）：异或 + 进位

**题目**：不用 `+`，实现两数相加。

**原理**：
- **无进位和** = `a ^ b`（异或恰好是"不进位的加法"）
- **进位** = `(a & b) << 1`（都是 1 才进位，进到高一位）
- 重复"求无进位和 + 求进位"，直到进位为 0

```java
public int getSum(int a, int b) {
    if (b == 0) return a;             // 没有进位了，结果就是无进位和
    int sum = a ^ b;                  // 无进位和
    int carry = (a & b) << 1;         // 进位
    return getSum(sum, carry);        // 把和与进位继续相加
}
```

> 参考实现：[src/BitManipulation/SumNum.java](../../src/BitManipulation/SumNum.java)（仓库是递归版，思路一致）

*不用加号加法是【异或求和 + 与进位】，关键突破点是【异或是无进位加法，`(a&b)<<1` 是进位，循环到进位为 0】，边界是【进位为 0 时返回无进位和】。*

---

## 五、汉明重量与汉明距离

### 5.1 位 1 的个数（191 题，汉明重量）

```java
int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= (n - 1);     // 每次消去最低位的 1
        count++;          // 消几次就有几个 1
    }
    return count;
}
```

**`n & (n-1)` 消去最低位 1** 的循环次数 = 1 的个数，比逐位检查快（只循环"1 的个数"那么多次）。

### 5.2 汉明距离（461 题）

两数二进制不同位的个数 = `异或后 1 的个数`：

```java
int hammingDistance(int x, int y) {
    return Integer.bitCount(x ^ y);   // 异或后数 1 的个数
}
```

> 参考实现：[src/BitManipulation/HammingDistance.java](../../src/BitManipulation/HammingDistance.java)、[src/BitManipulation/TotalHammingDistance.java](../../src/BitManipulation/TotalHammingDistance.java)

---

## 六、位运算在其他专题的应用

位运算常作为"优化手段"出现在别的专题里：

| 应用场景 | 怎么用 | 相关章节 |
| --- | --- | --- |
| 状态压缩 DP | 用一个 int 的每一位表示"选没选" | [09 动态规划](../09-动态规划/README.md) |
| 回溯中的 used 集合 | 用位掩码代替 `boolean[]`，更省空间 | [07 回溯](../07-回溯与搜索/README.md) |
| 子集枚举 | `for (int mask = 0; mask < (1<<n); mask++)` 枚举所有子集 | [07 回溯](../07-回溯与搜索/02-子集与组合问题.md) |
| 哈希/快速查找 | 位图（bitmap）节省空间 | [02 哈希表](../02-哈希表/README.md) |

**子集枚举模板**（用二进制位表示选取状态）：

```java
int n = nums.length;
for (int mask = 0; mask < (1 << n); mask++) {   // 2^n 个子集
    List<Integer> subset = new ArrayList<>();
    for (int i = 0; i < n; i++)
        if ((mask & (1 << i)) != 0)             // 第 i 位为 1 → 选 nums[i]
            subset.add(nums[i]);
    // 处理 subset
}
```

---

## 七、本章练习

| 题号 | 题目 | 关键点 | 参考实现 |
| --- | --- | --- | --- |
| 136 | 只出现一次的数字 | 异或抵消 | [SingleNum.java](../../src/BitManipulation/SingleNum.java) |
| 260 | 只出现一次的数字 III | 分组异或 | [SingleNum.java](../../src/BitManipulation/SingleNum.java) |
| 191 | 位 1 的个数 | `n & (n-1)` | [HammingDistance.java](../../src/BitManipulation/HammingDistance.java) |
| 461 | 汉明距离 | 异或后数 1 | [HammingDistance.java](../../src/BitManipulation/HammingDistance.java) |
| 477 | 汉明距离总和 | 按位统计 | [TotalHammingDistance.java](../../src/BitManipulation/TotalHammingDistance.java) |
| 371 | 两整数之和 | 异或 + 进位 | [SumNum.java](../../src/BitManipulation/SumNum.java) |
| 476 | 数字的补数 | 构造掩码取反 | [ComplementNum.java](../../src/BitManipulation/ComplementNum.java)、[BitWiseComplement.java](../../src/BitManipulation/BitWiseComplement.java) |
| 318 | 最大单词长度乘积 | 用 int 位表示字母集合 | [ProductWordsLength.java](../../src/BitManipulation/ProductWordsLength.java) |
| 1486 | 数组异或操作 | 异或遍历 | [XorOperation.java](../../src/BitManipulation/XorOperation.java) |
| 693 | 交替位二进制数 | `n ^ (n>>1)` 判断 | [AlternativeBits.java](../../src/BitManipulation/AlternativeBits.java) |
| 868 | 二进制间距 | 记录相邻 1 的位置 | [BinaryGap.java](../../src/BitManipulation/BinaryGap.java) |
| 1318 | 或运算的最少翻转次数 | 逐位比较 | [MinFlips.java](../../src/BitManipulation/MinFlips.java) |

### 二刷清单

- [ ] 136. 只出现一次的数字 —— 说清异或为什么能"抵消成对的数"
- [ ] 260. 只出现一次的数字 III —— 说清如何用一位把两个落单数分到两组
- [ ] 371. 两整数之和 —— 说清异或是"无进位和"、`(a&b)<<1` 是进位
- [ ] 191. 位 1 的个数 —— 说清 `n & (n-1)` 为什么消去最低位的 1

---

**本章完成！** 继续 [13 模拟与设计](../13-模拟与设计/README.md)。
