package array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PartitionArray {

    boolean canPartition = false;

    private void dfs(int sum, List<Integer> list){
        if(sum == 0){
            canPartition = true;
        }else if(sum<0){
            return;
        }else {
            for(int i = 0;i<list.size();i++){
                if(i>0 && list.get(i).equals(list.get(i - 1)))continue;
                int num = list.remove(i);
                dfs(sum - num,new LinkedList<>(list));
                list.add(i,num);
            }
        }
    }

    /**
     * 暴力求解，超时
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%2 == 1)return false;
        sum/=2;
        List<Integer> list = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i++){
            list.add(nums[i]);
        }
        dfs(sum,list);
        return canPartition;
    }

    public boolean canPartition2(int[] nums){
        int sum = 0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%2 == 1)return false;
        sum/=2;
        boolean[][] dp = new boolean[nums.length][sum + 1];
        if(nums[0] < sum)
            dp[0][nums[0]] = true;
        for(int i = 1;i<nums.length;i++){
            for(int j = 0;j<=sum;j++){
                dp[i][j] = dp[i-1][j];
                if(nums[i] == j){
                    dp[i][j] = true;
                    continue;
                }
                if(nums[i] < j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
                }
            }
        }
        return dp[nums.length-1][sum];
    }

    public static void main(String[] args) {
        PartitionArray partitionArray = new PartitionArray();
        int[] nums = {6,6};
        System.out.println(partitionArray.canPartition2(nums));
    }
}
