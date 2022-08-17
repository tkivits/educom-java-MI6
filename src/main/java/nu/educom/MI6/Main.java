package nu.educom.MI6;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Please input your service number:");

    String serviceNumber = input.nextLine();
    String result = checkServiceNumber(serviceNumber);

    if(result != null) {
      System.out.println("Welcome agent " + result);
      System.out.println("Please enter our super secret password:");
    }
    }
  public static boolean isNumeric(String num) {
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
  public static String leftPadWithZeros(String str) {
    return String.format("%0"+ (3 - str.length() )+"d%s",0 ,str);
  }
  public static String checkServiceNumber(String serviceNumber) {
    if(serviceNumber.length() < 3) {
      serviceNumber = leftPadWithZeros(serviceNumber);
    }
    try{
      if (!isNumeric(serviceNumber)) {
        throw new Exception("Input is incorrect.");
      }
      if (serviceNumber.length() > 3) {
        throw new Exception("Input is incorrect.");
      }
    } catch (Exception e) {
      System.out.println("Input is incorrect.");
    }
    return serviceNumber;
  }
}