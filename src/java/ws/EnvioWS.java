package ws;

import Utilidades.Constantes;
import dominio.EnvioImp; 
import dominio.CiudadImp; // Importación necesaria para la validación
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
import pojo.Envio;
import pojo.EstadoDeEnvio;
import dto.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Ciudad; // Necesario para la validación de ID de ciudad


@Path("envio")
public class EnvioWS {
    
    @Path("actualizar-estado") 
    @PUT // Es importante usar PUT o POST para actualizaciones
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEstadoEnvio(Envio envio) {
        
        // Validación mínima de los IDs que necesitamos
        if (envio == null || envio.getIdEnvio() == null || envio.getIdEnvio() <= 0 || 
            envio.getIdEstadoDeEnvio() == null || envio.getIdEstadoDeEnvio() <= 0) {
            
            throw new BadRequestException("El ID del Envío o el ID del Estado son inválidos.");
        }
        
        // Llama al método que ya implementaste en EnvioImp.java
        return EnvioImp.actualizarEstadoEnvio(envio); 
    }
    
    // --- Servicios de LECTURA (GET) ---
    
    @Path("obtener-envios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Envio> obtenerEnvios() {
        return EnvioImp.obtenerEnvios();
    }
    
    @Path("obtener-envios-por-noguia/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Envio> obtenerEnviosPorNoGuia(@PathParam("noGuia") String noGuia) {
        if(noGuia!=null && !noGuia.isEmpty()){
            return EnvioImp.obtenerEnviosPorNoGuia(noGuia);
        }
        throw new BadRequestException("El número de guía es inválido.");
    }
    
    @Path("obtener-estados-envios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<EstadoDeEnvio> obtenerEstadosEnvios() {
        return EnvioImp.obtenerEstadosDeEnvios();
    }
    
    @Path("obtener-envios-conductor/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Envio> obtenerEnvioConductor(@PathParam("idColaborador") int idColaborador){
        return EnvioImp.obtenerEnviosConductor(idColaborador);
    }

    // --- Servicios de ESCRITURA (POST/PUT) ---
    
    @Path("agregar-envio")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(Envio envio) {
        if (envio == null) {
            throw new BadRequestException("Los datos del envío son inválidos o están vacíos.");
        }
        
        if (validarEnvio(envio)) {
            throw new BadRequestException("Datos del envío inválidos. Verifique IDs de Cliente, Colaborador, Estado o Ciudades.");
        }
        
        return EnvioImp.registrarEnvio(envio);
    }

    @Path("editar-envio")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEnvio(Envio envio) {
        if (envio == null || envio.getIdEnvio() == null || envio.getIdEnvio() <= 0) {
            throw new BadRequestException("Los datos del envío o su ID son inválidos.");
        }
        
        if (validarEnvio(envio)) {
            throw new BadRequestException("Datos del envío inválidos. Verifique IDs de Cliente, Colaborador, Estado o Ciudades.");
        }
        
        return EnvioImp.editarEnvio(envio);
    }

    // --- Servicio de ELIMINAR (DELETE) ---
    
    @Path("eliminar-envio/{idEnvio}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEnvio(@PathParam("idEnvio") int idEnvio) {
        if (idEnvio <= 0) {
            throw new BadRequestException("El idEnvio debe ser mayor que 0.");
        }
        return EnvioImp.eliminarEnvio(idEnvio);
    }
    
    // --- MÉTODO DE VALIDACIÓN ---
    
    private boolean validarEnvio(Envio envio) {
        boolean error = false;
        
        // 1. Validación de campos esenciales (no vacíos/nulos)
        if (envio.getIdCliente() == null || envio.getIdCliente() <= 0) {
            error = true;
        }
        if (envio.getIdColaborador() == null || envio.getIdColaborador() <= 0) {
            error = true;
        }
        if (envio.getIdEstadoDeEnvio() == null || envio.getIdEstadoDeEnvio() <= 0) {
            error = true;
        }
        
        // 2. Validación de ID de Ciudad Origen (Clave Foránea)
        if (envio.getIdCiudadOrigen() == null || envio.getIdCiudadOrigen() <= 0) {
            error = true;
        } else {
            Ciudad ciudadOrigen = CiudadImp.obtenerCiudadPorId(envio.getIdCiudadOrigen());
            if (ciudadOrigen == null) {
                 error = true;
            }
        }
        
        // 3. Validación de ID de Ciudad Destino (Clave Foránea)
        if (envio.getIdCiudadDestino() == null || envio.getIdCiudadDestino() <= 0) {
            error = true;
        } else {
            Ciudad ciudadDestino = CiudadImp.obtenerCiudadPorId(envio.getIdCiudadDestino());
            if (ciudadDestino == null) {
                 error = true;
            }
        }

        // 4. Validación de campos de dirección de texto (ejemplo: no vacíos)
        if (envio.getOrigenCalle() == null || envio.getOrigenCalle().trim().isEmpty()) error = true;
        if (envio.getOrigenNumero() == null || envio.getOrigenNumero().trim().isEmpty()) error = true;
        if (envio.getDestinoCalle() == null || envio.getDestinoCalle().trim().isEmpty()) error = true;
        
        // *Se pueden añadir más validaciones de formato (REGEX) para CP, noGuia, etc. aquí*
        
        return error;
    }

}