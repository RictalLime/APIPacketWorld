package pojo;

public class HistorialDeEnvio {
    
    private Integer idHistorialDeEnvio;
    private String noGuia; // En tu script es VARCHAR(20)
    private Integer idColaborador; // En tu script es idColaborador, no noPersonal
    private Integer idEstadoDeEnvio; // ID numérico
    private String nombreEstado;     // Campo extra para mostrar el texto (JOIN)
    private String motivo;
    private String tiempoDeCambio;   // Mapea a columna 'tiempoDeCambio'

    public HistorialDeEnvio() {
    }

    public HistorialDeEnvio(Integer idHistorialDeEnvio, String noGuia, Integer idColaborador, Integer idEstadoDeEnvio, String nombreEstado, String motivo, String tiempoDeCambio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
        this.noGuia = noGuia;
        this.idColaborador = idColaborador;
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.nombreEstado = nombreEstado;
        this.motivo = motivo;
        this.tiempoDeCambio = tiempoDeCambio;
    }

    public Integer getIdHistorialDeEnvio() {
        return idHistorialDeEnvio;
    }

    public void setIdHistorialDeEnvio(Integer idHistorialDeEnvio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdEstadoDeEnvio() {
        return idEstadoDeEnvio;
    }

    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTiempoDeCambio() {
        return tiempoDeCambio;
    }

    public void setTiempoDeCambio(String tiempoDeCambio) {
        this.tiempoDeCambio = tiempoDeCambio;
    }
}