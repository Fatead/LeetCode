package tree;

import java.util.Arrays;

public class RebuildTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0||inorder.length==0)return null;
        TreeNode node = new TreeNode(preorder[0]);
        if(inorder.length == 1)return node;
        int leftPos = 0;
        while(inorder[leftPos] != node.val){
            leftPos++;
        }
        node.left = buildTree(Arrays.copyOfRange(preorder,1,leftPos+1),Arrays.copyOfRange(inorder,0,leftPos));
        node.right = buildTree(Arrays.copyOfRange(preorder,leftPos+1,preorder.length),Arrays.copyOfRange(inorder,leftPos+1,preorder.length));
        return node;
    }


    public static void main(String[] args) {
        RebuildTree rebuildTree = new RebuildTree();
        int[] preOrder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = rebuildTree.buildTree(preOrder,inorder);
    }

}
