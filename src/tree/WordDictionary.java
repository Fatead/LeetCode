package tree;

public class WordDictionary {


    TreeNode root;

    class TreeNode{
        int element = 26;
        TreeNode[] links;
        boolean isEnd;

        TreeNode(){
            links = new TreeNode[element];
        }

        public boolean isEnd(){
            return isEnd;
        }

        public void setEnd(){
            isEnd = true;
        }

        public TreeNode get(char c){
            return links[c-'a'];
        }

        public TreeNode get(int i){
            return links[i];
        }

        public void set(char c,TreeNode node){
            links[c-'a'] = node;
        }

        public boolean contains(char c){
            return links[c-'a']!=null;
        }
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TreeNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word.length() == 0)return;
        char[] chars = word.toCharArray();
        TreeNode node = root;
        for (char aChar : chars) {
            TreeNode node1 = node.get(aChar);
            if(node1 == null){
                node1 = new TreeNode();
            }
            node.set(aChar, node1);
            node = node1;
        }
        node.setEnd();
    }


    private boolean continueSearch(String word,TreeNode node){
        char[] chars = word.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i] == '.'){
                for(int j = 0;j<node.element;j++){
                    if(node.get(j)!=null){
                        if(continueSearch(word.substring(i+1),node.get(j))){
                            return true;
                        }
                    }
                }
                return false;
            } else if(node.contains(chars[i])){
                node = node.get(chars[i]);
            }else {
                return false;
            }
        }
        return node.isEnd;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return continueSearch(word,root);
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("ab");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search("a"));
    }

}
