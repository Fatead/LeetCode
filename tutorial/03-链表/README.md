# 03 链表

> **这一章学什么**：链表是"指针操作"的练兵场。它没有下标、不能随机访问，所有操作都靠**改 `next` 指针**完成。链表题的难不在算法，而在**边界**——头结点会不会被删、空链表怎么办、断链前有没有先存好下一个节点。
> **为什么重要**：链表是面试**最高频的手写题类型之一**。它代码量小、能在白板上写完，又能精准考察你对指针的掌控力。反转链表、合并链表、快慢指针判环，几乎是每个面试官的必备武器。

---

## 本章地图

| 小节 | 核心内容 | 难度 | 面试频率 |
| --- | --- | --- | --- |
| [01 链表基础与哑结点](01-链表基础与哑结点.md) | 链表结构、哑结点（dummy）技巧、增删改查、设计链表 | ★★☆☆☆ | ★★★★☆ |
| [02 反转与合并](02-反转与合并.md) | 反转链表、区间反转、K 个一组、合并两个/K 个有序链表 | ★★★☆☆ | ★★★★★ |
| [03 快慢指针](03-快慢指针.md) | 找中点、判环、找环入口、倒数第 k 个、回文链表 | ★★★☆☆ | ★★★★★ |
| [04 综合与进阶](04-综合与进阶.md) | 两数相加、重排链表、分隔链表、复制随机指针、链表排序 | ★★★★☆ | ★★★★☆ |

---

## 一、为什么学这一章

链表是一种**用指针串起来的线性结构**，它和数组构成了"线性表"的两种物理实现：

| 对比项 | 数组 | 链表 |
| --- | --- | --- |
| 随机访问第 k 个 | `O(1)` 直接下标 | `O(n)` 从头走 k 步 |
| 头部插入/删除 | `O(n)` 要搬移 | `O(1)` 改指针即可 |
| 中间插入/删除 | `O(n)` 要搬移 | `O(1)`（已定位到位置） |
| 内存 | 连续，缓存友好 | 分散，每个节点多一个指针开销 |
| 是否需要预分配大小 | 需要 | 不需要，用多少建多少 |

**面试里链表的三大价值**：

1. **考指针掌控力**。反转一个链表看起来简单，但 `pre`、`cur`、`next` 三个指针的推进顺序一错就断链。这是最能暴露"基本功是否扎实"的题。
2. **考边界意识**。删除头结点、空链表、单节点、删到只剩一个——链表题的 bug 几乎全在边界。**哑结点（dummy）就是专门用来消灭边界特判的。**
3. **代码量可控**。链表题通常 20 行以内，适合白板手写，所以面试官特别爱出。

---

## 二、链表的节点定义（必须背到肌肉记忆）

LeetCode 的单链表节点定义是固定的：

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```

双链表（设计题用）多一个 `prev`：

```java
class ListNode {
    int val;
    ListNode prev;
    ListNode next;
    ListNode(int val) { this.val = val; }
}
```

> 仓库里 [src/listTable/MyLinkedList.java](../../src/listTable/MyLinkedList.java) 的双链表设计题，就是用「`head`/`tail` 两个哨兵 + 双向指针」实现的，是双链表的标准模板。

---

## 三、链表题的三大通用武器

这三个武器覆盖了本章 90% 的题，务必刻进脑子：

### 武器 1：哑结点（dummy / 哨兵节点）

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// ... 所有操作都从 dummy 开始，最后 return dummy.next ...
```

**什么时候必须用哑结点？** 只要**头结点可能被删除或改变**，就用哑结点。它让"删头结点"和"删中间结点"变成同一套代码，消灭了"要不要特判头结点"这个最常见的 bug 来源。

> 典型题：删除倒数第 N 个结点、删除值为 val 的所有结点、合并两个有序链表——全都靠哑结点统一逻辑。

### 武器 2：三指针反转

