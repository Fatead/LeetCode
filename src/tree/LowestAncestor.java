package tree;

public class LowestAncestor {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pValue = p.val;
        int qValue = q.val;
        if(pValue<parentVal&&qValue<parentVal){
           return lowestCommonAncestor(root.left,p,q);
        }else if(qValue>parentVal&&pValue>parentVal){
            return lowestCommonAncestor(root.right,p,q);
        }else {
            return root;
        }
    }
}
