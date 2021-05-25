package stack;
import java.util.*;

public class ZigZagLevelOrder {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        int levelOrder = 0;
        List<Integer> currList = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node  = queue.poll();
            currList.add(node.val);
            if(node.left!=null){
                queue.add(node.left);
                nextNum++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextNum++;
            }
            currNum--;
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                levelOrder++;
                if(levelOrder%2==0){
                    Collections.reverse(currList);
                    result.add(new ArrayList<>(currList));
                }else {
                    result.add(new ArrayList<>(currList));
                }
                currList = new ArrayList<>();
            }
        }
        return result;
    }

}
