package pojo;

/**
 *
 * @author Tron7
 */
// Clase Sucursal
public class Sucursal {
    
    private Integer idSucursal;
    private String codigoSucursal;
    private String nombre;
    private String estatus;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private Integer idCiudad;
    private String ciudad;
    private Integer idEstado;
    private String estado;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String codigoSucursal, String nombre, String estatus, String calle, String numero, String colonia, String codigoPostal, Integer idCiudad, String ciudad, Integer idEstado, String estado) {
        this.idSucursal = idSucursal;
        this.codigoSucursal = codigoSucursal;
        this.nombre = nombre;
        this.estatus = estatus;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}