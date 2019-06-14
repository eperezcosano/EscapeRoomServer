package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.mysql.*;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;

    HashMap<String, User> userHashMap;
    HashMap<String, Objeto> objectoHashMap;
    private HashMap<String, ResourceBundle> data;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManagerImpl(){
        this.userHashMap = new HashMap<>();
        this.objectoHashMap = new HashMap<>();
        this.data = new HashMap<>();

    }
    public static GameManager getInstance(){
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public int sizeUsers() {
        int ret = this.userHashMap.size();
        logger.info("size " + ret);
        return ret;
    }

    public int sizeStore() {
        int ret = this.objectoHashMap.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public void deleteUser(String username, int userId) throws Exception {

        if(username.equals("admin")) {
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
        else throw new OnlyFunctionsAdminException();
    }
    @Override
    public void deleteObjectStore(String username, int idObject) throws Exception {
        if(username.equals("admin")) {
            Session session = null;

            try {
                session = Factory.getSession();
                session.delete(Objeto.class, idObject);
                log.info("Object deleted ID: " + idObject);

            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (session != null) session.close();
            }
        }
        else throw new OnlyFunctionsAdminException();
    }
    @Override
    public void añadirObjetosHashMap() throws Exception {
        Session session = null;

        try {
            session = Factory.getSession();
            List<Object> list = session.findAll(Objeto.class);
            for (Object object : list) {
                if (object instanceof Objeto) {
                    Objeto CP = (Objeto) object;
                    objectoHashMap.put(CP.getNombre(),CP);
                }
            }
            log.info("Size tienda:" + list.size());
            log.info("Todo correcto en el AñadirObjetos");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
    @Override
    public UserProfile getProfile(String username) throws UserNotFoundException, NotFunctionForAdminExcepction {
        if(username.equals("admin")) throw new NotFunctionForAdminExcepction();
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        UserProfile userProfile = this.passUserToUserProfile(user);
        return userProfile;
    }
    @Override
    public Inventario getInventary(String username) throws Exception {
        if (username.equals("admin")) throw new NotFunctionForAdminExcepction();
        User user = this.userHashMap.get(username);
        if (user == null) throw new UserNotFoundException();
        Session session = null;
        Inventario inventario = new Inventario(username);
        try {
            session = Factory.getSession();
            List<ObjetoInventario> lista = session.getInventario(username);

            log.info("User request: " + user);
            log.info("LISTA:"+lista.size());
            if (lista.size() != 0)
                inventario.setLista(lista);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        log.info("Login response: " + inventario);
        return inventario;
    }

    @Override
    public void setInventary(Inventario inventario) throws Exception {
        Session session = null;
        try {
            session = Factory.getSession();
            session.setInventario(inventario);
            log.info("Inventario actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public UserStatistics getStatistics(String username) throws UserNotFoundException, NotFunctionForAdminExcepction {
        if (username.equals("admin")) throw new NotFunctionForAdminExcepction();
        User user = this.userHashMap.get(username);

        if(user==null) throw new UserNotFoundException();
        logger.info("Logged in: "+user.toString());
        UserStatistics userStatistics = this.passUserToUserStatistics(user);
        return userStatistics;}
    @Override
    public User register(String username, String password, String name, String surname, String mail, int age) throws Exception {
       Session session = null;
       User u = new User(username, password, name, surname, mail, age);
        try {
            session = Factory.getSession();
            User insertUser = new User(1,username,password,50,"0",0,0,1,0,name,surname,mail,age,"0","0");
            session.save(insertUser);

            log.info("User insert: " + insertUser);
            this.userHashMap.put(username, insertUser);
        } catch (UserAlreadyExistsException e) {
            log.info("User already exists");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        logger.info("New user: " + u.toString());
        return u;
    }
    @Override
    public UserLogin getUserLogin(String username, String password) throws Exception {
        User user = this.userHashMap.get(username);
        if (user == null) {
            Session session = null;
            UserLogin res;
            try {
                session = Factory.getSession();
                User dataUser = session.getByUsername(username);
                log.info("DB User" + dataUser);
                if (dataUser != null && dataUser.getPassword().equals(password)) {
                    res = new UserLogin(dataUser.getUsername(), dataUser.getPassword());
                    userHashMap.put(dataUser.getUsername(), dataUser);
                    return res;
                }
                if (dataUser != null && !dataUser.getPassword().equals(password)) throw new PasswordNotMatchException();
                else throw new UserNotFoundException();
            } catch (PasswordNotMatchException e)
            {
                res = new UserLogin(username, null);
                return res;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (session != null) session.close();
            }
        } else {
            if (password.equals(user.getPassword())) {
                logger.info("Logged in: " + user.toString());
                UserLogin userLogin = this.passUserToUserLogin(user);
                return userLogin;
            } else throw new PasswordNotMatchException();
        }
    }
    @Override
    public User getUser(String username, String password) throws UserNotFoundException {
        User u = this.userHashMap.get(username);
        if(u==null) throw new UserNotFoundException();
        return u;
    }
    @Override
    public void buyObject(String nameObject, String username) throws Exception {
        Session session = null;
        User user;
        try {
            session = Factory.getSession();
            user = session.getByUsername(username);
        } catch (UserNotFoundException e) {
            log.info("User not found");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        if(user==null) throw new UserNotFoundException();
        Objeto objectohash = this.objectoHashMap.get(nameObject);
        if (objectohash == null) throw new ObjectNotExistException();
        Inventario inventario = this.getInventary(username);
        logger.info("User: "+ user.toString());
        int amountMock = 0;
        if (inventario!=null) {
            for (ObjetoInventario objetoInventario : inventario.getLista()) {
                if (objetoInventario.getNombre().equals(nameObject)) {
                    if (objetoInventario.getType().equals("weapon")) throw new WeaponException();
                    amountMock = objetoInventario.getAmount();
                }
            }
        }
        log.info("objetohash:" + objectohash.getId());
        log.info("Todo correcto en buy");

        Session session2 = null;


        try {
            session2 = Factory.getSession();
            session2.buy(objectohash.getId(), user.getId(), amountMock);

            log.info("Object buy.");

        } catch (UserAlreadyExistsException e) {
            log.info("User already exists");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session2 != null) session2.close();
        }

    }
    @Override
    public void setWeapon(String weapon, String username) throws Exception {
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        Objeto objectohash = this.objectoHashMap.get(weapon);
        if (objectohash == null) throw new ObjectNotExistException();
        logger.info("User: "+ user.toString());
        log.info("objetohash:" + objectohash.getId());
        log.info("Todo correcto en setWeapon");


        Session session = null;

        try {
            session = Factory.getSession();
            session.setWeapon(objectohash.getNombre(), user.getId());
            user.setCurrentWeapon(objectohash.getNombre());

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
    public void updateUser (UserStatistics user, String username) throws Exception{
        Session session = null;
        User usera;
        try {

            session = Factory.getSession();
            usera=session.getByUsername(username);
            session.updateUser(user,usera.getId());
            //   session.updateUser(user);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally{
            if(session != null) session.close();
        }
    }
    @Override
    public void setShield(String shield, String username) throws Exception {
        User user = this.userHashMap.get(username);
        if(user==null) throw new UserNotFoundException();
        Objeto objectohash = this.objectoHashMap.get(String.valueOf(shield));
        if (objectohash == null) throw new ObjectNotExistException();
        logger.info("User: "+ user.toString());
        log.info("Todo correcto en setShield");

        Session session = null;
        try {
            session = Factory.getSession();
            session.setShield(shield, user.getId());
            user.setCurrentShield(shield);

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
    public void addObjectStore(String name) throws ObjectExistException {
        Objeto objeto = this.objectoHashMap.get(name);
        if(objeto==null) {
            Objeto obje = new Objeto(name);
            this.objectoHashMap.put(obje.getNombre(),obje);
            logger.info("Objeto añadido a la store: " + obje.toString());
        }
        else throw new ObjectExistException();

    }
    @Override
    public List<String> getMapas() throws Exception {
        Boolean a = false;
        String id = null;
        List<String> list = new ArrayList<>();
        int cont=0;
        String map = "";
        while (!a) {
            if (cont == 0) {
                id = "mapa1";
            }
            if (cont == 1) {
                id = "mapa2";
                a=true;
            }
            ResourceBundle rs = data.get(id);
            if (rs == null) {
                Locale l = new Locale(id);
                rs = ResourceBundle.getBundle("bundle", l);
                data.put(id, rs);
            }
            boolean fin = false;
            int i = 1;
            while (!fin) {
                try {
                    map += rs.getString(String.valueOf(i));
                    log.info("Mi mapa:" + map);
                    i++;
                } catch (Exception e) {
                    fin = true;
                    list.add(map);
                    cont++;
                    map="";
                }

            }
        }
        return list;
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
        UserStatistics userStatistics = new UserStatistics(user.getCurrentEnemiesKilled(),user.getCurrentTime(),user.getPlayedGames(),user.getCurrentWeapon(),user.getCurrentShield(),user.getPassword(),user.getCurrentLevel(),user.getCash());
        return userStatistics;
    }

    @Override
    public User passUserStatisticsToUser(UserStatistics user) {
        return null;
    }

}
