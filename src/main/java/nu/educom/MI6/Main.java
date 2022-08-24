package nu.educom.MI6;

import nu.educom.MI6.LoginAttempt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static JFrame frame = new JFrame("MI6");
  private static JLabel label = new JLabel("Please input your service number:", JLabel.CENTER);
  private static JLabel labelTwo = new JLabel("", JLabel.CENTER);
  private static JTextField tfs = new JTextField();
  private static JTextField tfp = new JTextField();
  private static JButton submitButton = new JButton("Submit");
  private static List<String> isLoggedOn = new ArrayList<>();

  public static void showFrame() {
    tfs.setHorizontalAlignment(SwingConstants.CENTER);
    tfp.setHorizontalAlignment(SwingConstants.CENTER);
    frame.setLayout(new GridLayout(5, 1));
    frame.add(label);
    frame.add(labelTwo);
    frame.add(tfs);
    frame.add(tfp);
    frame.add(submitButton);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
          String serviceNumber = tfs.getText();
          String password = tfp.getText();
          boolean valid = true;
          boolean validTwo = true;

          if (serviceNumber.length() < 3) {
            serviceNumber = nu.educom.MI6.Utility.leftPadWithZeros(serviceNumber);
          }
          if (!nu.educom.MI6.Model.checkServiceNumber(serviceNumber)) {
            valid = false;
          }
          if (valid && isLoggedOn.contains(serviceNumber)) {
            label.setText("You are already logged in, agent:" + serviceNumber);
            valid = false;
          }
          if(valid) {
            if (!nu.educom.MI6.Model.checkPassword(password, serviceNumber)) {
              LoginAttempt.insertLoginAttempt(serviceNumber, 0);
              LocalDateTime lockoutTime = nu.educom.MI6.Model.getLockoutTime(serviceNumber);
              label.setText("ACCESS DENIED");
              labelTwo.setText("You can login at: " + lockoutTime);
              tfs.setText("");
              tfp.setText("");
            } else {
              label.setText("WELCOME AGENT: " + serviceNumber);
              boolean hasLicense = nu.educom.MI6.Agent.getLicenseToKill(serviceNumber);
              if(hasLicense) {
                labelTwo.setText("You have a license to kill.");
              } else {
                labelTwo.setText("You do not have a license to kill");
              }
              LoginAttempt.insertLoginAttempt(serviceNumber, 1);
              isLoggedOn.add(serviceNumber);
            }
          } else {
            label.setText("ACCESS DENIED");
            tfs.setText("");
            tfp.setText("");
          }
        }
      }
    });
  }

  public static void main(String[] args) {

    showFrame();

  }
}
