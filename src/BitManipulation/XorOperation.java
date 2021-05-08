package BitManipulation;

public class XorOperation {

    public int xorOperation(int n, int start) {
        int result = 0;
        for (int i = 0; i <n ; i++) {
            result ^= start + 2*i;
        }
        return result;
    }

    public static void main(String[] args) {
        XorOperation xorOperation = new XorOperation();
        System.out.println(xorOperation.xorOperation(10,5));
    }
}
