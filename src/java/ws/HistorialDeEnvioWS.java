/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.HistorialEnvioImp;
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
import pojo.HistorialDeEnvio;
import pojo.RespuestaRastreo; // <--- Importante: Importar el nuevo POJO
import dto.Mensaje;

@Path("historial-envio")
public class HistorialDeEnvioWS {
    
    // Este endpoint mantiene la compatibilidad por si lo usas en un panel admin
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeEnvio> obtenerHistorialEnvio() {
        return HistorialEnvioImp.obtenerTodos();
    }
    
    // --- ENDPOINT ACTUALIZADO PARA EL RASTREO PÚBLICO ---
    // Ahora retorna 'RespuestaRastreo' en lugar de 'List<HistorialDeEnvio>'
    @Path("obtener-historial/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaRastreo obtenerRastreoPorNoGuia(@PathParam("noGuia") String noGuia) {
        System.out.println("Número de guía recibido para rastreo completo: " + noGuia);

        if (noGuia != null && !noGuia.isEmpty()) {
            // Llamamos al nuevo método orquestador
            RespuestaRastreo respuesta = HistorialEnvioImp.obtenerRastreoCompleto(noGuia);

            if (respuesta == null) {
                throw new NotFoundException("No se encontró información para la guía: " + noGuia);
            }
            return respuesta;
        }
        throw new BadRequestException("El número de guía proporcionado es inválido.");
    }
    
    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null) {
            throw new BadRequestException("Los datos del historial de envío son inválidos o están vacíos.");
        }
        return HistorialEnvioImp.registrar(historial);
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null || historial.getIdHistorialDeEnvio() <= 0) {
            throw new BadRequestException("Los datos del historial de envío son inválidos o el ID es incorrecto.");
        }
        return HistorialEnvioImp.editar(historial);
    }

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarHistorialEnvio(@PathParam("id") int idHistorialDeEnvio) {
        if (idHistorialDeEnvio <= 0) {
            throw new BadRequestException("El idHistorialDeEnvio debe ser mayor que 0.");
        }
        return HistorialEnvioImp.eliminar(idHistorialDeEnvio);
    }
}