package greedy;

public class MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        if(customers.length == 0)return 0;
        int max = 0;
        int left = 0;
        for (int i = 0; i <=customers.length-minutes ; i++) {
            int unSatisfied = 0;
            for (int j = i; j <i+minutes ; j++) {
                if(grumpy[j] == 1){
                    unSatisfied += customers[j];
                }
            }
            if(unSatisfied>max){
                max = unSatisfied;
                left = i;
            }
        }
        int result = 0;
        for (int i = 0; i <customers.length ; i++) {
            if(i>=left && i<left + minutes){
                result += customers[i];
            }else if(grumpy[i] == 0){
                result += customers[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSatisfied maxSatisfied = new MaxSatisfied();
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        System.out.println(maxSatisfied.maxSatisfied(customers,grumpy,3));
    }

}
