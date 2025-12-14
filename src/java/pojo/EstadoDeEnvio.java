package pojo;

/**
 *
 * @author Angel2
 */
public class EstadoDeEnvio {
    
    private Integer idEstadoDeEnvio;
    private String nombre;

    // Constructor vacío
    public EstadoDeEnvio() {
    }

    // Constructor con parámetros
    public EstadoDeEnvio(Integer idEstadoDeEnvio, String nombre) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getIdEstadoDeEnvio() {
        return idEstadoDeEnvio;
    }

    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
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