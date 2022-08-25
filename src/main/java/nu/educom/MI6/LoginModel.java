package nu.educom.MI6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginModel {

    private String serviceNumber;
    private Timestamp dateTime;
    private boolean success;
    private int id;

    public LoginModel(int id, String serviceNumber, Timestamp dateTime, boolean success) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.dateTime = dateTime;
        this.success = success;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    protected static boolean checkPassword(String password, String serviceNumber) {

        String actualPassword = null;
        int active = 0;

        try {
            ResultSet rSet = AgentModel.getAgentByID(serviceNumber);
            rSet.next();
            actualPassword = rSet.getString(3);
            active = rSet.getInt(4);
            System.out.println(active);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (password.equals(actualPassword) && active == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void insertLoginAttempt(String serviceNumber, int success) throws SQLException {
        long now = System.currentTimeMillis();
        Timestamp dateTime = new Timestamp(now);
        Connection conn = null;

        try {
            conn = nu.educom.MI6.Connector.createConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO login_attempts (service_num, time_login, success) VALUES( " + "?,?,?)");
            stmt.setString(1, serviceNumber);
            stmt.setTimestamp(2, dateTime);
            stmt.setInt(3, success);
            int updatedRowCount = stmt.executeUpdate();
        } finally {
            conn.close();
        }
    }

    public static List<LoginModel> getLoginAttempts(String serviceNumber) throws SQLException {
        List<LoginModel> loginAttempts = new ArrayList<>();
        Connection conn = null;

        try {
            conn = nu.educom.MI6.Connector.createConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login_attempts WHERE service_num = ? AND ID > IFNULL((SELECT MAX(id) FROM login_attempts WHERE success = 1 AND service_num = ?), 0)");
            stmt.setString(1, serviceNumber);
            stmt.setString(2, serviceNumber);
            ResultSet rSet = stmt.executeQuery();
            while (rSet.next()) {
                loginAttempts.add(new LoginModel(rSet.getInt("ID"), rSet.getString("service_num"), rSet.getTimestamp("time_login"), rSet.getBoolean("success")));
            }
        } finally {
            conn.close();
        }
        return loginAttempts;
    }
}
