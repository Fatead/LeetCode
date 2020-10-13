package tree;

public class ConstructQuadTree {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node(){
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };

    private boolean equalValue(int[][] grid,int x0,int y0,int x1,int y1){
        int flag = grid[x0][y0];
        for(int i = x0;i<x1;i++){
            for(int j = y0;j<y1;j++){
                if(grid[i][j]!=flag)return false;
            }
        }
        return true;
    }


    public Node constructTree(int[][] grid,int x0,int y0,int x1,int y1){
        Node node = new Node();
        if(equalValue(grid,x0,y0,x1,y1)){
            node.isLeaf = true;
            node.val = grid[x0][y0] != 0;
            return node;
        }else {
            node.isLeaf = false;
            node.topLeft = constructTree(grid,x0,y0,(x0 + x1)/2,(y0 + y1)/2);
            node.topRight = constructTree(grid,(x0 + x1)/2 +1,y0,x1,(y0 + y1)/2);
            node.bottomLeft = constructTree(grid,x0,(y0 + y1)/2 + 1,(x0 +x1)/2,y1);
            node.bottomRight = constructTree(grid,(x0 + x1)/2 + 1,(y0 + y1)/2 + 1,x1,y1);
            return node;
        }
    }


    public Node construct(int[][] grid) {
        return constructTree(grid,0,0,grid.length,grid[0].length);
    }

}
