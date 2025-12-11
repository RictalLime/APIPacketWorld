package dominio;

import Utilidades.Constantes;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import dto.Mensaje;


public class EnvioImp {
    
    // ------------------------------------
    // OBTENER TODOS LOS ENVÍOS
    // ------------------------------------
    public static List<Envio> obtenerEnvios() {
        List<Envio> listaEnvios = new ArrayList<>();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // Consulta actualizada en el XML para usar JOINs
                listaEnvios = conexionBD.selectList("envio.getObtenerEnvios");
            } catch (Exception e) {
                System.err.println("Error al recuperar los envíos: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaEnvios;
    }

    // ------------------------------------
    // OBTENER ENVÍOS POR NÚMERO DE GUÍA
    // ------------------------------------
    public static List<Envio> obtenerEnviosPorNoGuia(String noGuia) {
        List<Envio> listaEnvios = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEnvios = conexionBD.selectList("envio.getObtenerEnviosPorNoGuia", noGuia);
            } catch (Exception e) {
                System.err.println("Error al recuperar los envíos: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return listaEnvios;
    }

    // ------------------------------------
    // REGISTRAR ENVÍO (INSERTAR)
    // ------------------------------------
    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // La sentencia INSERT en el XML fue modificada para usar ID de ciudad
                int resultado = conexionBD.insert("envio.registrar", envio);
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
                if (conexionBD != null) {
                    conexionBD.close();
                }
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
                // La sentencia UPDATE en el XML fue modificada para usar ID de ciudad
                int resultado = conexionBD.update("envio.editar", envio);
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
                if (conexionBD != null) {
                    conexionBD.close();
                }
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
                int resultado = conexionBD.delete("envio.eliminar", idEnvio);
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
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return mensaje;
    }

    // ------------------------------------
    // OBTENER ESTADOS DE ENVÍOS
    // ------------------------------------
    public static List<EstadoDeEnvio> obtenerEstadosDeEnvios() {
        List<EstadoDeEnvio> listaEstados = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaEstados = conexionBD.selectList("envio.getObtenerEstadosDeEnvio");
            } catch (Exception e) {
                System.err.println("Error al recuperar los estados de envíos: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return listaEstados;
    }

    // ------------------------------------
    // OBTENER ENVÍOS POR CONDUCTOR
    // ------------------------------------
    public static List<Envio> obtenerEnviosConductor(int idColaborador) {
        List<Envio> listaEnvios = new ArrayList<>();
        SqlSession conexionDB = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionDB != null) {
            try {
                listaEnvios = conexionDB.selectList("envio.getObtenerEnviosConductor", idColaborador);
            } catch (Exception e) {
                System.err.println("Error al recuperar los envíos: " + e.getMessage());

            } finally {
                if (conexionDB != null) {
                    conexionDB.close();
                }
            }
        }
        return listaEnvios;
    }
}