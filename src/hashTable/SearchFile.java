package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFile {

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String,List<String>> fileContentMap = new HashMap<>();
        for(String path:paths){
            String[] array = path.split(" ");
            String root = array[0];
            for(int i = 1;i<array.length;i++){
                String relativePath = array[i].substring(0,array[i].indexOf("("));
                String content = array[i].substring(array[i].indexOf("("),array[i].length()-1);
                String fullPath = root + "/" + relativePath;
                List<String> pathList = fileContentMap.getOrDefault(content,new ArrayList<>());
                pathList.add(fullPath);
                fileContentMap.put(content,pathList);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> pathList:fileContentMap.values()){
            if(pathList.size()>1){
                result.add(pathList);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SearchFile searchFile = new SearchFile();
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        List<List<String>> result = searchFile.findDuplicate(paths);
        System.out.println(result);
    }
}
