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

public class EnvioImp {
    
    // ------------------------------------
    // OBTENER ENVÍOS POR NÚMERO DE GUÍA (RASTREO WEB)
    // ------------------------------------
    public static List<Envio> getObtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> envios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        // --- DIAGNÓSTICO: Esto saldrá en la consola de NetBeans ---
        System.out.println("==================================================");
        System.out.println("[DEBUG] Buscando guía: '" + noGuia + "'");
        
        if (conexionBD != null) {
            try {
                // 1. Ejecutar consulta
                envios = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosPorNoGuia", noGuia);
                
                System.out.println("[DEBUG] Resultados encontrados en BD: " + (envios != null ? envios.size() : "null"));

                // 2. Cargar historial
                if (envios != null && !envios.isEmpty()) {
                    for (Envio envio : envios) {
                        System.out.println("[DEBUG] Envio encontrado ID: " + envio.getIdEnvio());
                        List<HistorialDeEnvio> historial = HistorialDeEnvioImp.obtenerHistorial(noGuia);
                        envio.setHistorial(historial);
                        envio.setPaquetes(new ArrayList<>()); // Inicializamos paquetes vacío para evitar nulls en el front
                    }
                } else {
                    System.out.println("[DEBUG] La lista regresó vacía. Verifica que el No. Guía sea IDÉNTICO en la BD.");
                }

            } catch (Exception e) {
                System.err.println("[ERROR CRÍTICO] Excepción en MyBatis: ");
                e.printStackTrace(); // Esto imprimirá el error real si el Mapper está mal
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("[ERROR] No se pudo conectar a la Base de Datos. Revisa MyBatisConfig.xml");
        }
        System.out.println("==================================================");
        return envios;
    }

    // ------------------------------------
    // OBTENER TODOS LOS ENVÍOS
    // ------------------------------------
    public static List<Envio> obtenerEnvios() {
        List<Envio> listaEnvios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEnvios = conexionBD.selectList("mybatis.mapper.EnvioMapper.obtenerEnvios");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return listaEnvios;
    }

    // ------------------------------------
    // REGISTRAR ENVÍO
    // ------------------------------------
    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("mybatis.mapper.EnvioMapper.registrar", envio);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }

    // ------------------------------------
    // EDITAR ENVÍO
    // ------------------------------------
    public static Mensaje editarEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("mybatis.mapper.EnvioMapper.editar", envio);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }

    // ------------------------------------
    // ELIMINAR ENVÍO
    // ------------------------------------
    public static Mensaje eliminarEnvio(int idEnvio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("mybatis.mapper.EnvioMapper.eliminar", idEnvio);
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Envío eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return mensaje;
    }

    // ------------------------------------
    // OBTENER ESTADOS DE ENVÍOS
    // ------------------------------------
    public static List<EstadoDeEnvio> obtenerEstadosDeEnvios() {
        List<EstadoDeEnvio> listaEstados = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEstados = conexionBD.selectList("mybatis.mapper.EstadoEnvioMapper.obtenerTodos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return listaEstados;
    }

    // ------------------------------------
    // OBTENER ENVÍOS POR CONDUCTOR
    // ------------------------------------
    public static List<Envio> obtenerEnviosConductor(int idColaborador) {
        List<Envio> listaEnvios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // NOTA: Verifica que en EnvioMapper.xml tengas este ID
                listaEnvios = conexionBD.selectList("mybatis.mapper.EnvioMapper.getObtenerEnviosConductor", idColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return listaEnvios;
    }

    // ------------------------------------
    // ACTUALIZAR ÚNICAMENTE EL ESTADO
    // ------------------------------------
    public static Mensaje actualizarEstadoEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("mybatis.mapper.EnvioMapper.actualizarEstado", envio); 
                conexionBD.commit();

                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Estado de Envío actualizado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo actualizar el estado (ID de envío no encontrado)");
                }
            } catch (Exception e) {
                if(conexionBD != null) conexionBD.rollback();
                mensaje.setError(true);
                mensaje.setMensaje("Error al actualizar estado: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }
}