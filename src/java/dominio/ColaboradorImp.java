package dominio;

import Utilidades.Constantes; // Importando tu clase Constantes
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import dto.Mensaje;

/**
 *
 * @author Tron7
 */
public class ColaboradorImp {
    
    // ------------------------------------
    // OBTENER TODOS LOS COLABORADORES
    // ------------------------------------
    public static List<Colaborador> obtenerColaboradores() {

        List<Colaborador> listaColaboradores = new ArrayList<>();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getColaboradores");
            } catch (Exception e) {
                //System.err.println("Error al recuperar los colaboradores: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaColaboradores;
    }
    
    // ------------------------------------
    // OBTENER COLABORADORES POR NOMBRE
    // ------------------------------------
    public static List<Colaborador> obtenerColaboradoresPorNombre(String nombre) {
        List<Colaborador> listaColaboradores = new ArrayList<>();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getColaboradoresPorNombre", nombre);
            } catch (Exception e) {
                //System.err.println("Error al recuperar los colaboradores por nombre: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaColaboradores;
    }    
    
    // ------------------------------------
    // OBTENER COLABORADORES POR ROL
    // ------------------------------------
    public static List<Colaborador> obtenerColaboradoresPorRol(int idRol) {
        List<Colaborador> listaColaboradores = new ArrayList<>();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getColaboradoresPorRol", idRol);
            } catch (Exception e) {
                //System.err.println("Error al recuperar los colaboradores por rol: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaColaboradores;
    }
    
    // ------------------------------------
    // OBTENER COLABORADOR POR NÚMERO PERSONAL
    // ------------------------------------
    public static List<Colaborador> obtenerColaboradorPorNoPersonal(String noPersonal) {
        List<Colaborador> colaborador = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                //System.out.println("Consultando colaborador con noPersonal: " + noPersonal);
                colaborador = conexionBD.selectList("colaborador.getColaboradorPorNoPersonal", noPersonal);
                //System.out.println("Resultado obtenido: " + colaborador);
            } catch (Exception e) {
                //System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return colaborador;
    }
    
    // ------------------------------------
    // REGISTRAR COLABORADOR (INSERTAR)
    // ------------------------------------
    public static Mensaje registrarColaborador(Colaborador colaborador) {

        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("colaborador.registrar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Colaborador(a) registrado con exito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo registrar al colaborador(as)");
                }
            } catch (Exception e) {
               mensaje.setError(true);
               // Este mensaje de error interno no usa la constante MSJ_ERROR_BD, ya que es un error de lógica/integridad.
               mensaje.setMensaje("Revisa que la CURP, numero personal o el correo electrónico no sean los de un colaborador ya agregado");
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return mensaje;
    }

    // ------------------------------------
    // EDITAR COLABORADOR
    // ------------------------------------
    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("colaborador.editar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Colaborador(a) editado con éxito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo editar el colaborador(a)");
                }
            } catch (Exception e) {
                e.printStackTrace();  
                mensaje.setError(true);
                // Aquí podrías mejorar el mensaje para indicar un posible error de duplicidad (CURP, email, etc.)
                mensaje.setMensaje("Error al editar el colaborador");
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }

    // ------------------------------------
    // ELIMINAR COLABORADOR
    // ------------------------------------
    public static Mensaje eliminarColaborador(int idColaborador) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("colaborador.eliminar", idColaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Colaborador eliminado con éxito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No se pudo eliminar el colaborador");
                }
            } catch (Exception e) {
                e.printStackTrace();  
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar el colaborador" );
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            mensaje.setError(true);
            mensaje.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return mensaje;
    }
    
    // ------------------------------------
    // GUARDAR FOTO
    // ------------------------------------
    public static Mensaje guardarFoto(Integer idColaborador, byte[] foto){
        Mensaje msj = new Mensaje();
        
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
                
        parametros.put("idColaborador",idColaborador);
        parametros.put("fotografia", foto);
        
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if(conexion!=null){
            try {
                int filasAfectadas = conexion.update("colaborador.guardarFoto", parametros);
                conexion.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("Foto editada correctamente");
                }else{
                    msj.setError(true);
                    msj.setMensaje("NO se logro editar la foto");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                if (conexion != null) {
                    conexion.close();
                }
            }
        }else{
            // USANDO LA CONSTANTE PROPORCIONADA
            msj.setError(true);
            msj.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        
        return msj;
    }
    
    // ------------------------------------
    // OBTENER FOTO
    // ------------------------------------
    public static Colaborador obtenerFoto(Integer idColaborador){
        Colaborador colaborador = null;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if(conexion!=null){
            try {
                colaborador = conexion.selectOne("colaborador.optenerFoto", idColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conexion != null) {
                    conexion.close();
                }
            }
        }
        return colaborador;
    }
    
    // ------------------------------------
    // OBTENER CONDUCTORES
    // ------------------------------------
    public static List<Colaborador> obtenerConductores() {

        List<Colaborador> listaColaboradores = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getConductores");
            } catch (Exception e) {
                //System.err.println("Error al recuperar los colaboradores: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaColaboradores;
    }
    
    // ------------------------------------
    // OBTENER CONDUCTORES SIN ASIGNAR
    // ------------------------------------
    public static List<Colaborador> obtenerConductoresSinAsignar() {

        List<Colaborador> listaColaboradores = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                listaColaboradores = conexionBD.selectList("colaborador.getConductoresSinAsignar");
            } catch (Exception e) {
                //System.err.println("Error al recuperar los colaboradores: " + e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            // USANDO LA CONSTANTE PROPORCIONADA
            System.err.println(Constantes.MSJ_ERROR_BD);
        }

        return listaColaboradores;
    }
}