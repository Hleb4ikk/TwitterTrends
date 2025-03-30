package com.gleb.twittertrends;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.Sentiment.Sentiment;
import com.gleb.twittertrends.Models.Tweet.Tweet;
import com.gleb.twittertrends.Parser.CountryParser;
import com.gleb.twittertrends.Parser.SentimentParser;
import com.gleb.twittertrends.Parser.TweetsParser;
import com.gleb.twittertrends.View.CountryView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class TwitterTrendsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CountryParser parser = new CountryParser("src/main/resources/com/gleb/states.json");
        TweetsParser tweetsParser = new TweetsParser("src/main/resources/com/gleb/cali_tweets2014.txt");
        SentimentParser sentimentParser = new SentimentParser("src/main/resources/com/gleb/sentiments.csv");
        Country country = parser.parse();
        ArrayList<Tweet> tweets = tweetsParser.parse();
        ArrayList<Sentiment> sentiments = sentimentParser.parse();

        for(int i = 0; i < sentiments.size(); i++){

            System.out.println(sentiments.get(i).getScore() + " " + sentiments.get(i).getPhrase());

        }

        CountryView countryView = new CountryView(country);
        Scene scene = new Scene(countryView, 1280, 720);
        stage.setTitle("Twitter Trends");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}