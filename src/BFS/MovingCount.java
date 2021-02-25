package BFS;

import java.util.*;

public class MovingCount {

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


    private boolean validPos(int posX,int posY,int k){
        List<Integer> list = new ArrayList<>();
        while (posX>0){
            list.add(posX%10);
            posX/=10;
        }
        while (posY>0){
            list.add(posY%10);
            posY/=10;
        }
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
            if(sum>k)return false;
        }
        return true;
    }

    public int movingCount(int m, int n, int k) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        Queue<MyEntry<Integer,Integer>> queue = new LinkedList<>();
        queue.add(new MyEntry(0,0));
        while (!queue.isEmpty()){
            MyEntry<Integer,Integer> pos = queue.poll();
            count++;
            if(pos.getKey()>0 && !set.contains((pos.getKey()-1)*100+pos.getValue()) && validPos(pos.getKey()-1,pos.getValue(),k)){
                queue.add(new MyEntry<>(pos.getKey()-1,pos.getValue()));
                set.add((pos.getKey()-1)*100+pos.getValue());
            }
            if(pos.getKey()+1<m && !set.contains((pos.getKey()+1)*100+pos.getValue()) && validPos(pos.getKey()+1,pos.getValue(),k)){
                queue.add(new MyEntry<>(pos.getKey()+1,pos.getValue()));
                set.add((pos.getKey()+1)*100+pos.getValue());
            }
            if(pos.getValue()>0 && !set.contains((pos.getKey())*100+pos.getValue()-1) && validPos(pos.getKey(),pos.getValue()-1,k)){
                queue.add(new MyEntry<>(pos.getKey(),pos.getValue()-1));
                set.add((pos.getKey())*100+pos.getValue()-1);
            }
            if(pos.getValue()+1<n && !set.contains((pos.getKey())*100+pos.getValue()+1) && validPos(pos.getKey(),pos.getValue()+1,k)){
                queue.add(new MyEntry<>(pos.getKey(),pos.getValue()+1));
                set.add((pos.getKey())*100+pos.getValue()+1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MovingCount movingCount = new MovingCount();
        System.out.println(movingCount.movingCount(2,3,1));
    }

}
