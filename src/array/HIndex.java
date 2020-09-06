package array;

public class HIndex {

    public int hIndex(int[] citations) {
        if(citations.length == 0)return 0;
        int hIndex = 0;
        while(hIndex<citations.length){
            int count = 0;
            for(int i:citations){
                if(i>=hIndex){
                    count++;
                }
            }
            if(count<hIndex){
                return --hIndex;
            }else if(count == 0&&hIndex ==0){
                return 0;
            }else {
                hIndex++;
            }
        }
        int count = 0;
        for(int i:citations){
            if(i>=hIndex){
                count++;
            }
        }
        if(count == hIndex){
            return hIndex;
        }else {
            return --hIndex;
        }
    }

    public static void main(String[] args) {
        int[] citations = {3,0,6,1,5};
        HIndex hIndex = new HIndex();
        System.out.println(hIndex.hIndex(citations));
    }
}
