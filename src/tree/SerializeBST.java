package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeBST {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private StringBuilder postOrder(TreeNode node,StringBuilder stringBuilder){
        if(node!=null){
            postOrder(node.left,stringBuilder);
            postOrder(node.right,stringBuilder);
            stringBuilder.append(node.val).append(" ");
        }
        return stringBuilder;
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        postOrder(root,stringBuilder);
        return stringBuilder.toString();
    }

    public TreeNode buildTreeInAndPost(int[] inOrder,int[] postOrder){
        if(inOrder.length == 0)return null;
        if(inOrder.length == 1)return new TreeNode(inOrder[0]);
        TreeNode root = new TreeNode(postOrder[postOrder.length-1]);
        int index = 0;
        for(int i = 0;i<inOrder.length;i++){
            if(inOrder[i] == postOrder[postOrder.length-1]){
                index = i;
            }
        }
        root.left = buildTreeInAndPost(Arrays.copyOfRange(inOrder,0,index),Arrays.copyOfRange(postOrder,0,index));
        root.right = buildTreeInAndPost(Arrays.copyOfRange(postOrder,index+1,postOrder.length),Arrays.copyOfRange(postOrder,index,postOrder.length-1));
        return root;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(" ");
        int[] postOrder = new int[strings.length];
        int[] inOrder = new int[strings.length];
        for(int i = 0;i<strings.length;i++){
            postOrder[i] = Integer.parseInt(strings[i]);
            inOrder[i] = Integer.parseInt(strings[i]);
        }
        Arrays.sort(inOrder);
        return buildTreeInAndPost(inOrder,postOrder);
    }

}
