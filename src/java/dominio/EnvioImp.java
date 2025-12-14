package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import pojo.HistorialDeEnvio; // Importante para el historial
import dto.Mensaje;

public class EnvioImp {
    
    // ------------------------------------
    // OBTENER ENVÍOS POR NÚMERO DE GUÍA (RASTREO WEB)
    // ------------------------------------
    public static List<Envio> obtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> envios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // 1. CORRECCIÓN DE NAMESPACE: Debe coincidir con el XML (mybatis.mapper.EnvioMapper)
                envios = conexionBD.selectList("mybatis.mapper.EnvioMapper.obtenerEnviosPorNoGuia", noGuia);
                
                // 2. CARGA DE HISTORIAL: Fundamental para que la web muestre la línea de tiempo
                for (Envio envio : envios) {
                    if (envio != null) {
                        // Llamamos al Imp de Historial que ya tienes creado
                        List<HistorialDeEnvio> historial = HistorialDeEnvioImp.obtenerHistorial(noGuia);
                        envio.setHistorial(historial);
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
                // CORRECCIÓN NAMESPACE
                int resultado = conexionBD.insert("mybatis.mapper.EnvioMapper.registrarEnvio", envio); // Asegúrate que en el XML el ID sea 'registrarEnvio' o ajusta aquí
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
                // Asumiendo que crearás un update con id 'editarEnvio' en el XML
                int resultado = conexionBD.update("mybatis.mapper.EnvioMapper.editarEnvio", envio);
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
                // Asumiendo id 'eliminarEnvio' en el XML
                int resultado = conexionBD.delete("mybatis.mapper.EnvioMapper.eliminarEnvio", idEnvio);
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
                // Nota: Esto requiere que EstadoEnvioMapper.xml tenga el namespace correcto
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
                // Asumiendo id 'obtenerEnviosConductor' en el XML
                listaEnvios = conexionBD.selectList("mybatis.mapper.EnvioMapper.obtenerEnviosConductor", idColaborador);
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
                // Asumiendo id 'actualizarEstado' en el XML
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