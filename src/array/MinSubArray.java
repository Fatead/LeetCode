package array;


public class MinSubArray {

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<len;i++){
            int sum = 0;
            for(int j = i;j<len;j++){
                sum = sum + nums[j];
                if(sum>=s){
                    min = Math.min(min,j-i+1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE?0:min;
    }


    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int s= 15;
        MinSubArray minSubArray = new MinSubArray();
        int result =  minSubArray.minSubArrayLen(s,array);
        System.out.println(result);
    }
}
