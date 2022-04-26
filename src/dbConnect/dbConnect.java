package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dbConnect {
	
	public static Connection connection;
    public static final String USERNAME = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/oppyElectro";
    public static final String PASSWORD = "";

    public dbConnect() {
    }

    public static Connection connect() throws SQLException, ClassNotFoundException {

        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return connection;

    }

}
