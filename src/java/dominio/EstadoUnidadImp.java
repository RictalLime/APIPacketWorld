package dominio;

/**
 *
 * @author Angel2
 */

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EstadoUnidad;

public class EstadoUnidadImp {

    public static List<EstadoUnidad> obtenerEstadosUnidad() {
        List<EstadoUnidad> lista = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.obtenerConexion();

        if (conexion != null) {
            try {
                lista = conexion.selectList("mybatis.mapper.EstadoUnidadMapper.obtenerTodos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexion.close();
            }
        }
        return lista;
    }
}