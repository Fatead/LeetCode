package greedy;

public class PlaceFlower {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int[] array = new int[flowerbed.length + 2];
        for(int i = 0;i<flowerbed.length;i++){
            array[i+1] = flowerbed[i];
        }
        for(int i = 1;i<flowerbed.length+1;i++){
            if(array[i] == 0 && array[i-1] == 0 && array[i+1] == 0){
                count++;
                i++;
            }
        }
        return count>=n;
    }

    public static void main(String[] args) {
        PlaceFlower placeFlower = new PlaceFlower();
        int[] array = {1,0,0,0,1};
        System.out.println(placeFlower.canPlaceFlowers(array,1));
    }
}
