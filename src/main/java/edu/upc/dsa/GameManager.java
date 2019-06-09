package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;

public interface GameManager{

    //ADMIN
    void deleteUser(String username, int userId) throws Exception, OnlyFunctionsAdmin;
    void deleteObjectStore(String username, String nameObject) throws Exception, OnlyFunctionsAdmin;

    //USER
    public User register(String username, String password, String name, String surname, String mail, int age) throws Exception;
    public UserLogin getUserLogin(String username, String password) throws Exception;
    public User getUser (String username,String password) throws UserNotFoundException;
    public UserProfile getProfile (String username) throws UserNotFoundException;
    public Inventario getInventary (String username) throws  Exception;
    public UserStatistics getStatistics(String username) throws UserNotFoundException;
    public UserLogin passUserToUserLogin (User user);
    public UserProfile passUserToUserProfile (User user);
    public UserStatistics passUserToUserStatistics (User user);

    public int sizeUsers();
    public void añadirObjetosHashMap() throws Exception;
    //OBJECTS
    public void buyObject(String nameObject, String username) throws ObjectNotExist, UserNotFoundException, WeaponException, Exception;
    void addObjectStore(String name) throws ObjectExist;
    public int sizeStore();


    public void clear();


}
