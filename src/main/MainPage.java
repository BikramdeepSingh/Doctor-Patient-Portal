package main;


import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import signInSignUp.SignIn;
import signInSignUp.SignUp;


import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
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
	public MainPage() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon logoimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\mainpageicon.png");
		frame = new JFrame("Doctor Patient Portal");
		frame.getContentPane().setBackground(UIManager.getColor("MenuBar.background"));
		frame.setBounds(100, 100, 538, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(logoimg.getImage());
		frame.setLocationRelativeTo(null);
		//ImageIcon docimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\doctor3.jpg");
		ImageIcon loginimg=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\\\mainpage2.jpg");
		JLabel lblNewLabel = new JLabel("Doctor Patient Portal");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(292, 0, 233, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane txtpnTheOnlyWay = new JTextPane();
		txtpnTheOnlyWay.setBackground(UIManager.getColor("MenuBar.background"));
		txtpnTheOnlyWay.setFont(new Font("Arial", Font.PLAIN, 17));
		txtpnTheOnlyWay.setText("To keep the body in good health is a duty, otherwise we shall not be able to keep our mind strong and clear.");
		txtpnTheOnlyWay.setBounds(292, 143, 233, 95);
		txtpnTheOnlyWay.setEditable(false);
		//txtpnTheOnlyWay.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(txtpnTheOnlyWay);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(357, 30, 96, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(279, 30, 233, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					@SuppressWarnings("unused")
					SignIn login=new SignIn();
					frame.dispose();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SignIn login=new SignIn();
				frame.dispose();
			}
		});
		ImageIcon log=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\login.png");
		btnNewButton.setIcon(log);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(292, 65, 222, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel(loginimg);
		lblNewLabel_4.setBounds(0, 0, 269, 245);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					@SuppressWarnings("unused")
					SignUp signup=new SignUp();
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SignUp signup=new SignUp();
			}
		});
		ImageIcon sign=new ImageIcon("D:\\#6 Projects\\Major Projects\\Efficient Doctor Patient Portal\\signup.png");
		btnNewButton_1.setIcon(sign);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_1.setBounds(292, 104, 222, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(345, 57, 109, 2);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(UIManager.getColor("List.dropLineColor"));
		separator.setForeground(UIManager.getColor("List.foreground"));
		separator.setBounds(20, 266, 0, 141);
		frame.getContentPane().add(separator);
		
	}
}
