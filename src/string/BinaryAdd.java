package string;

import java.util.Stack;

public class BinaryAdd {

    public String addBinary(String a, String b) {
        int length1 = a.length();
        int length2 = b.length();
        if(length1 == 0)return b;
        if(length2 == 0)return a;
        int length = Math.max(length1,length2);
        if(length1>length2){
            for(int i = 0;i<length1-length2;i++){
                b = "0" + b;
            }
        }else {
            for(int i =0;i<length2-length1;i++){
                a = "0" + a;
            }
        }
        int nextBit = 0;
        Stack<Integer> integerStack = new Stack<>();
        for(int i = length-1;i>=0;i--){
            int value = a.charAt(i) - '0' + b.charAt(i) - '0' + nextBit;
            if(value>=2){
                value = value%2;
                nextBit = 1;
            }else {
                nextBit = 0;
            }
            integerStack.push(value);
        }
        if(nextBit == 1)integerStack.push(nextBit);
        StringBuilder stringBuilder = new StringBuilder();
        while(!integerStack.isEmpty()){
            stringBuilder.append(integerStack.pop());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        BinaryAdd binaryAdd = new BinaryAdd();
        System.out.println(binaryAdd.addBinary(a,b));
    }

}
