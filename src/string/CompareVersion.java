package string;

public class CompareVersion {

    private int Str2Int(String str){
        char[] chars = str.toCharArray();
        int result = 0;
        for(int i = 0;i<chars.length;i++){
            result = result*10 + (chars[i] - '0');
        }
        return result;
    }

    public int compareVersion(String version1, String version2) {
        String[] verStr1 = version1.split("\\.");
        String[] verStr2 = version2.split("\\.");
        int len = Math.max(verStr1.length,verStr2.length);
        int[] verArray1 = new int[len];
        int[] verArray2 = new int[len];
        for(int i = 0;i<verStr1.length;i++){
            verArray1[i] = Str2Int(verStr1[i]);
        }
        for(int i = 0;i<verStr2.length;i++){
            verArray2[i] = Str2Int(verStr2[i]);
        }
        for(int i = 0;i<len;i++){
            if(verArray1[i] > verArray2[i]){
                return 1;
            }else if(verArray1[i]<verArray2[i]){
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String version1 = "3";
        String version2 = "2";
        CompareVersion compareVersion = new CompareVersion();
        int result =  compareVersion.compareVersion(version1,version2);
        System.out.println(result);
    }

}
