package array;

public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int i:nums){
            sum += i;
        }
        int preSum = 0;
        for(int i = 0;i<nums.length;i++){
            if(i>0)preSum += nums[i-1];
            if(preSum*2 == sum-nums[i])return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex pivotIndex = new PivotIndex();
        int[] nums = {1, 7, 3, 6, 5, 6};
        int result = pivotIndex.pivotIndex(nums);
        System.out.println(result);
    }

}
