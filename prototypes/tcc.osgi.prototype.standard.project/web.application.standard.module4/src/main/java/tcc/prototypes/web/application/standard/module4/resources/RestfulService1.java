package tcc.prototypes.web.application.standard.module4.resources;

import java.util.Date;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import tcc.prototypes.web.application.standard.module4.to.User;

/**
 * REST Web Service
 * 
 * http://localhost:8080/application/module4/resources/restfulService1
 * 
 */
@Path("restfulService1")
public class RestfulService1 {

    public RestfulService1() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getJson() {
        User user = new User();
        user.setUserId(new Date().getTime());
        user.setName("Felipe Rodrigues do Prado");
        user.setEmail("rodriguesprado.felipe@gmmail.com");
        user.setPassword("123456");
        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User postUser(JAXBElement<User> user) {
        User userTO = user.getValue();
        return userTO;
    }
    
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
