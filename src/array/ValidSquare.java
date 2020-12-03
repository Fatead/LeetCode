package array;

/**
 * 首先，判定四个点形成一个矩形
 * 并且相邻两边相等
 */
public class ValidSquare {

    private double getDistance(int[] p1, int[] p2){
        return (p1[0] - p2[0])*(p1[0] - p2[0]) + (p1[1] - p2[1])*(p1[1] - p2[1]);
    }

    private boolean valid(int[] p1, int[] p2, int[] p3, int[] p4){
        return (getDistance(p1, p2) == getDistance(p3,p4)) && (getDistance(p1, p4) == getDistance(p2,p3)) && (getDistance(p1, p3) == getDistance(p2,p4)) && ((getDistance(p1,p3) == getDistance(p1,p4)) || (getDistance(p1,p2) == getDistance(p1,p3) || (getDistance(p1,p4) == getDistance(p1,p2))));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if(!valid(p1,p2,p3,p4)){
            return false;
        }
        return getDistance(p1, p2) != 0 && getDistance(p1, p4) != 0 && getDistance(p1, p3) != 0;
    }

    public static void main(String[] args) {
        ValidSquare validSquare = new ValidSquare();
        int[] p1 = {1,1};
        int[] p2 = {1,1};
        int[] p3 = {1,1};
        int[] p4 = {1,1};
        System.out.println(validSquare.validSquare(p1,p2,p3,p4));
    }

}
