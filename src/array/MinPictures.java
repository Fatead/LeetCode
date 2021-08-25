package array;
import java.util.Arrays;

public class MinPictures {

    private int findMinAge(int[] ages, boolean[] flags){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <ages.length ; i++) {
            if(ages[i]<min && !flags[i]){
                min = ages[i];
            }
        }
        return min;
    }

    private int[] minPicture(int[] ages){
        int count = 0;
        int n = ages.length;
        int[] result = new int[ages.length];
        boolean[] flags = new boolean[ages.length];
        while (count< ages.length){
            int minAge = findMinAge(ages,flags);
            for (int i = 0; i <result.length ; i++) {
                if(ages[i] == minAge){
                    int current = 0;
                    if(ages[i] > ages[(i- 1 + n) %n]){
                        current = Math.max(result[(i- 1 + n) %n],current);
                    }
                    if(ages[i] > ages[(i+1)%n]){
                        current = Math.max(result[(i+1)%n],current);
                    }
                    result[i] = current+1;
                    count++;
                    flags[i] = true;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        MinPictures minPictures = new MinPictures();
        int[] ages = {4,5,6,2,3,3};
        //2 3 4 1 2 1
        System.out.println(Arrays.toString(minPictures.minPicture(ages)));
    }

}