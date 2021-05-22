package stack;

import java.util.Deque;
import java.util.LinkedList;

public class Find132Pattern {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find132Pattern find132Pattern = new Find132Pattern();
        int[] nums = {3,1,4,2};
        System.out.println(find132Pattern.find132pattern(nums));
    }

}