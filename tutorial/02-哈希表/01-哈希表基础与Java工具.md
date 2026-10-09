# 01 哈希表基础与 Java 工具

> **一句话理解**：哈希表用一次"算出来的地址"代替一次"从头找起"，把查找、插入、删除都压到**平均** `O(1)`。
> 刷题时你只需要问两个问题：**我要查的是什么？这个 key 的范围有多小？**

---

## 一、判定标准：什么时候能用哈希，什么时候不能

### 1.1 三个"能用"的信号

| 题目信号 | 哈希在做什么 | 典型题 |
| --- | --- | --- |
| "是否存在 / 是否重复" | `Set.contains` 代替 `O(n)` 扫描 | [217 存在重复元素](../src/hashTable/DuplicateNum.java) |
| "出现几次 / 出现最多的" | `Map` 计数，key 是元素，value 是次数 | [819 最常见的单词](../src/hashTable/MostCommonWord.java) |
| "找另一个数 / 找补数" | 把"找过的元素"存起来，用 `target - x` 直接命中 | [1 两数之和](../src/hashTable/TwoSum.java)、[128 最长连续序列](../src/UnionFind/LongestConsecutive.java) |

**统一的思维动作**：把"内层循环在找什么"写成一句话，然后用哈希把这个查找变成 `O(1)`。

```
暴力：for i in 0..n:  for j in 0..n:  if (f(nums[i], nums[j])) ...
                      ^^^^^^^^^^^^^ 这一层在"找 nums[j]"
       ↓ 把"已见过的 nums[j]"放进哈希
优化：for i in 0..n:  if (map.contains(g(nums[i]))) ...
```

### 1.2 四个"不能用"的红线

| 红线 | 为什么坏 | 替代方案 |
| --- | --- | --- |
| **需要有序遍历** | `HashMap` 的迭代顺序由哈希值决定，不可预测，且扩容后会变 | `TreeMap`（按 key 排序）或先排序 |
| **需要按下标访问"第 k 个"** | 哈希只能按 key 查，没有下标概念 | `ArrayList` / 数组 |
| **需要范围查询**（第一个 ≥ x） | 哈希只能精确命中 | `TreeMap.ceilingKey` |
| **需要维护顺序 / 访问先后** | 哈希只记录"有没有"，不记录"多久没用" | 哈希 + 双向链表（见 [04 设计数据结构](04-设计数据结构.md)） |

还有两条"能用但别用"：

- **key 空间很小且连续**（26 个字母、`1..n` 的整数）→ 用数组，快好几倍且零装箱开销。
- **数据量极小**（n ≤ 20）→ 双层循环，哈希的常数开销反而更大。

> **`HashMap` 的无序性是可以被观测到的**：`{1, 2, 3}` 与 `{3, 2, 1}` 迭代出来的顺序可能一样，也可能不一样，取决于容量和哈希值。**永远不要让程序正确性依赖它。** 需要稳定顺序时，`LinkedHashMap`（插入顺序）、`TreeMap`（排序）、或者"取出 key 后自己排序"。

---

## 二、Java 通用模板

### 2.1 模板 A：`HashMap` 计数

```java
import java.util.*;

/**
 * 频次统计四件套（任选一种，团队内保持一致）
 * 题目：统计每个元素出现的次数，返回出现次数最多的元素
 */
public class CountTemplate {

    // 写法 1：getOrDefault —— 最通用、最不容易出错，推荐默认使用
    public Map<String, Integer> countByGetOrDefault(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            // 不存在时返回 0，存在时返回旧值：一行完成"读 + 加 1 + 写回"
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
        return cnt;
    }

    // 写法 2：merge —— 语义最贴合"合并"，第三个参数是合并函数
    public Map<String, Integer> countByMerge(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            // key 不存在 → 直接放 1；已存在 → 执行 (旧值, 1) -> 旧值 + 1
            cnt.merge(w, 1, Integer::sum);
        }
        return cnt;
    }

    // 写法 3：computeIfAbsent —— 值本身是"集合"时的唯一优雅写法（分组必需）
    public Map<Integer, List<String>> groupByLength(String[] words) {
        Map<Integer, List<String>> groups = new HashMap<>();
        for (String w : words) {
            // 不存在 → 用 lambda 造一个空 List 放进 map 并返回它；存在 → 直接返回旧 List
            groups.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        }
        return groups;
    }
}
```

