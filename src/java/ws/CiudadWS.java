package ws;

import dominio.CiudadImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Ciudad;

@Path("ciudad")
public class CiudadWS {
    
    // GET: /api/ciudad/por-estado/{idEstado}
    @Path("por-estado/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ciudad> obtenerCiudadesPorIdEstado(@PathParam("idEstado") int idEstado) {
        if (idEstado <= 0) {
            throw new BadRequestException("ID de estado inválido.");
        }
        List<Ciudad> listaCiudades = CiudadImp.obtenerCiudadesPorIdEstado(idEstado);
        if (listaCiudades != null && !listaCiudades.isEmpty()) {
            return listaCiudades;
        } else {
            throw new NotFoundException("No se encontraron ciudades para el ID de estado " + idEstado + ".");
        }
    }
    
    // GET: /api/ciudad/id/{idCiudad}
    @Path("id/{idCiudad}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ciudad obtenerCiudadPorId(@PathParam("idCiudad") int idCiudad) {
        if (idCiudad <= 0) {
            throw new BadRequestException("ID de ciudad inválido.");
        }
        Ciudad ciudad = CiudadImp.obtenerCiudadPorId(idCiudad);
        if (ciudad != null) {
            return ciudad;
        } else {
            throw new NotFoundException("Ciudad con ID " + idCiudad + " no encontrada.");
        }
    }
}