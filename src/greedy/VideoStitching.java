package greedy;

public class VideoStitching {

    public int videoStitching(int[][] clips, int time) {
        int[] maxNums = new int[time];
        for (int[] clip : clips) {
            if(clip[0] < time){
                maxNums[clip[0]] = Math.max(clip[1],maxNums[clip[0]]);
            }
        }
        int result = 0, pre = 0, last = 0;
        for (int i = 0; i <time ; i++) {
            last = Math.max(last, maxNums[i]);
            if(last == i)return -1;
            //用完了一个被使用的子区间
            if(pre == i){
                result++;
                pre = last;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        VideoStitching videoStitching = new VideoStitching();
        int[][] clips = {
                {0,2},
                {4,6},
                {8,10},
                {1,9},
                {1,5},
                {5,9}
        };
        System.out.println(videoStitching.videoStitching(clips,10));
    }

}
