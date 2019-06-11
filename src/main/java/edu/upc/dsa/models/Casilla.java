package edu.upc.dsa.models;

public class Casilla {

    private int x;
    private int y;
    private String type;
    private String atributo;

    public Casilla(int x, int y, String type, String atributo) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.atributo = atributo;
    }

    public Casilla() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}