**为什么推荐 `getOrDefault`？** 因为它的读与写在**同一行**完成，不会出现"忘了写 `else` 分支"的 bug。

> 还有一种常见写法 `map.putIfAbsent(w, 0); map.put(w, map.get(w) + 1);`——它需要**两个语句、三次哈希查找**，纯属把一行的事拆成两行，不推荐。

**为什么分组必须用 `computeIfAbsent`？** 如果用 `getOrDefault`：

```java
// ❌ 错误：新造的 ArrayList 没有放回 map，分组全丢
groups.getOrDefault(len, new ArrayList<>()).add(w);

// ✅ 正确：computeIfAbsent 会把新造的 List 存进 map
groups.computeIfAbsent(len, k -> new ArrayList<>()).add(w);
```

这个坑非常隐蔽——代码能跑，不报错，只是 `map` 里永远是空的。

### 2.2 模板 B：`HashSet` 去重 / 存在性判断

```java
import java.util.*;

/**
 * 去重与存在性判断的三个惯用法
 */
public class SetTemplate {

    // 惯用法 1：把 add 的返回值当"是否重复"的信号 —— 省掉一次 contains
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            // add 返回 false 说明集合里已经有 x 了
            if (!seen.add(x)) {
                return true;
            }
        }
        return false;
    }

    // 惯用法 2：构造器一次完成"去重"，再转回数组
    public int[] dedup(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int[] res = new int[set.size()];
        int i = 0;
        for (int x : set) res[i++] = x;   // 顺序不可预测！需要顺序就用 LinkedHashSet
        return res;
    }
}
```

**`Set.add` 的返回值是被严重低估的一个特性**。`if (!set.add(x)) return true;` 比 `if (set.contains(x)) return true; set.add(x);` 少一次查找，而且不会出现"判断了却没加"的疏漏。

用一个实战例子体会"数组 vs 哈希"的取舍——求交集的两种版本：

### 2.3 模板 C：数组当哈希表（key 空间小的时候优先用它）

```java
/**
 * 数组即哈希表：下标就是 key，值就是 value
 * 适用前提：key 的范围已知、连续、且不大
 */
public class ArrayAsHash {

    // 场景 1：26 个小写字母 → int[26]
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;   // 'a'..'z' 映射到 0..25
            cnt[t.charAt(i) - 'a']--;   // 一边加一边减：最后全 0 就是异位词
        }
        for (int c : cnt) {
            if (c != 0) return false;
        }
        return true;
    }

    // 场景 2：nums[i] ∈ [1, n] → int[n + 1]，下标即值
    // 645. 错误的集合：一个数重复、一个数丢失
    public int[] findErrorNums(int[] nums) {
        int[] cnt = new int[nums.length + 1];
        for (int x : nums) cnt[x]++;      // 下标 0 空着不用，语义更直观
        int dup = 0, missing = 0;
        for (int v = 1; v <= nums.length; v++) {
            if (cnt[v] == 2) dup = v;
            if (cnt[v] == 0) missing = v;
        }
        return new int[]{dup, missing};
    }

    // 场景 3：查表替换 —— key 是字符，value 是一个数
    // 806. 写字符串需要的行数：widths[0..25] 就是一张"字符 → 宽度"的哈希表
    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1, used = 0;
        for (char c : s.toCharArray()) {
            int w = widths[c - 'a'];      // 一次数组访问，等价于一次哈希查找
            if (used + w > 100) {         // 这一行放不下了
                lines++;
                used = 0;
            }
            used += w;
        }
        return new int[]{lines, used};
    }
}
```

### 2.4 模板之后：逐个解释易错点

#### ① `getOrDefault` 的默认值类型必须匹配

```java
Map<Character, Integer> map = new HashMap<>();
map.getOrDefault('a', 0);        // ✅ 0 自动装箱为 Integer
int n = map.getOrDefault('a', 0); // ✅ 返回值自动拆箱
long x = map.getOrDefault('a', 0L); // ✅
```

