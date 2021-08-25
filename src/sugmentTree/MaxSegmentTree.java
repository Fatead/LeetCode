package sugmentTree;

import java.util.ArrayList;

public class MaxSegmentTree {

    private ArrayList<Integer> maxSegmentTree;
    private int n;

    public MaxSegmentTree(int[] arr) {
        n = arr.length;
        maxSegmentTree = new ArrayList<>(2 * n);
        for  (int i = 0; i < n; i++) {
            maxSegmentTree.add(0);
        }
        for (int i = 0; i < n; i++) {
            maxSegmentTree.add(arr[i]);
        }
        for (int i = n - 1; i > 0; i--) {
            maxSegmentTree.set(i, Math.max(maxSegmentTree.get(2 * i), maxSegmentTree.get(2 * i + 1)));
        }
    }

    /**
     * Get the minimum of range [left, right)
     */
    public int maxNum(int left, int right) {
        left += n;
        right += n;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            if ((left & 1) == 1) {
                max = Math.max(max, maxSegmentTree.get(left));
                left++;
            }
            if ((right & 1) == 1) {
                right--;
                max = Math.max(max,  maxSegmentTree.get(right));
            }
            left >>= 1;
            right >>= 1;
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {11,21,31,41,51,61,71};
        MaxSegmentTree maxSegmentTree = new MaxSegmentTree(nums);
        System.out.println(maxSegmentTree.maxNum(2,4));
    }
    
}