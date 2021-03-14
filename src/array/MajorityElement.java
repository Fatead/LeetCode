package array;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        int count = 1;
        int num = nums[0];
        for (int i = 1; i <nums.length; i++) {
            if(nums[i]==num){
                count++;
            }else {
                count--;
                if(count == 0){
                    count = 1;
                    num = nums[i];
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement.majorityElement(nums));
    }

}
