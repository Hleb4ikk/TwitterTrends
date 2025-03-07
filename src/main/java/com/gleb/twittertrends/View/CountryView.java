package com.gleb.twittertrends.View;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;

public class CountryView extends Group {

    public CountryView(Country country){

        HashMap<String, State> map = country.getMap();
        Set<Map.Entry<String, State>> pairs = map.entrySet();
        StateView[] stateViews = new StateView[pairs.size()];
        int i = 0;
        for(Map.Entry<String, State> pair : pairs) {
            stateViews[i] = new StateView(pair.getValue(), Color.color(0, 0, 0));
            i++;
        }
        super.getChildren().addAll(stateViews);

    }

}
