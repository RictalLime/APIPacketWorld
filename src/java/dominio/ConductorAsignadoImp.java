package dominio;

import Utilidades.Constantes; // Se mantiene solo para el MSJ_ERROR_BD
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.ConductorAsignado;
import dto.Mensaje;

/**
 *
 * @author Tron7
 */
public class ConductorAsignadoImp {
    
    // ------------------------------------
    // OBTENER TODOS
    // ------------------------------------
    public static List<ConductorAsignado> obtenerTodos() {
        List<ConductorAsignado> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("conductorAsignado.getAll"); // Mapper como cadena
            } catch (Exception e) {
                System.err.println("Error al recuperar conductores asignados: " + e.getMessage()); // Mensaje como cadena
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // ÚNICA CONSTANTE MANTENIDA: PARA FALLO DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return lista;
    }

    // ------------------------------------
    // REGISTRAR (INSERTAR)
    // ------------------------------------
    public static Mensaje registrar(ConductorAsignado conductor) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("conductorAsignado.insert", conductor); // Mapper como cadena
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado registrado correctamente"); // Mensaje como cadena
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el conductor asignado"); // Mensaje como cadena
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar conductor asignado: " + e.getMessage()); // Mensaje como cadena
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // ÚNICA CONSTANTE MANTENIDA: PARA FALLO DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        
        return mensaje;
    }

    // ------------------------------------
    // EDITAR (ACTUALIZAR)
    // ------------------------------------
    public static Mensaje editar(ConductorAsignado conductor) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("conductorAsignado.update", conductor); // Mapper como cadena
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado editado correctamente"); // Mensaje como cadena
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el conductor asignado"); // Mensaje como cadena
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar conductor asignado: " + e.getMessage()); // Mensaje como cadena
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // ÚNICA CONSTANTE MANTENIDA: PARA FALLO DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }

    // ------------------------------------
    // ELIMINAR
    // ------------------------------------
    public static Mensaje eliminar(int idConductorAsignado) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("conductorAsignado.delete", idConductorAsignado); // Mapper como cadena
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Conductor asignado eliminado correctamente"); // Mensaje como cadena
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el conductor asignado"); // Mensaje como cadena
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar conductor asignado: " + e.getMessage()); // Mensaje como cadena
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // ÚNICA CONSTANTE MANTENIDA: PARA FALLO DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }
}