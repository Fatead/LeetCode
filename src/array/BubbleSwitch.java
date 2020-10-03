package array;

public class BubbleSwitch {


    private void reverse(int[] array,int pos){
        if(array[pos] == 1){
            array[pos] = 0;
        }else {
            array[pos] = 1;
        }
    }

    public int bulbSwitch(int n) {
        int[] array = new int[n];
        for(int i = 1;i<=n;i++){
            for(int j = i-1;j<n;j+=i){
                reverse(array,j);
            }
        }
        int count = 0;
        for(int i = 0;i<n;i++){
            if(array[i] == 1)count++;
        }
        return count;
    }

    public static void main(String[] args) {
        BubbleSwitch bubbleSwitch = new BubbleSwitch();
        System.out.println(bubbleSwitch.bulbSwitch(3));
    }

}
