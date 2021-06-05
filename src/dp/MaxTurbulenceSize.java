package dp;

import java.util.Arrays;

public class MaxTurbulenceSize {

    public int maxTurbulenceSize(int[] arr) {
        int[] dpOdd = new int[arr.length];
        int[] dpEven = new int[arr.length];
        Arrays.fill(dpOdd,1);
        Arrays.fill(dpEven,1);
        for (int i = 0; i <dpOdd.length-1 ; i++) {
            if(i%2==1 && arr[i]>arr[i+1]){
                dpOdd[i+1] = dpOdd[i] + 1;
            }
            if(i%2==0 && arr[i]<arr[i+1]){
                dpOdd[i+1] = dpOdd[i] + 1;
            }
        }
        for (int i = 0; i <dpEven.length-1 ; i++) {
            if(i%2==0 && arr[i]>arr[i+1]){
                dpEven[i+1] = dpEven[i] + 1;
            }
            if(i%2==1 && arr[i]<arr[i+1]){
                dpEven[i+1] = dpEven[i] + 1;
            }
        }
        int max = 0;
        for (int i : dpEven) {
            max = Math.max(max,i);
        }
        for (int i : dpOdd) {
            max = Math.max(max,i);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxTurbulenceSize maxTurbulenceSize = new MaxTurbulenceSize();
        int[] arr = {9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulenceSize.maxTurbulenceSize(arr));
    }

}
