package BitManipulation;

public class MinFlips {

    public int minFlips(int a, int b, int c) {
        int result = 0;
        //对于32位进行循环
        for (int i = 0; i <32 ; i++){
            int bitA = (a>>i) & 1;
            int bitB = (b>>i) & 1;
            int bitC = (c>>i) & 1;
            //如果C的该位为0，那么A和B该位为1则反转，否则不反转，确保两个都是0。、
            //如果C的该位为1，那么A和B中至少要保证一个为0：只有两个数都为0的时候result += 1
            if(bitC == 0){
                result += bitA + bitB;
            }else {
                if(bitA+bitB == 0){
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MinFlips minFlips = new MinFlips();
        System.out.println(minFlips.minFlips(2,6,5));
    }

}
