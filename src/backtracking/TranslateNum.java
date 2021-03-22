package backtracking;
import java.util.HashSet;
import java.util.Set;

public class TranslateNum {

    Set<String> set = new HashSet<>();

    private void dfs(String string,int pos,StringBuilder stringBuilder){
        if(pos >= string.length()){
            set.add(stringBuilder.toString());
            return;
        }
        stringBuilder.append((char)(string.charAt(pos) - '0' + 'a'));
        dfs(string,pos+1,new StringBuilder(stringBuilder));
        stringBuilder.subSequence(0,stringBuilder.length()-1);
        if(pos+1 == string.length()){
            set.add(stringBuilder.toString());
            return;
        }else if(pos+1>string.length())return;
        int num = Integer.parseInt(string.substring(pos,pos+2));
        if(num>=26)return;
        stringBuilder.append((char)(num + 'a'));
        dfs(string,pos+2,new StringBuilder(stringBuilder));
        stringBuilder.subSequence(0,stringBuilder.length()-1);
    }

    public int translateNum(int num) {
        dfs(String.valueOf(num),0,new StringBuilder());
        return set.size();
    }

    public static void main(String[] args) {
        TranslateNum translateNum = new TranslateNum();
        System.out.println(translateNum.translateNum(4616258));
    }

}