# 10 树

> **这一章学什么**：树是**递归思维的第一个真正战场**。数组题的循环能让你"看一步走一步"，但树的题目必须先在脑子里建立"我在某个节点上，我的左右子树已经/将会给我什么"的模型。本章从**前序位置与后序位置的区别**这一个概念出发，一路打通遍历、属性、BST、路径、构造、序列化、Trie 与堆。
>
> **为什么重要**：树的题在面试里出现的频率仅次于数组。更关键的是——**树是后面所有"分治 / 树形 DP / 回溯 / 图搜索"的原型**。树想明白了，图、DFS、递归回溯都会跟着顺。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 树的遍历与递归思维](01-树的遍历与递归思维.md) | 递归三部曲、前/中/后序位置、迭代写法、层序遍历、Morris | ★★☆☆☆ | ★★★★★ |
| [02 二叉树的属性与递归](02-二叉树的属性与递归.md) | 自顶向下 vs 自底向上两大范式、深度、平衡、直径、宽度 | ★★★☆☆ | ★★★★★ |
| [03 二叉搜索树](03-二叉搜索树.md) | 中序有序、验证、增删改查、LCA、恢复 BST | ★★★☆☆ | ★★★★★ |
| [04 路径与深度问题](04-路径与深度问题.md) | 根到叶路径、任意节点间路径、路径和 III 与前缀和 | ★★★★☆ | ★★★★☆ |
| [05 构造与序列化](05-构造与序列化.md) | 前中后序重建、最大二叉树、序列化、验证前序序列化 | ★★★★☆ | ★★★★☆ |
| [06 树的进阶专题](06-树的进阶专题.md) | Trie 前缀树、手写堆、对顶堆、监控二叉树 | ★★★★☆ | ★★★☆☆ |

---

## 一、为什么学这一章

1. **树的题最能暴露"递归有没有真懂"**。很多人写递归靠猜：`return f(root.left) + f(root.right)` 蒙对了就过，蒙错了就卡死。本章会把递归拆成**明确的三个位置**（进入节点、处理完左子树、处理完右子树），让每一步都有依据。
2. **树的题变形多、套路稳定**。700+ 道树题里，90% 都能塞进"前序带参数下去"或"后序把结果拿上来"这两个模板，剩下的只是把 `int` 换成 `List`、把深度换成和。
3. **BST 是一类独立的送分题**。只要看到 BST，第一反应就是"**中序遍历有序**"，很多题从 `O(n)` 直接降到 `O(log n)`。
4. **Trie 和堆是工程岗的加分项**。自动补全、敏感词过滤、TopK、数据流中位数，都是这两个结构的主场。

---

## 二、语言基本功

> 这一节的每一段代码都必须是**闭着眼能默写**的程度。树的题写不出 `TreeNode`，后面全是空谈。

### 2.1 TreeNode 标准定义

```java
// LeetCode 题目里已经给好这个类，不用自己写，但必须能默写
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

> ⚠️ **仓库里的坑**：本仓库早期的文件（如 [src/tree/BuildTree.java](../src/tree/BuildTree.java)、[src/tree/BinaryTreeDiameter.java](../src/tree/BinaryTreeDiameter.java)）把字段写成了 `value` 而不是 `val`，还有的 `TreeNode` 是**非 static 的内部类**（如 [src/tree/MaxDepth.java](../src/tree/MaxDepth.java)）。自己练习没问题，但**贴到 LeetCode 之前一定要统一成 `val`**，否则编译不过。

### 2.2 三种遍历的递归模板

```java
// 前序：根 → 左 → 右
void preorder(TreeNode root, List<Integer> res) {
    if (root == null) return;
    res.add(root.val);          // ① 前序位置
    preorder(root.left, res);
    preorder(root.right, res);
}

// 中序：左 → 根 → 右
void inorder(TreeNode root, List<Integer> res) {
    if (root == null) return;
    inorder(root.left, res);
    res.add(root.val);          // ② 中序位置
    inorder(root.right, res);
}

