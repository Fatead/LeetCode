package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PossibleFBT {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    TreeNode root = null;

    List<TreeNode> result = new ArrayList<>();

    private TreeNode copyTree(TreeNode oldRoot){
        if(oldRoot == null)return null;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> copyQueue = new LinkedList<>();
        queue.add(oldRoot);
        TreeNode newRoot = new TreeNode(0);
        copyQueue.add(newRoot);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode copyNode = copyQueue.poll();
            if(node.left!=null){
                queue.add(node.left);
                copyNode.left = new TreeNode(0);
                copyQueue.add(copyNode.left);
            }
            if(node.right!=null){
                queue.add(node.right);
                copyNode.right = new TreeNode(0);
                copyQueue.add(copyNode.right);
            }
        }
        return newRoot;
    }

    private void dfs(TreeNode node, int n){
        if(n == 0){
            result.add(copyTree(root));
            return;
        }
        node.left = new TreeNode(0);
        node.right = new TreeNode(0);
        dfs(node.left,n-2);
        node.left.left = null;
        node.left.right = null;
        dfs(node.right,n-2);
        node.right.left = null;
        node.right.right = null;
    }

    public List<TreeNode> allPossibleFBT(int n) {
        if(n == 0)return null;
        TreeNode node = new TreeNode(0);
        dfs(node,n-1);
        return result;
    }


}
