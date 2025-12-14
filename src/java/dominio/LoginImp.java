package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class LoginImp {

    // Método para Colaboradores (Web/Panel)
    public static Colaborador validarSesionColaborador(String noPersonal, String password) {
        Colaborador respuesta = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasena", password);
                Colaborador colaborador = conexionBD.selectOne("login.iniciarSesion", parametros);
                if (colaborador != null) {
                    respuesta = colaborador;
                } else {
                    // Log opcional para web login
                    System.out.println("LOG (Web): Credenciales incorrectas para: " + noPersonal);
                }
            } catch (Exception e) {
                System.err.println("ERROR (Web): Excepción al validar sesión: " + e.getMessage());
                e.printStackTrace();
            } finally {
                 // Asegurar el cierre de la sesión para web login
                 conexionBD.close();
            }
        } else {
             System.err.println("ERROR (Web): No se pudo obtener la conexión a la BD.");
        }
        return respuesta;
    }

    // Método para Conductores (App Móvil) - **EL MÉTODO CLAVE**
    public static Colaborador validarSesionConductor(String noPersonal, String password) {
        Colaborador respuesta = null;
        SqlSession conexionDB = MyBatisUtil.obtenerConexion();
        
        // --- 1. Verificar Conexión a BD ---
        if (conexionDB == null) {
            System.err.println("ERROR (App): No se pudo obtener la conexión a la BD para: " + noPersonal);
            return null; // Retorna null si no hay conexión
        }
        
        try {
            HashMap<String, String> parametros = new LinkedHashMap<>();
            parametros.put("noPersonal", noPersonal);
            parametros.put("contrasena", password);
            
            // --- 2. Ejecutar Consulta MyBatis ---
            Colaborador colaborador = conexionDB.selectOne("login.iniciarSesionApp", parametros);
            
            // --- 3. Evaluar Resultado ---
            if (colaborador != null) {
                respuesta = colaborador;
                System.out.println("✅ LOG (App): LOGIN EXITOSO para conductor No. Personal: " + noPersonal);
            } else {
                System.out.println("❌ LOG (App): LOGIN FALLIDO para conductor No. Personal: " + noPersonal + " - Colaborador es NULL.");
            }
        } catch (Exception e) {
            // --- 4. Manejar Excepción de BD ---
            System.err.println("🚨 ERROR (App): Excepción en MyBatis/BD para " + noPersonal + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            // --- 5. Asegurar el Cierre de la Sesión ---
            if (conexionDB != null) {
                conexionDB.close();
            }
        }
        return respuesta;
    }
}