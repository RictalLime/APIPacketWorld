package ws;

// IMPORTANTE: Importamos la clase CORRECTA (la que tiene el 'De')
import dominio.HistorialDeEnvioImp; 

import java.util.ArrayList; // Agregamos ArrayList para manejar listas vacías
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import pojo.HistorialDeEnvio;
import dto.Mensaje;

@Path("historial-envio")
public class HistorialDeEnvioWS {
    
    // --- RASTREO PÚBLICO ---
    @Path("obtener-historial/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeEnvio> obtenerHistorialPorGuia(@PathParam("noGuia") String noGuia) {
        // DIAGNÓSTICO: Verificamos qué llega al servicio
        System.out.println("--- WS: Solicitud recibida para obtener historial ---");
        System.out.println("Número de guía recibido: " + noGuia);

        if (noGuia != null && !noGuia.isEmpty()) {
            List<HistorialDeEnvio> resultados = HistorialDeEnvioImp.obtenerHistorial(noGuia);
            
            // DIAGNÓSTICO: Verificamos qué devuelve la base de datos
            if (resultados == null) {
                System.out.println("WS: La base de datos devolvió NULL.");
                return new ArrayList<>(); // Retornar lista vacía en lugar de null para evitar errores en cliente
            } else {
                System.out.println("WS: Registros encontrados: " + resultados.size());
                // Imprimir el primer elemento si existe para verificar datos
                if (!resultados.isEmpty()) {
                    System.out.println("Dato ejemplo (ID): " + resultados.get(0).getIdHistorialDeEnvio());
                    System.out.println("Dato ejemplo (Estado): " + resultados.get(0).getIdEstadoDeEnvio());
                }
                return resultados;
            }
        }
        
        System.out.println("WS: Error - Número de guía inválido o vacío.");
        throw new BadRequestException("El número de guía proporcionado es inválido.");
    }
    
    // --- OTROS MÉTODOS CRUD ---
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialDeEnvio> obtenerTodos() {
        return HistorialDeEnvioImp.obtenerTodos();
    }
    
    @Path("agregar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null) {
            throw new BadRequestException("Datos inválidos.");
        }
        return HistorialDeEnvioImp.registrar(historial);
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarHistorialEnvio(HistorialDeEnvio historial) {
        if (historial == null || historial.getIdHistorialDeEnvio() <= 0) {
            throw new BadRequestException("ID incorrecto.");
        }
        return HistorialDeEnvioImp.editar(historial);
    }

    @Path("eliminar/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarHistorialEnvio(@PathParam("id") int idHistorialDeEnvio) {
        if (idHistorialDeEnvio <= 0) {
            throw new BadRequestException("ID inválido.");
        }
        return HistorialDeEnvioImp.eliminar(idHistorialDeEnvio);
    }
}