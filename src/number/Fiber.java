package number;

import java.util.HashMap;
import java.util.Map;

public class Fiber {

    public int fib(int n) {
        if(n==0)return 0;
        if(n==1)return 1;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        for (int i = 2; i <=n ; i++) {
            int value = map.get(i-1) + map.get(i-2);
            map.put(i,value);
        }
        return map.get(n);
    }

    public static void main(String[] args) {
        Fiber fiber = new Fiber();
        System.out.println(fiber.fib(5));
    }

}