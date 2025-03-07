    package com.gleb.twittertrends.Models.State;

    import java.util.ArrayList;

    public class State {

        private ArrayList<Polygon> polygons;

        public State(ArrayList<Polygon> borderCoordinates){
            setPolygons(borderCoordinates);
        }

        public ArrayList<Polygon> getPolygons() {
            return polygons;
        }

        public void setPolygons(ArrayList<Polygon> polygons) {
            this.polygons = polygons;
        }

        @Override
        public String toString() {
            return "State{" +
                    "polygons=" + polygons +
                    '}';
        }
    }