```java
ListNode pre = null, cur = head;
while (cur != null) {
    ListNode next = cur.next;   // ① 先存住下一个（否则断链后找不到）
    cur.next = pre;             // ② 翻转当前指针
    pre = cur;                  // ③ pre 前进
    cur = next;                 // ④ cur 前进
}
return pre;                     // pre 最终指向原链表最后一个 = 新头
```

**顺序铁律**：永远**先存 `next`，再改指针**。顺序一乱，链表就断在半路。

> 参考实现：[src/listTable/ReverseList2.java](../../src/listTable/ReverseList2.java)、[src/listTable/ReverList.java](../../src/listTable/ReverList.java)

### 武器 3：快慢指针

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;        // 慢指针走 1 步
    fast = fast.next.next;   // 快指针走 2 步
}
// 循环结束 slow 指向中点（偶数长度时指向后中点）
```

快慢指针解决三类问题：**找中点**（快 2 慢 1）、**判环**（相遇即有环）、**倒数第 k 个**（快指针先走 k 步）。

> 参考实现：找中点 [src/listTable/MiddleNode.java](../../src/listTable/MiddleNode.java)；判环 [src/listTable/CircleInList.java](../../src/listTable/CircleInList.java)；倒数第 k 个 [src/listTable/getKthFromEnd.java](../../src/listTable/getKthFromEnd.java)

---

## 四、链表题的"画图法"（本章最重要的学习习惯）

**链表题一定要画图。** 在纸上画出几个方框和箭头，手动模拟指针的移动，是写对链表代码的唯一捷径。

以反转链表为例，画出每一步：

```
初始:    pre=null   cur→[1]→[2]→[3]→null

第1轮:   next=[2]
         null←[1]   cur=[2]→[3]→null    pre=[1]

第2轮:   next=[3]
         null←[1]←[2]   cur=[3]→null    pre=[2]

第3轮:   next=null
         null←[1]←[2]←[3]   cur=null    pre=[3] ← 新头
