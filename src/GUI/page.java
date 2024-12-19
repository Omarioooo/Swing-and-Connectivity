package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class page extends JFrame {
    JLabel userName;
    JLabel pass;
    Map<Integer, List<Integer>> boxMap = new HashMap<>();

    JComboBox<Integer> box1, box2;

    page(String name, String password) {
        setTitle("SpongeBob SquarePants");
        setSize(380, 500);
        setLocation(600, 120);
        setBackground(Color.blue);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        box1 = createBox(box1, 60);
        box2 = createBox(box2, 200);
        mappingBox();
        box1.addActionListener(e -> logicOfMapping());

//        userName = new JLabel(name);
//        userName.setBounds(50,125, 200, 60);
//        userName.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
//        add(userName);
//
//        pass = new JLabel(password);
//        pass.setBounds(50,250, 200, 60);
//        pass.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
//        add(pass);

        try {
            JPanel pnl = new TablePnl();
            pnl.setBounds(50, 150, 300, 220);
            add(pnl);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(box1, "how are U");
        }




        setVisible(true);
    }

    void mappingBox() {
        boxMap.put(1, Arrays.asList(2, 3, 4, 5));
        boxMap.put(2, Arrays.asList(1, 3, 4, 5));
        boxMap.put(3, Arrays.asList(1, 2, 4, 5));
        boxMap.put(4, Arrays.asList(1, 2, 3, 5));
        boxMap.put(5, Arrays.asList(1, 2, 3, 4));
    }

    void logicOfMapping() {
        int num = (int) box1.getSelectedItem();
        List<Integer> list = boxMap.get(num);
        box2.removeAllItems();
        for (Integer number : list)
            box2.addItem(number);
    }

    JComboBox<Integer> createBox(JComboBox<Integer> box, int x_axis) {
        Integer arr[] = {1, 2, 3, 4, 5};
        box = new JComboBox<Integer>(arr);
        box.setBounds(x_axis, 50, 120, 40);
        add(box);

        return box;

    }
}
