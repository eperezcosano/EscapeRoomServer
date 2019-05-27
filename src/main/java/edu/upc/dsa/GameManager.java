package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.to.*;

import java.util.List;

public interface GameManager {

    /**
     * Check user credentials
     *
     * @param user user credentials (username and password)
     * @return user credentials (and its id)
     * @throws Exception if user is not found or connection error
     */
    UserTO login(UserTO user) throws Exception;

    /**
     * Add new user to data base
     *
     * @param user user credentials (username and password)
     * @throws Exception if user is already exists or connection error
     */
    void register(UserTO user) throws Exception;

    /**
     * Delete an user from data base
     *
     * @param userId user identification
     * @throws Exception if user is not found or connection error
     */
    void deleteUser(int userId) throws Exception;

    //TODO:
    List<Object> getObjects(UserTO user);
    List<String> getUserInventario(int idUser) throws Exception;
    void buy(int idObject, int idUser);
    void deleteObject(int idObject, int idUser);

    /*
     * Store
     * */
    void addObjectStore(Object object);
    void deleteObjectStore(int idObject);

    /*
     * Map
     * */


}
