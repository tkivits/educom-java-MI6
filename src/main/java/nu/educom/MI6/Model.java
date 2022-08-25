package nu.educom.MI6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
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

    protected static boolean checkPassword(String password, String serviceNumber) {

        String actualPassword = null;
        int active = 0;

        try {
            ResultSet rSet = nu.educom.MI6.Agent.getAgentByID(serviceNumber);
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

    protected static LocalDateTime getLockoutTime(String serviceNumber) {
        List<nu.educom.MI6.LoginAttempt> loginAttempts = nu.educom.MI6.LoginAttempt.getLoginAttempts(serviceNumber);

        int countAttempts = loginAttempts.size();
        if (countAttempts == 0) {
            return null;
        }

        int amount = (int) Math.pow(2.0, countAttempts - 1.0);
        nu.educom.MI6.LoginAttempt lastAttempt = loginAttempts.get(countAttempts - 1);
        LocalDateTime lockoutTime = lastAttempt.getDateTime().toLocalDateTime().plusMinutes(amount);
        if (lockoutTime.isAfter(LocalDateTime.now())) {
            return lockoutTime;
        }
        return null;
    }
}
