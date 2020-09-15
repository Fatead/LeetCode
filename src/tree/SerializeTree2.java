package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeTree2 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private String dfsSerialize(TreeNode node,String str){
        if(node == null){
            str += "None ";
        }else {
            str += String.valueOf(node.val) + " ";
            str = dfsSerialize(node.left,str);
            str = dfsSerialize(node.right,str);
        }
        return str;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return dfsSerialize(root,"");
    }


    private TreeNode dfsDeserialize(List<String> list){
        if(list.get(0).equals("None")){
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = dfsDeserialize(list);
        node.right = dfsDeserialize(list);
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArray = data.split(" ");
        List<String> list = new LinkedList<>(Arrays.asList(strArray));
        return dfsDeserialize(list);
    }

}
