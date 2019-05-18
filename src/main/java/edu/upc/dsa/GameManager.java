package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.to.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface GameManager {

    /*
    * Authentication
    * */
    UserTO login(UserTO user) throws Exception;
    void register(UserTO user) throws Exception;

    /*
     * User
     * */
    void deleteUser(UserTO user);
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
