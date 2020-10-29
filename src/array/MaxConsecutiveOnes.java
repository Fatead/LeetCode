package array;

public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i:nums){
            stringBuilder.append(i);
        }
        String[] strings = stringBuilder.toString().split("0");
        int max = 0;
        for(String string:strings){
            if(string.length()>max){
                max = string.length();
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        int result = maxConsecutiveOnes.findMaxConsecutiveOnes(nums);
        System.out.println(result);
    }

}
