package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.MapNotFoundException;
import edu.upc.dsa.exceptions.NotFunctionForAdminExcepction;
import edu.upc.dsa.exceptions.ObjectExistException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.to.User.UserProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/android")
@Path("/android")
public class AndroidService {
    private GameManager android;
    private Logger log = Logger.getLogger(AuthService.class.getName());

    public AndroidService() {
        this.android = GameManagerImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "profile", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Map.class),
            @ApiResponse(code = 404, message = "Map not found"),
            @ApiResponse(code = 600, message = "Exception")
    })

    @Path("/map/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response profile(@PathParam("id") int id) {
        try{
            String map = this.android.getMapa(id);
            return Response.status(201).entity(map).build();
        } catch(MapNotFoundException e10){
            return Response.status(404).build();
        } catch(Exception e10){
        return Response.status(600).build(); }
    }
}
