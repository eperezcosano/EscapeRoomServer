package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.to.*;
import edu.upc.dsa.mysql.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameManagerImpl implements GameManager {

    private Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    @Override
    public UserTO login(UserTO user) throws Exception {

        Session session = null;
        UserTO res = null;

        try {
            session = Factory.getSession();
            User dataUser = session.getByUsername(user.getUsername());

            log.info("User request: " + user);
            log.info("DB User" + dataUser);

            if (dataUser.getUsername().equals(user.getUsername()) &&
                    dataUser.getPassword().equals(user.getPassword()))
                res = new UserTO(dataUser.getId(), dataUser.getUsername(), dataUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        log.info("Login response: " + res);
        return res;
    }

    @Override
    public void register(UserTO user) throws Exception {

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
    public void deleteUser(UserTO user) {

    }

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

    @Override
    public void buy(int idObject, int idUser) {

    }

    @Override
    public void deleteObject(int idObject, int idUser) {

    }

    @Override
    public void addObjectStore(Object object) {

    }

    @Override
    public void deleteObjectStore(int idObject) {

    }
}
