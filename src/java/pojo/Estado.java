package pojo;

public class Estado {
    
    private Integer idEstado;
    private String nombre;

    public Estado() {
    }

    // Getters
    public Integer getIdEstado() {
        return idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}