package UnionFind;
import java.util.*;

public class SmallestStringWithSwap {

    public class UnionFind{

        int[] parents;

        public UnionFind(int n){
            parents = new int[n];
            for (int i = 0; i <n ; i++) {
                parents[i] = i;
            }
        }

        public int find(int x){
            while (x!=parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                parents[rootX] = rootY;
            }
        }

        public boolean isConnect(int x,int y){
            return find(x) == find(y);
        }

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs.size() == 0)return s;
        UnionFind unionFind = new UnionFind(s.length());
        //将可以交换的节点输入并查集
        for (List<Integer> pair : pairs) {
            unionFind.union(pair.get(0),pair.get(1));
        }
        //构建映射关系
        char[] charArray = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>(s.length());
        //将同一个联通分量的字符集合保存在一个优先队列中
        for (int i = 0; i < s.length(); i++) {
            int root = unionFind.find(i);
            //进行分组排序
            map.computeIfAbsent(root,key->new PriorityQueue<>()).offer(charArray[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(map.get(root).poll());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        SmallestStringWithSwap smallestStringWithSwap = new SmallestStringWithSwap();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0,3));
        list.add(Arrays.asList(1,2));
        System.out.println(smallestStringWithSwap.smallestStringWithSwaps("dcab",list));
    }

}