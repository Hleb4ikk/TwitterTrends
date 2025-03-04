package com.gleb.twittertrends.Models.State;

public class Polygon {

    private double[][] coordinates;

    public Polygon(double[][] coordinates){
        setCoordinates(coordinates);
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }
    public Double[] to1DimensionArray(){

        Double[] arr = new Double[coordinates.length*2];

        for(int i = 0; i < coordinates.length; i++){

            for(int k = 0; k < coordinates[0].length; k++){

                arr[i+k] = coordinates[i][k];

            }

        }
        return arr;
    }
}
