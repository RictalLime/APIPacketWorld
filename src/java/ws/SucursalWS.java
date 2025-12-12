/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import com.google.gson.Gson;
import dominio.SucursalImp;
import dto.Mensaje;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Sucursal;

/**
 *
 * @author Tron7
 */
@Path("sucursal")
public class SucursalWS {
    @Path("obtenerSucursal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursal() {
        return SucursalImp.obtenerSucursal();
    }

    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrar(String json){
        Gson gson = new Gson();
        try{
            Sucursal sucursal = gson.fromJson(json, Sucursal.class);
            return SucursalImp.registrarSucursal(sucursal);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editar(String json){
        Gson gson = new Gson();
        try {
            Sucursal sucursal = gson.fromJson(json, Sucursal.class);
            return SucursalImp.editarSucursal(sucursal);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizarEstatus/{idSucursal}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEstatus(@PathParam("idSucursal") int idSucursal){
        try {
            return SucursalImp.actualizarEstatus(idSucursal);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
