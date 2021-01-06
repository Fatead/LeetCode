package array;

import java.util.Arrays;

public class FloodFill {

    private void dfs(int[][] image,boolean[][] flagImage,int posX,int posY,int color,int value){
        if(posX<0||posY<0||posX>=image.length||posY>=image[0].length||image[posX][posY] != value||flagImage[posX][posY]){
            return;
        }
        image[posX][posY] = color;
        flagImage[posX][posY] = true;
        dfs(image,flagImage, posX-1, posY, color,value);
        dfs(image,flagImage,posX, posY-1, color,value);
        dfs(image,flagImage, posX+1, posY, color,value);
        dfs(image,flagImage,posX, posY+1, color,value);
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] flagImage = new boolean[image.length][image[0].length];
        dfs(image,flagImage,sr,sc,newColor,image[sr][sc]);
        return image;
    }


    public static void main(String[] args) {
        int[][] image = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };
        FloodFill floodFill = new FloodFill();
        int[][] result = floodFill.floodFill(image,1,1,2);
        System.out.println(Arrays.deepToString(result));
    }
}
