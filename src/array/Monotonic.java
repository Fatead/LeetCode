package array;

public class Monotonic {


    private boolean monotonicIncrease(int[] array){
        for (int i = 0; i <array.length-1 ; i++) {
            if(array[i]>array[i+1])return false;
        }
        return true;
    }

    private boolean monotonicDecrease(int[] array){
        for (int i = 0; i <array.length-1 ; i++) {
            if(array[i]<array[i+1])return false;
        }
        return true;
    }

    public boolean isMonotonic(int[] A) {
        return monotonicDecrease(A)||monotonicIncrease(A);
    }

    public static void main(String[] args) {
        Monotonic monotonic = new Monotonic();
        int[] array = {1,2,2,3};
        System.out.println(monotonic.isMonotonic(array));
    }

}
