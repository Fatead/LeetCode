package array;

import java.util.Arrays;
import java.util.Comparator;

public class KClosest {

    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, Comparator.comparingInt(ints -> (ints[0] * ints[0] + ints[1] * ints[1])));
        return Arrays.copyOfRange(points,0,k);
    }

    public static void main(String[] args) {
        KClosest kClosest = new KClosest();
        int[][] points = {
                {1,3},
                {-2,2},
        };
        System.out.println(Arrays.deepToString(kClosest.kClosest(points, 1)));
    }

}
