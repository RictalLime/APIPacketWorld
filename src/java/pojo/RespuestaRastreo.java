/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;
import java.util.List;

/**
 *
 * @author Angel2
 */

public class RespuestaRastreo {
    
    // Datos Generales del Envío (Header)
    private String guideNumber;  // noGuia
    private String status;       // Estatus actual (texto visible)
    private String statusCode;   // Código para el color (ej. "created", "transit")
    private String client;       // Nombre del cliente
    private String serviceType;  // Tipo de servicio
    private String origin;       // Origen
    private String destination;  // Destino
    
    // Listas anidadas
    private List<PaqueteResumen> packages;     // Lista de paquetes
    private List<HistorialDeEnvio> history;    // Tu POJO existente de historial

    // --- Constructor vacío ---
    public RespuestaRastreo() {}

    // --- Getters y Setters ---
    public String getGuideNumber() { return guideNumber; }
    public void setGuideNumber(String guideNumber) { this.guideNumber = guideNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public List<PaqueteResumen> getPackages() { return packages; }
    public void setPackages(List<PaqueteResumen> packages) { this.packages = packages; }

    public List<HistorialDeEnvio> getHistory() { return history; }
    public void setHistory(List<HistorialDeEnvio> history) { this.history = history; }

    // --- Clase interna para el resumen del paquete ---
    // Si ya tienes un POJO 'Paquete', puedes usar ese en lugar de esta clase estática
    public static class PaqueteResumen {
        private String id;
        private String description;
        private String dimensions;
        private String weight;

        public PaqueteResumen() {}
        
        public PaqueteResumen(String id, String description, String dimensions, String weight) {
            this.id = id;
            this.description = description;
            this.dimensions = dimensions;
            this.weight = weight;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getDimensions() { return dimensions; }
        public void setDimensions(String dimensions) { this.dimensions = dimensions; }

        public String getWeight() { return weight; }
        public void setWeight(String weight) { this.weight = weight; }
    }
}
