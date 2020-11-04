package array;

public class PoisonedDuration {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int time = 0;
        for(int i = 0;i<timeSeries.length-1;i++){
            if(timeSeries[i+1] - timeSeries[i] < duration){
                time += timeSeries[i+1] - timeSeries[i];
            }else {
                time += duration;
            }
        }
        time += duration;
        return time;
    }

    public static void main(String[] args) {
        int[] time = {1,4,5};
        PoisonedDuration poisonedDuration = new PoisonedDuration();
        System.out.println(poisonedDuration.findPoisonedDuration(time,4));
    }
}
