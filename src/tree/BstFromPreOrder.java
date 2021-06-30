package tree;

import java.util.Arrays;

public class BstFromPreOrder {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
        public TreeNode(int value, TreeNode left, TreeNode right){
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length==0)return null;
        TreeNode node = new TreeNode(preorder[0]);
        if(preorder.length==1)return node;
        int index = 0;
        for (int i = 1; i <preorder.length ; i++) {
            if(preorder[i]>preorder[0]){
                index = i;
                break;
            }
        }
        if(index>0){
            node.left = bstFromPreorder(Arrays.copyOfRange(preorder,1,index));
            node.right = bstFromPreorder(Arrays.copyOfRange(preorder,index,preorder.length));
        }else {
            node.right = bstFromPreorder(Arrays.copyOfRange(preorder,1,preorder.length));
        }
        return node;
    }


    public static void main(String[] args) {
        BstFromPreOrder bstFromPreOrder = new BstFromPreOrder();
        int[] preorder = {4,2};
        TreeNode node = bstFromPreOrder.bstFromPreorder(preorder);
    }
    
}