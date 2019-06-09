package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Api(value = "/admin")
@Path("/admin")
public class AdminService {

    private GameManager auth;
    private Logger log = Logger.getLogger(AdminService.class.getName());

    public AdminService() {
        this.auth = GameManagerImpl.getInstance();
    }

    @DELETE
    @ApiOperation(value = "delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFound"),
            @ApiResponse(code = 405, message = "Impossible to delete")
    })
    @Path("{username}/delete/{userId}")
    public Response deleteUser(@PathParam("userId") int id, @PathParam("username") String username) {
        log.info("DELETE /auth/delete/" + id);
        try {
            this.auth.deleteUser(username,id);
            return Response.status(200).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(405).build();
        }
    }
    @DELETE
    @ApiOperation(value = "delete an object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "ObjectNotFound"),
            @ApiResponse(code = 405, message = "Impossible to delete")
    })
    @Path("{username}/delete/{nameObject}")
    public Response deleteObject(@PathParam("nameObject") String nameObject, @PathParam("username") String username) {
        log.info("DELETE /auth/delete/" + nameObject);
        try {
            this.auth.deleteObjectStore(username,nameObject);
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
