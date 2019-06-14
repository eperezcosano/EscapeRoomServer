package edu.upc.dsa.to.User;

import java.sql.Timestamp;

public class UserStatistics {
    private int currentEnemiesKilled;
    private String currentTime;
    private int playedGames;
    private String currentWeapon;
    private String currentShield;
    private int currentLife;
    private int currentLevel=0;
    private int cash;

    public UserStatistics() {
    }

    public UserStatistics(int currentEnemiesKilled, String currentTime, int playedGames, String currentWeapon, String currentShield, int currentLife, int currentLevel, int cash) {
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentTime = currentTime;
        this.playedGames = playedGames;
        this.currentWeapon = currentWeapon;
        this.currentShield = currentShield;
        this.currentLife = currentLife;
        this.currentLevel = currentLevel;
        this.cash = cash;
    }


    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getCurrentShield() {
        return currentShield;
    }

    public void setCurrentShield(String currentShield) {
        this.currentShield = currentShield;
    }

    public String getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(String currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public int getCurrentEnemiesKilled() {
        return currentEnemiesKilled;
    }

    public void setCurrentEnemiesKilled(int currentEnemiesKilled) {
        this.currentEnemiesKilled = currentEnemiesKilled;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }
}
