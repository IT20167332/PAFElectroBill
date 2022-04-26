package model;

import org.json.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;

public class PowerManagement {

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

    //Insert Data
    public String insertpower(String reportID , int usage , int powerCuts , String weather) {

        String output = "";
        try {

            Connection con = connect();
            if(con == null) {
                return "error while connecting database";
            }

            String query = "insert into Power(`reportID` , `usage` , `powerCuts` , `weather`)"
                    + "value (?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, reportID);
            preparedStmt.setString(2, usage);
            preparedStmt.setString(3, powerCuts );
            preparedStmt.setString(4, weather );

            preparedStmt.execute();
            con.close();
            output = "Power usage report Insert Successfully";

        }catch(Exception e) {
            output = "Error while insert";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String readPower() {
        String output  = "";
        JSONObject obj=new JSONObject();
        try {
            Connection con = connect();

            if (con == null) {
                return "error while connecting database for reading";
            }

            String query = "select * from power";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                String reportID = Integer.toString(rs.getInt("reportID"));
                String itemCode = rs.getString("usage");
                String itemName = rs.getString("powerCuts");
                String itemPrice = Double.toString(rs.getDouble("weather"));

                // Add a row into the html table
                JSONObject obj2=new JSONObject();
                obj2.put("MonthlyPayment",reportID);
                obj2.put("address",usage);
                obj2.put("billId",powerCuts);
                obj2.put("dueDate", weather);

                obj.put(obj2);
                // buttons
                output = JSONValue.toJSONString(obj);
            }

            con.close();
            // Complete the html table



        }catch(Exception e) {

            output = "error while reading items";
            System.err.println(e.getMessage());

        }

        return output;
    }

    public String updatePower(String reportID, int usage, int powerCuts, String weather) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null){

                return "Error while connecting to the database for updating.";
            }

            // create a prepared statement
            String query = "UPDATE power SET pusage=?,powerCuts=?,weather=? WHERE reportID=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values

            preparedStmt.setString(2, usage);
            preparedStmt.setDouble(3, powerCuts);
            preparedStmt.setString(4, weather);
            preparedStmt.setString(1, reportID);

            // execute the statement
            preparedStmt.execute();

            con.close();

            output = "Updated successfully";

        }catch(Exception e) {

            output = "Error while updating the item.";
            System.err.println(e.getMessage());

        }

        return output;
    }

    public String deletePower(String reportID) {
        String output = "";
        try{
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            // create a prepared statement
            String query = "delete from items where reportID=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(reportID));

            // execute the statement
            preparedStmt.execute();

            con.close();

            output = "Deleted successfully";
        }catch (Exception e) {

            output = "Error while deleting the item.";
            System.err.println(e.getMessage());
        }

        return output;

    }


}
