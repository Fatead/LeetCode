package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourNum {


    public List<List<Integer>> threeSum(int[] nums,int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null||nums.length<3)return result;
        Arrays.sort(nums);
        for(int i =0;i<nums.length;i++){
            if(i>0&&nums[i] == nums[i-1])continue;
            int left = i+1;
            int right = nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum ==target){
                    List<Integer> tolist1=new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right]));
                    result.add(tolist1);
                    while(left<right&&nums[left+1] == nums[left])left++;
                    while(left<right&&nums[right-1] == nums[right])right--;
                    left++;
                    right--;
                }
                else if(sum<target)left++;
                else right--;
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null||nums.length<4)return result;
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-3;i++){
            if(i>0&&nums[i] == nums[i-1])continue;
            List<List<Integer>> threeList = threeSum(Arrays.copyOfRange(nums,i+1,nums.length),target-nums[i]);
            for(List<Integer> list:threeList){
                list.add(nums[i]);
            }
            result.addAll(threeList);
        }
        return result;
    }

    public static void main(String[] args) {
        FourNum fourNum = new FourNum();
        int[] array = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> result = fourNum.fourSum(array,0);
        System.out.println(result);
    }

}
