package tree;

public class Trie2 {

    private Trie2[] children;
    //该节点是否为字符串的结尾
    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie2() {
        children = new Trie2[26];
        isEnd = false;
    }


    /**
     * 子节点存在：沿着指针移动到子节点，继续处理下一个字符
     * 子节点不存在：创建一个新的子节点，记录在children对应的位置，沿着指针移动到子节点，继续处理下一个字符
     */
    public void insert(String word) {
        Trie2 node = this;
        for (int i = 0; i <word.length() ; i++) {
            if(node.children[word.charAt(i)-'a']==null){
                node.children[word.charAt(i)-'a'] = new Trie2();
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isEnd = true;
    }


    /**
     * 子节点存在：沿着指针移动到子节点，继续搜索下一字符
     * 子节点不存在：说明前缀树中不包含该字符串，返回空
     */
    public boolean search(String word) {
        Trie2 node = this;
        for (int i = 0; i <word.length() ; i++) {
            if(node.children[word.charAt(i)-'a']==null){
                return false;
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        return node!=null && node.isEnd;
    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie2 node = this;
        for (int i = 0; i <prefix.length() ; i++) {
            if(node.children[prefix.charAt(i)-'a']==null){
                return false;
            }
            node = node.children[prefix.charAt(i)-'a'];
        }
        return node!=null;
    }


}
