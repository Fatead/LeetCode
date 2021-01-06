package UnionFind;

import java.util.*;

public class MergeAccount {


    public class UnionFind{

        private int[] parent;

        public UnionFind(){
            parent = new int[10001];
            for(int i = 0;i<10001;i++){
                parent[i] = i;
            }
        }

        public void union(int x,int y){
            parent[find(x)] = find(y);
        }


        public int find(int x){
            if(parent[x]!=x)parent[x] = find(parent[x]);
            return parent[x];
        }

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind();
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();
        int id = 0;
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                unionFind.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> ans = new HashMap();
        for (String email: emailToName.keySet()) {
            int index = unionFind.find(emailToID.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
        }
        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }

    public static void main(String[] args) {
        List<List<String>> account = new ArrayList<>();
        String[] array1 = {"John", "johnsmith@mail.com", "john00@mail.com"};
        String[] array2 = {"John", "johnnybravo@mail.com"};
        String[] array3 = {"John", "johnsmith@mail.com", "john_newyork@mail.com"};
        String[] array4 = {"Mary", "mary@mail.com"};
        account.add(Arrays.asList(array1));
        account.add(Arrays.asList(array2));
        account.add(Arrays.asList(array3));
        account.add(Arrays.asList(array4));
    }
}
