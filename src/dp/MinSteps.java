package dp;

import java.util.Arrays;

public class MinSteps {

    public int minSteps(int n) {
        if(n <= 1)return 0;
        if(n == 2)return 2;
        if(n == 3)return 3;
        int result = n;
        for(int i = 2;i<n/2;i++){
            if(n%i == 0){
                result = Math.min(result,minSteps(n/i) + i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        MinSteps minSteps = new MinSteps();
        System.out.println(minSteps.minSteps(545));
    }
}
