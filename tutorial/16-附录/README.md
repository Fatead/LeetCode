# 16 附录

> 这是整套教程的**速查手册**。考前、面试前过一遍，比重读正文高效得多。所有模板都已在前面章节讲透，这里只做"默写级"的浓缩。

---

## 一、复杂度反推表（最重要）

**看到 `n` 的范围，就知道该用什么复杂度的算法。** 这是解题第一步。

| 输入规模 n | 允许的时间复杂度 | 应该想到的算法 |
| --- | --- | --- |
| ≤ 10 | `O(n!)`、`O(2^n)` | 回溯、全排列、暴力枚举 |
| ≤ 20 | `O(2^n)` | 状态压缩 DP、回溯 + 剪枝 |
| ≤ 100 | `O(n^4)` | 区间 DP、四重循环 |
| ≤ 500 | `O(n^3)` | Floyd、区间 DP |
| ≤ 5000 | `O(n^2)` | 线性 DP、双重循环 |
| ≤ 10^5 | `O(n log n)` | 排序、二分、堆、单调栈 |
| ≤ 10^6 ~ 10^7 | `O(n)` | 双指针、滑动窗口、前缀和、哈希 |
| ≥ 10^8 | `O(log n)`、`O(1)` | 数学、快速幂、位运算 |

---

## 二、模板速查（按专题）

### 二分查找

```java
int left = 0, right = n - 1;             // 闭区间 [left, right]
while (left <= right) {
    int mid = left + (right - left) / 2; // 防溢出
    if (nums[mid] == target) return mid;
    else if (nums[mid] < target) left = mid + 1;
    else right = mid - 1;
}
return -1;
```

### 双指针（对撞）

```java
int l = 0, r = n - 1;
while (l < r) {
    if (条件) l++;
    else r--;
}
```

### 滑动窗口

```java
int l = 0;
for (int r = 0; r < n; r++) {
    加入 nums[r];
    while (窗口不满足条件) { 移除 nums[l]; l++; }
    更新答案（此时 [l, r] 满足条件）;
}
```

### 回溯三步

```java
void backtrack(路径, 选择列表) {
    if (满足结束条件) { res.add(new ArrayList<>(路径)); return; }
    for (选择 : 选择列表) {
        做选择;              // path.add(x)
        backtrack(路径, 新选择列表);
        撤销选择;            // path.removeLast()
    }
}
```

### DP 五步

```
1. dp[i] 含义   2. 转移方程   3. 初始化   4. 遍历顺序   5. 举例手推
```

### 树的递归三部曲

```java
返回值 traverse(TreeNode root) {
    if (root == null) return 终止值;   // 1. 终止条件
    左 = traverse(root.left);          // 2. 递归左右
    右 = traverse(root.right);
    return 用左右推当前;               // 3. 后序位置处理
}
```

### 层序遍历（BFS）

```java
Queue<TreeNode> q = new LinkedList<>();
q.offer(root);
while (!q.isEmpty()) {
    int size = q.size();              // ★ 固定本层
    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
}
```

### 并查集

```java
int find(int x) { while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; } return x; }
void union(int a, int b) { int ra = find(a), rb = find(b); if (ra != rb) p[ra] = rb; }
```

### 0-1 背包 / 完全背包

```java
// 0-1 背包：内层逆序
for (int i = 0; i < n; i++)
    for (int j = W; j >= w[i]; j--)
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);

// 完全背包：内层正序
for (int i = 0; i < n; i++)
    for (int j = w[i]; j <= W; j++)
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
```

### 链表三指针反转

```java
ListNode pre = null, cur = head;
while (cur != null) {
    ListNode next = cur.next;  // 先存
    cur.next = pre;            // 翻转
    pre = cur; cur = next;     // 前进
}
return pre;
```

### 快慢指针（找中点/判环）

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

### 单调栈

```java
Deque<Integer> stack = new ArrayDeque<>();   // 存下标
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i])  // 找右边更大
        res[stack.pop()] = i;
    stack.push(i);
}
```

### 前缀和

```java
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];
// 区间 [l, r] 的和 = prefix[r + 1] - prefix[l]
```

---

## 三、Java 常用 API 速查

