package edu.upc.dsa.to;

public class ObjetoPartida {
    private int duraciontotal;
    private int monedastotal;
    private int enemigosmatadostotal;

    public ObjetoPartida(){

    }

    public ObjetoPartida(int duraciontotal, int monedastotal, int enemigosmatadostotal) {
        this.duraciontotal = duraciontotal;
        this.monedastotal = monedastotal;
        this.enemigosmatadostotal = enemigosmatadostotal;
    }

    public int getDuraciontotal() {
        return duraciontotal;
    }

    public void setDuraciontotal(int duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    public int getMonedastotal() {
        return monedastotal;
    }

    public void setMonedastotal(int monedastotal) {
        this.monedastotal = monedastotal;
    }

    public int getEnemigosmatadostotal() {
        return enemigosmatadostotal;
    }

    public void setEnemigosmatadostotal(int enemigosmatadostotal) {
        this.enemigosmatadostotal = enemigosmatadostotal;
    }
}
