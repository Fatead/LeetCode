package string;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i = 1;i<=n;i++){
            StringBuilder stringBuilder = new StringBuilder();
            if(i%3 == 0){
                stringBuilder.append("Fizz");
            }
            if(i%5 == 0){
                stringBuilder.append("Buzz");
            }
            if(stringBuilder.length() == 0){
                stringBuilder.append(String.valueOf(i));
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
