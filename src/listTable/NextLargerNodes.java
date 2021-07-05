package listTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextLargerNodes {

    private static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 暴力法
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode pt = head;
        while (pt!=null){
            list.add(pt.val);
            pt = pt.next;
        }
        int[] result = new int[list.size()];
        for (int i = 0; i <result.length-1 ; i++) {
            for (int j = i+1; j <result.length ; j++) {
                if(list.get(j) > list.get(i)){
                    result[i] = list.get(j);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 使用单调栈
     */
    public int[] nextLargerNodes2(ListNode head){
        List<Integer> list = new ArrayList<>();
        ListNode pt = head;
        while (pt!=null){
            list.add(pt.val);
            pt = pt.next;
        }
        int[] result = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        //从后往前倒着遍历
        for (int i = result.length - 1; i >= 0; i--) {
            int curr = list.get(i);
            //将栈中小于当前数的元素都pop出去，只保留大于当前元素的数据
            while (!stack.isEmpty() && stack.peek() <= curr){
                stack.pop();
            }
            result[i] = stack.isEmpty()?0:stack.peek();
            stack.add(curr);
        }
        return result;
    }

}
