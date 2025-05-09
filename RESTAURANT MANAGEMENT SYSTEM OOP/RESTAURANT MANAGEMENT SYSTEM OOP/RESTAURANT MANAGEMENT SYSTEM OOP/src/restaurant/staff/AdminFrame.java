package restaurant.staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminFrame extends JFrame {
    JPanel panel = new JPanel(new GridLayout(3, 3));

    public AdminFrame() {
        String[] buttonLabels = {
                "View Order",
                "View Menu",
                "View Users",
                "Reports",
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openFrame(label);
                }
            });
            panel.add(button);
        }

        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void openFrame(String label) {
        if (label.equals("View Menu")) {
            MealsViewerFrame mealsFrame = new MealsViewerFrame();
            mealsFrame.setVisible(true);
        } else if (label.equals("View Users")) {
            UsersViewerFrame usersFrame = new UsersViewerFrame();
            usersFrame.setVisible(true);
        } else if (label.equals("View Order")) {
            OrderViewerFrame orderFrame = new OrderViewerFrame();
            orderFrame.setVisible(true);
        } else if (label.equals("Reports")) {
            ReportsFrame reportsFrame = new ReportsFrame();
            reportsFrame.setVisible(true);
        }
    }
}
