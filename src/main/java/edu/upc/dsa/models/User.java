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
    private int cash;
    private Timestamp currentTime;
    private int currentLife;
    private int currentEnemiesKilled;
    private int currentLevel;
    private int playedGames = 0;
    private String name;
    private String surname;
    private String mail;
    private int age;
    private List<ObjTO> listObjetos;


    //Constructors
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.listObjetos = new ArrayList<>();
    }

    public User(String username, String password, String name, String surname, String mail, int age){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.listObjetos = new ArrayList<>();
    }
    public User(String username, String password, String name, String surname, String mail, int age, int enemigosmatados, Timestamp minutostotales, int monedasconseguidas, int partidasjugadas) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.listObjetos = new ArrayList<ObjTO>();
        this.currentEnemiesKilled = enemigosmatados;
        this.currentTime = minutostotales;
        this.playedGames = partidasjugadas;
    }
    public User(int id, String username, String password, int cash, Timestamp currentTime, int currentLife, int currentEnemiesKilled, int currentLevel, int partidasjugadas, String name, String surname, String mail, int age, List<ObjTO> listObjetos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.currentTime = currentTime;
        this.currentLife = currentLife;
        this.currentEnemiesKilled = currentEnemiesKilled;
        this.currentLevel = currentLevel;
        this.playedGames = partidasjugadas;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.age = age;
        this.listObjetos = listObjetos;
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

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
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

    public List<ObjTO> getListObjetos() {
        return listObjetos;
    }

    public void setListObjetos(List<ObjTO> listObjetos) {
        this.listObjetos = listObjetos;
    }
    public void addObject (ObjTO objTO)
    {
        this.listObjetos.add(objTO);
    }

    public int size ()
    {
        return this.listObjetos.size();
    }
}
