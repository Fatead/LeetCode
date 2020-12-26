package string;

import java.util.ArrayList;
import java.util.List;

public class BinarySubString {

    int count = 0;


    private boolean isSame(int num,String s){
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)!=(num + '0'))return false;
        }
        return true;
    }


    private boolean isBinary(String s){
        return isSame(0, s.substring(0, s.length() / 2)) && isSame(1, s.substring(s.length() / 2))
                || isSame(1, s.substring(0, s.length() / 2)) && isSame(0, s.substring(s.length() / 2));
    }


    public int countBinarySubstrings(String s) {
        for(int i = 0;i<s.length();i++){
            for(int j = i+2;j<=s.length();j+=2){
                if(isBinary(s.substring(i,j))){
                    count++;
                }
            }
        }
        return count;
    }


    public int countBinarySubstrings2(String s){
        if(s.length()<2)return 0;
        List<Integer> countList = new ArrayList<>();
        int count = 1;
        for(int i = 0;i<s.length()-1;i++){
            if(s.charAt(i)!=s.charAt(i+1)){
                countList.add(count);
                count = 1;
            }else {
                count++;
            }
        }
        countList.add(count);
        int sum = 0;
        for(int i = 0;i<countList.size()-1;i++){
            sum += Math.min(countList.get(i),countList.get(i+1));
        }
        return sum;
    }


    public static void main(String[] args) {
        BinarySubString binarySubString = new BinarySubString();
        int result = binarySubString.countBinarySubstrings2("00111011");
        System.out.println(result);
    }

}
