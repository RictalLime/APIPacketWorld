package ws;

import Utilidades.Utilidades;
import com.google.gson.Gson;
import dominio.ClienteImp;
import dominio.CiudadImp; 
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;
import pojo.Ciudad; 
import dto.Mensaje;

@Path("cliente")
public class ClienteWS {
    
    // --- Servicios de LECTURA (GET) ---
    
    @Path("obtener-clientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes(){
        return ClienteImp.getClientes();
    }
    
    @Path("obtener-clientes-nombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getCLientesPorNombre(@PathParam("nombre") String nombre){
        if(!nombre.isEmpty() && Utilidades.validarInyecciónSQL(nombre)){
            return ClienteImp.obtenerClientesPorNombre(nombre);
        }
        throw new BadRequestException("Nombre de búsqueda inválido.");
    }
    
    @Path("obtener-cliente-correo/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getCLientePorCorreo(@PathParam("correo") String correo){
        if(!correo.isEmpty() && Utilidades.validarInyecciónSQL(correo)){
            return ClienteImp.obtenerClientePorCorreo(correo);
        }
        throw new BadRequestException("Correo de búsqueda inválido.");
    }
    
    @Path("obtener-cliente-numero-telefono/{telefono}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getCLientePorNumeroTelefono(@PathParam("telefono") String telefono){
        if(!telefono.isEmpty() && Utilidades.validarInyecciónSQL(telefono)){
            return ClienteImp.obtenerClientePorNumeroTelefonico(telefono);
        }
        throw new BadRequestException("Número telefónico de búsqueda inválido.");
    }
    
    // --- Servicio de ESCRITURA: AGREGAR ---

    @Path("agregar-cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarCliente(String jsonCliente) {
        if (!jsonCliente.isEmpty()) {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            
            if(validarCliente(cliente)){ 
                throw new BadRequestException("Datos del cliente inválidos o ID de ciudad no existente.");
            }else{
                return ClienteImp.agregarCliente(cliente);
            }
        } else {
            throw new BadRequestException("Parámetro inválido: el cliente no puede estar vacio.");
        }
    }
    
    // --- Servicio de ESCRITURA: EDITAR ---
    
    @Path("editar-cliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String jsonCliente) {
        if (!jsonCliente.isEmpty()) {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            
            if(validarCliente(cliente)){
               throw new BadRequestException("Datos del cliente inválidos o ID de ciudad no existente.");
            }else{
               return ClienteImp.editarCliente(cliente);
            }
        } else {
            throw new BadRequestException("Datos de cliente vacíos.");
        }
    }
    
    // --- Servicio de ELIMINAR ---
    
    @Path("eliminar-cliente")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@FormParam("idCliente") Integer idCliente){
        if(idCliente != null && idCliente > 0){
            return ClienteImp.eliminarCliente(idCliente);
        }
        throw new BadRequestException("ID de cliente inválido.");
    }
    
    // --- VALIDACIÓN DE CLIENTE (MODIFICADA) ---
    
    private boolean validarCliente(Cliente cliente) {
        boolean error = false;
        
        // 1. Validaciones de formato (REGEX)
        if (cliente.getNombre() == null || !cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            error = true;
        }
        if (cliente.getApellidoPaterno() == null || !cliente.getApellidoPaterno().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            error = true;
        }
        if (cliente.getApellidoMaterno() == null || !cliente.getApellidoMaterno().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            error = true;
        }
        if (cliente.getTelefono() == null || !cliente.getTelefono().matches("^\\d{10}$")) {
            error = true;
        }
        if (cliente.getCorreo() == null || !cliente.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            error = true;
        }
        
        // 2. VALIDACIÓN CRÍTICA DE UBICACIÓN (NORMALIZADA)
        // Se valida que el idCiudad exista en la base de datos.
        if (cliente.getIdCiudad() == null || cliente.getIdCiudad() <= 0) {
            System.err.println("Error de validacion: ID de ciudad es nulo o cero.");
            error = true;
        } else {
            Ciudad ciudadExistente = CiudadImp.obtenerCiudadPorId(cliente.getIdCiudad());
            if (ciudadExistente == null) {
                 System.err.println("Error de validacion: El ID de ciudad no existe en la BD.");
                 error = true;
            }
        }
        
        return error;
    }
}