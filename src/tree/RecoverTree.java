package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoverTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> list = new ArrayList<>();

    Map<Integer,TreeNode> map = new HashMap<>();

    private void inOrder(TreeNode node){
        if(node == null)return;
        inOrder(node.left);
        list.add(node.val);
        map.put(node.val,node);
        inOrder(node.right);
    }

    private void swap(TreeNode node1,TreeNode node2){
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public void recoverTree(TreeNode root) {
        inOrder(root);
        List<int[]> reverse = new ArrayList<>();
        for (int i = 0; i <list.size()-1 ; i++) {
            if(list.get(i)>list.get(i+1)){
                reverse.add(new int[]{list.get(i),list.get(i+1)});
            }
        }
        if(reverse.size() == 2){
            swap(map.get(reverse.get(0)[0]),map.get(reverse.get(1)[1]));
        }else {
            swap(map.get(reverse.get(0)[0]),map.get(reverse.get(0)[1]));
        }
    }

}