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
            if(object instanceof HashMap<?, ?>){

                if(!((HashMap<?, ?>) object).isEmpty()){

                    ((HashMap<?, ?>) object).forEach((key, list1) -> {

                        if(key instanceof String){
                            System.out.println(key);
                        }
                        if(list1 instanceof ArrayList<?>){


                            if(!((ArrayList<?>) list1).isEmpty()){

                                ((ArrayList<?>) list1).forEach((list2) -> {

                                    if(list2 instanceof ArrayList<?>){

                                        if(!((ArrayList<?>) list2).isEmpty()) {

                                            ((ArrayList<?>) list2).forEach((list3) -> {

                                                if(list3 instanceof ArrayList<?>){

                                                    if(!((ArrayList<?>) list3).isEmpty()) {
                                                        System.out.print("[");
                                                        ((ArrayList<?>) list3).forEach((expr) -> {

                                                            if(expr instanceof ArrayList<?>){

                                                                if(!((ArrayList<?>) expr).isEmpty()){
                                                                    System.out.print("[");
                                                                    ((ArrayList<?>) expr).forEach((value) -> {

                                                                        if(value instanceof Double){

                                                                            System.out.print(value + " ");

                                                                        }

                                                                    });
                                                                    System.out.print("],\n");
                                                                }

                                                            }
                                                            else if (expr instanceof Double){
                                                                System.out.print(expr + " ");
                                                            }
                                                        });
                                                        System.out.print("],\n");
                                                    }

                                                }

                                            });

                                        }

                                    }

                                });

                            }

                        }

                    });

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Country(map);
    }
}