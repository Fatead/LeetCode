package backtracking;
import java.util.ArrayList;
import java.util.List;

public class SplitIntoFibonacci {

    List<Integer> result = new ArrayList<>();

    private void dfs(String S,int index,List<Integer> numList){
        if(S.length() <= index){
            return;
        }
        if(numList.size()<2){
            for(int i = index+1;i<S.length()+1;i++){
                if(S.charAt(index)=='0'&&i-index>1)break;
                if(i-index>10||Long.parseLong(S.substring(index,i))>Integer.MAX_VALUE)continue;
                int num = Integer.parseInt(S.substring(index,i));
                numList.add(num);
                dfs(S,i,new ArrayList<>(numList));
                numList.remove(numList.size()-1);
            }
        }else {
            int num1 = numList.get(numList.size()-1);
            int num2 = numList.get(numList.size()-2);
            for (int i = index+1; i <S.length()+1 ; i++) {
                if(S.charAt(index)=='0'&&i-index>1)break;
                if(i-index>10||Long.parseLong(S.substring(index,i))>Integer.MAX_VALUE)continue;
                int num = Integer.parseInt(S.substring(index,i));
                if(num == num1+num2){
                    numList.add(num);
                    if(i == S.length()){
                        result = numList;
                        return;
                    }
                    dfs(S,i,new ArrayList<>(numList));
                    numList.remove(numList.size()-1);
                }
            }
        }
    }

    public List<Integer> splitIntoFibonacci(String S) {
        dfs(S,0,new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        SplitIntoFibonacci splitIntoFibonacci = new SplitIntoFibonacci();
        System.out.println(splitIntoFibonacci.splitIntoFibonacci("5511816597"));
    }

}