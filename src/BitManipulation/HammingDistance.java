package BitManipulation;

public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int num =  x^y;
        int result = 0;
        while(num > 0){
            if(num%2 == 1)result++;
            num/=2;
        }
        return result;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(4,14));
    }

}
