package number;

public class Roman2Int {


    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        if(chars.length<1)return 0;
        int result = 0;
        for(int i = 0;i<chars.length;i++){
            switch (chars[i]){
                case 'M':result += 1000;break;
                case 'D':result += 500;break;
                case 'C':{
                    if(i == chars.length-1){
                        result += 100;
                        break;
                    }else if(chars[i+1]=='D'){
                        result += 400;
                        i++;
                        break;
                    }else if(chars[i+1]=='M'){
                        result += 900;
                        i++;
                        break;
                    }else{
                        result += 100;
                        break;
                    }
                }
                case 'L':result += 50;break;
                case 'X':{
                    if(i == chars.length-1){
                        result += 10;
                        break;
                    }else if(chars[i+1]=='L'){
                        result += 40;
                        i++;
                        break;
                    }else if(chars[i+1]=='C'){
                        result += 90;
                        i++;
                        break;
                    }else{
                        result += 10;
                        break;
                    }
                }
                case 'V':result += 5;break;
                case 'I':{
                    if(i == chars.length-1){
                        result += 1;
                        break;
                    }else if(chars[i+1]=='V'){
                        result += 4;
                        i++;
                        break;
                    }else if(chars[i+1]=='X'){
                        result += 9;
                        i++;
                        break;
                    }else{
                        result += 1;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private int getValues(char ch){
        switch(ch){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    private int Roman2int(String s){
        int sum = 0;
        int preNum = getValues(s.charAt(0));
        for(int i = 1;i< s.length();i++){
            int currNum = getValues(s.charAt(i));
            if(currNum <= preNum){
                sum += preNum;
            }else {
                sum -= preNum;
            }
            preNum = currNum;
        }
        sum += preNum;
        return sum;
    }

    public static void main(String[] args) {
        String str = "LVIII";
        Roman2Int roman2Int = new Roman2Int();
        int result = roman2Int.Roman2int(str);
        System.out.println(result);
    }
}
