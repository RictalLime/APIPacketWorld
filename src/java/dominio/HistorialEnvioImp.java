/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialDeEnvio;
import pojo.Envio;
import pojo.Paquete;
import pojo.RespuestaRastreo;
import dto.Mensaje;
import mybatis.MyBatisUtil;

public class HistorialEnvioImp {
    
    // --- NUEVO MÉTODO PRINCIPAL ---
    // Este método orquesta la consulta completa para el Frontend
    public static RespuestaRastreo obtenerRastreoCompleto(String noGuia) {
        RespuestaRastreo respuesta = new RespuestaRastreo();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                // 1. Consultar Datos Generales del Envío (Origen, Destino, Cliente, Estatus)
                // Usamos el ID del mapper que subiste: mybatis.mapper.EnvioMapper
                Envio envioInfo = conexionBD.selectOne("mybatis.mapper.EnvioMapper.getObtenerEnviosPorNoGuia", noGuia);

                if (envioInfo != null) {
                    // Llenamos la cabecera de la respuesta
                    respuesta.setGuideNumber(envioInfo.getNoGuia());
                    
                    // Asumiendo que tu POJO Envio tiene estos campos mapeados por los alias de tu XML
                    respuesta.setClient(envioInfo.getCliente()); 
                    respuesta.setOrigin(envioInfo.getOrigen());
                    respuesta.setDestination(envioInfo.getDestino());
                    respuesta.setStatus(envioInfo.getEstadoDeEnvio()); // Texto: "En Tránsito"
                    
                    // Lógica rápida para asignar color (statusCode) basado en el texto
                    String estadoLower = (envioInfo.getEstadoDeEnvio() != null) ? envioInfo.getEstadoDeEnvio().toLowerCase() : "";
                    if (estadoLower.contains("entregado")) respuesta.setStatusCode("delivered");
                    else if (estadoLower.contains("tránsito") || estadoLower.contains("camino")) respuesta.setStatusCode("transit");
                    else if (estadoLower.contains("creado") || estadoLower.contains("pendiente")) respuesta.setStatusCode("pending");
                    else respuesta.setStatusCode("unknown");

                    respuesta.setServiceType("Estándar"); // Valor por defecto o tomar de envioInfo.getCostoDeEnvio() si aplica

                    // 2. Consultar Paquetes
                    // Usamos el ID del mapper que subiste: paquete
                    List<Paquete> listaPaquetes = conexionBD.selectList("paquete.getPaquetePorNoGuia", noGuia);
                    List<RespuestaRastreo.PaqueteResumen> paquetesResumen = new ArrayList<>();
                    
                    if(listaPaquetes != null) {
                        for(Paquete p : listaPaquetes) {
                            RespuestaRastreo.PaqueteResumen pr = new RespuestaRastreo.PaqueteResumen();
                            pr.setId(String.valueOf(p.getIdPaquete()));
                            pr.setDescription(p.getDescripcion());
                            // Tu XML ya concatena dimensiones: CONCAT(p.alto, 'X', p.ancho...) AS dimensiones
                            pr.setDimensions(p.getDimensiones()); 
                            pr.setWeight(p.getPeso() + " kg");
                            paquetesResumen.add(pr);
                        }
                    }
                    respuesta.setPackages(paquetesResumen);

                    // 3. Consultar Historial (Timeline)
                    // Usamos el ID del mapper que subiste: historialDeEnvio
                    List<HistorialDeEnvio> historial = conexionBD.selectList("historialDeEnvio.getHistorialDeEnvioPorNoGuia", noGuia);
                    respuesta.setHistory(historial);

                } else {
                    // Si no existe el envío, retornamos null para manejar el 404 en el WS
                    return null; 
                }

            } catch (Exception e) {
                System.err.println("Error al recuperar rastreo completo: " + e.getMessage());
                e.printStackTrace();
                return null;
            } finally {
                conexionBD.close();
            }
        } else {
            System.err.println("Por el momento no se puede consultar la información");
            return null;
        }

        return respuesta;
    }

    // --- MÉTODOS EXISTENTES (Se mantienen igual por compatibilidad) ---

    public static List<HistorialDeEnvio> obtenerTodos() {
        List<HistorialDeEnvio> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("historialDeEnvio.getObtenerHistorialDeEnvio");
            } catch (Exception e) {
                System.err.println("Error al recuperar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return lista;
    }
    
    public static List<HistorialDeEnvio> obtenerHistorialPorNoGuia(String noGuia) {
        List<HistorialDeEnvio> lista = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("historialDeEnvio.getHistorialDeEnvioPorNoGuia", noGuia);
            } catch (Exception e) {
                System.err.println("Error al obtener historial de envío por noGuia: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return lista;
    }

    public static Mensaje registrar(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("historialDeEnvio.insert", historial);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío registrado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al registrar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo establecer conexión con la base de datos");
        }
        return mensaje;
    }

    public static Mensaje editar(HistorialDeEnvio historial) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("historialDeEnvio.update", historial);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío editado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al editar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return mensaje;
    }

    public static Mensaje eliminar(int idHistorialDeEnvio) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("historialDeEnvio.delete", idHistorialDeEnvio);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Historial de envío eliminado correctamente");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el historial de envío");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar historial de envío: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return mensaje;
    }
}