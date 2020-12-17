package tree;

import java.util.ArrayList;
import java.util.List;

public class PrintTree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    List<List<String>> resultList = new ArrayList<>();

    private int getLayer(TreeNode node){
        if(node == null)return 0;
        if(node.left == null && node.right == null)return 1;
        return Math.max(getLayer(node.left),getLayer(node.right)) + 1;
    }

    private void dfs(TreeNode node,int left,int right,int layer){
        if(layer>=resultList.size()||node == null)return;
        resultList.get(layer).set((left + right)/2, String.valueOf(node.val));
        dfs(node.left,left,(left + right)/2-1,layer+1);
        dfs(node.right,(left + right)/2,right,layer+1);
    }

    public List<List<String>> printTree(TreeNode root) {
        int layer = getLayer(root);
        int nodeNum = (int) Math.pow(2,layer)-1;
        //一层层的生成二叉树
        for(int i = 0;i<layer;i++){
            List<String> layerList = new ArrayList<>();
            for(int j = 0;j<nodeNum;j++){
                layerList.add("");
            }
            resultList.add(layerList);
        }
        dfs(root,0,nodeNum-1,0);
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        PrintTree printTree = new PrintTree();
        System.out.println(printTree.printTree(root));
    }

}
