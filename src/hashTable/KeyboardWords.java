package hashTable;

import java.util.*;

public class KeyboardWords {

    public HashMap getMap(){
        HashMap<Character,Integer> map=new HashMap();
        //第一行
        map.put('Q',1);
        map.put('W',1);
        map.put('E',1);
        map.put('R',1);
        map.put('T',1);
        map.put('Y',1);
        map.put('U',1);
        map.put('I',1);
        map.put('O',1);
        map.put('P',1);
        //第二行
        map.put('A',2);
        map.put('S',2);
        map.put('D',2);
        map.put('F',2);
        map.put('G',2);
        map.put('H',2);
        map.put('J',2);
        map.put('K',2);
        map.put('L',2);
        //第三行
        map.put('Z',3);
        map.put('X',3);
        map.put('C',3);
        map.put('V',3);
        map.put('B',3);
        map.put('N',3);
        map.put('M',3);
        return map;
    }

    public String[] findWords(String[] words) {
        Map<Character,Integer> map = getMap();
        List<String> resultList = new ArrayList<>();
        for(String str:words){
            String tempStr =  str.toUpperCase();
            boolean flag = true;
            for(int i = 0;i<tempStr.length()-1;i++){
                if(!map.get(tempStr.charAt(i)).equals(map.get(tempStr.charAt(i + 1)))){
                    flag = false;
                    break;
                }
            }
            if(flag)
                resultList.add(str);
        }
        String[] result = new String[resultList.size()];
        int count = 0;
        for(String str:resultList){
            result[count++] = str;
        }
        return result;
    }

    public static void main(String[] args) {
        KeyboardWords keyboardWords = new KeyboardWords();
        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
        String[] result = keyboardWords.findWords(words);
        System.out.println(Arrays.toString(result));
    }

}
