package edu.upc.dsa.models;

public class Map {
    //Atributes
    private String id;
    private int level;
    private String casillero;

    public Map(){

    }

    public Map(String id, int level, String casillero) {
        this.id = id;
        this.level = level;
        this.casillero = casillero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCasillero() {
        return casillero;
    }

    public void setCasillero(String casillero) {
        this.casillero = casillero;
    }
}
