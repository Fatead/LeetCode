# 02 哈希表

> **这一章学什么**：哈希表是"用空间换时间"最直白的一种实现。它把"在一堆元素里找一个"从 `O(n)` 的线性扫描，变成平均 `O(1)` 的一次定位。
> **为什么重要**：几乎每一道"看起来要两重循环"的题，用哈希都能砍掉一维；而 LRU/LFU 这类设计题，是面试官判断你"会不会写工程代码"的分水岭。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 哈希表基础与 Java 工具](01-哈希表基础与Java工具.md) | `HashMap`/`HashSet` API 与坑、哈希函数直觉、什么时候不能用哈希、数组当哈希表 | ★★☆☆☆ | ★★★★☆ |
| [02 计数与去重](02-计数与去重.md) | 频次统计、去重、重复元素、字母异位词分组、同构字符串 | ★★☆☆☆ | ★★★★☆ |
| [03 映射与查找](03-映射与查找.md) | 两数之和、前缀和 + 哈希、最长连续序列、快乐数、有效数独、回旋镖 | ★★★☆☆ | ★★★★★ |
| [04 设计数据结构](04-设计数据结构.md) | LRU / LFU 缓存、手写 HashMap/HashSet、O(1) 随机集合、设计推特 | ★★★★☆ | ★★★★☆ |

---

## 一、为什么学这一章

哈希表是**性价比最高**的一个专题，原因有三：

1. **它是"降维打击"的通用武器**。看到 `O(n^2)` 的双重循环，第一反应就该是："内层循环在找什么？能不能用哈希把这次查找变成 `O(1)`？"两数之和（[1](../src/hashTable/TwoSum.java)）、最长连续序列（[128](../src/UnionFind/LongestConsecutive.java)）、字母异位词分组（[49](../src/hashTable/GroupAna.java)）都是这个套路。
2. **它是前缀和的最佳搭档**。区间和问题 `sum(i..j) = k` 暴力要 `O(n^2)`，配上哈希就是 `O(n)`（见 [03 映射与查找](03-映射与查找.md)）。
3. **它撑起了一整类设计题**。LRU 缓存（[146](../src/hashTable/LRUCache4.java)）、LFU 缓存（[460](../src/hashTable/LFUCache2.java)）、O(1) 随机集合（[380](../src/hashTable/RandomizedSet.java)）——这些题的共同点是"哈希负责查找，另一个结构负责顺序"，是面试中最能体现工程素养的题型。

**它在面试中的位置**：哈希表很少单独考，它总是**作为最优解的一部分出现**。面试官问"还能更快吗"，很多时候答案就是"加一张哈希表"。

---

## 二、语言基本功：必须先背下来的 Java API

```java
// ========== Map：键值映射 ==========
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);                                // 插入/覆盖
map.get("a");                                   // 取，不存在返回 null（不是 -1！）
map.getOrDefault("b", 0);                       // 取，不存在返回默认值 —— 计数必备
map.containsKey("a");                           // 是否存在这个键
map.remove("a");                                // 删除
map.size();                                     // 键值对个数

// Java 8+ 三个"一行顶三行"的方法：
map.putIfAbsent("a", 0);                        // 只有不存在时才放
map.computeIfAbsent("a", k -> new ArrayList<>()).add(1);  // 不存在就先建一个再返回 —— 分组必备
map.merge("a", 1, Integer::sum);                // 计数神器：不存在放 1，存在则按函数合并
map.getOrDefault("a", 0) + 1 与 map.merge("a", 1, Integer::sum) 完全等价

// 遍历方式一：entrySet（需要键和值，最快）
for (Map.Entry<String, Integer> e : map.entrySet()) {
    String k = e.getKey();
    int v = e.getValue();
}
// 遍历方式二：keySet（只要键）
for (String k : map.keySet()) { }
// 遍历方式三：Java 8 forEach
map.forEach((k, v) -> System.out.println(k + "=" + v));
// ❌ 不要写成 for (String k : map.keySet()) { map.get(k); } 当你要值的时候 —— 多做一次查找

// ========== Set：只关心"在不在" ==========
Set<Integer> set = new HashSet<>();
set.add(1);                 // 返回 boolean：true 表示之前不存在
set.contains(1);            // O(1) 判断
set.remove(1);              // 删除
set.size();
set.addAll(list);           // 批量加入
set.retainAll(list);        // 交集（原地保留共有的）
// 用 Set 一次完成"统计不同元素个数"：new HashSet<>(list).size()

// ========== 三个 Map 兄弟的差别 ==========
Map<String, Integer> hash = new HashMap<>();        // 无序，O(1)
Map<String, Integer> linked = new LinkedHashMap<>();// 保持插入顺序（或访问顺序）
Map<String, Integer> tree = new TreeMap<>();        // 按 key 排序，O(log n)
```

