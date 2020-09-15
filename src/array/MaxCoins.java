package array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MaxCoins {


    public int maxCoins(int[] nums) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        for(int i:nums){
            list.add(i);
        }
        list.add(1);
        int result = 0;
        while(list.size()>2){
            int pos = 1;
            int min = Integer.MAX_VALUE;
            for(int i = 1;i<list.size()-1;i++){
                if(list.size()>4 && (i == 1||i == list.size()-2)){
                    continue;
                }
                if(list.get(i) < min ){
                    min = list.get(i);
                    pos = i;
                }
            }
            result += list.get(pos-1)*list.get(pos)*list.get(pos+1);
            list.remove(pos);
        }
        return result;
    }


    public static void main(String[] args) {
        MaxCoins maxCoins = new MaxCoins();
        int[] nums = {3,1,5,8};
        int result = maxCoins.maxCoins(nums);
        System.out.println(result);
    }

}
