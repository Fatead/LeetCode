# 04 字符串

> **这一章学什么**：字符串是数组的"表亲"——底层也是连续内存，但多了一层**不可变性**。这一章把字符串题分成五大类：**模拟、反转、匹配、编码解码、字符统计**。
> **为什么重要**：面试第一题有极大概率是字符串题。而字符串题最坑人的地方恰恰不是算法，而是 `String` 不可变导致的 `O(n^2)`、`substring` 的左闭右开、`charAt` 的下标越界、`==` 与 `equals` 的区别——这些"低级错误"能在 5 分钟内毁掉一场面试。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 字符串基础与常用 API](01-字符串基础与常用API.md) | 不可变性与 `StringBuilder`、API 速查、字母表计数数组 | ★☆☆☆☆ | ★★★★★ |
| [02 双指针与反转](02-双指针与反转.md) | 对撞双指针、原地反转、回文、退格比较 | ★★☆☆☆ | ★★★★★ |
| [03 字符串匹配与查找](03-字符串匹配与查找.md) | `strStr`、KMP 思想、`s+s` 技巧、同构、滑动窗口 | ★★★★☆ | ★★★★☆ |
| [04 编码解码与解析](04-编码解码与解析.md) | 栈展开、大数运算、版本号、路径化简、atoi | ★★★★☆ | ★★★★☆ |

---

## 一、为什么单独给字符串开一章

数组那一章讲的是"下标操作"，字符串题考的是**另外三件事**：

1. **不可变性的代价**。`s += c` 在循环里是 `O(n^2)`，写了就超时。这是 LeetCode 上最经典的性能陷阱。
2. **API 熟练度**。`charAt` / `substring` / `split` / `Character.isLetterOrDigit` / `StringBuilder.reverse` 这些 API 是否闭眼能写，直接决定你能不能把字符串题写成"填空题"。
3. **字符串是"多模板混合"的战场**。同一道题可能同时用双指针 + 哈希表 + 栈。比如 [394. 字符串解码](04-编码解码与解析.md) 用栈，[567. 字符串的排列](03-字符串匹配与查找.md) 用滑动窗口，[28. 找出字符串中第一个匹配项的下标](03-字符串匹配与查找.md) 用 KMP。

> **一句话**：字符串题 = 数组的下标技巧 + 不可变性的 API 性能 + 字符计数。

---

## 二、语言基本功

### 2.1 三兄弟怎么选：`String` / `StringBuilder` / `char[]`

| 类型 | 可变 | 追加代价 | 随机访问 | 什么时候用 |
| --- | --- | --- | --- | --- |
| `String` | ❌ 不可变 | `O(n)`（每次都新建对象） | `O(1)` `charAt` | 只读、拼接次数很少、做 Map 的 key |
| `StringBuilder` | ✅ | 均摊 `O(1)` | `O(1)` `charAt` | **需要动态增长**：循环里拼字符串、当栈用 |
| `char[]` | ✅ | 不支持（定长） | `O(1)`，**最快** | 原地修改、定长结果、力扣签名就是 `char[]` |

**决策口诀**：**"长度会变"选 `StringBuilder`，"原地改"选 `char[]`，其余用 `String`。**

```java
// ❌ O(n^2)：每次 += 都新建一个 String 并把旧内容复制过去
String s = "";
for (int i = 0; i < n; i++) {
    s += "a";                 // 第 i 次复制 i 个字符，总共 1+2+...+n = O(n^2)
}

// ✅ O(n)：StringBuilder 内部是可变 char 数组，满了才翻倍扩容
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append("a");
}
String result = sb.toString();

// ✅ 已知最终长度时更好：预留容量，一次扩容都不用
StringBuilder sb2 = new StringBuilder(n);
```

> ⚠️ `StringBuilder` 的常用方法名和 `String` 不一样：是 `length()`、`charAt(i)`、`setCharAt(i, c)`、`deleteCharAt(i)`、`delete(l, r)`、`insert(i, s)`、`reverse()`、`toString()`。**没有 `substring` 的"共享"语义，`substring` 返回 `String`。**

### 2.2 不可变性：`String` 到底不可变在哪

```java
String a = "abc";
String b = a;              // b 和 a 指向同一个对象（字符串常量池）
a = a + "d";               // 不是"修改 a"，而是新建 "abcd"，把 a 的引用改指过去
System.out.println(b);     // "abc" —— b 完全没受影响
```

这带来两个刷题后果：

- **`String` 可以安全地当 Map 的 key**（`HashMap` 依赖 `hashCode` 不变）。
- **`String` 做参数传递时，方法内的"修改"对外不可见**。想改只能返回新串。

### 2.3 互转与常用 API

