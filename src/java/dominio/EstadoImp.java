package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Estado;

public class EstadoImp {
    
    // OBTENER TODOS LOS ESTADOS
    public static List<Estado> obtenerTodosLosEstados() {
        List<Estado> listaEstados = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEstados = conexionBD.selectList("estado.obtenerTodos");
            } catch (Exception e) {
                System.err.println("Error al recuperar los estados: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return listaEstados;
    }
    
    // OBTENER ESTADO POR ID
    public static Estado obtenerEstadoPorId(int idEstado) {
        Estado estado = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                estado = conexionBD.selectOne("estado.obtenerPorId", idEstado);
            } catch (Exception e) {
                System.err.println("Error al recuperar el estado por ID: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return estado;
    }
}