package tree;

import java.util.ArrayList;
import java.util.List;

public class SumNum {


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<Integer> list = new ArrayList<>();

    private void travel(TreeNode node,int number){
        if(node == null)return;
        if(node.left == null && node.right == null){
            number = number*10 + node.val;
            list.add(number);
        }else{
            number = number*10 + node.val;
            travel(node.left,number);
            travel(node.right,number);
        }

    }

    public int sumNumbers(TreeNode root) {
        travel(root,0);
        int sum = 0;
        for(Integer integer:list){
            sum+=integer;
        }
        return sum;
    }

}
