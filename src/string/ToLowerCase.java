package string;

public class ToLowerCase {

    public String toLowerCase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i)>='A'&&str.charAt(i)<='Z'){
                stringBuilder.append((char)(str.charAt(i) + 'a'-'A'));
            }else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        ToLowerCase toLowerCase = new ToLowerCase();
        System.out.println(toLowerCase.toLowerCase("HellO"));

    }

}
