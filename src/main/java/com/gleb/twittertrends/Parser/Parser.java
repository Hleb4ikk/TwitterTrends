package com.gleb.twittertrends.Parser;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.Polygon;
import com.gleb.twittertrends.Models.State.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private FileReader fileReader;
    public static boolean isAlive = false;

    public Parser(String path) {
        if (!isAlive) {
            try {
                fileReader = new FileReader(path); // "src/main/resources/com/gleb/states.json"
                isAlive = true;
            } catch (Exception e) {
                isAlive = false;
                e.printStackTrace();
            }
        }
    }

    public Country parse() {
        HashMap<String, State> map = new HashMap<>();
        try {
            Object object = new JSONParser().parse(fileReader);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Country(map);
    }
}