// 后序：左 → 右 → 根
void postorder(TreeNode root, List<Integer> res) {
    if (root == null) return;
    postorder(root.left, res);
    postorder(root.right, res);
    res.add(root.val);          // ③ 后序位置
}
```

**三行代码换个位置，就是三种遍历。** 记住这一点，比背"前序根左右"有用得多。

### 2.3 前序位置 / 中序位置 / 后序位置（本节最重要的概念）

```java
void traverse(TreeNode root) {
    if (root == null) return;
    // ===== 前序位置：刚进入这个节点，左右子树还一无所知 =====
    traverse(root.left);
    // ===== 中序位置：左子树已经全部处理完，右子树还没碰 =====
    traverse(root.right);
    // ===== 后序位置：左右子树都处理完了，它们的结果都拿到了 =====
}
```

| 位置 | 时机 | 能拿到什么 | 典型用途 |
| --- | --- | --- | --- |
| **前序位置** | 进入节点时 | 只能拿到**从祖先传下来的参数** | 传深度、传路径、传上下界、传剩余和 |
| 中序位置 | 左子树处理完 | 左子树的结果 + 父辈参数 | BST 从小到大访问（第 k 小、验证 BST） |
| **后序位置** | 离开节点时 | **左右子树的返回值** | 求高度、求子树和、汇总答案 |

> **一句话抓住本质**：**前序是"我还没做事，先领任务"；后序是"我把活干完了，交结果"。**
> 所以只有**后序位置**能拿到子树的计算结果——这就是为什么"求高度""求子树和""求直径"这类题**必须写成后序**。

### 2.4 层序遍历用 Queue 的模板

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;            // 空树必须提前返回
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();             // ★ 关键：先固定本层元素个数
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {     // 只处理本层，新入队的孩子留到下一层
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        res.add(level);
    }
    return res;
}
```

`int size = queue.size()` 这一行是**按层分组的唯一关键**：不固定 size，队列里会混着两层甚至三层的节点。

### 2.5 N 叉树 Node 定义

```java
class Node {
    public int val;
    public List<Node> children;      // 孩子装在 List 里，数量和"是不是叶子"都要用 size() 判断

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
```

N 叉树只是把"两个固定孩子"换成"一个 List"，**递归的骨架完全不变**：

```java
void traverse(Node root) {
    if (root == null) return;
    // 前序位置
    for (Node child : root.children) traverse(child);
    // 后序位置
}
```

### 2.6 Java 语法坑速查

```java
// 坑 1：Stack 底层是 Vector，所有方法都加 synchronized，单线程刷题效率低
Stack<TreeNode> s1 = new Stack<>();          // 不推荐
Deque<TreeNode> s2 = new ArrayDeque<>();     // 推荐：push / pop / peek / isEmpty
Deque<TreeNode> q  = new ArrayDeque<>();     // 推荐：offer / poll / peek

// 坑 2：ArrayDeque 不允许存 null → 层序分组不要用"塞 null 当分隔符"（数组队列可以，ArrayDeque 不行）
Queue<TreeNode> q2 = new LinkedList<>();     // 要存 null 哨兵时用 LinkedList

// 坑 3：Integer 比较必须用 equals 或先拆箱，== 比的是引用
Integer a = 200, b = 200;
System.out.println(a == b);                  // false！(-128~127 之外不缓存)
System.out.println(a.equals(b));             // true

// 坑 4：可变对象放进 List 要新建副本，否则后面被改掉
res.add(level);                              // 危险：level 之后还会被 clear()
res.add(new ArrayList<>(level));             // 正确
```

### 2.7 复杂度直觉：看到数据范围就该反应过来的事

