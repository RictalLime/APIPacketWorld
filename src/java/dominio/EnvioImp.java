package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import pojo.HistorialDeEnvio;
import pojo.Paquete; // Importante
import dto.Mensaje;
import java.sql.Connection;

public class EnvioImp {
    
    // ------------------------------------
    // 1. RASTREO WEB (Con Historial y Paquetes)
    // ------------------------------------
    public static List<Envio> getObtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> envios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        System.out.println("========== CONSULTA RASTREO ==========");
        
        if (conexionBD != null) {
            try {
                // 1. Obtener Datos Generales del Envío
                // Asegúrate que el ID en EnvioMapper.xml sea 'getObtenerEnviosPorNoGuia'
                envios = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosPorNoGuia", noGuia);
                
                if (envios != null && !envios.isEmpty()) {
                    Envio envioEncontrado = envios.get(0);
                    System.out.println("[EXITO] Envío ID: " + envioEncontrado.getIdEnvio());
                    
                    // 2. Obtener e Inyectar Historial
                    List<HistorialDeEnvio> historial = HistorialDeEnvioImp.obtenerHistorial(noGuia);
                    envioEncontrado.setHistorial(historial);
                    
                    // 3. Obtener e Inyectar Paquetes (Contenido)
                    // Usamos el ID del envío recuperado para buscar sus paquetes
                    if (envioEncontrado.getIdEnvio() != null && envioEncontrado.getIdEnvio() > 0) {
                        List<Paquete> paquetes = PaqueteImp.obtenerPaquetesPorEnvio(envioEncontrado.getIdEnvio());
                        envioEncontrado.setPaquetes(paquetes);
                        System.out.println("[INFO] Paquetes encontrados: " + paquetes.size());
                    } else {
                        envioEncontrado.setPaquetes(new ArrayList<>());
                    }
                } else {
                    System.out.println("[FALLO] No se encontró la guía en la BD.");
                }

            } catch (Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envios;
    }

    // --- MÉTODOS AUXILIARES (Para escritorio/móvil - Se mantienen para compatibilidad) ---
    
    public static List<Envio> obtenerEnvios() {
        List<Envio> lista = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null) { 
            try { lista = c.selectList("mybatis.mapper.EnvioMapper.getObtenerEnvios"); } 
            catch (Exception e) { e.printStackTrace(); } 
            finally { c.close(); } 
        }
        return lista;
    }

    public static List<EstadoDeEnvio> obtenerEstadosDeEnvios() {
        List<EstadoDeEnvio> lista = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null) { 
            try { lista = c.selectList("mybatis.mapper.EstadoEnvioMapper.obtenerTodos"); } 
            catch (Exception e) { e.printStackTrace(); } 
            finally { c.close(); } 
        }
        return lista;
    }
    
    public static List<Envio> obtenerEnviosConductor(int id) {
        List<Envio> lista = new ArrayList<>();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c != null) { 
            try { lista = c.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosConductor", id); } 
            catch (Exception e) { e.printStackTrace(); } 
            finally { c.close(); } 
        }
        return lista;
    }
    
    public static Mensaje registrarEnvio(Envio e) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c!=null){
             try{ 
                 int r = c.insert("mybatis.mapper.EnvioMapper.registrar", e);
                 c.commit();
                 m.setError(r<=0); m.setMensaje(r>0?"OK":"Error");
             } catch(Exception ex){ m.setError(true); m.setMensaje(ex.getMessage()); }
             finally{ c.close(); }
        }
        return m;
    }
    
    public static Mensaje editarEnvio(Envio e) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c!=null){
             try{ 
                 int r = c.update("mybatis.mapper.EnvioMapper.editar", e);
                 c.commit();
                 m.setError(r<=0); m.setMensaje(r>0?"OK":"Error");
             } catch(Exception ex){ m.setError(true); m.setMensaje(ex.getMessage()); }
             finally{ c.close(); }
        }
        return m;
    }
    
    public static Mensaje eliminarEnvio(int id) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c!=null){
             try{ 
                 int r = c.delete("mybatis.mapper.EnvioMapper.eliminar", id);
                 c.commit();
                 m.setError(r<=0); m.setMensaje(r>0?"OK":"Error");
             } catch(Exception ex){ m.setError(true); m.setMensaje(ex.getMessage()); }
             finally{ c.close(); }
        }
        return m;
    }
    
    public static Mensaje actualizarEstadoEnvio(Envio e) { 
        Mensaje m = new Mensaje();
        SqlSession c = MyBatisUtil.obtenerConexion();
        if(c!=null){
             try{ 
                 int r = c.update("mybatis.mapper.EnvioMapper.actualizarEstado", e);
                 c.commit();
                 m.setError(r<=0); m.setMensaje(r>0?"OK":"Error");
             } catch(Exception ex){ m.setError(true); m.setMensaje(ex.getMessage()); }
             finally{ c.close(); }
        }
        return m;
    }
}