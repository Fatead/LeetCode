package tree;

import java.util.*;

public class ConvertBST {

    List<Integer> list = new ArrayList<>();

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private void inOrder(TreeNode node){
        if(node == null)return;
        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);
    }

    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        Map<Integer,Integer> map = new HashMap<>();
        int[] sum = new int[list.size()+1];
        for(int i = list.size()-1;i>=0;i--){
            sum[i] = sum[i+1] + list.get(i);
            map.put(list.get(i),sum[i]);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            node.val = map.get(node.val);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return root;
    }

    int sum2 = 0;

    public TreeNode convertBST2(TreeNode root){
        if(root!=null){
            convertBST2(root.right);
            sum2 += root.val;
            root.val = sum2;
            convertBST2(root.left);
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
