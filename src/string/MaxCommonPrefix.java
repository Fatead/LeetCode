package string;

public class MaxCommonPrefix {

//    public String commonPrefix(String str1,String str2){
//        StringBuffer stringBuffer = new StringBuffer();
//        //for(int i = 0;i<)
//    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)return "";
        else if(strs.length == 1)return strs[0];
        int minLength = Integer.MAX_VALUE;
        StringBuffer str = new StringBuffer();
        for(int i = 0;i<strs.length;i++){
            if(strs[i].length()<minLength)minLength = strs[i].length();
        }
        for(int i =0;i<minLength;i++){
            char c = 0;
            for(int j = 0;j<strs.length-1;j++){
                c = strs[j].charAt(i);
                if(strs[j].charAt(i)!=strs[j+1].charAt(i))return str.toString();
            }
            if(c!=0) str.append(c);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        MaxCommonPrefix maxCommonPrefix = new MaxCommonPrefix();
        String[] strs = {"flower","flow","flight","c"};
        System.out.println(maxCommonPrefix.longestCommonPrefix(strs));
    }
}
