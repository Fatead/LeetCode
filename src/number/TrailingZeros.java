package number;

public class TrailingZeros {



    public int trailingZeroes(int n) {

        int zeroCount = 0;
        long currentMul = 5;
        while(n>=currentMul){
            zeroCount += (n/currentMul);
            currentMul*=5;
        }
        return zeroCount;
    }

    public static void main(String[] args) {
        TrailingZeros trailingZeros = new TrailingZeros();
        int result =  trailingZeros.trailingZeroes(379);
        System.out.println(result);
    }
}
