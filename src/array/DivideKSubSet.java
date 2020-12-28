package array;
import java.util.Arrays;

/**
 * 使用dfs + 回溯的算法，使用k个桶来存放数据。
 */
public class DivideKSubSet {


    public boolean search(int[] groups,int row,int[] nums,int target){
        if(row<0)return true;
        int v = nums[row--];
        for(int i = 0;i<groups.length;i++){
            if(groups[i] + v<=target){
                groups[i]+=v;
                if(search(groups,row,nums,target))return true;
                groups[i]-=v;
            }
            if(groups[i] == 0)break;
        }
        return false;
    }


    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%k!=0)return false;
        int subSum = sum/k;
        Arrays.sort(nums);
        int len = nums.length-1;
        if(nums[len] > subSum)return false;
        while(len>=0 && nums[len] == subSum){
            len--;
            k--;
        }
        return search(new int[k],len,nums,subSum);
    }

    public static void main(String[] args) {
        DivideKSubSet divideKSubSet = new DivideKSubSet();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        boolean flag = divideKSubSet.canPartitionKSubsets(nums,4);
        System.out.println(flag);
    }

}