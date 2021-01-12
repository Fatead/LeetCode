package DFS;

public class TargetSum {


    private int num = 0;

    private void dfs(int[] nums,int pos,int S,int total){
        if(pos==nums.length){
            if(S == total)
                num++;
            return;
        }
        dfs(nums,pos+1,S,total + nums[pos]);
        dfs(nums,pos+1,S,total - nums[pos]);
    }

    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums,0,S,0);
        return num;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(nums,1));
    }

}
