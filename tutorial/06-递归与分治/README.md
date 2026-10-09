# 06 递归与分治

> **这一章学什么**：递归不是"一种算法"，而是**一种把问题变小的思维方式**。
> 分治、记忆化搜索（DP 的前身）、树的几乎所有操作、回溯的骨架，全都长在递归上面。
> 学不好递归，后面「树」「回溯」「动态规划」三章都会退化成背模板。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 递归思维与三要素](01-递归思维与三要素.md) | 三要素、如何相信递归、递归树、递归转迭代 | ★★☆☆☆ | ★★★★☆ |
| [02 分治算法](02-分治算法.md) | 分治三步、归并排序、快速幂、大数乘法、主定理 | ★★★☆☆ | ★★★★☆ |
| [03 记忆化与递归优化](03-记忆化与递归优化.md) | 重叠子问题、memo 模板、记忆化 vs 递推、剪枝 | ★★★☆☆ | ★★★★★ |
| [04 分治应用进阶](04-分治应用进阶.md) | 快速选择、合并 K 链表、分治求最大子数组/多数元素、归并统计逆序对 | ★★★★☆ | ★★★★☆ |

---

## 一、为什么学这一章

三个很实际的理由：

1. **递归是"读懂别人代码"的门槛**。树、图、回溯、分治的题解几乎全是递归写法；看不出递归函数的语义，就只能一行行模拟栈。
2. **递归是三大专题的共同底座**：树的后序递归能解决一半树题；回溯本质是"递归 + 撤销选择"；**记忆化搜索就是 DP 的自顶向下写法**，先学它再学递推会顺很多。
3. **面试里递归题的定位很特别**：

| 题型 | 面试频率 | 面试官真正想看什么 |
| --- | --- | --- |
| 递归写链表/树（反转、深度、合并） | ★★★★★ | 能不能一句话说清递归函数的语义 |
| 分治（归并、快速幂、快速选择） | ★★★★☆ | 会不会写 merge，会不会分析 `T(n)` |
| 记忆化搜索 | ★★★★★ | 能不能看出"重叠子问题"并加 memo |
| 递归转迭代 | ★★☆☆☆ | 简历写了"熟悉 JVM"才会被追问 |

> **一句话定位**：这一章不是新知识点，而是**把"循环思维"升级成"递归思维"**。升级完成后再看树和 DP，难度会掉一个档。

---

## 二、语言基本功 1：递归函数怎么写

### 2.1 一张图看清递归函数的骨架

```java
/**
 * 递归函数的三要素（写之前先用注释把这三行填好，再写代码）
 *
 * 要素 1 终止条件：规模最小的问题，直接给出答案，不再递归
 * 要素 2 本层逻辑：把当前问题拆成「结构相同、规模更小」的子问题
 * 要素 3 合并：拿到子问题的结果后，算出本层的答案并返回
 */
private int dfs(状态参数) {
    // ① 终止条件：必须先写，且要覆盖所有边界
    if (到达最小规模) return 最小规模的答案;

    // ② 本层逻辑：定义好"往下传什么"
    int 子结果 = dfs(规模更小的状态);

    // ③ 合并：定义好"往上传什么"
    return 合并(本层信息, 子结果);
}
```

**三要素里最容易被忽略的是"要素 0"：函数语义。** 即"这个递归函数返回的到底是什么"。语义没定清楚，终止条件和合并都会写错。

### 2.2 返回值的三种语义（最容易搞混的地方）

用仓库里的真实代码对照，一眼就能分清：

| 语义 | 含义 | 仓库例子 |
| --- | --- | --- |
| **A. 返回值就是答案** | 每层的返回值直接参与最终答案 | `MaxDepth.maxDepth2`：`Math.max(left, right) + 1` |
| **B. 返回值是"给上层用的局部信息"** | 返回值不是答案，最终答案存在成员变量里 | `MaxPathSum.getMax`：返回"以 root 为起点的最大向下路径和"，答案在 `max` |
| **C. 返回 void，靠副作用收集** | 用成员变量 / 集合累积结果 | `MaxDepthTree.dfs`（全局 `maxDepth`）、`GenerateTree.buildTree`（往 `list` 里 add） |

