package edu.upc.dsa.mysql;

import edu.upc.dsa.exceptions.UserAlreadyExistsException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.ObjetoInventario;
import edu.upc.dsa.models.User;
import edu.upc.dsa.to.User.UserRanking;
import edu.upc.dsa.to.User.UserStatistics;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.*;

public class SessionImpl implements Session {

    private Logger log = Logger.getLogger(SessionImpl.class.getName());
    private Connection connection;

    SessionImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/escaperoom", "root", "Mazinger72");
            log.info("Connected to db");
        } catch (Exception e) {
            log.error("Error exception");
            e.printStackTrace();
        }
    }

    public void save(Object entity) throws Exception {

        if (entity instanceof User)
            if (!find(User.class, -1, entity.getClass().getMethod("getUsername").invoke(entity).toString()).isEmpty()) throw new UserAlreadyExistsException();

        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        String query ="INSERT INTO " + entity.getClass().getSimpleName() +" (";
        for (Field f: fields) sb.append(f.getName()).append(",");
        query += sb.deleteCharAt(sb.length() - 1).toString();
        query += ") VALUES (";
        sb = new StringBuilder();
        for (Field f: fields) sb.append("?,");
        query += sb.deleteCharAt(sb.length() - 1).toString();
        query += ")";

        log.info("query: " + query);

        PreparedStatement prep = this.connection.prepareStatement(query);
        for (int i = 1; i < fields.length + 1; i++) {
            if (int.class.equals(fields[i - 1].getType())) {
                String labelName = fields[i - 1].getName().substring(0, 1).toUpperCase() + fields[i - 1].getName().substring(1);
                int intValue = (int) entity.getClass().getMethod("get" + labelName).invoke(entity);
                prep.setInt(i, intValue);
            } else if (String.class.equals(fields[i - 1].getType())) {
                prep.setString(i, new PropertyDescriptor(fields[i - 1].getName(), entity.getClass()).getReadMethod().invoke(entity).toString());
            } else {
                String labelName = fields[i - 1].getName().substring(0, 1).toUpperCase() + fields[i - 1].getName().substring(1);
                Timestamp timestampValue = (Timestamp) entity.getClass().getMethod("get" + labelName).invoke(entity);
                prep.setTimestamp(i, timestampValue);
            }
        }

        prep.execute();
        prep.close();
    }

    public User getByUsername(String username) throws Exception {
           return (User) find(User.class, -1, username).get(0);
    }

    @Override
    public List<Objeto> listaObjetos() throws Exception {
       return (List<Objeto>) find(Objeto.class, 0, "").get(0); }

    public Object get(Class theClass, int id) throws Exception {
        return find(theClass, id, null).get(0);
    }

    public void deleteInventario (int objetoId, int userId) throws Exception{
        String query ="DELETE FROM Inventario WHERE userId = " + userId +" AND objetoId = " + objetoId;
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        log.info("query: " + query);
    }

    public void deleteAllInventarioFromOneUser (int userId) throws Exception{
        String query ="DELETE FROM Inventario WHERE userId = " + userId;
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        log.info("query: " + query);
    }

    public void deleteObjetoFromInventario (int objetoId) throws Exception{
        String query ="DELETE FROM Inventario WHERE objetoId = " + objetoId;
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        log.info("query: " + query);
    }

    public List<Object> findAll(Class theClass) throws Exception {
        return find(theClass, 0, null);
    }

    public List<UserRanking> getRanking() throws Exception {
        List<UserRanking> res = new ArrayList<>();
        String query ="SELECT User.name, User.recordTime, User.currentEnemiesKilled, User.currentLife FROM User WHERE User.recordTime IS NOT NULL ORDER BY recordTime ASC LIMIT 10;";
        ResultSet rs;

        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        rs = prep.getResultSet();

        if (rs == null)
        {
            return null;
        }
        while (rs.next()) {

            log.info("PEPEPE");
            log.info("User created");
            res.add(new UserRanking(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));

        }
        return res;

    }

    @Override
    public List<ObjetoInventario> getInventario(String username) throws Exception {
        List<Object> res = new ArrayList<>();
        ResultSet rs;
        String query;
        List<ObjetoInventario> objetos = new LinkedList<>();
        query = "SELECT Inventario.amount, Objeto.* FROM User, Objeto, Inventario WHERE User.username = ? AND User.id=Inventario.userId AND Inventario.objetoId=Objeto.id ";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setString(1, username);
        prep.execute();
        rs = prep.getResultSet();

        if (rs == null)
        {
            return null;
        }
        while (rs.next()) {

            log.info("Creating object...");
            log.info("Object created");
            objetos.add(new ObjetoInventario(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(1)));
        }
        return objetos;
    }

    @Override
    public void setInventario(Inventario inventario) throws Exception {
        User user;
        try{
             user = getByUsername(inventario.getUsername());
        }catch (Exception e){
            user=null;
        }
        if(user!=null) {
            this.deleteAllInventarioFromOneUser(user.getId());
            for (ObjetoInventario objetoInventario : inventario.getLista()) {
            this.insertObjectInInventory(objetoInventario.getId(),user.getId(),objetoInventario.getAmount());
            }
        }
    }
    @Override
    public void updateUser (UserStatistics user, int id) throws Exception {
        String query ="UPDATE User SET cash='"+user.getCash()+"', playedGames='"+user.getPlayedGames()+"',currentTime='"+user.getCurrentTime()+"', currentLife='"+user.getCurrentLife()+"', currentEnemiesKilled='"+user.getCurrentEnemiesKilled()+"', currentLevel='"+ user.getCurrentLevel()+"', currentWeapon='"+user.getCurrentWeapon()+"', currentShield='"+user.getCurrentShield()+"' WHERE id='"+id+"'";
        log.info(query);
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        prep.close();
    }
    public void update(Object entity, int id) throws Exception {
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        String query ="UPDATE " + entity.getClass().getSimpleName() +" SET ";
        for (Field f: fields) sb.append(f.getName()).append("=?,");
        query += sb.deleteCharAt(sb.length() - 1).toString();
        query += " WHERE id = ?";

        PreparedStatement prep = this.connection.prepareStatement(query);
        for (int i = 1; i < fields.length + 1; i++) prep.setString(i, new PropertyDescriptor(fields[i - 1].getName(), entity.getClass()).getReadMethod().invoke(entity).toString());
        prep.setInt(fields.length + 1, id);
        prep.execute();
        prep.close();

        log.info("query: " + query);
    }

    public void delete(Class theClass, int id) throws Exception {
        Object object = find(theClass, id, "admin").get(0);
        String query = "DELETE FROM " + theClass.getSimpleName() + " WHERE id = ?";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setInt(1, id);
        prep.execute();
        log.info("que sale:" + prep);
        log.info("query: " + query);
        if(theClass.getSimpleName().equals("Objeto")){
            this.deleteObjetoFromInventario(id);
        }
    }

    @Override
    public void buy( int objetoId, int userId, int amount) throws Exception {
        String query;
        int amountBueno = amount + 1;
        if(amount!=1) {
            this.deleteInventario(objetoId, userId);
        }
        query = "INSERT INTO Inventario (userId, objetoId, amount) VALUES ('" + userId + "','" + objetoId +"','" + amountBueno + "')";
        PreparedStatement prep = this.connection.prepareStatement(query);

        prep.execute();
        prep.close();
    }
    @Override
    public void insertObjectInInventory( int objetoId, int userId, int amount) throws Exception {
        String query;
        query = "INSERT INTO Inventario (userId, objetoId, amount) VALUES ('" + userId + "','" + objetoId +"','" + amount + "')";
        PreparedStatement prep = this.connection.prepareStatement(query);

        prep.execute();
        prep.close();
    }
    @Override
    public void setWeapon(String nombre, int userId) throws Exception {
        String query = "UPDATE User SET currentWeapon = '" + nombre + "' WHERE id = " +  userId + ";";
        PreparedStatement prep = this.connection.prepareStatement(query);

        prep.execute();
        prep.close();
    }

    @Override
    public void setShield(String shield, int userId) throws Exception {
        String query = "UPDATE User SET currentShield = '" + shield + "' WHERE id = " +  userId + ";";
        PreparedStatement prep = this.connection.prepareStatement(query);

        prep.execute();
        prep.close();
    }

    @Override
    public void updateCash(Objeto objeto, User user) throws Exception {
        int cashe = user.getCash()-objeto.getCoste();
        String query ="UPDATE User SET cash='"+ cashe+"' WHERE id='"+user.getId()+"'";
        log.info(query);
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.execute();
        prep.close();
    }

    public void close() throws Exception {
        this.connection.close();
        log.info("Connection closed");
    }

    private List<Object> find(Class theClass, int id, String username) throws Exception {

        List<Object> res = new ArrayList<>();
        ResultSet rs;
        Object object;
        String query;

        if (id > 0) {
            query = "SELECT * FROM " + theClass.getSimpleName() + " WHERE id = ?";
            PreparedStatement prep = this.connection.prepareStatement(query);
            prep.setInt(1, id);
            prep.execute();
            rs = prep.getResultSet();
        } else if (id == 0) {
            query = "SELECT * FROM " + theClass.getSimpleName();
            Statement statement = this.connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
        } else { //id == -1
            log.info("Username: " + username);
            query = "SELECT * FROM " + theClass.getSimpleName() + " WHERE username = ?";
            PreparedStatement prep = this.connection.prepareStatement(query);
            prep.setString(1, username);
            prep.execute();
            rs = prep.getResultSet();
        }
            log.info("query (find): " + query);
            while (rs.next()) {

                log.info("Creating object...");
                object = theClass.newInstance();
                log.info("Object created");

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    columnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

                    log.info("Column name: " + columnName);

                    switch (rs.getMetaData().getColumnType(i)) {
                        case INTEGER:
                            int intValue = rs.getInt(i);
                            theClass.getMethod("set" + columnName, int.class).invoke(object, intValue);
                            log.info("set" + columnName);
                            break;
                        case VARCHAR:
                            String stringValue = rs.getString(i);
                            theClass.getMethod("set" + columnName, String.class).invoke(object, stringValue);
                            log.info("set" + columnName);
                            break;
                        case TIMESTAMP:
                            Timestamp dateValue = rs.getTimestamp(i);
                            theClass.getMethod("set" + columnName, Timestamp.class).invoke(object, dateValue);
                            break;
                        case BOOLEAN:
                            Boolean booleanValue = rs.getBoolean(i);
                            theClass.getMethod("set" + columnName, Boolean.class).invoke(object, booleanValue);
                            break;
                        default:
                            log.info("Missing type: " + rs.getMetaData().getColumnType(i));
                            break;
                    }
                }
                log.info("Object founded: " + object);
                res.add(object);
            }
            return res;
    }
}
