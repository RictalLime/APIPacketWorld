package dominio;

import Utilidades.Constantes; // Importando la constante de error de BD
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import dto.Mensaje;
import pojo.Paquete;

public class PaqueteImp {
    
    // ------------------------------------
    // OBTENER PAQUETES POR ID ENVÍO
    // ------------------------------------
    public static List<Paquete> obtenerPaquetesPorEnvio(int idEnvio) {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquetes = null;
        
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.getPaquetesPorEnvio", idEnvio);
            } catch (Exception e) {
                //System.err.println("Error al obtener paquetes por envío: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        
        return paquetes;
    }
    
    // ------------------------------------
    // OBTENER TODOS LOS PAQUETES
    // ------------------------------------
    public static List<Paquete> obtenerPaquetes() {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquetes = null;
        
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.getPaquetes");
            } catch (Exception e) {
                //System.err.println("Error al obtener paquetes: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        
        return paquetes;
    }
    
    // ------------------------------------
    // OBTENER PAQUETES POR NO. GUÍA
    // ------------------------------------
    public static List<Paquete> obtenerPaquetesPorNoGuia(String noguia) {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquetes = null;
        
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.getPaquetePorNoGuia", noguia);
            } catch (Exception e) {
                //System.err.println("Error al obtener paquetes por No. Guía: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        
        return paquetes;
    }
    
    // ------------------------------------
    // REGISTRAR PAQUETE (INSERTAR)
    // ------------------------------------
    public static Mensaje registrarPaquete(Paquete paquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("paquete.registrar", paquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar paquete: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        
        return mensaje;
    }

    // ------------------------------------
    // EDITAR PAQUETE
    // ------------------------------------
    public static Mensaje editarPaquete(Paquete paquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("paquete.editar", paquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar paquete: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }

    // ------------------------------------
    // ELIMINAR PAQUETE
    // ------------------------------------
    public static Mensaje eliminarPaquete(int idPaquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("paquete.eliminar", idPaquete);
                conexionBD.commit();
                
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Paquete eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el paquete");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar paquete: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }
}