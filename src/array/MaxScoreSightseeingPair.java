package array;

public class MaxScoreSightseeingPair {

    public int maxScoreSightseeingPair(int[] values) {
        int currMax = 0,mx = values[0] + 0;
        for (int j = 1; j <values.length ; j++) {
            currMax = Math.max(currMax,mx + values[j] - j);
            mx = Math.max(mx,values[j] + j);
        }
        return currMax;
    }

    public static void main(String[] args) {
        MaxScoreSightseeingPair maxScoreSightseeingPair = new MaxScoreSightseeingPair();
        int[] values = {8,1,5,2,6};
        System.out.println(maxScoreSightseeingPair.maxScoreSightseeingPair(values));
    }

}
