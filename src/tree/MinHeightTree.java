package tree;

import java.util.*;

public class MinHeightTree {

    //自己的方法，超时
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> intMap = new HashMap<>();
        for(int[] arr:edges){
            Set<Integer> set1 = intMap.getOrDefault(arr[0],new HashSet<>());
            set1.add(arr[1]);
            intMap.put(arr[0],set1);
            Set<Integer> set2 = intMap.getOrDefault(arr[1],new HashSet<>());
            set2.add(arr[0]);
            intMap.put(arr[1],set2);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int[] heightArray = new int[n];
        Arrays.fill(heightArray,Integer.MAX_VALUE);
        if(edges.length == 0){
            result.add(0);
            return result;
        }
        for(int i = 0;i<n;i++){
            if(intMap.get(i).size()==1)continue;
            queue.add(i);
            int height = 0;
            Set<Integer> intSet = new HashSet<>();
            intSet.add(i);
            int currNum = 1;
            int nextNum = 0;
            while(intSet.size()<n){
                int curr = queue.poll();
                intSet.add(curr);
                currNum--;
                for(int num:intMap.get(curr)){
                    if(!intSet.contains(num)){
                        queue.add(num);
                        nextNum++;
                    }
                }
                if(currNum == 0){
                    height++;
                    currNum = nextNum;
                    nextNum = 0;
                }
            }
            heightArray[i] = height;
        }
        int min = Integer.MAX_VALUE;
        for(int i:heightArray){
            if(i<min){
                min = i;
            }
        }
        for(int i = 0;i<heightArray.length;i++){
            if(heightArray[i] == min){
                result.add(i);
            }
        }
        return result;
    }

    //从底向上开始找
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        Map<Integer, List<Integer>> intMap = new HashMap<>();
        for(int[] arr:edges){
            List<Integer> list1 = intMap.getOrDefault(arr[0],new LinkedList<>());
            list1.add(arr[1]);
            intMap.put(arr[0],list1);
            List<Integer> list2 = intMap.getOrDefault(arr[1],new LinkedList<>());
            list2.add(arr[0]);
            intMap.put(arr[1],list2);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] degree = new int[n];
        for(int i = 0;i<n;i++){
            degree[i] = intMap.get(i).size();
            if(degree[i] == 1){
                queue.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            result = new ArrayList<>();
            int size = queue.size();
            for(int i = 0;i<size;i++){
                int curr = queue.poll();
                result.add(curr);
                List<Integer> list = intMap.get(curr);
                for(int neighbor:list){
                    degree[neighbor]--;
                    if(degree[neighbor] == 1){
                        queue.add(neighbor);
                    }
                }
                intMap.put(curr,list);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        MinHeightTree minHeightTree = new MinHeightTree();
        int[][] edges = {
        };
        List<Integer> list = minHeightTree.findMinHeightTrees(1,edges);
        System.out.println(list);
    }

}
