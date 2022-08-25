package nu.educom.MI6;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
    protected static LocalDateTime getLockoutTime(String serviceNumber) {
        List<LoginModel> loginAttempts = null;
        try {
            loginAttempts = LoginModel.getLoginAttempts(serviceNumber);
        } catch (SQLException e) {
            e.getMessage();
        }

        int countAttempts = loginAttempts.size();
        if (countAttempts == 0) {
            return null;
        }

        int amount = (int) Math.pow(2.0, countAttempts - 1.0);
        LoginModel lastAttempt = loginAttempts.get(countAttempts - 1);
        LocalDateTime lockoutTime = lastAttempt.getDateTime().toLocalDateTime().plusMinutes(amount);
        if (lockoutTime.isAfter(LocalDateTime.now())) {
            return lockoutTime;
        }
        return null;
    }
}
