package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallestFromLeaf {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    List<String> list = new ArrayList<>();

    private void dfs(TreeNode node,String path){
        if(node == null){
            return;
        }
        path = path + (char)(node.val + 'a');
        if(node.left == null && node.right == null){
            list.add(path);
        }
        dfs(node.left, path);
        dfs(node.right,path);
    }

    public String smallestFromLeaf(TreeNode root) {
        if(root == null)return "";
        if(root.left == null && root.right == null)return new String(String.valueOf((char)(root.val + 'a')));
        dfs(root,"");
        List<String> reverseList = new ArrayList<>();
        for (String s : list) {
            if(s.length()<=1)continue;
            reverseList.add(new StringBuilder(s).reverse().toString());
        }
        if(reverseList.size() == 0)return "";
        Collections.sort(reverseList);
        return reverseList.get(0);
    }


}
