package com.gleb.twittertrends.Structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentTrie {

    static class TrieNode{
        Map<String, TrieNode> children = new HashMap<>();
        Double score = null;
    }
    private final TrieNode root = new TrieNode();

    public void insert(String phrase, double score){
        TrieNode node = root;
        for(String word : phrase.split(" ")){
            node = node.children.computeIfAbsent(word, k -> new TrieNode());
        }
        node.score = score;
    }
    public Double search(List<String> words, int startIndex){

        TrieNode node = root;
        Double lastScore = null;

        for(int i = startIndex; i < words.size(); i++){
//            System.out.println(words.get(i));
            node = node.children.get(words.get(i));
            if(node == null) break;
            if(node.score != null) lastScore = node.score;
        }
        return lastScore;
    }
}
