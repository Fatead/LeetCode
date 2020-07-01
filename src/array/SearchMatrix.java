package array;

public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0)return false;
        int n = matrix[0].length;
        if(n == 0)return false;
        if(matrix[0][0] > target)return false;
        int row = m-1;
        for(int i = 0;i<m;i++){
            if(matrix[i][0] == target)return true;
            else if(matrix[i][0] > target){
                row = i-1;
                break;
            }
        }
        for(int i = 0;i<n;i++){
            if(matrix[row][i] == target)return true;
            else if(matrix[row][i] > target)return false;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {}
        };
        SearchMatrix searchMatrix = new SearchMatrix();
        boolean result = searchMatrix.searchMatrix(matrix,13);
        System.out.println(result);
    }
}
