package hashTable;

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {


    private int getPos(int pos,int length){
        while(pos<0){
            pos+=length;
        }
        return pos%length;
    }

    public boolean circularArrayLoop(int[] nums) {
        for(int i = 0;i<nums.length;i++){
            Set<Integer> set = new HashSet<>();
            Boolean forwardFlag = true;
            if(nums[i] < 0)forwardFlag = false;
            set.add(i);
            int pos = getPos(i + nums[i],nums.length);
            while(!set.contains(pos)){
                if((nums[pos]>0&&!forwardFlag)||(nums[pos]<0&&forwardFlag))break;
                set.add(pos);
                pos = getPos(pos + nums[pos],nums.length);
            }
            if(set.contains(pos) && pos!=getPos(pos + nums[pos],nums.length))return true;
        }
        return false;
    }


    public static void main(String[] args) {
        CircularArrayLoop circularArrayLoop = new CircularArrayLoop();
        int[] nums = {-2,1,-1,-2,-2};
        System.out.println(circularArrayLoop.circularArrayLoop(nums));
    }

}
