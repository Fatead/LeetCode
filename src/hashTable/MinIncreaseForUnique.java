package hashTable;
import java.util.*;

public class MinIncreaseForUnique {

    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        if(A.length<=1)return 0;
        int sum = 0;
        Queue<Integer> duplicateNum = new LinkedList<>();
        Set<Integer> numSet = new HashSet<>();
        //记录所有已经出现的数
        for (int i : A) {
            numSet.add(i);
        }
        for (int i = 1; i <A.length ; i++) {
            if(A[i] == A[i-1]){
                //放入重复的数
                duplicateNum.add(A[i]);
            }
        }
        while (!duplicateNum.isEmpty()){
            int num = duplicateNum.poll();
            int minNum = num;
            //找到一个还没使用的数
            while (numSet.contains(minNum)){
                minNum++;
            }
            numSet.add(minNum);
            sum += minNum - num;
        }
        return sum;
    }


    public static void main(String[] args) {
        MinIncreaseForUnique minIncreaseForUnique = new MinIncreaseForUnique();
        int[] A = {1,2,2};
        System.out.println(minIncreaseForUnique.minIncrementForUnique(A));
    }

}
