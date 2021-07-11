package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class TwoCitySchedCost {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return (ints[0] - ints[1]) - (t1[0] - t1[1]) ;
            }
        });
        int n = costs.length/2;
        int result = 0;
        for (int i = 0; i <n ; i++) {
            result += costs[i][0] + costs[i+n][1];
        }
        return result;
    }

    public static void main(String[] args) {
        TwoCitySchedCost twoCitySchedCost = new TwoCitySchedCost();
        int[][] costs = {
                {10,20},
                {30,200},
                {400,50},
                {30,20}
        };
        System.out.println(twoCitySchedCost.twoCitySchedCost(costs));
    }

}