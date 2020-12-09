package tree;

public class TreeToStr {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private String visit(TreeNode node){
        StringBuilder stringBuilder = new StringBuilder();
        if(node == null)return stringBuilder.toString();
        stringBuilder.append(node.val);
        if(node.left == null && node.right == null)return stringBuilder.toString();
        if(node.left!=null){
            stringBuilder.append("(").append(visit(node.left)).append(")");
        }else {
            stringBuilder.append("()");
        }
        if(node.right!=null){
            stringBuilder.append("(").append(visit(node.right)).append(")");
        }
        return stringBuilder.toString();
    }

    public String tree2str(TreeNode t) {
        return visit(t);
    }

}
