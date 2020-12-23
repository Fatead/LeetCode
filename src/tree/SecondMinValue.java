package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondMinValue {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private List<Integer> list = new ArrayList<>();

    private void inOrder(TreeNode node){
        if(node==null)return;
        list.add(node.val);
        inOrder(node.left);
        inOrder(node.right);
    }

    public int findSecondMinimumValue(TreeNode root) {
        inOrder(root);
        Collections.sort(list);
        int min = list.get(0);
        for(int i:list){
            if(i!=min)return i;
        }
        return -1;
    }

}