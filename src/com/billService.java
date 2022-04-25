package com;
import model.BillManagement;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
@Path("/billmanagement")
public class billService {

	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getAllBillData() 
	{ 
		BillManagement newbill = new BillManagement();
		
		String getData = newbill.readItemsForAdmin();
		
		return getData;
	} 
	
	@POST
	@Path("/add/{MonthlyPayment}/{address}/{billId}/{dueDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertbilldata(@PathParam("MonthlyPayment") int MonthlyPayment,
								 @PathParam("address") String address,
								 @PathParam("billId") String billId,
								 @PathParam("dueDate") String dueDate) {
		
		BillManagement newbill = new BillManagement();
		String output = newbill.insertBill(MonthlyPayment,address , billId, dueDate);
		return output;
	}
	
	
}
