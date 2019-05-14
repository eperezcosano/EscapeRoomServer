package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {

    //Attributes
    private int id;
    private String username;
    private String password;
    private String objects;
    private int cash;
    private int level;

    //Constructors
    public User() {}

    public User(int id, String username, String password, String objects, int cash, int level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.objects = objects;
        this.cash = cash;
        this.level = level;
    }

    //Methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", objects='" + objects + '\'' +
                ", cash=" + cash +
                ", level=" + level +
                '}';
    }
}
