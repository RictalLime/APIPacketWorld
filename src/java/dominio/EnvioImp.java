package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import pojo.HistorialDeEnvio;
import pojo.Paquete;
import dto.Mensaje;

public class EnvioImp {
    
    // ------------------------------------
    // 1. RASTREO WEB (Con Historial y Paquetes)
    // ------------------------------------
    public static List<Envio> getObtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> envios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // 1. Obtener Datos Generales del Envío (La cabecera)
                envios = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosPorNoGuia", noGuia);
                
                // Si encontramos el envío, llenamos sus detalles
                if (envios != null && !envios.isEmpty()) {
                    Envio envioEncontrado = envios.get(0);
                    
                    // --- INYECCIÓN DE DATOS ANIDADOS ---
                    try {
                        // 2. Obtener y setear PAQUETES usando el ID del envío
                        List<Paquete> listaPaquetes = PaqueteImp.obtenerPaquetesPorEnvio(envioEncontrado.getIdEnvio());
                        envioEncontrado.setPaquetes(listaPaquetes);
                        
                        // 3. Obtener y setear HISTORIAL usando el No. de Guía
                        List<HistorialDeEnvio> listaHistorial = HistorialDeEnvioImp.obtenerHistorial(envioEncontrado.getNoGuia());
                        envioEncontrado.setHistorial(listaHistorial);
                        
                    } catch (Exception ex) {
                        System.err.println("Error al cargar detalles anidados (paquetes/historial): " + ex.getMessage());
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envios;
    }

    // ------------------------------------
    // 2. MÉTODOS CRUD (Necesarios para EnvioWS)
    // ------------------------------------

    public static Mensaje registrar(Envio envio) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                // 1. Registrar el Envío
                int filasAfectadas = conexionBD.insert("mybatis.mapper.EnvioMapper.registrar", envio);
                
                if (filasAfectadas > 0) {
                    // 2. CAMBIO IMPORTANTE: Registrar automáticamente el primer historial
                    // Se asume que el envío llega con un idEstadoDeEnvio inicial (ej. Pendiente)
                    HistorialDeEnvio historialInicial = new HistorialDeEnvio();
                    historialInicial.setNoGuia(envio.getNoGuia());
                    historialInicial.setIdEstadoDeEnvio(envio.getIdEstadoDeEnvio());
                    historialInicial.setIdColaborador(envio.getIdColaborador());
                    historialInicial.setMotivo("Registro inicial del envío en sistema");
                    
                    // Insertamos el historial dentro de la misma transacción
                    conexionBD.insert("mybatis.mapper.HistorialDeEnvioMapper.registrarCambioEstatus", historialInicial);
                    
                    conexionBD.commit();
                    msj.setError(false);
                    msj.setMensaje("Envío registrado con éxito. Guía: " + envio.getNoGuia());
                } else {
                    conexionBD.rollback();
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar el envío.");
                }
            } catch (Exception e) {
                if(conexionBD != null) conexionBD.rollback();
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

    public static Mensaje editar(Envio e) {
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
             try{ 
                 int r = c.update("mybatis.mapper.EnvioMapper.editar", e);
                 c.commit();
                 if (r > 0) {
                    m.setError(false);
                    m.setMensaje("Envío actualizado correctamente.");
                 } else {
                    m.setError(true);
                    m.setMensaje("No se pudo actualizar el envío.");
                 }
             } catch(Exception ex){ 
                 m.setError(true); 
                 m.setMensaje("Error al editar: " + ex.getMessage()); 
             } finally{ 
                 c.close(); 
             }
        } else {
            m.setError(true);
            m.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return m;
    }
    
    public static Mensaje eliminarEnvio(int id) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
             try{ 
                 int r = c.delete("mybatis.mapper.EnvioMapper.eliminar", id);
                 c.commit();
                 if (r > 0) {
                    m.setError(false);
                    m.setMensaje("Envío eliminado correctamente.");
                 } else {
                    m.setError(true);
                    m.setMensaje("No se pudo eliminar el envío.");
                 }
             } catch(Exception ex){ 
                 m.setError(true); 
                 m.setMensaje("Error al eliminar: " + ex.getMessage()); 
             } finally{ 
                 c.close(); 
             }
        } else {
            m.setError(true);
            m.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return m;
    }
    
    public static Mensaje actualizarEstadoEnvio(Envio e) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
             try{ 
                 // 1. Actualizar el estatus en la tabla envio
                 int r = c.update("mybatis.mapper.EnvioMapper.actualizarEstado", e);
                 
                 if (r > 0) {
                    // 2. Registrar en el historial el cambio
                    // Nota: 'e' debería traer el motivo si viene del cliente, si no, ponemos uno genérico
                    HistorialDeEnvio historial = new HistorialDeEnvio();
                    historial.setNoGuia(e.getNoGuia());
                    historial.setIdEstadoDeEnvio(e.getIdEstadoDeEnvio());
                    historial.setIdColaborador(e.getIdColaborador()); // Importante: Quién hizo el cambio
                    historial.setMotivo("Actualización de estatus"); 
                    
                    c.insert("mybatis.mapper.HistorialDeEnvioMapper.registrarCambioEstatus", historial);
                     
                    c.commit();
                    m.setError(false);
                    m.setMensaje("Estatus actualizado correctamente.");
                 } else {
                    c.rollback();
                    m.setError(true);
                    m.setMensaje("No se pudo actualizar el estatus.");
                 }
             } catch(Exception ex){ 
                 if(c != null) c.rollback();
                 m.setError(true); 
                 m.setMensaje("Error al actualizar estatus: " + ex.getMessage()); 
             } finally{ 
                 c.close(); 
             }
        } else {
            m.setError(true);
            m.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return m;
    }
    
    public static List<Envio> obtenerTodos() {
        List<Envio> list = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
            try{
                list = c.selectList("mybatis.mapper.EnvioMapper.obtenerTodos");
            }catch(Exception e){ 
                e.printStackTrace(); 
            } finally{ 
                c.close(); 
            }
        }
        return list;
    }

    public static List<EstadoDeEnvio> obtenerEstadosDeEnvios() {
        List<EstadoDeEnvio> list = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
            try{
                list = c.selectList("mybatis.mapper.EnvioMapper.obtenerEstadosDeEnvios");
            }catch(Exception e){ 
                e.printStackTrace(); 
            } finally{ 
                c.close(); 
            }
        }
        return list;
    }

    public static List<Envio> obtenerEnviosConductor(int idColaborador) {
        List<Envio> list = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null){
            try{
                list = c.selectList("mybatis.mapper.EnvioMapper.obtenerEnviosConductor", idColaborador);
            }catch(Exception e){ 
                e.printStackTrace(); 
            } finally{ 
                c.close(); 
            }
        }
        return list;
    }
}