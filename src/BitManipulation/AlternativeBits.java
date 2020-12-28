package BitManipulation;

public class AlternativeBits {

    private String num2Str(int num){
        StringBuilder stringBuilder = new StringBuilder();
        while (num>0){
            stringBuilder.append(num%2);
            num/=2;
        }
        return stringBuilder.toString();
    }


    public boolean hasAlternatingBits(int n) {
        String string = num2Str(n);
        for(int i = 0;i<string.length()-1;i++){
            if(string.charAt(i) == string.charAt(i+1))return false;
        }
        return true;
    }

    public static void main(String[] args) {
        AlternativeBits alternativeBits = new AlternativeBits();
        System.out.println(alternativeBits.hasAlternatingBits(11));
    }

}
