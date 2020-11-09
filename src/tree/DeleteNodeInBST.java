package tree;

/**
 * 1.如果被删除节点是leaf直接删除
 * 2.如果被删除节点只有一个child,使用child代替原节点
 * 3.如果被删除的节点有两个children，则在right subtree中寻找successor，将原节点值替换为successor的值，并递归删除successor,将问题转化为步骤1或2
 */
public class DeleteNodeInBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    public int successor(TreeNode root){
        root = root.right;
        while(root.left!=null){
            root = root.left;
        }
        return root.val;
    }


    public int predecessor(TreeNode root){
        root = root.left;
        while(root.right!=null){
            root = root.right;
        }
        return root.val;
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if(key > root.val){
            root.right =  deleteNode(root.right,key);
        }else if(key < root.val){
            root.left = deleteNode(root.left,key);
        }else {
            if(root.left == null && root.right == null)root = null;
            else if(root.right!=null){
                root.val = successor(root);
                root.right = deleteNode(root.right,root.val);
            }else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left,root.val);
            }
        }
        return root;
    }

}
