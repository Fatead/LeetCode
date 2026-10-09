# 11 图与并查集

> **这一章学什么**：图是最通用的数据结构——树是无环连通图、链表是退化的图。本章讲图的两种遍历（DFS/BFS）、拓扑排序、最短路，以及处理"连通性"的神器**并查集**。
> **为什么重要**：图论题看似高级，其实套路固定。岛屿类、拓扑排序、并查集是面试三大高频，掌握模板后 Medium 稳过。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 图的表示与遍历](01-图的表示与遍历.md) | 邻接表/矩阵、DFS/BFS 模板、岛屿问题、克隆图 | ★★★☆☆ | ★★★★☆ |
| [02 拓扑排序与最短路](02-拓扑排序与最短路.md) | 课程表、拓扑排序（Kahn/DFS）、Dijkstra、二分图 | ★★★★☆ | ★★★★☆ |
| [03 并查集](03-并查集.md) | 并查集模板、路径压缩、按秩合并、冗余连接、账户合并 | ★★★★☆ | ★★★★★ |

---

## 一、图的两种"物理表示"

| 表示法 | 结构 | 适用 | 查"i→j 有没有边" | 查"i 的所有邻居" |
| --- | --- | --- | --- | --- |
| **邻接矩阵** | `boolean[n][n]` | 稠密图、点少 | `O(1)` | `O(n)` |
| **邻接表** | `List<List<Integer>>` | 稀疏图（LeetCode 主流） | `O(度数)` | `O(度数)` |

**LeetCode 的图题 99% 用邻接表**，因为大部分图是稀疏的（边远少于 `n²`）。建图模板：

```java
List<List<Integer>> graph = new ArrayList<>();
for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
for (int[] e : edges) {
    graph.get(e[0]).add(e[1]);
    graph.get(e[1]).add(e[0]);   // 无向图加两条；有向图只加一条
}
```

---

## 二、图论三大工具的选择

| 要解决的问题 | 用什么 | 为什么 |
| --- | --- | --- |
| 遍历所有节点 / 连通块个数 | DFS 或 BFS | 两者都行，看习惯 |
| **最短路径（无权图/边权为 1）** | **BFS** | BFS 按层扩展，第一次到达即最短 |
| 最短路径（带正权） | Dijkstra | 优先队列每次扩展最近的 |
| **有向图是否有环 / 排课顺序** | **拓扑排序** | 入度为 0 的点先输出 |
| **动态合并 + 查询连通性** | **并查集** | 近 `O(1)` 判断两点是否连通 |
| 判断无向图是否有环 | 并查集 | 加边时发现两端已连通 → 有环 |

> **DFS 还是 BFS？** 求"是否存在/遍历全部"用 DFS（代码短）；求"最短步数/最少操作"用 BFS（按层保证最短）。**这是选择遍历方式的黄金准则。**

---

## 三、DFS 与 BFS 的通用模板

### 3.1 DFS（递归 + visited）

```java
boolean[] visited;
void dfs(int node, List<List<Integer>> graph) {
    visited[node] = true;              // 标记已访问（防止死循环）
    for (int next : graph.get(node))
        if (!visited[next]) dfs(next, graph);
}
```

**图的 DFS 和树的 DFS 唯一区别就是 `visited` 数组**——因为图有环，不标记会无限递归。

### 3.2 BFS（队列 + visited）

```java
void bfs(int start, List<List<Integer>> graph) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[graph.size()];
    queue.offer(start);
    visited[start] = true;             // ★ 入队时就标记，不是出队时
    while (!queue.isEmpty()) {
        int node = queue.poll();
        for (int next : graph.get(node))
            if (!visited[next]) {
                visited[next] = true;  // 防止同一节点被多次入队
                queue.offer(next);
            }
    }
}
```

**最高频 bug**：在"出队时"才标记 visited。这会导致同一节点被重复入队，不仅慢还可能出错——**必须在"入队时"立刻标记**。

---

## 四、本章学习路径

