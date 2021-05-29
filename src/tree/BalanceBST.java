package tree;
import java.util.ArrayList;
import java.util.List;

public class BalanceBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> list = new ArrayList<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        list.add(node.val);
        postOrder(node.right);
    }

    private TreeNode buildBST(List<Integer> list,int left,int right){
        if(left>right)return null;
        int mid = (left + right)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = buildBST(list,left,mid-1);
        node.right = buildBST(list,mid+1,right);
        return node;
    }

    public TreeNode balanceBST(TreeNode root) {
        postOrder(root);
        int size = list.size();
        TreeNode newRoot = buildBST(list,0,size-1);
        return newRoot;
    }

}