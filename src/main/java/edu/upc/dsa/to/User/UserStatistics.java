package edu.upc.dsa.to.User;

import java.sql.Timestamp;

public class UserStatistics {
    private int currentEnemiesKilled;
    private Timestamp currentTime;
    private int playedGames;
    private String currentWeapon;

    public UserStatistics() {
    }


    public UserStatistics(int currentEnemiesKilled, Timestamp currentTime, int partidasjugadas, String currentWeapon) {
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentTime = currentTime;
        this.playedGames = partidasjugadas;
        this.currentWeapon = currentWeapon;
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
