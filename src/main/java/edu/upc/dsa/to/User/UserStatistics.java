package edu.upc.dsa.to.User;

import java.sql.Timestamp;

public class UserStatistics {
    private int currentEnemiesKilled;
    private Timestamp currentTime;
    private int playedGames;
    private String currentWeapon;
    private String currentShield;
    private int currentLevel=0;

    public UserStatistics() {
    }

    public UserStatistics(int currentEnemiesKilled, Timestamp currentTime, int playedGames, String currentWeapon, String currentShield, int currentLevel) {
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentTime = currentTime;
        this.playedGames = playedGames;
        this.currentWeapon = currentWeapon;
        this.currentShield = currentShield;
        this.currentLevel = currentLevel;
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

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }
}
