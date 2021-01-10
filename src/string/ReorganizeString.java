package string;

import java.util.*;

public class ReorganizeString {

    public String reorganizeString(String S) {
        char[] chars = S.toCharArray();
        int len = chars.length;
        int[] count = new int[26];
        int maxCount = 0;
        for(char c:chars){
            count[c-'a']++;
            maxCount = Math.max(maxCount,count[c-'a']);
        }
        if(maxCount>(len+1)/2)return "";
        StringBuilder result = new StringBuilder();
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character character, Character t1) {
                return count[t1-'a']-count[character-'a'];
            }
        });
        for(char c = 'a';c<='z';c++){
            if(count[c-'a']>0){
                queue.offer(c);
            }
        }
        while(queue.size()>1){
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            result.append(letter1).append(letter2);
            count[letter1-'a']--;
            count[letter2-'a']--;
            if(count[letter1-'a']>0){
                queue.offer(letter1);
            }
            if(count[letter2-'a']>0){
                queue.offer(letter2);
            }
        }
        if(queue.size()>0){
            result.append(queue.poll());
        }
        return result.toString();
    }


    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        System.out.println(reorganizeString.reorganizeString("asdasfaregergwerfsdgvasdfawertgwsetrhsergawretrtuhtdyjxfcghbzdf"));

    }

}
