package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeByDoublyList {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    List<Node> list = new ArrayList<>();

    private void postOrderVisit(Node node){
        if(node == null)return;
        postOrderVisit(node.left);
        list.add(node);
        postOrderVisit(node.right);
    }

    public Node treeToDoublyList(Node root) {
        postOrderVisit(root);
        for (int i = 0; i < list.size()-1; i++) {
            Node node = list.get(i);
            Node nextNode = list.get(i+1);
            node.right = nextNode;
            nextNode.left = node;
        }
        list.get(0).left = list.get(list.size()-1);
        list.get(list.size()-1).right = list.get(0);
        return list.get(0);
    }

}