如果 value 存的是 `Long` 而默认值写 `0`（`int`），编译能过但语义容易混乱，**保持默认值与 value 类型一致**。

#### ② `get` 返回 `null` 时的拆箱炸弹

```java
Map<String, Integer> map = new HashMap<>();
int v = map.get("missing");      // 💥 NullPointerException！
int v2 = map.getOrDefault("missing", 0); // ✅
```

这是刷题最常遇到的运行时错误之一：**局部测试样例过了（因为 key 存在），换个用例就崩**。

#### ③ `Integer` 的比较不要用 `==`

```java
Map<String, Integer> map = new HashMap<>();
if (map.get(k) == 1) { }         // ⚠️ 两个 Integer 的引用比较
```

`Integer` 有 `-128..127` 的缓存，小数字恰好"看起来正确"，一旦计数值超过 127 就会静默出错。**注意**：如果一边是 `int`（比如字面量 `1`，或先赋值给 `int` 变量），Java 会自动拆箱做数值比较，是安全的。稳妥写法：

```java
if (map.get(k).equals(1)) { }        // 显式 equals
int c = map.getOrDefault(k, 0);
if (c == 1) { }                      // 先拆箱成 int 再比，推荐
```

#### ④ 遍历时不能增删键

```java
// ❌ ConcurrentModificationException
for (String k : map.keySet()) { if (k.startsWith("a")) map.remove(k); }
// ✅ Java 8+：removeIf（keySet / entrySet 都可用）
map.keySet().removeIf(k -> k.startsWith("a"));
```

#### ⑤ 扩容的容量预估

```java
// 已知要放 10000 个元素，直接指定初始容量，避免多次扩容（每次扩容要 rehash 全部元素）
Map<Integer, Integer> map = new HashMap<>(16384);   // 建议：期望容量 / 0.75 向上取整
```

LeetCode 上数据量小时不写也没问题，但**面试时主动说一句"预设初始容量避免扩容"是加分项**。

---

## 三、典型例题

### 1. 两数之和（哈希版）

**题意一句话**：在数组中找两个数，和为 `target`，返回下标。

**思路**：暴力的瓶颈是"对每个 `nums[i]`，都要扫一遍数组找 `target - nums[i]`"——`O(n^2)`。这一步扫的其实只是"**之前见过哪些数**"，所以边遍历边把见过的数放进哈希，内层 `O(n)` 直接变 `O(1)`。

```java
public int[] twoSum(int[] nums, int target) {
    // key = 数值，value = 下标；一遍遍历中同时完成"查"和"存"
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];
        // 先查：此时 map 里只有 i 之前的元素，天然避免了"自己配自己"
        if (seen.containsKey(need)) {
            return new int[]{seen.get(need), i};
        }
        seen.put(nums[i], i);   // 后存，保证不会用到当前元素
    }
    return new int[0];
}
```

> 参考实现：[src/hashTable/TwoSum.java](../src/hashTable/TwoSum.java)

**复杂度**：时间 `O(n)`（每个元素一次哈希查找、一次插入）；空间 `O(n)`（最坏要存下 n-1 个数）。

> ⚠️ **仓库版本说明**：`TwoSum.java` 里的 `twoSum` 实现的是另一道题（有序数组、返回**数值**而非下标），而且写法绕：先把所有数塞进 `Set`，再对每个 `nums[i]` 判断 `target - nums[i]` 是否存在，为了处理 `target == 2 * nums[i]` 还需要额外数一遍重复次数。它把"查补数"和"自己配自己"两个问题混在了一起。上面给的**推荐版本**用"先查后存"一次性解决了这个问题——**因为当前元素还没进表，`need == nums[i]` 的情况永远不会命中自己**。仓库里还有一个 `twoSum2` 用的是排序后双指针，返回的也是数值。

*两数之和是【哈希映射】的第一课，关键突破点是"先查补数、再把当前数存表"，边界是【空数组返回空、同一个元素不能用两次】。*

### 2. 字母异位词分组（两种 key）

