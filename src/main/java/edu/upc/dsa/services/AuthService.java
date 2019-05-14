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
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/auth")
@Path("/auth")
public class AuthService {

    private GameManager auth;
    private Logger log = Logger.getLogger(AuthService.class.getName());

    public AuthService() {
        this.auth = new GameManagerImpl();
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
    public Response register(UserTO user) {
        log.info("POST /auth/register, User: " + user);
        try {
            this.auth.register(user);
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
            @ApiResponse(code = 200, message = "Successful", response = UserTO.class),
            @ApiResponse(code = 404, message = "Incorrect user")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserTO user) {
        log.info("POST /auth/login, User: " + user);
        try {
            UserTO res = this.auth.login(user);
            if (res != null) {
                GenericEntity<UserTO> entity = new GenericEntity<UserTO>(res) {};
                log.info("User logged in " + res);
                return Response.status(201).entity(entity).build();
            } else {
                log.info("Incorrect user");
                return Response.status(404).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
