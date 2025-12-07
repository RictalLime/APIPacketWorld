/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Tron7
 */
public class ConductorAsignado {
    private Integer idConductorAsignado;
    private Integer idColaborador;
    private Integer idUnidad;
    private String conductor;
    private String unidad;
    private String vin;

    public ConductorAsignado() {
    }

    public ConductorAsignado(Integer idConductorAsignado, Integer idColaborador, Integer idUnidad, String conductor, String unidad, String vin) {
        this.idConductorAsignado = idConductorAsignado;
        this.idColaborador = idColaborador;
        this.idUnidad = idUnidad;
        this.conductor = conductor;
        this.unidad = unidad;
        this.vin = vin;
    }

    public Integer getIdConductorAsignado() {
        return idConductorAsignado;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public String getConductor() {
        return conductor;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getVin() {
        return vin;
    }

    public void setIdConductorAsignado(Integer idConductorAsignado) {
        this.idConductorAsignado = idConductorAsignado;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
