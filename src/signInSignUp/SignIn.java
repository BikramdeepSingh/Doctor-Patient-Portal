package signInSignUp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.sql.*;

import doctor.DoctorLogin;
import main.AdminLogin;
import patient.AfterLogin;


@SuppressWarnings("serial")
public class SignIn extends JFrame
{
	private boolean adminLogged=false;
	private boolean doctorLogged=false, patientLogged=false;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public SignIn()
	{		
		setTitle("Login");
		addingComponent();
		setVisible(true);
		
	}
	private void addingComponent() 
	{
		setSize(300,400);
		setResizable(false);
		setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("D:\\#6 Projects\\Minor Projects\\Online Examination System\\login.jpg");
	    setIconImage(img.getImage());  //changes the image to displayed
		JLabel l1,l2,l3,l4;
		JTextField t1;
		JPasswordField t2;
		t2=new JPasswordField("");
		JButton b;
		JRadioButton c;
		JComboBox<String> cb;
		c=new JRadioButton("Show/Hide Password");
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
						t2.setEchoChar((char)0);	
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(c.isSelected())
				t2.setEchoChar((char)0);
			else
				t2.setEchoChar('*');
			}
		});
		ImageIcon back=new ImageIcon("D:\\#6 Projects\\Minor Projects\\Online Examination System\\backimg.png");
		ImageIcon ok=new ImageIcon("D:\\#6 Projects\\Minor Projects\\Online Examination System\\ok.png");
		l3=new JLabel(back);
		l1=new JLabel("UserID");
		l2=new JLabel("Password");
		l4=new JLabel("Login As...");
		t1=new JTextField("");
		b=new JButton("Login");
		b.setForeground(Color.RED);
		
		String []loginAs= {"Select here","Admin","Doctor","Patient"};
		cb=new JComboBox<String>(loginAs);
		
		l1.setFont(new Font("Times New Roman",Font.BOLD, 16));
		l2.setFont(new Font("Times New Roman",Font.BOLD, 17));
		t1.setFont(new Font("Times New Roman",Font.PLAIN, 14));
		t2.setFont(new Font("Times New Roman",Font.PLAIN, 14));
		l4.setFont(new Font("Times New Roman",Font.BOLD, 17));
		cb.setSelectedIndex(0);
		cb.setFont(new Font("Times New Roman",Font.BOLD, 17));
		b.setFont(new Font("Times New Roman",Font.BOLD, 16));
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==cb)
				{
					JComboBox<?> newCB=(JComboBox<?>)e.getSource();
					String msg=(String)newCB.getSelectedItem();
					switch(msg) {
					case "Admin":adminLogged=true;
					             break;
					case "Doctor":doctorLogged=true;
					              break;
					case "Patient":patientLogged=true;
					               break;
					default: break;
					}
					
				}
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usingKeyListener(t1,t2);
			}
		});
		setLayout(null);

		t1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener(t1,t2);
				}
			}
		});
		
		t2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener(t1,t2);
				}
			}
		});
		
		b.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					usingKeyListener(t1,t2);
				}
			}
		});
		
		l1.setBounds(20, 200, 80, 25);
		l2.setBounds(20, 240, 80, 25);
		l4.setBounds(20, 160, 80, 25);
		cb.setBounds(120, 160, 150, 25);
		t1.setBounds(120, 200, 150, 25);
		t2.setBounds(120, 240, 150, 25);
		b.setBounds(100, 305, 100, 25);
	    b.setIcon(ok);
	    l3.setBounds(80, 3, 130, 150);
	    c.setBounds(115, 270, 150, 20);

		add(l3);
		add(l4);
		add(l1);
		add(l2);
		add(t1);
		add(t2);
		add(c);
		add(cb);
		add(b);		
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private void usingKeyListener(JTextField t1, JPasswordField t2)
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorpatientportal", "root" , "Mylaptop@99");
			System.out.println("Connection Established...");
			ps = con.prepareStatement("");

			if(adminLogged)
			{
				String AdminQuery="Select * from adminlogin where UserName='"+t1.getText()+"' and pass='"+t2.getText()+"'";
				rs = ps.executeQuery(AdminQuery);
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login Successful...");
					dispose();
					AdminLogin adminlogin = new AdminLogin(t1.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login/Password");
				}
			}
			
			else if(doctorLogged)
			{
				String DoctorQuery="Select * from doctorlogin where username='"+t1.getText()+"' and password='"+t2.getText()+"'";
				rs = ps.executeQuery(DoctorQuery);
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login Successful...");
					dispose();
					DoctorLogin doctorlogin=new DoctorLogin(t1.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login/Password");
				}
			}
			
			else if(patientLogged)
			{
				String PatientQuery="Select * from patientlogin where userName='"+t1.getText()+"' and password='"+t2.getText()+"'";
				rs = ps.executeQuery(PatientQuery);
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login Successful...");
					dispose();
					AfterLogin patientlogin=new AfterLogin(t1.getText());
					patientlogin.getPatientInfo();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login/Password");
				}
				
			}
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex);
		}
	}
}