必须记住的语法坑，逐条对照：

| 坑 | 现象 | 正确做法 |
| --- | --- | --- |
| `map.get(k)` 返回 `null` | 自动拆箱成 `int` 时抛 `NullPointerException` | 用 `getOrDefault(k, 0)` |
| `getOrDefault` 之后又 `containsKey` | 多做一次查找，代码还啰嗦 | `getOrDefault` 一次搞定 |
| `map.get(k) == 1` 比较 `Integer` | 小整数（-128~127）有缓存侥幸正确，超出范围就错 | `map.get(k).equals(1)` 或先拆箱成 `int` |
| 键是可变对象（数组、可变 List） | `hashCode` 变了就再也找不到 | 键用 `String`/`Integer`/不可变类型 |
| `HashMap` 的遍历顺序 | 不保证，扩容后顺序还会变 | 需要顺序就用 `LinkedHashMap`/`TreeMap` |
| `set.add(x)` 的返回值 | 丢弃了"是否重复"这个宝贵信息 | `if (!set.add(x)) { /* x 重复 */ }` |
| `Map.Entry` 的 `setValue` | 迭代时改值是可以的，增删键不行 | 要删就用 `Iterator.remove()` |

> **`containsKey` vs `get` 到底怎么选**：如果只需要判断"在不在"，用 `containsKey`（语义清晰）；如果拿到值还要用，用 `getOrDefault` 一次完成，别写 `containsKey` + `get` 两次查找。只有当**合法的值本身就是 `null`** 时，才必须用 `containsKey` 区分——刷题时基本遇不到。

---

## 三、哈希函数的直觉：为什么平均是 `O(1)`

```
put("apple", 5)
   ↓  hashCode("apple") = -1503159937
   ↓  扰动 + 取模  → 桶下标 index = (n-1) & hash
   ↓  落到某个桶（链表/红黑树）里
   ├─ 桶为空          → 直接放进去
   ├─ 桶里有元素      → 逐个 equals 比较（哈希冲突）
   │                    链表长度 ≥ 8 且容量 ≥ 64 时，链表转红黑树
   └─ 元素数 > 容量 × 0.75 → 扩容为 2 倍，所有元素重新分布
```

| 概念 | 含义 | 刷题时要知道的事 |
| --- | --- | --- |
| 哈希函数 | 把任意 key 映射成 int | 好的哈希让元素均匀撒进桶里 |
| 冲突 | 不同 key 落到同一个桶 | 冲突多 → 退化成链表 → 查找变 `O(n)` |
| 负载因子 | 元素数 / 桶数，默认 0.75 | 太高冲突多，太低浪费空间 |
| 扩容 | 元素数超过阈值时桶数翻倍 | **触发扩容的那一次 `put` 是 `O(n)`**，均摊后仍是 `O(1)` |
| 最坏情况 | 所有 key 冲突 | 最坏 `O(n)`，所以哈希表的复杂度必须说"**平均** `O(1)`" |

**Java 的几个关键实现细节**（面试被追问时能答上就加分）：

