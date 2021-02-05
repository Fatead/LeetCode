package array;

public class FindNumberIn2DArray {

    private boolean binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target)return true;
            else if(nums[mid]>target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return false;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)return false;
        for (int i = 0; i <matrix.length ; i++) {
            if(matrix[i][0]>target)return false;
            if(binarySearch(matrix[i],target))return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FindNumberIn2DArray findNumberIn2DArray = new FindNumberIn2DArray();
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        System.out.println(findNumberIn2DArray.findNumberIn2DArray(matrix,19));
    }

}