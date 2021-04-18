package string;

public class BuddyStrings {

    private boolean equalCharArray(char[] charsA,char[] charsB){
        for (int i = 0; i <charsA.length ; i++) {
            if(charsA[i]!=charsB[i])return false;
        }
        return true;
    }


    public boolean buddyStrings(String a, String b) {
        if(a.length()!=b.length())return false;
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        for (int i = 0; i <charsA.length ; i++) {
            for (int j = i+1; j <charsA.length ; j++) {
                char temp = charsA[i];
                charsA[i] = charsA[j];
                charsA[j] = temp;
                if(equalCharArray(charsA,charsB))return true;
                temp = charsA[i];
                charsA[i] = charsA[j];
                charsA[j] = temp;
            }
        }
        return false;
    }

    public boolean buddyStrings2(String a,String b){
        if(a.length()!=b.length())return false;
        int[] arrayA = new int[26];
        int[] arrayB = new int[26];
        int diffCount = 0;
        for (int i = 0; i <a.length() ; i++) {
            if(a.charAt(i)!=b.charAt(i)){
                diffCount++;
            }
            arrayA[a.charAt(i)-'a']++;
            arrayB[b.charAt(i)-'a']++;
        }
        if(diffCount>2)return false;
        boolean repeatChar = false;
        for (int i = 0; i <arrayA.length ; i++) {
            if(arrayA[i]!=arrayB[i])return false;
            if(arrayA[i]>1)repeatChar = true;
        }
        if((diffCount == 0 || diffCount == 1) && !repeatChar){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        BuddyStrings buddyStrings = new BuddyStrings();
        System.out.println(buddyStrings.buddyStrings2("","aaaaaaacb"));
    }

}
