package listTable;

import java.util.ArrayList;
import java.util.List;

public class SplitList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    private List<Integer> getPartLength(int length,int k){
        int size = length/k;
        int divide = length%k;
        List<Integer> result = new ArrayList<>();
        while(k>=0){
            if(divide>0){
                result.add(size+1);
                divide--;
            }else {
                result.add(size);
            }
            k--;
        }
        return result;
    }


    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode pt = root;
        while(pt!=null){
            length++;
            pt = pt.next;
        }
        List<Integer> partLength = getPartLength(length,k);
        ListNode[] listNodes = new ListNode[k];
        pt = root;
        for(int i = 0;i<k;i++){
            listNodes[i] = pt;
            if(pt==null)continue;
            for(int j = 0;j<partLength.get(i)-1;j++){
                pt = pt.next;
            }
            if(pt !=null){
                ListNode p = pt.next;
                pt.next = null;
                pt = p;
            }
        }
        return listNodes;
    }


    public static void main(String[] args) {

    }

}
