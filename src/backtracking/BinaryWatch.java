package backtracking;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {

    List<String> timeList = new ArrayList<>();


    private void backTrack(int num,int[] nums,int start,int hour,int minute){
        if(num == 0){
            if(hour>11||minute>59)return;
            StringBuilder time = new StringBuilder();
            if(hour<10){
                time.append(hour).append(":");
            }else {
                time.append(hour).append(":");
            }
            if(minute<10){
                time.append("0").append(minute);
            }else {
                time.append(minute);
            }
            timeList.add(time.toString());
        }else {
            for(int i = start;i<nums.length;i++){
                if(i<4){
                    hour += nums[i];
                    backTrack(num-1,nums,i+1,hour,minute);
                    hour -= nums[i];
                }else {
                    minute += nums[i];
                    backTrack(num-1,nums,i+1,hour,minute);
                    minute -= nums[i];
                }
            }
        }
    }

    public List<String> readBinaryWatch(int num) {
        if(num<0)return timeList;
        int[] nums = {1,2,4,8,1,2,4,8,16,32};
        backTrack(num,nums,0,0,0);
        return timeList;
    }

    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        System.out.println(binaryWatch.readBinaryWatch(1));
    }

}
