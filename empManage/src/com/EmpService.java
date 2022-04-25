package com; 
import model.Employee; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Employees") 
public class EmpService 
{ 
	Employee empObj = new Employee(); 
 @GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readEmp() 
 { 
 return empObj.readEmp(); 
}
 
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertItem(@FormParam("eName") String eName, 
  @FormParam("position") String position, 
  @FormParam("salary") String salary, 
  @FormParam("empType") String empType) 
 { 
  String output = empObj.insertEmp(eName,position,salary,empType); 
 return output; 
 }

 
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateItem(String empData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject userObject = new JsonParser().parse(empData).getAsJsonObject(); 
 //Read the values from the JSON object
  String empId = userObject.get("empId").getAsString(); 
  String eName = userObject.get("eName").getAsString(); 
  String position = userObject.get("position").getAsString(); 
  String salary = userObject.get("salary").getAsString(); 
  String  empType = userObject.get("empType").getAsString(); 
  String output = empObj.updateEmp(empId, eName, position, salary, empType); 
 return output; 
 }

 
 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteEmp(String empData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(empData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String empId = doc.select("empId").text(); 
  String output = empObj.deleteEmp(empId); 
 return output; 
 }
 
}