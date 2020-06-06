package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DistinctFullSort {


    List<List<Integer>> fullSortList = new ArrayList<>();
    HashSet<String> sortSet = new HashSet<>();

    public void swap(int[] nums,int pos1,int pos2){
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }

    public void fullSort(int[] nums,int begin,int end){
        if(begin == end){
            List<Integer> result =  new ArrayList<>();
            StringBuffer stringBuffer = new StringBuffer();
            for(int i:nums){
                result.add(i);
                stringBuffer.append(Integer.valueOf(i));
            }
            if(!sortSet.contains(stringBuffer.toString())) {
                fullSortList.add(result);
                sortSet.add(stringBuffer.toString());
            }
        }
        for(int i =begin;i<=end;i++){
            swap(nums,begin,i);
            fullSort(nums,begin+1,end);
            swap(nums,begin,i);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        fullSort(nums,0,nums.length-1);
        return fullSortList;
    }

    public static void main(String[] args) {
        DistinctFullSort distinctFullSort = new DistinctFullSort();
        int[] nums = {2,2,1,1};
        List<List<Integer>> result = distinctFullSort.permuteUnique(nums);
        System.out.println(result.toString());
    }
}
