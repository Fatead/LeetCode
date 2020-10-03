package sort;

import java.util.ArrayList;
import java.util.List;

public class LexicalSort {


    List<Integer> result = new ArrayList<>();

    public void dfs(int num,int n){
        if(num>n)return;
        result.add(num);
        num *=10;
        for(int i = 0;i<10;i++)
            dfs(num+i,n);
    }


    public List<Integer> lexicalOrder(int n) {
        for(int i = 1;i<10;i++){
            dfs(i,n);
        }
        System.out.println(result);
        return result;
    }


    public static void main(String[] args) {
        LexicalSort lexicalSort = new LexicalSort();
        lexicalSort.lexicalOrder(1);
    }
}
