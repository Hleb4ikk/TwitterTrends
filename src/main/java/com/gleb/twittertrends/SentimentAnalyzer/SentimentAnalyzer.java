package com.gleb.twittertrends.SentimentAnalyzer;

import com.gleb.twittertrends.Models.Sentiment.Sentiment;
import com.gleb.twittertrends.Models.Tweet.Tweet;
import com.gleb.twittertrends.Structures.SentimentTrie;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class SentimentAnalyzer {

    public static HashMap<String, Double> calculateAverageSentiments(HashMap<String, ArrayList<Tweet>> groupedTweets, ArrayList<Sentiment> sentiments) {
        SentimentTrie sentimentTrie = new SentimentTrie();

        for (Sentiment sentiment : sentiments) {
            sentimentTrie.insert(sentiment.getPhrase(), sentiment.getScore());
        }

        HashMap<String, Double> averageSentiments = new HashMap<>();

        for (String state : groupedTweets.keySet()) {
            ArrayList<Tweet> tweets = groupedTweets.get(state);
            double sentimentSum = 0;
            int sentimentCount = 0;

            for (Tweet tweet : tweets) {
                Double sentiment = calculateTweetSentiment(tweet, sentimentTrie);

                if (sentiment != null) {
                    sentimentSum += sentiment;
                    sentimentCount++;
                }
            }

            if (sentimentCount > 0) {
                averageSentiments.put(state, sentimentSum / sentimentCount);
            }
        }

        return averageSentiments;
    }

    private static Double calculateTweetSentiment(Tweet tweet, SentimentTrie sentimentTrie) {
        String text = tweet.getString();
        List<String> words = extractWordsFromTweet(text);
        double sentimentSum = 0;
        int sentimentCount = 0;

        for (int i = 0; i < words.size(); i++) {
            Double sentimentScore = sentimentTrie.search(words, i);

            if (sentimentScore != null) {
                sentimentSum += sentimentScore;
                sentimentCount++;
            }
        }

        return sentimentCount > 0 ? (sentimentSum / sentimentCount) : null;
    }

    private static List<String> extractWordsFromTweet(String text) {
        List<String> words = new ArrayList<>();
        String[] wordArray = text.split("\\W+"); // Извлекаем только слова

        for (String word : wordArray) {
            if (!word.isEmpty()) {
                words.add(word.toLowerCase());
            }
        }

        return words;
    }
}

