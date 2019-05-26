package edu.upc.dsa.mysql;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
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

        PreparedStatement prep = this.connection.prepareStatement(query);
        for (int i = 1; i < fields.length + 1; i++) {
            if (int.class.equals(fields[i - 1].getType())) {
                String labelName = fields[i - 1].getName().substring(0, 1).toUpperCase() + fields[i - 1].getName().substring(1);
                int intValue = (int) entity.getClass().getMethod("get" + labelName).invoke(entity);
                prep.setInt(i, intValue);
            } else if (String.class.equals(fields[i - 1].getType())) {
                prep.setString(i, new PropertyDescriptor(fields[i - 1].getName(), entity.getClass()).getReadMethod().invoke(entity).toString());
            }
        }

        prep.execute();
        prep.close();

        log.info("query: " + query);
    }

    public User getByUsername(String username) throws Exception {
        return (User) find(User.class, -1, username).get(0);
    }

    public Object get(Class theClass, int id) throws Exception {
        return find(theClass, id, null).get(0);
    }

    public List<Object> findAll(Class theClass) throws Exception {
        return find(theClass, 0, null);
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
        String query ="DELETE FROM " + theClass.getSimpleName() + " WHERE id = ?";
        PreparedStatement prep = this.connection.prepareStatement(query);
        prep.setInt(1 , id);
        prep.execute();

        log.info("query: " + query);
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
        }
        else if (id == 0) {
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

            for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i);
                columnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

                log.info("Column name: " + columnName);

                switch (rs.getMetaData().getColumnType(i))
                {
                    case INTEGER:
                        int intValue = rs.getInt(i);
                        theClass.getMethod("set" + columnName, int.class).invoke(object, intValue);
                        break;
                    case VARCHAR:
                        String stringValue = rs.getString(i);
                        theClass.getMethod("set" + columnName, String.class).invoke(object, stringValue);
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

            log.info("Objecto founded: " + object);
            res.add(object);
        }

        return res;
    }

}
