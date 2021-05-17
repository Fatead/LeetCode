package random;

import java.util.*;

public class RandomIndex {

    Map<Integer, List<Integer>> map = new HashMap<>();

    public RandomIndex(int[] nums){
        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(nums[i])){
                List<Integer> list = map.get(nums[i]);
                list.add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i],list);
            }
        }
    }

    public int pick(int target){
        List<Integer> list = map.get(target);
        Random random = new Random();
        int reserve = 0;
        int count = 0;
        for (Integer integer : list) {
            count++;
            int rand = random.nextInt(count) + 1;
            if(rand == count){
                reserve = integer;
            }
        }
        return reserve;
    }


    public static void main(String[] args) {
        RandomIndex randomIndex = new RandomIndex(new int[]{1,2,3,3,3});
        System.out.println(randomIndex.pick(3));
    }

}