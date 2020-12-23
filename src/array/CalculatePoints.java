package array;

import java.util.ArrayList;
import java.util.List;

public class CalculatePoints {


    private int strToNum(String str){
        boolean flag = true;
        if(str.contains("-")){
            flag = false;
            str = str.substring(1);
        }
        int num = 0;
        for(int i = 0;i<str.length();i++){
            num = num*10 + (str.charAt(i) - '0');
        }
        if(flag)return num;
        return -num;
    }

    public int calPoints(String[] ops) {
        List<Integer> recordNum = new ArrayList<>();
        for(String str:ops){
            if(str.equals("+")){
                recordNum.add(recordNum.get(recordNum.size()-1) + recordNum.get(recordNum.size()-2));
            }else if(str.equals("D")){
                recordNum.add(recordNum.get(recordNum.size()-1) * 2);
            }else if(str.equals("C")){
                recordNum.remove(recordNum.size()-1);
            }else {
                recordNum.add(strToNum(str));
            }
            System.out.println(recordNum);
        }
        int sum = 0;
        for(int i:recordNum){
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        CalculatePoints calculatePoints = new CalculatePoints();
        String[] ops = {"1","C","-62","-45","-68"};
        System.out.println(calculatePoints.calPoints(ops));
    }

}
