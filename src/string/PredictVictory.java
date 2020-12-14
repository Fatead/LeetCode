package string;

public class PredictVictory {


    private boolean sameSenate(StringBuilder stringBuilder){
        if(stringBuilder.length()<2)return true;
        for(int i = 0;i<stringBuilder.length()-1;i++){
            if(stringBuilder.charAt(i)!=stringBuilder.charAt(i+1))return false;
        }
        return true;
    }


    public String predictPartyVictory(String senate) {
        int RCount = 0;
        int DCount = 0;
        while (senate.length()>0){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0;i<senate.length();i++){
                if(senate.charAt(i) == 'R'){
                    if(RCount>0){
                        RCount--;
                    }else {
                        DCount++;
                        stringBuilder.append("R");
                    }
                }else if(senate.charAt(i) == 'D'){
                    if(DCount>0){
                        DCount--;
                    }else {
                        RCount++;
                        stringBuilder.append("D");
                    }
                }
            }
            if(sameSenate(stringBuilder)){
                if(stringBuilder.toString().contains("R"))return "Radiant";
                return "Dire";
            }
            senate = stringBuilder.toString();
        }
        return "Dire";
    }

    public static void main(String[] args) {
        PredictVictory predictVictory = new PredictVictory();
        System.out.println(predictVictory.predictPartyVictory("RRDDDD"));
    }

}
