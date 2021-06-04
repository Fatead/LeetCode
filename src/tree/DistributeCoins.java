package tree;

/**
 * 如果树的叶子节点包含0枚金币，那么需要从它的父节点中移动一枚金币到叶子节点上
 * 如果树的叶子节点包含4枚金币，那么需要将3枚金币移动到别处
 */
public class DistributeCoins {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int value;
        public TreeNode(int value){
            this.value = value;
        }
    }

    int result = 0;

    /**
     * node.val + R + L -1：表示当前节点需要拿给子节点的金币个数或者从子节点拿给自己的金币个数，即当前节点的金币移动次数
     * 后续遍历保证了子节点已经得到了需要的金币，另外每个节点都需要保证只有一个硬币，多余的硬币都需要被移动走
     */
    private int dfs(TreeNode node){
        if(node == null)return 0;
        int L = dfs(node.left);
        int R = dfs(node.right);
        result += Math.abs(L) + Math.abs(R);
        return node.value + R + L -1;
    }

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return result;
    }

}
