package string;

public class LicenseKey {

    public String licenseKeyFormatting(String S, int K) {
        String[] strings = S.split("-");
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:strings){
            stringBuilder.append(str.toUpperCase());
        }
        int len = stringBuilder.length();
        StringBuilder result = new StringBuilder();
        int size = len%K;
        char[] chars = stringBuilder.toString().toCharArray();
        for(int i = 0;i<size;i++){
            result.append(chars[i]);
        }
        if(result.length()!=0)
            result.append('-');
        for(int i = 0;i<len/K;i++){
            for(int j = 0;j<K;j++){
                result.append(chars[size + i*K +j]);
            }
            if(i!=len/K-1)
                result.append('-');
        }
        if(result.toString().endsWith("-")){
            return result.toString().substring(0,result.length()-1);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        LicenseKey licenseKey = new LicenseKey();
        System.out.println(licenseKey.licenseKeyFormatting("2-5g-3-J",2));
    }
}
