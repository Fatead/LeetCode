package string;

public class CheckRecord {

    public boolean checkRecord(String s) {
        int count = 0;
        int pos = 0;
        if(s.contains("LLL"))return false;
        while(pos<s.length() && count <=1){
            if(s.charAt(pos) == 'A'){
                count++;
            }
            pos++;
        }
        return count <= 1;

    }

    public static void main(String[] args) {
        CheckRecord checkRecord = new CheckRecord();
        System.out.println(checkRecord.checkRecord("PPALLP"));
    }

}
