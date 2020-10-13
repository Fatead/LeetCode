package string;

public class CountSegments {

    public int countSegments(String s) {
        String[] strings = s.split(" ");
        int count = 0;
        for(String str:strings){
            if(str.length()!=0){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountSegments countSegments = new CountSegments();
        System.out.println(countSegments.countSegments(""));
    }
}
