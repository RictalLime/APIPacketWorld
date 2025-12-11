package ws;

import dominio.EstadoImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Estado;

@Path("estado")
public class EstadoWS {
    
    // GET: /api/estado/todos
    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> obtenerTodosLosEstados() {
        List<Estado> listaEstados = EstadoImp.obtenerTodosLosEstados();
        if (listaEstados != null && !listaEstados.isEmpty()) {
            return listaEstados;
        } else {
            throw new NotFoundException("No se encontraron estados.");
        }
    }
    
    // GET: /api/estado/id/{idEstado}
    @Path("id/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Estado obtenerEstadoPorId(@PathParam("idEstado") int idEstado) {
        if (idEstado <= 0) {
            throw new BadRequestException("ID de estado inválido.");
        }
        Estado estado = EstadoImp.obtenerEstadoPorId(idEstado);
        if (estado != null) {
            return estado;
        } else {
            throw new NotFoundException("Estado con ID " + idEstado + " no encontrado.");
        }
    }
}