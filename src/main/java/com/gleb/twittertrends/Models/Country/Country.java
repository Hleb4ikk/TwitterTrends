package com.gleb.twittertrends.Models.Country;

import com.gleb.twittertrends.Models.State.State;

import java.util.HashMap;


public class Country{
    public static boolean isAlive = false;

    private HashMap<String, State> map;

    public Country(HashMap<String, State> map){
        if(!isAlive) {
            setMap(map);
            isAlive = true;
        }
    }
    public HashMap<String, State> getMap() {
        return map;
    }
    public void setMap(HashMap<String, State> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Country{" +
                "map=" + map.toString() +
                '}';
    }
}
