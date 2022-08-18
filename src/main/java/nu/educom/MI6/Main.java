package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Main {

  private static List<String> isLoggedOn = new ArrayList<>();
  private static List<String> isBlacklisted = new ArrayList<>();
  public static void main(String[] args) {
    boolean loop = true;
    JFrame accessFrame = new JFrame();
    Scanner input = new Scanner(System.in);

    while(loop) {
      System.out.println("Please input your service number:");
      String serviceNumber = input.nextLine();

      if(serviceNumber.length() < 3) {
        serviceNumber = leftPadWithZeros(serviceNumber);
      }

      if(isBlacklisted.contains(serviceNumber)) {
        JOptionPane.showMessageDialog(accessFrame, "ACCESS DENIED");
      } else if(isLoggedOn.contains(serviceNumber)) {
          System.out.println("You are already logged on.");
          System.out.println(" ");
        } else {
          serviceNumber = checkServiceNumber(serviceNumber);
          if(serviceNumber != null) {
            System.out.println("Please enter our super secret password:");
            String password = input.nextLine();
            if(checkPassword(password, serviceNumber)) {
              JOptionPane.showMessageDialog(accessFrame, "WELCOME AGENT: " + serviceNumber);
            } else {
              JOptionPane.showMessageDialog(accessFrame, "ACCESS DENIED");
            }
          } else {
            JOptionPane.showMessageDialog(accessFrame, "ACCESS DENIED");
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
      if (serviceNumber.contains("-") || serviceNumber.equals("000")) {
        throw new Exception("ACCESS DENIED");
      }
      if (!isNumeric(serviceNumber)) {
        throw new Exception("ACCESS DENIED");
      }
      if (serviceNumber.length() > 3) {
        throw new Exception("ACCESS DENIED");
      }
    } catch (Exception e) {
      return null;
    }
    return serviceNumber;
  }
  protected static boolean checkPassword(String password, String serviceNumber) {
    String actualPassword = "For ThE Royal QUEEN";
    if(actualPassword.equals(password)) {
      return true;
    } else {
      isBlacklisted.add(serviceNumber);
      return false;
    }
  }
}