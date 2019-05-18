package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.mysql.UserAlreadyExistsException;
import edu.upc.dsa.to.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.jaxrs.PATCH;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users")
@Path("/users")
public class UserService {

    private GameManager users;
    final static Logger log = Logger.getLogger(UserService.class.getName());
    public UserService() { this.users = new GameManagerImpl();}

    @GET
    @ApiOperation(value = "Get Inventario of a user");
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User doesn't exist")
    })
    @Path("/getInventario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserInventario(@PathParam("idUser") int idUser) throws Exception {
        List<String> objetos = this.users.getUserInventario(idUser);
        GenericEntity<List<String>> entity = new GenericEntity<List<String>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;
    }



}
