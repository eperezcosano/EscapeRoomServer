package edu.upc.dsa.to.User;

import edu.upc.dsa.models.ObjetoInventario;

import java.util.List;

public class UserInventary {

    private List<ObjetoInventario> lista;

    public UserInventary(List<ObjetoInventario> lista) {
        this.lista = lista;
    }

    public UserInventary() {
    }

    public List<ObjetoInventario> getLista() {
        return lista;
    }

    public void setLista(List<ObjetoInventario> lista) {
        this.lista = lista;
    }
}