| 树的规模 | 你可以写 | 你不能写 |
| --- | --- | --- |
| `n ≤ 100` | 每个节点都重算一遍 `O(n^2)` | — |
| `n ≤ 10^4` | `O(n log n)`（排序 / 堆） | `O(n^2)` |
| `n ≤ 10^5` | `O(n)`、`O(n log n)` | `O(n^2)` |
| 链式树（`n = 10^5` 全是左孩子） | 递归深度 `O(n)`，Java 默认栈**可能溢出**，要改迭代 | 盲目递归 |

---

## 三、学习路径

```
第 1 天  01 遍历与递归思维    → 默写前中后序递归 + 迭代 + 层序，做 6 道遍历题
第 2 天  02 属性与递归        → 建立"自顶向下 / 自底向上"的判断反射，做 6 道属性题
第 3 天  03 二叉搜索树        → 把"中序有序"刻进本能，做 6 道 BST 题
第 4 天  04 路径与深度        → 区分"根到叶"与"任意节点间"，做 5 道路径题
第 5 天  05 构造与序列化      → 找根 + 划分区间，做 5 道构造题 + 2 道序列化题
第 6 天  06 进阶专题          → Trie + 堆，各做 3 道
第 7 天  复习：不看答案默写 01 的层序模板 + 03 的验证 BST 模板 + 04 的最大路径和模板
```

**每道题都必须能回答**：这道题我是在**前序位置**做事，还是在**后序位置**做事？答不上来就是没做透。

---

## 四、参考代码索引

本章涉及的所有仓库实现（全部来自 `src/tree/` 以及少量 `src/stack/`、`src/array/`、`src/listTable/`、`src/hashTable/`、`src/queue/`）：

