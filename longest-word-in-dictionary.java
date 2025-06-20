//Trie + DFS:
	// 1.	Insert all words into a Trie.
	// 2.	Each TrieNode tracks if it marks the end of a word.
	// 3.	Then perform DFS on the Trie:
	// •	Only proceed down a branch if the prefix ending there is a word (isEnd == true).
	// •	Track the longest word that can be built character by character.
	// 4.	Return the best candidate.
//DFS is essential to explore all words that can be built letter-by-letter from valid prefixes.
//	•	Time: O(N * L), where N = number of words, L = average word length
//	•	Space: O(N * L), for Trie storage and DFS recursion stack.

class Solution {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
    private void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }
    private TrieNode root;
    public String longestWord(String[] words) {
        root = new TrieNode();
        // 1. Insert all words into Trie
        for (String word : words) {
            insert(word);
        }
        // 2. DFS from root
        return dfs(root, "");
    }
    private String dfs(TrieNode node, String path) {
        String best = path;
        for (int i = 0; i < 26; i++) {
            TrieNode child = node.children[i];
            if (child != null && child.isEnd) {
                String childWord = dfs(child, path + (char)(i + 'a'));
                if (childWord.length() > best.length() ||
                   (childWord.length() == best.length() && childWord.compareTo(best) < 0)) {
                    best = childWord;
                }
            }
        }
        return best;
    }
}