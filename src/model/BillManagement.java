package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject; 
import org.json.simple.JSONValue;  

public class BillManagement {

	public Connection connect() {
      Connection con = null;
      try{
          Class.forName("com.mysql.jdbc.Driver");
          con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pafpoject/billingService", "root", "");
          //For testing
          //System.out.print("Successfully connected");

      }catch(Exception e){

          e.printStackTrace();
      }

      return con;
	}

  //Insert Data
  public String insertBill(int MonthlyPayment , String address , String billId , String dueDate) {

      String output = "";
      try {

          Connection con = connect();
          if(con == null) {
              return "error while connecting database";
          }

          String query = "insert into BILL(`MonthlyPayment` , `address` , `billId` , `dueDate`)"
                  + "value (?,?,?,?,?)";

          PreparedStatement preparedStmt = con.prepareStatement(query);

          // binding values
          preparedStmt.setInt(1, MonthlyPayment);
          preparedStmt.setString(2, address);
          preparedStmt.setString(3, billId );
          preparedStmt.setString(4,dueDate );

          //execute the statement
          preparedStmt.execute();
          con.close();
          output = "Bill added!";

      }catch(Exception e) {
          output = "Error while inserting";
          System.err.println(e.getMessage());
      }
      return output;
  }


  //read Data
  public String readItemsForAdmin() {
      String output  = "";
      JSONObject obj=new JSONObject(); 
      try {
          Connection con = connect();

          if (con == null) {
              return "error while connecting database!";
          }

          String query = "select * from items";
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(query);

          while (rs.next())
          {
              String MonthlyPayment = Integer.toString(rs.getInt("MonthlyPayment"));
              String address = rs.getString("address");
              String billId = rs.getString("billId");
              String dueDate = Double.toString(rs.getDouble("dueDate"));
              
              // Add a row into the html table 
              
              obj.put("MonthlyPayment",MonthlyPayment);    
              obj.put("address",address);    
              obj.put("billId",billId); 
              obj.put("dueDate", dueDate);
              // buttons
              
          }

          con.close();
          // Complete the html table
          output = JSONValue.toJSONString(obj); 
      }catch(Exception e) {

          output = "error while reading items";
          //System.err.println(e.getMessage());
      }
      return output;
  }

  //update
  public String updateBill(String ID, int MonthlyPayment, String address, String billId, String dueDate) {

      String output = "";

      try {

          Connection con = connect();

          if (con == null){

              return "Error while updating.";
          }

          // create a prepared statement
          String query = "UPDATE items SET MonthlyPayment=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?";

          PreparedStatement preparedStmt = con.prepareStatement(query);

          // binding values
          preparedStmt.setString(1,Integer.toString(MonthlyPayment) );
          preparedStmt.setString(2, address);
          preparedStmt.setString(3, billId);
          preparedStmt.setString(4, dueDate);
          // execute the statement
          preparedStmt.execute();

          con.close();

          output = "Updated successfully";

      }catch(Exception e) {

          output = "Error while updating bill.";
          //System.err.println(e.getMessage());

      }
      return output;
  }

  //delete
  public String deleteBill(String ID) {
      String output = "";
      try{
          Connection con = connect();
          if (con == null) {
              return "Error while deleting.";
          }

          // create a prepared statement
          String query = "delete from items where itemID=?";
          PreparedStatement preparedStmt = con.prepareStatement(query);

          
          preparedStmt.setInt(1, Integer.parseInt(ID));

          preparedStmt.execute();

          con.close();

          output = "Deleted successfully";
      }catch (Exception e) {

          output = "Error while deleting the item.";
          //System.err.println(e.getMessage());
      }

      return output;

  }
}
