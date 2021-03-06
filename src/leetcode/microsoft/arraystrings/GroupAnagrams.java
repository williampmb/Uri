/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode.microsoft.arraystrings;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William Barbosa
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //String[] strs = {"aa", "bb", "a", "b"};

        List<List<String>> groupAnagrams = g.groupAnagrams(strs);
        for (List<String> l : groupAnagrams) {
            for (String s : l) {
                System.out.print(s + " ");
            }
            System.out.println("");
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        Trie ans = new Trie();
        for (String s : strs) {
            int[] letters = processStr(s);
            Trie cur = ans;
            addWordInTrie(cur, letters, s);
        }

        List<List<String>> lists = ans.getLists();
        return lists;
    }

    private int[] processStr(String s) {
        int[] ans = new int[26];

        for (char c : s.toCharArray()) {
            ans[c - 'a']++;
        }
        return ans;
    }

    private void addWordInTrie(Trie cur, int[] letters, String s) {

        for (int i = 0; i < 26; i++) {
            while (letters[i] != 0) {
                if (cur.tries[i] == null) {
                    cur.tries[i] = new Trie();
                }
                cur = cur.tries[i];
                letters[i]--;
            }
        }
        cur.myList.add(s);
    }

    class Trie {

        List<String> myList;
        Trie[] tries;

        public Trie() {
            tries = new Trie[26];
            myList = new ArrayList<>();
        }

        public List<List<String>> getLists() {
            List<List<String>> ans = new ArrayList<>();
            for (Trie t : tries) {
                if (t != null) {
                    ans.addAll(t.getLists());
                }
            }
            if (myList != null && myList.size() != 0) {
                ans.add(myList);
            }
            return ans;
        }
    }
}