```

**只要能把这张图画出来，代码自然就写对了。** 不画图硬写，就是链表题 bug 的温床。

---

## 五、本章学习路径

```
第 1 天  01 链表基础与哑结点   → 背熟哑结点模板，做删除结点系列 4 道
第 2 天  02 反转与合并（上）   → 反转链表三种写法 + 区间反转，默写模板
第 3 天  02 反转与合并（下）   → K 个一组反转 + 合并两个有序链表
第 4 天  03 快慢指针           → 找中点 + 判环 + 找环入口（Floyd 算法）
第 5 天  03 快慢指针（复习）   → 倒数第 k 个 + 回文链表，重做错题
第 6 天  04 综合与进阶         → 两数相加 + 重排链表 + 复制随机指针
第 7 天  本章综合             → 限时做 反转链表 II + K 个一组 + 环形链表 II
```

---

## 六、易错点总览（整章通用）

| 易错点 | 后果 | 正确做法 |
| --- | --- | --- |
| 反转时忘了先存 `next` | 改完 `cur.next = pre` 就找不到下一个节点 | 永远第一行 `ListNode next = cur.next` |
| 删头结点没用哑结点 | 要为"删的是头"写特判，容易漏 | 加 `dummy`，`return dummy.next` |
| 快慢指针判环条件写错 | `fast.next.next` 空指针 | `while (fast != null && fast.next != null)` |
| 环形链表没有先判空 | `head == null` 直接 `.next` 空指针 | 循环条件已含判空，但初始也要注意 |
| 偶数长度找中点取到前中点 | 某些题要后中点 | 快慢都从 `head` 出发得后中点；需要前中点让 `fast` 从 `head.next` 出发 |
| 合并链表忘了接上剩余部分 | 一条链走完后另一条被丢弃 | 循环后 `cur.next = (l1 != null) ? l1 : l2` |
| 用 `==` 比较节点值 | 比的是引用不是值 | 比值用 `node.val`，比节点用 `==` |

---

## 七、参考代码索引

本章涉及的所有仓库实现：

| 知识点 | 仓库文件 |
| --- | --- |
| 设计链表（双链表 + 哨兵） | [src/listTable/MyLinkedList.java](../../src/listTable/MyLinkedList.java) |
| 反转链表（三种写法） | [src/listTable/ReverseList2.java](../../src/listTable/ReverseList2.java)、[src/listTable/ReverList.java](../../src/listTable/ReverList.java)、[src/listTable/ReverseList3.java](../../src/listTable/ReverseList3.java) |
| 反转链表 II（区间反转） | [src/listTable/ReverseBetween.java](../../src/listTable/ReverseBetween.java)、[src/listTable/ReverseList.java](../../src/listTable/ReverseList.java) |
| K 个一组翻转 | [src/listTable/ReverseKGroup.java](../../src/listTable/ReverseKGroup.java) |
| 合并两个有序链表 | [src/listTable/MergeTwoList.java](../../src/listTable/MergeTwoList.java)、[src/listTable/MergeList.java](../../src/listTable/MergeList.java) |
| 合并 K 个有序链表 | [src/listTable/MergeKList.java](../../src/listTable/MergeKList.java)、[src/listTable/MergeKLists.java](../../src/listTable/MergeKLists.java) |
| 链表中点 | [src/listTable/MiddleNode.java](../../src/listTable/MiddleNode.java) |
| 环形链表（判环） | [src/listTable/CircleInList.java](../../src/listTable/CircleInList.java) |
| 环形链表 II（找入口） | [src/listTable/CircleInList2.java](../../src/listTable/CircleInList2.java) |
| 倒数第 k 个结点 | [src/listTable/getKthFromEnd.java](../../src/listTable/getKthFromEnd.java) |
| 删除倒数第 N 个结点 | [src/listTable/RemoveNthFromEnd.java](../../src/listTable/RemoveNthFromEnd.java) |
| 回文链表 | [src/listTable/IsPalindrome.java](../../src/listTable/IsPalindrome.java)、[src/listTable/PalindromeList.java](../../src/listTable/PalindromeList.java) |
| 删除重复结点 | [src/listTable/DeleteDuplicates.java](../../src/listTable/DeleteDuplicates.java)、[src/listTable/DeleteDuplicates2.java](../../src/listTable/DeleteDuplicates2.java)、[src/listTable/DeleteDuplicates3.java](../../src/listTable/DeleteDuplicates3.java) |
| 删除指定结点 | [src/listTable/DeleteNode.java](../../src/listTable/DeleteNode.java)、[src/listTable/RemoveElement.java](../../src/listTable/RemoveElement.java) |
| 两数相加 | [src/listTable/AddTwoNum.java](../../src/listTable/AddTwoNum.java)、[src/listTable/AddTwoNums.java](../../src/listTable/AddTwoNums.java) |
| 重排链表 | [src/listTable/Reorder.java](../../src/listTable/Reorder.java) |
| 分隔链表 | [src/listTable/PartitionList.java](../../src/listTable/PartitionList.java)、[src/listTable/SplitList.java](../../src/listTable/SplitList.java) |
| 奇偶链表 | [src/listTable/OddEvenList.java](../../src/listTable/OddEvenList.java) |
| 旋转链表 | [src/listTable/RotateList.java](../../src/listTable/RotateList.java)、[src/listTable/RotateRight.java](../../src/listTable/RotateRight.java) |
| 相交链表 | [src/listTable/IntersectionNode.java](../../src/listTable/IntersectionNode.java)、[src/listTable/IntersectList.java](../../src/listTable/IntersectList.java) |
| 复制带随机指针的链表 | [src/listTable/CopyRandomList.java](../../src/listTable/CopyRandomList.java)、[src/listTable/CopyListTable.java](../../src/listTable/CopyListTable.java) |
| 链表排序（归并） | [src/listTable/SortList.java](../../src/listTable/SortList.java)、[src/listTable/InsertSortList.java](../../src/listTable/InsertSortList.java) |
| 两两交换结点 | [src/listTable/SwapNode.java](../../src/listTable/SwapNode.java)、[src/listTable/SwapKNode.java](../../src/listTable/SwapKNode.java) |

---

**开始 →** [01 链表基础与哑结点](01-链表基础与哑结点.md)
