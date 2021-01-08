package number;

public class DominantIndex {

    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>max){
                max = nums[i];
                index = i;
            }
        }
        for(int i = 0;i<nums.length;i++){
            if(i == index)continue;
            if(nums[i]*2>max)return -1;
        }
        return index;
    }


    public static void main(String[] args) {
        DominantIndex dominantIndex = new DominantIndex();
        int[] nums = {1,2,3,4};
        System.out.println(dominantIndex.dominantIndex(nums));
    }

}
