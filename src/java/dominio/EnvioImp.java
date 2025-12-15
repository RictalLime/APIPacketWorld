package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import pojo.HistorialDeEnvio;
import dto.Mensaje;
import java.sql.Connection;

public class EnvioImp {
    
    public static List<Envio> getObtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> envios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        System.out.println("================== DIAGNOSTICO DE ENVIO ==================");
        
        if (conexionBD != null) {
            try {
                // 1. VERIFICAR QUE VE JAVA EN LA BD
                Connection conn = conexionBD.getConnection();
                System.out.println("[INFO] Conectado a: " + conn.getMetaData().getURL());
                
                // Ejecutamos una consulta genérica para ver qué guías existen realmente
                List<Envio> todos = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnvios");
                System.out.println("[INFO] Total de envíos en la tabla: " + (todos != null ? todos.size() : "0"));
                if(todos != null){
                    System.out.print("[INFO] Guías disponibles en BD: ");
                    for(Envio e : todos){
                        System.out.print("'" + e.getNoGuia() + "' ");
                    }
                    System.out.println();
                }

                // 2. BUSQUEDA REAL
                System.out.println("[INFO] Buscando guía exacta: '" + noGuia + "'");
                envios = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosPorNoGuia", noGuia);
                
                if (envios != null && !envios.isEmpty()) {
                    System.out.println("[EXITO] ¡Envío encontrado! ID: " + envios.get(0).getIdEnvio());
                    for (Envio envio : envios) {
                        List<HistorialDeEnvio> historial = HistorialDeEnvioImp.obtenerHistorial(noGuia);
                        envio.setHistorial(historial);
                        envio.setPaquetes(new ArrayList<>()); 
                    }
                } else {
                    System.out.println("[FALLO] No se encontró coincidencia.");
                }

            } catch (Exception e) {
                System.err.println("[ERROR CRÍTICO] " + e.getMessage());
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("[ERROR] Conexión nula.");
        }
        System.out.println("==========================================================");
        return envios;
    }

    // ... Resto de métodos requeridos para que no marque error ...
    // (Copia aquí el resto de tus métodos tal cual los tenías: registrarEnvio, etc.)
    
    public static List<Envio> obtenerEnvios() {
        // Implementación dummy para cumplir contrato
        return new ArrayList<>(); 
    }
    public static Mensaje registrarEnvio(Envio e) { return new Mensaje(false, "OK"); }
    public static Mensaje editarEnvio(Envio e) { return new Mensaje(false, "OK"); }
    public static Mensaje eliminarEnvio(int id) { return new Mensaje(false, "OK"); }
    public static List<EstadoDeEnvio> obtenerEstadosDeEnvios() { return new ArrayList<>(); }
    public static List<Envio> obtenerEnviosConductor(int id) { return new ArrayList<>(); }
    public static Mensaje actualizarEstadoEnvio(Envio e) { return new Mensaje(false, "OK"); }
}