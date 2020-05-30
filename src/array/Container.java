package array;

import java.util.ArrayList;
import java.util.List;

public class Container {


    public int maxArea(int[] height) {
        int max = 0;
        if(height.length == 2)return Math.min(height[0],height[1]);
        int i = 0,j =  height.length-1;
        List<Integer> numList = new ArrayList<>();
        numList.add(Math.min(height[i],height[j])*(j-i));
        while(i+1<j){
            if(height[i]<height[j]){
                i++;
            }else {
                j--;
            }
            numList.add(Math.min(height[i],height[j])*(j-i));
        }
        for(Integer integer:numList){
            if(integer>max)max = integer;
        }
        return max;
    }

    public int MaxArea(int[] height){
        List<Integer> numList = new ArrayList<>();
        for(int i =0;i<height.length-1;i++){
            for(int j = 0;j<height.length;j++){
                numList.add(Math.min(height[i],height[j])*(j-i));
            }
        }
        int max = 0;
        for(Integer integer:numList){
            if(integer>max){
                max = integer;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Container container = new Container();
        int[] array = {1,8,6,2,5,4,8,3,7};
        System.out.println(container.maxArea(array));
    }
}
