package com.test.auth;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.dropwizard.auth.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/user/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{

    @RolesAllowed({"ADMIN"})
    @GET
    @Path("admin")
    public String show(@Auth Principal principal)  throws  JsonProcessingException{
        return "{ \"user\" : \""+principal.getName()+"\",\"status\" : \"OK\"}";
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("admin2")
    public String getSecretPlan(@Context SecurityContext context) throws JsonProcessingException {
        User userPrincipal = (User) context.getUserPrincipal();
        return  "{ \"status\" : \"OK\"}";
    }

    @GET
    @Path("implicit-permitall")
    public String implicitPermitAllAuthorization(@Auth Principal principal) {
        return "'" + principal.getName() + "' has user privileges";
    }

    @GET
    @Path("noauth")
    public String hello() {
        return "hello";
    }
}
