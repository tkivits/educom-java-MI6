package nu.educom.MI6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAttempt {

    private String serviceNumber;
    private Timestamp dateTime;
    private boolean success;
    private int id;

    public LoginAttempt(int id, String serviceNumber, Timestamp dateTime, boolean success) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.dateTime = dateTime;
        this.success = success;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

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

    public static List<LoginAttempt> getLoginAttempts(String serviceNumber) {
        List<LoginAttempt> loginAttempts = new ArrayList<>();

        try {
            Connection conn = nu.educom.MI6.Connector.createConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login_attempts WHERE service_num = ? AND ID > IFNULL((SELECT MAX(id) FROM login_attempts WHERE success = 1 AND service_num = ?), 0)");
            stmt.setString(1, serviceNumber);
            stmt.setString(2, serviceNumber);
            ResultSet rSet = stmt.executeQuery();
            while (rSet.next()) {
                loginAttempts.add(new LoginAttempt(rSet.getInt("ID"), rSet.getString("service_num"), rSet.getTimestamp("time_login"), rSet.getBoolean("success")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginAttempts;
    }
}
