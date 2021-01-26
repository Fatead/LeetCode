package string;

public class GoatLatin {

    public String toGoatLatin(String S) {
        String[] words = S.split(" ");
        char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for(String word:words){
            boolean vowelFlag = false;
            for(char vowel:vowels){
                if(word.charAt(0)==vowel){
                    stringBuilder.append(word).append("ma");
                    vowelFlag = true;
                    break;
                }
            }
            if(!vowelFlag){
                stringBuilder.append(word.substring(1)).append(word.charAt(0)).append("ma");
            }
            for (int i = 0; i <index ; i++) {
                stringBuilder.append("a");
            }
            stringBuilder.append(" ");
            index++;
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        GoatLatin goatLatin = new GoatLatin();
        System.out.println(goatLatin.toGoatLatin("I speak Goat Latin"));
    }

}
