package BitManipulation;

public class SingleNum {


    /**
     * 分组位运算
     */
    public int[] singleNumbers(int[] nums){
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        int div = 1;
        //div循环左移,找到位运算中第一位为1的
        while ((div&result) == 0){
            div<<=1;
        }
        //用a和b分别存储分组计算的结果
        int a = 0,b = 0;
        for (int num : nums) {
            if((num&div) == 0){
                a ^=num;
            }else {
                b ^=num;
            }
        }
        return new int[]{a,b};
    }


    public static void main(String[] args) {

    }

}
