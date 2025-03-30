package com.gleb.twittertrends.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class Parser<T> {
    private BufferedReader bufferedReader;
    public Parser(String path){
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public abstract T parse();

}
