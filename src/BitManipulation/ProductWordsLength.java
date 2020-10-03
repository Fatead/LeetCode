package BitManipulation;

public class ProductWordsLength {


    public int bitNumber(char ch) {
        return (int)ch - (int)'a';
    }

    public boolean noCommonLetters(String s1, String s2) {
        int bitmask1 = 0, bitmask2 = 0;
        for (char ch : s1.toCharArray())
            bitmask1 |= 1 << bitNumber(ch);
        for (char ch : s2.toCharArray())
            bitmask2 |= 1 << bitNumber(ch);
        return (bitmask1 & bitmask2) == 0;
    }

    public int maxProduct(String[] words) {
        int maxLength = 0;
        for(int i = 0;i<words.length;i++){
            for(int j = i;j<words.length;j++){
                if(noCommonLetters(words[i],words[j])){
                    maxLength = Math.max(maxLength,words[i].length()*words[j].length());
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {

    }
}
