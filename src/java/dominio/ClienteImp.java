package dominio;

import Utilidades.Constantes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import dto.Mensaje;

public class ClienteImp {

    // ------------------------------------
    // OBTENER CLIENTES
    // ------------------------------------
    public static List<Cliente> getClientes(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Cliente> respuesta = null;
        if(conexionBD !=null){
            try {
                // La consulta SELECT en el XML ahora hace el JOIN
                respuesta = conexionBD.selectList("cliente.obtenerClientes");
            } catch (Exception e) {
                 System.err.println("Error al obtener clientes: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
             System.err.println(Constantes.MSJ_ERROR_BD);
        }  
        return respuesta;
    }
    
    // ------------------------------------
    // OBTENER CLIENTE POR NOMBRE
    // ------------------------------------
    public static List<Cliente> obtenerClientesPorNombre(String nombre) {
        List<Cliente> clientes = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("nombre", nombre);
                clientes = conexionBD.selectList("cliente.obtenerClientesPorNombre", parametros);
            } catch (Exception e) {
                 System.err.println("Error al obtener clientes por nombre: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return clientes;
    }
    
    // ------------------------------------
    // OBTENER CLIENTE POR CORREO
    // ------------------------------------
    public static List<Cliente> obtenerClientePorCorreo(String correo) {
        List<Cliente> cliente = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("correo", correo);
                cliente = conexionBD.selectList("cliente.obtenerClientesPorCorreo", parametros);
            } catch (Exception e) {
                 System.err.println("Error al obtener cliente por correo: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return cliente;
    }
    
    // ------------------------------------
    // OBTENER CLIENTE POR TELÉFONO
    // ------------------------------------
    public static List<Cliente> obtenerClientePorNumeroTelefonico(String telefono) {
        List<Cliente> cliente = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("telefono", telefono);
                cliente = conexionBD.selectList("cliente.obtenerClientesPorTelefono", parametros);
            } catch (Exception e) {
                 System.err.println("Error al obtener cliente por teléfono: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return cliente;
    }
    
    // ------------------------------------
    // INSERTAR (AGREGAR) CLIENTE
    // ------------------------------------
    public static Mensaje agregarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                // Usa el idCiudad del POJO
                int filasAfectadas = conexion.insert("cliente.agregarCliente", cliente);
                conexion.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente agregado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo agregar el cliente");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Hubo un problema al intentar guardar el cliente: " + e.getMessage());
            } finally {
                if (conexion != null) {
                    conexion.close();
                }
            }
        } else {
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD); 
        }

        return msj;
    }
    
    // ------------------------------------
    // EDITAR CLIENTE
    // ------------------------------------
    public static Mensaje editarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                // Usa el idCiudad del POJO
                int filasAfectadas = conexion.update("cliente.editarCliente", cliente);
                conexion.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente editado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo editar el cliente");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Hubo un error al intentar editar el cliente: " + e.getMessage());
            } finally {
                if (conexion != null) {
                    conexion.close();
                }
            }
        } else {
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD); 
        }

        return msj;
    }
    
    // ------------------------------------
    // ELIMINAR CLIENTE
    // ------------------------------------
    public static Mensaje eliminarCliente(Integer idCliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                HashMap<String, Integer> parametros = new LinkedHashMap<>();
                parametros.put("idCliente", idCliente);
                int filasAfectadas = conexionBD.delete("cliente.eliminarCliente", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente eliminado exitosamente.");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el Cliente para eliminar.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje("Error al eliminar el Cliente: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}