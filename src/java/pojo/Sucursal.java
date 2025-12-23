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
    
    // ==============================================
    // ¡CAMPOS DE CLAVE FORÁNEA AGREGADOS PARA EL INSERT!
    // MyBatis los necesita para las columnas idCiudad e idEstado
    private Integer idCiudad; 
    private Integer idEstado;
    // ==============================================
    
    private String ciudad;   // Nombre de la Ciudad (para SELECT y mostrar)
    private String estado;   // Nombre del Estado (para SELECT y mostrar)

    public Sucursal() {
    }

    // Constructor completo
    public Sucursal(Integer idSucursal, String codigoSucursal, String nombre, String estatus, String calle, String numero, String colonia, String codigoPostal, Integer idCiudad, Integer idEstado, String ciudad, String estado) {
        this.idSucursal = idSucursal;
        this.codigoSucursal = codigoSucursal;
        this.nombre = nombre;
        this.estatus = estatus;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.idCiudad = idCiudad;
        this.idEstado = idEstado;
        this.ciudad = ciudad;
        this.estado = estado;
    }
    
    // --- GETTERS ---

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

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }
    
    // ==============================================
    // ¡GETTERS PARA LOS IDs REQUERIDOS POR MYBATIS!
    public Integer getIdCiudad() {
        return idCiudad;
    }

    public Integer getIdEstado() {
        return idEstado;
    }
    // ==============================================

    // --- SETTERS ---

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

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // ==============================================
    // ¡SETTERS PARA LOS IDs REQUERIDOS POR MYBATIS!
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    // ==============================================
}