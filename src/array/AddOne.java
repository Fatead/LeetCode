package array;
import java.util.Arrays;

public class AddOne {

    public int[] plusOne(int[] digits) {
        if(digits.length == 0)return new int[0];
        boolean flag = false;
        for(int i = digits.length-1;i>=0;i--){
            if(i == 0&&(digits[i] + 1 == 10)){
                flag = true;
            }
            else if(digits[i] + 1<10){
                digits[i] +=1;
                break;
            }else {
                digits[i] = 0;
            }
        }
        if(flag){
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }else return digits;
    }

    public static void main(String[] args) {
        int[] array = {
                9,9,9,8
        };
        AddOne addOne = new AddOne();
        int[] result = addOne.plusOne(array);
        System.out.println(Arrays.toString(result));
    }
}
