package number;

import java.util.Arrays;

public class ReverseBit {


    private int[] convertIntBit(int n){
        int[] array = new int[Integer.SIZE];
        for(int i = 0;i<Integer.SIZE;i++){
            array[Integer.SIZE-i-1] = ((n>>i)&1);
        }
        return array;
    }

    public int reverseBits(int n) {
        int[] array = convertIntBit(n);
        int result = 0;
        int count = 1;
        for(int i = 0;i<array.length;i++){
            result += array[i]*count;
            count*=2;
        }
        return result;
    }


    public static void main(String[] args) {
        ReverseBit reverseBit = new ReverseBit();
        int result =  reverseBit.reverseBits(43261596);
        System.out.println(result);
    }
}
