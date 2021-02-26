package number;

import java.util.Arrays;

public class PrintNumbers {

    public int[] printNumbers(int n) {
        int[] result = new int[(int) Math.pow(10,n)-1];
        for (int i = 0; i < result.length; i++) {
            result[i] = i+1;
        }
        return result;
    }

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        System.out.println(Arrays.toString(printNumbers.printNumbers(1)));
    }

}
