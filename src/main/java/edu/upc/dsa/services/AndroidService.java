package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Inventario;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.to.ObjTO;
import edu.upc.dsa.to.User.UserProfile;
import edu.upc.dsa.to.User.UserStatistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
            @ApiResponse(code = 201, message = "Successful", response = Map.class, responseContainer ="List"),
            @ApiResponse(code = 404, message = "Map not found"),
            @ApiResponse(code = 600, message = "Exception")
    })

    @Path("/maps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maps() {
        try {
            List<String> mapas = this.android.getMapas();
            int i=1;
            List<Map> listaMapas = new ArrayList<>();
            for(String mapasito : mapas)
            {
                Map map = new Map(i, mapasito);
                log.info("Y mi puto string:" + map);
                listaMapas.add(map);
                i++;
            }
            GenericEntity<List<Map>> entity = new GenericEntity<List<Map>>(listaMapas){};
            return Response.status(201).entity(entity).build();
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
    @Path("/update/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserStatistics user, @PathParam("username") String username) {
        try {
            this.android.updateUser(user,username);
            return Response.status(201).build();
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



