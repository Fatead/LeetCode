package array;

public class MountainArray {

    public boolean validMountainArray(int[] arr) {
        if(arr.length<3)return false;
        boolean flag = false;
        if(arr[0]>arr[1])return false;
        for (int i = 0; i <arr.length-1 ; i++) {
            if(!flag){
                if(arr[i]>arr[i+1]){
                    flag = true;
                }else if(arr[i] == arr[i+1]){
                    return false;
                }
            }else {
                if(arr[i]<=arr[i+1]){
                    return false;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        int[] nums = {0,1,2,3,4,5};
        System.out.println(mountainArray.validMountainArray(nums));
    }

}
