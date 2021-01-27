package dp;

import java.util.Arrays;

public class LongestMountain {

    public int longestMountain(int[] arr) {
        if(arr.length<3)return 0;
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        for (int i = 0; i <arr.length-1 ; i++) {
            if(arr[i+1]>arr[i]){
                left[i+1] = left[i]+1;
            }
        }
        for (int i = arr.length-1; i >0 ; i--) {
            if(arr[i]<arr[i-1]){
                right[i-1] = right[i]+1;
            }
        }
        int len = 2;
        for(int i = 1;i<arr.length-1;i++){
            if(arr[i]>arr[i-1]&&arr[i]<arr[i+1]){
                len = Math.max(len,left[i] + right[i] + 1);
            }
        }
        if(len>=3){
            return len;
        }
        return 0;
    }

    public static void main(String[] args) {
        LongestMountain longestMountain = new LongestMountain();
        int[] array = {2,1,4,7,3,2,5};
        System.out.println(longestMountain.longestMountain(array));
    }

}
