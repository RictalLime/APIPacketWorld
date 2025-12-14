package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Ciudad;

public class CiudadImp {
    
    // OBTENER CIUDADES POR ID ESTADO
    public static List<Ciudad> obtenerCiudadesPorIdEstado(int idEstado) {
        List<Ciudad> listaCiudades = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaCiudades = conexionBD.selectList("ciudad.obtenerPorIdEstado", idEstado);
            } catch (Exception e) {
                System.err.println("Error al recuperar ciudades por ID de Estado: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return listaCiudades;
    }
    
    // OBTENER CIUDAD POR ID CIUDAD
    public static Ciudad obtenerCiudadPorId(int idCiudad) {
        Ciudad ciudad = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                ciudad = conexionBD.selectOne("ciudad.obtenerPorId", idCiudad);
            } catch (Exception e) {
                System.err.println("Error al recuperar la ciudad por ID: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return ciudad;
    }
}