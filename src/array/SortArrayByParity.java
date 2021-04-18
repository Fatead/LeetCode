package array;

import java.util.Arrays;

public class SortArrayByParity {

    private void swap(int[] A,int i,int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length-1;
        while (left<right){
            while(left<A.length && A[left]%2==0){
                left++;
            }
            while(right>=0 && A[right]%2==1){
                right--;
            }
            if(left>=right)break;
            swap(A,left,right);
        }
        return A;
    }

    public static void main(String[] args) {
        SortArrayByParity sortArrayByParity = new SortArrayByParity();
        int[] nums = {3,1,2,4};
        System.out.println(Arrays.toString(sortArrayByParity.sortArrayByParity(nums)));
    }
}
