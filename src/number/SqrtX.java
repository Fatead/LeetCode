package number;

public class SqrtX {

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int mid = 0;
        while(left<=right){
            mid = (left + right)/2;
            if(mid*mid == x){
                return mid;
            } else if((long)mid*mid>x){
                right = mid-1;
            }else {
                left = mid+1;
            }
            System.out.println(mid);
        }
        if(mid*mid>x)return mid-1;
        else return mid;
    }

    public static void main(String[] args) {
        SqrtX sqrtX = new SqrtX();
        System.out.println(sqrtX.mySqrt(2147395599));
    }
}
