package backtracking;
import java.util.*;

public class NumSameConsecDiff {

    Set<Integer> set = new HashSet<>();

    public void dfs(int num,int n,int k,int curr){
        if(num>n)return;
        if(num == n){
            set.add(curr);
            return;
        }
        int before = curr%10;
        if(before + k<10){
            int nextCurr = curr*10 + before + k;
            dfs(num+1,n,k,nextCurr);
        }
        if(before - k>=0){
            int nextCurr = curr*10 + before - k;
            dfs(num+1,n,k,nextCurr);
        }
    }

    public int[] numsSameConsecDiff(int n, int k) {
        for (int i = 1; i <10 ; i++) {
            dfs(1,n,k,i);
        }
        int[] result = new int[set.size()];
        int count = 0;
        for (Integer integer : set) {
            result[count++] = integer;
        }
        return result;
    }

    public static void main(String[] args) {
        NumSameConsecDiff numSameConsecDiff = new NumSameConsecDiff();
        System.out.println(Arrays.toString(numSameConsecDiff.numsSameConsecDiff(2, 2)));
    }

}