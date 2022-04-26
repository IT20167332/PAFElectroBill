package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.PowerManagement;
@Path("http://localhost:8989/power_management/power_service/power")
public class powerService {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String insertpower()
    {
        PowerManagement newbill = new PowerManagement();

        String getData = newbill.readPower();

        return getData;

    }
    @POST
    @Path("http://localhost:8989/power_management/power_service/power/{reportID}/{usage}/{powerCuts}/{weather}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertPower(@PathParam("reportID") String reportID,
                                 @PathParam("usage") int usage,
                                 @PathParam("powerCuts") int powerCuts,
                                 @PathParam("weather") String weather) {

        PowerManagement newbill = new PowerManagement();
        String output = newbill.insertpower(reportID,usage , powerCuts, weather);
        return output;
    }

    @PUT
    @Path("http://localhost:8989/power_management/power_service/power/{reportID}/{usage}/{powerCuts}/{weather}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePower(@PathParam("reportID") String reportID,
                              @PathParam("usage") int usage,
                              @PathParam("powerCuts") int powerCuts,
                              @PathParam("weather") String weather){
        PowerManagement newPower = new PowerManagement();
        String output = newPower.updatePower(reportID,usage , powerCuts, weather);
        return output;
    }

    @DELETE
    @Path("http://localhost:8989/power_management/power_service/power/{id}")
    public String DeletePower(@PathParam("id") String id){
        PowerManagement delete = new PowerManagement();
        String output = delete.deletePower(id);
        return output;
    }
}