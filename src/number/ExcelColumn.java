package number;

import java.util.ArrayList;
import java.util.List;

public class ExcelColumn {


    public String convertToTitle(int n) {
        List<Integer> list = new ArrayList<>();
        while(n>26){
            int nextBit = n/26;
            int res = n%26;
            if(res == 0){
                res = 26;
                n = nextBit-1;
            }else {
                n = nextBit;
            }
            list.add(res);
        }
        list.add(n);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = list.size()-1;i>=0;i--){
            stringBuilder.append((char)(list.get(i) + 'A' -1));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ExcelColumn excelColumn = new ExcelColumn();
        String str =  excelColumn.convertToTitle(52);
        System.out.println(str);
    }
}