**题意一句话**：把互为字母异位词的字符串分到同一组。

**思路**：暴力两两比较每对词是否异位是 `O(n^2 · k)`。关键洞察是：**只要给每个词算一个"同组词完全相同的 key"，一次遍历就能分组**。于是问题变成"怎么设计 key"。

```java
// 解法一：排序后的字符串当 key —— O(n · k log k)，代码最短
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        String key = new String(cs);                        // "eat" / "tea" / "ate" → "aet"
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}

// 解法二：26 位计数数组编码成 key —— O(n · k)，比排序快
public List<List<String>> groupAnagrams2(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        // 关键：把数组"序列化"成字符串。不能写 cnt.toString()（那是地址）！
        StringBuilder key = new StringBuilder();
        for (int c : cnt) key.append(c).append('#');   // '#' 分隔，避免 1,11 和 11,1 混淆
        map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}
```

> 参考实现：[src/hashTable/GroupAna.java](../src/hashTable/GroupAna.java)、[src/hashTable/NumSpecialEquivGroups.java](../src/hashTable/NumSpecialEquivGroups.java)

两种 key 的对比：

| 项 | 排序 key | 计数 key |
| --- | --- | --- |
| 时间 | `O(n · k log k)` | `O(n · k)` |
| 空间（单个 key） | `k` | `26` 或 `O(1)` |
| 代码量 | 3 行 | 5 行 + 一个分隔符坑 |
| 适用 | 字符集大、词长短 | 字符集小（仅小写字母） |

> ⚠️ **仓库版本说明**：`GroupAna.java` 用 `Arrays.sort` 后的字符串的 `hashCode()`（一个 `int`）当 key，再靠 `Set<Integer>` 去重、再双重遍历回填结果。它的**思路是对的**（排序做 key），但把 `String` 压成 `int` 引入了理论上的哈希冲突风险，时间复杂度也退化成了 `O(n^2)`（外层遍历 key、内层遍历全部词）。**推荐版本**直接用 `String` 当 key 并用 `computeIfAbsent` 分组，一次遍历完成。另外注意它新建了 `List<String> strList`、`List<Integer> valueList` 两个平行数组，这是典型的"用两个 List 模拟 Map"——直接用 `Map` 就够。

**第 893 题「特殊等价字符串组」是同一个套路的进阶版**：奇数位和偶数位可以**各自**任意交换，所以 key = 奇数位排序 + 偶数位排序，最后 `Set` 的大小就是组数。见 [src/hashTable/NumSpecialEquivGroups.java](../src/hashTable/NumSpecialEquivGroups.java)。

> **一组题的共同套路**：key 的设计原则是"**把可以自由重排的部分规范化**"。异位词能整体重排 → 整体排序/计数；特殊等价字符串只能奇偶位内部重排 → 分开排序。

*字母异位词分组是【规范化 key + 哈希分组】，关键突破点是"设计一个同组词完全相同的 key"，边界是【空字符串要进组、空输入返回空表、计数 key 必须加分隔符】。*

### 3. 同构字符串

**题意一句话**：`s` 和 `t` 长度相同，`s` 的每个字符能否稳定替换成 `t` 对应位置的字符（不允许两个不同字符映射到同一个字符）。

**思路**：这是一道"**双向映射**"题，也是最经典的哈希陷阱题。

```
s = "badc"   t = "baba"
只看 s → t：b→b, a→a, d→b, c→a    每个 s 字符都只对应一个 t 字符 ✅
但 t → s：  b 同时被 b 和 d 映射        ❌ 两个不同字符撞到同一个 t 字符
```

```java
public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Character> s2t = new HashMap<>();
    Map<Character, Character> t2s = new HashMap<>();   // 反向表：防止多对一
    for (int i = 0; i < s.length(); i++) {
        char a = s.charAt(i), b = t.charAt(i);
        // 不用 getOrDefault：因为"合法的值"也可能是某个字符，必须区分"没登记"和"登记了但不同"
        if (s2t.containsKey(a) && s2t.get(a) != b) return false;
        if (t2s.containsKey(b) && t2s.get(b) != a) return false;
        s2t.put(a, b);
        t2s.put(b, a);
    }
    return true;
}
```

