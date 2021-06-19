package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PancakeSort {

    List<Integer> list = new ArrayList<>();

    private void reverse(int[] arr, int index){
        for (int i = 0; i <(index+1)/2 ; i++) {
            int temp = arr[i];
            arr[i] = arr[index-i];
            arr[index-i] = temp;
        }
    }


    private void pancake(int[] arr,int num){
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i] == num){
                list.add(i+1);
                reverse(arr,i);
                list.add(num);
                reverse(arr,num-1);
                return;
            }
        }
    }

    public List<Integer> pancakeSort(int[] arr) {
        //从末尾依次判断各位数是否已经排好序了
        for (int i = arr.length-1; i >= 0; i--) {
            //如果没有，则将数字i+1放到arr[i]
            if(arr[i] != i+1){
                pancake(arr,i+1);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        PancakeSort pancakeSort = new PancakeSort();
        int[] array = {3,2,4,1};
        System.out.println(pancakeSort.pancakeSort(array));
    }

}
