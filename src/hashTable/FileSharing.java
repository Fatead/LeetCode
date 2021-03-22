package hashTable;

import java.util.*;

public class FileSharing {


    int size;
    List[] lists;
    List<Integer> userList = new ArrayList<>();

    public FileSharing(int size) {
        this.size = size;
        lists = new ArrayList[size];
        for (int i = 0; i <size ; i++) {
            lists[i] = new ArrayList();
        }
    }

    public int join(int[] ownChunks){
        //给新加入用户分配的id是currId
        int currId = 1;
        while (true){
            if(userList.contains(currId)){
                currId++;
            }else {
                break;
            }
        }
        for (int ownChunk : ownChunks) {
            lists[ownChunk-1].add(currId);
        }
        userList.add(currId);
        return currId;
    }

    public int[] request(int userId,int chunkID){
        //如果该用户以由该文件的权限，返回一个list
        if(lists[chunkID-1].contains(userId)){
            List<Integer> currList = lists[chunkID-1];
            Collections.sort(currList);
            int[] array = new int[lists[chunkID-1].size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = currList.get(i);
            }
            return array;
        }else {
            if(lists[chunkID-1].size()>0){
                List<Integer> currList = new ArrayList<>(lists[chunkID-1]);
                Collections.sort(currList);
                lists[chunkID-1].add(userId);
                int[] array = new int[currList.size()];
                for (int i = 0; i <array.length ; i++) {
                    array[i] = currList.get(i);
                }
                return array;
            }else {
                return new int[]{};
            }
        }
    }


    /**
     * @param userID
     * 某ID的用户离开系统，不能从该用户提取文件了
     */
    private void leave(int userID){
        for(List list:lists){
            int removeIndex = 0;
            if(list.contains(userID)){
                for (int i = 0; i < list.size(); i++) {
                    if((Integer) (list.get(i)) == userID){
                        removeIndex = i;
                    }
                }
                list.remove(removeIndex);
            }

        }

        int removeIndex = 0;
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i) == userID){
                removeIndex = i;
            }
        }
        userList.remove(removeIndex);
    }


    public static void main(String[] args) {
        FileSharing fileSharing = new FileSharing(4);
        System.out.println(fileSharing.join(new int[]{1,2}));
        System.out.println(fileSharing.join(new int[]{2,3}));
        System.out.println(fileSharing.join(new int[]{4}));
        System.out.println(Arrays.toString(fileSharing.request(1, 3)));
        System.out.println(Arrays.toString(fileSharing.request(2, 2)));
        fileSharing.leave(1);
        System.out.println(Arrays.toString(fileSharing.request(2, 1)));
        fileSharing.leave(2);
        System.out.println(fileSharing.join(new int[]{}));
    }

}
