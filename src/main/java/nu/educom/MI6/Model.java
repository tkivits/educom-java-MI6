package nu.educom.MI6;

import java.sql.ResultSet;
import java.sql.SQLException;

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

        try {
            ResultSet rSet = nu.educom.MI6.Agent.getAgentByID(serviceNumber);
            rSet.next();
            actualPassword = rSet.getString(3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (password.equals(actualPassword)) {
            return true;
        } else {
            return false;
        }
    }

}
