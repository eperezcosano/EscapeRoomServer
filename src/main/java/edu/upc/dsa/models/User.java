package edu.upc.dsa.models;

import edu.upc.dsa.to.ObjTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User {

    //Attributes
    private int id;
    private String username;
    private String password;
    private int cash=0;
    private String currentTime;
    private int currentLife=0;
    private int currentEnemiesKilled=0;
    private int currentLevel=0;
    private int playedGames = 0;
    private String name;
    private String surname;
    private String mail;
    private int age;
    private String currentWeapon;
    private String currentShield;
    private String recordTime;


    //Constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id=id;
    }

    public User(String username, String password, String name, String surname, String mail, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
    }

    public User(String username, String password, String name, String surname, String mail, int age, int enemigosmatados, String minutostotales, int monedasconseguidas, int partidasjugadas) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.currentEnemiesKilled = enemigosmatados;
        this.currentTime = minutostotales;
        this.playedGames = partidasjugadas;
    }

    public User(int id, String username, String password, int cash, String currentTime, int currentLife, int currentEnemiesKilled, int currentLevel, int playedGames, String name, String surname, String mail, int age, String currentWeapon, String currentShield, String recordTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.currentTime = currentTime;
        this.currentLife = currentLife;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLevel = currentLevel;
        this.playedGames = playedGames;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.currentWeapon = currentWeapon;
        this.currentShield = currentShield;
        this.recordTime = recordTime;
    }

    public User(String username, String password, int cash, String currentWeapon, String currentTime, int currentLife, int currentEnemiesKilled, int currentLevel, int partidasjugadas, String name, String surname, String mail, int age, String currentShield, String recordTime) {
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.currentTime = currentTime;
        this.currentLife = currentLife;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLevel = currentLevel;
        this.currentWeapon = currentWeapon;
        this.playedGames = partidasjugadas;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.currentShield=currentShield;
        this.recordTime = recordTime;
    }

    public User( String username, String password, int cash, String currentTime, int currentLife, int currentEnemiesKilled, int currentLevel, int playedGames, String name, String surname, String mail, int age, String currentWeapon, String currentShield, String recordTime) {
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.currentTime = currentTime;
        this.currentLife = currentLife;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLevel = currentLevel;
        this.playedGames = playedGames;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.currentWeapon = currentWeapon;
        this.currentShield = currentShield;
        this.recordTime = recordTime;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
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

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
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

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cash=" + cash +
                ", currentTime='" + currentTime + '\'' +
                ", currentLife=" + currentLife +
                ", currentEnemiesKilled=" + currentEnemiesKilled +
                ", currentLevel=" + currentLevel +
                ", playedGames=" + playedGames +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                ", currentWeapon='" + currentWeapon + '\'' +
                ", currentShield='" + currentShield + '\'' +
                ", recordTime='" + recordTime + '\'' +
                '}';
    }
}