- `HashMap` 的桶数永远是 **2 的幂**，所以可以用位运算 `(n - 1) & hash` 代替取模，比 `%` 快。
- 计算下标前会把 `hashCode` 的高 16 位异或到低 16 位（**扰动函数**），避免高位信息被丢弃导致冲突。
- `hashCode` 相等 **不保证** `equals` 相等，但 `equals` 相等 **必须** `hashCode` 相等——所以自定义类做 key 必须同时重写这两个方法。
- 自己算下标时务必取绝对值：`int idx = Math.abs(key.hashCode()) % buckets.length;`，因为 `hashCode()` 可能返回负数，负数取模还是负数，直接拿去当数组下标会 `ArrayIndexOutOfBoundsException`。

---

## 四、什么时候**不能**用哈希

这是本章最值钱的一张表。哈希表不是万能的，**选错结构比不会写更致命**：

| 需求 | 哈希表行不行 | 该用什么 | 原因 |
| --- | --- | --- | --- |
| 判断元素是否存在 | ✅ 平均 `O(1)` | `HashSet` | 哈希的主场 |
| 计数/频次统计 | ✅ 平均 `O(1)` | `HashMap` 或计数数组 | 哈希的主场 |
| **需要按 key 有序** | ❌ | `TreeMap` / 排序 | `HashMap` 遍历顺序不可预测，扩容后还会变 |
| **需要按插入顺序遍历** | ❌ | `LinkedHashMap` | `HashMap` 不记录插入顺序 |
| **需要按下标随机访问第 k 个元素** | ❌ | `ArrayList` / 数组 | 哈希只能按 key 查，没有"第 k 个"的概念 |
| **需要范围查询**（找 ≥ x 的最小 key） | ❌ | `TreeMap` 的 `ceilingKey` | 哈希只能精确查 |
| **需要维护访问顺序**（LRU） | ❌ 单独不行 | 哈希 + 双向链表 | 哈希查得快，但不知道"谁最久没用" |
| **需要 O(1) 随机取一个元素** | ❌ 单独不行 | 数组 + 哈希 | 哈希没有下标，只能用迭代器"数到第 k 个"（`O(n)`） |
| **答案必须稳定可复现** | ⚠️ 谨慎 | 排序 / `TreeMap` | 哈希的遍历顺序是实现细节，不要依赖它 |
| key 是可变对象 | ⚠️ 危险 | 换成不可变 key | `hashCode` 一变就永久失联 |
| 数据量极小（n ≤ 20） | ⚠️ 没必要 | 双层循环 | 哈希的常数开销比一次比较还大 |

> **一句话判据**：只问"**在不在 / 有几个**"→ 哈希；只要牵扯到"**顺序 / 排名 / 第几个 / 范围**"→ 哈希要搭配别的结构，或者干脆换结构。

---

## 五、数组当哈希表：最小 key 空间的降维打法

当 key 的取值范围很小时，**数组比 `HashMap` 快几倍**，因为没有哈希计算、没有装箱、没有指针跳转。

```java
// 1. 26 个小写字母计数（替代 Map<Character, Integer>）
int[] cnt = new int[26];
for (char c : s.toCharArray()) cnt[c - 'a']++;
// 用 26 个 int 做 key，判断两个词是否互为字母异位词
// cnt1[c-'a'] == cnt2[c-'a'] 逐个比，或者 Arrays.equals(cnt1, cnt2)

// 2. ASCII 全集（替代 Map<Character, Integer>，含大小写和符号）
int[] ascii = new int[128];
for (char c : s.toCharArray()) ascii[c]++;

// 3. 值域固定的计数（LeetCode 多数题的 nums[i] ∈ [1, n]）
int[] appear = new int[n + 1];          // 下标即值，天然是一张哈希表
for (int x : nums) appear[x]++;
// 典型：645 找重复和丢失的数 → [src/hashTable/ErrorNums.java](../src/hashTable/ErrorNums.java)

// 4. 用"桶"做分组（桶排序 / 桶计数）
List<Integer>[] buckets = new List[n + 1];   // 按出现次数分组

// 5. 用"排序后的字符数组"当 key（字母异位词的经典 key）
char[] cs = word.toCharArray();
Arrays.sort(cs);
String key = new String(cs);                 // 同组词的 key 完全相同
```

