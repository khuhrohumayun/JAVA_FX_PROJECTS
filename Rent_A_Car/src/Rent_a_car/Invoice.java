package Rent_a_car ; 

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Invoice {

	JFrame frame;
    private int cnic;
    private String name;
    private int age;
    private int contact;
    private String address;
    private String email;
    private String mk;
    private String md;
    private String yr;
    private String frm;
    private String pd;
	private int price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice window = new Invoice( 0,"" , 0, 0, "", "" ,  "" ,  "" , "", "" , "",0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Invoice(int cnic, String name, int age, int contact, String address, String email, String mk, String md, String yr, String frm, String pd,int price) {
		this.cnic = cnic;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.mk = mk;
        this.md = md;
        this.yr = yr;
        this.frm = frm;
        this.pd = pd;
        this.price = price ; 
		initialize();
	}
      
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 398);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 397, 52);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Rent_A_Car");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(150, 11, 108, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Invoice");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(176, 27, 59, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(0, 50, 201, 313);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CNIC: " + cnic);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 46, 180, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Name: " + name);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(10, 77, 180, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Age: " + age);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_1.setBounds(10, 112, 180, 14);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Address: " + address);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2.setBounds(10, 144, 180, 14);
		panel_1.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("Contact: " + contact);
		lblNewLabel_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2_1.setBounds(10, 169, 180, 14);
		panel_1.add(lblNewLabel_2_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1 = new JLabel("Email id: " + email);
		lblNewLabel_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2_1_1.setBounds(10, 202, 180, 14);
		panel_1.add(lblNewLabel_2_1_1_2_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 255, 255));
		panel_3.setBounds(10, 256, 181, 46);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Total: " + price);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(51, 102, 204));
		lblNewLabel_3.setBounds(10, 21, 137, 14);
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 204));
		panel_2.setBounds(201, 50, 196, 313);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("MAKE: " + mk);
		lblNewLabel_2_2.setBounds(10, 39, 176, 14);
		panel_2.add(lblNewLabel_2_2);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2_2_1 = new JLabel("MODEL: " + md);
		lblNewLabel_2_2_1.setBounds(10, 77, 176, 14);
		panel_2.add(lblNewLabel_2_2_1);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Year: " + yr);
		lblNewLabel_2_2_1_1.setBounds(10, 116, 176, 14);
		panel_2.add(lblNewLabel_2_2_1_1);
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("From: " + frm);
		lblNewLabel_2_2_1_1_1.setBounds(10, 153, 176, 14);
		panel_2.add(lblNewLabel_2_2_1_1_1);
		lblNewLabel_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2_2_1_1_1_1 = new JLabel("Period: " + pd);
		lblNewLabel_2_2_1_1_1_1.setBounds(10, 191, 176, 14);
		panel_2.add(lblNewLabel_2_2_1_1_1_1);
		lblNewLabel_2_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnBook = new JButton("Confirm");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"Your car : " + md + " "+ " Booked Succesfully" + " "  + "Thank you ") ; 
			
			Record rc = new Record( cnic, name ,age, contact, address, email ,  mk ,  md , yr , frm, pd,price);
		       rc.setVisible(true); 
			
			}
		});
		btnBook.setBounds(83, 256, 89, 33);
		panel_2.add(btnBook);
		btnBook.setForeground(new Color(204, 51, 0));
		btnBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBook.setBackground(new Color(127, 255, 212));
		
		Record rc = new Record(cnic, name, age, contact, address, email, mk, md, yr, frm, pd, price);
		rc.setVisible(true);

	}
}
