package model;

import org.json.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;

public class UserMamagement {

    public Connection connect() {
        Connection con = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pafpoject/billingService", "root", "");
            //For testing
            System.out.print("Successfully connected");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public String insertUser(int UserId , String UserName , String Password , String Address, String Area) {

        String output = "";
        try {
            Connection con = connect();
            if(con == null) {
                return "error while connecting database";
            }

            String query = "insert into BILL(`UserId` , `UserName` , `Password` , `Address`,`Area`)"
                    + "value (?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, UserId);
            preparedStmt.setString(2, UserName);
            preparedStmt.setString(3, Password );
            preparedStmt.setString(4, Address );
            preparedStmt.setString(4, Area);

            //execute the statement
            preparedStmt.execute();
            con.close();
            output = "Bill added!";

        }catch(Exception e) {
            output = "Error while inserting user";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String readAllUsers() {
        String output  = "";
        JSONObject obj=new JSONObject();
        try {
            Connection con = connect();
            if (con == null) {
                return "error while connecting database!";
            }
            String query = "select * from Users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                String UserId = Integer.toString(rs.getInt("UserId"));
                String UserName = rs.getString("UserName");
                String Address = rs.getString("Address");
                String Area = Double.toString(rs.getDouble("Area"));

                // Add a row into the html table

                obj.put("UserId",UserId);
                obj.put("UserName",UserName);
                obj.put("Address",Address);
                obj.put("Area", Area);
                // buttons

            }

            con.close();
            // Complete the html table

            output = JSONValue.toJSONString(obj);
        }catch(Exception e) {
            output = "error while reading user";
            //System.err.println(e.getMessage());
        }
        return output;
    }

    public String updateUserProfile(int UserId, String UserName, String Address, String Area, String Password) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null){

                return "Error while updating.";
            }

            // create a prepared statement
            String query = "UPDATE items SET UserName=?,Address=?,Area=?,Password=? WHERE UserId=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values

            preparedStmt.setString(2, UserName);
            preparedStmt.setString(3, Address);
            preparedStmt.setString(4, Area);
            preparedStmt.setString(1, Password);
            preparedStmt.setString(4, Integer.toString(UserId));
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

    public String deleteUser(int UserId) {
        String output = "";
        try{
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            // create a prepared statement
            String query = "delete from items where UserId=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, UserId);

            // execute the statement
            preparedStmt.execute();

            con.close();

            output = "Deleted successfully";
        }catch (Exception e) {

            output = "Error while deleting the user.";
            System.err.println(e.getMessage());
        }

        return output;

    }
}
