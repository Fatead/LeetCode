package string;

public class ReverseString2 {

    private String getReverseStr(String s,int index,int end){
        StringBuilder stringBuilder = new StringBuilder(s.substring(index,end));
        return stringBuilder.reverse().toString();
    }

    public String reverseStr(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<s.length()/(2*k);i++){
            stringBuilder.append(getReverseStr(s,i*2*k, i*2*k + k));
            stringBuilder.append(s, i*2*k + k, i*2*k + 2*k);
        }
        if(s.length()%(2*k) > k){
            int end = s.length() - s.length() % (k*2) + k;
            int begin = s.length() - s.length()%(k*2);
            stringBuilder.append(getReverseStr(s,begin, end));
            stringBuilder.append(s, end, s.length());
        }else {
            stringBuilder.append(getReverseStr(s,s.length() - s.length()%(k*2),s.length()));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ReverseString2 reverseString2 = new ReverseString2();
        String result =  reverseString2.reverseStr("abcdefg",1);
        System.out.println(result);
    }

}
