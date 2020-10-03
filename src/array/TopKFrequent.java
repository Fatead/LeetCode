package array;

import java.util.*;

public class TopKFrequent {


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[k];
        for(int i:nums){
            int count =  map.getOrDefault(i,0);
            map.put(i,++count);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        int numCount = list.get(list.size()-k);
        int count = 0;
        for(int i:map.keySet()){
            if(map.get(i) >=numCount){
                result[count++] = i;
            }
        }
        return result;
    }

    public int[] topKFrequent2(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[k];
        for(int i:nums){
            int count =  map.getOrDefault(i,0);
            map.put(i,++count);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[1] - t1[1];
            }
        });
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int num = entry.getKey(),count = entry.getValue();
            if(queue.size() == k){
                if(queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }else {
                queue.offer(new int[]{num,count});
            }
        }
        for(int i = 0;i<k;i++){
            result[i] = queue.poll()[0];
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] nums = {-1,-1};
        int[] result =  topKFrequent.topKFrequent(nums,1);
        System.out.println(Arrays.toString(result));
    }

}
