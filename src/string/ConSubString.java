package string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
*   哈希表，双指针
* */
public class ConSubString {


    //巧用HashMap，使得我们不再纠结单词的顺序
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String,Integer> wordsMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int wordSize = words.length;
        if(wordSize == 0)return result;
        int wordLen = words[0].length();
        for(int i = 0;i<wordSize;i++){
            int value = wordsMap.getOrDefault(words[i],0);
            wordsMap.put(words[i],value+1);
        }
        for(int i =0;i<s.length()-wordLen*wordSize+1;i++){
            HashMap<String,Integer> strMap = new HashMap<>();
            for(int j = 0;j<wordSize;j++){
                String str = s.substring(i+j*wordLen,i+(j+1)*wordLen);
                System.out.println(str);
                int value = strMap.getOrDefault(str,0);
                strMap.put(str,value+1);
                if(strMap.getOrDefault(str,0)>wordsMap.getOrDefault(str,0))break;
                if(j == wordSize-1)result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ConSubString conSubString = new ConSubString();
        String str = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(conSubString.findSubstring(str,words));
    }
}
