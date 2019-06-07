package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.User.UserInventary;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;

import java.util.List;

public interface GameManager {

    /**
     * Check user credentials
     *
     * @param user user credentials (username and password)
     * @return user credentials (and its id)
     * @throws Exception if user is not found or connection error
     */
    void register(UserLogin user) throws Exception;

    /**
     * Delete an user from data base
     *
     * @param userId user identification
     * @throws Exception if user is not found or connection error
     */
    void deleteUser(int userId) throws Exception;

    //TODO:
    /*
     * Store
     * */
    void deleteObjectStore(int idObject);

    /*
     * Map
     * */

    //USER
    public User addUser(String username, String password, String name, String surname, String mail, int age) throws UserAlreadyExistsException;
    public UserLogin getUserLogin(String username, String password) throws Exception;
    public User getUser (String username,String password) throws UserNotFoundException;
    public UserProfile getProfile (String username) throws UserNotFoundException;
    public Inventario getInventary (String username) throws  Exception;
    public UserStatistics getStatistics(String username) throws UserNotFoundException;
    public UserLogin passUserToUserLogin (User user);
    public UserProfile passUserToUserProfile (User user);
    public UserStatistics passUserToUserStatistics (User user);
   // public UserInventary passUserToUserInvetary (User user);
    public int sizeUsers();
    public void añadirObjetosHashMap() throws Exception;
    //OBJECTS
    public void buyObject(String nameObject, String username) throws ObjectNotExist, UserNotFoundException, WeaponException, Exception;
    void addObjectStore(String name) throws ObjectExist;
    public int sizeStore();


    public void clear();


}
