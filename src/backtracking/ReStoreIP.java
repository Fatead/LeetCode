package backtracking;

import java.util.ArrayList;
import java.util.List;

public class ReStoreIP {

    List<String> IPList = new ArrayList<>();

    private void backTrack(String s,int pos,int num, String ip){
        if(num  >= 4 && pos < s.length()){
            return;
        }else if(num == 4 && pos == s.length()){
            IPList.add(ip);
            return;
        }
        for(int i = 1;i <= 3;i++){
            if(pos + i <= s.length()){
                String addr = s.substring(pos,pos + i);
                int number = Integer.parseInt(addr);
                if(number>255||(addr.length()>1&&addr.startsWith("0")))break;
                String str = "";
                if(num != 0){
                    str = ip + "." + addr;
                }else {
                    str = addr;
                }
                backTrack(s,pos + i,num + 1,str);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if(len>12||len<4)return IPList;
        backTrack(s,0,0,"");
        return IPList;
    }

    public static void main(String[] args) {
        ReStoreIP reStoreIP = new ReStoreIP();
        String s = "010010";
        List<String> result = reStoreIP.restoreIpAddresses(s);
        System.out.println(result);
    }
}
