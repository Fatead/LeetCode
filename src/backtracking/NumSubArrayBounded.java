package backtracking;

/**
 * 暴力解法，超时
 * 改用滑动窗口
 */
public class NumSubArrayBounded {

    private boolean isSubArrayBounded(int[] A,int left,int right,int L,int R){
        int max = A[left];
        for(int i = left;i<=right;i++){
            if(A[i]>R)return false;
            max = Math.max(max,A[i]);
        }
        return max>=L&&max<=R;
    }


    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int count = 0;
        for(int i = 0;i<A.length;i++){
            //子数组开始的位置
            for(int j = 0;j+i<A.length;j++){
                if(isSubArrayBounded(A,j,j+i,L,R)){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 滑动窗口的方法
     */
    public int numSubarrayBoundedMax2(int[] A, int L, int R) {
        int count = 0;
        int left = -1;
        int right = -1;
        //当窗口的大小大于0时，窗口肯定为符合条件的子数组
        for(int i = 0;i<A.length;i++){
            //如果当前元素大于R的话，说明包含当前元素的子数组不符合题意，被排除，此使将窗口的left和right更新为当前坐标
            if(A[i] <=R){
                //如果当前元素在区间内，则从后向前看，计数公式为当前元素的坐标减去窗口第一个元素的坐标
                if(A[i]>=L){
                    right = i;
                    count += i-left;
                }else {
                    //如果当前元素小于L,则形成的子数组中必须包含之前窗口中的元素
                    count += right-left;
                }
            }else {
                left = i;
                right = i;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        NumSubArrayBounded numSubArrayBounded = new NumSubArrayBounded();
        int[] A = {2, 1, 4, 3};
        System.out.println(numSubArrayBounded.numSubarrayBoundedMax2(A,2,3));
    }

}
