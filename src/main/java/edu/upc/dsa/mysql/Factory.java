package edu.upc.dsa.mysql;

public class Factory {
    public static Session getSession() {
        return new SessionImpl();
    }
}
