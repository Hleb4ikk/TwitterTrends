package com.gleb.twittertrends.TweetAnalyzer;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.Sentiment.Sentiment;
import com.gleb.twittertrends.Models.State.Polygon;
import com.gleb.twittertrends.Models.State.State;
import com.gleb.twittertrends.Models.Tweet.Coordinates;
import com.gleb.twittertrends.Models.Tweet.Tweet;
import com.gleb.twittertrends.Parser.SentimentParser;
import com.gleb.twittertrends.Parser.TweetsParser;
import com.gleb.twittertrends.Structures.SentimentTrie;

import java.util.*;


public class TweetAnalyzer {

    public static String findState(Coordinates tweetCoordinates, Country country) {
        String closestState = null;
        double minDistance = Double.MAX_VALUE;

        for (Map.Entry<String, State> entry : country.getMap().entrySet()) {
            String stateName = entry.getKey();
            State state = entry.getValue();

            for (Polygon polygon : state.getPolygons()) {
                if (isPointInsidePolygon(tweetCoordinates, polygon)) {
                    return stateName; // Если точка внутри полигона
                } else {
                    double distance = calculateDistanceToPolygon(tweetCoordinates, polygon);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestState = stateName;
                    }
                }
            }
        }
        return closestState; // Вернуть ближайший штат
    }

    private static boolean isPointInsidePolygon(Coordinates point, Polygon polygon) {
        int intersectCount = 0;
        ArrayList<ArrayList<Double>> vertices = polygon.getCoordinates();

        for (int i = 0; i < vertices.size(); i++) {
            ArrayList<Double> vertex1 = vertices.get(i);
            ArrayList<Double> vertex2 = vertices.get((i + 1) % vertices.size());

            if (rayIntersectsEdge(point.getX(), point.getY(), vertex1, vertex2)) {
                intersectCount++;
            }
        }
        return (intersectCount % 2) == 1; // Чётное число пересечений -> точка вне полигона
    }

    private static boolean rayIntersectsEdge(double px, double py, ArrayList<Double> vertex1, ArrayList<Double> vertex2) {
        // Проверка на пересечение луча
        double x1 = vertex1.get(0), y1 = vertex1.get(1);
        double x2 = vertex2.get(0), y2 = vertex2.get(1);

        if (y1 > py != y2 > py &&
                px < (x2 - x1) * (py - y1) / (y2 - y1) + x1) {
            return true;
        }
        return false;
    }

    private static double calculateDistanceToPolygon(Coordinates point, Polygon polygon) {
        double minDistance = Double.MAX_VALUE;
        ArrayList<ArrayList<Double>> vertices = polygon.getCoordinates();

        for (ArrayList<Double> vertex : vertices) {
            double distance = calculateEuclideanDistance(point.getX(), point.getY(), vertex.get(0), vertex.get(1));
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

    private static double calculateEuclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public static HashMap<String, ArrayList<Tweet>> groupTweetsByState(ArrayList<Tweet> tweets, Country country) {
        HashMap<String, ArrayList<Tweet>> groupedTweets = new HashMap<>();

        for (Tweet tweet : tweets) {
            Coordinates tweetCoordinates = tweet.getCoordinates();

            // Используем функцию findState для определения штата
            String stateName = findState(tweetCoordinates, country);

            // Добавляем твит в соответствующий штат
            groupedTweets.putIfAbsent(stateName, new ArrayList<>());
            groupedTweets.get(stateName).add(tweet);
        }

        return groupedTweets;
    }
}