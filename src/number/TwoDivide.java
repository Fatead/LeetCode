package number;

public class TwoDivide {


    //超时
    public int divide2(int dividend, int divisor) {
        if(divisor == 0)return 0;
        boolean positive = true;
        if((dividend>0&&divisor<0)||(dividend<0&&divisor>0))positive = false;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        if(dividend==Integer.MIN_VALUE)dividend = Integer.MAX_VALUE;
        int sum = 0,count = 0;
        while(sum+divisor<=dividend){
            sum += divisor;
            count++;
        }
        if(positive) return count;
        else return -count;
    }

    public int divide(int dividend, int divisor) {
        boolean sign = (dividend>0) ^ (divisor>0);
        if(dividend>0)dividend = -dividend;
        if(divisor>0)divisor = -divisor;
        int result = 0;
        while(dividend <= divisor){
            int temp_divisor = divisor;
            int temp_result = -1;
            while(dividend <= (temp_divisor<<1)){
                if(temp_divisor<= (Integer.MIN_VALUE>>1))break;
                temp_divisor = temp_divisor<<1;
                temp_result = temp_result<<1;
            }
            dividend -= temp_divisor;
            result += temp_result;
        }
        if(!sign){
            if(result == Integer.MIN_VALUE)return Integer.MAX_VALUE;
            result = -result;
        }
        return result;
    }

    public static void main(String[] args) {
        TwoDivide twoDivide = new TwoDivide();
        System.out.println(twoDivide.divide(Integer.MIN_VALUE,-1));
    }
}
