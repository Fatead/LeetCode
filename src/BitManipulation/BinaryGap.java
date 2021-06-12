package BitManipulation;

public class BinaryGap {


    private String num2binary(int num){
        StringBuilder stringBuilder = new StringBuilder();
        while (num>0){
            stringBuilder.append((char)((num%2) + '0'));
            num/=2;
        }
        return stringBuilder.toString();
    }


    public int binaryGap(int n) {
        String binary = num2binary(n);
        int maxGap = 0;
        int gap = -1;
        for (int i = 0; i <binary.length() ; i++) {
            if(binary.charAt(i) == '0' && gap!=-1){
                gap++;
            }else if(binary.charAt(i) == '1'){
                maxGap = Math.max(maxGap,gap);
                gap = 1;
            }
        }
        return maxGap;
    }


    public static void main(String[] args) {
        BinaryGap binaryGap = new BinaryGap();
        System.out.println(binaryGap.binaryGap(1));
    }

}
