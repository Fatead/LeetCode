package array;

public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int endTime = 0;
        for (int[] trip : trips) {
            endTime = Math.max(endTime, trip[2]);
        }
        for (int i = 0; i <endTime ; i++) {
            //到达目的地
            for (int[] trip : trips) {
                if(i == trip[2]){
                    capacity += trip[0];
                }
            }
            for (int[] trip : trips) {
                if(i == trip[1]){
                    capacity -= trip[0];
                    if(capacity<0)return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        int[][] trip = {
                {3,2,7},
                {3,7,9},
                {8,3,9}
        };
        System.out.println(carPooling.carPooling(trip,11));
    }

}