```java
// 1. String <-> char[]
char[] arr = s.toCharArray();          // 复制一份，改 arr 不影响 s
String s2 = new String(arr);           // 最常用
String s3 = String.valueOf(arr);       // 等价
String s4 = new String(arr, 1, 3);     // 从下标 1 取 3 个字符

// 2. charAt / substring（左闭右开！）
char c = s.charAt(0);                  // 越界抛 StringIndexOutOfBoundsException
String sub = s.substring(1, 4);        // 取下标 1、2、3，不含 4
String tail = s.substring(2);          // 从 2 到末尾
int last = s.length() - 1;             // 最后一个字符的下标

// 3. 字符串 <-> 数字
int i = Integer.parseInt(" -42 ".trim());   // 会抛 NumberFormatException
String t = String.valueOf(123);             // 比 123 + "" 更快、更清晰
int i2 = Integer.valueOf("42");             // 返回 Integer，自动拆箱
long l = Long.parseLong("9999999999");

// 4. Character 工具类（比手写 char 区间好，还能处理 Unicode）
Character.isLetter(c);          Character.isDigit(c);
Character.isLetterOrDigit(c);   Character.isWhitespace(c);
Character.isUpperCase(c);       Character.isLowerCase(c);
Character.toLowerCase(c);       Character.toUpperCase(c);
char d = (char) (c + 32);       // 手写小写转换：'A'=65, 'a'=97，差 32
int idx = c - 'a';              // 字母表下标：'a'->0, 'z'->25（最常用的一行）

// 5. 拼接与分割
String j = String.join("-", "a", "b");       // "a-b"
String[] parts = "a,b,,c".split(",");        // ["a","b","","c"]，空串保留
String[] p2 = "a,b,,".split(",");            // ["a","b"]，末尾空串被丢弃！
String[] p3 = "a,b,,".split(",", -1);        // ["a","b","",""]，limit=-1 才保留
String[] p4 = "a.b".split("\\.");            // 正则元字符必须转义
```

### 2.4 `equals` 与 `==`：字符串题的头号送命题

```java
String a = "abc";                  // 字符串常量池
String b = "abc";                  // 复用池中同一个对象
String c = new String("abc");      // 堆上新对象
String d = a.substring(0, 3);      // Java 7+ 返回新对象

System.out.println(a == b);        // true  —— 池化，同一个引用
System.out.println(a == c);        // false —— 不同对象
System.out.println(a.equals(c));   // true  —— 比较内容 ✅
System.out.println(a == d);        // false —— 别赌，永远不要用 ==
```

**铁律**：**比内容一律 `equals`；`==` 只用来判 `null` 或比较 `char` / `int`。**

```java
if (s == null || s.equals("")) { }        // ✅ 先判 null
if ("".equals(s)) { }                     // ✅ 更稳，null 也不会 NPE
if (s.equals("abc")) { }                  // ❌ s 为 null 时 NPE
```

### 2.5 三行高频模板：字母表计数数组

字符串题的"哈希表"99% 场景其实就是一个小数组：

```java
/** 统计小写字母出现次数：array[0] 表示 'a' 的个数 */
int[] cnt = new int[26];
for (char c : s.toCharArray()) {
    cnt[c - 'a']++;                   // 'a'->0 ... 'z'->25
}

/** 需要处理大小写：开 52 或 128 的数组 */
int[] cnt2 = new int[128];            // 直接用 ASCII 值当下标，最省心
for (int i = 0; i < s.length(); i++) {
    cnt2[s.charAt(i)]++;
}

/** 判断两个字符串是否互为字母异位词：计数数组相等即可（不用排序） */
boolean isAnagram(String a, String b) {
    if (a.length() != b.length()) return false;
    int[] cnt = new int[26];
    for (int i = 0; i < a.length(); i++) {
        cnt[a.charAt(i) - 'a']++;
        cnt[b.charAt(i) - 'a']--;     // 一边加一边减，最后全 0 就是异位词
    }
    for (int x : cnt) if (x != 0) return false;
    return true;
}
```

> 注意：**字符下标越大越安全，但 `new int[26]` 是最高频写法**，因为面试官默认输入是小写字母。如果题目说"包含所有 ASCII 字符"，就要开 128。

---

## 三、本章学习路径

```
第 1 天  01 基础与 API      → 把上面 2.1~2.5 的代码默写一遍，做 5 道简单题
第 2 天  02 双指针与反转    → 做 6 道，重点是 151 / 680 / 844
第 3 天  03 匹配与查找      → 做 6 道，重点是 28 / 459 / 567
第 4 天  04 编码解码与解析  → 做 6 道，重点是 394 / 8 / 43
第 5 天  回刷 + 限时模拟    → 60 分钟做 3 道 Medium，写完对着本章易错点清单自查
```

**每道题做完必须自问**：我有没有在循环里用 `+=` 拼 `String`？有没有把 `substring(i, j)` 当成闭区间？有没有用 `==` 比字符串？

