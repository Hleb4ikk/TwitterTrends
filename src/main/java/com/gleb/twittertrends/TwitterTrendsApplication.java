package com.gleb.twittertrends;

import com.gleb.twittertrends.Parser.Parser;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class TwitterTrendsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parser parser = new Parser("src/main/resources/com/gleb/states.json");
        try (FileReader reader = new FileReader("src/main/resources/com/gleb/states.json")) {
            parser.parse();
        } catch (Exception e) {
//            country = null;
            e.printStackTrace();
        }
//        CountryView countryView = new CountryView(country);
//        Scene scene = new Scene(countryView, 1280, 720);
        stage.setTitle("Twitter Trends");
//        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}