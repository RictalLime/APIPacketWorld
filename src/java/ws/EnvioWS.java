package ws;

import Utilidades.Constantes;
import dominio.EnvioImp; 
import dominio.CiudadImp; // Importación para la validación de Ciudad
import dominio.EstadoImp; // Importación necesaria para la validación de Estado
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
import pojo.Ciudad; 
import pojo.Estado; // Importación necesaria para el POJO Estado

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
        
        // Llama al método en EnvioImp.java
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
        
        // El método validarEnvio ahora verifica los IDs de Ciudad y Estado
        if (validarEnvio(envio)) {
            throw new BadRequestException("Datos del envío inválidos. Verifique IDs de Cliente, Colaborador, Estado, Ciudades y Estados de Origen/Destino.");
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
        
        // El método validarEnvio ahora verifica los IDs de Ciudad y Estado
        if (validarEnvio(envio)) {
            throw new BadRequestException("Datos del envío inválidos. Verifique IDs de Cliente, Colaborador, Estado, Ciudades y Estados de Origen/Destino.");
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
    
    // --- MÉTODO DE VALIDACIÓN (MODIFICADO) ---
    
    /**
     * Valida los campos esenciales y verifica la existencia de las claves foráneas (FKs) 
     * de Ciudad y ESTADO en la base de datos.
     * @param envio El objeto Envio a validar.
     * @return true si se encuentra un error, false si la validación es exitosa.
     */
    private boolean validarEnvio(Envio envio) {
        boolean error = false;
        
        // 1. Validación de IDs esenciales
        if (envio.getIdCliente() == null || envio.getIdCliente() <= 0) {
            error = true;
        }
        if (envio.getIdColaborador() == null || envio.getIdColaborador() <= 0) {
            error = true;
        }
        if (envio.getIdEstadoDeEnvio() == null || envio.getIdEstadoDeEnvio() <= 0) {
            error = true;
        }
        
        // 2. Validación de Origen (Ciudad y Estado)
        
        // 2a. Validar ID de Ciudad Origen
        if (envio.getIdOrigenCiudad() == null || envio.getIdOrigenCiudad() <= 0) {
            error = true;
        } else {
            Ciudad ciudadOrigen = CiudadImp.obtenerCiudadPorId(envio.getIdOrigenCiudad());
            if (ciudadOrigen == null) {
                 error = true;
            }
        }
        
        // 2b. Validar ID de Estado de Origen (NUEVO)
        if (envio.getIdOrigenEstado() == null || envio.getIdOrigenEstado() <= 0) {
            error = true;
        } else {
            Estado estadoOrigen = EstadoImp.obtenerEstadoPorId(envio.getIdOrigenEstado());
            if (estadoOrigen == null) {
                error = true;
            }
        }
        
        // 3. Validación de Destino (Ciudad y Estado)
        
        // 3a. Validar ID de Ciudad Destino
        if (envio.getIdDestinoCiudad() == null || envio.getIdDestinoCiudad() <= 0) {
            error = true;
        } else {
            Ciudad ciudadDestino = CiudadImp.obtenerCiudadPorId(envio.getIdDestinoCiudad());
            if (ciudadDestino == null) {
                 error = true;
            }
        }
        
        // 3b. Validar ID de Estado de Destino (NUEVO)
        if (envio.getIdDestinoEstado() == null || envio.getIdDestinoEstado() <= 0) {
            error = true;
        } else {
            Estado estadoDestino = EstadoImp.obtenerEstadoPorId(envio.getIdDestinoEstado());
            if (estadoDestino == null) {
                error = true;
            }
        }

        // 4. Validación de campos de dirección de texto
        if (envio.getOrigenCalle() == null || envio.getOrigenCalle().trim().isEmpty()) error = true;
        if (envio.getOrigenNumero() == null || envio.getOrigenNumero().trim().isEmpty()) error = true;
        if (envio.getDestinoCalle() == null || envio.getDestinoCalle().trim().isEmpty()) error = true;
        
        return error;
    }

}