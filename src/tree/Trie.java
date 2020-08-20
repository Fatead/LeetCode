package tree;

public class Trie {


    private TrieNode rootNode;

    class TrieNode{
        private TrieNode[] links;
        private final int element = 26;
        private boolean isEnd;

        public TrieNode(){
            links = new TrieNode[element];
        }

        public boolean contains(char c){
            return links[c-'a']!=null;
        }

        public TrieNode get(char c){
            return links[c-'a'];
        }

        public void put(char c,TrieNode node){
            links[c-'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        rootNode = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = rootNode;
        for(int i = 0;i<word.length();i++){
            if(!node.contains(word.charAt(i))){
                node.put(word.charAt(i),new TrieNode());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = rootNode;
        for(int i =0;i<word.length();i++){
            if(node.contains(word.charAt(i))){
                node = node.get(word.charAt(i));
            }else {
                return false;
            }
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = rootNode;
        for(int i =0;i<prefix.length();i++){
            if(node.contains(prefix.charAt(i))){
                node = node.get(prefix.charAt(i));
            }else {
                return false;
            }
        }
        return true;
    }

}
