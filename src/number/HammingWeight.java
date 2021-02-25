package number;

public class HammingWeight {

    public int hammingWeight(int n) {
        boolean flag = true;
        if(n<0)flag = false;
        int result = 0;
        while (n!=0){
            if(n%2==1||n%2==-1){
                result++;
            }
            n/=2;
        }
        if(!flag){
            if(result==1){
                return 1;
            }else if(result==32){
                return 1;
            } else {
                return 32-result+1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HammingWeight hammingWeight = new HammingWeight();
        System.out.println(hammingWeight.hammingWeight(56));
    }

}