**什么时候该用数组而不是 `Map`？**

| 判据 | 用数组 | 用 `Map` |
| --- | --- | --- |
| key 范围 | 有限、连续、已知（字母、0..n 的数） | 任意（字符串、长整数、坐标） |
| key 是否稀疏 | 不稀疏（大部分下标都会被用到） | 稀疏（用数组会浪费大量空间） |
| 性能要求 | 极致（常数最小） | 够用 |
| 典型题 | 有效的字母异位词、[806 写字符串需要的行数](../src/hashTable/NumOfLines.java)、[645](../src/hashTable/ErrorNums.java) | 两数之和、字母异位词分组、[819 最常见的单词](../src/hashTable/MostCommonWord.java) |

> ⚠️ **反例警告**：仓库里的 [src/hashTable/MyHashMap.java](../src/hashTable/MyHashMap.java) 直接开了 `new MyEntry[1000000]`，用"下标即 key"的数组当哈希表。做题能过（因为 key ≤ 10^6），但这**不是哈希表**——它浪费 `O(key 值域)` 的空间，key 一大就崩。真正的手写哈希表见 [04 设计数据结构](04-设计数据结构.md)。

---

## 六、本章学习路径

```
第 1 天  01 哈希表基础与 Java 工具   → 把 Map/Set 的 8 个 API 敲一遍，做 3 道简单题
第 2 天  02 计数与去重               → 频次模板 + 两种分组 key，做 5 道题
第 3 天  03 映射与查找               → 两数之和 + 前缀和哈希，做 6 道题
第 4 天  03 映射与查找（复习）       → 最长连续序列、快乐数、有效数独，重做 3 道错题
第 5 天  04 设计数据结构             → LRU 先手写双向链表，再用 LinkedHashMap 秒杀
第 6 天  04 设计数据结构             → LFU、RandomizedSet、手写 HashMap
第 7 天  本章综合                    → 限时 60 分钟做 LRU + 字母异位词分组 + 和为 K 的子数组
```

**每道设计题都必须**：先自己从零写双向链表 → 写出 LRU → 再去看 `LinkedHashMap` 版本，体会"标准库帮你做了什么"。

---

## 七、参考代码索引

本章涉及的所有仓库实现（均已确认存在）：

