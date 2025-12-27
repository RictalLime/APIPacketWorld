package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialDeEnvio;
import dto.Mensaje;

public class HistorialDeEnvioImp {

    // ---------------------------------------------------------
    // 1. MÉTODOS PARA RASTREO Y LÓGICA DE NEGOCIO
    // ---------------------------------------------------------

    // Registra un cambio de estatus (Usado por EnvioImp y WS)
    public static Mensaje registrarCambioEstatus(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                // Namespace correcto: mybatis.mapper.HistorialDeEnvioMapper
                int filasAfectadas = conexion.insert("mybatis.mapper.HistorialDeEnvioMapper.registrarCambioEstatus", historial);
                conexion.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío registrado correctamente.");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el historial del envío.");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar el historial: " + e.getMessage());
            } finally {
                conexion.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }

    // Obtiene la lista de historial para una guía (Usado por el Rastreo)
    public static List<HistorialDeEnvio> obtenerHistorial(String noGuia) {
        List<HistorialDeEnvio> historial = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                historial = conexion.selectList("mybatis.mapper.HistorialDeEnvioMapper.obtenerHistorialPorEnvio", noGuia);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexion.close();
            }
        }
        return historial;
    }
    
    // ---------------------------------------------------------
    // 2. MÉTODOS CRUD GENÉRICOS (Para que compile el WS)
    // ---------------------------------------------------------
    
    // Este método lo usa el WS para 'agregar', reutilizamos la lógica de cambio de estatus
    public static Mensaje registrar(HistorialDeEnvio historial) {
        return registrarCambioEstatus(historial);
    }

    // Método habilitado: Ya existe la consulta en el XML
    public static List<HistorialDeEnvio> obtenerTodos() {
        List<HistorialDeEnvio> list = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
            try{
                list = c.selectList("mybatis.mapper.HistorialDeEnvioMapper.obtenerTodos");
            }catch(Exception e){ 
                e.printStackTrace(); 
            } finally{ 
                c.close(); 
            }
        }
        return list;
    }
    
    public static Mensaje editar(HistorialDeEnvio historial) {
        Mensaje m = new Mensaje();
        m.setError(true);
        m.setMensaje("Edición de historial no implementada en XML (ID: editar)");
        // Para implementar: Agrega <update id="editar"> en HistorialDeEnvioMapper.xml
        return m;
    }
    
    public static Mensaje eliminar(int id) {
        Mensaje m = new Mensaje();
        m.setError(true);
        m.setMensaje("Eliminación de historial no implementada en XML (ID: eliminar)");
        // Para implementar: Agrega <delete id="eliminar"> en HistorialDeEnvioMapper.xml
        return m;
    }
}