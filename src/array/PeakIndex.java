package array;

public class PeakIndex {

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(mid==0)mid++;
            if(mid == arr.length-1)mid--;
            if(arr[mid]>arr[mid-1]&&arr[mid]>arr[mid+1]){
                return mid;
            }else if(arr[mid]<arr[mid-1]){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PeakIndex peakIndex = new PeakIndex();
        int[] array = {3,5,3,2,0};
        System.out.println(peakIndex.peakIndexInMountainArray(array));
    }

}
