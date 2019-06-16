package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.ObjTO;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserRanking;
import edu.upc.dsa.to.User.UserStatistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/user", description = "Endpoint to User Service")
@Path("/user")

public class UserService {

    final static Logger logger = Logger.getLogger(UserService.class);

    private GameManager ma;

    public UserService() throws Exception {
        this.ma = GameManagerImpl.getInstance();
        this.ma.añadirObjetosHashMap();
    }
    @GET
    @ApiOperation(value = "profile", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserProfile.class),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 600, message = "Not function for ADMIN")
    })

    @Path("/profile/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response profile(@PathParam("username") String username) {
        try{
            UserProfile userProfile = this.ma.getProfile(username);
            return Response.status(201).entity(userProfile).build();
        }catch(UserNotFoundException e1){
            return Response.status(404).build();
        } catch(NotFunctionForAdminExcepction e10){
            return Response.status(600).build();
        }
    }
    @GET
    @ApiOperation(value = "statistics", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserStatistics.class),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 600, message = "Not function for ADMIN")

    })
    @Path("/statistics/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statistics(@PathParam("username") String username) {
        try{
            UserStatistics userStatistics = this.ma.getStatistics(username);
            return Response.status(201).entity(userStatistics).build();
        }catch(UserNotFoundException e1){
            return Response.status(404).build();
        }catch(NotFunctionForAdminExcepction e10){
            return Response.status(600).build();
        }
    }
    @GET
    @ApiOperation(value = "inventory", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Inventario.class),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "You not have any object"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")

    })

    @Path("/inventory/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inventory(@PathParam("username") String username){
        try {
            Inventario inventario = this.ma.getInventary(username);
            if(inventario==null)
            { return Response.status(500).build();}
            else return Response.status(201).entity(inventario).build();
        }catch (UserNotFoundException e1){
            return Response.status(404).build();
        }catch(NotFunctionForAdminExcepction e10){
            return Response.status(600).build();
        }catch (Exception e)
        { return Response.status(700).build(); }
    }
    @GET
    @ApiOperation(value = "ranking", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserRanking.class, responseContainer ="List"),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "You not have any object"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")

    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ranking () {
        try {
            List<UserRanking> ranking = this.ma.getRanking();
            if (ranking.size()==0) {
                return Response.status(500).build();
            } else return Response.status(201).entity(ranking).build();
        } catch (UserNotFoundException e1) {
            return Response.status(404).build();
        } catch (NotFunctionForAdminExcepction e10) {
            return Response.status(600).build();
        } catch (Exception e) {
            return Response.status(700).build();
        }
    }
    @POST
    @ApiOperation(value = "Buy", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjTO.class ),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "Object not found"),
            @ApiResponse(code = 501, message = "You can't buy two same weapons"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")
    })
    @Path("/buy/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buy(ObjTO objTO, @PathParam("username") String username) {
        try{
            this.ma.buyObject(objTO.getNombre(),username);
            return Response.status(201).entity(objTO).build();
        }catch(WeaponException e1)
        {return Response.status(501).build();
        }catch (ObjectNotExistException e2)
        { return Response.status(500).build();
        }catch (UserNotFoundException e3)
        { return Response.status(404).build();
        }catch(NotFunctionForAdminExcepction e10){
            return Response.status(600).build();
        }catch (Exception e4)
        { return Response.status(700).build();
        }
    }
    @POST
    @ApiOperation(value = "setWeapon", notes = "sets a weapon to an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "Object not found"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")
    })
    @Path("/setWeapon/{username}")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response setWeapon(ObjTO objTO,@PathParam("username") String username) {
        try {
            this.ma.setWeapon(objTO.getNombre(), username);
            return Response.status(201).entity(objTO).build();
        } catch (ObjectNotExistException e2) {
            return Response.status(500).build();
        } catch (UserNotFoundException e3) {
            return Response.status(404).build();
        } catch (NotFunctionForAdminExcepction e10) {
            return Response.status(600).build();
        } catch (Exception e4) {
            return Response.status(700).build();


        }
    }
    @POST
    @ApiOperation(value = "setShield", notes = "sets a weapon to an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "Object not found"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")
    })
    @Path("/setShield/{username}")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response setShield(String shield,@PathParam("username") String username) {
        try {
            this.ma.setShield(shield, username);
            return Response.status(201).entity(shield).build();
        } catch (ObjectNotExistException e2) {
            return Response.status(500).build();
        } catch (UserNotFoundException e3) {
            return Response.status(404).build();
        } catch (NotFunctionForAdminExcepction e10) {
            return Response.status(600).build();
        } catch (Exception e4) {
            return Response.status(700).build();
        }
    }
}
