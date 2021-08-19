package tree;

public class DeleteBSTNode {

    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public int successor(TreeNode node){
        node = node.right;
        while (node.left!=null){
            node = node.left;
        }
        return node.val;
    }

    public int predecessor(TreeNode node){
        node = node.left;
        while (node.right!=null){
            node = node.right;
        }
        return node.val;
    }

    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null)return null;
        if(root.val>key){
            root.left = deleteNode(root.left,key);
        }else if(root.val<key){
            root.right = deleteNode(root.right,key);
        }else {
            if(root.left == null && root.right == null)root = null;
            else if(root.right!=null){
                root.val = successor(root);
                //递归地删除后继结点
                root.right = deleteNode(root.right, root.val);
            }else{
                root.val = predecessor(root);
                //递归地删除前驱结点
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

}