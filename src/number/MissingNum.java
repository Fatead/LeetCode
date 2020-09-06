package number;

public class MissingNum {

    public int missingNumber(int[] nums) {
        int[] array = new int[nums.length+1];
        for(int i :nums){
            array[i] = 1;
        }
        for(int i = 0;i<array.length;i++){
            if(array[i] == 0){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        MissingNum missingNum = new MissingNum();
        int[] array = {3,0,1};
        System.out.println(missingNum.missingNumber(array));
    }
}
