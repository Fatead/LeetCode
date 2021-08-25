package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Connect {


    public static class Node{
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val, Node left, Node right, Node next) {
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
        Queue<Node> queue = new LinkedList<>();
        if(root == null)return root;
        queue.add(root);
        int currNum = 1;
        int nextNum = 0;
        Node pre = null;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            currNum--;
            if(pre!=null){
                pre.next = node;
            }
            pre = node;
            if(node.left!=null){
                queue.add(node.left);
                nextNum++;
            }
            if(node.right!=null){
                queue.add(node.right);
                nextNum++;
            }
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                pre = null;
            }
        }
        return root;
    }


}
