## Java BFS 标准解题模板

### 基础模板（层序遍历）

```java
public void bfs(TreeNode root) {
    if (root == null) return;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int size = queue.size();  // 关键：记录当前层节点数
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            // 处理当前节点 node

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        // 这里可以做「每层」的统计/判断
    }
}
```

### 图/网格 BFS 模板（带 visited）

```java
public int bfs(int[][] grid, int startX, int startY) {
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{startX, startY});
    visited[startX][startY] = true;

    int step = 0;
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] cur = queue.poll();
            // 到达目标判断
            if (isTarget(cur)) return step;

            for (int[] d : dirs) {
                int nx = cur[0] + d[0], ny = cur[1] + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny] || grid[nx][ny] == 0) continue;

                visited[nx][ny] = true;  // 入队时标记
                queue.offer(new int[]{nx, ny});
            }
        }
        step++;
    }
    return -1;
}
```

### 关键要点

| 要点 | 说明 |
|------|------|
| `queue.size()` 提前保存 | 用于区分「当前层」和「下一层」，求最短步数必须 |
| visited 标记时机 | **入队时**标记，不是出队时，否则会重复入队 |
| 多源 BFS | 初始把所有源点入队，天然求「到最近源点距离」 |
| 0-1 BFS | 用 `Deque`，边权 0 头插，边权 1 尾插 |
| 双向 BFS | 起点终点同时扩展，判断两 visited 相交，降低指数 |

---

## BFS vs DFS 对比

### 核心差异

| 维度 | BFS | DFS |
|------|-----|-----|
| 数据结构 | `Queue`（队列） | `Stack` / 递归调用栈 |
| 搜索方式 | 按层扩展，辐射状 | 一条路走到底，再回溯 |
| 空间复杂度 | O(宽度)，最坏 O(N) | O(深度)，最坏 O(N) |
| 能否求最短路径 | **能**（无权图/边权相等） | 不能（除非穷举所有路径） |
| 实现方式 | 迭代 | 递归 / 显式栈 |

### 应用场景对比

| 场景 | 推荐 | 原因 |
|------|------|------|
| 无权图最短路径 | BFS | 按层扩展，首次到达即最短 |
| 网格最短路（走迷宫） | BFS | 同上 |
| 层序遍历 / 每层统计 | BFS | 天然分层 |
| 拓扑排序（Kahn） | BFS | 入度为零入队 |
| 路径枚举 / 回溯 | DFS | 天然维护路径 |
| 连通分量 / 岛屿数量 | 都行 | DFS 代码更短 |
| 排列组合 / 子集 | DFS | 回溯框架 |
| 树的前中后序遍历 | DFS | 递归最自然 |
| 判断环（有向图） | DFS | 三色标记 |
| 拓扑排序（DFS） | DFS | 后序逆序 |

### 代码风格对比（同一题：二叉树层序 vs 前序）

```java
// BFS：层序
void bfs(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode n = q.poll();
        visit(n);
        if (n.left != null) q.offer(n.left);
        if (n.right != null) q.offer(n.right);
    }
}

// DFS：前序
void dfs(TreeNode root) {
    if (root == null) return;
    visit(root);
    dfs(root.left);
    dfs(root.right);
}
```

### 选型决策

- **求「最短 / 最少步数」** → BFS
- **求「所有方案 / 路径」** → DFS + 回溯
- **数据规模大、深度深** → 优先 BFS（避免栈溢出），或 DFS 改迭代
- **需要按层处理** → BFS
- **需要剪枝、状态复杂** → DFS（回溯中剪枝更自然）

### 一句话总结

> **BFS 像水波扩散**，一层层向外，适合「最短、最少、最近」；**DFS 像走迷宫**，一条路走到黑，适合「所有方案、路径、回溯」。两者时间通常都是 O(N)，区别主要在空间形态和适用问题上。