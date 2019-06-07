package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.*;
import edu.upc.dsa.mysql.*;
import edu.upc.dsa.to.User.UserInventary;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;

    HashMap<String, User> userHashMap;
    HashMap<String, ObjTO> objectHashMap;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManagerImpl(){
        this.userHashMap = new HashMap<>();
        this.objectHashMap = new HashMap<>();
    }
    public static GameManager getInstance(){
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }
    @Override
    public void register(UserLogin user) throws Exception {

        Session session = null;

        try {
            session = Factory.getSession();
            User insertUser = new User(user.getUsername(), user.getPassword());
            session.save(insertUser);

            log.info("User insert: " + insertUser);

        } catch (UserAlreadyExistsException e) {
            log.info("User already exists");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void deleteUser(int userId) throws Exception {

        Session session = null;

        try {
            session = Factory.getSession();
            session.delete(User.class, userId);
            log.info("User deleted ID: " + userId);

        } catch (UserNotFoundException e) {
            log.info("User not found");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void deleteObjectStore(int idObject) {

    }
/*
    @Override
    public List<Object> getObjects(UserTO user) {
        return null;
    }

    @Override
    public List<String> getUserInventario(int idUser) throws Exception {
        Session session = null;
        UserInventario user;
        List<String> objetos = new ArrayList<>();
        try{
            session = Factory.getSession();
            user = (UserInventario)session.get(UserInventario.class,idUser);
            objetos = user.getObjectos();

        }
        catch(Exception e){
            log.error("Error al abrir la sesion:" +e.getMessage());
            throw new UserNotFoundException();
        }
        finally {
            if (session != null) session.close();
        }

        return objetos;

    }
*/



    public int sizeUsers() {
        int ret = this.userHashMap.size();
        logger.info("size " + ret);
        return ret;
    }
    public int sizeStore() {
        int ret = this.objectHashMap.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public UserProfile getProfile(String username) throws UserNotFoundException {
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        UserProfile userProfile = this.passUserToUserProfile(user);
        return userProfile;
    }

    @Override
    public Inventario getInventary(String username) throws Exception {
        User user = this.userHashMap.get(username);
        if (user == null) throw new UserNotFoundException();
        Session session = null;
        Inventario inventario = new Inventario(username);

        try {
            session = Factory.getSession();
            List<ObjetoInventario> lista = session.getInventario(username);

            log.info("User request: " + user);

            if (lista.size() != 0)
                inventario.setLista(lista);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        log.info("Login response: " + inventario);
        return inventario;
    }
    @Override
    public UserStatistics getStatistics(String username) throws UserNotFoundException {
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        logger.info("Logged in: "+user.toString());
        UserStatistics userStatistics = this.passUserToUserStatistics(user);
        return userStatistics;}

    @Override
    public User addUser(String username, String password, String name, String surname, String mail, int age) throws UserAlreadyExistsException {
        User u = this.userHashMap.get(username);
        if(u!=null) throw new UserAlreadyExistsException();
        u = new User(username,password, name,surname,mail,age);
        this.userHashMap.put(username,u);
        logger.info("New user: "+u.toString());
        return u;       }

    @Override
    public UserLogin getUserLogin(String username, String password) throws Exception {
        User user = this.userHashMap.get(username);
        if(user==null) {
            Session session = null;
            UserLogin res = null;

            try {
                session = Factory.getSession();
                User dataUser = session.getByUsername(username);

                log.info("User request: " + user);
                log.info("DB User" + dataUser);

                if (dataUser.getUsername().equals(dataUser.getUsername()) &&
                        dataUser.getPassword().equals(dataUser.getPassword())) {
                    res = new UserLogin(dataUser.getUsername(), dataUser.getPassword());
                    User user1 = new User(res.getUsername(),res.getPassword());
                    userHashMap.put(user1.getUsername(),user1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null) session.close();
            }

            log.info("Login response: " + res);
            return res;
        }
        if(!password.equals(user.getPassword())) throw new PasswordNotMatchException();
        logger.info("Logged in: "+user.toString());
        UserLogin userLogin = this.passUserToUserLogin(user);
        return userLogin;
    }

    @Override
    public User getUser(String username, String password) throws UserNotFoundException {
        User u = this.userHashMap.get(username);
        if(u==null) throw new UserNotFoundException();
        return u;
    }

    @Override
    public void buyObject(String name, String username) throws ObjectNotExist, UserNotFoundException {
        ObjTO object = this.objectHashMap.get(name);
        if (object==null)  throw new ObjectNotExist();
        logger.info("Objecto: " + object.toString());
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        logger.info("User: "+ user.toString());
       // user.addObject(object);
        logger.info("Objeto añadido: " + object.toString());
    }

    @Override
    public void addObjectStore(String name) throws ObjectExist {
        ObjTO objTO = this.objectHashMap.get(name);
        if(objTO ==null) {
            ObjTO obje = new ObjTO(name);
            this.objectHashMap.put(obje.getNombre(),obje);
            logger.info("Objeto añadido a la store: " + obje.toString());
        }
        else throw new ObjectExist();

    }

    @Override
    public void clear() {
        this.userHashMap.clear();
    }

    @Override
    public UserLogin passUserToUserLogin(User user) {
        UserLogin userLogin = new UserLogin(user.getUsername(),user.getPassword());
        return userLogin;
    }

    @Override
    public UserProfile passUserToUserProfile(User user) {
        UserProfile userProfile= new UserProfile(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),user.getMail(),user.getAge());
        return userProfile;
    }

    @Override
    public UserStatistics passUserToUserStatistics(User user) {
        UserStatistics userStatistics = new UserStatistics(user.getCurrentEnemiesKilled(),user.getCurrentTime(),user.getPlayedGames());
        return userStatistics;
    }

   // @Override
  /*  public UserInventary passUserToUserInvetary(User user) {
        UserInventary userInventary = new UserInventary(user.getListObjetos());
        return userInventary;
    }
    */
}
