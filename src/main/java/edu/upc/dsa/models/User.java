package edu.upc.dsa.models;

import org.joda.time.DateTime;

public class User {

    //Attributes
    private int id;
    private String username;
    private String password;
    private int cash;
    private DateTime currentTime;
    private int currentLife;
    private int currentEnemiesKilled;
    private int currentLevel;

    //Constructors
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, int cash, DateTime currentTime, int currentLife, int currentEnemiesKilled, int currentLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.currentTime = currentTime;
        this.currentLife = currentLife;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLevel = currentLevel;
    }

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

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public DateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(DateTime currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public int getCurrentEnemiesKilled() {
        return currentEnemiesKilled;
    }

    public void setCurrentEnemiesKilled(int currentEnemiesKilled) {
        this.currentEnemiesKilled = currentEnemiesKilled;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cash=" + cash +
                ", currentTime=" + currentTime +
                ", currentLife=" + currentLife +
                ", currentEnemiesKilled=" + currentEnemiesKilled +
                ", currentLevel=" + currentLevel +
                '}';
    }
}
