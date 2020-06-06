package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullSort {


    List<List<Integer>> fullSortList = new ArrayList<>();

    public void swap(int[] nums,int pos1,int pos2){
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }


    public void fullSort(int[] nums,int begin,int end){
        if(begin == end){
            //System.out.println(Arrays.toString(nums));
            List<Integer> result =  new ArrayList<>();
            for(int i:nums)result.add(i);
            fullSortList.add(result);
        }
        for(int i = begin;i<=end;i++){
            swap(nums,begin,i);
            fullSort(nums,begin+1,end);
            swap(nums,begin,i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        fullSort(nums,0,length-1);
        return fullSortList;
    }

    public static void main(String[] args) {
        FullSort fullSort = new FullSort();
        int[] nums = {1,2,3,4};
        System.out.println(fullSort.permute(nums).toString());
    }
}
