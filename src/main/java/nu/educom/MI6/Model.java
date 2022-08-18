package nu.educom.MI6;

public class Model {
    protected static boolean checkServiceNumber(String serviceNumber) {
        try{
            if (serviceNumber.contains("-")) {
                throw new Exception("ACCESS DENIED");
            }
            if (serviceNumber.length() > 3) {
                throw new Exception("ACCESS DENIED");
            }
        } catch (Exception e) {
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
