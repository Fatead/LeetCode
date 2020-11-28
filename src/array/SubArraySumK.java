package array;

public class SubArraySumK {

    private int sumK(int[] nums,int k,int pos){
        int sum = 0;
        int count = 0;
        for(int i = pos;i<nums.length;i++){
            sum += nums[i];
            if(sum == k)count++;
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        int num = 0;
        for(int i= 0;i<nums.length;i++){
            //从i开始，数组的和为k
            num += sumK(nums,k,i);
        }
        return num;
    }

    public static void main(String[] args) {
        SubArraySumK subArraySumK = new SubArraySumK();
        int[] nums = {1,1,1};
        System.out.println(subArraySumK.subarraySum(nums,3));
    }

}
