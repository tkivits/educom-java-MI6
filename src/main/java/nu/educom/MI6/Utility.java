package nu.educom.MI6;

public class Utility {
    protected static String leftPadWithZeros(String str) {
        return String.format("%0"+ (3 - str.length() )+"d%s",0 ,str);
    }
    protected static boolean isNumeric(String num) {
        if(num == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    protected static boolean isInRange(String num, int min, int max) {
        if(num == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(num);
            if (max < i || i < min) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
