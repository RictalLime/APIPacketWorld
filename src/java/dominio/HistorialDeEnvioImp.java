package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialDeEnvio;
import dto.Mensaje;

/**
 *
 * @author Angel2
 */

public class HistorialDeEnvioImp {

    // Método para registrar un cambio de estatus en la BD
    public static Mensaje registrarCambioEstatus(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.getSession();

        if (conexion != null) {
            try {
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
            mensaje.setMensaje("Error de conexión con la base de datos.");
        }

        return mensaje;
    }

    // Método para obtener la lista de historial de un envío específico
    public static List<HistorialDeEnvio> obtenerHistorial(int noGuia) {
        List<HistorialDeEnvio> historial = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.getSession();

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
}