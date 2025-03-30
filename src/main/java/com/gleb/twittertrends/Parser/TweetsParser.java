package com.gleb.twittertrends.Parser;

import com.gleb.twittertrends.Models.Tweet.Coordinates;
import com.gleb.twittertrends.Models.Tweet.Tweet;

import java.util.ArrayList;
import java.util.Date;

public class TweetsParser extends Parser<ArrayList<Tweet>>{

    public TweetsParser(String path){
        super(path);
    }

    @Override
    public ArrayList<Tweet> parse() {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            while (super.getBufferedReader().ready()) {
                // Чтение строки из файла
                String line = super.getBufferedReader().readLine();

                // Разделение строки на компоненты
                String[] parts = line.split("\\t", 4);
                if (parts.length == 4) {
                    // Обработка координат
                    String[] coords = parts[0].replaceAll("[\\[\\]]", "").split(",");
                    double x = Double.parseDouble(coords[1].trim());
                    double y = Double.parseDouble(coords[0].trim());
                    Coordinates coordinates = new Coordinates(x, y);

                    // Обработка даты
                    Date date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parts[2].trim());

                    // Обработка текста твита
                    String text = parts[3].trim();

                    // Создание объекта Tweet
                    Tweet tweet = new Tweet(coordinates, date, text);
                    tweets.add(tweet);
                }
            }
            super.getBufferedReader().close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tweets;
    }

}
