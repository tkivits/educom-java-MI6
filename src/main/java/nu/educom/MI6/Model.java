package nu.educom.MI6;

public class Model {
    protected static boolean checkServiceNumber(String serviceNumber) {
        if (!Utility.isNumeric(serviceNumber)) {
            return false;
        }
        if (!Utility.isInRange(serviceNumber, 1, 956)) {
            return false;
        }
        if (serviceNumber.contains("+")) {
            return false;
        }
        if (serviceNumber.length() > 3) {
            return false;
        }
        return true;
    }
    protected static boolean checkPassword(String password) {
        String actualPassword = "For ThE Royal QUEEN";
        if(password.equals(actualPassword)) {
            return true;
        } else {
            return false;
        }
    }

}
