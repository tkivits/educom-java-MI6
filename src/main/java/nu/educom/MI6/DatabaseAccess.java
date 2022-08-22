package nu.educom.MI6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
    Connection conn = initiateConnection();

    public Connection initiateConnection() {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:4400/mi6";
            String user      = "MI6User";
            String password  = "vmCyOY(0LnYN_K3-";

            conn = DriverManager.getConnection(url, user, password);

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn == null);
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
