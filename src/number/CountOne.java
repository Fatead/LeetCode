package number;

public class CountOne {


    private int[] convert(int n){
        int[] array = new int[Integer.SIZE];
        for(int i = 0;i<Integer.SIZE;i++){
            array[i] = (n>>i)&1;
        }
        return array;
    }

    public int hammingWeight(int n) {
        int[] array = convert(n);
        int count = 0;
        for(int i:array){
            if(i == 1)count++;
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
