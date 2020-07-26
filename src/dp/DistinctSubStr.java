package dp;

public class DistinctSubStr {


    public int numDistinct(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        if(length1 == 0||length2 == 0)return 0;
        int[][] array = new int[length2][length1];
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        if(chars1[0] == chars2[0])array[0][0] = 1;
        for(int i = 1;i<chars1.length;i++){
            if(chars1[i] == chars2[0]){
                array[0][i] = array[0][i-1] + 1;
            }else {
                array[0][i] = array[0][i-1];
            }
        }
        for(int i = 1;i<length2;i++){
            for(int j = i;j<length1;j++){
                if(chars1[j] == chars2[i]){
                    array[i][j] = array[i-1][j-1] + array[i][j-1];
                }else {
                    array[i][j] = array[i][j-1];
                }
            }
        }
        return array[length2-1][length1-1];
    }

    public static void main(String[] args) {
        String S = "rabbbit";
        String T = "rabbit";
        DistinctSubStr distinctSubStr = new DistinctSubStr();
        System.out.println(distinctSubStr.numDistinct(S,T));
    }
}
