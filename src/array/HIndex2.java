package array;

public class HIndex2 {


    public int hIndex(int[] citations){
        for(int i = 0;i<citations.length;i++){
            if(citations.length - i <= citations[i]){
                return citations.length - i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] citations = {7,7};
        HIndex2 hIndex = new HIndex2();
        System.out.println(hIndex.hIndex(citations));
    }
}
