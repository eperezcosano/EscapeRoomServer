package edu.upc.dsa.models;

public class ObjetoInventario {
    private int id;
    private String type;
    private String nombre;
    private int coste;
    private  String atributo; //para cada tipo de objeto este valor será diferente : color, mensaje, ...
    private int amount;

    public ObjetoInventario(){

    }

    public ObjetoInventario(int id, String type, String nombre, int coste, String atributo, int amount) {
        this.id = id;
        this.type = type;
        this.nombre = nombre;
        this.coste = coste;
        this.atributo = atributo;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

