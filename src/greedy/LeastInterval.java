package greedy;

import java.util.HashMap;
import java.util.Map;

public class LeastInterval {

    public int leastInterval(char[] tasks, int n) {
        if(n == 0)return tasks.length;
        //用于计数的map
        Map<Character,Integer> countMap = new HashMap<>();
        //用于记录冷却时间的map
        Map<Character,Integer> timeMap = new HashMap<>();
        for(char c:tasks){
            countMap.put(c,countMap.getOrDefault(c,0) + 1);
            timeMap.put(c,0);
        }
        int totalTime = 0;
        int charCount = 0;
        while(charCount < tasks.length){
            //新一轮任务分配开始，所有冷却时间减一
            totalTime++;
            for(char c:timeMap.keySet()){
                if(timeMap.get(c)>0){
                    timeMap.put(c,timeMap.get(c) - 1);
                }
            }
            //如果当前冷却时间为0并且计数大于0的任务不存在，则等待
            boolean flag = false;
            for(char c:countMap.keySet()){
                if(countMap.get(c) > 0 && timeMap.get(c) == 0){
                    flag = true;
                }
            }
            if(!flag){
                continue;
            }
            //否则则选择冷却时间为0并且计数最大的任务
            int maxCount = 0;
            char maxChar = tasks[0];
            for(char c:countMap.keySet()){
                if(timeMap.get(c) == 0 && maxCount < countMap.get(c)){
                    maxCount = countMap.get(c);
                    maxChar = c;
                }
            }
            charCount++;
            timeMap.put(maxChar,n+1);
            countMap.put(maxChar,countMap.get(maxChar)-1);
        }
        return totalTime;
    }


    public static void main(String[] args) {
        LeastInterval leastInterval = new LeastInterval();
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(leastInterval.leastInterval(tasks,2));
    }

}
