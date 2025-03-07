package com.gleb.twittertrends;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.State;
import com.gleb.twittertrends.Parser.Parser;
import com.gleb.twittertrends.View.CountryView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class TwitterTrendsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parser parser = new Parser("src/main/resources/com/gleb/states.json");

        Country country = parser.parse();

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