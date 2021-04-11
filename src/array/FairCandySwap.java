package array;

import java.util.Arrays;

public class FairCandySwap {

    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result = new int[2];
        int sumA = 0,sumB = 0;
        for (int i : A) {
            sumA+=i;
        }
        for (int i : B) {
            sumB+=i;
        }
        int avg = (sumA+sumB)/2;
        for (int i = 0; i <A.length ; i++) {
            for (int j = 0; j <B.length ; j++) {
                if(sumA-A[i] +B[j] == avg){
                    result[0] = A[i];
                    result[1] = B[j];
                    return result;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] A = {1,2,5};
        int[] B = {2,4};
        FairCandySwap fairCandySwap = new FairCandySwap();
        System.out.println(Arrays.toString(fairCandySwap.fairCandySwap(A, B)));
    }
}
