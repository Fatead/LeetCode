package number;

public class AdditiveNum {


    private long fetchCurValue(String num, int l, int r) {
        if (l < r && num.charAt(l) == '0') {
            return -1;
        }
        long res = 0;
        while (l <= r) {
            res = res * 10 + num.charAt(l++) - '0';
        }
        return res;
    }

    public boolean dfs(String num,int len,int pos,long sum, long pre, int count){
        if(len == pos)return count > 2;
        for(int i = pos;i<len;i++){
            long curr = fetchCurValue(num,pos,i);
            if(curr < 0 || (count >= 2 && curr!=sum)){
                continue;
            }
            if(dfs(num,len,i +1,pre + curr,curr,count + 1)){
                return true;
            }
        }
        return false;
    }


    //使用回溯的思想
    public boolean isAdditiveNumber(String num) {
        return dfs(num,num.length(),0,0,0,0);
    }


    public static void main(String[] args) {
        AdditiveNum additiveNum = new AdditiveNum();
        System.out.println(additiveNum.isAdditiveNumber("199100199"));
    }
}
