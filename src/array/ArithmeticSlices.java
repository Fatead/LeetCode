package array;

import java.util.Arrays;

public class ArithmeticSlices {


    private int getNum(int n){
        int result = 0;
        for(int i = 1;i<n;i++){
            result += i;
        }
        return result;
    }

    public int numberOfArithmeticSlices(int[] A) {
        if(A.length <3)return 0;
        int[] array = new int[A.length];
        for(int i = 0;i<A.length-1;i++){
            array[i] = A[i+1] - A[i];
        }
        int num = 1;
        int result = 0;
        for(int i = 0;i<array.length-2;i++){
            if(array[i] == array[i+1]){
                num += 1;
            }else {
                result += getNum(num);
                num = 1;
            }
        }
        if(num>1)result+=getNum(num);
        return result;
    }


    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        int[] array = {1,2,3,4};
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(array));
    }
}
