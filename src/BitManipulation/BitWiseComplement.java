package BitManipulation;

public class BitWiseComplement {

    private String getBit(int n){
        StringBuilder stringBuilder = new StringBuilder();
        while (n!=0){
            stringBuilder.append(n%2);
            n/=2;
        }
        return stringBuilder.reverse().toString();
    }

    private int getNumber(String number){
        int result = 0;
        for (int i = 0; i <number.length() ; i++) {
            if(number.charAt(i) == '1'){
                result += 1;
            }
            result *= 2;
        }
        return result/2;
    }

    public int bitwiseComplement(int n) {
        if(n==0)return 1;
        String bitRepresent = getBit(n);
        StringBuilder wiseString = new StringBuilder();
        for (int i = 0; i <bitRepresent.length() ; i++) {
            wiseString.append(1^(bitRepresent.charAt(i)-'0'));
        }
        return getNumber(wiseString.toString());
    }

    public static void main(String[] args) {
        BitWiseComplement bitWiseComplement = new BitWiseComplement();
        System.out.println(bitWiseComplement.bitwiseComplement(8));
    }

}