package string;

public class CompareBackSpace {

    public boolean backspaceCompare(String S, String T) {
        StringBuilder SNew = new StringBuilder();
        for (int i = 0; i <S.length() ; i++) {
            if(S.charAt(i)=='#'){
                if(SNew.length()>0){
                    SNew = new StringBuilder(SNew.substring(0,SNew.length()-1));
                }
            }else {
                SNew.append(S.charAt(i));
            }
        }
        StringBuilder TNew = new StringBuilder();
        for (int i = 0; i <T.length() ; i++) {
            if(T.charAt(i)=='#'){
                if(TNew.length()>0){
                    TNew = new StringBuilder(TNew.substring(0,TNew.length()-1));
                }
            }else {
                TNew.append(T.charAt(i));
            }
        }
        return SNew.toString().equals(TNew.toString());
    }

    public static void main(String[] args) {
        CompareBackSpace compareBackSpace = new CompareBackSpace();
        System.out.println(compareBackSpace.backspaceCompare("ab#c","ad#c"));
    }

}
