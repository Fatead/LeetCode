package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlipMatchVoyage {


    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> list = new ArrayList<>();
    boolean flag = true;

    private void dfs(TreeNode node,int[] nums){
        if(node == null || !flag || nums.length==0)return;
        if(node.val!=nums[0]||(node.left == null && node.right == null && nums.length>1)){
            list = new ArrayList<>();
            list.add(-1);
            flag = false;
            return;
        }
        if(node.left == null && node.right == null){
            return;
        }
        if(node.left == null){
            dfs(node.right, Arrays.copyOfRange(nums,1,nums.length));
        }else if(node.right == null){
            dfs(node.left, Arrays.copyOfRange(nums,1,nums.length));
        }else {
            int leftIndex = 0;
            int rightIndex = 0;
            for (int i = 0; i <nums.length ; i++) {
                if(nums[i] == node.left.val){
                    leftIndex = i;
                }else if(nums[i] == node.right.val){
                    rightIndex = i;
                }
            }
            if(leftIndex == 1 && leftIndex<rightIndex){
                dfs(node.left,Arrays.copyOfRange(nums,leftIndex,rightIndex));
                dfs(node.right,Arrays.copyOfRange(nums,rightIndex,nums.length));
            }else if(rightIndex == 1 && rightIndex<leftIndex){
                list.add(node.val);
                dfs(node.right,Arrays.copyOfRange(nums,rightIndex,leftIndex));
                dfs(node.left,Arrays.copyOfRange(nums,leftIndex,nums.length));
            }else {
                list = new ArrayList<>();
                list.add(-1);
                flag = false;
            }
        }
    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        if(root == null)return list;
        dfs(root,voyage);
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        int[] nums = {3,4,2,1};
        FlipMatchVoyage flipMatchVoyage = new FlipMatchVoyage();
        System.out.println(flipMatchVoyage.flipMatchVoyage(root,nums));
    }

}
