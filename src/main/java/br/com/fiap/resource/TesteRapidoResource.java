package br.com.fiap.resource;

import br.com.fiap.bo.TesteRapidoBO;
import br.com.fiap.to.TesteRapidoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/testerapido")
public class TesteRapidoResource {
    private TesteRapidoBO testeRapidoBO = new TesteRapidoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTest(@Valid TesteRapidoTO testeRapidoTO){
        TesteRapidoTO resultado = testeRapidoBO.saveTest(testeRapidoTO);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else  {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllMyTests(@PathParam("id_usuario") long userId){
        ArrayList<TesteRapidoTO> resultado = testeRapidoBO.findAllMyTests(userId);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else   {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/atualizar-teste/{id_teste}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTest(@Valid TesteRapidoTO testeRapidoTO, @PathParam("id_teste") Long testId){
        testeRapidoTO.setTestId(testId);
        TesteRapidoTO resultado = testeRapidoBO.updateTest(testeRapidoTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else   {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id_usuario}")
    public Response deleteTest(@PathParam("id_usuario") Long userId){
        Response.ResponseBuilder response = null;

        if (testeRapidoBO.deleteTest(userId)) {
            response = Response.status(204);
        } else   {
            response = Response.status(404);
        }
        return response.build();
    }
}
