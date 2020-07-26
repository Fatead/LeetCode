package tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<List<Integer>> pathList = new ArrayList<>();

    public List<List<Integer>> sum(TreeNode treeNode,List<Integer> list,int sum){
        if(treeNode == null){
            return pathList;
        }
        list.add(treeNode.val);
        if(treeNode.left == null && treeNode.right == null && treeNode.val == sum){
            pathList.add(new ArrayList<>(list));
            return pathList;
        }else if(treeNode.left == null && treeNode.right == null) {
            return pathList;
        }
        if(treeNode.left == null){
            return sum(treeNode.right,new ArrayList<>(list),sum - treeNode.val);
        }
        if(treeNode.right == null){
            return sum(treeNode.left,new ArrayList<>(list),sum - treeNode.val);
        }
        sum(treeNode.right,new ArrayList<>(list),sum - treeNode.val);
        sum(treeNode.left,new ArrayList<>(list),sum - treeNode.val);
        return pathList;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return pathList;
        list.add(root.val);
        if(root.left == null && root.right == null){
            if(root.val == sum){
                pathList.add(list);
                return pathList;
            }
            return pathList;
        }
        if(root.left == null){
            return sum(root.right,new ArrayList<>(list),sum - root.val);
        }
        if(root.right == null){
            return sum(root.left,new ArrayList<>(list),sum - root.val);
        }
        sum(root.right,new ArrayList<>(list),sum - root.val);
        sum(root.left,new ArrayList<>(list),sum - root.val);
        return pathList;
    }

    private TreeNode buildTree(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(-2);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(-1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node3.left = node6;
        return root;
    }

    public static void main(String[] args) {
        PathSum2 pathSum2 = new PathSum2();
        TreeNode root = pathSum2.buildTree();
        pathSum2.pathSum(root,-1);
    }

}
