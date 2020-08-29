package array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthElement {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k-1];
    }

    public int findKthLargest2(int[] nums, int k){
        if(nums.length == 0)return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i:nums){
            heap.add(i);
            if(heap.size()>k){
                heap.poll();
            }
        }
        return heap.peek();
    }


    private void swap(int[] nums,int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


    private void maxHeap(int[] nums, int i, int heapSize){
        int left = i*2 + 1;
        int right = i*2 + 2;
        int max = i;
        if(left<heapSize&&nums[left] > nums[i]){
            max = left;
        }
        if(right<heapSize&&nums[right]>nums[i]){
            max = right;
        }
        if(max!=i){
            swap(nums,i,max);
            maxHeap(nums,max,heapSize);
        }
    }

    private void buildMaxHeap(int[] nums,int heapSize){
        for(int i = heapSize/2;i>=0;i--){
            maxHeap(nums,i,nums.length);
        }
    }



    public static void main(String[] args) {

    }

}
