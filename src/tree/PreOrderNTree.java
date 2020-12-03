package tree;

import java.util.ArrayList;
import java.util.List;

public class PreOrderNTree {

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


    private void preVisit(Node node){
        if(node == null)return;
        result.add(node.val);
        for(Node node1:node.children){
            preVisit(node1);
        }
    }


    public List<Integer> preorder(Node root) {
        preVisit(root);
        return result;
    }


}
