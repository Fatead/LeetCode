package array;

public class ValidTriangle {

    private boolean valid(int a,int b,int c){
        return a + b > c && a + c > b && b + c > a && a > 0 & b > 0 & c > 0;
    }

    public int triangleNumber(int[] nums) {
        if(nums.length<3)return 0;
        int count = 0;
        for(int i = 0;i<nums.length-2;i++){
            for(int j = i+1;j<nums.length-1;j++){
                for(int k = j+1;k<nums.length;k++){
                    if(valid(nums[i],nums[j],nums[k]))count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        ValidTriangle validTriangle = new ValidTriangle();
        int[] nums = {2,2,3,4};
        System.out.println(validTriangle.triangleNumber(nums));
    }

}
