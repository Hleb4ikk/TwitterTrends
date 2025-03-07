package com.gleb.twittertrends.View;

import com.gleb.twittertrends.Models.State.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class StateView extends Group{

    public StateView(State state, Color color){
        Polygon[] polygons = new Polygon[state.getPolygons().size()];
        for(int i = 0; i < state.getPolygons().size(); i++){
            polygons[i] = new Polygon();
            polygons[i].getPoints().addAll(CentralizePoints(state.getPolygons().get(i).to1DimensionArray()));
            polygons[i].setFill(color);
        }
        super.getChildren().addAll(polygons);
    }

    private Double[] CentralizePoints(Double[] arr){
        Double[] arr_centralized = new Double[arr.length];
        for(int i = 0; i < arr_centralized.length; i++){

            if(i%2 == 0) arr_centralized[i] = arr[i]*10+1850;
            else arr_centralized[i] = arr[i]*-10+825;

        }
        return arr_centralized;
    }

}
