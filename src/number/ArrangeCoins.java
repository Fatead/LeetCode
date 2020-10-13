package number;

public class ArrangeCoins {

    public int arrangeCoins(int n) {
        int count = 1;
        while(n>0){
            n = n-count;
            count++;
        }
        return count-2;
    }


    public static void main(String[] args) {
        ArrangeCoins arrangeCoins = new ArrangeCoins();
        System.out.println(arrangeCoins.arrangeCoins(8));
    }
}
