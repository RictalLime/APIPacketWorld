package pojo;

/**
 *
 * @author Angel2
 */

public class HistorialDeEnvio {
    private Integer idHistorialDeEnvio;
    private Integer noGuia;      // Llave foránea hacia Envio
    private Integer noPersonal;  // Llave foránea hacia Colaborador
    private String motivo;
    private String estatusNuevo;
    private String fechaCambio;

    public HistorialDeEnvio() {
    }

    public HistorialDeEnvio(Integer idHistorialDeEnvio, Integer noGuia, Integer noPersonal, String motivo, String estatusNuevo, String fechaCambio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
        this.noGuia = noGuia;
        this.noPersonal = noPersonal;
        this.motivo = motivo;
        this.estatusNuevo = estatusNuevo;
        this.fechaCambio = fechaCambio;
    }

    // Getters y Setters

    public Integer getIdHistorialDeEnvio() {
        return idHistorialDeEnvio;
    }

    public void setIdHistorialDeEnvio(Integer idHistorialDeEnvio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
    }

    public Integer getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(Integer noGuia) {
        this.noGuia = noGuia;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstatusNuevo() {
        return estatusNuevo;
    }

    public void setEstatusNuevo(String estatusNuevo) {
        this.estatusNuevo = estatusNuevo;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public String toString() {
        return "HistorialDeEnvio{" + "idHistorialDeEnvio=" + idHistorialDeEnvio + ", noGuia=" + noGuia + ", noPersonal=" + noPersonal + ", motivo=" + motivo + ", estatusNuevo=" + estatusNuevo + ", fechaCambio=" + fechaCambio + '}';
    }
}