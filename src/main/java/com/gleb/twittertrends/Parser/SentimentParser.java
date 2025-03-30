package com.gleb.twittertrends.Parser;

import com.gleb.twittertrends.Models.Sentiment.Sentiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SentimentParser extends Parser<ArrayList<Sentiment>> {

    public SentimentParser(String path) {
        super(path);
    }

    @Override
    public ArrayList<Sentiment> parse() {
        ArrayList<Sentiment> sentiments = new ArrayList<>();
        BufferedReader bufferedReader = getBufferedReader();

        if (bufferedReader == null) {
            System.out.println("Error: BufferedReader is null. Check the file path.");
            return sentiments;
        }

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Разделение строки по запятой
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String phrase = parts[0].trim();
                    double score = Double.parseDouble(parts[1].trim()); // Преобразование score
                    sentiments.add(new Sentiment(score, phrase));
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing score: " + e.getMessage());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing file: " + e.getMessage());
            }
        }

        return sentiments;
    }
}
