package string;

public class FindStr {

    public int strStr(String haystack, String needle) {
        int length = haystack.length();
        int needleLength = needle.length();
        if(needleLength == 0)return 0;
        else if(length<needleLength)return -1;
        for(int i = 0;i<length-needleLength+1;i++){
            if(haystack.substring(i,i+needleLength).equals(needle))return i;
        }
        return -1;
    }

    public int strStrPointer(String haystack, String needle){
        int L = haystack.length();
        int K = needle.length();
        if(K == 0)return 0;
        if(L<K)return -1;
        for(int i = 0;i<L-K+1;i++){
            if(haystack.charAt(i)==needle.charAt(0)){
                int j = 0;
                for(;j<K&&haystack.charAt(i+j)==needle.charAt(j);j++);
                if(j == K)return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        FindStr findStr = new FindStr();
        System.out.println(findStr.strStrPointer(haystack,needle));
    }
}
