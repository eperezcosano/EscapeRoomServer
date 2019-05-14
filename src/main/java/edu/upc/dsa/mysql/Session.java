package edu.upc.dsa.mysql;

import edu.upc.dsa.models.User;

import java.util.List;

public interface Session {
    void save(Object entity) throws Exception;
    Object get(Class theClass, int id) throws Exception;
    User getByUsername(String username) throws Exception;
    List<Object> findAll(Class theClass) throws Exception;
    void update(Object object, int id) throws Exception;
    void delete(Class theClass, int id) throws Exception;
    void close() throws Exception;
}