package string;

public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        if(word.length()<=1)return true;
        if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'){
            if(word.charAt(1) >= 'A' && word.charAt(1) <= 'Z'){
                for(int i = 1;i<word.length();i++){
                    if(!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')){
                        return false;
                    }
                }
            }else {
                for(int i = 1;i<word.length();i++){
                    if(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z'){
                        return false;
                    }
                }
            }
        }else {
            for(int i = 0;i<word.length();i++){
                if(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z'){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DetectCapital detectCapital = new DetectCapital();
        boolean result =  detectCapital.detectCapitalUse("GooGle");
        System.out.println(result);
    }

}
