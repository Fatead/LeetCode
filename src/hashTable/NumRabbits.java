package hashTable;

import java.util.HashMap;
import java.util.Map;

public class NumRabbits {

    public int numRabbits(int[] answers) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer,map.getOrDefault(answer,0) + 1);
        }
        int result = 0;
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            if(value<=key+1){
                result+=key+1;
            }else{
                int temp = key+1;
                while (temp<value){
                    temp+=key+1;
                }
                result += temp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NumRabbits numRabbits = new NumRabbits();
        int[] ans = {};
        System.out.println(numRabbits.numRabbits(ans));

    }

}
