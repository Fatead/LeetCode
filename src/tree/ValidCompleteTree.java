package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ValidCompleteTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        if(root.left == null && root.right == null)return true;
        List<List<TreeNode>> allNodeList  = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        List<TreeNode> list = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node);
            if(node.left!=null){
                queue.add(node.left);
                nextNum++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextNum++;
            }
            currNum--;
            if(currNum==0){
                currNum = nextNum;
                nextNum = 0;
                allNodeList.add(new ArrayList<>(list));
                list = new ArrayList<>();
            }
        }
        int num = 1;
        for (int i = 0; i <allNodeList.size()-1 ; i++) {
            if(allNodeList.get(i).size()!=num){
                return false;
            }
            num*=2;
        }
        int finalNum = allNodeList.get(allNodeList.size()-1).size();
        List<TreeNode> last2List = allNodeList.get(allNodeList.size()-2);
        for (TreeNode treeNode : last2List) {
            if(treeNode.left!=null && treeNode.right!=null){
                finalNum -= 2;
            }else if(finalNum == 0){
              return true;
            } else if(treeNode.left == null){
                return false;
            }else return finalNum == 1;
        }
        return true;
    }

}
