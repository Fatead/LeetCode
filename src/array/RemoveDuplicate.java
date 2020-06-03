package array;

public class RemoveDuplicate {

    public int removeDuplicates(int[] nums) {
        int newLength = nums.length;
        for(int i = 0;i<newLength-1;i++){
            if(nums[i]==nums[i+1]){
                for(int j = i;j<newLength-1;j++){
                    nums[j] = nums[j+1];
                }
                newLength--;
                i--;
            }
        }
        return newLength;
    }

    public int remove(int[] nums){
        int length = nums.length;
        if(length == 0)return 0;
        int i = 0;
        for(int j = 1;j<length;j++){
            if(nums[i]!=nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return ++i;
    }

    public static void main(String[] args) {
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        int[] nums = new int[]{6, 6, 6, 6, 6,3,3,3};
        System.out.println(removeDuplicate.removeDuplicates(nums));

    }
}
