package array;

public class MinArray {

    public int minArray(int[] numbers) {
        if(numbers.length==1)return numbers[0];
        int left = 0;
        int right = numbers.length-1;
        if(numbers[0]<numbers[numbers.length-1]){
            return numbers[0];
        }
        if(numbers[0]==numbers[numbers.length-1]){
            int min = Integer.MAX_VALUE;
            for (int number : numbers) {
                min = Math.min(min, number);
            }
            return min;
        }
        while(left<=right){
            int mid = left + (right-left)/2;
            if(mid>0 &&mid<numbers.length&& numbers[mid]<numbers[mid-1]){
                return numbers[mid];
            }
            if(numbers[mid]>=numbers[0]){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return numbers[0];
    }

    public static void main(String[] args) {
        MinArray minArray = new MinArray();
        int[] array = {10,1,10,10,10};
        System.out.println(minArray.minArray(array));
    }

}
