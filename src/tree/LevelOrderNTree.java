package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderNTree {

    class Node{
        public int val;

        public List<Node> children;

        public Node(){

        }

        public Node(int val){
            this.val = val;
        }

        public Node(int val,List<Node> children){
            this.val = val;
            this.children = children;
        }

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        int currNum = 1;
        int nextNum = 0;
        if(root == null)return result;
        queue.add(root);
        List<Integer> currList = new ArrayList<>();
        while(!queue.isEmpty()){
            Node node = queue.poll();
            currNum--;
            currList.add(node.val);
            for(Node child:node.children){
                queue.add(child);
                nextNum++;
            }
            if(currNum == 0){
                currNum = nextNum;
                nextNum = 0;
                result.add(new ArrayList<>(currList));
                currList = new ArrayList<>();
            }
        }
        return result;
    }

}
