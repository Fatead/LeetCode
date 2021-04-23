package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CBTInserter {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int value;
        public TreeNode(int value){
            this.value = value;
        }
    }

    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode currentInsertNode = null;
    TreeNode root = null;

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            //找到插入节点
            if(node.left == null || node.right == null){
                currentInsertNode = node;
                if(node.left!=null){
                    queue.add(node.left);
                }
                break;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
    }

    public int insert(int v) {
        if(currentInsertNode.left == null){
            currentInsertNode.left = new TreeNode(v);
            queue.add(currentInsertNode.left);
            return currentInsertNode.value;
        }else if(currentInsertNode.right == null){
            currentInsertNode.right = new TreeNode(v);
            queue.add(currentInsertNode.right);
            int value = currentInsertNode.value;
            currentInsertNode = queue.poll();
            return value;
        }
        return 0;
    }

    public TreeNode get_root() {
        return this.root;
    }

}