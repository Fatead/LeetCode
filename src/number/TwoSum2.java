package number;
import java.util.HashMap;
import java.util.Map;

public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> intMap = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0;i<numbers.length;i++){
            intMap.put(numbers[i],i+1);
        }
        for(int i = 0;i<numbers.length;i++){
            int res = target - numbers[i];
            if(intMap.containsKey(res)){
                result[0] = i+1;
                result[1] = intMap.get(res);
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
