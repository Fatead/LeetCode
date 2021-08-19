package tree;
import java.util.ArrayList;
import java.util.List;

public class GenerateTree {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right){
            this.val = x;
            this.left = left;
            this.right = right;
        }

    }


    private List<TreeNode> buildTree(int start,int end){
        List<TreeNode> list = new ArrayList<>();
        if(start == end){
            list.add(new TreeNode(start));
            return list;
        }
        for (int i = start; i <=end ; i++) {
            //以start到i-i构建左边的二叉搜索树
            List<TreeNode> left = buildTree(start,i-1);
            List<TreeNode> right = buildTree(i+1,end);
            for (TreeNode treeNode : left) {
                for (TreeNode node : right) {
                    TreeNode newNode = new TreeNode(i,treeNode,node);
                    list.add(newNode);
                }
            }
        }
        return list;
    }


    public List<TreeNode> generateTrees(int n) {
        if(n == 0)return new ArrayList<>();
        return buildTree(1,n);
    }


    public static void main(String[] args) {
        GenerateTree generateTree = new GenerateTree();
        List<TreeNode> result = generateTree.generateTrees(3);
    }

}