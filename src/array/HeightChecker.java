package array;

import java.util.Arrays;

public class HeightChecker {

    public int heightChecker(int[] heights) {
        int[] nums = Arrays.copyOfRange(heights,0,heights.length);
        Arrays.sort(heights);
        int count = 0;
        for (int i = 0; i <heights.length ; i++) {
            if(heights[i]!=nums[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        HeightChecker heightChecker = new HeightChecker();
        int[] height = {1,1,4,2,1,3};
        System.out.println(heightChecker.heightChecker(height));
    }

}
