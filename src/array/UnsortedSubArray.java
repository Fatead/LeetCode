package array;

import java.util.Arrays;

/**
 * 从原长度的数组开始，要么向左搜索，要么向右搜索
 */
public class UnsortedSubArray {

    private int minLength = Integer.MAX_VALUE;

    private boolean continueSubArray(int[] nums,int begin,int end){
        int subMin = Integer.MAX_VALUE;
        int subMax = Integer.MIN_VALUE;
        for(int i = begin;i<end;i++){
            if(nums[i] > subMax)subMax = nums[i];
            if(nums[i] < subMin)subMin = nums[i];
        }
        for(int i = 0;i<begin;i++){
            if(nums[i]>subMin)return false;
        }
        for(int i = end;i<nums.length;i++){
            if(nums[i]<subMax)return false;
        }
        return true;
    }


    private void dfs(int[] nums,int begin,int end){
        if(begin>=end)return;
        if(continueSubArray(nums,begin,end)){
            if(end-begin<minLength)minLength = end-begin;
            dfs(nums,begin+1,end);
            dfs(nums,begin,end-1);
        }
    }

    private boolean continueArray(int[] nums){
        if(nums.length<=1)return true;
        for(int i = 0;i<nums.length-1;i++){
            if(nums[i+1] < nums[i])return false;
        }
        return true;
    }

    public int findUnsortedSubarray(int[] nums) {
        if(continueArray(nums))return 0;
        dfs(nums,0,nums.length);
        if(minLength == 1)minLength = 0;
        return minLength;
    }


    public int findUnsortedSubarray2(int[] nums) {
        int[] originalNum = nums.clone();
        Arrays.sort(nums);
        int start = nums.length;
        int end = 0;
        for(int i = 0;i<nums.length;i++){
            if(originalNum[i]!=nums[i]){
                start = Math.min(i,start);
                end = Math.max(i,end);
            }
        }
        return (end - start >=0 ? (end-start+1):0 );
    }

    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        UnsortedSubArray unsortedSubArray = new UnsortedSubArray();
        int result = unsortedSubArray.findUnsortedSubarray(array);
        System.out.println(result);
    }

}
