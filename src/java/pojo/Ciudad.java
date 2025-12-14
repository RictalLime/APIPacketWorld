package pojo;

public class Ciudad {
    
    private Integer idCiudad;
    private String nombre;
    private Integer idEstado; // Clave foránea

    public Ciudad() {
    }

    // Getters
    public Integer getIdCiudad() {
        return idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    // Setters
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}