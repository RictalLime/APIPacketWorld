/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import dominio.ConductorAsignadoImp;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import pojo.ConductorAsignado;
import dto.Mensaje;

/**
 *
 * @author Tron7
 */

@Path("conductor-asignado")
public class ConductorAsignadoWS {
    
    //http://localhost:8095/packet-world/api/conductor-asignado/obtener-todos
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConductorAsignado> obtenerConductoresAsignados() {
        List<ConductorAsignado> lista = new ArrayList<>();
        lista = ConductorAsignadoImp.obtenerTodos();
        
        

        return lista;
    }
    
    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarConductorAsignado(ConductorAsignado conductor) {
        if (conductor == null) {
            throw new BadRequestException("Los datos del conductor asignado son inválidos o están vacíos.");
        }
        return ConductorAsignadoImp.registrar(conductor);
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarConductorAsignado(ConductorAsignado conductor) {
        if (conductor == null || conductor.getIdConductoresAsignados() <= 0) {
            throw new BadRequestException("Los datos del conductor asignado son inválidos o el ID es incorrecto.");
        }
        return ConductorAsignadoImp.editar(conductor);
    }

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarConductorAsignado(@PathParam("id") int idConductorAsignado) {
        if (idConductorAsignado <= 0) {
            throw new BadRequestException("El idConductoresAsignados debe ser mayor que 0.");
        }
        return ConductorAsignadoImp.eliminar(idConductorAsignado);
    }
}
