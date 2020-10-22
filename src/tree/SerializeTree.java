package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SerializeTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        if(root == null)return stringBuilder.toString();
        nodeQueue.add(root);
        int nodeCount = 1;
        while(!nodeQueue.isEmpty()){
            TreeNode treeNode = nodeQueue.poll();
            if(treeNode.val!=0) nodeCount--;
            stringBuilder.append(treeNode.val).append(" ");
            if(treeNode.left == null){
                nodeQueue.add(new TreeNode(-100));
            }else {
                nodeQueue.add(treeNode.left);
                nodeCount++;
            }
            if(treeNode.right == null){
                nodeQueue.add(new TreeNode(-100));
            }else {
                nodeQueue.add(treeNode.right);
                nodeCount++;
            }
            System.out.println(nodeQueue);
            if(nodeCount == 0)break;
        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if(data == null||data.length() == 0)return null;
        String[] nodeValues = data.split(" ");
        int len = nodeValues.length;
        Map<Integer,TreeNode> nodeMap = new HashMap<>();
        for(int i = 0;i<len;i++){
            System.out.println(i);
            if(!nodeValues[i].equals("-100")){
                TreeNode node = new TreeNode(Integer.parseInt(nodeValues[i]));
                nodeMap.put(i,node);
            }
        }
        for(int i = 0;i<len;i++){
            TreeNode node = nodeMap.get(i);
            if(node!=null){
                node.left = nodeMap.get(i*2+1);
                node.right = nodeMap.get(i*2+2);
            }
        }
        return nodeMap.get(0);
    }

}