---

## 四、参考代码索引

本章涉及的所有仓库实现（`src/string/` 下 60+ 个文件中最相关的部分，另有几个散落在 `array`、`hashTable`、`stack`、`slidingWindows`、`number` 目录）：

| 知识点 | 仓库文件 |
| --- | --- |
| 大小写转换 | [src/string/ToLowerCase.java](../src/string/ToLowerCase.java)、[src/string/DetectCapital.java](../src/string/DetectCapital.java) |
| 替换与去空格 | [src/string/ReplaceSpace.java](../src/string/ReplaceSpace.java)、[src/string/CountSegments.java](../src/string/CountSegments.java)、[src/string/LengthOfLastWord.java](../src/string/LengthOfLastWord.java)、[src/string/LastLength.java](../src/string/LastLength.java) |
| 格式化与拼接 | [src/string/LicenseKey.java](../src/string/LicenseKey.java)、[src/string/GoatLatin.java](../src/string/GoatLatin.java)、[src/string/CamelMatch.java](../src/string/CamelMatch.java) |
| 左旋转字符串 | [src/string/ReverseLeftWords.java](../src/string/ReverseLeftWords.java) |
| 反转字符串 | [src/array/ReverseString.java](../src/array/ReverseString.java)、[src/string/ReverseString2.java](../src/string/ReverseString2.java) |
| 反转单词 | [src/string/ReverseWords.java](../src/string/ReverseWords.java)、[src/string/ReverseWords3.java](../src/string/ReverseWords3.java)、[src/string/ReverseWordsInSentence.java](../src/string/ReverseWordsInSentence.java) |
| 反转指定字符 | [src/string/ReverseOnlyLetters.java](../src/string/ReverseOnlyLetters.java)、[src/array/ReverseVowel.java](../src/array/ReverseVowel.java) |
| 回文 | [src/string/Palindrome.java](../src/string/Palindrome.java)、[src/string/Palindrome2.java](../src/string/Palindrome2.java) |
| 双指针比较 | [src/string/CompareBackSpace.java](../src/string/CompareBackSpace.java)、[src/string/LongPressedName.java](../src/string/LongPressedName.java)、[src/string/SubSequence.java](../src/string/SubSequence.java) |
| 相邻去重 | [src/string/RemoveDuplicates.java](../src/string/RemoveDuplicates.java) |
| 前缀 / 子串匹配 | [src/string/MaxCommonPrefix.java](../src/string/MaxCommonPrefix.java)、[src/string/FindStr.java](../src/string/FindStr.java)、[src/string/RepeatSubString.java](../src/string/RepeatSubString.java)、[src/string/RotateString.java](../src/string/RotateString.java)、[src/string/GcdOfStrings.java](../src/string/GcdOfStrings.java) |
| 同构 / 模式匹配 | [src/hashTable/IsomorphicStr.java](../src/hashTable/IsomorphicStr.java)、[src/hashTable/WordPattern.java](../src/hashTable/WordPattern.java) |
| 字符统计 | [src/string/FirstUniqueChar.java](../src/string/FirstUniqueChar.java)、[src/string/UniqueChar.java](../src/string/UniqueChar.java)、[src/string/BuddyStrings.java](../src/string/BuddyStrings.java)、[src/string/FindDifference.java](../src/string/FindDifference.java) |
| 窗口 / 排列 | [src/slidingWindows/CheckInclusion.java](../src/slidingWindows/CheckInclusion.java)、[src/slidingWindows/CheckInclusion2.java](../src/slidingWindows/CheckInclusion2.java)、[src/slidingWindows/FindAnagrams.java](../src/slidingWindows/FindAnagrams.java) |
| 编码解码 / 解析 | [src/string/DecodeString.java](../src/string/DecodeString.java)、[src/string/ZString.java](../src/string/ZString.java)、[src/string/CompareVersion.java](../src/string/CompareVersion.java)、[src/stack/SimplifyPath.java](../src/stack/SimplifyPath.java) |
| 大数运算 | [src/string/AddString.java](../src/string/AddString.java)、[src/string/BinaryAdd.java](../src/string/BinaryAdd.java)、[src/number/Multiply.java](../src/number/Multiply.java) |
| 罗马数字 / atoi | [src/number/Int2Roman.java](../src/number/Int2Roman.java)、[src/number/Roman2Int.java](../src/number/Roman2Int.java)、[src/number/StringToInt.java](../src/number/StringToInt.java) |
| 括号系列 | [src/stack/ValidParentheses.java](../src/stack/ValidParentheses.java)、[src/string/Parenthesis.java](../src/string/Parenthesis.java)、[src/stack/RemoveOuterParentheses.java](../src/stack/RemoveOuterParentheses.java) |

---

**开始 →** [01 字符串基础与常用 API](01-字符串基础与常用API.md)
