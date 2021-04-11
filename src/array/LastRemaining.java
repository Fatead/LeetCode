package array;

import java.util.ArrayList;

public class LastRemaining {

    /**
     * 超时
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int[] array = new int[n];
        int remain = n;
        int index= 0;
        while (remain>1){
            int count = m;
            while (count>0){
                if(array[index] == 0){
                    count--;
                    if(count == 0)break;
                }
                index++;
                index%=n;
            }
            System.out.println("删除的index是："+ index);
            array[index] = 1;
            remain--;
            index++;
            index%=n;
        }
        for (int i = 0; i <array.length ; i++) {
            if(array[i]==0){
                return i;
            }
        }
        return 0;
    }

    class ListNode{

        int value;
        ListNode next;

        public ListNode(int value){
            this.value = value;
        }

    }


    /**
     * 使用循环链表模拟超时
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        int remain = n;
        ListNode head = new ListNode(-1);
        ListNode pt = head;
        int count = 0;
        while (n>0){
            ListNode listNode = new ListNode(count++);
            n--;
            pt.next = listNode;
            pt = listNode;
        }
        pt.next = head.next;
        pt = pt.next;
        ListNode pre = head;
        int pos = m-1;
        while (remain>1){
            while (pos>0){
                pt = pt.next;
                pre = pre.next;
                pos--;
            }
            pre.next = pt.next;
            pt = pt.next;
            remain--;
            pos = m-1;
        }
        return pt.value;
    }


    public int lastRemaining3(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            list.add(i);
        }
        int index = 0;
        while (n>1){
            index = (index+m-1)%n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        LastRemaining lastRemaining = new LastRemaining();
        System.out.println(lastRemaining.lastRemaining2(10,17));
    }

}
