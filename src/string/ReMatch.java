package string;

public class ReMatch {


    //没有通过全部用例
    public boolean isMatch2(String s, String p) {
        char[] chars = s.toCharArray();
        char[] pattern = p.toCharArray();
        if(chars.length == 0||pattern.length == 0)return false;
        int i = 0,j = 1;
        char preChar = pattern[0];
        while(i<chars.length&&j<pattern.length){
            char currChar = pattern[j];
            if(currChar == '*'){
                if(preChar == '.')return true;
                if(preChar == chars[i]){
                    while(preChar == chars[i]&&i<chars.length-1){
                        i++;
                    }
                    if(j == pattern.length-1)break;
                    preChar = pattern[j+1];
                    j += 1;
                }else{
                    if(j == pattern.length-1)break;
                    preChar = pattern[j+1];
                    j += 2;
                }
            }else if(currChar == '.'){
                i++;
                j++;
                preChar = '.';
            }else if(chars[i]!=currChar){
                if(j == pattern.length-1)return false;
                else if(pattern[j+1] == '*')j+=2;
                else return false;
            }else if(chars[i] == currChar){
                preChar = currChar;
                i++;
                j++;
            }
        }
        if(i >= chars.length-1){
            while(j<pattern.length){
                if(pattern[j-1] =='.')j++;
                else if(j == pattern.length)return false;
                if(pattern[j] == '*')
                j+=2;
                else return false;
            }
            return true;
        }
        else return false;
    }

    public boolean isMatch(String s, String p){
        if(p.isEmpty())return s.isEmpty();
        boolean firstMatch = (!s.isEmpty()&&(s.charAt(0) == p.charAt(0) || p.charAt(0)=='.'));
        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatch(s, p.substring(2)) ||
                    (firstMatch && isMatch(s.substring(1), p)));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args) {
        ReMatch reMatch = new ReMatch();
        String s = "aabmfvvvv";
        String pattern = "c*a*bmfv*";
        System.out.println(reMatch.isMatch(s,pattern));
    }
}
