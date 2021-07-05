package doctor;

import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPasswordField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;


import main.MainPage;
import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class DoctorLogin {

	private JFrame frame;
	private JLayeredPane layeredPane_1;
	private JPanel MyDetails;
	private JPanel ViewAppointments;
	private JPanel UpdatePatientDetails;
	private JPanel AvailableOrgan;
	private JPanel ChangePassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	public String userName=null,organ;
	public String Name=null,categorie=null,address=null;
	public String doctorid=null;
	private JComboBox<String> comboBox;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
	private JTextPane textPane,textPane_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JScrollPane scrollPane;
	private JTable Appointments;
	private JTable OrganTable;
	
	static Connection connection=null;
	static String databaseName="doctorpatientportal";
	static String url="jdbc:mysql://localhost:3306/"+databaseName ;
	static String username="root";
	static String password="Mylaptop@99";
	private JTable TreatmentHistory;
	protected String strDate;

	/**
	 * Create the application.
	 */
	public DoctorLogin(String userName) {
		initialize();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		this.userName=userName;
		getDoctorInfo();
	}
	
	private int checkValidity(String str)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=con.prepareStatement("");
			rs=ps.executeQuery(str);
			if(rs.next())
				return 1;
			else 
				return 0;
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			}
		return 0;
	}
	
	private void displayTable(JTable table,String query)
    {
        PreparedStatement ps1;
        ResultSet rs1;
        try
        {   
        	Class.forName("com.mysql.jdbc.Driver");
        	connection = DriverManager.getConnection(url, username , password);
        	System.out.println("Connection Established...");
                                         ps1 = connection.prepareStatement("");
                                         rs1= ps1.executeQuery(query);
                                         table.setModel(DbUtils.resultSetToTableModel(rs1));
        } catch(Exception e)  {
        		JOptionPane.showMessageDialog(null,e);
        }
    }
	
	public void getDoctorInfo()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=con.prepareStatement("");
			String query="Select * from doctorlogin where username='"+userName+"'";
			rs=ps.executeQuery(query);
			while(rs.next())
			{
				textPane.setText(rs.getString("address"));
				textField_1.setText(rs.getString("doctorid"));
				textField.setText(rs.getString("Name"));
				textField_2.setText(rs.getString("categorie"));
				textField_3.setText(rs.getString("username"));
			}
			//System.out.println(Name+" "+doctorid+" "+categorie+" "+address+" "+userName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void switchPanels(JPanel panel)
	{
		layeredPane_1.removeAll();
		layeredPane_1.add(panel);
		layeredPane_1.repaint();
		layeredPane_1.revalidate();
	}
	
	public void showPass(JRadioButton rb, JPasswordField pf)
	{
			if(rb.isSelected())
			{
				pf.setEchoChar((char)0);
			}
			else
			{
				pf.setEchoChar('*');
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon logoimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\doctoricon.png");
		
		frame = new JFrame("Doctor Window");
		frame.setBounds(100, 100, 860, 552);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(logoimg.getImage());
		
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 51, 834, 463);
		frame.getContentPane().add(layeredPane_1);
		layeredPane_1.setLayout(new CardLayout(0, 0));
		
		MyDetails = new JPanel();
		layeredPane_1.add(MyDetails, "name_256336098725500");
		MyDetails.setLayout(null);
		
		ImageIcon doc=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\doctor2.jpg");
		JLabel lblNewLabel = new JLabel(doc);
		lblNewLabel.setBounds(0, 40, 301, 389);
		MyDetails.add(lblNewLabel);
		
		JLabel lblInformation = new JLabel("Personal Information");
		lblInformation.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblInformation.setBounds(443, 0, 251, 55);
		MyDetails.add(lblInformation);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		lblName.setBounds(317, 76, 72, 30);
		MyDetails.add(lblName);
		
		JLabel lblNewLabel_1 = new JLabel("Categorie");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(317, 310, 99, 30);
		MyDetails.add(lblNewLabel_1);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddress.setBounds(317, 201, 99, 30);
		MyDetails.add(lblAddress);
		
		textPane = new JTextPane();
		textPane.setText(address);
		textPane.setEditable(false);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPane.setBounds(572, 201, 231, 79);
		MyDetails.add(textPane);
		
		JLabel lblExperience = new JLabel("User Name");
		lblExperience.setFont(new Font("Arial", Font.BOLD, 20));
		lblExperience.setBounds(317, 369, 121, 30);
		MyDetails.add(lblExperience);
		
		textField = new JTextField("");
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setText(Name);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(572, 75, 231, 35);
		MyDetails.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("");
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setText(doctorid);
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setBounds(572, 134, 231, 35);
		MyDetails.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					switchPanels(ChangePassword);
				}
			}
		});
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ChangePassword);
			}
		});
		btnChangePassword.setFont(new Font("Arial", Font.BOLD, 18));
		btnChangePassword.setBounds(453, 425, 231, 30);
		MyDetails.add(btnChangePassword);
		
		textField_3 = new JTextField("");
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_3.setText(categorie);
		textField_3.setBackground(new Color(255, 255, 255));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(572, 369, 231, 35);
		MyDetails.add(textField_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(317, 41, 487, 2);
		MyDetails.add(separator);
		
		textField_2 = new JTextField("");
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(572, 310, 231, 35);
		MyDetails.add(textField_2);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Arial", Font.BOLD, 20));
		lblDoctorId.setBounds(317, 135, 109, 30);
		MyDetails.add(lblDoctorId);
		
		ViewAppointments = new JPanel();
		layeredPane_1.add(ViewAppointments, "name_256339799116200");
		ViewAppointments.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Patient Appointments");
		lblNewLabel_4.setBounds(315, 0, 232, 40);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 22));
		ViewAppointments.add(lblNewLabel_4);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(187, 38, 490, 2);
		ViewAppointments.add(separator_4);
		
		JLabel lblEnterDateTo = new JLabel("Enter date ");
		lblEnterDateTo.setBounds(265, 44, 108, 33);
		lblEnterDateTo.setFont(new Font("Arial", Font.BOLD, 18));
		ViewAppointments.add(lblEnterDateTo);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 112, 814, 341);
		ViewAppointments.add(scrollPane_2);
		
		Appointments = new JTable();
		scrollPane_2.setViewportView(Appointments);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(436, 50, 152, 19);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		ViewAppointments.add(dateChooser);
		
		JButton btnNewButton_7 = new JButton("Show Appointment");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					strDate = dateFormat.format(dateChooser.getDate());
					displayTable(Appointments,"select * from appointment where DoctorName='"+textField.getText()+"' and Date='"+strDate+"' ");
					
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnNewButton_7.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_7.setBounds(288, 75, 268, 27);
		ViewAppointments.add(btnNewButton_7);
		
		UpdatePatientDetails = new JPanel();
		layeredPane_1.add(UpdatePatientDetails, "name_256342438342000");
		UpdatePatientDetails.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Update Details");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3.setBounds(326, 10, 161, 33);
		UpdatePatientDetails.add(lblNewLabel_3);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(224, 41, 375, 2);
		UpdatePatientDetails.add(separator_3);
		
		JLabel lblPatientId = new JLabel("Patient User Name");
		lblPatientId.setFont(new Font("Arial", Font.BOLD, 16));
		lblPatientId.setBounds(224, 53, 151, 20);
		UpdatePatientDetails.add(lblPatientId);
		
		JLabel lblTreatmentFor = new JLabel("Treatment For");
		lblTreatmentFor.setFont(new Font("Arial", Font.BOLD, 16));
		lblTreatmentFor.setBounds(224, 95, 127, 20);
		UpdatePatientDetails.add(lblTreatmentFor);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 16));
		lblDescription.setBounds(224, 132, 100, 27);
		UpdatePatientDetails.add(lblDescription);
		
		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					updatePatientInfo();
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(431, 53, 168, 31);
		UpdatePatientDetails.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					updatePatientInfo();
			}
		});
		textField_6.setColumns(10);
		textField_6.setBounds(431, 94, 168, 28);
		UpdatePatientDetails.add(textField_6);
		
		textPane_1 = new JTextPane();
		textPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					updatePatientInfo();
			}
		});
		textPane_1.setBounds(357, 132, 242, 94);
		UpdatePatientDetails.add(textPane_1);
		
		JButton btnNewButton_5 = new JButton("Update");
		btnNewButton_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String PUN=textField_5.getText();
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					updatePatientInfo();
					displayTable(TreatmentHistory,"select TreatmentFor,Description from patientlogin where userName='"+PUN+"'");
				}
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PUN=textField_5.getText();
					updatePatientInfo();		
					displayTable(TreatmentHistory,"select TreatmentFor,Description from patientlogin where userName='"+PUN+"'");
			}
		});
		btnNewButton_5.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_5.setBounds(224, 193, 121, 33);
		UpdatePatientDetails.add(btnNewButton_5);
		
		JLabel lblPatientHistory = new JLabel("Patient Treatment History");
		lblPatientHistory.setFont(new Font("Arial", Font.BOLD, 18));
		lblPatientHistory.setBounds(23, 236, 227, 33);
		UpdatePatientDetails.add(lblPatientHistory);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(23, 266, 366, 2);
		UpdatePatientDetails.add(separator_5);
		
		JButton btnNewButton_6 = new JButton("Check Treatment History");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PUN=textField_5.getText();
				if(textField_5.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Enter patient username");
				else
					displayTable(TreatmentHistory,"select TreatmentFor, Description from patientlogin where userName='"+PUN+"'");
			}
		});
		btnNewButton_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String PUN=textField_5.getText();
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(textField_5.getText().length()==0)
						JOptionPane.showMessageDialog(null, "Enter patient username");
					else
						displayTable(TreatmentHistory,"select TreatmentFor, Description from patientlogin where userName='"+PUN+"'");
				}
			}
		});
		btnNewButton_6.setFont(new Font("Arial", Font.BOLD, 13));
		btnNewButton_6.setBounds(605, 55, 202, 33);
		UpdatePatientDetails.add(btnNewButton_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 279, 785, 174);
		UpdatePatientDetails.add(scrollPane_1);
		
		TreatmentHistory = new JTable();
		scrollPane_1.setViewportView(TreatmentHistory);
		
		AvailableOrgan = new JPanel();
		layeredPane_1.add(AvailableOrgan, "name_256344902808500");
		AvailableOrgan.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Available Organs");
		lblNewLabel_2.setBounds(339, 9, 195, 36);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		AvailableOrgan.add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(261, 43, 360, 2);
		separator_1.setBackground(Color.DARK_GRAY);
		AvailableOrgan.add(separator_1);
		
		JLabel lblSearchOrgan = new JLabel("Search Organ");
		lblSearchOrgan.setBounds(261, 55, 128, 28);
		lblSearchOrgan.setFont(new Font("Arial", Font.BOLD, 18));
		AvailableOrgan.add(lblSearchOrgan);
		
		comboBox = new JComboBox<>();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				getOrganDetail();
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrganDetail();
			}
		});
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Organ","Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
		comboBox.setBounds(475, 55, 146, 36);
		AvailableOrgan.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 814, 345);
		AvailableOrgan.add(scrollPane);
		
		OrganTable = new JTable();
		scrollPane.setViewportView(OrganTable);
		
		ChangePassword = new JPanel();
		layeredPane_1.add(ChangePassword, "name_493709910759000");
		ChangePassword.setLayout(null);
		
		JLabel label = new JLabel("Update Password");
		label.setFont(new Font("Arial", Font.BOLD, 24));
		label.setBounds(328, 0, 216, 39);
		ChangePassword.add(label);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(221, 37, 439, 2);
		ChangePassword.add(separator_2);
		
		JLabel label_1 = new JLabel("Old Password");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(175, 126, 130, 27);
		ChangePassword.add(label_1);
		
		JLabel label_2 = new JLabel("New Password");
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(175, 191, 130, 27);
		ChangePassword.add(label_2);
		
		JLabel label_3 = new JLabel("Confirm New Password");
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(175, 261, 192, 27);
		ChangePassword.add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField.setBounds(380, 127, 132, 27);
		ChangePassword.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField_1.setBounds(380, 192, 132, 27);
		ChangePassword.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField_2.setBounds(380, 262, 132, 27);
		ChangePassword.add(passwordField_2);
		
		JButton button = new JButton("Save Changes");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBounds(263, 324, 148, 39);
		ChangePassword.add(button);
		
		JButton button_1 = new JButton("Discard");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.setText("");
					passwordField_1.setText("");
					passwordField_2.setText("");
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 16));
		button_1.setBounds(466, 324, 142, 39);
		ChangePassword.add(button_1);
		
		JRadioButton rb1 = new JRadioButton("Show/Hide password");
		rb1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField.setEchoChar((char)0);
			}
		});
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rb1,passwordField);
			}
		});
		rb1.setFont(new Font("Arial", Font.BOLD, 14));
		rb1.setBounds(538, 129, 177, 21);
		ChangePassword.add(rb1);
		
		JRadioButton rb2 = new JRadioButton("Show/Hide password");
		rb2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_1.setEchoChar((char)0);
			}
		});
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rb2,passwordField_1);
			}
		});
		rb2.setFont(new Font("Arial", Font.BOLD, 14));
		rb2.setBounds(538, 194, 177, 21);
		ChangePassword.add(rb2);
		
		JRadioButton rb3 = new JRadioButton("Show/Hide password");
		rb3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_2.setEchoChar((char)0);
			}
		});
		rb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rb3,passwordField_2);
			}
		});
		rb3.setFont(new Font("Arial", Font.BOLD, 14));
		rb3.setBounds(538, 264, 177, 21);
		ChangePassword.add(rb3);
		
		
		JButton btnNewButton = new JButton("My Details");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					switchPanels(MyDetails);
					getDoctorInfo();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(MyDetails);
				getDoctorInfo();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(10, 10, 157, 31);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Appointments");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					switchPanels(ViewAppointments);
					displayTable(Appointments,"select * from appointment where DoctorName='"+textField.getText()+"'");
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ViewAppointments);
				displayTable(Appointments,"select * from appointment where DoctorName='"+textField.getText()+"'");
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_1.setBounds(165, 10, 177, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Patient Details");
		btnNewButton_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					switchPanels(UpdatePatientDetails);
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(UpdatePatientDetails);
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_2.setBounds(337, 10, 201, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Available Organs");
		btnNewButton_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					switchPanels(AvailableOrgan);
					displayTable(OrganTable,"Select * from organdetail");
				}
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(AvailableOrgan);
				displayTable(OrganTable,"Select * from organdetail");
				
			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_3.setBounds(533, 10, 169, 31);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Logout");
		btnNewButton_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully Logged Out");
				frame.dispose();
				@SuppressWarnings("unused")
				MainPage mainpage=new MainPage();
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully Logged Out");
				frame.dispose();
				@SuppressWarnings("unused")
				MainPage mainpage=new MainPage();
			}
		});
		btnNewButton_4.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_4.setBounds(699, 10, 145, 31);
		frame.getContentPane().add(btnNewButton_4);
	}
	
	private void updatePatientInfo() {
		if(textField_5.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Enter patient user name");
		else if(textField_6.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Enter treatment detail");
		else if(textPane_1.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Enter treatment description");
		else
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=con.prepareStatement("");
			String check="select * from patientlogin where userName='"+textField_5.getText()+"'";
			rs=ps.executeQuery(check);
			if(rs.next())
			{
				String query="update patientlogin set TreatmentFor='"+textField_6.getText()+"',Description='"+textPane_1.getText()+"' where userName='"+textField_5.getText()+"'";
				ps.executeUpdate(query); 
				displayTable(TreatmentHistory,"Select userName,Name,Age,TreatmentFor,Description from patientlogin where userName='"+textField_5.getText()+"'");
				textField_5.setText(null);
				textField_6.setText(null);
				textPane_1.setText(null);
			}
			else
			{
					JOptionPane.showMessageDialog(null, "Invalid user name");
			}
			}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		}
	}
	
	private void getOrganDetail() {
		organ = (String)comboBox.getSelectedItem();
		int x=checkValidity("select * from organdetail where Organ='"+organ+"'");
		if(x==0)
			JOptionPane.showMessageDialog(null, "Sorry! Organ not available");
		else
		{
			try {
					displayTable(OrganTable,"select * from organdetail where Organ='"+organ+"'");
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void changePassword() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=con.prepareStatement("");
			if(passwordField_2.getText().length()==0 && passwordField_1.getText().length()==0 && passwordField.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null, "Password Field('s) are empty" );
			}
			else
			{
				String oldPass=null;
				String getInfo="Select password from doctorlogin where doctorid='"+doctorid+"'";
				rs=ps.executeQuery(getInfo);
				while(rs.next())
				{
					oldPass=rs.getString("password");
				}
				if(passwordField_2.getText().equals(passwordField_1.getText()) && passwordField.getText().equals(oldPass))
				{
					String passwordQuery= "update doctorlogin set password='"+passwordField_1.getText()+"' where doctorid='"+doctorid+"' ";
					ps.executeUpdate(passwordQuery);
					passwordField.setText("");
					passwordField_1.setText(null);
					passwordField_2.setText(null);
					JOptionPane.showMessageDialog(null,"Password Successfully Changed");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Confirm Password");
				}
			}
		}
		catch(Exception exc)
		{
			System.out.println("Exception :"+exc);
		}
	}
}

