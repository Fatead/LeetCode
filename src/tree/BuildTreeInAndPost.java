package tree;

import java.util.Arrays;

public class BuildTreeInAndPost {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0)return null;
        if(inorder.length == 1)return new TreeNode(inorder[0]);
        int rootValue = postorder[postorder.length-1];
        int index = 0;
        for(int i =0;i<inorder.length;i++){
            if(inorder[i] == rootValue){
                index = i;
                break;
            }
        }
        TreeNode rootNode = new TreeNode(rootValue);
        if(index != 0)
            rootNode.left = buildTree(Arrays.copyOfRange(inorder,0,index),Arrays.copyOfRange(postorder,0,index));
        if(index!= inorder.length-1)
            rootNode.right = buildTree(Arrays.copyOfRange(inorder,index+1,inorder.length),Arrays.copyOfRange(postorder,index,postorder.length-1));
        return rootNode;
    }

    public static void main(String[] args) {
        int[] inOrder = {1,3,2};
        int[] postOrder = {3,2,1};
        BuildTreeInAndPost buildTreeInAndPost = new BuildTreeInAndPost();
        TreeNode root =buildTreeInAndPost.buildTree(inOrder,postOrder);
    }
}
