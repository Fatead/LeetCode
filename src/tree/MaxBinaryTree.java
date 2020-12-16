package tree;

public class MaxBinaryTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0)return null;
        int maxValue = Integer.MIN_VALUE;
        int maxPos = 0;
        if(nums.length == 1){
            return new TreeNode(nums[0]);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i] > maxValue){
                maxValue = nums[i];
                maxPos = i;
            }
        }
        TreeNode node = new TreeNode(maxValue);
        int[] numsLeft = new int[maxPos];
        int[] numsRight = new int[nums.length-maxPos-2];
        for(int i = 0;i<maxPos;i++){
            numsLeft[i] = nums[i];
        }
        for(int i = 0;i<numsRight.length;i++){
            numsRight[i] = nums[i+maxPos+2];
        }
        node.left = constructMaximumBinaryTree(numsLeft);
        node.right = constructMaximumBinaryTree(numsRight);
        return node;
    }

}
