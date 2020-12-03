package tree;

import java.util.ArrayList;
import java.util.List;

public class PostOrderNTree {

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


    List<Integer> result = new ArrayList<>();


    private void postVisit(Node node){
        if(node == null)return;
        for(Node node1:node.children){
            postorder(node1);
        }
        result.add(node.val);
    }

    public List<Integer> postorder(Node root) {
        postVisit(root);
        return result;
    }


}
