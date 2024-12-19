package GUI;

import DBConnectivity.DBOperations;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HowAreU extends JFrame {
    JButton exit, login, clear;
    JPanel titlePnl, componentPnl;
    JLabel pageLbl;
    JTextField userNameField;
    JPasswordField passwordField;
    ImageIcon imageIcon;
    Image image;

    public HowAreU() {

        // Initializing the frame
        setTitle("SpongeBob SquarePants");
        setSize(380, 500);
        setLocation(600, 120);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // set frame icon
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/spong.png"));
        image = imageIcon.getImage();
        setIconImage(image);

        // Title panel and its title
        titlePnl = createPnl(titlePnl, 5, 70);
        createTitleLbl(pageLbl);

        // Components panel and the components
        componentPnl = createPnl(componentPnl, 80, 380);

        createLbl(pageLbl, "USER_NAME :", 100);
        createLbl(pageLbl, "PASSWORD :", 160);

        userNameField = createTestField(userNameField, 100);
        passwordField = createPasswordField(passwordField, 160);

        exit = createButtons(exit, 20, "EXIT");
        clear = createButtons(clear, 130, "CLEAR");
        login = createButtons(login, 240, "LOGIN");

        setVisible(true);
    }

    private void createLbl(JLabel lbl, String text, int y_axis) {
        lbl = new JLabel(text);
        lbl.setBounds(20, y_axis, 140, 30);
        lbl.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        componentPnl.add(lbl);
    }

    private void createTitleLbl(JLabel lbl) {
        lbl = new JLabel("SpongeBob SquarePants");
        lbl.setFont(new Font("Arial", Font.BOLD, 25));
        lbl.setBounds(25, 10, 300, 50);
        lbl.setForeground(Color.GRAY);
        titlePnl.add(lbl, CENTER_ALIGNMENT);
    }

    private JPanel createPnl(JPanel pnl, int y_axis, int height) {
        pnl = new JPanel();
        pnl.setBackground(Color.cyan);
        pnl.setBounds(5, y_axis, 355, height);
        pnl.setLayout(null);
        add(pnl);

        return pnl;
    }

    private JTextField createTestField(JTextField field, int y_axis) {
        field = new JTextField();
        field.setBounds(180, y_axis, 160, 40);
        field.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        componentPnl.add(field);

        return field;
    }

    private JPasswordField createPasswordField(JPasswordField field, int y_axis) {
        field = new JPasswordField();
        field.setBounds(180, y_axis, 160, 40);
        field.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        componentPnl.add(field);

        return field;
    }

    private JButton createButtons(JButton btn, int x_axis, String text) {
        btn = new JButton(text);
        btn.setBounds(x_axis, 280, 100, 35);
        btn.setBackground(Color.GRAY);
        btn.setFocusPainted(false);
        btn.addActionListener(new buttonHandler());
        componentPnl.add(btn);

        return btn;
    }

    private void loginCheck()  {
        String userName = userNameField.getText();
        String password = passwordField.getText();

        if (userName.equalsIgnoreCase("omar") && password.equalsIgnoreCase("1234")){
            new page(userName, password);
        }else {
            JOptionPane.showMessageDialog(userNameField, "Invalid information");
        }
    }

    private void clearFields(){
        userNameField.setText("");
        passwordField.setText("");
    }

    private class buttonHandler extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                loginCheck();

            } else if (e.getSource() == clear) {
                clearFields();

            } else {
                System.exit(0);
            }
        }
    }

}
