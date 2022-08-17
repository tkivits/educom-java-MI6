package nu.educom.MI6;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static List<String> isLoggedOn = new ArrayList<>();
  private static List<String> isBlacklisted = new ArrayList<>();
  public static void main(String[] args) {
    boolean loop = true;
    Scanner input = new Scanner(System.in);

    while(loop) {
      System.out.println("Please input your service number:");
      String serviceNumber = input.nextLine();

      if(serviceNumber.length() < 3) {
        serviceNumber = leftPadWithZeros(serviceNumber);
      }

      if(isBlacklisted.contains(serviceNumber)) {
        System.out.println("ERROR: You are blacklisted!");
        System.out.println(" ");
      } else if(isLoggedOn.contains(serviceNumber)) {
          System.out.println("You are already logged on.");
          System.out.println(" ");
        } else {
          serviceNumber = checkServiceNumber(serviceNumber);
          if(serviceNumber != null) {
            System.out.println("Please enter our super secret password:");
            String password = input.nextLine();
            checkPassword(password, serviceNumber);
          }
        }
      }
    }
  protected static boolean isNumeric(String num) {
    if(num == null) {
      return false;
    }
    try {
      int i = Integer.parseInt(num);
      if (i > 956) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
  protected static String leftPadWithZeros(String str) {
    return String.format("%0"+ (3 - str.length() )+"d%s",0 ,str);
  }
  protected static String checkServiceNumber(String serviceNumber) {
    try{
      if (!isNumeric(serviceNumber)) {
        throw new Exception("Input is incorrect.");
      }
      if (serviceNumber.length() > 3) {
        throw new Exception("Input is incorrect.");
      }
    } catch (Exception e) {
      System.out.println("Input is incorrect.");
      System.out.println(" ");
      return null;
    }
    return serviceNumber;
  }
  protected static void checkPassword(String password, String serviceNumber) {
    String actualPassword = "For ThE Royal QUEEN";
    if(actualPassword.equals(password)) {
      isLoggedOn.add(serviceNumber);
      System.out.println("You are now logged on, agent " + serviceNumber);
      System.out.println(" ");
    } else {
      isBlacklisted.add(serviceNumber);
      System.out.println("You are now blacklisted!");
      System.out.println(" ");
    }
  }
}