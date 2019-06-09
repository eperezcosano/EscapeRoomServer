package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.PasswordNotMatchException;
import edu.upc.dsa.exceptions.UserAlreadyExistsException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.to.*;

import edu.upc.dsa.to.User.UserLogin;
import edu.upc.dsa.to.User.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/auth")
@Path("/auth")
public class AuthService {

    private GameManager auth;
    private Logger log = Logger.getLogger(AuthService.class.getName());

    public AuthService() {
        this.auth = GameManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "register user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 405, message = "UserAlreadyExists"),
            @ApiResponse(code = 404, message = "Impossible to register")
    })
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserRegister user) {
        log.info("POST /auth/register, User: " + user);
        try {
            this.auth.register(user.getUsername(),user.getPassword(),user.getName(),user.getSurname(),user.getMail(),user.getAge());
            log.info("User registered");
            return Response.status(201).build();
        } catch (UserAlreadyExistsException e) {
            return Response.status(405).build();
        } catch (Exception e) {
            log.info("User not registered");
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "login user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserLogin.class),
            @ApiResponse(code = 404, message = "Incorrect user"),
            @ApiResponse(code = 500, message = "Password not match", responseContainer="List")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserLogin user) {
        log.info("POST /auth/login, User: " + user);
        try {
            UserLogin res = this.auth.getUserLogin(user.getUsername(),user.getPassword());
            if (res != null) {
                GenericEntity<UserLogin> entity = new GenericEntity<UserLogin>(res) {};
                log.info("User logged in " + res);
                return Response.status(200).entity(entity).build();
            } else {
                log.info("Incorrect user");
                return Response.status(404).build();
            }
        } catch(PasswordNotMatchException e2) {
            return Response.status(500).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFound"),
            @ApiResponse(code = 405, message = "Impossible to delete")
    })
    @Path("/delete/{userId}")
    public Response deleteUser(@PathParam("userId") int id) {
        log.info("DELETE /auth/delete/" + id);
        try {
            this.auth.deleteUser(id);
            return Response.status(200).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(405).build();
        }
    }
}
