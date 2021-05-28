package tree;

import java.util.LinkedHashMap;

public class BstToGst {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    int sum = 0;

    LinkedHashMap<Integer,TreeNode> map = new LinkedHashMap<>();

    private void postOrder(TreeNode node){
        if(node == null)return;
        postOrder(node.left);
        map.put(node.val,node);
        sum += node.val;
        postOrder(node.right);
    }

    public TreeNode bstToGst(TreeNode root) {
        postOrder(root);
        for(Integer val:map.keySet()){
            map.get(val).val = sum;
            sum -= val;
        }
        return root;
    }


}