| 知识点 | 仓库文件 |
| --- | --- |
| 遍历（二叉树 + N 叉树，递归 + 迭代） | [src/tree/PreOrder.java](../src/tree/PreOrder.java)、[src/tree/InOrder.java](../src/tree/InOrder.java)、[src/tree/PostOrder.java](../src/tree/PostOrder.java)、[src/stack/PreOrder.java](../src/stack/PreOrder.java)、[src/stack/Inorder.java](../src/stack/Inorder.java)、[src/stack/PostOrder.java](../src/stack/PostOrder.java)、[src/tree/PreOrderNTree.java](../src/tree/PreOrderNTree.java)、[src/tree/PostOrderNTree.java](../src/tree/PostOrderNTree.java)、[src/tree/LevelOrderNTree.java](../src/tree/LevelOrderNTree.java)、[src/tree/MaxDepthOfNTree.java](../src/tree/MaxDepthOfNTree.java) |
| 层序遍历族 | [src/tree/LevelOrder.java](../src/tree/LevelOrder.java)、[src/tree/LevelOrder2.java](../src/tree/LevelOrder2.java)、[src/tree/ZagLevelOrder.java](../src/tree/ZagLevelOrder.java)、[src/tree/LevelOrderPrint.java](../src/tree/LevelOrderPrint.java)、[src/stack/ZigZagLevelOrder.java](../src/stack/ZigZagLevelOrder.java)、[src/tree/LevelAvg.java](../src/tree/LevelAvg.java)、[src/tree/LargestValue.java](../src/tree/LargestValue.java)、[src/tree/RightSideView.java](../src/tree/RightSideView.java)、[src/tree/BottomLeftValue.java](../src/tree/BottomLeftValue.java)、[src/tree/CousinsNode.java](../src/tree/CousinsNode.java)、[src/tree/AddRow.java](../src/tree/AddRow.java) |
| N 叉树 | [src/tree/PreOrderNTree.java](../src/tree/PreOrderNTree.java)、[src/tree/PostOrderNTree.java](../src/tree/PostOrderNTree.java)、[src/tree/LevelOrderNTree.java](../src/tree/LevelOrderNTree.java)、[src/tree/MaxDepthOfNTree.java](../src/tree/MaxDepthOfNTree.java) |
| 深度 / 平衡 / 对称 / 修改合并 | [src/tree/MaxDepth.java](../src/tree/MaxDepth.java)、[src/tree/MaxDepthTree.java](../src/tree/MaxDepthTree.java)、[src/tree/MinDepth.java](../src/tree/MinDepth.java)、[src/tree/IsBalancedTree.java](../src/tree/IsBalancedTree.java)、[src/tree/IsSymmetric.java](../src/tree/IsSymmetric.java)、[src/tree/SymmetricTree.java](../src/tree/SymmetricTree.java)、[src/tree/SameTree.java](../src/tree/SameTree.java)、[src/tree/FlipEquiv.java](../src/tree/FlipEquiv.java)、[src/tree/InvertTree.java](../src/tree/InvertTree.java)、[src/tree/MirrorTree.java](../src/tree/MirrorTree.java)、[src/tree/MergeTree.java](../src/tree/MergeTree.java)、[src/tree/TreeTilt.java](../src/tree/TreeTilt.java)、[src/tree/LeftLeavesSum.java](../src/tree/LeftLeavesSum.java)、[src/tree/CountNodes.java](../src/tree/CountNodes.java)、[src/tree/NumOfTree.java](../src/tree/NumOfTree.java)、[src/tree/UnivalTree.java](../src/tree/UnivalTree.java)、[src/tree/PruneTree.java](../src/tree/PruneTree.java) |
| 直径 / 宽度 / 最深子树 / 完全二叉树 | [src/tree/BinaryTreeDiameter.java](../src/tree/BinaryTreeDiameter.java)、[src/tree/BinaryTreeWidth.java](../src/tree/BinaryTreeWidth.java)、[src/tree/SubTreeDeepest.java](../src/tree/SubTreeDeepest.java)、[src/tree/ValidCompleteTree.java](../src/tree/ValidCompleteTree.java) |
| 二叉树 LCA / 子树 | [src/tree/LowestCommonAncestor2.java](../src/tree/LowestCommonAncestor2.java)、[src/tree/LowestCommonAncestor.java](../src/tree/LowestCommonAncestor.java)、[src/tree/SubTree.java](../src/tree/SubTree.java)、[src/tree/SubStructure.java](../src/tree/SubStructure.java)、[src/tree/DistanceK.java](../src/tree/DistanceK.java) |
| BST 基础操作 + 中序性质应用 | [src/tree/ValidBST.java](../src/tree/ValidBST.java)、[src/tree/isValidBST.java](../src/tree/isValidBST.java)、[src/tree/SearchInBST.java](../src/tree/SearchInBST.java)、[src/tree/InsertIntoBST.java](../src/tree/InsertIntoBST.java)、[src/tree/DeleteBSTNode.java](../src/tree/DeleteBSTNode.java)、[src/tree/DeleteNodeInBST.java](../src/tree/DeleteNodeInBST.java)、[src/tree/TrimBST.java](../src/tree/TrimBST.java)、[src/tree/RangeSumBST.java](../src/tree/RangeSumBST.java)、[src/tree/RangeSumBST2.java](../src/tree/RangeSumBST2.java)、[src/tree/KthElement.java](../src/tree/KthElement.java)、[src/tree/KthBST.java](../src/tree/KthBST.java)、[src/tree/ModeInBST.java](../src/tree/ModeInBST.java)、[src/tree/MinDiffInBST.java](../src/tree/MinDiffInBST.java)、[src/tree/ConvertBST.java](../src/tree/ConvertBST.java)、[src/tree/BstToGst.java](../src/tree/BstToGst.java)、[src/tree/RecoverTree.java](../src/tree/RecoverTree.java)、[src/tree/BSTIterator.java](../src/tree/BSTIterator.java)、[src/tree/SecondMinValue.java](../src/tree/SecondMinValue.java) |
| BST 构造 / 转换 / LCA | [src/tree/ArrayToBST.java](../src/tree/ArrayToBST.java)、[src/tree/ListToBST.java](../src/tree/ListToBST.java)、[src/tree/BalanceBST.java](../src/tree/BalanceBST.java)、[src/tree/IncreasingBST.java](../src/tree/IncreasingBST.java)、[src/tree/TreeByDoublyList.java](../src/tree/TreeByDoublyList.java)、[src/tree/BstFromPreOrder.java](../src/tree/BstFromPreOrder.java)、[src/tree/LowestAncestor.java](../src/tree/LowestAncestor.java)、[src/tree/CommonAncestor.java](../src/tree/CommonAncestor.java)、[src/tree/TargetBST.java](../src/tree/TargetBST.java)、[src/tree/DiffSearchTree.java](../src/tree/DiffSearchTree.java)、[src/tree/DiffSearchTree2.java](../src/tree/DiffSearchTree2.java)、[src/tree/GenerateTree.java](../src/tree/GenerateTree.java) |
| 路径问题 | [src/tree/PathSum.java](../src/tree/PathSum.java)、[src/tree/PathSum2.java](../src/tree/PathSum2.java)、[src/tree/PathSum3.java](../src/tree/PathSum3.java)、[src/tree/TreePathSum.java](../src/tree/TreePathSum.java)、[src/tree/AllPath.java](../src/tree/AllPath.java)、[src/tree/SumRootToLeaf.java](../src/tree/SumRootToLeaf.java)、[src/tree/SumNum.java](../src/tree/SumNum.java)、[src/tree/SmallestFromLeaf.java](../src/tree/SmallestFromLeaf.java)、[src/tree/MaxAncestorDiff.java](../src/tree/MaxAncestorDiff.java)、[src/tree/LongestUnivaluePath.java](../src/tree/LongestUnivaluePath.java)、[src/tree/MaxPathSum.java](../src/tree/MaxPathSum.java) |
| 构造 / 序列化 | [src/tree/RebuildTree.java](../src/tree/RebuildTree.java)、[src/tree/BuildTree.java](../src/tree/BuildTree.java)、[src/tree/BuildTreeInAndPost.java](../src/tree/BuildTreeInAndPost.java)、[src/tree/ConstructTreeFromPrePost.java](../src/tree/ConstructTreeFromPrePost.java)、[src/tree/MaxBinaryTree.java](../src/tree/MaxBinaryTree.java)、[src/tree/InsertIntoMaxTree.java](../src/tree/InsertIntoMaxTree.java)、[src/tree/ConstructQuadTree.java](../src/tree/ConstructQuadTree.java)、[src/tree/QuadTreeIntersect.java](../src/tree/QuadTreeIntersect.java)、[src/tree/RecoverFromPreorder.java](../src/tree/RecoverFromPreorder.java)、[src/tree/CodeC.java](../src/tree/CodeC.java)、[src/tree/SerializeTree.java](../src/tree/SerializeTree.java)、[src/tree/SerializeTree2.java](../src/tree/SerializeTree2.java)、[src/tree/SerializeBST.java](../src/tree/SerializeBST.java)、[src/tree/ValidTreeSerialization.java](../src/tree/ValidTreeSerialization.java)、[src/tree/DuplicateSubTree.java](../src/tree/DuplicateSubTree.java)、[src/tree/TreeToStr.java](../src/tree/TreeToStr.java) |
| Trie 与堆（优先队列） | [src/tree/Trie.java](../src/tree/Trie.java)、[src/tree/Trie2.java](../src/tree/Trie2.java)、[src/tree/WordDictionary.java](../src/tree/WordDictionary.java)、[src/hashTable/MapSum.java](../src/hashTable/MapSum.java)、[src/hashTable/ReplaceWord.java](../src/hashTable/ReplaceWord.java)、[src/queue/MyPriorityQueue.java](../src/queue/MyPriorityQueue.java)、[src/tree/BinaryHeap.java](../src/tree/BinaryHeap.java)、[src/array/KthElement.java](../src/array/KthElement.java)、[src/array/KthLargest2.java](../src/array/KthLargest2.java)、[src/array/LeastNumbers.java](../src/array/LeastNumbers.java)、[src/array/TopKFrequent.java](../src/array/TopKFrequent.java)、[src/array/FindKthLargest.java](../src/array/FindKthLargest.java)、[src/listTable/MedianFinder.java](../src/listTable/MedianFinder.java)、[src/listTable/MedianFinder2.java](../src/listTable/MedianFinder2.java)、[src/listTable/MergeKLists.java](../src/listTable/MergeKLists.java)、[src/listTable/MergeKList.java](../src/listTable/MergeKList.java)、[src/tree/CBTInserter.java](../src/tree/CBTInserter.java)、[src/tree/MinCameraCover.java](../src/tree/MinCameraCover.java) |

