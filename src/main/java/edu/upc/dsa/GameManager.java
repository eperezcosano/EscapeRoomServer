package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserRanking;
import edu.upc.dsa.to.User.UserStatistics;

import java.util.List;

public interface GameManager{

    //ADMIN
    void deleteUser(String username, int userId) throws Exception;
    void deleteObjectStore(String username, int idObject) throws Exception;


    //USER
    User register(String username, String password, String name, String surname, String mail, int age) throws Exception;
    UserLogin getUserLogin(String username, String password) throws Exception;
    User getUser (String username,String password) throws UserNotFoundException;
    UserProfile getProfile (String username) throws UserNotFoundException, NotFunctionForAdminExcepction;
    Inventario getInventary (String username) throws  Exception;
    void setInventary (Inventario inventario) throws Exception;
    UserStatistics getStatistics(String username) throws UserNotFoundException, NotFunctionForAdminExcepction;
    void buyObject(String nameObject, String username) throws Exception;
    void setWeapon(String weapon, String username) throws Exception;
    void setShield(String shield, String username) throws Exception;
    void updateUser (UserStatistics user, String username) throws Exception;
    List<UserRanking> getRanking () throws Exception;
    UserLogin passUserToUserLogin (User user);
    UserProfile passUserToUserProfile (User user);
    UserStatistics passUserToUserStatistics (User user);
    User passUserStatisticsToUser (UserStatistics user);


    int sizeUsers();
    int sizeStore();

    void añadirObjetosHashMap() throws Exception;
    void addObjectStore(String name) throws ObjectExistException;

    //UNITY
    List<String> getMapas() throws Exception;

    void clear();


}
