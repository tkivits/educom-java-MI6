package nu.educom.MI6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection createConn() {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://127.0.0.1:3306/mi6";
            String user      = "MI6User";
            String password  = "vmCyOY(0LnYN_K3-";

            conn = DriverManager.getConnection(url, user, password);

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
