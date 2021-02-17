package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LemonChange {

    /**
     * 最大堆的贪心算法，超时
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer integer, Integer t1) {
                return t1-integer;
            }

        });
        for (int i = 0; i <bills.length ; i++) {
            if(bills[i]==5){
                queue.add(bills[i]);
            }else {
                int money = bills[i];
                List<Integer> list = new ArrayList<>();
                while(!queue.isEmpty() && money>5){
                    if(queue.peek()>=money){
                        list.add(queue.poll());
                    }else {
                        money -=queue.poll();
                        if(money==5){
                            queue.add(bills[i]);
                            break;
                        }
                    }
                }
                if(money>5)return false;
                queue.addAll(list);
            }
        }
        return true;
    }


    public boolean lemonadeChange2(int[] bills){
        int five = 0,ten = 0;
        for (int bill : bills) {
            if(bill==5){
                five++;
            }else if(bill ==10){
                if(five>0){
                    five--;
                    ten++;
                }else {
                    return false;
                }
            }else {
                if(ten>0&&five>0){
                    ten--;
                    five--;
                }else if(five>2){
                    five-=3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LemonChange lemonChange = new LemonChange();
        int[] bills = {5,5,5,5,20,20,5,5,20,5};
        System.out.println(lemonChange.lemonadeChange(bills));
    }

}
