package nu.educom.MI6;

import java.sql.*;

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
    public static Timestamp getLicenseToKill(String id) {
        Timestamp licenseToKill = null;
        ResultSet rSet;
        try {
            Connection conn = nu.educom.MI6.Connector.createConn();
            Statement stmt = conn.createStatement();
            String strStmt = "SELECT * FROM agents WHERE service_num = " + id;
            rSet = stmt.executeQuery(strStmt);
            rSet.next();
            licenseToKill = rSet.getTimestamp(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenseToKill;
    }
}