---

## 五、仓库实现勘误（读代码时务必注意）

本仓库是**个人刷题记录**，不是教科书。下面这些文件**思路对但代码有 bug 或写法不佳**，教程正文里会给出**推荐版本**，请勿直接照抄仓库版：

| 文件 | 问题 | 教程里的推荐做法 |
| --- | --- | --- |
| [src/tree/IsBalancedTree.java](../src/tree/IsBalancedTree.java) | `isBalanced` 里写的是 `> 2`，高度差为 2 时误判为平衡；且每个节点重算高度，`O(n^2)` | 见 [02 二叉树的属性与递归](02-二叉树的属性与递归.md)，后序一遍扫完 `O(n)` |
| [src/tree/TargetBST.java](../src/tree/TargetBST.java) | 只判断 `set.contains(k - i)`，`k = 2i` 时会用同一个节点两次 | 见 [03 二叉搜索树](03-二叉搜索树.md) 的 `HashSet` 边遍历边查写法 |
| [src/tree/LevelAvg.java](../src/tree/LevelAvg.java) | `levelNum = nextNum` 写在 `nextNum = 0` 之后，第二层起平均数恒为 0 | 见 [01 树的遍历与递归思维](01-树的遍历与递归思维.md) 的 `size` 分层模板 |
| [src/tree/MaxBinaryTree.java](../src/tree/MaxBinaryTree.java) | 右半段长度写成 `nums.length - maxPos - 2`，最大值在末尾时会 `NegativeArraySizeException`，否则漏元素 | 用下标区间 `[lo, hi)` 递归，不复制数组 |
| [src/stack/PostOrder.java](../src/stack/PostOrder.java) | 单栈版 `while (root != null \|\| stack.isEmpty())` 少了一个 `!`，会 `EmptyStackException` | 用 `postorderTraversal2`（逆序输出）或双栈法 |
| [src/tree/SerializeTree.java](../src/tree/SerializeTree.java) | 用 `-100` 当空节点哨兵，值域冲突；靠 `val != 0` 计数，值为 0 的节点会算错 | 见 [05 构造与序列化](05-构造与序列化.md)，用字符串 `null` |
| [src/tree/Flatten2.java](../src/tree/Flatten2.java) | `preNode` 从未赋值，方法等于空操作 | 用 [src/tree/Flatten.java](../src/tree/Flatten.java) 的前序收集 + 重连 |
| [src/tree/SubTree.java](../src/tree/SubTree.java) | `dfs` 没有 `null` 终止判断，未命中时会空指针 | 改为 `if (s == null) return false;` 再做 `sameTree` |
| [src/tree/ConstructQuadTree.java](../src/tree/ConstructQuadTree.java) | 子区间起点写 `(x0 + x1) / 2 + 1`，跳过了中间一行/一列 | 用 `[x0, mx) [mx, x1)` 左闭右开划分 |

---

**开始 →** [01 树的遍历与递归思维](01-树的遍历与递归思维.md)
