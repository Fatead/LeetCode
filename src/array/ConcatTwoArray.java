package array;

import java.util.Arrays;

public class ConcatTwoArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)return;
        if(m == 0){
            for(int i = 0;i<n;i++){
                nums1[i] = nums2[i];
            }
            return;
        }
        int pos1 = 0,pos2 = 0,pos = 0;
        int[] nums = new int[m+n];
        while(pos1<m&&pos2<n){
            if(nums1[pos1]<nums2[pos2]){
                nums[pos++] = nums1[pos1++];
            }else {
                nums[pos++] = nums2[pos2++];
            }
        }
        while(pos1<m){
            nums[pos++] = nums1[pos1++];
        }
        while(pos2<n){
            nums[pos++] = nums2[pos2++];
        }
        for(int i = 0;i<nums.length;i++){
            nums1[i] = nums[i];
        }
    }

    public static void main(String[] args) {
        int[] num1 = {4,0,0,0,0,0};
        int[] num2 = {1,2,3,5,6};
        ConcatTwoArray concatTwoArray = new ConcatTwoArray();
        concatTwoArray.merge(num1,1,num2,5);
        System.out.println(Arrays.toString(num1));

    }
}
