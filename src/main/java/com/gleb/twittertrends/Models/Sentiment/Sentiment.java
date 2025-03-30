package com.gleb.twittertrends.Models.Sentiment;

public class Sentiment {

    private double Score;
    private String phrase;

    public Sentiment(double score, String phrase) {
        Score = score;
        this.phrase = phrase;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
