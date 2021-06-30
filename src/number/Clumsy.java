package number;

public class Clumsy {

    /**
     * 每四个一组的数进行计算，把最终得到的结果累加起来
     */
    public int clumsy(int n) {
        int sum = 0;
        int count = n/4;
        int index = n;
        for (int i = 0; i <count ; i++) {
            int groupSum = 0;
            if(i==0){
                groupSum = (index--)*(index--)/(index--) + (index--);
            }else {
                groupSum = -(index--)*(index--)/(index--) + (index--);
            }
            sum += groupSum;
        }
        if(n%4==0){
            return sum;
        }else {
            if (count>0) {
                if(index == 1){
                    sum -=1;
                }else if(index == 2){
                    sum -= 2;
                }else if(index == 3){
                    sum -= 6;
                }
            }else {
                if(index == 1){
                    sum +=1;
                }else if(index == 2){
                    sum += 2;
                }else if(index == 3){
                    sum += 6;
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Clumsy clumsy = new Clumsy();
        System.out.println(clumsy.clumsy(4));
    }

}