```
第 1 天  01 图的表示与遍历   → 建图模板 + DFS/BFS 模板，做岛屿数量
第 2 天  01 岛屿系列         → 岛屿最大面积、被围绕的区域、腐烂的橘子（多源 BFS）
第 3 天  02 拓扑排序         → 课程表 I/II，DFS 判环 + Kahn 入度法
第 4 天  02 最短路/二分图    → Dijkstra 模板、判断二分图
第 5 天  03 并查集           → 背熟并查集模板（路径压缩 + 按秩合并）
第 6 天  03 并查集应用       → 冗余连接、账户合并、最长连续序列
第 7 天  本章综合           → 限时做 课程表 II + 岛屿数量 + 冗余连接
```

---

## 五、参考代码索引

本章实现分布在 `src/graph`、`src/UnionFind`、`src/BFS`、`src/DFS`：

| 知识点 | 仓库文件 |
| --- | --- |
| 岛屿数量（并查集版） | [src/UnionFind/NumIslands.java](../../src/UnionFind/NumIslands.java) |
| 克隆图 | [src/graph/CloneGraph.java](../../src/graph/CloneGraph.java) |
| 所有可能路径（DFS） | [src/graph/AllPathSourceTarget.java](../../src/graph/AllPathSourceTarget.java) |
| 课程表（判环） | [src/graph/CourseGraph.java](../../src/graph/CourseGraph.java) |
| 课程表 II（拓扑序） | [src/graph/CourseGraph2.java](../../src/graph/CourseGraph2.java) |
| 图中找环 | [src/graph/GraphFindCycle.java](../../src/graph/GraphFindCycle.java) |
| 判断二分图 | [src/graph/BiPartition.java](../../src/graph/BiPartition.java) |
| 冗余连接（并查集/DFS） | [src/graph/RedundantConnection.java](../../src/graph/RedundantConnection.java)、[src/graph/RedundantConnectionUF.java](../../src/graph/RedundantConnectionUF.java)、[src/UnionFind/FindRedundantConnection.java](../../src/UnionFind/FindRedundantConnection.java) |
| 冗余连接 II（有向图） | [src/UnionFind/FindRedundantDirectedConnection.java](../../src/UnionFind/FindRedundantDirectedConnection.java) |
| 并查集模板类 | [src/UnionFind/UnionFind.java](../../src/UnionFind/UnionFind.java) |
| 省份数量（朋友圈） | [src/UnionFind/CircleNum.java](../../src/UnionFind/CircleNum.java) |
| 账户合并 | [src/UnionFind/MergeAccount.java](../../src/UnionFind/MergeAccount.java)、[src/UnionFind/MergeAccount2.java](../../src/UnionFind/MergeAccount2.java) |
| 最长连续序列 | [src/UnionFind/LongestConsecutive.java](../../src/UnionFind/LongestConsecutive.java) |
| 使网络互连 | [src/UnionFind/MakeConnected.java](../../src/UnionFind/MakeConnected.java) |
| 连接所有点的最小费用（最小生成树） | [src/UnionFind/MinCostConnectPoints.java](../../src/UnionFind/MinCostConnectPoints.java) |
| 移除石子 | [src/UnionFind/RemoveStones.java](../../src/UnionFind/RemoveStones.java) |
| 交换字符串中的元素 | [src/UnionFind/SmallestStringWithSwap.java](../../src/UnionFind/SmallestStringWithSwap.java) |
| 被围绕的区域 | [src/UnionFind/Solve.java](../../src/UnionFind/Solve.java) |
| 腐烂的橘子（多源 BFS） | [src/BFS/OrangesRotting.java](../../src/BFS/OrangesRotting.java) |
| 打开转盘锁（BFS） | [src/BFS/OpenLock.java](../../src/BFS/OpenLock.java) |
| 机器人运动范围（BFS） | [src/BFS/MovingCount.java](../../src/BFS/MovingCount.java) |
| 钥匙和房间（DFS） | [src/BFS/VisitRooms.java](../../src/BFS/VisitRooms.java) |

---

**开始 →** [01 图的表示与遍历](01-图的表示与遍历.md)
