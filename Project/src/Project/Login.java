package Project;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	public JPanel contentPane;
	public static JTextField user;
	public static JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public void login_test() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.RED);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblLogin.setBounds(35, 26, 294, 27);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUsername.setBounds(23, 67, 192, 27);
		contentPane.add(lblUsername);
		
		user = new JTextField();
		user.setBounds(23, 104, 208, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPassword.setBounds(23, 146, 208, 27);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					Statement stmt=con.createStatement();
					
					String sql="Select * from usertable where user='"+user.getText()+"'and pass='"+pass.getText().toString()+"'";
					
					//PreparedStatement statement =con.prepareStatement("Select * from login");
					ResultSet rs=stmt.executeQuery(sql);
					
					//System.out.println(rs.next());
					
					if(rs.next()){
						JOptionPane.showMessageDialog(null, "Logged in successfully!");
					    Menu f1=new Menu();
					    f1.chooseOption();
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                    
					con.close();
						
				}catch(Exception e){System.out.print(e);}
			}
		});
		btnLogin.setBounds(298, 178, 89, 23);
		contentPane.add(btnLogin);
		
		pass = new JPasswordField();
		pass.setBounds(23, 184, 208, 20);
		contentPane.add(pass);
	 }
	
	
}
