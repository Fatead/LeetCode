package tree;

import java.util.*;

public class BuildTree {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int value;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(inorder.length == 1)return new TreeNode(inorder[0]);
        else if(inorder.length == 2){
            TreeNode treeNode = new TreeNode(preorder[0]);
            if(inorder[0] == preorder[0]){
                treeNode.right = new TreeNode(inorder[1]);
            }else{
                treeNode.left = new TreeNode(inorder[0]);
            }
            return treeNode;
        }else if(inorder.length == 0||inorder.length!=preorder.length){
            return null;
        }else{
            TreeNode treeNode = new TreeNode(preorder[0]);
            int position = 0;
            for(int i = 0;i<inorder.length;i++){
                if(inorder[i] == preorder[0]){
                    position = i;
                }
            }
            int[] inorder1 = Arrays.copyOfRange(inorder,0,position);
            int[] inorder2 = Arrays.copyOfRange(inorder,position+1,inorder.length);
            int[] preorder1 = new int[inorder1.length];
            int[] preorder2 = new int[inorder2.length];
            int n = 0,m = 0;
            for(int i =0;i<preorder.length;i++){
                for(int j = 0;j<inorder1.length;j++){
                    if(preorder[i] == inorder1[j]){
                        preorder1[n++] = preorder[i];
                    }
                }
                for(int j = 0;j<inorder2.length;j++){
                    if(preorder[i] == inorder2[j]){
                        preorder2[m++] = preorder[i];
                    }
                }
            }
            System.out.println("preder1:" +Arrays.toString(preorder1));
            System.out.println("preder2:" +Arrays.toString(preorder2));
            treeNode.left = buildTree(preorder1,inorder1);
            treeNode.right = buildTree(preorder2,inorder2);
            return treeNode;
        }
    }

    public static void main(String[] args) {
        int[] preOrderArray = {3,9,20,15,7};
        int[] inOrderArray = {9,3,15,20,7};
        BuildTree buildTree = new BuildTree();
        buildTree.buildTree(preOrderArray,inOrderArray);
    }
}
