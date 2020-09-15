package number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GuessNum {

    public String getHint(String secret, String guess) {
        int bullsCount = 0;
        int cowsCount = 0;
        Map<Character,Integer> secretMap = new HashMap<>();
        Map<Character,Integer> guessMap = new HashMap<>();
        for(int i = 0;i<secret.length();i++){
            if(secret.charAt(i) == guess.charAt(i))bullsCount++;
            else {
                int secretCount =  secretMap.getOrDefault(secret.charAt(i),0);
                secretCount++;
                secretMap.put(secret.charAt(i),secretCount);
                int guessCount =  guessMap.getOrDefault(guess.charAt(i),0);
                guessCount++;
                guessMap.put(guess.charAt(i),guessCount);
            }
        }
        for(char c:secretMap.keySet()){
            if(guessMap.containsKey(c)){
                cowsCount+=Math.min(secretMap.get(c),guessMap.get(c));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bullsCount).append("A");
        stringBuilder.append(cowsCount).append("B");
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        GuessNum guessNum = new GuessNum();
        String result = guessNum.getHint("1807","7810");
        System.out.println(result);
    }
}
