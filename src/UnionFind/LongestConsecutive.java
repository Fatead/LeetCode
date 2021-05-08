package UnionFind;
import java.util.Arrays;

public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if(nums.length==0)return 0;
        int longest = 0;
        int currLength = 1;
        Arrays.sort(nums);
        for (int i = 0; i <nums.length-1 ; i++) {
            if(nums[i] + 1 == nums[i+1]){
                currLength++;
            }else if(nums[i] != nums[i+1]){
                longest = Math.max(longest,currLength);
                currLength = 1;
            }
        }
        longest = Math.max(longest,currLength);
        return longest;
    }

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int[] nums = {
                1,2,0,1
        };
        System.out.println(longestConsecutive.longestConsecutive(nums));
    }

}
