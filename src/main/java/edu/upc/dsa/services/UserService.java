package edu.upc.dsa.services;

import io.swagger.annotations.Api;

import javax.ws.rs.*;

@Api(value = "/users")
@Path("/users")
public class UserService {

//    private GameManager users;
//    final static Logger log = Logger.getLogger(UserService.class.getName());
//    public UserService() { this.users = new GameManagerImpl();}
//
//    @GET
//    @ApiOperation(value = "Get Inventario of a user");
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful"),
//            @ApiResponse(code = 404, message = "User doesn't exist")
//    })
//    @Path("/inventario")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getUserInventario(@PathParam("idUser") int idUser) throws Exception {
//        try{
//            List<String> objetos = this.users.getUserInventario(idUser);
//            GenericEntity<List<String>> entity = new GenericEntity<List<String>>(objetos) {};
//            return Response.status(201).entity(entity).build();
//
//        }catch (UserNotFoundException e){
//            e.printStackTrace();
//            return Response.status(404).build();
//        }
//
//
//
//    }

}
