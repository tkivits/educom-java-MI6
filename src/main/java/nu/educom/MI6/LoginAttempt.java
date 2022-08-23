package nu.educom.MI6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;

public class LoginAttempt {
    public static void insertLoginAttempt(String serviceNumber, int success) {
        long now = System.currentTimeMillis();
        Timestamp dateTime = new Timestamp(now);

        try {
            Connection conn = nu.educom.MI6.Connector.createConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO login_attempts (service_num, time_login, success) VALUES( " + "?,?,?)");
            stmt.setString(1, serviceNumber);
            stmt.setTimestamp(2, dateTime);
            stmt.setInt(3, success);
            int updatedRowCount = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
