package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeastNumbers {

    /**
     * 使用最大堆帮助找出最小的K个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((integer, t1) -> -Integer.compare(integer,t1));
        for (int i = 0; i <arr.length ; i++) {
            if(queue.size()<k){
                queue.add(arr[i]);
            }else {
                if(!queue.isEmpty() && queue.peek()>arr[i]){
                    queue.poll();
                    queue.add(arr[i]);
                }
            }
        }
        int[] result = new int[k];
        int count = 0;
        while (!queue.isEmpty()){
            result[count++] = queue.poll();
        }
        return result;
    }


    /**
     * 基于快排的思想找出数组的分界点K
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if(k == 0 || arr.length == 0){
            return new int[0];
        }
        return quickSort(arr,0,arr.length-1,k-1);
    }

    private int[] quickSort(int[] nums,int left,int right,int k){
        int j = partition(nums,left,right);
        if(j==k)return Arrays.copyOf(nums,j+1);
        return j > k ? quickSort(nums,left,j-1,k) : quickSort(nums,j+1,right,k);
    }


    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums,int left,int right){
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

    public static void main(String[] args) {
        LeastNumbers leastNumbers = new LeastNumbers();
        int[] nums = {0,0,0,2,0,5};
        int[] result = leastNumbers.getLeastNumbers(nums,0);
        System.out.println(Arrays.toString(result));
    }

}
