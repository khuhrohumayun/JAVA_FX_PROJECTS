package Rent_a_car;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants; 
public class Home {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
    public  JComboBox comboBox ; 
    public  JComboBox comboBox_1 ;
    public JComboBox comboBox_2 ; 
    public JComboBox comboBox_4 ; 
    public JComboBox comboBox_5 ; 
    public int cnic , age , contact ,price ; 
    public String first_name , last_name , email ,  address ; 
    public String mk , md , yr ,frm , prd ;  
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 204));
		frame.setBounds(100, 100, 413, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Car Reservation System");   
		   
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 397, 42);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rent_A_Car");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(164, 11, 108, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setForeground(new Color(204, 51, 102));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(77, 53, 76, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CNIC:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(21, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("First Name :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(21, 128, 66, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Last Name :");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1.setBounds(21, 161, 66, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Age :");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_1.setBounds(21, 196, 46, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Address:");
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2.setBounds(21, 227, 66, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("Contact:");
		lblNewLabel_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2_1.setBounds(21, 264, 66, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1 = new JLabel("Email id : ");
		lblNewLabel_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_2_1_1.setBounds(21, 300, 82, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1_2_1_1);
		
		textField = new JTextField();
		textField.setBounds(91, 92, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 125, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(91, 158, 86, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 193, 86, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(91, 224, 86, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(91, 261, 86, 20);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(91, 297, 86, 20);
		frame.getContentPane().add(textField_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 204));
		panel_1.setBounds(208, 40, 189, 321);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("MAKE:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(10, 50, 46, 14);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("MODEL:");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2_1.setBounds(10, 87, 46, 14);
		panel_1.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Year:");
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2_1_1.setBounds(10, 122, 39, 14);
		panel_1.add(lblNewLabel_2_2_1_1);
		
		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("From:");
		lblNewLabel_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2_1_1_1.setBounds(10, 155, 39, 14);
		panel_1.add(lblNewLabel_2_2_1_1_1);
		
		JLabel lblNewLabel_2_2_1_1_1_1 = new JLabel("Period :");
		lblNewLabel_2_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2_1_1_1_1.setBounds(10, 188, 46, 14);
		panel_1.add(lblNewLabel_2_2_1_1_1_1);
		
		JButton btnBook = new JButton("Book\r\n");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			mk = comboBox.getSelectedItem().toString().trim() ; 
		    md = comboBox_1.getSelectedItem().toString().trim() ; 
		    yr = comboBox_2.getSelectedItem().toString().trim(); 	
		    frm = comboBox_4.getSelectedItem().toString().trim() ; 
			prd = comboBox_5.getSelectedItem().toString().trim() ; 
			if(md.equals("Land Cruiser") ) {
				
				price = 200000 ; 
				
			}
			
			else if(md.equals("Corolla") ) {
				
				price = 60000 ; 
				
			}
else if(md.equals("Civic") ) {
				
				price = 100000 ; 
				
			}
else if(md.equals("Brv") ) {
	
	price = 150000 ; 
	
}
else if (md.equals("Sportage")) {
	price = 170000 ; 
}
else if (md.equals("Stonic")) {
	price = 120000 ; 
}
else if (md.equals("Alto")) {
	price = 45000 ; 
}
else if (md.equals("Swift")) {
	price = 120000 ; 
}

			
			Invoice inc = new Invoice( cnic,first_name+last_name , age, contact, address, email ,  mk ,  md , yr , frm , prd,price);
           inc.frame.setVisible(true);
           
           
		   }
		});
		btnBook.setForeground(Color.WHITE);
		btnBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBook.setBackground(new Color(127, 255, 212));
		btnBook.setBounds(50, 236, 101, 37);
		panel_1.add(btnBook);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Toyota", "Suzukie", "Honda ", "Kia"}));
		comboBox.setBackground(new Color(0, 204, 204));
		comboBox.setBounds(53, 46, 86, 22);
		panel_1.add(comboBox);
		
		 comboBox_1 = new JComboBox();
		 comboBox_1.setForeground(new Color(255, 255, 255));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Corolla", "Civic", "Land Cruiser", "Brv", "Sportage", "Stonic", "Alto ", "Swift"}));
		comboBox_1.setBackground(new Color(51, 204, 204));
		comboBox_1.setBounds(53, 83, 86, 22);
		panel_1.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setForeground(new Color(255, 255, 255));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"","2025",  "2024", "2023", "2022", "2021", "2020"}));
		comboBox_2.setBackground(new Color(51, 204, 204));
		comboBox_2.setBounds(53, 118, 86, 22);
		panel_1.add(comboBox_2);
		
		 comboBox_4 = new JComboBox();
		 comboBox_4.setForeground(new Color(255, 255, 255));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"", "NEW Motors ", "Sania Motors ", "Sameen Motors"}));
		comboBox_4.setBackground(new Color(51, 204, 204));
		comboBox_4.setBounds(53, 151, 86, 22);
		panel_1.add(comboBox_4);
		
	    comboBox_5 = new JComboBox();
	    comboBox_5.setForeground(new Color(255, 255, 255));
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"", "7 days", "14 days", "1 month ", "2 month", "3 month"}));
		comboBox_5.setBackground(new Color(51, 204, 204));
		comboBox_5.setBounds(53, 184, 86, 22);
		panel_1.add(comboBox_5);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reserve\r\n");
		lblNewLabel_1_1.setForeground(new Color(204, 51, 51));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1_1.setBounds(53, 11, 76, 28);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				cnic = (int) Double.parseDouble(textField.getText()) ; 
				age = (int) Double.parseDouble(textField_3.getText()) ; 
				contact = (int) Double.parseDouble(textField_5.getText()) ;
				
				first_name = textField_1.getText() ; 
				last_name = textField_2.getText() ; 
				address = textField_4.getText() ; 
				email = textField_6.getText() ; 
               
 
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(153, 51, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(88, 325, 89, 23);
		frame.getContentPane().add(btnNewButton);

	
	
	
	      
	}



}
