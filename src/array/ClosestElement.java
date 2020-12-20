package array;

import java.util.ArrayList;
import java.util.List;

public class ClosestElement {


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos = 0;
        for(int i = 0;i<arr.length;i++){
            if(arr[i]>x){
                pos = i;
                break;
            }
            if(i == arr.length-1){
                pos = arr.length-1;
            }
        }
        List<Integer> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = pos-k-1;i<pos+k+1;i++){
            if(i<0 || i>arr.length-k)continue;
            int sum = 0;
            for(int j = i;j<i+k;j++){
                sum += Math.abs(arr[j]-x);
            }
            if(sum<min){
                min = sum;
                ans = i;
            }
        }
        for(int i = ans;i<ans+k;i++){
            result.add(arr[i]);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ClosestElement closestElement = new ClosestElement();
        System.out.println(closestElement.findClosestElements(nums,4,5));
    }
}
