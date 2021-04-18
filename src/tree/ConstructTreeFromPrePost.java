package tree;

import java.util.Arrays;

public class ConstructTreeFromPrePost {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    private TreeNode dfs(int[] pre,int[] post){
        if(pre.length == 0)return null;
        TreeNode node = new TreeNode(pre[0]);
        if(pre.length ==1)return node;
        int posNode = pre[1];
        for (int i = 0; i <post.length ; i++) {
            if(post[i] == posNode){
                //Arrays.copyOfRange 包左不包右
                node.left = dfs(Arrays.copyOfRange(pre,1,i+2),Arrays.copyOfRange(post,0,i+1));
                node.right = dfs(Arrays.copyOfRange(pre,i+2,pre.length),Arrays.copyOfRange(post,i+1,post.length-1));
            }
        }
        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre.length == 0)return null;
        return dfs(pre,post);
    }

}
