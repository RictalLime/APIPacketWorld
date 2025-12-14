package pojo;

import java.util.List;

/**
 * Clase Envio - Adaptada a la normalización de Ubicación.
 */
public class Envio {
    
    private Integer idEnvio;
    
    // Relación con Cliente y Colaborador
    private Integer idCliente;
    private String cliente; 
    private Integer idColaborador;
    private String colaborador; 

    // Datos del Envío
    private String noGuia;
    private float costoDeEnvio;
    private Integer idEstadoDeEnvio;
    private String estadoDeEnvio; 
    
    // --- UBICACIÓN DE ORIGEN ---
    // Claves foráneas (IDs) para la escritura (INSERT/UPDATE)
    private Integer idCiudadOrigen; 
    
    // Campos de la dirección física (Se mantienen)
    private String origen; // Campo concatenado para visualización
    private String origenCalle;
    private String origenNumero;
    private String origenColonia;
    private String origenCodigoPostal; 
    
    // Campos de Lectura (Obtenidos via JOINs)
    private String nombreCiudadOrigen;
    private String nombreEstadoOrigen;
    
    // --- UBICACIÓN DE DESTINO ---
    // Clave foránea (ID) para la escritura (INSERT/UPDATE)
    private Integer idCiudadDestino; 
    
    // Campos de la dirección física 
    private String destino; // Campo concatenado para visualización
    private String destinoCalle; 
    private String destinoNumero;
    private String destinoColonia;
    private String destinoCodigoPostal;
    
    // Campos de Lectura (Obtenidos via JOINs)
    private String nombreCiudadDestino;
    private String nombreEstadoDestino;
    
    // --- LISTAS DETALLADAS (No están en la tabla envio, se llenan aparte) ---
    private List<HistorialDeEnvio> historial;
    private List<Paquete> paquetes;

    public Envio() {
    }
    
    // Constructor completo (Adaptado al nuevo modelo)
    // NOTA: El constructor debe reflejar la estructura de tu aplicación si lo utilizas. 
    // Por simplicidad, se omite el constructor con todos los parámetros aquí.

    // --- Getters ---
    public Integer getIdEnvio() { return idEnvio; }
    public Integer getIdCliente() { return idCliente; }
    public String getCliente() { return cliente; } 
    public Integer getIdColaborador() { return idColaborador; }
    public String getColaborador() { return colaborador; } 
    public String getNoGuia() { return noGuia; }
    public float getCostoDeEnvio() { return costoDeEnvio; }
    public Integer getIdEstadoDeEnvio() { return idEstadoDeEnvio; }
    public String getEstadoDeEnvio() { return estadoDeEnvio; } 

    // ORIGEN IDs y Dirección
    public Integer getIdCiudadOrigen() { return idCiudadOrigen; }
    public String getOrigen() { return origen; } // Campo concatenado
    public String getOrigenCalle() { return origenCalle; }
    public String getOrigenNumero() { return origenNumero; }
    public String getOrigenColonia() { return origenColonia; }
    public String getOrigenCodigoPostal() { return origenCodigoPostal; }
    public String getNombreCiudadOrigen() { return nombreCiudadOrigen; } 
    public String getNombreEstadoOrigen() { return nombreEstadoOrigen; } 

    // DESTINO IDs y Dirección
    public Integer getIdCiudadDestino() { return idCiudadDestino; }
    public String getDestino() { return destino; } // Campo concatenado
    public String getDestinoCalle() { return destinoCalle; }
    public String getDestinoNumero() { return destinoNumero; }
    public String getDestinoColonia() { return destinoColonia; }
    public String getDestinoCodigoPostal() { return destinoCodigoPostal; }
    public String getNombreCiudadDestino() { return nombreCiudadDestino; } 
    public String getNombreEstadoDestino() { return nombreEstadoDestino; } 
    
    // LISTAS
    public List<HistorialDeEnvio> getHistorial() { return historial; }
    public List<Paquete> getPaquetes() { return paquetes; }

    // --- Setters ---
    public void setIdEnvio(Integer idEnvio) { this.idEnvio = idEnvio; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setIdColaborador(Integer idColaborador) { this.idColaborador = idColaborador; }
    public void setColaborador(String colaborador) { this.colaborador = colaborador; }
    public void setNoGuia(String noGuia) { this.noGuia = noGuia; }
    public void setCostoDeEnvio(float costoDeEnvio) { this.costoDeEnvio = costoDeEnvio; }
    public void setIdEstadoDeEnvio(Integer idEstadoDeEnvio) { this.idEstadoDeEnvio = idEstadoDeEnvio; }
    public void setEstadoDeEnvio(String estadoDeEnvio) { this.estadoDeEnvio = estadoDeEnvio; } 
    
    // ORIGEN
    public void setIdCiudadOrigen(Integer idCiudadOrigen) { this.idCiudadOrigen = idCiudadOrigen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public void setOrigenCalle(String origenCalle) { this.origenCalle = origenCalle; }
    public void setOrigenNumero(String origenNumero) { this.origenNumero = origenNumero; }
    public void setOrigenColonia(String origenColonia) { this.origenColonia = origenColonia; }
    public void setOrigenCodigoPostal(String origenCodigoPostal) { this.origenCodigoPostal = origenCodigoPostal; }
    public void setNombreCiudadOrigen(String nombreCiudadOrigen) { this.nombreCiudadOrigen = nombreCiudadOrigen; }
    public void setNombreEstadoOrigen(String nombreEstadoOrigen) { this.nombreEstadoOrigen = nombreEstadoOrigen; }

    // DESTINO
    public void setIdCiudadDestino(Integer idCiudadDestino) { this.idCiudadDestino = idCiudadDestino; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setDestinoCalle(String destinoCalle) { this.destinoCalle = destinoCalle; }
    public void setDestinoNumero(String destinoNumero) { this.destinoNumero = destinoNumero; }
    public void setDestinoColonia(String destinoColonia) { this.destinoColonia = destinoColonia; }
    public void setDestinoCodigoPostal(String destinoCodigoPostal) { this.destinoCodigoPostal = destinoCodigoPostal; }
    public void setNombreCiudadDestino(String nombreCiudadDestino) { this.nombreCiudadDestino = nombreCiudadDestino; }
    public void setNombreEstadoDestino(String nombreEstadoDestino) { this.nombreEstadoDestino = nombreEstadoDestino; }
    
    // LISTAS (Esto era lo que faltaba)
    public void setHistorial(List<HistorialDeEnvio> historial) { this.historial = historial; }
    public void setPaquetes(List<Paquete> paquetes) { this.paquetes = paquetes; }

}