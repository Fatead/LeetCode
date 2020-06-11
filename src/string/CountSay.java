package string;

public class CountSay {

    public String trans(String s){
        char[] chars = s.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        int count = 1;
        for(int i = 1;i<chars.length;i++){
            if(chars[i] == chars[i-1]){
                count++;
            }else {
                stringBuffer.append(count).append(chars[i-1]);
                count = 1;
            }
        }
        stringBuffer.append(count).append(chars[chars.length-1]);
        return stringBuffer.toString();
    }

    public String countAndSay(int n) {
        if(n == 1)return "1";
        return trans(countAndSay(n-1));
    }

    public static void main(String[] args) {
        CountSay countSay = new CountSay();
        System.out.println(countSay.countAndSay(9));
    }
}
