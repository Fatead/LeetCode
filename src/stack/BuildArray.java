package stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildArray {


    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        String push = "Push";
        String pop = "Pop";
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i : target) {
            set.add(i);
            max = Math.max(0,i);
        }
        for (int i = 1; i <=max ; i++) {
            if(!set.contains(i)){
                result.add(push);
                result.add(pop);
            }else {
                result.add(push);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BuildArray buildArray = new BuildArray();
        int[] target = {1,3};
        int n = 3;
        System.out.println(buildArray.buildArray(target,n));
    }

}
