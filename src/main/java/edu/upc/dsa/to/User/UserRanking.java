package edu.upc.dsa.to.User;

public class UserRanking {
    private String name;
    private String currentLife;
    private int currentEnemiesKilled;
    private int recordTime;

    public UserRanking(){

    }
    public UserRanking(String name, String currentTime, int currentEnemiesKilled, int recordTime) {
        this.name = name;
        this.currentLife = currentTime;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.recordTime = recordTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(String currentTime) {
        this.currentLife = currentTime;
    }

    public int getCurrentEnemiesKilled() {
        return currentEnemiesKilled;
    }

    public void setCurrentEnemiesKilled(int currentEnemiesKilled) {
        this.currentEnemiesKilled = currentEnemiesKilled;
    }

    public int getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(int recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "UserRanking{" +
                "name='" + name + '\'' +
                ", currentTime='" + currentLife + '\'' +
                ", currentEnemiesKilled=" + currentEnemiesKilled +
                ", recordTime=" + recordTime +
                '}';
    }
}
