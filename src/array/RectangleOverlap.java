package array;

public class RectangleOverlap {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1[0]==rec1[2]||rec1[1]==rec1[3]||rec2[0]==rec2[2]||rec2[1]==rec2[3]){
            return false;
        }
        if(rec1[2]<=rec2[0]||rec1[0]>=rec2[2]){
            return false;
        }
        if(rec1[1]>=rec2[3]||rec1[3]<=rec2[1]){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RectangleOverlap rectangleOverlap = new RectangleOverlap();
        int[] rec1 = {0,0,2,2};
        int[] rec2 = {1,1,3,3};
        System.out.println(rectangleOverlap.isRectangleOverlap(rec1,rec2));
    }

}
