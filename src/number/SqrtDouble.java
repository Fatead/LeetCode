package number;

public class SqrtDouble {

    /**
     * 对X在e的精度范围内开n次方
     * @param X
     * @param n
     * @param e
     * @return
     */
    private static double calculate(double X,int n,double e){
        if(n%2==0&&X<0){
            System.out.println("error input");
            return -1;
        }
        if(n==0){
            return 1;
        }
        if(n<0){
            X = 1/X;
            n = -n;
        }
        boolean flag = true;
        if(X<0){
            X = -X;
            flag = false;
        }
        double high;
        double low;
        double mid;
        double target;
        if(X<1){
            low = 0;
            high = 1;
            target = 1;
            mid = (low + high)/2;
        }else {
            double num = 0;
            target = 1;
            while (target<X){
                target = 1;
                num++;
                for (int i = 0; i <n ; i++) {
                    target *= num;
                }

            }
            low = num-1;
            high = num;
            mid = (low + high)/2;
            target = 1;
        }
        for (int i = 0; i <n ; i++) {
            target *= mid;
        }
        while (Math.abs(target - X)>e){
            if(target>X){
                high = mid;
            }else {
                low = mid;
            }
            mid = (low + high)/2;
            target = 1;
            for (int i = 0; i <n ; i++) {
                target *= mid;
            }
        }
        return flag ?mid:-mid;
    }


}
