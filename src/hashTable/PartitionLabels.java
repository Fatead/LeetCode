package hashTable;

import java.util.*;

public class PartitionLabels {

    static class MyEntry<K,V> implements Map.Entry<K,V>{

        K key;
        V value;

        public MyEntry(K key,V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            V olderValue = this.value;
            this.value = v;
            return olderValue;
        }
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if(S == null||S.equalsIgnoreCase(""))return result;
        Map<Character,MyEntry<Integer,Integer>> map = new HashMap<>();
        for(int i = 0;i<S.length();i++){
            if(!map.containsKey(S.charAt(i))){
                MyEntry<Integer,Integer> entry = new MyEntry<>(i,i);
                map.put(S.charAt(i),entry);
            }else {
                MyEntry<Integer,Integer> entry = map.get(S.charAt(i));
                entry.setValue(i);
                map.put(S.charAt(i),entry);
            }
        }
        int[][] array = new int[map.size()][2];
        int count = 0;
        for(MyEntry entry:map.values()){
            array[count][0] = (int) entry.getKey();
            array[count][1] = (int) entry.getValue();
            count++;
        }
        Arrays.sort(array, Comparator.comparingInt(ints -> ints[0]));
        int left = 0;
        int right = 0;
        for(int i = 0;i<array.length;i++){
            if(array[i][0]>right){
                result.add(right-left+1);
                left = array[i][0];
                right = array[i][1];
            }else if(array[i][1]>right){
                right = array[i][1];
            }
        }
        result.add(right-left+1);
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
        System.out.println(partitionLabels.partitionLabels(""));
    }

}