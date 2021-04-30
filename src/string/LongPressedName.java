package string;

public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        int posName = 0;
        int posType = 0;
        while (posName<name.length() && posType<typed.length()){
            int countName = 0;
            int countType = 0;
            char charName = name.charAt(posName);
            while (posName<name.length() && name.charAt(posName) == charName){
                countName++;
                posName++;
            }
            if(posType>=typed.length() || typed.charAt(posType)!=charName){
                return false;
            }
            while (posType<typed.length() && typed.charAt(posType) == charName){
                countType++;
                posType++;
            }
            if(countName>countType)return false;
        }
        return posName == name.length() && posType == typed.length();
    }

    public static void main(String[] args) {
        LongPressedName longPressedName = new LongPressedName();
        System.out.println(longPressedName.isLongPressedName("alexaa","aaleexxxxxaa"));
    }

}
