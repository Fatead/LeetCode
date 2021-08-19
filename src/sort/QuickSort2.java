package sort;
import java.util.Arrays;

public class QuickSort2 {

    private void swap(int[] nums , int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int partition(int[] nums, int left, int right){
        int value = nums[left];
        int i = left, j = right+1;
        while (true){
            while (++i<right && nums[i]<value);
            while (--j>left && nums[j]>value);
            if(i>=j)break;
            swap(nums,i,j);
        }
        nums[left] = nums[j];
        nums[j] = value;
        return j;
    }

    public void sort(int[] nums, int left, int right){
        if(left>=right)return;
        int pos = partition(nums,left,right);
        sort(nums,left,pos-1);
        sort(nums,pos+1,right);
    }

    public int[] quickSort(int[] nums){
        sort(nums,0,nums.length-1);
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {9,4,6,7,6,3,1,8,0};
        QuickSort2 quickSort = new QuickSort2();
        int[] result = quickSort.quickSort(nums);
        System.out.println(Arrays.toString(result));
    }

}