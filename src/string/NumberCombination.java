package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberCombination {

    Map<String,String> phone = new HashMap<String,String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<>();

    public void combine(String result,String digit){
        if(digit.length() == 0){
            output.add(result);
            return;
        }
        String str = phone.get(digit.substring(0,1));
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            combine(result + c,digit.substring(1));
        }

    }

    public List<String> letterCombinations(String digits) {
        if(digits!=null&&digits.length()>0){
            combine("",digits);
            return output;
        }else return output;
    }

    public static void main(String[] args) {


    }
}