> 参考实现：[src/hashTable/IsomorphicStr.java](../src/hashTable/IsomorphicStr.java)、[src/hashTable/WordPattern.java](../src/hashTable/WordPattern.java)

**这里为什么不能省掉反向表？** 因为映射关系必须是**双射**。单看 `s → t` 会漏掉"两个不同字符映射到同一个字符"的情况。仓库版本 `IsomorphicStr.java` 意识到了这一点，但检测方式是"最后统计 `map.values()` 有没有重复"——**只在最终状态检查是不够的**吗？其实对"单射"检查来说等价（值集合无重复 ⟺ 是单射），但代码里 `new ArrayList<>(values)` 与 `new HashSet<>(values)` 的比较多构造了两个集合。**推荐做法是边遍历边用反向表拦截**，可以提前 `return false`，也更容易理解。

*同构字符串是【双向哈希映射】，关键突破点是"必须维护两张互为反向的表"，边界是【长度不等直接 false、字符与自身映射是合法的】。*

### 4. 宝石与石头

**题意一句话**：`jewels` 里的字符是宝石，数一数 `stones` 里有多少个宝石。

**思路**：暴力的内层循环在做"这个石头是不是宝石"的查找；把 `jewels` 放进 `Set`，这次查找变 `O(1)`。

```java
public int numJewelsInStones(String jewels, String stones) {
    Set<Character> jewelSet = new HashSet<>();
    for (char c : jewels.toCharArray()) jewelSet.add(c);
    int count = 0;
    for (char c : stones.toCharArray()) {
        if (jewelSet.contains(c)) count++;   // O(1) 判断
    }
    return count;
}
```

> 参考实现：[src/hashTable/JewelInStone.java](../src/hashTable/JewelInStone.java)

**复杂度**：时间 `O(m + n)`，空间 `O(m)`（m = 宝石种类数，最多 52）。题目保证只含英文字母时，可以开 `boolean[128]` 当哈希表，省掉 `Set` 的装箱开销。

*宝石与石头是【Set 存在性判断】，关键突破点是"把被查询的集合预先装进哈希"，边界是【jewels 或 stones 为空字符串时返回 0】。*

### 5. 只出现一次的数字

**题意一句话**：除某个元素只出现一次外，其余都出现两次，找出它。

**思路**：哈希计数是最直观的 `O(n)` 解；但这题有更优的 `O(1)` 空间解——**异或**。

```java
// 解法一：哈希计数，O(n) 时间 / O(n) 空间，通用（出现两次、三次都能改）
public int singleNumber(int[] nums) {
    Map<Integer, Integer> cnt = new HashMap<>();
    for (int x : nums) cnt.merge(x, 1, Integer::sum);
    for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
        if (e.getValue() == 1) return e.getKey();
    }
    return -1;
}

// 解法二（最优）：异或，O(n) 时间 / O(1) 空间
public int singleNumber2(int[] nums) {
    int res = 0;
    for (int x : nums) res ^= x;   // a ^ a = 0，a ^ 0 = a，异或满足交换律
    return res;
}
```

> 参考实现：[src/hashTable/SingleNum.java](../src/hashTable/SingleNum.java)

**为什么异或能行？** 异或满足交换律和结合律，`x ^ x = 0`，`0 ^ y = y`。所有出现两次的数两两抵消，剩下的就是只出现一次的那个。

| 解法 | 时间 | 空间 | 备注 |
| --- | --- | --- | --- |
| 哈希计数 | `O(n)` | `O(n)` | 通用，能处理"出现 1 次/3 次"等变体 |
| 排序后相邻比较 | `O(n log n)` | `O(1)` | 会破坏原数组 |
| **异或** | `O(n)` | `O(1)` | 最优，但只适用于"其余出现偶数次" |

*只出现一次的数字是【计数哈希与位运算的取舍】，关键突破点是"发现重复次数是偶数就可以用异或"，边界是【单元素数组直接返回该元素、异或初值必须是 0】。*

### 6. 最常见的单词

