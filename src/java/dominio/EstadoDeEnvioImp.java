package dominio;

/**
 *
 * @author Angel2
 */

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EstadoDeEnvio;

public class EstadoDeEnvioImp {

    public static List<EstadoDeEnvio> obtenerEstadosEnvio() {
        List<EstadoDeEnvio> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                lista = conexion.selectList("mybatis.mapper.EstadoEnvioMapper.obtenerTodos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexion.close();
            }
        }
        return lista;
    }
}