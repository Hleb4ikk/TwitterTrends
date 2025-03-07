package com.gleb.twittertrends.Models.State;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<ArrayList<Double>> coordinates;

    public Polygon(ArrayList<ArrayList<Double>> coordinates){
        setCoordinates(coordinates);
    }

    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }
    public Double[] to1DimensionArray(){
        Double[] arr = new Double[coordinates.size() * 2];
        for(int i = 0; i < coordinates.size(); i++){
            arr[2 * i] = coordinates.get(i).get(0); // X coordinate
            arr[2 * i] = coordinates.get(i).get(0);
            arr[2 * i + 1] = coordinates.get(i).get(1); // Y coordinate
        }
        return arr;
    }


    @Override
    public String toString() {
        return "Polygon{" +
                "coordinates=" + coordinates +
                '}';
    }
}
