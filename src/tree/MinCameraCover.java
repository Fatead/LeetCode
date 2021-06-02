package tree;

public class MinCameraCover {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }


    /**
     * 状态定义：
     * 0.当前节点安装相机时需要的最少相机数
     * 1.当前节点不安装相机，但是能被覆盖到的时候，需要的最少相机数
     * 2.当前节点不安装相机，也不能被覆盖的时候，需要的最少相机数
     * 返回的int[3]数组对应三种状态
     */
    public int[] minCamera(TreeNode root){
        int[] dp = new int[3];
        if(root == null){
            dp[0] = Integer.MAX_VALUE/2;
            dp[2] = Integer.MAX_VALUE/2;
            return dp;
        }
        int[] left = minCamera(root.left);
        int[] right = minCamera(root.right);
        //状态0，如果当前节点已经安装相机，那么孩子节点可以装或者不装
        dp[0] = Math.min(left[0],Math.min(left[1],left[2])) + Math.min(right[0],Math.min(right[1],right[2])) + 1;
        //状态1，如果当前节点没有安装相机，也能被覆盖
        dp[1] = Math.min(left[0] + Math.min(right[0],right[1]),right[0] + Math.min(left[0],left[1]));
        dp[2] = left[1] + right[1];
        return dp;
    }


    public int minCameraCover(TreeNode root){
        int[] ans = minCamera(root);
        return Math.min(ans[0],ans[1]);
    }

}
