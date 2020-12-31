package array;

import java.util.Arrays;

public class KthLargest {

    int[] array;
    int kPosition;

    public KthLargest(int k, int[] nums) {
        array = new int[10000];
        Arrays.fill(array,Integer.MIN_VALUE);
        for(int i = 0;i<nums.length;i++){
            array[i] = nums[i];
        }
        Arrays.sort(array);
        kPosition = k;
    }

    public int add(int val) {
        if(val<array[0])return array[array.length-kPosition];
        int pos = 0;
        while(pos+1<array.length && array[pos+1]<val){
            array[pos] = array[pos+1];
            pos++;
        }
        array[pos] = val;
        return array[array.length-kPosition];
    }


    public static void main(String[] args) {
        int[] nums = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3,nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

}
