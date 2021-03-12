package tree;

import java.util.Arrays;

public class VerifyPostOrder {

    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length<=1)return true;
        int root = postorder[postorder.length-1];
        for (int i = postorder.length - 2; i >= 0; i--) {
            //找到左右子树的分界点
            if(postorder[i]<root){
                //此使需要保证左子树中的所有节点全部小于根节点
                for (int j = 0; j <i ; j++) {
                    if(postorder[j]>root){
                        return false;
                    }
                }
                //分别验证左右子树
                int[] leftOrder = Arrays.copyOfRange(postorder,0,i+1);
                int[] rightOrder = Arrays.copyOfRange(postorder,i+1,postorder.length-1);
                return verifyPostorder(leftOrder) && verifyPostorder(rightOrder);
            }
        }
        return verifyPostorder(Arrays.copyOfRange(postorder,0,postorder.length-1));
    }

    public static void main(String[] args) {
        VerifyPostOrder verifyPostOrder = new VerifyPostOrder();
        int[] postOrder = {1,6,3,2,5};
        System.out.println(verifyPostOrder.verifyPostorder(postOrder));
    }

}
