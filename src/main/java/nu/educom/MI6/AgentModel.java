package nu.educom.MI6;

import java.sql.*;

public class AgentModel {
    protected static boolean checkServiceNumber(String serviceNumber) {
        boolean valid = true;
        if (!nu.educom.MI6.Utility.isNumeric(serviceNumber)) {
            valid = false;
        }
        if (!nu.educom.MI6.Utility.isInRange(serviceNumber, 1, 956)) {
            valid = false;
        }
        if (serviceNumber.contains("+")) {
            valid = false;
        }
        if (serviceNumber.length() > 3) {
            valid = false;
        }
        return valid;
    }
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
