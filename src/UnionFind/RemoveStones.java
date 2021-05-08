package UnionFind;
import java.util.HashMap;
import java.util.Map;

/**
 * No.947 移除最多的同行或者同列的石头
 */
public class RemoveStones {

    public int removeStones(int[][] stones){
        UnionFind unionFind = new UnionFind();
        for (int[] stone : stones) {
            //横坐标加10001是为了区分横纵坐标。
            //所有横坐标相等的节点和纵坐标相等的节点都属于同一个联通分量。
            unionFind.union(stone[0] +10001, stone[1]);
        }
        //最多可以移除的石头的个数 = 所有石头的个数 - 联通分量的个数
        return stones.length-unionFind.getCount();
    }


    private class UnionFind{

        private int count;
        //key是节点自己，value是该节点的父节点
        private Map<Integer,Integer> parent;

        public UnionFind(){
            parent = new HashMap<>();
            count = 0;
        }

        /**
         * 找到某个节点的父节点
         */
        public int find(int x){
            if(!parent.containsKey(x)){
                parent.put(x,x);
                count++;
            }
            if(x!=parent.get(x)){
                //如果x节点不是顶点，更新其父节点
                parent.put(x,find(parent.get(x)));
            }
            return parent.get(x);
        }


        /**
         * 将两个节点所在的树合并
         */
        public void union(int x,int y){
            //分别找到两个节点的父节点
            int rootX = find(x);
            int rootY = find(y);
            //两个节点已经联通了
            if(rootX == rootY){
                return;
            }
            //合并两个联通分量
            parent.put(rootX,rootY);
            count--;
        }

        public int getCount() {
            return count;
        }

    }

}
