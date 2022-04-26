package com;
import model.BillManagement;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;


@Path("/bill_management")
public class billService {

	@GET
	@Path("/bill_service")
	@Produces(MediaType.TEXT_PLAIN) 
	public String getAllBillData() 
	{ 
		BillManagement newbill = new BillManagement();
		
		String getData = newbill.readItemsForAdmin();
		
		return getData;
	} 
	
	@POST
	@Path("/bill_service/{MonthlyPayment}/{address}/{billId}/{dueDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertbilldata(@PathParam("MonthlyPayment") int MonthlyPayment,
								 @PathParam("address") String address,
								 @PathParam("billId") String billId,
								 @PathParam("dueDate") String dueDate) {
		
		BillManagement newbill = new BillManagement();
		String output = newbill.insertBill(MonthlyPayment,address , billId, dueDate);
		return output;
	}
	
	@PUT
	@Path("/bill_service/{id}/{MonthlyPayment}/{address}/{billId}/{dueDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBillData(@PathParam("id") String id,
								 @PathParam("MonthlyPayment") int MonthlyPayment,
								 @PathParam("address") String address,
								 @PathParam("billId") String billId,
								 @PathParam("dueDate") String dueDate) {
		
		BillManagement newbill = new BillManagement();
		String output = newbill.updateBill(id, MonthlyPayment,address , billId, dueDate);
		return output;
	}
	
	@DELETE
	@Path("/bill_service/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBillData(@PathParam("id") String id) {
		
		BillManagement newbill = new BillManagement();
		String output = newbill.deleteBill(id);
		return output;
	}
	
	
}
