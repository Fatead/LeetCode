package number;

public class StringToInt {

    public int Change(String str){
        if(str.length()<1)return 0;
        if(!(str.charAt(0)<='9'&&str.charAt(0)>='0'))return 0;
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = str.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i]<='9'&&chars[i]>='0'){
                stringBuffer.append(chars[i]);
            }else{
                return (int)Double.parseDouble(stringBuffer.toString());
            }
        }
        return (int)Double.parseDouble(stringBuffer.toString());
    }

    public int myAtoi(String str) {
        str = str.trim();
        if(str.length()>=11&&str.substring(0,11).equals("-2147483647"))return -2147483647;
        if(str.length()<1)return 0;
        if(str.charAt(0) == '+'){
            return Change(str.substring(1));
        }else if(str.charAt(0) == '-'){
            if(Change(str.substring(1))==Integer.MAX_VALUE)return Integer.MIN_VALUE;
            return -Change(str.substring(1));
        }else if(str.charAt(0)<='9'&&str.charAt(0)>='0'){
            return Change(str);
        }else return 0;
    }

    public static void main(String[] args) {
        StringToInt stringToInt = new StringToInt();
        String str = "   -42";
        System.out.println(stringToInt.myAtoi(str));
    }
}
