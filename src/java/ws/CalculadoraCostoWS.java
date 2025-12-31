/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Tron7
 */
public class CalculadoraCostoWS {
    public static float calcular(String cpOrigen, String cpDestino, int numPaquetes) {
        float costoTotal = 0f;

        if (cpOrigen == null || cpDestino == null || cpOrigen.trim().isEmpty() || cpDestino.trim().isEmpty()) {
            //System.err.println("Error: Código postal origen o destino vacío.");
            return 0f;
        }

        try {
            String urlStr = "http://sublimas.com.mx:8080/calculadora/api/envios/distancia/" + cpOrigen + "," + cpDestino;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status != 200) {
                //System.err.println("Error HTTP al consultar distancia: " + status);
                return 0f;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            if (json.has("error") && !json.get("error").getAsBoolean() && json.has("distanciaKM")) {
                float distanciaKM = json.get("distanciaKM").getAsFloat();

                // Tabla de costos por km
                float costoPorKm;
                if (distanciaKM <= 200) costoPorKm = 4f;
                else if (distanciaKM <= 500) costoPorKm = 3f;
                else if (distanciaKM <= 1000) costoPorKm = 2f;
                else if (distanciaKM <= 2000) costoPorKm = 1f;
                else costoPorKm = 0.5f;

                costoTotal = distanciaKM * costoPorKm;

                // Costo adicional por paquetes
                if (numPaquetes == 2) costoTotal += 50;
                else if (numPaquetes == 3) costoTotal += 80;
                else if (numPaquetes == 4) costoTotal += 110;
                else if (numPaquetes >= 5) costoTotal += 150;

                //System.out.println("Costo calculado: " + costoTotal + " (Distancia: " + distanciaKM + " km, Paquetes: " + numPaquetes + ")");
            } else {
                //System.err.println("Respuesta inválida o error en JSON: " + response.toString());
            }

        } catch (Exception e) {
            System.err.println("Excepción al calcular costo: ");
        }

        return costoTotal;
    }
}