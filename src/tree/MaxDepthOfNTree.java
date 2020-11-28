package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxDepthOfNTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if(root == null)return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        int depth = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            queue.addAll(node.children);
            currNum--;
            nextNum += node.children.size();
            if(currNum == 0){
                depth++;
                currNum = nextNum;
                nextNum = 0;
            }
        }
        return depth;
    }


    public int maxDepth2(Node root){
        if(root == null)return 0;
        int result = 1;
        for(Node child:root.children){
            result = Math.max(result,1+ maxDepth2(child));
        }
        return result;
    }

}
