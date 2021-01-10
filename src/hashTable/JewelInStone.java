package hashTable;

import java.util.HashSet;
import java.util.Set;

public class JewelInStone {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <jewels.length() ; i++) {
            set.add(jewels.charAt(i));
        }
        int count = 0;
        for (int i = 0; i <stones.length() ; i++) {
            if(set.contains(stones.charAt(i)))count++;
        }
        return count;
    }

    public static void main(String[] args) {
        JewelInStone jewelInStone = new JewelInStone();
        System.out.println(jewelInStone.numJewelsInStones("aA","aAAbbbb"));
    }

}
