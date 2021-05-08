package array;

public class MinDeleteSize {

    public int minDeletionSize(String[] strs) {
        int num = 0;
        char[][] matrix = new char[strs.length][strs[0].length()];
        for (int i = 0; i <strs.length ; i++) {
            for (int j = 0; j <strs[0].length() ; j++) {
                matrix[i][j] = strs[i].charAt(j);
            }
        }
        for (int i = 0; i <matrix[0].length ; i++) {
            for (int j = 0; j <matrix.length-1 ; j++) {
                if(matrix[j][i]>matrix[j+1][i]){
                    num++;
                    break;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        MinDeleteSize minDeleteSize = new MinDeleteSize();
        String[] strings = {"a","b"};
        System.out.println(minDeleteSize.minDeletionSize(strings));
    }

}
