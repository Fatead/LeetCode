package BitManipulation;

public class ComplementNum {

    public int findComplement(int num) {
        int temp = num;
        int num2 = 1;
        while(temp>0){
            temp>>=1;
            num2<<=1;
        }
        num2-=1;
        return num^num2;
    }

    public static void main(String[] args) {
        System.out.println(~1);
    }

}
