package com.gleb.twittertrends.Models.Tweet;

import com.gleb.twittertrends.Models.Country.Country;
import com.gleb.twittertrends.Models.State.Polygon;
import com.gleb.twittertrends.Models.State.State;

import java.util.ArrayList;
import java.util.Date;

public class Tweet {

    private Coordinates coordinates;
    private Date date;
    private String string;

    public Tweet(Coordinates coordinates, Date date, String string){
        setCoordinates(coordinates);
        setDate(date);
        setString(string);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
