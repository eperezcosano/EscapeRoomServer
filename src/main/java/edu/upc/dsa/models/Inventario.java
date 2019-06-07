package edu.upc.dsa.models;

import java.util.List;

public class Inventario {

    private String username;
    private List<ObjetoInventario> lista;

    public Inventario(){

    }

    public Inventario(String userId) {
        this.username = userId;
    }

    public String getUserId() {
        return username;
    }

    public void setUserId(String userId) {
        this.username = userId;
    }

    public List<ObjetoInventario> getLista() {
        return lista;
    }

    public void setLista(List<ObjetoInventario> lista) {
        this.lista = lista;
    }

    public int size(){
        return this.lista.size();
    }
    @Override
    public String toString() {
        return "Inventario{" +
                "username='" + username + '\'' +
                ", lista=" + lista +
                '}';
    }
}
