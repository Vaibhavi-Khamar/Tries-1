//Trie
//Time Complexity = O(Nl + Ml)
class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode [] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
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
    private TrieNode root;
    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        StringBuilder result = new StringBuilder();
        for (String word: dictionary){
            insert(word);
        }
        String [] strArr = sentence.split(" ");
        for( String word: strArr){
            //search replacement
            StringBuilder replacement = new StringBuilder();
            TrieNode curr = root;
            for(int i =0;i<word.length();i++){
                char ch = word.charAt(i);
                if(curr.children[ch - 'a']==null || curr.isEnd){
                    break;
                }
                replacement.append(ch);
                curr = curr.children[ch - 'a'];
            }
            if(curr.isEnd){
                result.append(replacement);
            } else{
                result.append(word);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}