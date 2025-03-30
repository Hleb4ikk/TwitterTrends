package com.gleb.twittertrends;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.Sentiment.Sentiment;
import com.gleb.twittertrends.Models.Tweet.Tweet;
import com.gleb.twittertrends.Parser.CountryParser;
import com.gleb.twittertrends.Parser.SentimentParser;
import com.gleb.twittertrends.Parser.TweetsParser;
import com.gleb.twittertrends.TweetAnalyzer.TweetAnalyzer;
import com.gleb.twittertrends.View.CountryView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TwitterTrendsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CountryParser parser = new CountryParser("src/main/resources/com/gleb/states.json");
        Country country = parser.parse();

        ArrayList<Tweet> tweets = new ArrayList<>();

        final String[] tweetsPrefix = new String[]{"family_"}; //"cali_", "family_", "football_", "high_school_", "movie_", "shopping_", "snow_", "texas_", "weekend_"

        for(String zone : tweetsPrefix){
            tweets.addAll(new TweetsParser("src/main/resources/com/gleb/" + zone + "tweets2014.txt").parse());
        }

        ArrayList<Sentiment> sentiments = new SentimentParser("src/main/resources/com/gleb/sentiments.csv").parse();

        HashMap<String, ArrayList<Double>> groupedTweets = TweetAnalyzer.groupTweetsScores(tweets, sentiments, country);
        HashMap<String, Double> avgScoreByStates = TweetAnalyzer.calculateAvarageScoreOfTweets(groupedTweets);
        CountryView countryView = new CountryView(country, avgScoreByStates);
        Scene scene = new Scene(countryView, 1280, 720);
        stage.setTitle("Twitter Trends");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}