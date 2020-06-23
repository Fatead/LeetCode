package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KthPermutation {

    public String getPermutation(int n, int k) {
        String ans = "";
        int base = IntStream.range(1, n).reduce(1, (a, b) -> a * b);
        List<Integer> list = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
        --k;
        while (n > 1) {
            ans = ans + String.valueOf(list.get(k / base));
            list.remove(k / base);
            k = k % base;
            base /= n-- - 1;
        }
        return ans + list.get(0);
    }

    public static void main(String[] args) {
        KthPermutation kthPermutation = new KthPermutation();
        System.out.println(kthPermutation.getPermutation(4,9));
    }
}
