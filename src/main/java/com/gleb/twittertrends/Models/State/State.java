    package com.gleb.twittertrends.Models.State;

    import java.util.Arrays;

    public class State {

        private Polygon[] polygons;

        public State(Polygon[] borderCoordinates){
            setPolygons(borderCoordinates);
        }

        public Polygon[] getPolygons() {
            return polygons;
        }

        public void setPolygons(Polygon[] polygons) {
            this.polygons = polygons;
        }

        @Override
        public String toString() {
            return "State{" +
                    "polygons=" + Arrays.toString(polygons) +
                    '}';
        }
    }
