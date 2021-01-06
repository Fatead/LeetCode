package UnionFind;

import java.util.*;

/**
 * 思路有些问题，不能保证list中的元素已经完全合并
 */
public class MergeAccount2 {


    public class MyEntry<K,V> implements Map.Entry<K,V>{

        K key;

        V value;

        public MyEntry(K key,V value){
            this.key = key;
            this.value = value;
        }

        public MyEntry(MyEntry originalEntry){
            this.value = (V) originalEntry.value;
            this.key = (K) originalEntry.key;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = this.value;
            this.value = v;
            return oldValue;
        }

    }


    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<MyEntry<String, Set<String>>> list = new ArrayList<>();
        for(List<String> account:accounts){
            String name = account.get(0);
            Set<String> mailSet = new HashSet<>();
            for(int i = 1;i<account.size();i++){
                mailSet.add(account.get(i));
            }
            MyEntry<String,Set<String>> myEntry = new MyEntry<>(name, mailSet);
            boolean flag = false;
            int index = 0;
            for(int i = 0;i<list.size();i++){
                MyEntry<String,Set<String>> originalEntry = list.get(i);
                Set<String> testSet = new HashSet<>(originalEntry.getValue());
                testSet.retainAll(myEntry.getValue());
                if(testSet.size()!=0){
                    flag = true;
                    index = i;
                    break;
                }
            }
            if(flag){
                list.get(index).getValue().addAll(mailSet);
            }else {
                list.add(myEntry);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(MyEntry<String,Set<String>> myEntry:list){
            List<String> mailList = new ArrayList<>(myEntry.getValue());
            mailList.sort((a, b) -> {
                int len = Math.min(a.length(), b.length());
                for (int i = 0; i < len; ++i) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return a.charAt(i) - b.charAt(i);
                    }
                }
                return a.length() - b.length();
            });
            mailList.add(0,myEntry.key);
            result.add(mailList);
        }
        return result;
    }

    public static void main(String[] args) {
        MergeAccount2 mergeAccount2 = new MergeAccount2();
        List<List<String>> account = new ArrayList<>();
        String[] array1 = {"John", "johnsmith@mail.com", "john00@mail.com"};
        String[] array2 = {"John", "johnnybravo@mail.com"};
        String[] array3 = {"John", "johnsmith@mail.com", "john_newyork@mail.com"};
        String[] array4 = {"Mary", "mary@mail.com"};
        account.add(Arrays.asList(array1));
        account.add(Arrays.asList(array2));
        account.add(Arrays.asList(array3));
        account.add(Arrays.asList(array4));
        System.out.println(mergeAccount2.accountsMerge(account));
    }

}
