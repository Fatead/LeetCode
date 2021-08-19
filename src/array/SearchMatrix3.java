package array;

public class SearchMatrix3 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int targetLine = matrix.length-1;
        for (int i = 0; i <matrix.length-1 ; i++) {
            if(matrix[i][0]<=target && matrix[i+1][0]>target){
                targetLine = i;
            }
        }
        for (int i = 0; i <matrix[0].length ; i++) {
            if(matrix[targetLine][i] == target)return true;
        }
        return false;
    }

    private int searchRow(int[][] matrix, int target){
        int left = 0, right = matrix.length-1;
        int mid = 0;
        while (left<right){
            mid = left  + (right-left+1)/2;
            if(matrix[mid][0]>target){
                right = mid-1;
            }else {
                left = mid;
            }
        }
        return left;
    }

    private boolean searchColumn(int[][] matrix, int target, int line){
        int left = 0, right = matrix[0].length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(matrix[line][mid] == target){
                return true;
            }else if(matrix[line][mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return false;
    }


    public boolean searchMatrix2(int[][] matrix, int target){
        int line = searchRow(matrix,target);
        if(line<0)return false;
        return searchColumn(matrix,target,line);
    }

    public static void main(String[] args) {
        SearchMatrix3 searchMatrix3 = new SearchMatrix3();
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        System.out.println(searchMatrix3.searchMatrix2(matrix,60));
    }

}