| 知识点 | 仓库文件 |
| --- | --- |
| 两数之和（哈希 + 双指针） | [src/hashTable/TwoSum.java](../src/hashTable/TwoSum.java) |
| 字母异位词分组 | [src/hashTable/GroupAna.java](../src/hashTable/GroupAna.java) |
| 特殊等价字符串组 / 分组 key | [src/hashTable/NumSpecialEquivGroups.java](../src/hashTable/NumSpecialEquivGroups.java) |
| 同构字符串 | [src/hashTable/IsomorphicStr.java](../src/hashTable/IsomorphicStr.java) |
| 单词规律 | [src/hashTable/WordPattern.java](../src/hashTable/WordPattern.java) |
| 宝石与石头 | [src/hashTable/JewelInStone.java](../src/hashTable/JewelInStone.java) |
| 最常见的单词 | [src/hashTable/MostCommonWord.java](../src/hashTable/MostCommonWord.java) |
| 按频率排序 / TopK 词 | [src/hashTable/FrequencySort.java](../src/hashTable/FrequencySort.java)、[src/hashTable/TopKWords.java](../src/hashTable/TopKWords.java) |
| 赎金信 / 最短补全词 / 单词子集 | [src/hashTable/RansomMagazine.java](../src/hashTable/RansomMagazine.java)、[src/hashTable/ShortestCompletingWord.java](../src/hashTable/ShortestCompletingWord.java)、[src/hashTable/WordSubSet.java](../src/hashTable/WordSubSet.java) |
| 只出现一次的数字 | [src/hashTable/SingleNum.java](../src/hashTable/SingleNum.java) |
| 重复元素 1 / 2 / 3 | [src/hashTable/DuplicateNum.java](../src/hashTable/DuplicateNum.java)、[src/hashTable/DuplicateNum2.java](../src/hashTable/DuplicateNum2.java)、[src/hashTable/DuplicateNum3.java](../src/hashTable/DuplicateNum3.java) |
| 数组中的重复数字 | [src/hashTable/RepeatNumber.java](../src/hashTable/RepeatNumber.java) |
| 找重复和丢失的数 | [src/hashTable/ErrorNums.java](../src/hashTable/ErrorNums.java) |
| 两个数组的交集（去重版） | [src/array/ArrayIntersection.java](../src/array/ArrayIntersection.java) |
| 两个数组的交集 II（保留次数） | [src/array/ArrayIntersection2.java](../src/array/ArrayIntersection2.java) |
| 最长连续序列 | [src/UnionFind/LongestConsecutive.java](../src/UnionFind/LongestConsecutive.java) |
| 快乐数 | [src/hashTable/HappyNumber.java](../src/hashTable/HappyNumber.java) |
| 重复的 DNA 序列 | [src/hashTable/RepeatedDNA.java](../src/hashTable/RepeatedDNA.java) |
| 有效数独 | [src/hashTable/ValidSudoku.java](../src/hashTable/ValidSudoku.java) |
| 和为 K 的子数组（前缀和 + 哈希） | [src/array/SubArraysDiv.java](../src/array/SubArraysDiv.java)、[src/array/SubArraySumK.java](../src/array/SubArraySumK.java) |
| 回旋镖的数量 | [src/hashTable/Boomerangs.java](../src/hashTable/Boomerangs.java) |
| 数组嵌套 / 环形数组循环 | [src/hashTable/NestArray.java](../src/hashTable/NestArray.java)、[src/hashTable/CircularArrayLoop.java](../src/hashTable/CircularArrayLoop.java) |
| 写字符串需要的行数（数组当哈希表） | [src/hashTable/NumOfLines.java](../src/hashTable/NumOfLines.java) |
| 子域名访问计数 / 查找重复文件 | [src/hashTable/SubDomainVisits.java](../src/hashTable/SubDomainVisits.java)、[src/hashTable/SearchFile.java](../src/hashTable/SearchFile.java) |
| LRU 缓存（四种写法） | [src/hashTable/LRUCache4.java](../src/hashTable/LRUCache4.java)、[src/hashTable/LRUCache3.java](../src/hashTable/LRUCache3.java)、[src/hashTable/LRU.java](../src/hashTable/LRU.java)、[src/hashTable/LRUCache2.java](../src/hashTable/LRUCache2.java) |
| LFU 缓存（两种写法） | [src/hashTable/LFUCache.java](../src/hashTable/LFUCache.java)、[src/hashTable/LFUCache2.java](../src/hashTable/LFUCache2.java) |
| 手写 HashMap / HashSet | [src/hashTable/MyHashMap.java](../src/hashTable/MyHashMap.java)、[src/hashTable/MyHashSet.java](../src/hashTable/MyHashSet.java) |
| O(1) 随机集合 | [src/hashTable/RandomizedSet.java](../src/hashTable/RandomizedSet.java) |
| 设计推特 / 文件系统 | [src/hashTable/Twitter.java](../src/hashTable/Twitter.java)、[src/hashTable/FileSharing.java](../src/hashTable/FileSharing.java) |
| 前缀映射求和 | [src/hashTable/MapSum.java](../src/hashTable/MapSum.java) |

---

**开始 →** [01 哈希表基础与 Java 工具](01-哈希表基础与Java工具.md)
