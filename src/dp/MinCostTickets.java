package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinCostTickets {

    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet();
        for (int d: days) {
            dayset.add(d);
        }
        return dp(1);
    }

    public int dp(int i) {
        if (i > 365) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        if (dayset.contains(i)) {
            memo[i] = Math.min(Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]), dp(i + 30) + costs[2]);
        } else {
            memo[i] = dp(i + 1);
        }
        return memo[i];
    }


    public static void main(String[] args) {
        MinCostTickets minCostTickets = new MinCostTickets();
        int[] days = {1,4,6,7,8,20};
        int[] costs = {7,2,15};
        System.out.println(minCostTickets.mincostTickets(days,costs));
    }

}