```java
// 语义 A：返回值就是答案
public int maxDepth2(TreeNode root) {
    if (root == null) return 0;                              // 终止条件
    int leftHeight = maxDepth2(root.left);
    int rightHeight = maxDepth2(root.right);
    return Math.max(leftHeight, rightHeight) + 1;             // 合并
}

// 语义 B：返回值是"给上层的信息"，答案在成员变量里
int max = Integer.MIN_VALUE;
private int getMax(TreeNode root) {
    if (root == null) return 0;
    int leftValue = Math.max(getMax(root.left), 0);           // 负数不如不选，直接截断
    int rightValue = Math.max(getMax(root.right), 0);
    max = Math.max(max, root.val + leftValue + rightValue);   // 答案在这里更新
    return root.val + Math.max(leftValue, rightValue);        // 返回给上层的"单边路径"
}
```

> **经验**：写树题时，先用一句话说出"这个函数的返回值是什么"，再动手。`MaxPathSum` 之所以是高频难题，就是因为它同时用了语义 B（返回值）和全局变量（答案）两套通道。

### 2.3 状态传递的四种通道

| 方向 | 手段 | 说明 | 仓库例子 |
| --- | --- | --- | --- |
| 往下传（父 → 子） | 方法参数 | 把"当前已知的信息"带下去 | `maxDepth(node, depth)` |
| 往上传（子 → 父） | 返回值 | 把"子问题的结论"交上来 | `Math.max(left, right) + 1` |
| 全局共享 | 成员变量 / 集合 | 任意层随时读写，**要警惕状态污染** | `count`、`list`、`max` |
| 回退撤销 | 手动恢复 | 回溯的核心动作 | `path.remove(path.size() - 1)` |

**全局变量是初学者的头号 bug 来源**：一旦用了成员变量，递归函数就不再是"纯函数"，多测几组用例就会互相污染（LeetCode 上表现为一交就 WA，本地单测却对）。能靠返回值解决的，就不要用成员变量。

---

## 三、语言基本功 2：递归与迭代怎么选

| 维度 | 递归 | 迭代 |
| --- | --- | --- |
| 代码长度 | 短，接近问题定义 | 长，需要自己维护状态 |
| 可读性 | 高（树/图尤其明显） | 低（树的中序迭代尤其难写） |
| 空间 | `O(递归深度)` 的栈 | `O(1)` 或 `O(自己开的栈)` |
| 栈溢出风险 | **有** | 无 |
| 常数开销 | 每层一次函数调用 | 无调用开销，更快 |
| 适合 | 树、图、分治、回溯 | 线性递推、深度可能到 `10^5` 的问题 |

**决策口诀**：

1. **结构是树/图** → 先写递归，除非深度会爆栈
2. **结构是数组，且每层只有一个子问题** → 直接写循环（链式递归没有递归的必要）
3. **递归深度随输入线性增长，且 `n ≥ 10^4`** → 必须转迭代（见第五节）
4. **面试官说"不能用递归"** → 用显式栈改写（见第五节）

---

## 四、语言基本功 3：递归栈深度与 StackOverflowError

Java 每次方法调用都会在虚拟机栈上压一个**栈帧**（局部变量表 + 操作数栈 + 返回地址）。栈大小固定，压不下就抛 `StackOverflowError`。

```java
// 试一下：这个函数会打印到多少？
public static void deep(int n) {
    if (n == 0) return;
    deep(n - 1);
}
// deep(100_000) → java.lang.StackOverflowError
// 默认 -Xss 在 64 位 HotSpot 上约 512KB ~ 1MB，每帧几十~上百字节 → 大约能递归 1 万 ~ 2 万层
```

