package com.gleb.twittertrends.Parser;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.Polygon;
import com.gleb.twittertrends.Models.State.State;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryParser extends Parser<Country>{

    public CountryParser(String path) {
        super(path);
    }

    public Country parse() {
        HashMap<String, State> map = new HashMap<>();
        try {
            Object object = new JSONParser().parse(super.getBufferedReader());
            if (object instanceof HashMap<?, ?>) {
                if (!((HashMap<?, ?>) object).isEmpty()) {
                    ((HashMap<?, ?>) object).forEach((key, list1) -> {
                        String abbr = "";
                        State state;
                        if (key instanceof String) {
                            abbr = (String)key;
                        }
                        ArrayList<Polygon> state_parts = new ArrayList<>();
                        if (list1 instanceof ArrayList<?>) {
                            if (!((ArrayList<?>) list1).isEmpty()) {
                                ((ArrayList<?>) list1).forEach((list2) -> {
                                    ArrayList<ArrayList<Double>> coordinates = new ArrayList<>();
                                    if (list2 instanceof ArrayList<?>) {
                                        if (!((ArrayList<?>) list2).isEmpty()) {
                                            ((ArrayList<?>) list2).forEach((obj) -> {
                                                ArrayList<Double> coordinate = new ArrayList<>();
                                                if (obj instanceof ArrayList<?>) {
                                                    if (!((ArrayList<?>) obj).isEmpty()) {

                                                        ((ArrayList<?>) obj).forEach((obj1) -> {
                                                            if (obj1 instanceof Double) {
                                                                coordinate.add((Double) obj1);
                                                            }
                                                        });
                                                    }
                                                }
                                                coordinates.add(coordinate);
                                            });
                                        }
                                    }
                                    state_parts.add(new Polygon(coordinates));
                                });
                            }
                        }
                         state = new State(state_parts);
                        map.put(abbr, state);
                    });
                }
            }
            super.getBufferedReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Country(map);
    }
}