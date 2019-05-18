package edu.upc.dsa.models;

public class Inventario {
    private int userId;
    private int objectId;

    public Inventario(){

    }

    public Inventario(int userId, int objectId) {
        this.userId = userId;
        this.objectId = objectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
