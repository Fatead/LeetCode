package tree;

import java.util.*;

public class ModeInBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> list = new ArrayList<>();


    private void preOrder(TreeNode node){
        if(node == null)return;
        list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public int[] findMode(TreeNode root) {
        preOrder(root);
        Map<Integer,Integer> map = new HashMap<>();
        for(Integer i:list){
            map.put(i,map.getOrDefault(i,0) + 1);
        }
        int maxCount = Integer.MIN_VALUE;
        for(Integer i:map.values()){
            if(i>maxCount){
                maxCount = i;
            }
        }
        List<Integer> resultList = new LinkedList<>();
        for(Integer i:map.keySet()){
            if(map.get(i) == maxCount){
                resultList.add(i);
            }
        }
        int count = 0;
        int[] result = new int[resultList.size()];
        for(Integer i:resultList){
            result[count++] = i;
        }
        return result;
    }


    public static void main(String[] args) {

    }

}
