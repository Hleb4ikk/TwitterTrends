package com.gleb.twittertrends.View;

import com.gleb.twittertrends.Models.State.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class StateView extends Group{

    public StateView(State state, Color color){
        Polygon[] polygons = new Polygon[state.getPolygons().length];
        for(int i = 0; i < polygons.length; i++){
            polygons[i].getPoints().addAll(state.getPolygons()[i].to1DimensionArray());
            polygons[i].setFill(color);
        }
        super.getChildren().addAll(polygons);
    }


}
