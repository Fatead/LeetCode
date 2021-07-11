package array;

import java.util.Arrays;
import java.util.Comparator;

public class AllCellsDistOrder {

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int[][] arrays = new int[rows*cols][2];
        int count = 0;
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <cols ; j++) {
                arrays[count][0] = i;
                arrays[count][1] = j;
                count++;
            }
        }
        Arrays.sort(arrays, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return (Math.abs(ints[0] - rCenter) + Math.abs(ints[1] - cCenter)) - (Math.abs(t1[0] - rCenter) + Math.abs(t1[1] - cCenter));
            }
        });
        return arrays;
    }

    public static void main(String[] args) {
        AllCellsDistOrder allCellsDistOrder = new AllCellsDistOrder();
        System.out.println(Arrays.deepToString(allCellsDistOrder.allCellsDistOrder(2, 3, 1, 2)));
    }

}
