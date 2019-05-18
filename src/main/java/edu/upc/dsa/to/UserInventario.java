package edu.upc.dsa.to;

import java.util.List;

public class UserInventario {
    private int id;
    private List<String> objectos;

    public UserInventario(){

    }

    public UserInventario(int id) {
        this.id = id;
        this.objectos = objectos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getObjectos() {
        return objectos;
    }

    public void setObjectos(List<String> objectos) {
        this.objectos = objectos;
    }
}
