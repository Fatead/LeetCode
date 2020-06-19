package array;

public class JumpGame {

    public boolean canJump(int[] nums) {
        if(nums.length == 0)return false;
        int[] array = new int[nums.length];
        for(int i = 0;i<nums.length;i++){
            if(i == 0){
                array[i] = nums[0];
            }else {
                array[i] = Math.max(array[i - 1], (nums[i] + i));
            }
            if(i == nums.length-1 && i<=array[i])return true;
            else if(array[i] < i+1)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = {1,1,0};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(array));
    }

}
