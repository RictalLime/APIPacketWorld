package pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Clase Cliente - Adaptada a la normalización de Ubicación.
 */
public class Cliente {
    
    // Atributos de Identificación y Contacto
    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    
    // Atributos de Dirección
    private String calle;
    private String numeroDeCasa;
    private String colonia;
   @SerializedName("codigoPostal")
    private String cp; // Código Postal
    
    // ATRIBUTOS DE UBICACIÓN (NORMALIZADOS)
    
    /**
     * Clave Foránea: Usada para INSERT/UPDATE. 
     * Apunta a la tabla 'ciudad'.
     */
    private Integer idCiudad; 
    
    /**
     * Campo de Lectura: Nombre de la ciudad obtenido vía JOIN.
     * Usado para SELECT.
     */
    private String nombreCiudad;
    
    private Integer idEstado;
    /**
     * Campo de Lectura: Nombre del estado obtenido vía JOIN.
     * Usado para SELECT.
     */
    private String nombreEstado;
    
    // Constructor por defecto
    public Cliente() {
    }

    // --- Getters y Setters ---

    // ID y Nombres
    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    // Dirección
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getNumeroDeCasa() {
        return numeroDeCasa;
    }
    public void setNumeroDeCasa(String numeroDeCasa) {
        this.numeroDeCasa = numeroDeCasa;
    }
    public String getColonia() {
        return colonia;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    
    // Código Postal
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }



    // Contacto
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    // NUEVOS CAMPOS DE UBICACIÓN
    
    public Integer getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    public String getNombreCiudad() {
        return nombreCiudad;
    }
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}