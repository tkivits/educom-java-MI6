package nu.educom.MI6;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Please input your service number:");

    try {
      int serviceNumber = input.nextInt();
      if(String.valueOf(serviceNumber).length() > 3) {
        System.out.println("Input is incorrect.");
      }
      if(String.valueOf(serviceNumber).length() < 3) {
        String paddedSerNum = String.format("%03d", serviceNumber);
      }
    } catch (Exception e) {
      System.out.println("Input is incorrect.");
    }
  }
}