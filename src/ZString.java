import java.util.ArrayList;
import java.util.List;

public class ZString {

    public String convert1(String s, int numRows) {
        StringBuffer result = new StringBuffer();
        char[] charArray = s.toCharArray();
        char[][] matrix = new char[numRows][s.length()];
        boolean flag = true;
        int m = 0,n = 0,i = 0;
        while(i<charArray.length){
            if(flag && m<numRows){
                matrix[m][n] = charArray[i++];
                m++;
            }else if(m == numRows){
                flag = false;
                m-=2;
                n++;
            }else if(!flag && m>=0){
                matrix[m][n] = charArray[i++];
                m--;
                n++;
            }else{
                flag = true;
                m+=2;
                n--;
            }
        }
        for(int p = 0;p<matrix.length;p++){
            for(int q = 0;q<matrix[0].length;q++){
                if(matrix[p][q]!='\u0000'){
                    result.append(matrix[p][q]);
                }
            }
        }
        return result.toString();
    }

    public String convert(String s, int numRows){
        if(numRows == 1)return s;
        List<StringBuilder> builderList = new ArrayList<>();
        for(int i =0;i<numRows;i++){
            builderList.add(new StringBuilder());
        }
        int curRow = 0;
        boolean downFlag = false;
        for(char c:s.toCharArray()){
            builderList.get(curRow).append(c);
            if(curRow == 0|| curRow == numRows-1){
                downFlag  = !downFlag;
            }
            curRow = downFlag ? curRow+1:curRow-1;
        }
        StringBuilder result = new StringBuilder();
        for(StringBuilder stringBuilder:builderList){
            result.append(stringBuilder);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String str = "LEETCODEISHIRING";
        ZString zString = new ZString();
        System.out.println(zString.convert(str,4));
    }
}
