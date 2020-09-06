package tree;

import java.util.*;

public class LowestCommonAncestor {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    Map<Integer,TreeNode> nodeMap = new HashMap<>();
    List<Integer> inorderList = new ArrayList<>();

    private void inOrder(TreeNode node){
        if(node == null)return;
        inOrder(node.left);
        inorderList.add(node.val);
        nodeMap.put(node.val,node);
        inOrder(node.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)return null;
        inOrder(root);
        Map<Integer,Integer> intMap = new HashMap<>();
        for(int i =0;i<inorderList.size();i++){
            intMap.put(inorderList.get(i),i);
        }
        TreeNode node = root;
        while((intMap.get(p.val)<intMap.get(node.val)&&intMap.get(q.val)<intMap.get(node.val)) || (intMap.get(p.val)>intMap.get(node.val)&&intMap.get(q.val)>intMap.get(node.val)) ){
            if(intMap.get(p.val)<intMap.get(node.val)&&intMap.get(q.val)<intMap.get(node.val)){
                node = node.left;
            }else {
                node = node.right;
            }
        }
        return node;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
        if(root == p|| root == q)return root;
        if(root!=null){
            TreeNode leftNode = lowestCommonAncestor2(root.left,p,q);
            TreeNode rightNode = lowestCommonAncestor2(root.right,p,q);
            if(leftNode!= null && rightNode!=null){
                return root;
            }else if(leftNode == null){
                return rightNode;
            }else {
                return leftNode;
            }
        }
        return null;
    }

}
