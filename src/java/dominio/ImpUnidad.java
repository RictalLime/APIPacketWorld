package dominio;

import Utilidades.Constantes; // Importando la constante de error de BD
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EstadoUnidad;
import dto.Mensaje;
import pojo.TipoUnidad;
import pojo.Unidad;


public class ImpUnidad {
    
    // ------------------------------------
    // OBTENER TODAS LAS UNIDADES
    // ------------------------------------
    public static List<Unidad> obtenerUnidades(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidades");
            }catch(Exception e){
                System.err.println("Error al obtener unidades: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    // ------------------------------------
    // OBTENER UNIDADES DISPONIBLES
    // ------------------------------------
    public static List<Unidad> obtenerUnidadesDisponibles(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List <Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesDisponibles");
            }catch(Exception e){
                System.err.println("Error al obtener unidades disponibles: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    // ------------------------------------
    // OBTENER UNIDADES POR VIN
    // ------------------------------------
    public static List<Unidad> obtenerUnidadPorVin(String vin) {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if (conexionDB != null) {
            try {
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesPorVin", vin);
            } catch (Exception e) {
                 System.err.println("Error al obtener unidad por VIN: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }

    // ------------------------------------
    // OBTENER UNIDADES POR MARCA
    // ------------------------------------
    public static List<Unidad> obtenerUnidadesPorMarca(String marca) {
        List<Unidad> unidad = null;
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        if (conexionDB != null) {
            try {
                HashMap<String,String> parametros = new LinkedHashMap<>();
                parametros.put("marca", marca);
                unidad = conexionDB.selectList("unidad.obtenerUnidadesPorMarca",parametros);
                
            } catch (Exception e) {
                System.err.println("Error al obtener unidad por marca: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return unidad;
    }

    // ------------------------------------
    // OBTENER UNIDADES POR NII
    // ------------------------------------
    public static List<Unidad> obtenerUnidadesPorNii(String nii) {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if (conexionDB != null) {
            try {
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesPorNii", nii);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    // ------------------------------------
    // AGREGAR UNIDAD (INSERTAR)
    // ------------------------------------
    public static Mensaje agregarUnidad(Unidad unidad) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        if(conexionDB!=null){
            try{
                int filasAFectas = conexionDB.insert("unidad.agregarUnidad", unidad);
                conexionDB.commit();
                if(filasAFectas>0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Unidad agregada correctamente");
                } else{
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo agregar la unidad");
                }
            }catch(Exception e){
                mensaje.setError(true);
                mensaje.setMensaje("Error al agregar unidad: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        }else{
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }
    
    // ------------------------------------
    // EDITAR UNIDAD
    // ------------------------------------
    public static Mensaje editarUnidad(Unidad unidad){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        
        if(conexionDB!=null){
            try{
                int filasAfectadas = conexionDB.update("unidad.editarUnidad", unidad);
                conexionDB.commit();
                if(filasAfectadas>0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Unidad editada correctamente");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar la unidad");
                }   
            }catch(Exception e){
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar unidad: " + e.getMessage());
            }finally{
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        }else{
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }
    
    // ------------------------------------
    // ELIMINAR UNIDAD
    // ------------------------------------
    public static Mensaje eliminarUnidad(Integer idUnidad){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        
        if(conexionDB!=null){
            try{
                int filasAfectadas = conexionDB.delete("unidad.eliminarUnidad", idUnidad);
                conexionDB.commit();
                if(filasAfectadas>0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Unidad eliminada satidfactoriamente");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar la unidad");
                }
            }catch(Exception e){
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar unidad: " + e.getMessage());
            }finally{
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        }else{
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        
        return mensaje;
    }
    
    // ------------------------------------
    // OBTENER TIPOS DE UNIDAD
    // ------------------------------------
    public static List<TipoUnidad> obtenerTiposDeUnidad(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<TipoUnidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerTiposDeUnidad");
            }catch(Exception e){
                System.err.println("Error al obtener tipos de unidad: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }

    // ------------------------------------
    // OBTENER ESTADOS DE UNIDAD
    // ------------------------------------
    public static List<EstadoUnidad> obtenerEstadosDeUnidad() {
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<EstadoUnidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerEstadosDeUnidad");
            }catch(Exception e){
                System.err.println("Error al obtener estados de unidad: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    // ------------------------------------
    // OBTENER UNIDADES SIN ASIGNAR
    // ------------------------------------
    public static List<Unidad> obtenerUnidadesSinAsignar(){
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        List<Unidad> respuesta = null;
        if(conexionDB!=null){
            try{
                respuesta = conexionDB.selectList("unidad.obtenerUnidadesSinAsignar");
            }catch(Exception e){
                System.err.println("Error al obtener unidades sin asignar: " + e.getMessage());
            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PARA ERROR DE CONEXIÓN
            System.err.println(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}