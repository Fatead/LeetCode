package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindContinuousSequence {

    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <=target/2 ; i++) {
            int sum = i;
            for (int j = i+1; j <target ; j++) {
                sum += j;
                if(sum == target){
                    List<Integer> list = new ArrayList<>();
                    for (int k = i; k <=j ; k++) {
                        list.add(k);
                    }
                    lists.add(list);
                    break;
                }else if(sum>target){
                    break;
                }
            }
        }
        int[][] result = new int[lists.size()][];
        for (int i = 0; i <lists.size() ; i++) {
            result[i] = new int[lists.get(i).size()];
            for (int j = 0; j <lists.get(i).size() ; j++) {
                result[i][j] = lists.get(i).get(j);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindContinuousSequence findContinuousSequence = new FindContinuousSequence();
        int[][] result = findContinuousSequence.findContinuousSequence(15);
        System.out.println(Arrays.deepToString(result));
    }

}