| 递归深度 | 风险 | 说明 |
| --- | --- | --- |
| `≤ 100` | 安全 | 快速幂（32 层）、二分（30 层）都在这里 |
| `≤ 5 000` | 基本安全 | 归并排序（`log n` 层）、随机快排（期望 `log n` 层） |
| `10^4` 附近 | **危险** | 链表递归反转、链状树的递归遍历 |
| `≥ 10^5` | 必然溢出 | 只能用迭代 |

**两个必须记住的点**：① `StackOverflowError` 是 `Error` 不是 `Exception`，`catch (Exception e)` 抓不到；② **快排最坏情况会溢出**——每次取端点为 pivot 时递归深度是 `n` 而不是 `log n`，这是必须随机选 pivot 的第二个理由。

---

## 五、语言基本功 4：Java 没有尾递归优化

**事实**：HotSpot JVM 至今**没有实现尾调用消除（TCO）**。理由很实际——需要保留完整栈信息才能打印正确的异常堆栈（`StackOverflowError` 的堆栈、`printStackTrace` 都依赖它）。

```java
// 这两个写法在 Java 里占用的栈空间【完全一样】
private int factTail(int n, int acc) {          // 尾递归写法，Java 不会优化
    if (n <= 1) return acc;
    return factTail(n - 1, n * acc);            // 编译器不会把它变成循环，照样爆栈
}
```

**结论**：不要指望把递归写成尾递归来避免爆栈，只能**手动用显式栈改写**。

### 显式栈改写模板

```java
// 递归版：深度 = 树高，链状树会爆栈
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}

// 显式栈版：把"当前节点 + 当前深度"一起压栈，栈在堆上，不受 JVM 栈限制
public int maxDepthIterative(TreeNode root) {
    if (root == null) return 0;
    Deque<TreeNode> nodeStack = new ArrayDeque<>();
    Deque<Integer> depthStack = new ArrayDeque<>();
    nodeStack.push(root);
    depthStack.push(1);
    int ans = 0;
    while (!nodeStack.isEmpty()) {
        TreeNode node = nodeStack.pop();
        int depth = depthStack.pop();
        ans = Math.max(ans, depth);
        if (node.left != null) {
            nodeStack.push(node.left);
            depthStack.push(depth + 1);           // 两个栈必须同步 push/pop
        }
        if (node.right != null) {
            nodeStack.push(node.right);
            depthStack.push(depth + 1);
        }
    }
    return ans;
}
```

**改写的通用套路**：① 方法参数（往下传的状态）和节点一起打包压栈（两个栈 / `Object[]` / 自定义类）；② 终止条件变成"循环条件 + 更小规模的节点不入栈"；③ 返回值（往上传的结果）需要用后序式回传，中序/后序的迭代版就难在这里。

> ⚠️ **JVM 基础坑**：Java 的 `Stack` 是遗留类（继承 `Vector`，所有方法 `synchronized`），刷题和工程都应改用 `ArrayDeque`（`push/pop/peek` 对应栈，`offer/poll/peek` 对应队列）。仓库里的 `src/listTable/ReverseList2.java` 用了 `Stack`，能跑通但不是最佳实践。
>
> ⚠️ **另一个坑**：显式栈只能解决**栈深度**问题，**不能**解决重复计算问题——那是下一节「记忆化」的工作。

---

## 六、学习路径

| 轮次 | 内容 | 目标 | 建议题量 |
| --- | --- | --- | --- |
| 第 1 天 | [01 递归思维与三要素](01-递归思维与三要素.md) | 能默写"函数语义 + 终止条件 + 合并"三步法 | 6 题 |
| 第 2 天 | [02 分治算法](02-分治算法.md) | 能闭眼写 `merge`，看到 `T(n)` 能秒答复杂度 | 8 题 |
| 第 3 天 | [03 记忆化与递归优化](03-记忆化与递归优化.md) | 一眼看出重叠子问题并写出 memo 模板 | 6 题 |
| 第 4~5 天 | [04 分治应用进阶](04-分治应用进阶.md) | 快速选择、合并 K 链表、归并统计逆序对 | 8 题 |
| 第 6 天 | 本章二刷 | 限时 45 分钟做 3 道 Medium | 3 题 |

