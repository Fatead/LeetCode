package random;

import java.util.Random;

/**
 * 蓄水池抽样算法
 */
public class RandomNode {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }
    }

    ListNode head;

    public RandomNode(ListNode head) {
        this.head = head;
    }


    /** Returns a random node's value. */
    public int getRandom() {
        int count = 0;
        int reserve = 0;
        ListNode pt = head;
        Random random = new Random();
        while (pt!=null){
            count++;
            int rand = random.nextInt(count) + 1;
            if(rand == count){
                reserve = pt.val;
            }
            pt = pt.next;
        }
        return reserve;
    }

}