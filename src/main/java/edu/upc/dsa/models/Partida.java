package edu.upc.dsa.models;

public class Partida {
    private int id;
    private int idUsuario;
    private int duracion;
    private int enemigosmatados;
    private boolean finalizada;
    private int monedas;
    public Partida(){

    }

    public Partida(int id, int idUsuario, int duracion, int enemigosmatados, boolean finalizada, int monedas) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.duracion = duracion;
        this.enemigosmatados = enemigosmatados;
        this.finalizada = finalizada;
        this.monedas = monedas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getEnemigosmatados() {
        return enemigosmatados;
    }

    public void setEnemigosmatados(int enemigosmatados) {
        this.enemigosmatados = enemigosmatados;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }
}
