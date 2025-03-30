package com.gleb.twittertrends.View;

import com.gleb.twittertrends.Models.State.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import jdk.swing.interop.SwingInterOpUtils;

public class StateView extends Group{

    public StateView(String name, State state, Color color){
        Polygon[] polygons = new Polygon[state.getPolygons().size()];

        for(int i = 0; i < state.getPolygons().size(); i++){
            polygons[i] = new Polygon();
            polygons[i].getPoints().addAll(CentralizePoints(state.getPolygons().get(i).to1DimensionArray()));
            polygons[i].setFill(color);
            polygons[i].setStroke(Color.BLACK);
        }
        Double[] pointOfName = CountPointOfName(state);
        Text nameView = new Text(name);

        nameView.setX(pointOfName[0] - (nameView.getLayoutBounds().getMaxX() -  nameView.getLayoutBounds().getMinX())/2);
        nameView.setY(pointOfName[1]);
        nameView.setFill(Color.BLACK);
        super.getChildren().addAll(polygons);
        super.getChildren().addAll(nameView);
    }

    private Double[] CentralizePoints(Double[] arr){
        Double[] arr_centralized = new Double[arr.length];
        for(int i = 0; i < arr_centralized.length; i++){

            if(i%2 == 0) arr_centralized[i] = arr[i]*10+1850;
            else arr_centralized[i] = arr[i]*-10+825;

        }
        return arr_centralized;
    }

    private Double[] CountPointOfName(State state) {
        double max = 0;
        double coordinateX = 0;
        double coordinateY = 0;

        for (int i = 0; i < state.getPolygons().size(); i++) {
            double square = 0.;
            double temp_coordinateX = 0;
            double temp_coordinateY = 0;

            Double[] centralizedPoints = CentralizePoints(state.getPolygons().get(i).to1DimensionArray());

            for (int k = 0; k < centralizedPoints.length - 3; k+=2) {
                double x1 = centralizedPoints[k];
                double y1 = centralizedPoints[k + 1];
                double x2 = centralizedPoints[k + 2];
                double y2 = centralizedPoints[k + 3];

                // Вычисление площади и центра масс
                double generalPart = x1 * y2 - x2 * y1;
                square += generalPart;

                temp_coordinateX += (x1 + x2) * generalPart;
                temp_coordinateY += (y1 + y2) * generalPart;
            }

            // Абсолютное значение площади
            square = Math.abs(0.5 * square);

            // Вычисление центра масс
            temp_coordinateX = temp_coordinateX / (6 * square);
            temp_coordinateY = temp_coordinateY / (6 * square);

            // Выбор полигона с максимальной площадью
            if (max < square) {
                max = square;
                coordinateX = temp_coordinateX;
                coordinateY = temp_coordinateY;
            }
        }

        return new Double[]{coordinateX, coordinateY};
    }
}
