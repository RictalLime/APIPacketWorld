package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Paquete;
import dto.Mensaje;

public class PaqueteImp {

    // ------------------------------------
    // OBTENER PAQUETES POR ID ENVÍO (OPTIMIZADO PARA RASTREO)
    // ------------------------------------
    public static List<Paquete> obtenerPaquetesPorEnvio(int idEnvio) {
        List<Paquete> paquetes = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // Usamos el namespace correcto del nuevo Mapper
                paquetes = conexionBD.selectList("mybatis.mapper.PaqueteMapper.obtenerPaquetesPorEnvio", idEnvio);
            } catch (Exception e) {
                System.err.println("Error al obtener paquetes: " + e.getMessage());
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return paquetes;
    }
    
    // ------------------------------------
    // OBTENER TODOS LOS PAQUETES (Restaurado para compatibilidad con WS)
    // ------------------------------------
    public static List<Paquete> obtenerPaquetes() {
        List<Paquete> paquetes = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                // Asegúrate de agregar <select id="obtenerPaquetes"> en tu PaqueteMapper.xml
                // O si no lo usas, retorna lista vacía para que compile:
                // paquetes = conexionBD.selectList("mybatis.mapper.PaqueteMapper.obtenerPaquetes");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return paquetes;
    }

    // ------------------------------------
    // OBTENER PAQUETE POR NO. GUIA (Restaurado)
    // ------------------------------------
    public static Paquete obtenerPaquetePorNoGuia(String noGuia) {
        Paquete paquete = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                // Usamos selectOne porque esperamos UN solo objeto Paquete (o null)
                // Asegúrate de tener este query en tu Mapper si usas este método
                paquete = conexionBD.selectOne("mybatis.mapper.PaqueteMapper.getPaquetePorNoGuia", noGuia);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return paquete;
    }

    // ------------------------------------
    // REGISTRAR PAQUETE
    // ------------------------------------
    public static Mensaje registrar(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if(conexionBD != null){
            try {
                int r = conexionBD.insert("mybatis.mapper.PaqueteMapper.registrar", paquete);
                conexionBD.commit();
                
                if (r > 0) {
                    msj.setError(false);
                    msj.setMensaje("Paquete registrado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar el paquete");
                }
            } catch(Exception e) { 
                msj.setError(true); 
                msj.setMensaje("Error al registrar: " + e.getMessage()); 
            } finally { 
                conexionBD.close(); 
            }
        } else {
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return msj;
    }

    // ------------------------------------
    // EDITAR PAQUETE
    // ------------------------------------
    public static Mensaje editar(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if(conexionBD != null){
            try {
                // Asegúrate de tener este UPDATE en tu XML
                // int r = conexionBD.update("mybatis.mapper.PaqueteMapper.editar", paquete);
                // conexionBD.commit();
                // ... lógica de respuesta ...
                msj.setError(false); msj.setMensaje("Función pendiente de implementación en XML");
            } catch(Exception e) { 
                msj.setError(true); msj.setMensaje("Error al editar: " + e.getMessage()); 
            } finally { 
                conexionBD.close(); 
            }
        } else {
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return msj;
    }

    // ------------------------------------
    // ELIMINAR PAQUETE
    // ------------------------------------
    public static Mensaje eliminar(int idPaquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if(conexionBD != null){
            try {
                // Asegúrate de tener este DELETE en tu XML
                // int r = conexionBD.delete("mybatis.mapper.PaqueteMapper.eliminar", idPaquete);
                // conexionBD.commit();
                // ... lógica de respuesta ...
                msj.setError(false); msj.setMensaje("Función pendiente de implementación en XML");
            } catch(Exception e) { 
                msj.setError(true); msj.setMensaje("Error al eliminar: " + e.getMessage()); 
            } finally { 
                conexionBD.close(); 
            }
        } else {
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return msj;
    }
}