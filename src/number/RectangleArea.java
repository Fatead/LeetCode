package number;

public class RectangleArea {


    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = 0;
        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);
        if(area1 == 0)return area2;
        if(area2 == 0)return area1;
        if(C>=G&&D>=H&&E>=A&&F>=B){
            area = area1;
        }else if(G>C&&H>D&&A>E&&B>F){
            area = area2;
        }else if(B>=H||F>=D||E>=C||A>=G){
            area = area1 + area2;
        }else if(E>=A&&D>=H&&C<=G&&B>=F){
            area = area1 + area2 - (C-E)*(H-B);
        }else if(A>=E&&H>=D&&G<=C&&F>=B){
            area = area1 + area2 -(G-A)*(D-F);
        }else if(E>=A&&F>=B&&G>=C&&H>=D){
            area = area1 + area2 -(C-E)*(D-F);
        }else if(A>=E&&B>=F&&C>=G&&D>=H){
            area = area1 + area2 -(G-A)*(H-B);
        }
        return area;
    }

    private int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);
        if(area1 == 0)return area2;
        if(area2 == 0)return area1;
        if(B>=H||F>=D||E>=C||A>=G){
            return area1 + area2;
        }else {
            int down = Math.max(B,F);
            int up = Math.min(D,H);
            int left = Math.max(A,E);
            int right = Math.min(C,G);
            return area1 + area2 - (up-down)*(right - left);
        }
    }

    public static void main(String[] args) {
        RectangleArea rectangleArea = new RectangleArea();
        int area =  rectangleArea.computeArea2(-2,-2,2,2,3,3,4,4);
        System.out.println(area);
    }
}
