package number;

public class SmallestRangeI {

    public int smallestRangeI(int[] A, int K) {
        if(A.length<=1)return 0;
        int small = Integer.MAX_VALUE;
        int large = Integer.MIN_VALUE;
        for (int i = 0; i <A.length ; i++) {
            small = Math.min(small,A[i]);
            large = Math.max(large,A[i]);
        }
        if(large-small<2*K){
            return 0;
        }else {
            return large-small-2*K;
        }
    }



    public static void main(String[] args) {
        SmallestRangeI smallestRangeI = new SmallestRangeI();
        int[] A = {2,7,2};
        System.out.println(smallestRangeI.smallestRangeI(A,1));
    }

}
