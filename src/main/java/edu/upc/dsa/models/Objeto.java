package edu.upc.dsa.models;

public class Objeto {
    private int id;
    private String type;
    private String nombre;
    private int coste;
    private  String atributo; //para cada tipo de objeto este valor será diferente : color, mensaje, ...

    public Objeto(){

    }
    public Objeto(int id, String type, String nombre, String atributo,int coste) {
        this.id = id;
        this.type = type;
        this.nombre = nombre;
        this.atributo = atributo;
        this.coste = coste;

    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}
