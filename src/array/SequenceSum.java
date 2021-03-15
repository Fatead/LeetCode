package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequenceSum {

    public static List<Integer> getSequence(int n,int len){
        List<Integer> list = new ArrayList<>();
        for (int i = len; i <= 100; i++) {//先从最短的情况判断
            //首先保证n要大于等于i * (i - 1) / 2
            //其次判断n和i * (i - 1) / 2的差是不是正好对当前元素的个数整除
            if ((n - i * (i - 1) / 2) >= 0 && (n - i * (i - 1) / 2) % i == 0) {
                int a = (n - i * (i - 1) / 2) / i;
                for (int j = 0; j < i; j++) {
                    list.add(a + j);
                }
                return list;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            int len = sc.nextInt();
            List<Integer> list = getSequence(n,len);
            if(list.size() == 0){
                System.out.println("no");
            }else {
                for (int i = 0; i < list.size(); i++) {
                    if(i == 0){
                        System.out.println(list.get(0));
                    }else {
                        System.out.println(list.get(i));
                    }
                }
            }
        }
    }

}
