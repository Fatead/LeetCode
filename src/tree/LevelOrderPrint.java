package tree;

import java.nio.ShortBuffer;
import java.util.*;


public class LevelOrderPrint {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)return result;
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        queue.add(null);
        int count = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null && queue.isEmpty()){
                break;
            }else if(node==null){
                if(count%2==1){
                    List<Integer> newList = new ArrayList<>(list);
                    Collections.reverse(newList);
                    result.add(newList);
                }else {
                    result.add(new ArrayList<>(list));
                }
                count++;
                list = new ArrayList<>();
                queue.add(null);
            }
            if(node!=null){
                list.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        if(count%2==1){
            List<Integer> newList = new ArrayList<>(list);
            Collections.reverse(newList);
            result.add(newList);
        }else {
            result.add(new ArrayList<>(list));
        }
        return result;
    }


}
