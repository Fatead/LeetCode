package number;

/**
 * 位运算，每四位转一次
 */
public class ToHex {

    public String toHex(int num) {
        if(num == 0)return "0";
        StringBuilder stringBuilder = new StringBuilder();
        int temp;
        while (num!=0){
            temp = num&15;
            if(temp>9){
                stringBuilder.append((char)(temp-10+'a'));
            }else {
                stringBuilder.append(temp);
            }
            num>>>=4;
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        ToHex toHex = new ToHex();
        System.out.println(toHex.toHex(-1));
    }

}