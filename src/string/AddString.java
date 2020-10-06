package string;

public class AddString {

    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int len = Math.max(len1,len2);
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        if(len1>len2){
            for(int i =0;i<Math.abs(len1-len2);i++){
                stringBuilder2.append(0);
            }
        }else {
            for(int i =0;i<Math.abs(len1-len2);i++){
                stringBuilder1.append(0);
            }
        }
        stringBuilder1.append(num1);
        stringBuilder2.append(num2);
        int addBit = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<len;i++){
            int bit1 = stringBuilder1.charAt(len-i-1) - '0';
            int bit2 = stringBuilder2.charAt(len-i-1) - '0';
            int curr = bit1 + bit2 + addBit;
            addBit = (curr - curr%10)/10;
            curr = curr%10;
            stringBuilder.append(curr);
        }
        if(addBit!=0){
            stringBuilder.append(addBit);
        }
        return stringBuilder.reverse().toString();
    }


    public static void main(String[] args) {
        AddString addString = new AddString();
        System.out.println(addString.addStrings("9991",""));
    }

}
