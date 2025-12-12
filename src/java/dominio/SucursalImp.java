/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dto.Mensaje;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Sucursal;

/**
 *
 * @author Tron7
 */
public class SucursalImp {
    
    public static List<Sucursal> obtenerSucursal() {
        List<Sucursal> sucursales = java.util.Collections.emptyList();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("sucursal.obtenerSucursales");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursales;
    }

    
    public static Mensaje registrarSucursal(Sucursal sucursal){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();
                if(filasAfectadas == 1){
                    mensaje.setError(false);
                    mensaje.setMensaje("Registro de la sucursal"+sucursal.getNombre()+"Agregado correctamente");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("Lo sentimos la informacion no pudo ser guardada.");
                    conexionBD.close();
                }
            }catch (Exception e){
                conexionBD.rollback();
                mensaje.setError(true);
                mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
            }
            conexionBD.close();
        }else{
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        return mensaje;
    }
    
    public static Mensaje editarSucursal(Sucursal sucursal){
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.update("sucursal.editar", sucursal);
                conexionBD.commit();
                if (filasAfectadas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Informacion de la sucursal "+sucursal.getNombre()+", actualizada correctamente");
                }else{
                    mensaje.setMensaje("Lo sentimos la informacion no pudo ser actualizada");
                    conexionBD.close();
                }
            }catch (Exception e) {
                conexionBD.rollback();
                mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
            }
            conexionBD.close();
        }else{
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        return mensaje;
    }
    
    public static Sucursal obtenerSucursalPorId(int idSucursal){
        Sucursal sucursal = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try{
                sucursal = conexionBD.selectOne("sucursal.obtenerSucursalPorId", idSucursal);
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursal;
    }
    
    public static Mensaje actualizarEstatus(int idSucursal){
        Mensaje mensaje = new Mensaje();
        Sucursal sucursalActual = obtenerSucursalPorId(idSucursal);
        if (sucursalActual == null) {
            mensaje.setError(true);
            mensaje.setMensaje("Sucursal con ID " + idSucursal + " no encontrada.");
            return mensaje;
        }

        String estatusActual = sucursalActual.getEstatus();
        String nuevoEstatus = estatusActual.equalsIgnoreCase("activa") 
                              ? "inactiva" 
                              : "activa";

        Sucursal sucursalParaActualizar = new Sucursal();
        sucursalParaActualizar.setIdSucursal(idSucursal);
        sucursalParaActualizar.setEstatus(nuevoEstatus);

        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.update("sucursal.actualizarEstatus", sucursalParaActualizar);
                conexionBD.commit();

                if (filasAfectadas == 1){
                    mensaje.setError(false);
                    mensaje.setMensaje("Estatus cambiado a " + nuevoEstatus + " correctamente.");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo actualizar el estatus de la sucursal.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                mensaje.setError(true);
                mensaje.setMensaje("Error en la base de datos durante la actualización.");
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos.");
        }
        return mensaje;
    }
}
