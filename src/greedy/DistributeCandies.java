package greedy;

import java.util.HashSet;
import java.util.Set;

public class DistributeCandies {

    public int distributeCandies(int[] candyType) {
        Set<Integer> candiesSet = new HashSet<>();
        for(int i:candyType){
            candiesSet.add(i);
        }
        if(candiesSet.size()>candyType.length/2){
            return candyType.length/2;
        }else {
            return candiesSet.size();
        }
    }

    public static void main(String[] args) {
        DistributeCandies distributeCandies = new DistributeCandies();
        int[] candies = {1,1,2,3};
        int result = distributeCandies.distributeCandies(candies);
        System.out.println(result);
    }

}