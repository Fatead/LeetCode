package number;

public class RepeatedNTimes {

    public int repeatedNTimes(int[] nums) {
        int chosen = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] == chosen){
                count++;
            }else if(count<=1){
                chosen = nums[i];
            }else {
                count--;
            }
        }
        count = 0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i] == chosen){
                count++;
            }
        }
        if(count == nums.length/2){
            return chosen;
        }
        return nums[nums.length-1];
    }

    public static void main(String[] args) {
        RepeatedNTimes repeatedNTimes = new RepeatedNTimes();
        int[] nums = {2,1,2,5,3,2};
        System.out.println(repeatedNTimes.repeatedNTimes(nums));
    }

}
