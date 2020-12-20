package backtracking;
import java.util.ArrayList;
import java.util.List;

public class SplitArray {

    private boolean flag = false;

    private void removeElement(List<Integer> list,int i){
        list.remove(list.lastIndexOf(i));
    }

    private void dfs(List<Integer> list){
        if(list.size() == 0){
            flag = true;
            return;
        }
        List<Integer> choseList = new ArrayList<>();
        for (Integer integer : list) {
            if (choseList.contains(integer)) {
                continue;
            }
            if (choseList.size() != 0 && integer - choseList.get(choseList.size() - 1) > 1) {
                break;
            }
            choseList.add(integer);
            if(choseList.size()>=3){
                List<Integer> newList = new ArrayList<>(list);
                for(int i:choseList){
                    removeElement(newList,i);
                }
                if(newList.size() == 0){
                    flag = true;
                    return;
                }
                if(newList.size()>=3){
                    dfs(newList);
                }
            }
        }
    }


    public boolean isPossible(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i:nums){
            list.add(i);
        }
        dfs(list);
        return flag;
    }


    public static void main(String[] args) {
        SplitArray splitArray = new SplitArray();
        int[] nums = {1,2,3,3,4,4,5};
        System.out.println(splitArray.isPossible(nums));
    }

}