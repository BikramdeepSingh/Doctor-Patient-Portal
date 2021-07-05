package patient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainPage;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class AfterLogin extends JFrame 
    {
	
	private JPanel contentPane;
	private JTextField textField;
	private JLayeredPane layeredPane;
	private JPanel MyDetails;
	private JPanel BookAppointment;
	private JPanel ViewBookings;
	private JPanel CancelBooking;
	private JPanel SearchDoctor;
	private JPanel DonateOrgan;
	private JPanel SearchOrgan;
	private JPanel Feedback;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_10;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_11;
	private JTextField textField_12;
	private String userName=null,organ,timePeriod;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JComboBox<String> comboBox,comboBox_1,comboBox_7,comboBox_3,comboBox_6;
	private JDateChooser dateChooser;
	private JTextPane textPane;
	//private int check_availability;
	
	static Connection connection=null;
	static String databaseName="doctorpatientportal";
	static String url="jdbc:mysql://localhost:3306/"+databaseName ;
	static String username="root";
	static String password="Mylaptop@99";
	private String strDate;
	ResultSet rs,rs1,rs2;
	PreparedStatement ps,ps2,ps3;
	private JPanel ChangePassword;
	private JTable OrganTable;
	private JTable DoctorTable;
	private JTable BookingHistory;
	private JTextField textField_8;
	protected String bloodGroup;
	protected String getBloodGroup;
	protected String getOrgan;
	protected String selected;
	protected String option;
	protected String getType;
	protected String getTime;
	private String doctorName;
		
	private void displayTable(JTable table,String a)
    {
		PreparedStatement ps1;
		ResultSet rs1;
		try
		{        
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username , password);
			System.out.println("Connection Established...");	
                                         ps1 = connection.prepareStatement("");
                                         rs1= ps1.executeQuery(a);
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
	
	public void getDoctorName(String type)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=connection.prepareStatement("");
			String query="Select Name from doctorlogin where categorie='"+type+"' ";
			rs=ps.executeQuery(query);
			if(rs.next()) {
				doctorName=rs.getString("Name");
				}
		} catch (Exception e) {
			System.out.println("getDoctorName "+e.getMessage());
		}
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
	
	public void getPatientInfo()
	{
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=connection.prepareStatement("");
			String query="Select Name,Age,email,mobileNumber from patientlogin where username='"+userName+"'";
			rs=ps.executeQuery(query);
			while(rs.next())
			{
				textField.setText(rs.getString("Name"));
				textField_1.setText(rs.getString("Age"));
				textField_2.setText(rs.getString("email"));
				textField_3.setText(rs.getString("mobileNumber"));
			}
		} catch (Exception e) {
			System.out.println("getPatientInfo "+e.getMessage());
		}
	}
	
	/**
	 * Create the frame.
	 */
	public AfterLogin(String userName) {
		this.userName=userName;
		setTitle("Patient Login");
		ImageIcon patientlog=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\patientlogin.png");
		setIconImage(patientlog.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("My Details");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(MyDetails);
					getPatientInfo();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(MyDetails);
				getPatientInfo();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton.setBounds(0, 0, 130, 29);
		contentPane.add(btnNewButton);
		
		JButton btnBookAppointment = new JButton("Book Appointment");
		btnBookAppointment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(BookAppointment);
				}
			}
		});
		btnBookAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BookAppointment);
			}
		});
		btnBookAppointment.setFont(new Font("Arial", Font.BOLD, 16));
		btnBookAppointment.setBounds(127, 0, 181, 29);
		contentPane.add(btnBookAppointment);
		
		JButton btnDonateOrgan = new JButton("Donate Organ");
		btnDonateOrgan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(DonateOrgan);
				}
			}
		});
		btnDonateOrgan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(DonateOrgan);
			}
		});
		btnDonateOrgan.setFont(new Font("Arial", Font.BOLD, 16));
		btnDonateOrgan.setBounds(304, 0, 162, 29);
		contentPane.add(btnDonateOrgan);
		
		JButton btnSearchOrgan = new JButton("Search Organ");
		btnSearchOrgan.addKeyListener(new KeyAdapter() {
			@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						switchPanels(SearchOrgan);
						displayTable(OrganTable,"select * from organdetail");
					}
				}
		});
		btnSearchOrgan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(SearchOrgan);
				displayTable(OrganTable,"select * from organdetail");
			}
		});
		btnSearchOrgan.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearchOrgan.setBounds(463, 0, 162, 29);
		contentPane.add(btnSearchOrgan);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(Feedback);
				}
			}
		});
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Feedback);
			}
		});
		btnFeedback.setFont(new Font("Arial", Font.BOLD, 16));
		btnFeedback.setBounds(621, 0, 151, 29);
		contentPane.add(btnFeedback);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					JOptionPane.showMessageDialog(null, "Sucessfully logged out...");
					dispose();
					@SuppressWarnings("unused")
					MainPage mainpage=new MainPage();
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sucessfully logged out...");
				dispose();
				@SuppressWarnings("unused")
				MainPage mainpage=new MainPage();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(769, 0, 124, 29);
		contentPane.add(btnNewButton_1);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 39, 883, 469);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		MyDetails = new JPanel();
		layeredPane.add(MyDetails, "name_959587464818000");
		MyDetails.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Details");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setBounds(565, 0, 138, 42);
		MyDetails.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(427, 47, 62, 36);
		MyDetails.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setBounds(587, 52, 245, 34);
		MyDetails.add(textField);
		textField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Arial", Font.BOLD, 20));
		lblAge.setBounds(427, 109, 62, 35);
		MyDetails.add(lblAge);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail.setBounds(427, 168, 83, 29);
		MyDetails.add(lblEmail);
		
		JLabel lblPhno = new JLabel("Mobile No.");
		lblPhno.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhno.setBounds(426, 225, 122, 33);
		MyDetails.add(lblPhno);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(511, 52, 182, -5);
		MyDetails.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(427, 40, 405, 2);
		MyDetails.add(separator_1);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(587, 169, 245, 33);
		MyDetails.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(587, 225, 245, 33);
		MyDetails.add(textField_3);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(587, 113, 245, 33);
		MyDetails.add(textField_1);
		
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		
		ImageIcon patientimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\patient3.jpg");
		JLabel lblNewLabel_2 = new JLabel(patientimg);
		lblNewLabel_2.setBounds(0, 0, 386, 459);
		MyDetails.add(lblNewLabel_2);
		
		JButton btnNewButton_8 = new JButton("Make Changes");
		btnNewButton_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					textField_2.setEditable(true);
					textField_2.setBackground(Color.CYAN);
					textField_3.setEditable(true);
					textField_3.setBackground(Color.CYAN);
				}
			}
		});
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setEditable(true);
				textField_2.setBackground(Color.CYAN);
				textField_3.setEditable(true);
				textField_3.setBackground(Color.CYAN);
			}
		});
		btnNewButton_8.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_8.setBounds(472, 285, 182, 36);
		MyDetails.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Discard");
		btnNewButton_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					getPatientInfo();
					textField_2.setEditable(false);
					textField_2.setBackground(Color.WHITE);
					textField_3.setEditable(false);
					textField_3.setBackground(Color.WHITE);
				}
			}
		});
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPatientInfo();
				textField_2.setEditable(false);
				textField_2.setBackground(Color.WHITE);
				textField_3.setEditable(false);
				textField_3.setBackground(Color.WHITE);
			}
		});
		btnNewButton_9.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_9.setBounds(664, 285, 159, 36);
		MyDetails.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Change Password");
		btnNewButton_10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					switchPanels(ChangePassword);
			}
		});
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ChangePassword);
			}
		});
		btnNewButton_10.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_10.setBounds(546, 423, 212, 36);
		MyDetails.add(btnNewButton_10);
		
		JButton btnViewBookings = new JButton("View Bookings");
		btnViewBookings.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(ViewBookings);
					String un=userName;
					displayTable(BookingHistory,"select * from appointment where UserName='"+un+"'");
				}				
			}
		});
		btnViewBookings.setBounds(565, 377, 179, 36);
		MyDetails.add(btnViewBookings);
		btnViewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ViewBookings);
				String un=userName;
				displayTable(BookingHistory,"select * from appointment where UserName='"+un+"'");;
			}
		});
		btnViewBookings.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton btnDone = new JButton("Done");
		btnDone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					updatePatientDetails();
				}
			}
		});
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePatientDetails();
			}
		});
		btnDone.setFont(new Font("Arial", Font.BOLD, 20));
		btnDone.setBounds(613, 331, 90, 36);
		MyDetails.add(btnDone);
		
		BookAppointment = new JPanel();
		layeredPane.add(BookAppointment, "name_959593575976200");
		BookAppointment.setLayout(null);
		
		JLabel lblNewBooking = new JLabel("New Booking");
		lblNewBooking.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewBooking.setBounds(537, 0, 195, 44);
		BookAppointment.add(lblNewBooking);
		
		JLabel lblAppointmentId = new JLabel("Your Appointment Id");
		lblAppointmentId.setFont(new Font("Arial", Font.BOLD, 20));
		lblAppointmentId.setBounds(421, 298, 218, 33);
		BookAppointment.add(lblAppointmentId);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.BOLD, 14));
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setEditable(false);
		textField_4.setBounds(688, 301, 171, 33);
		BookAppointment.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Arial", Font.BOLD, 20));
		lblCategory.setBounds(421, 54, 128, 33);
		BookAppointment.add(lblCategory);
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Normal", "Bone", "Skin", "Heart", "Dentist", "Neurologist", "Kidney", "Cardiologist", "Plastic Surgeon"}));
		comboBox.setBounds(621, 54, 238, 33);
		BookAppointment.add(comboBox);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Arial", Font.BOLD, 20));
		lblDate.setBounds(421, 105, 118, 22);
		BookAppointment.add(lblDate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Arial", Font.BOLD, 20));
		lblTime.setBounds(421, 145, 118, 22);
		BookAppointment.add(lblTime);
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00 ", "17:00:00", "17:30:00 ", "18:00:00", "18:30:00", "19:00:00", "19:30:00"}));
		comboBox_1.setBounds(621, 140, 238, 33);
		BookAppointment.add(comboBox_1);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 18));
		dateChooser.setBounds(621, 97, 238, 30);
		BookAppointment.add(dateChooser);
		
		
		JButton btnNewButton_2 = new JButton("Confirm");
		btnNewButton_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					bookAppointment();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAppointment();
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_2.setBounds(661, 196, 165, 33);
		BookAppointment.add(btnNewButton_2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(421, 42, 438, 2);
		BookAppointment.add(separator_2);
		
		JButton btnSearchDoctor = new JButton("Search Doctor");
		btnSearchDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					switchPanels(SearchDoctor);
					displayTable(DoctorTable,"select doctorid,Name,categorie from doctorlogin");
				}
			}
		});
		btnSearchDoctor.setBounds(647, 387, 212, 33);
		BookAppointment.add(btnSearchDoctor);
		btnSearchDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(SearchDoctor);
				displayTable(DoctorTable,"select doctorid,Name,categorie from doctorlogin");
			}
		});
		btnSearchDoctor.setFont(new Font("Arial", Font.BOLD, 20));
		
		ImageIcon appointment=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\appointment1.png");
		JLabel lblNewLabel_4 = new JLabel(appointment);
		lblNewLabel_4.setBounds(0, 0, 393, 459);
		BookAppointment.add(lblNewLabel_4);
		
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setFont(new Font("Arial", Font.BOLD, 20));
		lblDoctorName.setBounds(421, 341, 157, 33);
		BookAppointment.add(lblDoctorName);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Arial", Font.BOLD, 14));
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(688, 344, 171, 33);
		BookAppointment.add(textField_8);
		
		ViewBookings = new JPanel();
		layeredPane.add(ViewBookings, "name_959595806889100");
		ViewBookings.setLayout(null);
		
		JLabel lblBookingHistory = new JLabel("Booking History");
		lblBookingHistory.setFont(new Font("Arial", Font.BOLD, 26));
		lblBookingHistory.setBounds(314, -3, 215, 44);
		ViewBookings.add(lblBookingHistory);
		
		JButton btnCancelBookings = new JButton("Cancel Bookings");
		btnCancelBookings.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					switchPanels(CancelBooking);
			}
		});
		btnCancelBookings.setBounds(690, 425, 183, 34);
		ViewBookings.add(btnCancelBookings);
		btnCancelBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(CancelBooking);
			}
		});
		btnCancelBookings.setFont(new Font("Arial", Font.BOLD, 16));
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(220, 39, 390, 2);
		ViewBookings.add(separator_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 51, 863, 364);
		ViewBookings.add(scrollPane_2);
		
		BookingHistory = new JTable();
		scrollPane_2.setViewportView(BookingHistory);
		
		CancelBooking = new JPanel();
		layeredPane.add(CancelBooking, "name_959597969385600");
		CancelBooking.setLayout(null);
		
		JLabel lblCancelBooking = new JLabel("Cancel Booking");
		lblCancelBooking.setFont(new Font("Arial", Font.BOLD, 26));
		lblCancelBooking.setBounds(330, 0, 212, 44);
		CancelBooking.add(lblCancelBooking);
		
		JLabel label = new JLabel("Appointment Id");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(229, 56, 164, 31);
		CancelBooking.add(label);
		
		textField_6 = new JTextField();
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(textField_6.getText().length()==0)
						JOptionPane.showMessageDialog(null, "Please enter appointment ID");
					else
					{
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connection= DriverManager.getConnection(url ,username, password);
							System.out.println("Connection Established... ");
							ps=connection.prepareStatement("");
							String query="delete from appointment where AppointmentID='"+textField_6.getText()+"'";
							ps.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Appointment cancelled");
							textField_6.setText(null);
						}catch(Exception ex) {
							System.out.println("Appointment Cancelled "+ex.getMessage());
						}
					}
				}
			}
		});
		textField_6.setBounds(453, 54, 196, 33);
		CancelBooking.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_6.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Please enter appointment ID");
				else
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connection= DriverManager.getConnection(url ,username, password);
						System.out.println("Connection Established... ");
						ps=connection.prepareStatement("");
						String query="delete from appointment where AppointmentID='"+textField_6.getText()+"'";
						ps.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Appointment cancelled");
						textField_6.setText(null);
					}catch(Exception ex) {
						System.out.println("Appointment Cancelled "+ex.getMessage());
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_3.setBounds(349, 97, 179, 33);
		CancelBooking.add(btnNewButton_3);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(229, 42, 420, 2);
		CancelBooking.add(separator_8);
		
		ImageIcon cancelimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\cancel.jpeg");
		JLabel lblNewLabel_5 = new JLabel(cancelimg);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(229, 162, 420, 240);
		CancelBooking.add(lblNewLabel_5);
		
		SearchDoctor = new JPanel();
		layeredPane.add(SearchDoctor, "name_959608233093900");
		SearchDoctor.setLayout(null);
		
		JLabel lblSearchDoctor = new JLabel("Search Doctor");
		lblSearchDoctor.setFont(new Font("Arial", Font.BOLD, 26));
		lblSearchDoctor.setBounds(352, 0, 181, 41);
		SearchDoctor.add(lblSearchDoctor);
		
		JLabel lblSearchBy = new JLabel("Search By :-");
		lblSearchBy.setFont(new Font("Arial", Font.BOLD, 20));
		lblSearchBy.setBounds(247, 56, 131, 19);
		SearchDoctor.add(lblSearchBy);
		
		JComboBox<String> comboBox_2 = new JComboBox<>();
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Name", "Category"}));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option=(String)comboBox_2.getSelectedItem();
			}
		});
		comboBox_2.setBounds(474, 51, 195, 29);
		SearchDoctor.add(comboBox_2);
		
		JLabel lblEnterDetails = new JLabel("Enter Details");
		lblEnterDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblEnterDetails.setBounds(247, 96, 145, 19);
		SearchDoctor.add(lblEnterDetails);
		
		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(option=="Name")
						displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where Name='"+textField_7.getText()+"'");
					else if(option=="Category")
						displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where categorie='"+textField_7.getText()+"'");
					else
						JOptionPane.showMessageDialog(null, "Select from available options");
					comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Name", "Category"}));
					textField_7.setText(null);
				}
			}
		});
		textField_7.setBounds(474, 90, 198, 29);
		SearchDoctor.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(option=="Name")
						displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where Name='"+textField_7.getText()+"'");
					else if(option=="Category")
						displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where categorie='"+textField_7.getText()+"'");
					else
						JOptionPane.showMessageDialog(null, "Select from available options");
					comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Name", "Category"}));
					textField_7.setText(null);
				}
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(option=="Name")
					displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where Name='"+textField_7.getText()+"'");
				else if(option=="Category")
					displayTable(DoctorTable,"Select doctorid,Name,categorie from doctorlogin where categorie='"+textField_7.getText()+"'");
				else
					JOptionPane.showMessageDialog(null, "Select from available options");
				comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Name", "Category"}));
				textField_7.setText(null);
			}
		});
		btnNewButton_4.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_4.setBounds(503, 129, 131, 33);
		SearchDoctor.add(btnNewButton_4);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Arial", Font.BOLD, 20));
		lblResults.setBounds(10, 174, 118, 19);
		SearchDoctor.add(lblResults);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(247, 35, 421, 2);
		SearchDoctor.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(10, 198, 195, 2);
		SearchDoctor.add(separator_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 203, 856, 242);
		SearchDoctor.add(scrollPane_1);
		
		DoctorTable = new JTable();
		scrollPane_1.setViewportView(DoctorTable);
		
		DonateOrgan = new JPanel();
		layeredPane.add(DonateOrgan, "name_959609974497800");
		DonateOrgan.setLayout(null);
		
		JLabel lblDonarDetails = new JLabel("Organ Details");
		lblDonarDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonarDetails.setFont(new Font("Arial", Font.BOLD, 26));
		lblDonarDetails.setBounds(425, 10, 365, 44);
		DonateOrgan.add(lblDonarDetails);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblName_1.setBounds(378, 64, 81, 29);
		DonateOrgan.add(lblName_1);
		
		textField_10 = new JTextField();
		textField_10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					donateOrganDetails();
				}
			}
		});
		textField_10.setBounds(586, 64, 287, 31);
		DonateOrgan.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblAge_1 = new JLabel("Age");
		lblAge_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblAge_1.setBounds(378, 103, 63, 31);
		DonateOrgan.add(lblAge_1);
		
		JLabel lblPhno_1 = new JLabel("Ph.No.");
		lblPhno_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhno_1.setBounds(378, 150, 81, 23);
		DonateOrgan.add(lblPhno_1);
		
		JLabel lblOrgan = new JLabel("Organ");
		lblOrgan.setFont(new Font("Arial", Font.BOLD, 20));
		lblOrgan.setBounds(378, 231, 81, 23);
		DonateOrgan.add(lblOrgan);
		
		comboBox_3 = new JComboBox<>();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==comboBox_3)
				{
					organ=(String)comboBox_3.getSelectedItem();
				}
			}
		});
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
		comboBox_3.setBounds(586, 228, 287, 29);
		DonateOrgan.add(comboBox_3);
		
		comboBox_6 = new JComboBox<String>();
		comboBox_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==comboBox_6)
				{
					timePeriod=(String)comboBox_6.getSelectedItem();
				}
			}
		});
		comboBox_6.setBackground(Color.WHITE);
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Now", "After Death"}));
		comboBox_6.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_6.setBounds(586, 273, 287, 29);
		DonateOrgan.add(comboBox_6);
		
		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setFont(new Font("Arial", Font.BOLD, 20));
		lblBloodGroup.setBounds(378, 183, 122, 38);
		DonateOrgan.add(lblBloodGroup);
		
		comboBox_7 = new JComboBox<String>();
		comboBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					bloodGroup=(String)comboBox_7.getSelectedItem();
			}
		});
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}));
		comboBox_7.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_7.setBackground(Color.WHITE);
		comboBox_7.setBounds(586, 183, 287, 29);
		DonateOrgan.add(comboBox_7);
		
		
		JButton btnNewButton_5 = new JButton("Submit");
		btnNewButton_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					donateOrganDetails();
				}
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donateOrganDetails();
			}
		});
		btnNewButton_5.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_5.setBounds(637, 336, 162, 29);
		DonateOrgan.add(btnNewButton_5);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(378, 52, 495, 2);
		DonateOrgan.add(separator_5);
		
		textField_11 = new JTextField();
		textField_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					donateOrganDetails();
				}
			}
		});
		textField_11.setColumns(10);
		textField_11.setBounds(586, 102, 287, 31);
		DonateOrgan.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					donateOrganDetails();
				}
			}
		});
		textField_12.setColumns(10);
		textField_12.setBounds(586, 143, 287, 31);
		DonateOrgan.add(textField_12);
		
		ImageIcon heart=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\donate.jpg");
		JLabel label_1 = new JLabel(heart);
		label_1.setBounds(0, 0, 351, 459);
		DonateOrgan.add(label_1);
		
		JLabel lblWhenToDonate = new JLabel("When to Donate");
		lblWhenToDonate.setFont(new Font("Arial", Font.BOLD, 20));
		lblWhenToDonate.setBounds(378, 276, 156, 23);
		DonateOrgan.add(lblWhenToDonate);
		
		SearchOrgan = new JPanel();
		layeredPane.add(SearchOrgan, "name_959611651504300");
		SearchOrgan.setLayout(null);
		
		JLabel lblSearchOrgans = new JLabel("Search Organs");
		lblSearchOrgans.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchOrgans.setFont(new Font("Arial", Font.BOLD, 26));
		lblSearchOrgans.setBounds(236, 10, 365, 44);
		SearchOrgan.add(lblSearchOrgans);
		
		JLabel lblSearchBy_1 = new JLabel("Blood Group :-");
		lblSearchBy_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblSearchBy_1.setBounds(266, 101, 153, 23);
		SearchOrgan.add(lblSearchBy_1);
		
		JComboBox<String> comboBox_4 = new JComboBox<>();
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}));
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBloodGroup=(String)comboBox_4.getSelectedItem();
			}
		});
		comboBox_4.setBounds(429, 97, 216, 30);
		SearchOrgan.add(comboBox_4);
		
		JLabel lblName_2 = new JLabel("Organ :-");
		lblName_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblName_2.setBounds(265, 134, 77, 23);
		SearchOrgan.add(lblName_2);
		
		JComboBox<String> comboBox_5 = new JComboBox<String>();
		comboBox_5.setBackground(Color.WHITE);
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
		comboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrgan=(String)comboBox_5.getSelectedItem();
			}
		});
		comboBox_5.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_5.setBounds(429, 130, 216, 30);
		SearchOrgan.add(comboBox_5);
		
		JComboBox<String> comboBox_8 = new JComboBox<String>();
		comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Blood Group", "Organ", "Both"}));
		comboBox_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected=(String)comboBox_8.getSelectedItem();
			}
		});
		comboBox_8.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_8.setBackground(Color.WHITE);
		comboBox_8.setBounds(429, 64, 216, 30);
		SearchOrgan.add(comboBox_8);
		
		JButton btnNewButton_6 = new JButton("Search");
		btnNewButton_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(selected=="Blood Group")
						displayTable(OrganTable,"select * from organdetail where BloodGroup='"+getBloodGroup+"'");
					else if(selected=="Organ")
						displayTable(OrganTable,"select * from organdetail where Organ='"+getOrgan+"' ");
					else if(selected=="Both")
						displayTable(OrganTable,"select * from organdetail where BloodGroup='"+getBloodGroup+"' AND Organ='"+getOrgan+"' ");
					else 
						JOptionPane.showMessageDialog(null, "Select type to search for" );
					comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Blood Group", "Organ", "Both"}));
					comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}));
					comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
				}
			}
		});
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected=="Blood Group")
					displayTable(OrganTable,"select * from organdetail where BloodGroup='"+getBloodGroup+"'");
				else if(selected=="Organ")
					displayTable(OrganTable,"select * from organdetail where Organ='"+getOrgan+"' ");
				else if(selected=="Both")
					displayTable(OrganTable,"select * from organdetail where BloodGroup='"+getBloodGroup+"' AND Organ='"+getOrgan+"' ");
				else 
					JOptionPane.showMessageDialog(null, "Select type to search for" );
				comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Blood Group", "Organ", "Both"}));
				comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}));
				comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
			}
		});
		btnNewButton_6.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_6.setBounds(655, 97, 145, 30);
		SearchOrgan.add(btnNewButton_6);
		
		JLabel lblResults_1 = new JLabel("Results");
		lblResults_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblResults_1.setBounds(10, 175, 115, 19);
		SearchOrgan.add(lblResults_1);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(188, 50, 500, 2);
		SearchOrgan.add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(10, 193, 184, 2);
		SearchOrgan.add(separator_10);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 865, 235);
		SearchOrgan.add(scrollPane);
		
		OrganTable = new JTable();
		scrollPane.setViewportView(OrganTable);
		
		JLabel lblSearchBy_2 = new JLabel("Search By :-");
		lblSearchBy_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblSearchBy_2.setBounds(266, 68, 154, 23);
		SearchOrgan.add(lblSearchBy_2);
				
		Feedback = new JPanel();
		layeredPane.add(Feedback, "name_959731167676700");
		Feedback.setLayout(null);
		
		JLabel lblFeedback = new JLabel("Feedback");
		lblFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeedback.setFont(new Font("Arial", Font.BOLD, 26));
		lblFeedback.setBounds(242, 10, 365, 33);
		Feedback.add(lblFeedback);
		
		textPane = new JTextPane();
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					getFeedback();
				}
			}
		});
		textPane.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane.setBounds(10, 209, 863, 181);
		Feedback.add(textPane);
		
		JButton btnNewButton_7 = new JButton("Submit");
		btnNewButton_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					getFeedback();
				}
			}
		});
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeedback();
			}
		});
		btnNewButton_7.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_7.setBounds(379, 400, 156, 33);
		Feedback.add(btnNewButton_7);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(207, 41, 476, 2);
		Feedback.add(separator_4);
		
		ImageIcon picture=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\feedback.png");
		JLabel lblNewLabel_3 = new JLabel(picture);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(187, 49, 518, 150);
		Feedback.add(lblNewLabel_3);
		
		ChangePassword = new JPanel();
		layeredPane.add(ChangePassword, "name_51998953603200");
		ChangePassword.setLayout(null);
		
		JLabel label_2 = new JLabel("Update Password");
		label_2.setBounds(331, 10, 204, 35);
		label_2.setFont(new Font("Arial", Font.BOLD, 22));
		ChangePassword.add(label_2);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(234, 43, 391, 2);
		ChangePassword.add(separator_11);
		
		JLabel label_3 = new JLabel("Old Password");
		label_3.setFont(new Font("Arial", Font.BOLD, 18));
		label_3.setBounds(123, 71, 152, 39);
		ChangePassword.add(label_3);
		
		JLabel label_4 = new JLabel("New Password");
		label_4.setFont(new Font("Arial", Font.BOLD, 18));
		label_4.setBounds(123, 131, 179, 32);
		ChangePassword.add(label_4);
		
		JLabel label_5 = new JLabel("Confirm New Password");
		label_5.setFont(new Font("Arial", Font.BOLD, 18));
		label_5.setBounds(123, 190, 211, 39);
		ChangePassword.add(label_5);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePassword();
				}
			}
		});
		passwordField.setBounds(346, 77, 145, 32);
		ChangePassword.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePassword();
				}
			}
		});
		passwordField_1.setBounds(346, 134, 145, 32);
		ChangePassword.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePassword();
				}
			}
		});
		passwordField_2.setBounds(346, 194, 145, 35);
		ChangePassword.add(passwordField_2);
		
		JRadioButton radioButton = new JRadioButton("Show/Hide password");
		radioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.setEchoChar((char)0);
				}
			}
		});
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(radioButton,passwordField);
			}
		});
		radioButton.setFont(new Font("Arial", Font.BOLD, 18));
		radioButton.setBounds(532, 74, 219, 32);
		ChangePassword.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Show/Hide password");
		radioButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField_1.setEchoChar((char)0);
				}
			}
		});
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(radioButton_1,passwordField_1);
			}
		});
		radioButton_1.setFont(new Font("Arial", Font.BOLD, 18));
		radioButton_1.setBounds(532, 130, 219, 35);
		ChangePassword.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Show/Hide password");
		radioButton_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField_2.setEchoChar((char)0);
				}
			}
		});
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPass(radioButton_2,passwordField_2);
			}
		});
		radioButton_2.setFont(new Font("Arial", Font.BOLD, 18));
		radioButton_2.setBounds(532, 193, 219, 32);
		ChangePassword.add(radioButton_2);
		
		JButton button = new JButton("Save Changes");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					changePassword();
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 18));
		button.setBounds(222, 262, 171, 39);
		ChangePassword.add(button);
		
		JButton button_1 = new JButton("Discard");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.setText("");
					passwordField_1.setText(null);
					passwordField_2.setText(null);
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				passwordField_1.setText(null);
				passwordField_2.setText(null);
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 18));
		button_1.setBounds(424, 262, 152, 39);
		ChangePassword.add(button_1);
	}
	
	private void updatePatientDetails() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=connection.prepareStatement("");
			if(textField_2.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Please enter E-mail");
			else if(textField_2.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Please enter Mobile Number");
			else
			{
				String Query= "update patientlogin set email='"+textField_2.getText()+"', mobileNumber='"+textField_3.getText()+"' where userName='"+userName+"' ";
				ps.executeUpdate(Query);
				getPatientInfo();
				textField_2.setEditable(false);
				textField_2.setBackground(Color.WHITE);
				textField_3.setEditable(false);
				textField_3.setBackground(Color.WHITE);
				JOptionPane.showMessageDialog(null,"Details Updated");
			}						
			}
		catch(Exception exc)
		{
			System.out.println("updatePatientDetails :"+exc);
		}
	}
	
	private void bookAppointment() {
		getType=(String)comboBox.getSelectedItem();
		getTime=(String)comboBox_1.getSelectedItem();
		getDoctorName(getType);
		if(getType=="--Select--")	
			JOptionPane.showMessageDialog(null, "Please select category");
		else if(getTime=="--Select--")
			JOptionPane.showMessageDialog(null, "Please select appointment time");
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
				System.out.println("Connection Established...");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				strDate = dateFormat.format(dateChooser.getDate());
				
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				 LocalDateTime now = LocalDateTime.now();
			     String date1 = dtf.format(now).toString();
			     System.out.println(date1);
			     
			     DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");  
				 LocalDateTime nowTime = LocalDateTime.now();
			     String time1 = time.format(nowTime).toString();
			     System.out.println(time1);
			     
			     System.out.println("comparing dates "+(strDate.compareTo(date1)<0));
			     System.out.println("comparing time "+(getTime.compareTo(time1)<0));
			     
			     if( (strDate.compareTo(date1)==0)  && (getTime.compareTo(time1)<0) )
					{
						JOptionPane.showMessageDialog(null, "Time not applicable");
					}	
					else {
						 	if(strDate.compareTo(date1)<0) 
						 	{
								JOptionPane.showMessageDialog(null, "Date not applicable");	
							}
			  
						else {
							ps=connection.prepareStatement("insert into appointment(Category, Date, Time, PatientName, UserName, DoctorName) values(?,?,?,?,?,?)");
							ps.setString(1, getType);
							System.out.println(strDate);
							ps.setString(2, strDate); 
							ps.setString(3, getTime); 
							ps.setString(4, textField.getText()); 
							ps.setString(5, userName); 
							ps.setString(6, doctorName);
							ps.executeUpdate();
							ps=connection.prepareStatement("select AppointmentID,DoctorName from appointment where UserName='"+userName+"' and Date='"+strDate+"' and Time='"+getTime+"' and Category='"+getType+"'");
							rs=ps.executeQuery();
							if(rs.next()) {
								String newName;
								newName=checkAvailability(rs.getString("DoctorName"),getType,rs.getString("AppointmentID"));
								if(newName.compareTo("noAvail")!=0) {
								System.out.println("a"+newName);
								String updateAvail="insert into availability(DoctorName,Date,Time) values('"+newName+"', '"+strDate+"', '"+getTime+"')";
								ps2.executeUpdate(updateAvail);
								ps3=connection.prepareStatement("select AppointmentID,DoctorName from appointment where UserName='"+userName+"' and Date='"+strDate+"' and Time='"+getTime+"' and Category='"+getType+"'");
								rs2=ps3.executeQuery();
								if(rs2.next()) {
								JOptionPane.showMessageDialog(null, "Appointment Booked");
								textField_4.setText(rs2.getString("AppointmentID"));
								textField_8.setText(rs2.getString("DoctorName"));
								}
								}
							}
							comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Normal", "Bone", "Skin", "Heart", "Dentist", "Neurologist", "Kidney", "Cardiologist", "Plastic Surgeon"}));
							dateChooser.setCalendar(null);
							comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00 ", "17:00:00", "17:30:00 ", "18:00:00", "18:30:00", "19:00:00", "19:30:00"}));
	
						}
					}
		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
	}
}
	
	private void donateOrganDetails() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps=connection.prepareStatement("");
			if(textField_10.getText().length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else if(textField_11.getText().length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else if(textField_12.getText().length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else if(bloodGroup.length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else if(organ.length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else if(timePeriod.length()==0)
				JOptionPane.showMessageDialog(null, "*All Fields are Manadatory");
			else
			{
				ps=connection.prepareStatement("INSERT INTO organdetail(Name,Age,PhoneNumber,BloodGroup,Organ,WhenToDonate,InformerId) VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, textField_10.getText());
				ps.setString(2, textField_11.getText());
				ps.setString(3, textField_12.getText());
				ps.setString(4, bloodGroup);
				ps.setString(5, organ);
				ps.setString(6, timePeriod);
				ps.setString(7, userName);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Successfully Updated");
				textField_10.setText(null);
				textField_11.setText(null);
				textField_12.setText(null);
				comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"}));
				comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Kidney", "Cornea", "Heart", "Lung", "Intestine", "Pancreas"}));
				comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"--Select--", "Now", "After Death"}));
			}
		} catch (Exception ex) {
			System.out.println("donateOrganDetails "+ex.getMessage());
		}
	}
	
	private void getFeedback() {
		if(textPane.getText().length()==0)
			JOptionPane.showMessageDialog(null, "No feedback entered");
		else
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
				System.out.println("Connection Established...");
				ps=connection.prepareStatement("update patientlogin set Feedback=' "+ textPane.getText()+ " ' where userName='"+userName+"'");    
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Feedback entered");
				textPane.setText(null);
			}
			catch(Exception ex)
			{
				System.out.println("Feedback "+ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void changePassword() {
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
				String getPass="Select password from patientlogin";
				rs = ps.executeQuery(getPass);
				while(rs.next())
					oldPass=rs.getString("password");
				if(passwordField_2.getText().equals(passwordField_1.getText()) && passwordField.getText().equals(oldPass))
				{
					String passwordQuery= "update patientlogin set password='"+passwordField_1.getText()+"' where userName='"+userName+"' ";
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
			System.out.println("ChangePassword :"+exc);
		}

	}
		
	private String checkAvailability(String name, String type,String appID) {
		String name1 = name;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(url ,username, password);
			System.out.println("Connection Established... ");
			ps=connection.prepareStatement("");
			String query="select * from availability where DoctorName='"+name+"' and Date='"+strDate+"' and Time='"+getTime+"' ";
			System.out.println("a");
			rs=ps.executeQuery(query);	
			System.out.println("b");
			if(rs.next())
			{
				System.out.println("c");
				ps2=connection.prepareStatement("select * from doctorlogin where Name <> '"+name1+"' and categorie='"+type+"'");
				System.out.println("c1");
				rs1=ps2.executeQuery();
				System.out.println("d");
				
				if(rs1.next()) {
				name1=rs1.getString("Name");
				ps3=connection.prepareStatement("update appointment set DoctorName='"+rs1.getString("Name")+"' where AppointmentID='"+appID+"'");
				System.out.println("e");
				ps3.executeUpdate();
				System.out.println("f");
					return name1;
					}
				}
			 else{
					JOptionPane.showMessageDialog(null,"No Doctor Available for given date and time");
					return "noAvail";
				}			
		
		}
		catch(Exception ex)
		{
			System.out.println("checkAVail exception "+ex.getMessage());
		}
		return name1;
	}
	
	/*private void enableAvailability(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(url ,username, password);
			System.out.println("Connection Established... ");
			ps=connection.prepareStatement("");
			String query="update availability set Availability='0' where DoctorName='"+name+"')";
			ps.executeUpdate(query);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	private void disableAvailability(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection(url ,username, password);
			System.out.println("Connection Established... ");
			ps=connection.prepareStatement("");
			String query="update availability set Availability='1' where DoctorName='"+name+"')";
			ps.executeUpdate(query);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}*/
	
}

