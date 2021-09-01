package array;
import java.util.*;

public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i : arr2) {
            map.put(i,0);
        }
        for (int i : arr1) {
            if(map.containsKey(i)){
                map.put(i,map.get(i) + 1);
            }else {
                list.add(i);
            }
        }
        Collections.sort(list);
        int count = 0;
        for (int i : arr2) {
            for (int j = 0; j <map.get(i) ; j++) {
                result[count++] = i;
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            result[count++] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        RelativeSortArray relativeSortArray = new RelativeSortArray();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        System.out.println(Arrays.toString(relativeSortArray.relativeSortArray(arr1, arr2)));
    }

}