```java
// ===== 数组 =====
Arrays.sort(arr);                       // 升序
Arrays.sort(arr, (a, b) -> b - a);      // 降序（需 Integer[]）
Arrays.fill(arr, val);
int[] copy = Arrays.copyOfRange(arr, from, to);
Arrays.binarySearch(arr, key);

// ===== 集合 =====
map.getOrDefault(k, 0);
map.merge(k, 1, Integer::sum);          // 计数神器
map.computeIfAbsent(k, x -> new ArrayList<>()).add(v);  // 分组神器
set.add(x);                             // 返回 false 表示已存在

// ===== 栈/队列/双端 =====
Deque<Integer> stack = new ArrayDeque<>();   // push/pop/peek
Queue<Integer> queue = new LinkedList<>();   // offer/poll/peek
Deque<Integer> deque = new ArrayDeque<>();   // offerFirst/offerLast...

// ===== 堆 =====
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                 // 小顶堆
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);  // 大顶堆

// ===== 字符串 =====
String.valueOf(charArr);
s.toCharArray();
s.substring(begin, end);
s.split("\\s+");
StringBuilder sb = new StringBuilder(); sb.append(x); sb.reverse(); sb.toString();
Character.isDigit(c); Character.isLetter(c); Character.toLowerCase(c);

// ===== 数值 =====
Integer.MAX_VALUE; Integer.MIN_VALUE;
Integer.bitCount(x);                    // 二进制中 1 的个数
Math.max/min/abs/pow;
Integer.parseInt(s);
```

---

## 四、高频易错点总清单

| 分类 | 易错点 | 正确做法 |
| --- | --- | --- |
| 通用 | 整数溢出 | `mid = l + (r - l) / 2`，用 `long` |
| 通用 | `Integer` 用 `==` 比较 | 用 `.equals()` 或拆箱 |
| 哈希 | `map.get` 返回 null 拆箱 NPE | 用 `getOrDefault` |
| 数组 | 二分边界死循环 | 想清楚闭/开区间，`mid` 取法与边界更新配套 |
| 链表 | 反转忘存 next | 永远先 `next = cur.next` |
| 链表 | 删头没用哑结点 | 加 `dummy` |
| 树 | 层序用 `queue.size()` 当循环条件 | 循环前取 `int size` |
| 树 | 子集/路径没拷贝 | `new ArrayList<>(path)` |
| 回溯 | 结果存引用而非快照 | 收集时拷贝 |
| 回溯 | 去重用 `i > 0` 而非 `i > start` | 树层去重用 `i > start` |
| DP | dp 含义没定清 | 先一句话写死定义 |
| DP | 0-1/完全背包遍历方向反 | 0-1 逆序、完全正序 |
| 并查集 | 按秩合并 size 写错 | `size[大根] += size[小根]` |
| 图 | BFS 出队时才标记 visited | 入队时立刻标记 |
| 并发 | `wait` 用 `if` | 用 `while` 重检条件 |

---

## 五、各章模板出处

| 模板 | 详细讲解 |
| --- | --- |
| 二分、双指针、滑动窗口、前缀和 | [01 数组与双指针](../01-数组与双指针/README.md) |
| 哈希计数、LRU | [02 哈希表](../02-哈希表/README.md) |
| 哑结点、反转、快慢指针 | [03 链表](../03-链表/README.md) |
| 字符串匹配 | [04 字符串](../04-字符串/README.md) |
| 单调栈、单调队列 | [05 栈与队列](../05-栈与队列/01-栈与队列基础.md) |
| 递归三要素 | [06 递归与分治](../06-递归与分治/README.md) |
| 回溯三步、剪枝 | [07 回溯与搜索](../07-回溯与搜索/01-回溯模板与思想.md) |
| 贪心、区间调度 | [08 贪心](../08-贪心/README.md) |
| DP 五步、背包、股票 | [09 动态规划](../09-动态规划/README.md) |
| 遍历、BST、路径 | [10 树](../10-树/README.md) |
| DFS/BFS、拓扑、并查集 | [11 图与并查集](../11-图与并查集/README.md) |
| 异或、位掩码 | [12 位运算](../12-位运算/README.md) |
| 矩阵、设计、并发 | [13 模拟与设计](../13-模拟与设计/README.md) |

---

## 六、面试前最后一天清单

- [ ] 复习本附录的「复杂度反推表」和「模板速查」
- [ ] 每个专题默写 1 个核心模板（二分、回溯、DP、树递归、并查集、链表反转）
- [ ] 过一遍「高频易错点总清单」
- [ ] 复习自己的错题本（卡点）
- [ ] 准备好"口述解题流程"：复述题意 → 边界 → 暴力 → 优化 → 写码 → 测样例 → 复杂度

**祝你面试顺利！**

---

**返回 →** [教程总目录](../README.md)
