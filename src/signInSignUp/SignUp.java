package signInSignUp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class SignUp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField textField_2;
	private JPasswordField textField_3;
	private JButton btnNewButton;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */	
	private void initialize() {
		ImageIcon icon=new ImageIcon("D:\\\\#6 Projects\\\\Minor Projects\\\\Online Examination System\\\\login.jpg");
		frame = new JFrame("Sign Up");
		frame.setBounds(100, 100, 494, 600);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(icon.getImage());
		ImageIcon im=new ImageIcon("D:\\\\#6 Projects\\\\Minor Projects\\\\Online Examination System\\\\signup1.jpg");
		JLabel lblNewLabel = new JLabel(im);
		lblNewLabel.setBounds(37, 22, 412, 174);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(75, 206, 87, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UserName");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(75, 250, 135, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Mobile No.");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_5.setBounds(75, 294, 135, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_6.setBounds(75, 338, 135, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("E-Mail");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setBounds(75, 382, 135, 25);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(75, 426, 183, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(270, 207, 143, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(270, 250, 143, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(270, 294, 143, 25);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(270, 338, 143, 25);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(270, 382, 143, 25);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(270, 426, 143, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(75, 470, 183, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JPasswordField();
		textField_3.setBounds(270, 470, 143, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
			
		btnNewButton = new JButton("Sign Up");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usingKeyListener();//textField,textField_1,textField_4,textField_5,textField_6,textField_2,textField_3
				}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnNewButton.setBounds(169, 514, 135, 36);
		frame.getContentPane().add(btnNewButton);
		ImageIcon ok=new ImageIcon("D:\\\\#6 Projects\\\\Minor Projects\\\\Online Examination System\\\\ok.png");
		btnNewButton.setIcon(ok);
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
		
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener();
				}
			}
		});
	}
	//JTextField textField, JTextField textField_1, JTextField textField_4, JTextField textField_5, JTextField textField_6, JPasswordField textField_2, JPasswordField textField_3
	@SuppressWarnings("deprecation")
	private void usingKeyListener()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			if(textField.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_1.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_2.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_3.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_4.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_5.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else if(textField_6.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Please enter data into all fields");
			else
			{
				if(textField_3.getText().equals(textField_2.getText())){
					ps = con.prepareStatement("insert into patientlogin (Name, userName, mobileNumber, Age, email, password) values (?, ?, ?, ?, ?, ?);");
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_4.getText());
					ps.setString(4, textField_5.getText());
					ps.setString(5, textField_6.getText());
					ps.setString(6, textField_2.getText());
					
					ps.executeUpdate();//return type is int
					JOptionPane.showMessageDialog(null, "SignUp Successful...");
					frame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Passwords do not match");
				}
			}
		
		}
		catch(Exception exception)
		{
			System.out.println("Exception : "+exception.getMessage());
		}
	}
}

