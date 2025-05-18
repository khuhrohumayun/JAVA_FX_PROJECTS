package Rent_a_car;

import javax.swing.*;
import java.awt.*;

public class Record {

    JFrame frame;

    public Record(int cnic, String name, int age, int contact, String address, String email, String mk, String md, String yr, String frm, String pd, int price) {
        initialize(cnic, name, age, contact, address, email, mk, md, yr, frm, pd, price);
    }

    private void initialize(int cnic, String name, int age, int contact, String address, String email, String mk, String md, String yr, String frm, String pd, int price) {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Car Booking Record");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(150, 10, 200, 30);
        frame.getContentPane().add(lblTitle);

        String[] labels = {
            "CNIC: " + cnic, "Name: " + name, "Age: " + age, 
            "Contact: " + contact, "Address: " + address, "Email: " + email,
            "Make: " + mk, "Model: " + md, "Year: " + yr,
            "From: " + frm, "Period: " + pd, "Price: " + price
        };

        int yPosition = 50;

        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lbl.setBounds(20, yPosition, 400, 20);
            frame.getContentPane().add(lbl);
            yPosition += 30;
        }

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(200, yPosition, 100, 30);
        btnClose.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(btnClose);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
