package array;

public class CheckPossibility {

    private boolean increase(int[] nums,int pos){
        int[] array = new int[nums.length-1];
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            if(i == pos)continue;
            array[count++] = nums[i];
        }
        for(int i = 0;i<array.length-1;i++){
            if(array[i]>array[i+1])return false;
        }
        return true;
    }

    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int pos1 = 0;
        int pos2 = 0;
        if(nums.length<=1)return true;
        for(int i = 0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                count++;
                pos1 = i;
                pos2 = i+1;
            }
        }
        if(count == 0)return true;
        if(count>1)return false;
        return increase(nums,pos1)||increase(nums,pos2);
    }

    public static void main(String[] args) {
        CheckPossibility checkPossibility = new CheckPossibility();
        int[] nums = {3,4,2,3};
        System.out.println(checkPossibility.checkPossibility(nums));
    }

}