**题意一句话**：把段落按非字母字符切词、转小写，排除 `banned` 后返回出现次数最多的词。

**思路**：两步——**切词**（模拟题）+ **计数取最大**（哈希题）。切词的坑在于"连续多个非字母字符"和"结尾没有分隔符"。

```java
public String mostCommonWord(String paragraph, String[] banned) {
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    // 技巧：在末尾补一个非字母字符，省掉"循环结束后还要处理最后一段"的特判
    paragraph += " ";

    Map<String, Integer> cnt = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    for (char c : paragraph.toCharArray()) {
        if (Character.isLetter(c)) {
            sb.append(Character.toLowerCase(c));
        } else if (sb.length() > 0) {      // 遇到分隔符且缓冲区非空 → 一个词结束
            String w = sb.toString();
            sb.setLength(0);               // 复用同一个 StringBuilder，避免反复 new
            if (!bannedSet.contains(w)) {
                cnt.merge(w, 1, Integer::sum);
            }
        }
    }

    String best = "";
    int max = 0;
    for (Map.Entry<String, Integer> e : cnt.entrySet()) {
        if (e.getValue() > max) {
            max = e.getValue();
            best = e.getKey();             // 严格大于 → 并列时取任意一个即可
        }
    }
    return best;
}
```

> 参考实现：[src/hashTable/MostCommonWord.java](../src/hashTable/MostCommonWord.java)

**复杂度**：时间 `O(n + m)`（n 为段落长度，m 为 banned 长度）；空间 `O(n)`。

> **两个易错点**：（1）`paragraph += " "` 这个补尾字符的技巧必须要有，否则最后一个词会被丢掉；（2）如果题目要求"并列时返回字典序最小的"，`>` 就要改成"`>` 或 `== 且 word.compareTo(best) < 0`"。

*最常见的单词是【切词 + 频次哈希】，关键突破点是"在末尾补分隔符统一处理边界"，边界是【全段落无字母时词表为空、banned 词不参与计数】。*

---

## 四、易错点清单

| 易错点 | 后果 | 正确做法 |
| --- | --- | --- |
| `map.get(k)` 直接赋给 `int` | key 不存在时 `NullPointerException` | `getOrDefault(k, 0)` |
| 用 `Integer` 的 `==` 比较计数 | 超过 127 后静默出错 | 先拆箱成 `int` 再比，或用 `equals` |
| 期望 `HashMap` 按插入顺序遍历 | 结果顺序随机、扩容后变化 | 用 `LinkedHashMap` / `TreeMap`，或取出后排序 |
| 分组时写 `getOrDefault(k, new ArrayList<>()).add(x)` | 新 List 没存回 map，分组全空 | `computeIfAbsent(k, x -> new ArrayList<>()).add(...)` |
| 遍历 `Map` 时 `map.remove` | `ConcurrentModificationException` | `Iterator.remove()` 或 `keySet().removeIf(...)` |
| 计数 key 用 `int[26].toString()` | 得到的是地址字符串，所有词同类 | 拼接成字符串，并用分隔符隔开 |
| 用可变对象（数组、可变 List）当 key | `hashCode` 变了就永远找不到 | 用 `String`/`Integer` 等不可变类型 |
| 自己算下标时忘了取绝对值 | 负下标 → 数组越界 | `Math.abs(key.hashCode()) % n` |
| 哈希表 key 空间小却硬用 `Map` | 慢几倍，代码更长 | 用 `int[26]` / `int[n+1]` |
| 认为哈希查找一定是 `O(1)` | 复杂度分析写错 | 必须说"**平均** `O(1)`，最坏 `O(n)`" |

---

## 五、本节练习

### 入门

