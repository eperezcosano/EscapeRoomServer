package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/user", description = "Endpoint to User Service")
@Path("/user")

public class UserService {

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManager ma;

    public UserService() throws Exception {
        this.ma = GameManagerImpl.getInstance();
        this.ma.añadirObjetosHashMap();
        if (ma.sizeUsers() == 0) {
            logger.info("Mecago en tus.");

            this.ma.addUser("Carlo","Carlo","Carlo","Car","car",21);
            this.ma.addUser("Mario","Mario","Mario","San","mama",21);
            this.ma.addObjectStore("katana");
            this.ma.addObjectStore("corredera");
            this.ma.addObjectStore("fusildetambor");
            this.ma.addObjectStore("keyred");
            this.ma.addObjectStore("keyyellow");
            this.ma.addObjectStore("keyblue");
            this.ma.addObjectStore("clueyellow");
            this.ma.addObjectStore("clueblue");
            this.ma.addObjectStore("cluered");
            this.ma.buyObject("katana","Carlo");
        }
    }
    @POST
    @ApiOperation(value = "Mock Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserLogin.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found", responseContainer="List"),
            @ApiResponse(code = 500, message = "Password not match", responseContainer="List")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserLogin user) {
        try{
            UserLogin u = this.ma.getUserLogin(user.getUsername(),user.getPassword());
            return Response.status(201).entity(u).build();
        }catch(PasswordNotMatchException e2){
            return Response.status(500).build();
        }catch(Exception e3) {
            return Response.status(404).build();
        }
    }
    @POST
    @ApiOperation(value = "Mock Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserProfile.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Existant user", responseContainer="List")
    })
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserProfile user) {
        try{
            User u = this.ma.addUser(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),user.getMail(),user.getAge());
            return Response.status(201).entity(u).build();
        }catch(UserAlreadyExistsException e1) {
            return Response.status(500).build();
        }
    }
    @GET
    @ApiOperation(value = "profile", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserProfile.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/profile/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response profile(@PathParam("username") String username) {
        try{
            UserProfile userProfile = this.ma.getProfile(username);
            return Response.status(201).entity(userProfile).build();
        }catch(UserNotFoundException e1){
            return Response.status(404).build();
        }
    }
    @GET
    @ApiOperation(value = "statistics", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserStatistics.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/statistics/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statistics(@PathParam("username") String username) {
        try{
            UserStatistics userStatistics = this.ma.getStatistics(username);
            return Response.status(201).entity(userStatistics).build();
        }catch(UserNotFoundException e1){
            return Response.status(404).build();
        }
    }
    @GET
    @ApiOperation(value = "inventory", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserInventary.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/inventory/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inventory(@PathParam("username") String username){
        try {
            Inventario userInventary = this.ma.getInventary(username);
            logger.info("Inventario");
            return Response.status(201).entity(userInventary).build();
        }catch (UserNotFoundException e1){
            logger.info("Excepcion e1:,", e1);
            return Response.status(404).build();
        }catch (Exception e)
        {
            logger.info("Excepcion,", e);
            return Response.status(404).build();
        }
    }
    @POST
    @ApiOperation(value = "Buy", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class ),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Object not found")

    })
    @Path("/buy/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buy(Objeto objTO, @PathParam("username") String username) {
        try{
            this.ma.buyObject(objTO.getNombre(),username);
            return Response.status(201).entity(objTO).build();
        }catch(WeaponException e1) {
            return Response.status(500).build();
        }catch (Exception e2) {
            return Response.status(404).build();
        }
    }

}
