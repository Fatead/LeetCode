package tree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {

    int size;
    List<Integer> list = new ArrayList<>();
    int pos;

    public class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int value;
        public TreeNode(int value){
            this.value = value;
        }
    }

    private void InOrder(TreeNode node){
        if(node == null)return;
        InOrder(node.left);
        list.add(node.value);
        InOrder(node.right);
    }

    public BSTIterator(TreeNode root) {
        InOrder(root);
        size= list.size();
        pos = 0;
    }

    /** @return the next smallest number */
    public int next() {
        if(pos<size){
            int result = list.get(pos);
            pos++;
            return result;
        }else {
            return 0;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return pos < size;
    }
}
