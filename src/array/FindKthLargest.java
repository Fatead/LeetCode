package array;

import java.util.Random;

/**
 * 基于快速选择的思想找到第K大的元素
 */
public class FindKthLargest {

    Random random = new Random();

    private void swap(int[] nums ,int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int randomPartition(int[] nums, int left, int right){
        //取得left 和 right 中间的一个随机值
        int index = random.nextInt(right - left + 1) + left;
        swap(nums, index, right);
        return partition(nums,left,right);
    }

    private int partition(int[] nums, int left ,int right){
        int pivot = nums[right];
        int i = left-1;
        for(int j = left;j<right;j++){
            if(nums[j]<pivot){
                swap(nums,++i,j);
            }
        }
        swap(nums,i+1,right);
        return i+1;
    }

    private int quickSelect(int[] nums, int left, int right, int index){
        int pivot = randomPartition(nums,left,right);
        if(pivot == index){
            return nums[index];
        }else {
            return pivot<index?quickSelect(nums, pivot+1, right,index):quickSelect(nums,left,pivot-1,index);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }

    public static void main(String[] args) {
        FindKthLargest findKthLargest = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest.findKthLargest(nums,2));
    }

}