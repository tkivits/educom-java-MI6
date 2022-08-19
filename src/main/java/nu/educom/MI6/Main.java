package nu.educom.MI6;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Main {
  private static JFrame frame = new JFrame("MI6");
  private static JLabel label = new JLabel("Please input your service number:", JLabel.CENTER);
  private static JTextField tfs = new JTextField();
  private static JTextField tfp = new JTextField();
  private static JButton submitButton = new JButton("Submit");
  private static List<String> isLoggedOn = new ArrayList<>();
  private static List<String> isBlacklisted = new ArrayList<>();

  public static void showFrame() {
    tfs.setHorizontalAlignment(SwingConstants.CENTER);
    tfp.setHorizontalAlignment(SwingConstants.CENTER);
    frame.setLayout(new GridLayout(4, 1));
    frame.add(label);
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

          if (serviceNumber.length() < 3) {
            serviceNumber = Utility.leftPadWithZeros(serviceNumber);
          }
          if (!Model.checkServiceNumber(serviceNumber)) {
            valid = false;
          }
          if (isBlacklisted.contains(serviceNumber)) {
            valid = false;
          }
          if (isLoggedOn.contains(serviceNumber)) {
            label.setText("You are already logged in, agent:" + serviceNumber);
          }
          if (!Model.checkPassword(password)) {
            valid = false;
          }
          if(!valid) {
            label.setText("ACCESS DENIED");
            tfs.setText("");
            tfp.setText("");
            isBlacklisted.add(serviceNumber);
          } else {
            label.setText("WELCOME AGENT: " + serviceNumber);
            tfs.setText("");
            tfp.setText("");
            isLoggedOn.add(serviceNumber);
          }
        }
      }
    });
  }

  public static void main(String[] args) {

      showFrame();

  }
}
