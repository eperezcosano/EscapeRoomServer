package edu.upc.dsa.to;

public class ObjTO {
    private String nombre;

    public ObjTO(String nombre) {
        this.nombre = nombre;
    }

    public ObjTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ObjTO{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