| 题号 | 题目 | 关键点/模板 | 参考实现 |
| --- | --- | --- | --- |
| 1 | 两数之和 | 先查后存，哈希映射 | [TwoSum.java](../src/hashTable/TwoSum.java) |
| 771 | 宝石与石头 | `Set.contains` | [JewelInStone.java](../src/hashTable/JewelInStone.java) |
| 217 | 存在重复元素 | `!set.add(x)` | [DuplicateNum.java](../src/hashTable/DuplicateNum.java) |
| 136 | 只出现一次的数字 | 计数哈希 / 异或 | [SingleNum.java](../src/hashTable/SingleNum.java) |
| 645 | 错误的集合 | `int[n+1]` 数组当哈希表 | [ErrorNums.java](../src/hashTable/ErrorNums.java) |
| 806 | 写字符串需要的行数 | 数组查表 | [NumOfLines.java](../src/hashTable/NumOfLines.java) |
| 500 | 键盘行 | 数组/`Map` 查表 | [KeyboardWords.java](../src/hashTable/KeyboardWords.java) |
| 804 | 唯一摩尔斯密码词 | `Set` 去重计数 | [UniqueMorse.java](../src/hashTable/UniqueMorse.java) |
| 383 | 赎金信 | 频次相减 | [RansomMagazine.java](../src/hashTable/RansomMagazine.java) |
| 884 | 两句话中的不常见单词 | 全局计数 = 1 | [UnCommonSentence.java](../src/hashTable/UnCommonSentence.java) |

### 进阶（面试高频）

| 题号 | 题目 | 关键点 | 参考实现 |
| --- | --- | --- | --- |
| 49 | 字母异位词分组 | 规范化 key（排序 / 计数） | [GroupAna.java](../src/hashTable/GroupAna.java) |
| 205 | 同构字符串 | 双向映射表 | [IsomorphicStr.java](../src/hashTable/IsomorphicStr.java) |
| 290 | 单词规律 | 字符 ↔ 单词 双向映射 | [WordPattern.java](../src/hashTable/WordPattern.java) |
| 819 | 最常见的单词 | 切词 + 计数 | [MostCommonWord.java](../src/hashTable/MostCommonWord.java) |
| 128 | 最长连续序列 | `Set` + 只从起点扩展（`O(n)`） | [LongestConsecutive.java](../src/UnionFind/LongestConsecutive.java) |
| 380 | O(1) 时间插入、删除、获取随机元素 | 数组 + 哈希 | [RandomizedSet.java](../src/hashTable/RandomizedSet.java) |
| 146 | LRU 缓存 | 哈希 + 双向链表 | [LRUCache4.java](../src/hashTable/LRUCache4.java) |
| 460 | LFU 缓存 | 双哈希 + 频次分组 | [LFUCache2.java](../src/hashTable/LFUCache2.java) |
| 705 / 706 | 设计哈希集合 / 哈希映射 | 拉链法 | [MyHashSet.java](../src/hashTable/MyHashSet.java)、[MyHashMap.java](../src/hashTable/MyHashMap.java) |
| 609 | 在系统中查找重复文件 | 内容 → 路径列表 | [SearchFile.java](../src/hashTable/SearchFile.java) |
| 811 | 子域名访问计数 | 字符串切分 + 计数 | [SubDomainVisits.java](../src/hashTable/SubDomainVisits.java) |
| 677 | 键值映射 | 前缀匹配求和 | [MapSum.java](../src/hashTable/MapSum.java) |
| 447 | 回旋镖的数量 | 距离计数 + 排列数 | [Boomerangs.java](../src/hashTable/Boomerangs.java) |
| 1160 | 拼写单词 | 计数比较 | [ShortestCompletingWord.java](../src/hashTable/ShortestCompletingWord.java) |
| 916 | 单词子集 | 26 位取 max 再比较 | [WordSubSet.java](../src/hashTable/WordSubSet.java) |

### 二刷清单（48 小时后重做）

- [ ] 1. 两数之和 —— 能讲清"为什么必须**先查后存**"，先说清楚它怎么避免自己配自己
- [ ] 49. 字母异位词分组 —— 能默写排序 key 和计数 key 两种写法，记得 `#` 分隔符
- [ ] 205. 同构字符串 / 290. 单词规律 —— 能默写双向映射的两个 `containsKey` 判断
- [ ] 819. 最常见的单词 —— 记得在段落末尾补分隔符
- [ ] 217. 存在重复元素 —— 能写出 `!set.add(x)` 这个一行版

---

**下一节 →** [02 计数与去重](02-计数与去重.md)
