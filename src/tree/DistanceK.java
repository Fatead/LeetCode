package tree;

import java.util.*;

public class DistanceK {

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int value){
            this.val = value;
        }
    }


    //记录每个节点的父节点
    Map<TreeNode,TreeNode> map = new HashMap<>();

    TreeNode targetNode;

    Set<TreeNode> usedNode = new HashSet<>();


    /**
     * 分别向三个方向进行DFS
     */
    private void dfs(TreeNode node,List<Integer> list,int k){
        if(node!=null && !usedNode.contains(node)){
            usedNode.add(node);
            if(k <= 0){
                list.add(node.val);
                return;
            }
            dfs(node.left,list,k-1);
            dfs(node.right,list,k-1);
            dfs(map.get(node),list,k-1);
        }
    }


    private void find(TreeNode node,TreeNode parent,TreeNode target){
        if(null == node)return;
        if(node.val == target.val){
            targetNode = node;
        }
        map.put(node,parent);
        find(node.left,node,target);
        find(node.right,node,target);
    }


    /**
     * 找到目标节点后，从目标节点开始向三个方向搜索
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        if(root == null)return result;
        find(root,null,target);
        dfs(targetNode,result,k);
        return result;
    }

}
