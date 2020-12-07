package number;

/**
 * Created by zero on 2019/7/31.
 */
public class TwoSum {

    public static void main(String[] args)
    {
        int[] arr = {2, 7, 11, 15};
        int[] result = twoSum(arr,9);
        for(int i =0;i<result.length;i++){
            System.out.println(result[i]);
        }

    }

    private static int[] twoSum(int[] nums, int target)
    {
        for(int i = 0;i<nums.length;i++)
        {
            for(int j = i+1;j<nums.length;j++)
            {
                if(nums[i]+nums[j] == target)
                {
                    int[] arr = new int[]{i,j};
                    return arr;
                }
            }
        }
        return null;
    }


}
