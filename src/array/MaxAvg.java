package array;

public class MaxAvg {

    public double findMaxAverage(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length-k+1;i++){
            double sum = 0;
            if(i>0 && nums[i-1] > nums[i+3])continue;
            for(int j = i;j<i+k;j++){
                sum += nums[j];
            }
            max = Math.max(max,sum/k);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1,12,-5,-6,50,3,600};
        MaxAvg maxAvg = new MaxAvg();
        System.out.println(maxAvg.findMaxAverage(array,4));
    }
}
