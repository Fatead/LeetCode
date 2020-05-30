package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNum {


    //超出时间限制
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null||nums.length<3)return result;
        Arrays.sort(nums);
        for(int i =0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                for(int k = j+1;k<nums.length;k++){
                    boolean addFlag = true;
                    if(nums[i]+nums[j]+nums[k] == 0){
                        int[] arr = {nums[i],nums[j],nums[k]};
                        List<Integer> list = new ArrayList<>();
                        for (int value : arr) {
                            list.add(value);
                        }
                        for(List test:result){
                            if(test.equals(list)){
                                addFlag =false;
                                break;
                            }
                        }
                        if(addFlag){
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null||nums.length<3)return result;
        Arrays.sort(nums);
        for(int i =0;i<nums.length;i++){
            if(nums[i]>0)break;
            if(i>0&&nums[i] == nums[i-1])continue;
            int left = i+1;
            int right = nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum ==0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left<right&&nums[left+1] == nums[left])left++;
                    while(left<right&&nums[right-1] == nums[right])right--;
                    left++;
                    right--;
                }
                else if(sum<0)left++;
                else right--;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        ThreeNum threeNum = new ThreeNum();
        System.out.println(threeNum.threeSum(nums).toString());
    }
}