**每道题都必须**：先自己写 → 卡住看思路 → 看懂后关掉重写 → 记错题本。递归题额外要求：**能用一句话说出递归函数的语义**。

---

## 七、参考代码索引

本章涉及的所有仓库实现（均已确认存在）：

| 知识点 | 仓库文件 |
| --- | --- |
| 递归三要素（返回值型 / 全局变量型） | [src/tree/MaxDepth.java](../src/tree/MaxDepth.java)、[src/tree/MaxDepthTree.java](../src/tree/MaxDepthTree.java) |
| 二叉树最小深度（单侧为空的边界） | [src/tree/MinDepth.java](../src/tree/MinDepth.java) |
| 递归反转链表 / 递归合并有序链表 | [src/listTable/ReverseList3.java](../src/listTable/ReverseList3.java)、[src/listTable/MergeLists.java](../src/listTable/MergeLists.java)、[src/listTable/MergeList.java](../src/listTable/MergeList.java)、[src/listTable/MergeTwoList.java](../src/listTable/MergeTwoList.java) |
| 递归求 1+2+…+n / 斐波那契 | [src/number/SumN.java](../src/number/SumN.java)、[src/number/Fib.java](../src/number/Fib.java)、[src/number/Fiber.java](../src/number/Fiber.java) |
| 分治：链表归并排序 / 合并 K 个链表 | [src/listTable/SortList.java](../src/listTable/SortList.java)、[src/listTable/MergeKLists.java](../src/listTable/MergeKLists.java)、[src/listTable/MergeKList.java](../src/listTable/MergeKList.java) |
| 分治：快速幂 / 大数乘法 | [src/number/MyPow.java](../src/number/MyPow.java)、[src/number/PowerX.java](../src/number/PowerX.java)、[src/number/Multiply.java](../src/number/Multiply.java) |
| 分治：整数替换 / K 个一组翻转链表 | [src/DFS/IntegerReplacement.java](../src/DFS/IntegerReplacement.java)、[src/listTable/ReverseKGroup.java](../src/listTable/ReverseKGroup.java)、[src/listTable/SwapKNode.java](../src/listTable/SwapKNode.java) |
| 分治：四叉树 / 平衡 BST | [src/tree/ConstructQuadTree.java](../src/tree/ConstructQuadTree.java)、[src/tree/BalanceBST.java](../src/tree/BalanceBST.java) |
| 记忆化：最低票价（`Integer[]` + null 判空） | [src/dp/MinCostTickets.java](../src/dp/MinCostTickets.java) |
| 记忆化：DFS 超时 vs 递推 | [src/DFS/MinPathSum.java](../src/DFS/MinPathSum.java)、[src/dp/MinPathSum.java](../src/dp/MinPathSum.java)、[src/DFS/UniquePath.java](../src/DFS/UniquePath.java) |
| 记忆化：树形递归 | [src/dp/Rob3.java](../src/dp/Rob3.java) |
| 快速选择 / 最大子数组和 / 多数元素 | [src/array/FindKthLargest.java](../src/array/FindKthLargest.java)、[src/dp/MaxSubArray.java](../src/dp/MaxSubArray.java)、[src/array/MajorityElement.java](../src/array/MajorityElement.java)、[src/array/MajorNum.java](../src/array/MajorNum.java) |
| 归并统计：逆序对 / 右侧更小数 | [src/array/ReversePairs.java](../src/array/ReversePairs.java)、[src/array/CountSmaller.java](../src/array/CountSmaller.java) |
| 递归构造所有 BST | [src/tree/GenerateTree.java](../src/tree/GenerateTree.java)、[src/tree/DiffSearchTree2.java](../src/tree/DiffSearchTree2.java)、[src/tree/DiffSearchTree.java](../src/tree/DiffSearchTree.java) |

---

**开始 →** [01 递归思维与三要素](01-递归思维与三要素.md)
