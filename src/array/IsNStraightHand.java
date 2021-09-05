package array;

import java.util.Arrays;

public class IsNStraightHand {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length%groupSize != 0){
            return false;
        }
        Arrays.sort(hand);
        boolean[] chosen = new boolean[hand.length];
        int count = 0;
        int curr = groupSize;
        int before = -1;
        while (count<hand.length){
            int lastCount = count;
            for (int i = 0; i <hand.length ; i++) {
                if(!chosen[i] && ( before == -1 || before + 1 == hand[i])){
                    chosen[i] = true;
                    before = hand[i];
                    curr--;
                    count++;
                    if(curr == 0){
                        before = -1;
                        curr = groupSize;
                        break;
                    }
                }
            }
            if(lastCount == count)break;
        }
        return count == hand.length;
    }

    public static void main(String[] args) {
        IsNStraightHand isNStraightHand = new IsNStraightHand();
        int[] hand = {1,2,3,3,2,1};
        System.out.println(isNStraightHand.isNStraightHand(hand,3));
    }

}
