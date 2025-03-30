package com.gleb.twittertrends.TweetAnalyzer;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.Sentiment.Sentiment;
import com.gleb.twittertrends.Models.Tweet.Tweet;
import com.gleb.twittertrends.Parser.SentimentParser;
import com.gleb.twittertrends.Parser.TweetsParser;
import com.gleb.twittertrends.Structures.SentimentTrie;

import java.util.*;

public class TweetAnalyzer {
    public static HashMap<String, Double> calculateAvarageScoreOfTweets(HashMap<String, ArrayList<Double>> groupedTweets){

        HashMap<String, Double> avgScoreOfTweetsGroupedByStates = new HashMap<>();
        ArrayList<Double> avgScores = new ArrayList<>();

        for(ArrayList<Double> listOfScores : groupedTweets.values()){
            double avgScore = 0;
            for(int i = 0; i < listOfScores.size(); i++){
                avgScore+=listOfScores.get(i);
            }
            avgScore/=listOfScores.size();
            avgScores.add(avgScore);
        }
        Set<String> keys = groupedTweets.keySet();
        int i = 0;
        for(String key : keys){
            avgScoreOfTweetsGroupedByStates.put(key, avgScores.get(i));
            i++;
        }

        return avgScoreOfTweetsGroupedByStates;
    }
    public static HashMap<String, ArrayList<Double>> groupTweetsScores(ArrayList<Tweet> tweets, ArrayList<Sentiment> sentiments, Country country){
        SentimentTrie trie = new SentimentTrie();

        for(Sentiment sentiment : sentiments){
            trie.insert(sentiment.getPhrase().toLowerCase(), sentiment.getScore());
        }
        HashMap<String, ArrayList<Double>> hashMap = new HashMap<>();

        for(Tweet tweet : tweets){
            tweet.calculateNearestState(country);
            double score = calculateTweetScore(tweet.getString(), trie);
            if(!hashMap.containsKey(tweet.getNearestState())) {
                hashMap.put(tweet.getNearestState(), new ArrayList<>());
                hashMap.get(tweet.getNearestState()).add(score);
            } else {
                hashMap.get(tweet.getNearestState()).add(score);
            }
        }

        return hashMap;
    }
    private static double calculateTweetScore(String text, SentimentTrie trie){

        List<String> words = Arrays.asList(text.toLowerCase().replaceAll("[^a-z0-9\\- ]", "").split(" "));
        double score = 0.;
        for(int i = 0; i < words.size(); i++){
            Double phraseScore = trie.search(words, i);
            if(phraseScore != null){
                score += phraseScore;
            }
        }
        return score;
    }
}

