package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.proteanit.sql.DbUtils;

//import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AdminLogin {
	
	

	

	static Connection connection=null;
	static String databaseName="doctorpatientportal";
	static String url="jdbc:mysql://localhost:3306/"+databaseName ;
	static String username="root";
	static String password="Mylaptop@99";
	ResultSet rs;
	PreparedStatement ps;
	
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JPanel AddDoctor;
	private JPanel ViewDoctors;
	private JPanel ViewPatients;
	private JPanel ChangePassword;
	private JTextPane textPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField_3;
	private JPasswordField passwordField_4;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel DeleteDoctor;
	private JTable DoctorList;
	@SuppressWarnings("unused")
	private String userName=null;
	private JTable PatientList;
	
	
	 private void display(JTable table,String query)
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
	    }
	    catch(Exception e)
	    {
	            JOptionPane.showMessageDialog(null,e);
	    }
	    }

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
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
	 * Create the application.
	 */
	public AdminLogin(String userName) {
		initialize();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		this.userName=userName;
		
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		ImageIcon logoimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\admin.png");
		
		frame = new JFrame("Admin");
		frame.setBounds(100, 100, 664, 531);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(logoimg.getImage());
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 63, 630, 423);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		AddDoctor = new JPanel();
		layeredPane.add(AddDoctor, "name_490541611721300");
		AddDoctor.setLayout(null);
		
		JLabel lblAddDoctorDetails = new JLabel("Doctor Details");
		lblAddDoctorDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAddDoctorDetails.setBounds(255, 9, 167, 38);
		AddDoctor.add(lblAddDoctorDetails);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(123, 45, 367, 2);
		AddDoctor.add(separator);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(123, 67, 142, 29);
		AddDoctor.add(lblName);
		
		JLabel lblDesignation = new JLabel("Categorie");
		lblDesignation.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDesignation.setBounds(123, 148, 142, 29);
		AddDoctor.add(lblDesignation);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddress.setBounds(123, 235, 142, 29);
		AddDoctor.add(lblAddress);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUserName.setBounds(123, 106, 142, 32);
		AddDoctor.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(123, 283, 142, 29);
		AddDoctor.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblConfirmPassword.setBounds(123, 322, 142, 28);
		AddDoctor.add(lblConfirmPassword);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		textField.setBounds(312, 68, 178, 29);
		AddDoctor.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(312, 106, 178, 29);
		AddDoctor.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(312, 149, 178, 29);
		AddDoctor.add(textField_2);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		passwordField_3.setBounds(312, 283, 178, 29);
		AddDoctor.add(passwordField_3);
		
		passwordField_4 = new JPasswordField();
		passwordField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		passwordField_4.setBounds(312, 322, 178, 29);
		AddDoctor.add(passwordField_4);
		
		textPane = new JTextPane();
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		textPane.setBounds(312, 235, 178, 38);
		AddDoctor.add(textPane);
		
		JButton btnNewButton_2 = new JButton("Add Doctor");
		btnNewButton_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usingKeyListener();
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2.setBounds(341, 375, 149, 38);
		AddDoctor.add(btnNewButton_2);
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					makeFieldNull();
				}
			}
		});
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeFieldNull();				
			}
		});
		btnDiscard.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDiscard.setBounds(500, 375, 120, 38);
		AddDoctor.add(btnDiscard);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDoctorId.setBounds(123, 187, 142, 29);
		AddDoctor.add(lblDoctorId);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					usingKeyListener();
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(312, 188, 178, 29);
		AddDoctor.add(textField_3);
		
		JRadioButton rb1 = new JRadioButton("Show/Hide ");
		rb1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_3.setEchoChar((char)0);
			}
		});
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rb1,passwordField_3);
			}
		});
		rb1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rb1.setBounds(496, 288, 105, 21);
		AddDoctor.add(rb1);
		
		JRadioButton rb2 = new JRadioButton("Show/Hide ");
		rb2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_4.setEchoChar((char)0);
			}
		});
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rb2,passwordField_4);
			}
		});
		rb2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rb2.setBounds(496, 327, 105, 21);
		AddDoctor.add(rb2);
		
		ViewDoctors = new JPanel();
		layeredPane.add(ViewDoctors, "name_490541620510100");
		ViewDoctors.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctors List");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(266, 10, 163, 37);
		ViewDoctors.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(165, 45, 293, 2);
		ViewDoctors.add(separator_1);
		
		JButton btnDeleteDoctorInfo = new JButton("Delete Doctor Info");
		btnDeleteDoctorInfo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					switchPanels(DeleteDoctor);
			}
		});
		btnDeleteDoctorInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(DeleteDoctor);
			}
		});
		btnDeleteDoctorInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDeleteDoctorInfo.setBounds(444, 390, 176, 23);
		ViewDoctors.add(btnDeleteDoctorInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 610, 322);
		ViewDoctors.add(scrollPane);
		
		DoctorList = new JTable();
		scrollPane.setViewportView(DoctorList);
		
		ViewPatients = new JPanel();
		layeredPane.add(ViewPatients, "name_490541628938200");
		ViewPatients.setLayout(null);
		
		JLabel lblVisitedPatients = new JLabel("Visited Patients");
		lblVisitedPatients.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblVisitedPatients.setBounds(252, 10, 133, 35);
		ViewPatients.add(lblVisitedPatients);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(162, 43, 293, 2);
		ViewPatients.add(separator_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 65, 610, 348);
		ViewPatients.add(scrollPane_1);
		
		PatientList = new JTable();
		scrollPane_1.setViewportView(PatientList);
		
		ChangePassword = new JPanel();
		layeredPane.add(ChangePassword, "name_490541637868800");
		ChangePassword.setLayout(null);
		
		JLabel lblUpdatePassword = new JLabel("Update Password");
		lblUpdatePassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUpdatePassword.setBounds(246, 10, 140, 39);
		ChangePassword.add(lblUpdatePassword);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(178, 47, 275, 2);
		ChangePassword.add(separator_3);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOldPassword.setBounds(115, 119, 95, 27);
		ChangePassword.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewPassword.setBounds(115, 175, 116, 27);
		ChangePassword.add(lblNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblConfirmNewPassword.setBounds(115, 229, 152, 27);
		ChangePassword.add(lblConfirmNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField.setBounds(273, 120, 132, 27);
		ChangePassword.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField_1.setBounds(273, 176, 132, 27);
		ChangePassword.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		passwordField_2.setBounds(273, 230, 132, 27);
		ChangePassword.add(passwordField_2);
		
		JButton btnNewButton = new JButton("Save Changes");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					changePassword();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					changePassword();
				}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(180, 320, 132, 39);
		ChangePassword.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Discard");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				{
					passwordField.setText("");
					passwordField_1.setText(null);
					passwordField_2.setText(null);
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				passwordField_1.setText(null);
				passwordField_2.setText(null);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(322, 320, 131, 39);
		ChangePassword.add(btnNewButton_1);
		
		JRadioButton rd1 = new JRadioButton("Show/Hide password");
		rd1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField.setEchoChar((char)0);
			}
		});
		rd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rd1,passwordField);
			}
		});
		rd1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rd1.setBounds(422, 122, 164, 21);
		ChangePassword.add(rd1);
		
		JRadioButton rd2 = new JRadioButton("Show/Hide password");
		rd2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_1.setEchoChar((char)0);
			}
		});
		rd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rd2,passwordField_1);
			}
		});
		rd2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rd2.setBounds(422, 178, 164, 21);
		ChangePassword.add(rd2);
		
		JRadioButton rd3 = new JRadioButton("Show/Hide password");
		rd3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					passwordField_2.setEchoChar((char)0);
			}
		});
		rd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(rd3,passwordField_2);
			}
		});
		rd3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		rd3.setBounds(422, 232, 164, 21);
		ChangePassword.add(rd3);
		
		
		DeleteDoctor = new JPanel();
		layeredPane.add(DeleteDoctor, "name_110513702824300");
		DeleteDoctor.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Delete Doctor Info");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(229, 0, 182, 30);
		DeleteDoctor.add(lblNewLabel_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(124, 28, 325, 2);
		DeleteDoctor.add(separator_4);
		
		JLabel lblDoctorId_1 = new JLabel("Doctor ID");
		lblDoctorId_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDoctorId_1.setBounds(278, 85, 102, 30);
		DeleteDoctor.add(lblDoctorId_1);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					deletingDoctor();
			}
		});
		textField_4.setBounds(207, 125, 204, 19);
		DeleteDoctor.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					deletingDoctor();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletingDoctor();
			}	
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setBounds(269, 154, 87, 30);
		DeleteDoctor.add(btnDelete);
		
		ImageIcon deleteimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\delete.png");
		JLabel lblNewLabel_2 = new JLabel(deleteimg);
		lblNewLabel_2.setBounds(214, 205, 197, 168);
		DeleteDoctor.add(lblNewLabel_2);
		
		JButton btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					switchPanels(AddDoctor);
			}
		});
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(AddDoctor);
			}
		});
		btnAddDoctor.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddDoctor.setBounds(10, 10, 115, 41);
		frame.getContentPane().add(btnAddDoctor);
		
		JButton btnViewDoctors = new JButton("View Doctors");
		btnViewDoctors.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(ViewDoctors);
					String query="Select * from doctorlogin";
					display(DoctorList,query);
				}
				}
		});
		btnViewDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ViewDoctors);
				String query="Select * from doctorlogin";
				display(DoctorList,query);
			}
		});
		btnViewDoctors.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewDoctors.setBounds(122, 10, 121, 41);
		frame.getContentPane().add(btnViewDoctors);
		
		JButton btnViewPatients = new JButton("View Patients");
		btnViewPatients.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(ViewPatients);
					String query="Select Name,mobileNumber,Age,email,userName,password from patientlogin";
					display(PatientList,query);
				}
			}
		});
		btnViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ViewPatients);
				String query="Select Name,mobileNumber,Age,email,userName,password from patientlogin";
				display(PatientList,query);
			}
		});
		btnViewPatients.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnViewPatients.setBounds(237, 10, 132, 41);
		frame.getContentPane().add(btnViewPatients);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					switchPanels(ChangePassword);
			}
		});
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ChangePassword);
			}
		});
		btnChangePassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnChangePassword.setBounds(365, 10, 156, 41);
		frame.getContentPane().add(btnChangePassword);
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					JOptionPane.showMessageDialog(null, "Successfully Logged Out");
					frame.dispose();
					@SuppressWarnings("unused")
					MainPage mainpage=new MainPage();
				}
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully Logged Out");
				frame.dispose();
				@SuppressWarnings("unused")
				MainPage mainpage=new MainPage();
			}
		});
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogOut.setBounds(519, 10, 121, 41);
		frame.getContentPane().add(btnLogOut);
	}
	
	/*
	 * inserting new doctor information in the database
	 */
	@SuppressWarnings("deprecation")
	private void usingKeyListener()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(url ,username, password);
			System.out.println("Connection Established... ");
			if( textField.getText().length()==0 )
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(textField_1.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(textField_2.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(textField_3.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(textPane.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(passwordField_3.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else if(passwordField_4.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Enter data into all fields");
			else
			{
				if(passwordField_3.getText().equals(passwordField_4.getText()))
				{
					ps=connection.prepareStatement("INSERT INTO doctorlogin(username ,doctorid ,Name ,categorie ,address ,password) VALUES (?,?,?,?,?,?)");
					ps.setString(1, textField_1.getText());
					ps.setString(2, textField_3.getText());
					ps.setString(3, textField.getText());
					ps.setString(4, textField_2.getText());
					ps.setString(5, textPane.getText());
					ps.setString(6, passwordField_3.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Successfully Updated");
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
					textPane.setText(null);
					passwordField_3.setText(null);
					passwordField_4.setText(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Password's do not match");
				}
			}
		}
		catch(Exception exc)
		{
			System.out.println("Exception :"+exc);
		}
	}
	/*
	 * deleting doctor information
	 */
	private void deletingDoctor()
	{
		if(textField_4.getText().length()==0)
		{
			JOptionPane.showMessageDialog(null, "Enter Doctor ID");
		}
		else
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection= DriverManager.getConnection(url ,username, password);
				System.out.println("Connection Established... ");
				ps=connection.prepareStatement("");
				String docID=textField_4.getText();
						String deleteQuery= "delete from doctorlogin where doctorid =  "+docID;
						ps.executeUpdate(deleteQuery);
						JOptionPane.showMessageDialog(null,"Doctor details were successfully deleted");
						textField_4.setText("");
			}
			catch(Exception exc)
			{
				System.out.println("Exception :"+exc);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void changePassword()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(url ,username, password);
			System.out.println("Connection Established... ");
			ps=connection.prepareStatement("");
			if(passwordField_2.getText().length()==0 && passwordField_1.getText().length()==0 && passwordField.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null, "Password Field('s) are empty" );
			}
			else
			{
				String oldPass = null;
				String getPass="Select pass from adminlogin";
				rs = ps.executeQuery(getPass);
				while(rs.next())
					oldPass=rs.getString("pass");
				if(passwordField_2.getText().equals(passwordField_1.getText()) && passwordField.getText().equals(oldPass))
				{
					String passwordQuery= "update adminlogin set pass='"+passwordField_1.getText()+"' where UserName='admin' ";
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
				rs.close();
			}
		}
		catch(Exception exc)
		{
			System.out.println("Exception :"+exc);
		}
	}
	
	private void makeFieldNull()
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		textPane.setText(null);
		passwordField_3.setText(null);
		passwordField_4.setText(null);
	}
}
