package nu.educom.MI6;

import java.sql.*;

public class Agent {
    public static ResultSet getAgentByID(String id) throws SQLException {
        ResultSet rSet = null;
        Connection conn = null;
        try {
            conn = nu.educom.MI6.Connector.createConn();
            Statement stmt = conn.createStatement();
            String strStmt = "SELECT * FROM agents WHERE service_num = " + id;
            rSet = stmt.executeQuery(strStmt);
        } finally {
            conn.close();
        }
        return rSet;
    };
    public static Timestamp getLicenseToKill(String id) throws SQLException {
        Timestamp licenseToKill = null;
        ResultSet rSet;
        Connection conn = null;
        try {
            conn = nu.educom.MI6.Connector.createConn();
            Statement stmt = conn.createStatement();
            String strStmt = "SELECT * FROM agents WHERE service_num = " + id;
            rSet = stmt.executeQuery(strStmt);
            rSet.next();
            licenseToKill = rSet.getTimestamp(5);
        } finally {
            conn.close();
        }
        return licenseToKill;
    }
}
