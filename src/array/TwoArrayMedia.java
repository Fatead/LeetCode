package array;

public class TwoArrayMedia {

    public int findKth(int[] num1,int i,int[] num2,int j,int k){
        if(i>=num1.length)return num2[j+k-1];
        if(j>=num2.length)return num1[i+k-1];
        if(k==1){
            return Math.min(num1[i],num2[j]);
        }
        int mediaNum1 = ( i+k/2-1 < num1.length) ? num1[i+k/2-1]:Integer.MAX_VALUE;
        int mediaNum2 = ( j+k/2-1 < num2.length) ? num2[j+k/2-1]:Integer.MAX_VALUE;
        if(mediaNum1<mediaNum2){
            return findKth(num1,i+k/2,num2,j,k- k/2);
        }else {
            return findKth(num1,i,num2,j+k/2,k- k/2);
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m+n+1)/2;
        int right = (m+n+2)/2;
        return (findKth(nums1,0,nums2,0,left) + findKth(nums1,0,nums2,0,right))/2.0;
    }

    public static void main(String[] args) {
        TwoArrayMedia twoArrayMedia = new TwoArrayMedia();
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        System.out.println(twoArrayMedia.findMedianSortedArrays(num1,num2));
        System.out.println(twoArrayMedia.findKth(num1,0,num2,0,4));
    }
}
