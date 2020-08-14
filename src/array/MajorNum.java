package array;

public class MajorNum {

    public int majorityElement(int[] nums) {
        if(nums.length == 0)return 0;
        int element = 0;
        element = nums[0];
        int count = 1;
        for(int i =1;i<nums.length;i++){
            if(nums[i] == element){
                count++;
            }else {
                count--;
                if(count <= 0){
                    element = nums[i];
                    count = 1;
                }
            }
        }
        return element;
    }

    public static void main(String[] args) {

    }
}
