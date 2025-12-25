package ws;

import java.util.Set;
import javax.ws.rs.core.Application; // <--- ESTE ES EL IMPORT CORRECTO

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application { // <--- Hereda de javax.ws.rs.core.Application

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.CiudadWS.class);
        resources.add(ws.ClienteWS.class);
        resources.add(ws.ColaboradorWS.class);
        resources.add(ws.ConductorAsignadoWS.class);
        resources.add(ws.Cors.class);
        resources.add(ws.EnvioWS.class);
        resources.add(ws.EstadoWS.class);
        resources.add(ws.HistorialDeBajaWS.class);
        resources.add(ws.HistorialDeEnvioWS.class);
        resources.add(ws.LoginWS.class);
        resources.add(ws.PaqueteWS.class);
        resources.add(ws.RolWS.class);
        resources.add(ws.SucursalWS.class); // Asegúrate de que esta clase exista, si no, coméntala
        resources.add(ws.UnidadWS.class);
    }
}