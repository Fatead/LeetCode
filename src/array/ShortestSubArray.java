package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestSubArray {



    private int getLength(int[] nums,int num){
        int leftPos = Integer.MAX_VALUE;
        int rightPos = 0;
        for(int i= 0;i<nums.length;i++){
            if(nums[i] == num){
                leftPos = Math.min(leftPos,i);
                rightPos = Math.max(rightPos,i);
            }
        }
        return rightPos-leftPos + 1;
    }


    public int findShortestSubArray(int[] nums) {
        if(nums.length == 0)return 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0) + 1);
        }
        int max = 0;
        for(int i:map.values()){
            max = Math.max(max,i);
        }
        List<Integer> maxElementList = new ArrayList<>();
        for(int i:map.keySet()){
            if(map.get(i) == max){
                maxElementList.add(i);
            }
        }
        int minLength = Integer.MAX_VALUE;
        for(int i:maxElementList){
            minLength = Math.min(minLength,getLength(nums,i));
        }
        return minLength;
    }

    public static void main(String[] args) {
        ShortestSubArray shortestSubArray = new ShortestSubArray();
        int[] nums = {};
        System.out.println(shortestSubArray.findShortestSubArray(nums));
    }

}
