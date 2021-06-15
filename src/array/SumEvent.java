package array;
import java.util.Arrays;

public class SumEvent {

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] result = new int[queries.length];
        int sum = 0;
        for (int num : nums) {
            if(num%2 == 0){
                sum += num;
            }
        }
        for (int i = 0; i <queries.length ; i++) {
            if(nums[queries[i][1]]%2 == 0 && queries[i][0]%2==0){
                sum += queries[i][0];
            }else if(nums[queries[i][1]]%2 == 0 && Math.abs(queries[i][0]%2)==1){
                sum -= nums[queries[i][1]];
            }else if(Math.abs(nums[queries[i][1]]%2) == 1 && Math.abs(queries[i][0]%2)==1){
                sum += nums[queries[i][1]] + queries[i][0];
            }
            nums[queries[i][1]] += queries[i][0];
            result[i] = sum;
        }
        return result;
    }

    public static void main(String[] args) {
        SumEvent sumEvent = new SumEvent();
        int[] nums = {1,2,3,4};
        int[][] query = {
                {},
        };
        System.out.println(Arrays.toString(sumEvent.sumEvenAfterQueries(nums, query)));
    }

}