/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

public class HistorialDeEnvio {
    
    // Tus campos originales
    private Integer idHistorialDeEnvio;
    private Integer idEstadoDeEnvio;
    private Integer idColaborador;
    private String colaborador;
    private String noGuia;
    private String motivo;
    private String tiempoDeCambio;
    
    // --- EL CAMPO QUE CAUSA EL ERROR ---
    // MyBatis intenta guardar 'nombreEstado' aquí porque viene en tu consulta SQL.
    private String nombreEstado; 
    // -----------------------------------

    public HistorialDeEnvio() {
    }

    public HistorialDeEnvio(Integer idHistorialDeEnvio, Integer idEstadoDeEnvio, Integer idColaborador, String colaborador, String noGuia, String motivo, String tiempoDeCambio, String nombreEstado) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.idColaborador = idColaborador;
        this.colaborador = colaborador;
        this.noGuia = noGuia;
        this.motivo = motivo;
        this.tiempoDeCambio = tiempoDeCambio;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdHistorialDeEnvio() {
        return idHistorialDeEnvio;
    }

    public void setIdHistorialDeEnvio(Integer idHistorialDeEnvio) {
        this.idHistorialDeEnvio = idHistorialDeEnvio;
    }

    public Integer getIdEstadoDeEnvio() {
        return idEstadoDeEnvio;
    }

    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) {
        this.idEstadoDeEnvio = idEstadoDeEnvio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
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

    // --- GETTERS Y SETTERS PARA EL CAMPO FALTANTE ---
    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}