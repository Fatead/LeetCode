package array;

import java.util.*;

public class CountSmaller {


    /**
     * 暴力解法，超时
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] counts = new int[nums.length];
        for(int i = 0;i<nums.length;i++){
            int count = 0;
            for(int j = i;j<nums.length;j++){
                if(nums[j]<nums[i])count++;
            }
            counts[i] = count;
        }
        List<Integer> result = new ArrayList<>();
        for(int i:counts){
            result.add(i);
        }
        return result;
    }

    public List<Integer> countSmaller2(int[] nums){
        int[] counts = new int[nums.length];
        LinkedList<Integer> orderedList = new LinkedList<>();
        for(int i = nums.length-1;i>=0;i--){
            int pos = orderedList.size();
            counts[i] = orderedList.size();
            int left = 0;
            int right = orderedList.size()-1;
            while(left<=right){
                int mid = (right + left +1)/2;
                if(orderedList.get(mid)<nums[i]) {
                    left = mid + 1;
                }else {
                    right = mid -1;
                }
            }
            pos = left;
            counts[i] = pos;
            orderedList.add(pos,nums[i]);
            //System.out.println(orderedList);
        }
        List<Integer> result = new ArrayList<>();
        for(int i:counts){
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        CountSmaller countSmaller = new CountSmaller();
        int[] nums = {};
        List<Integer> result = countSmaller.countSmaller2(nums);
        System.out.println(result);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
    }

}
