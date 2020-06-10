package array;

public class SearchRotateArray {


    private int searchInArr(int[] nums, int begin_pos, int end_pos, int target){
        if((begin_pos == end_pos && nums[begin_pos]!=target)||begin_pos>end_pos)return -1;
        else if((begin_pos == end_pos && nums[begin_pos]==target))return begin_pos;
        if(begin_pos == end_pos-1 && nums[begin_pos]!=target && nums[end_pos]!=target)return -1;
        else if(begin_pos == end_pos-1 && nums[begin_pos]==target)return begin_pos;
        else if(begin_pos == end_pos-1 && nums[end_pos]==target)return end_pos;
        int media_pos = (begin_pos+end_pos)/2;
        int media = nums[media_pos];
        if(nums[begin_pos]<=media&&media<=nums[end_pos]){
            if(target<=media){
                return searchInArr(nums,begin_pos,media_pos,target);
            }else {
                return searchInArr(nums,media_pos+1,end_pos,target);
            }
        }
        if(media<=nums[begin_pos]){
            if(target>=nums[begin_pos]||target<=media){
                return searchInArr(nums,begin_pos,media_pos,target);
            }else{
                return searchInArr(nums,media_pos+1,end_pos,target);
            }
        }else {
            if(target>=media||target<=nums[end_pos]){
                return searchInArr(nums,media_pos,end_pos,target);
            }else{
                return searchInArr(nums,begin_pos,media_pos-1,target);
            }
        }
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0)return -1;
        return searchInArr(nums,0,len-1,target);
    }

    public int search2(int[] nums, int target){
        int len = nums.length;
        if(len == 0)return -1;
        int left = 0,right = nums.length-1;
        if(len==1)return nums[0]==target?0:-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid] == target)return mid;
            if(nums[0]<=nums[mid]){
                if(target>=nums[0]&&target<nums[mid]){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                if(target>nums[mid]&&target<=nums[len-1]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotateArray searchRotateArray = new SearchRotateArray();
        int[] array = {1,2};
        System.out.println(searchRotateArray.search(array,10));
    }
}
