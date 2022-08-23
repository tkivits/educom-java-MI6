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
    public static boolean getLicenseToKill(String id) {
        ResultSet rSet = null;
        boolean hasLicense = false;
        try {
            Connection conn = nu.educom.MI6.Connector.createConn();
            Statement stmt = conn.createStatement();
            String strStmt = "SELECT * FROM agents WHERE service_num = " + id;
            rSet = stmt.executeQuery(strStmt);
            rSet.next();
            String licToKill = rSet.getString(5);
            if(licToKill.equals("yes")) {
                hasLicense = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasLicense;
    }
}
