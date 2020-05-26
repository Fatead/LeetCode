package number;

public class Int2Roman {


    public String intToRoman(int num) {
        int new_Num = num;
        StringBuffer stringBuffer = new StringBuffer();
        int M_num = new_Num/1000;
        new_Num = new_Num%1000;
        for(int i =0;i<M_num;i++){
            stringBuffer.append("M");
        }
        if(new_Num>=900){
            stringBuffer.append("CM");
            new_Num = new_Num%900;
        }else if(new_Num>=500){
            stringBuffer.append("D");
            new_Num %=500;
        }else if(new_Num>=400){
            stringBuffer.append("CD");
            new_Num %= 400;
        }
        int C_Num = new_Num/100;
        new_Num %=100;
        for(int i = 0 ;i<C_Num;i++){
            stringBuffer.append("C");
        }
        if(new_Num>=90){
            stringBuffer.append("XC");
            new_Num%=90;
        }else if(new_Num>=50){
            stringBuffer.append("L");
            new_Num%=50;
        }else if(new_Num>=40){
            stringBuffer.append("XL");
            new_Num%=40;
        }
        int L_Num = new_Num/10;
        new_Num %= 10;
        for(int i =0;i<L_Num;i++){
            stringBuffer.append("X");
        }

        if(new_Num>=9){
            stringBuffer.append("IX");
            new_Num%=9;
        }else if(new_Num>=5){
            stringBuffer.append("V");
            new_Num%=5;
        }else if(new_Num>=4){
            stringBuffer.append("IV");
            new_Num%=4;
        }
        int I_Num = new_Num/1;
        new_Num %= 1;
        for(int i =0;i<I_Num;i++){
            stringBuffer.append("I");
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Int2Roman int2Roman = new Int2Roman();
        System.out.println(int2Roman.intToRoman(99));
    }
}
