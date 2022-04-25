package model; 
import java.sql.*; 
public class Employee 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee", "root", "root"); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertEmp(String eName, String position, String salary, String empType) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into emp (`empId`,`eName`,`position`,`salary`,`empType`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, eName); 
 preparedStmt.setString(3,position ); 
 preparedStmt.setString(4, salary); 
 preparedStmt.setString(5, empType); 
 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String readUsers() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>User Nic</th>" +
 "<th>Employee</th>" + 
 "<th>Salary</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from emp"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String userID = Integer.toString(rs.getInt("empId")); 
 String userName = rs.getString("eName"); 
 String userNIC = rs.getString("position"); 
 String contactNumber = rs.getString("salary"); 
 String userType = rs.getString("empType"); 
 // Add into the html table
 output += "<tr><td>" + eName + "</td>"; 
 output += "<td>" + position + "</td>"; 
 output += "<td>" + salary + "</td>"; 
 output += "<td>" + empType + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='Users.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='empId' type='hidden' value='" + empId
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the users."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String updateEmp(String empId, String eName, String position, String salary, String empType) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE emp SET eName=?,position=?,salary=?,empType=? WHERE empID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, eName); 
 preparedStmt.setString(2, position); 
 preparedStmt.setString(3, salary); 
 preparedStmt.setString(4, empType); 
 preparedStmt.setInt(5, Integer.parseInt(empId)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 

public String deleteEmp(String empId) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from emp where empId=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(empId)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 
