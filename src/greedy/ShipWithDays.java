package greedy;

public class ShipWithDays {

    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        int min = sum/days;
        for (int weight : weights) {
            min = Math.max(min,weight);
        }
        while (min < sum){
            int realDay = 1;
            int currSum = 0;
            for (int i = 0; i <weights.length ; i++) {
                if(currSum + weights[i]<=min){
                    currSum += weights[i];
                }else {
                    realDay++;
                    currSum = weights[i];
                }
            }
            if(realDay > days){
                min++;
            }else {
                return min;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShipWithDays shipWithDays = new ShipWithDays();
        int[] weights = {3,2,2,4,1,4};
        System.out.println(shipWithDays.shipWithinDays(weights,3));
    }

}
