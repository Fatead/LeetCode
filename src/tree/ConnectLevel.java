package tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectLevel {

    public static class Node{
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val, Node left, Node right,Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
        Node(int val){
            this.val = val;
        }
    }

    public Node connect(Node root) {
        if(root == null)return null;
        Queue<Node> queue = new LinkedList<>();
        int currCount = 1;
        int nextCount = 0;
        queue.add(root);
        Node preNode = null;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.left!=null){
                queue.add(node.left);
                nextCount++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextCount++;
            }
            if(preNode!=null)
                preNode.next = node;
            currCount--;
            if(currCount == 0){
                currCount = nextCount;
                nextCount = 0;
                preNode = null;
            }else {
                preNode = node;
            }
        }
        return root;
    }

}
