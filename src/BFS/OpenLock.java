package BFS;

import java.util.*;

public class OpenLock {

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        Set<String> hasSeen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        //使用null节点来标识一层节点遍历结束，开始下一层
        queue.offer(null);
        int depth = 0;
        while (!queue.isEmpty()){
            String node = queue.poll();
            if(node == null){
                depth++;
                if(queue.peek()!=null){
                    queue.offer(null);
                }
            }else if(node.equalsIgnoreCase(target)){
                return depth;
            }else if(!deadSet.contains(node)){
                for(int i = 0;i<4;i++){
                    for(int j = -1;j<=1;j+=2){
                        int y = (node.charAt(i)-'0' + j + 10)%10;
                        String nextElement = node.substring(0,i) + (y + "") + node.substring(i+1);
                        if(!hasSeen.contains(nextElement)){
                            queue.add(nextElement);
                            hasSeen.add(nextElement);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
        String[] deadEnds = {"0201","0101","0102","1212","2002"};
        System.out.println(openLock.openLock(deadEnds,"0202"));
    }

}
