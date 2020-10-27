package BitManipulation;

public class TotalHammingDistance {



    public int totalHammingDistance(int[] nums) {
        int totalNum = 0;
        for(int i = 0;i<32;i++){
            int count = 0;
            for(int j = 0;j<nums.length;j++){
                count+= nums[j]&1;
                nums[j] >>= 1;
            }
            totalNum += count*(nums.length - count);
        }
        return totalNum;
    }


    public static void main(String[] args) {
        int[] nums = {4,14};
        TotalHammingDistance totalHammingDistance = new TotalHammingDistance();
        int result = totalHammingDistance.totalHammingDistance(nums);
        System.out.println(result);
    }

}
