package pojo;

import java.util.List;

public class Envio {
    
    // ==========================================================
    // PROPIEDADES
    // ==========================================================
    
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
    // Claves foráneas (IDs)
    private Integer idOrigenCiudad; // Mapea a idOrigenCiudad en la DB
    private Integer idOrigenEstado; // Mapea a idOrigenEstado en la DB (Nuevo)
    
    // Campos de la dirección física
    private String origen; // Campo concatenado para visualización (Opcional, si lo usas)
    private String origenCalle;
    private String origenNumero;
    private String origenColonia;
    private String origenCodigoPostal; 
    
    // Campos de Lectura (Obtenidos via JOINs)
    private String nombreCiudadOrigen; // Viene del JOIN
    private String nombreEstadoOrigen; // Viene del JOIN (Nuevo)
    
    // --- UBICACIÓN DE DESTINO ---
    // Clave foránea (ID)
    private Integer idDestinoCiudad; 
    private Integer idDestinoEstado; // Mapea a idDestinoEstado en la DB (Nuevo)
    
    // Campos de la dirección física 
    private String destino; // Campo concatenado para visualización (Opcional, si lo usas)
    private String destinoCalle; 
    private String destinoNumero;
    private String destinoColonia;
    private String destinoCodigoPostal;
    
    // Campos de Lectura (Obtenidos via JOINs)
    private String nombreCiudadDestino; 
    private String nombreEstadoDestino; // Viene del JOIN (Nuevo)
    
    private Integer idSucursal;
    
    private List<HistorialDeEnvio> historial;
    private List<Paquete> paquetes;

    
    // ==========================================================
    // CONSTRUCTORES
    // ==========================================================
    
    /**
     * Constructor Vacío
     */
    public Envio() {
    }
    
    /**
     * Constructor Sobrecargado (con todos los parámetros)
     */
    public Envio(Integer idEnvio, Integer idCliente, String cliente, Integer idColaborador, String colaborador, String noGuia, float costoDeEnvio, Integer idEstadoDeEnvio, String estadoDeEnvio, Integer idOrigenCiudad, Integer idOrigenEstado, String origen, String origenCalle, String origenNumero, String origenColonia, String origenCodigoPostal, String nombreCiudadOrigen, String nombreEstadoOrigen, Integer idDestinoCiudad, Integer idDestinoEstado, String destino, String destinoCalle, String destinoNumero, String destinoColonia, String destinoCodigoPostal, String nombreCiudadDestino, String nombreEstadoDestino, Integer idSucursal) {
        this.idEnvio = idEnvio;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.idColaborador = idColaborador;
        this.colaborador = colaborador;
        this.noGuia = noGuia;
        this.costoDeEnvio = costoDeEnvio;
        this.idEstadoDeEnvio = idEstadoDeEnvio;
        this.estadoDeEnvio = estadoDeEnvio;
        
        // Origen
        this.idOrigenCiudad = idOrigenCiudad;
        this.idOrigenEstado = idOrigenEstado;
        this.origen = origen;
        this.origenCalle = origenCalle;
        this.origenNumero = origenNumero;
        this.origenColonia = origenColonia;
        this.origenCodigoPostal = origenCodigoPostal;
        this.nombreCiudadOrigen = nombreCiudadOrigen;
        this.nombreEstadoOrigen = nombreEstadoOrigen;
        
        // Destino
        this.idDestinoCiudad = idDestinoCiudad;
        this.idDestinoEstado = idDestinoEstado;
        this.destino = destino;
        this.destinoCalle = destinoCalle;
        this.destinoNumero = destinoNumero;
        this.destinoColonia = destinoColonia;
        this.destinoCodigoPostal = destinoCodigoPostal;
        this.nombreCiudadDestino = nombreCiudadDestino;
        this.nombreEstadoDestino = nombreEstadoDestino;
        this.idSucursal = idSucursal;
    }
    
