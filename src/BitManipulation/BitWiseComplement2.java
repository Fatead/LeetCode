package BitManipulation;

public class BitWiseComplement2 {

    public int bitwiseComplement(int n) {
        int num = 1;
        while (num<n){
            num = (num<<1) + 1;
        }
        return n^num;
    }

    public static void main(String[] args) {
        BitWiseComplement2 bitWiseComplement2 = new BitWiseComplement2();
        System.out.println(bitWiseComplement2.bitwiseComplement(10));
    }

}