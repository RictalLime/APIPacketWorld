package ws;

import Utilidades.Utilidades;
import dominio.LoginImp;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import dto.Mensaje;



@Path("login")
public class LoginWS {

    @Context
    private UriInfo context;

    public LoginWS() {
    }
    
    
    @Path("iniciar-sesion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador iniciarSesionColaborador(@FormParam("noPersonal") String noPersonal,@FormParam("contrasena") String password){
        if(!noPersonal.isEmpty() && !password.isEmpty()&& noPersonal.length() <=10 && Utilidades.validarInyecciónSQL(password) && Utilidades.validarInyecciónSQL(noPersonal)){
            return LoginImp.validarSesionColaborador(noPersonal, password);
        }
        throw new BadRequestException();
    }
    
    @Path("iniciar-sesion-app")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador iniciarSesionConductor(@FormParam("noPersonal") String noPersonal,@FormParam("contrasena") String password){
        //if(!noPersonal.isEmpty() && !password.isEmpty() && noPersonal.length() <=10 && Utilidades.validarInyecciónSQL(password) && Utilidades.validarInyecciónSQL(noPersonal)){
            return LoginImp.validarSesionConductor(noPersonal, password);
        //}  
        //throw new BadRequestException();
    }
    
}

