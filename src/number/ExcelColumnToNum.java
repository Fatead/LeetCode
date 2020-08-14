package number;

public class ExcelColumnToNum {

    public int titleToNumber(String s) {
        if(s == null|| s.length() == 0)return 0;
        char[] chars = s.toCharArray();
        int level = 1;
        int result = 0;
        for(int i = chars.length-1;i>=0;i--){
            result = result + (chars[i] - 'A' + 1)*level;
            level *=26;
        }
        return result;
    }

    public static void main(String[] args) {
        ExcelColumnToNum excelColumnToNum = new ExcelColumnToNum();
        int result = excelColumnToNum.titleToNumber("ZY");
        System.out.println(result);
    }
}
