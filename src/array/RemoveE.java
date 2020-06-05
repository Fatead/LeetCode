package array;

public class RemoveE {

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        if(length<1)return 0;
        for(int i =0;i<length;i++){
            if(nums[i] == val){
                for(int j = i;j<length-1;j++){
                    nums[j] = nums[j+1];
                }
                length--;
                i--;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,1,2,3,3,3,3,5,3,6,1,4};
        RemoveE removeE = new RemoveE();
        System.out.println(removeE.removeElement(array,3));


    }
}
