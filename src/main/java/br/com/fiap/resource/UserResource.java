package br.com.fiap.resource;

import br.com.fiap.bo.UserBO;
import br.com.fiap.to.UserTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class UserResource {
    private UserBO userBO = new UserBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(UserTO userTO) {
        UserTO resultado = userBO.saveUser(userTO);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else{
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        UserTO resultado = userBO.findById(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/atualiza-usuario/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Valid UserTO userTO, @PathParam("id") Long id){
        userTO.setId(id);
        UserTO resultado = userBO.updateUser(userTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else  {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePaciente(@PathParam("id") Long id){
        Response.ResponseBuilder response = null;

        if (userBO.deleteUser(id)) {
            response = Response.status(204);
        } else  {
            response = Response.status(404);
        }
        return response.build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
        UserTO user = userBO.findByEmailAndPassword(email, password);
        Response.ResponseBuilder response = null;

        if (user != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }

        response.entity(user);
        return response.build();
    }
}
