package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CamelMatch {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            int i = 0,j = 0;
            boolean flag = true;
            while (i<query.length() && j<pattern.length()){
                if(Character.isUpperCase(pattern.charAt(j))){
                    if(Character.isLowerCase(query.charAt(i))){
                        i++;
                    }else if(query.charAt(i) == pattern.charAt(j)){
                        i++;
                        j++;
                    }else {
                        flag = false;
                        break;
                    }
                }else {
                    if(query.charAt(i) == pattern.charAt(j)) {
                        i++;
                        j++;
                    }else {
                        i++;
                    }
                }
            }
            if(!flag){
                result.add(false);
            }else {
                if(i == query.length() && j == pattern.length()){
                    result.add(true);
                } else if(i<query.length()){
                    boolean f = true;
                    for (int k = i+1; k <query.length() ; k++) {
                        if(Character.isUpperCase(query.charAt(k))){
                            f = false;
                            break;
                        }
                    }
                    result.add(f);
                }else {
                    result.add(false);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CamelMatch camelMatch = new CamelMatch();
        String[] queries = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
        List<Boolean> result = camelMatch.camelMatch(queries,"FoBaT");
        System.out.println(result);
    }

}
