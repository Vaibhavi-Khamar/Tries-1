// Trie
// TC & SC = O(l) for insert, search, startswith. Where l is length of words.
class Trie {
    class TrieNode{
        boolean isEnd;
        TrieNode [] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) { //O(l)
        TrieNode curr = root; //curr is used for traverse down the trie paths
        for(int i =0; i<word.length(); i++){
            char ch = word.charAt(i);
            //check if curr trienode has child ch
            if(curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) { //O(l)
        TrieNode curr = root;
        for(int i =0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null){
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) { //O(l)
        TrieNode curr = root;
        for(int i =0; i<prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return true;
    }
}