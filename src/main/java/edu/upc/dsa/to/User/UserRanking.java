package edu.upc.dsa.to.User;

public class UserRanking {
    private String name;
    private String currentTime;
    private int currentEnemiesKilled;
    private int currentLife;

    public UserRanking(){

    }
    public UserRanking(String name, String currentTime, int currentEnemiesKilled, int currentLife) {
        this.name = name;
        this.currentTime = currentTime;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLife = currentLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentEnemiesKilled() {
        return currentEnemiesKilled;
    }

    public void setCurrentEnemiesKilled(int currentEnemiesKilled) {
        this.currentEnemiesKilled = currentEnemiesKilled;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    @Override
    public String toString() {
        return "UserRanking{" +
                "name='" + name + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", currentEnemiesKilled=" + currentEnemiesKilled +
                ", currentLife=" + currentLife +
                '}';
    }
}
