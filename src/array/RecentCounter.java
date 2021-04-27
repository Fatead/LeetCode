package array;

import java.util.ArrayList;
import java.util.List;

public class RecentCounter {

    List<Integer> requests = new ArrayList<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        requests.add(t);
        int count = 1;
        for (int i = requests.size()-2; i >=0 ; i--) {
            if(requests.get(i) + 3000>=t){
                count++;
            }else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }

}
