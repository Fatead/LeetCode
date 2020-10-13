package tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum3 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }


    private int countPath(TreeNode node, int sum){
        if(node == null)return 0;
        sum = sum - node.val;
        int result = sum == 0?1:0;
        return result + countPath(node.left,sum) + countPath(node.right,sum);
    }

    public int pathSum(TreeNode root, int sum) {
        if(root == null)return 0;
        int result = countPath(root,sum);
        int left = pathSum(root.left,sum);
        int right = pathSum(root.right,sum);
        return result + left + right;
    }

    private TreeNode buildTree(){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;
        return root;
    }

    public static void main(String[] args) {
        PathSum3 pathSum3 = new PathSum3();
        TreeNode root = pathSum3.buildTree();
        int result =  pathSum3.pathSum(root,8);
        System.out.println(result);
    }
}
