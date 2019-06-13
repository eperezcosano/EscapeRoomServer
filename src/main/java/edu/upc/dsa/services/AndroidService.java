package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.*;
<<<<<<< HEAD
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.User;
=======
import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.to.ObjTO;
>>>>>>> d2e5932eb4e0adf1b3c62ef99afae065112c64c8
import edu.upc.dsa.to.User.UserProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.ws.rs.*;
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
    public Response profile(@PathParam("id") String id) {
        try {
            String map = this.android.getMapa(id);
            Map mapa = new Map(1, map);
            log.info("Y mi puto string:" + map);
            return Response.status(201).entity(mapa).build();
        } catch (MapNotFoundException e10) {
            return Response.status(404).build();
        } catch (Exception e10) {
            return Response.status(600).build();
        }
    }

    @POST
    @ApiOperation(value = "Set inventory", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Can't set the inventory, retry")
    })
    @Path("/setinventory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setInventory(Inventario inventario) {
        try {
            this.android.setInventary(inventario);
            return Response.status(201).build();
        }catch (Exception e1) {
            return Response.status(500).build();
        }
    }
<<<<<<< HEAD
    @POST
    @ApiOperation(value="updateUser", notes = "asdad")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful" ),
            @ApiResponse(code = 404, message = "First login or register"),
            @ApiResponse(code = 500, message = "Object not found"),
            @ApiResponse(code = 501, message = "You can't buy two same weapons"),
            @ApiResponse(code = 600, message = "Not function for ADMIN"),
            @ApiResponse(code = 700, message = "Exception")
    })
    @PathParam("/updateUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
            this.android.updateUser(user,user.getId() );
            return Response.status(201).entity(updateUser(user)).build();
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

=======
}
>>>>>>> d2e5932eb4e0adf1b3c62ef99afae065112c64c8

