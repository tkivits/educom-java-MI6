package nu.educom.MI6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Main {

  private static List<String> isLoggedOn = new ArrayList<>();
  private static List<String> isBlacklisted = new ArrayList<>();

  public static void main(String[] args) {
    JFrame jFrame = new JFrame();
    Scanner input = new Scanner(System.in);

    boolean loop = true;

    while (loop) {
      boolean valid = true;

      System.out.println("Please input your service number:");
      String serviceNumber = input.nextLine();

      if (serviceNumber.length() < 3) {
        serviceNumber = Utility.leftPadWithZeros(serviceNumber);
      }
      if (!Utility.isNumeric(serviceNumber)) {
        valid = false;
      }
      if (!Utility.isInRange(serviceNumber, 1, 956)) {
        valid = false;
      }
      if (isBlacklisted.contains(serviceNumber)) {
        valid = false;
      }
      if (isLoggedOn.contains(serviceNumber)) {
        JOptionPane.showMessageDialog(jFrame, "You are already logged in, agent:" + serviceNumber);
      }
      if (!Model.checkServiceNumber(serviceNumber)) {
        valid = false;
      }
      if (!valid) {
        JOptionPane.showMessageDialog(jFrame, "ACCESS DENIED", "!!!", JOptionPane.ERROR_MESSAGE);
        continue;
      }

      System.out.println("Please enter our super secret password:");
      String password = input.nextLine();

      if (!Model.checkPassword(password)) {
        valid = false;
      }
      if(!valid) {
        JOptionPane.showMessageDialog(jFrame, "ACCESS DENIED", "!!!", JOptionPane.ERROR_MESSAGE);
        isBlacklisted.add(serviceNumber);
      } else {
        JOptionPane.showMessageDialog(jFrame, "WELCOME AGENT: " + serviceNumber);
        isLoggedOn.add(serviceNumber);
      }
    }
  }
}