    // ==========================================================
    // GETTERS
    // ==========================================================
    
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
    public Integer getIdOrigenCiudad() { return idOrigenCiudad; }
    public Integer getIdOrigenEstado() { return idOrigenEstado; }
    public String getOrigen() { return origen; } 
    public String getOrigenCalle() { return origenCalle; }
    public String getOrigenNumero() { return origenNumero; }
    public String getOrigenColonia() { return origenColonia; }
    public String getOrigenCodigoPostal() { return origenCodigoPostal; }
    public String getNombreCiudadOrigen() { return nombreCiudadOrigen; } 
    public String getNombreEstadoOrigen() { return nombreEstadoOrigen; } // Nuevo Getter

    // DESTINO IDs y Dirección
    public Integer getIdDestinoCiudad() { return idDestinoCiudad; }
    public Integer getIdDestinoEstado() { return idDestinoEstado; }
    public String getDestino() { return destino; } 
    public String getDestinoCalle() { return destinoCalle; }
    public String getDestinoNumero() { return destinoNumero; }
    public String getDestinoColonia() { return destinoColonia; }
    public String getDestinoCodigoPostal() { return destinoCodigoPostal; }
    public String getNombreCiudadDestino() { return nombreCiudadDestino; } 
    public String getNombreEstadoDestino() { return nombreEstadoDestino; } // Nuevo Getter
    public Integer getIdSucursal() { return idSucursal;}
    
    public List<HistorialDeEnvio> getHistorial() { return historial; }
    public List<Paquete> getPaquetes() { return paquetes; }

    // ==========================================================
    // SETTERS
    // ==========================================================
    
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
    public void setIdOrigenCiudad(Integer idOrigenCiudad) { this.idOrigenCiudad = idOrigenCiudad; }
    public void setIdOrigenEstado(Integer idOrigenEstado) { this.idOrigenEstado = idOrigenEstado; } // Nuevo Setter
    public void setOrigen(String origen) { this.origen = origen; }
    public void setOrigenCalle(String origenCalle) { this.origenCalle = origenCalle; }
    public void setOrigenNumero(String origenNumero) { this.origenNumero = origenNumero; }
    public void setOrigenColonia(String origenColonia) { this.origenColonia = origenColonia; }
    public void setOrigenCodigoPostal(String origenCodigoPostal) { this.origenCodigoPostal = origenCodigoPostal; }
    public void setNombreCiudadOrigen(String nombreCiudadOrigen) { this.nombreCiudadOrigen = nombreCiudadOrigen; }
    public void setNombreEstadoOrigen(String nombreEstadoOrigen) { this.nombreEstadoOrigen = nombreEstadoOrigen; } // Nuevo Setter

    // DESTINO
    public void setIdDestinoCiudad(Integer idDestinoCiudad) { this.idDestinoCiudad = idDestinoCiudad; }
    public void setIdDestinoEstado(Integer idDestinoEstado) { this.idDestinoEstado = idDestinoEstado; } // Nuevo Setter
    public void setDestino(String destino) { this.destino = destino; }
    public void setDestinoCalle(String destinoCalle) { this.destinoCalle = destinoCalle; }
    public void setDestinoNumero(String destinoNumero) { this.destinoNumero = destinoNumero; }
    public void setDestinoColonia(String destinoColonia) { this.destinoColonia = destinoColonia; }
    public void setDestinoCodigoPostal(String destinoCodigoPostal) { this.destinoCodigoPostal = destinoCodigoPostal; }
    public void setNombreCiudadDestino(String nombreCiudadDestino) { this.nombreCiudadDestino = nombreCiudadDestino; }
    public void setNombreEstadoDestino(String nombreEstadoDestino) { this.nombreEstadoDestino = nombreEstadoDestino; } // Nuevo Setter
    public void setIdSucursal(Integer idSucursal) {this.idSucursal = idSucursal;}
    
    public void setHistorial(List<HistorialDeEnvio> historial) { this.historial = historial; }
    public void setPaquetes(List<Paquete> paquetes) { this.paquetes = paquetes; }

}
