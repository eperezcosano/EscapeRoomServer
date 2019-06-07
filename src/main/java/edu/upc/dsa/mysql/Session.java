package edu.upc.dsa.mysql;

import edu.upc.dsa.exceptions.BDException;
import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.ObjetoInventario;
import edu.upc.dsa.models.User;

import java.util.List;

public interface Session {
    void save(Object entity) throws Exception;
    Object get(Class theClass, int id) throws Exception;
    User getByUsername(String username) throws Exception;
    List<Object> findAll(Class theClass) throws Exception;
    List<ObjetoInventario> getInventario (String username) throws Exception;
    void update(Object object, int id) throws Exception;
    void delete(Class theClass, int id) throws Exception;
    void buy(int objetoId, int userId , int amount) throws Exception;
    void close() throws Exception;
}