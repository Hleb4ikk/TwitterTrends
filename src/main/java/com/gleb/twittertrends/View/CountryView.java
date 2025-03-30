package com.gleb.twittertrends.View;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.*;

public class CountryView extends Group {

    private HashMap<String, Double> normalizeScores(HashMap<String, Double> scores) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // Находим минимальное и максимальное значения
        for (Double value : scores.values()) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        // Нормализуем все значения
        HashMap<String, Double> normalizedScores = new HashMap<>();
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            double normalizedValue = ((entry.getValue() - min) / (max - min)); // Нормализация
            normalizedScores.put(entry.getKey(), normalizedValue);
        }

        return normalizedScores;
    }

    private Color countColor(String key, HashMap<String, Double> avgScoreByStates) {
        HashMap<String, Double> normalizedValues = normalizeScores(avgScoreByStates);
        if (avgScoreByStates.containsKey(key)) {

            if (avgScoreByStates.get(key) < 0) {
                return Color.color(0, 0, 1 - normalizedValues.get(key)); // Синий цвет
            } else if(avgScoreByStates.get(key) > 0){
                return Color.color(1, 1-normalizedValues.get(key), 0); // Жёлтый цвет
            } else {
                return Color.color(1, 1, 1);
            }
        } else {
            // Если ключ отсутствует
            return Color.color(0.5, 0.5, 0.5); // Серый цвет
        }
    }

    public CountryView(Country country, HashMap<String, Double> avgScoreByStates){

        HashMap<String, State> map = country.getMap();
        Set<Map.Entry<String, State>> pairs = map.entrySet();
        StateView[] stateViews = new StateView[pairs.size()];
        int i = 0;
        for(Map.Entry<String, State> pair : pairs) {
            stateViews[i] = new StateView(pair.getKey(), pair.getValue(), countColor(pair.getKey(), avgScoreByStates));
            i++;
        }
        super.getChildren().addAll(stateViews);

    }
}
