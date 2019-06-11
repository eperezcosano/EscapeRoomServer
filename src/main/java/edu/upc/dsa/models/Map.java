package edu.upc.dsa.models;

import java.util.List;

public class Map {
    //Atributes
    private int id;
    private String mapLevel;

    public Map() {
    }

    public Map(int id, String mapLevel) {
        this.id = id;
        this.mapLevel = mapLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(String mapLevel) {
        this.mapLevel = mapLevel;
    }
}
