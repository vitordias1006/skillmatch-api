package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(@Valid UsuarioTO usuarioTO) {
        UsuarioTO resultado = usuarioBO.saveUser(usuarioTO);
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
        UsuarioTO resultado = usuarioBO.findById(id);
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
    public Response updateUser(@Valid UsuarioTO usuarioTO, @PathParam("id") Long id){
        usuarioTO.setId(id);
        UsuarioTO resultado = usuarioBO.updateUser(usuarioTO);
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

        if (usuarioBO.deleteUser(id)) {
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
        UsuarioTO user = usuarioBO.findByEmailAndPassword(email, password);
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
