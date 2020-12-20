package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeWidth {


    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
        public TreeNode(int value,TreeNode left,TreeNode right){
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }

    class AnnotateNode{
        TreeNode node;
        int depth;
        int pos;
        AnnotateNode(TreeNode node,int depth,int pos){
            this.node = node;
            this.depth = depth;
            this.pos = pos;
        }

    }


    public int widthOfBinaryTree(TreeNode root) {
        Queue<AnnotateNode> queue = new LinkedList<>();
        queue.add(new AnnotateNode(root,0,0));
        int currDepth = 0;
        int left = 0;
        int result = 0;
        while (!queue.isEmpty()){
            AnnotateNode annotateNode = queue.poll();
            if(annotateNode.node!=null){
                queue.add(new AnnotateNode(annotateNode.node.left,annotateNode.depth+1,annotateNode.pos*2));
                queue.add(new AnnotateNode(annotateNode.node.right,annotateNode.depth+1,annotateNode.pos*2+1));
                if(currDepth!=annotateNode.depth){
                    currDepth = annotateNode.depth;
                    left = annotateNode.pos;
                }
                result = Math.max(result,annotateNode.pos-left + 1);
            }
        }
        return result;
    }

}
