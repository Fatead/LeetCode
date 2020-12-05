package array;

public class RangeSum {

    public int maxCount(int m, int n, int[][] ops) {
        if(ops.length == 0||ops[0].length == 0 )return m*n;
        int minWidth = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;
        for(int i = 0;i<ops.length;i++){
            minLength = Math.min(minLength,ops[i][0]);
            minWidth = Math.min(minWidth,ops[i][1]);
        }
        return minLength*minWidth;
    }

    public static void main(String[] args) {
        RangeSum rangeSum = new RangeSum();
    }

}
