package nu.educom.MI6;

import nu.educom.MI6.LoginAttempt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
            if (!Model.checkPassword(password, serviceNumber)) {
              LocalDateTime lockoutTime = nu.educom.MI6.Model.getLockoutTime(serviceNumber);
              if(LocalDateTime.now().isAfter(lockoutTime)) {
                try {
                  LoginAttempt.insertLoginAttempt(serviceNumber, 0);
                } catch (SQLException e) {
                  e.getMessage()
                }
              }
              label.setText("ACCESS DENIED");
              labelTwo.setText("You can login at: " + lockoutTime);
              tfs.setText("");
              tfp.setText("");
            } else {
              label.setText("WELCOME AGENT: " + serviceNumber);
              Timestamp licenseToKill = null;
              try {
                licenseToKill = Agent.getLicenseToKill(serviceNumber);
              } catch (SQLException e) {
                e.getMessage();
              }
              if (licenseToKill == null) {
                labelTwo.setText("You do not have a license to kill");
              } else {
                labelTwo.setText("You have a license to kill until: " + licenseToKill);
              }
              List<LoginAttempt> loginAttempts = LoginAttempt.getLoginAttempts(serviceNumber);
              JList<LoginAttempt> attemptList = new JList(loginAttempts.toArray());
              attemptList.setLayoutOrientation(JList.VERTICAL);
              attemptList.setVisibleRowCount(-1);
              frame.setLayout(new GridLayout(6, 1));
              frame.add(attemptList);
              tfs.setText("");
              tfp.setText("");
              try {
                LoginAttempt.insertLoginAttempt(serviceNumber, 1);
              } catch (SQLException e) {
                e.getMessage();
              }
              isLoggedOn.add(serviceNumber);
            }
          } else {
            labelTwo.setText("");
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
