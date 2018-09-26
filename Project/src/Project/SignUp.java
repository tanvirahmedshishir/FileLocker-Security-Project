package Project;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JButton btnSignUp;

	/**
	 * Launch the application.
	 */
	public  void signUp_test() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUp = new JLabel("Sign up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(Color.RED);
		lblSignUp.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblSignUp.setBounds(95, 11, 203, 27);
		contentPane.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUsername.setBounds(38, 65, 196, 27);
		contentPane.add(lblUsername);
		
		user = new JTextField();
		user.setBounds(38, 98, 240, 27);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPassword.setBounds(43, 158, 235, 27);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(38, 196, 240, 27);
		contentPane.add(pass);
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					//Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					Statement stmt=con.createStatement();
					
					String sql="Select * from usertable where user='"+user.getText()+"'and pass='"+pass.getText().toString()+"'";
					String query="Insert into usertable(user,pass) values ('"+user.getText()+"','"+pass.getText().toString()+"')";
					String qry="Select * from usertable where user='"+user.getText()+"'";
					
					//PreparedStatement statement =con.prepareStatement("Select * from login");
					ResultSet rs=stmt.executeQuery(sql);
					ResultSet rs2=stmt.executeQuery(qry);
					//ResultSet rs3=stmt.executeQuery(query);
					
					//System.out.println(rs.next());
					
					if(rs2.next())
						
						    JOptionPane.showMessageDialog(null, "this user is taken already");
					else{
						
						String IQuery = "INSERT INTO usertable(user,pass,caeser) VALUES('"+user.getText()+"', '"+pass.getText().toString()+"','0')";
					
						stmt.execute(IQuery);
						JOptionPane.showMessageDialog(null, "signup completed!");
					}
                    
					con.close();
						
				}catch(Exception e){System.out.print(e);}
			}
		});
		btnSignUp.setBounds(312, 198, 89, 23);
		contentPane.add(btnSignUp);
	}
}
