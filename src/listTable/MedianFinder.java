package listTable;

import java.util.*;

class MedianFinder {

    LinkedList<Integer> list;

    /** initialize your data structure here. */
    public MedianFinder() {
        list = new LinkedList<>();
    }

    public void addNum(int num) {
        int insertPos = list.size();
        if(insertPos == 0){
            list.add(num);
            return;
        }
        if(list.get(0) > num){
            list.add(0,num);
            return;
        }
        int left = 0;
        int right = list.size()-1;
        while(left<=right){
            insertPos = left + ((right - left)>>1);
            if(num == list.get(insertPos)){
                break;
            }else if(num < list.get(insertPos)){
                right = insertPos -1;
            }else {
                left = insertPos + 1;
            }
        }
        while(insertPos + 1 <= list.size() && list.get(insertPos) < num)insertPos++;
        while(insertPos - 1 >= 0 && list.get(insertPos - 1) > num)insertPos--;
        list.add(insertPos,num);
    }

    public double findMedian() {
        if(list.size()%2 == 1){
            return list.get(list.size()/2);
        }else {
            return (double) (list.get(list.size()/2-1) + list.get(list.size()/2))/2;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        //System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        medianFinder.addNum(10);
        medianFinder.addNum(8);
        medianFinder.addNum(15);
        medianFinder.addNum(0);
        //System.out.println(medianFinder.findMedian());
    }
}
