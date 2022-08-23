package nu.educom.MI6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Agent {
    public static ResultSet getAgentByID(String id) {
        ResultSet rSet = null;
        try {
            Connection conn = nu.educom.MI6.Connector.createConn();
            Statement stmt = conn.createStatement();
            String strStmt = "SELECT * FROM agents WHERE service_num = " + id;
            rSet = stmt.executeQuery(strStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSet;
    };
}
