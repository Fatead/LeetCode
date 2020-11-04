package number;

import java.util.Arrays;

public class ConstructRectangle {

    public int[] constructRectangle(int area) {
        int length = 0;
        int width = 0;
        int d = (int) Math.sqrt(area);
        while(area%d!=0){
            d++;
        }
        length = d;
        width = area/d;
        if(length>width) return new int[]{length,width};
        return new int[]{width,length};
    }

    public static void main(String[] args) {
        ConstructRectangle constructRectangle = new ConstructRectangle();
        int[] result = constructRectangle.constructRectangle(396);
        System.out.println(Arrays.toString(result));
    }

}
