package hashTable;

import java.util.HashSet;
import java.util.Set;

public class RandomizedSet {

    Set<Integer> numSet;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        numSet = new HashSet<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(numSet.contains(val))return false;
        numSet.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(numSet.contains(val)){
            numSet.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int size = numSet.size();
        int rand = (int)(Math.random()*size);
        int count = 0;
        for(int integer:numSet){
            if(count++ == rand){
                return integer;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
