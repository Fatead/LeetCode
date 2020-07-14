package tree;

import java.util.ArrayList;
import java.util.List;

public class DiffSearchTree2 {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        TreeNode(int val){
            this.val = val;
        }
    }


    private List<TreeNode> generateTreeList(int start, int end){
        List<TreeNode> list = new ArrayList<TreeNode> ();
        if(start >= end){
            list.add(null);
            return list;
        }
        for(int i = start;i<=end;i++){
            List<TreeNode> leftList = generateTreeList(start,i-1);
            List<TreeNode> rightList = generateTreeList(i+1,end);
            for(TreeNode leftNode : leftList){
                for (TreeNode rightNode : rightList) {
                    TreeNode treeNode = new TreeNode(i,leftNode, rightNode);
                    list.add(treeNode);
                }
            }
        }
        return list;
    }

    public List<TreeNode> generateTrees(int n) {
        if(n == 0)return new ArrayList<TreeNode>();
        return generateTreeList(1,n);
    }


    public static void main(String[] args) {
        DiffSearchTree2 tree = new DiffSearchTree2();
        List<TreeNode> result = tree.generateTrees(3);
        
    }
}
