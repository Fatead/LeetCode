package slidingWindows;

/**
 * 把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」。
 */
public class LongestOnes {

    public int longestOnes(int[] A, int K) {
        int result = 0;
        int left = 0, right = 0;
        int zeros = 0;
        while (right<A.length){
            if(A[right]==0){
                zeros++;
            }
            //当区间内zero的个数大于限制的时候，左指针右移
            while (zeros>K){
                if(A[left++]==0){
                    zeros--;
                }
            }
            result = Math.max(result,right-left+1);
            right++;
        }
        return result;
    }

    public static void main(String[] args) {
        LongestOnes longestOnes = new LongestOnes();
        int[] A = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(longestOnes.longestOnes(A,2));
    }

}