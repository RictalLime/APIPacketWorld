/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.PaqueteImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import pojo.Paquete;
import dto.Mensaje;

@Path("paquetes")
public class PaqueteWS {

    @Path("obtener-paquetes-envio/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetesPorEnvio(@PathParam("idEnvio") int idEnvio) {
        if (idEnvio <= 0) {
            throw new BadRequestException("El idEnvio debe ser mayor que 0.");
        }
        List<Paquete> paquetes = PaqueteImp.obtenerPaquetesPorEnvio(idEnvio);
        if (paquetes != null && !paquetes.isEmpty()) {
            return paquetes;
        } else {
            throw new NotFoundException("No se encontraron paquetes para el envío con idEnvio: " + idEnvio);
        }
    }
    
    @Path("obtener-paquetes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes() {
       return PaqueteImp.obtenerPaquetes();
    }
    
    // CORRECCIÓN: Devuelve OBJETO (Paquete), no List<Paquete>
    @Path("obtener-paquete-por-noguia/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paquete obtenerPaquetePorNoGuia(@PathParam("noGuia") String noGuia) {
        if(noGuia != null && !noGuia.isEmpty()){
            // PaqueteImp retorna un objeto, y aquí el WS retorna un objeto. ¡Tipos compatibles!
            return PaqueteImp.obtenerPaquetePorNoGuia(noGuia);
        }
        throw new BadRequestException("Número de guía inválido");
    }

    @Path("agregar-paquete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("Los datos del paquete son inválidos o están vacíos.");
        }
        return PaqueteImp.registrar(paquete);
    }

    @Path("editar-paquete")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new BadRequestException("Los datos del paquete son inválidos o están vacíos.");
        }
        return PaqueteImp.editar(paquete);
    }

    @Path("eliminar-paquete/{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") int idPaquete) {
        if (idPaquete <= 0) {
            throw new BadRequestException("El idPaquete debe ser mayor que 0.");
        }
        return PaqueteImp.eliminar(idPaquete);
    }
}
