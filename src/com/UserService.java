package com;

import model.UserMamagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user_managemrnt")
public class UserService {

    @GET
    @Path("/user_service/user")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllBillData()
    {
        UserMamagement newbill = new UserMamagement();
        String getData = newbill.readAllUsers();
        return getData;
    }

    @POST
    @Path("/user_service/user/{UserId}/{UserName}/{Password}/{Address}/{Area}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertbilldata(@PathParam("UserId") int UserId,
                                 @PathParam("UserName") String UserName,
                                 @PathParam("Password") String Password,
                                 @PathParam("Address") String Address,
                                 @PathParam("Area") String Area) {

        UserMamagement newUser = new UserMamagement();
        String output = newUser.insertUser(UserId,UserName , Password, Address,Area);
        return output;
    }

    @PUT
    @Path("/user_service/user/{UserId}/{UserName}/{Password}/{Address}/{Area}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUserProfile(@PathParam("UserId") int UserId,
                                 @PathParam("UserName") String UserName,
                                 @PathParam("Password") String Password,
                                 @PathParam("Address") String Address,
                                 @PathParam("Area") String Area) {

        UserMamagement newUser = new UserMamagement();
        String output = newUser.updateUserProfile(UserId,UserName , Password, Address,Area);
        return output;
    }
    @DELETE
    @Path("/user_service/user/{UserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("UserId") int UserId){
        UserMamagement newUser = new UserMamagement();

        String outPut = newUser.deleteUser(UserId);
        return outPut;
    }
}
