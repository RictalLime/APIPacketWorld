package pojo;

/**
 *
 * @author Angel2
 */

public class EstadoDeEnvio {
    
    private Integer idEstadoEnvio;
    private String nombre;

    public EstadoDeEnvio() {
    }

    public EstadoDeEnvio(Integer idEstadoEnvio, String nombre) {
        this.idEstadoEnvio = idEstadoEnvio;
        this.nombre = nombre;
    }

    public Integer getIdEstadoEnvio() {
        return idEstadoEnvio;
    }

    public void setIdEstadoEnvio(Integer idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre; // Devuelve el nombre para mostrarse fácil en ComboBoxes
    }
}