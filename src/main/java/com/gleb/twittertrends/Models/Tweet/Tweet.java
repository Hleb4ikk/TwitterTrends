package com.gleb.twittertrends.Models.Tweet;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.Polygon;
import com.gleb.twittertrends.Models.State.State;

import java.util.ArrayList;
import java.util.Date;

public class Tweet {
    private String nearestState;
    private Coordinates coordinates;
    private Date date;
    private String string;

    public Tweet(Coordinates coordinates, Date date, String string){
        setCoordinates(coordinates);
        setDate(date);
        setString(string);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }
    public String getNearestState() {
        return nearestState;
    }
    public void setString(String string) {
        this.string = string;
    }
    public void calculateNearestState(Country country) {
        String nearestState = null;
        double minDistance = Double.MAX_VALUE;

        // Проход по всем штатам
        for (String stateName : country.getMap().keySet()) {
            State state = country.getMap().get(stateName);

            // Проход по всем полигонам текущего штата
            for (Polygon polygon : state.getPolygons()) {
                // Проход по всем точкам полигона
                for (ArrayList<Double> coordinate : polygon.getCoordinates()) {
                    double x = coordinate.get(0);
                    double y = coordinate.get(1);

                    // Вычисляем евклидово расстояние
                    double distance = calculateEuclideanDistance(this.coordinates.getX(), this.coordinates.getY(), x, y);

                    // Обновляем минимальное расстояние и ближайший штат
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestState = stateName;
                    }
                }
            }
        }

        this.nearestState = nearestState;
    }
    private double calculateEuclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
