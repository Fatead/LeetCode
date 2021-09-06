package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class RearrangeBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int[] countArray = new int[10005];
        for (int barcode : barcodes) {
            countArray[barcode]++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(integer -> -countArray[integer]));
        for (int i = 1; i <countArray.length ; i++) {
            if(countArray[i]!=0){
                queue.add(i);
            }
        }
        int[] curr = new int[n];
        int count = 0;
        while (!queue.isEmpty()){
            int num = queue.poll();
            for (int i = 0; i <countArray[num] ; i++) {
                curr[count++] = num;
            }
        }
        int[] result = new int[n];
        count = 0;
        for (int i = 0; i <result.length ; i+=2) {
            result[i] = curr[count++];
        }
        for (int i = 1; i <result.length ; i+=2) {
            result[i] = curr[count++];
        }
        return result;
    }

    public static void main(String[] args) {
        RearrangeBarcodes rearrangeBarcodes = new RearrangeBarcodes();
        int[] barcodes = {1,1,1,2,2,2};
        System.out.println(Arrays.toString(rearrangeBarcodes.rearrangeBarcodes(barcodes)));
    }

}
