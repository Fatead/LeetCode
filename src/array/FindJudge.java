package array;

import java.util.HashSet;
import java.util.Set;

public class FindJudge {

    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n+1];
        Set<Integer> trustSet = new HashSet<>();
        for (int[] ints : trust) {
            count[ints[1]]++;
            trustSet.add(ints[0]);
        }
        for (int i = 1; i <count.length ; i++) {
            if(count[i] == n-1 && !trustSet.contains(i)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindJudge findJudge = new FindJudge();
        int[][] trust = {
                {1,3},
                {1,4},
                {2,3},
                {2,4},
                {4,3}
        };
        System.out.println(findJudge.findJudge(4,trust));
    }

}
