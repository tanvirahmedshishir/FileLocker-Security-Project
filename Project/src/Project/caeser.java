package Project;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class caeser extends JFrame {

	private JPanel contentPane;
	JFileChooser fc = new JFileChooser();
	String str;
	String spath;
	 static int enc_key;
	 static int check_dec_key ;

	/**
	 * Launch the application.
	 */
	  static char[] chars = {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
          'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
          'y', 'z', '0', '1', '2', '3', '4', '5',
          '6', '7', '8', '9', 'A', 'B', 'C', 'D',
          'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
          'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
          'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
          '#', '$', '%', '^', '&', '(', ')', '+',
          '-', '*', '/', '[', ']', '{', '}', '=',
          '<', '>', '?', '_', '"', '.', ',', ' '
  };
	private JPasswordField secretkey22;


  public void ciper_encrypt(String path) throws IOException, SQLException {

        
      //int key = Integer.parseInt(d.get_key_database(user));
      String encrypt_message = readFile(path);
      String enc = encrypt(encrypt_message);
      System.out.println("Encryption Done!");
      System.out.println("Encrypted text: " + enc);
      try{
      	PrintWriter writer = new PrintWriter(spath);
          writer.print("");
          writer.print(enc);
          writer.close();
      }catch(Exception e){System.out.print(e);}
  }
  public void ciper_decryption(String path) throws IOException, SQLException {

      String encrypt_message = readFile(spath);
      String dec = decrypt(encrypt_message);
      System.out.println("Decryption Done!");
      System.out.println("Decrypted text: " + dec);
      try{
        	PrintWriter writer = new PrintWriter(spath);
            writer.print("");
            writer.print(dec);
            writer.close();
        }catch(Exception e){System.out.print(e);}
  }
  // Caesar cipher
  static String encrypt(String text)
  {
      char[] plain = text.toCharArray();

      for (int i = 0; i < plain.length; i++) {
          for (int j = 0; j < chars.length; j++) {
              if (j <= chars.length - enc_key) {
                  if (plain[i] == chars[j]) {
                      plain[i] = chars[j + enc_key];
                      break;
                  }
              }
              else if (plain[i] == chars[j]) {
                  plain[i] = chars[j - (chars.length - enc_key + 1)];
              }
          }
      }
      return String.valueOf(plain);
  }

  static String decrypt(String cip)
  {
      char[] cipher = cip.toCharArray();
      for (int i = 0; i < cipher.length; i++) {
          for (int j = 0; j < chars.length; j++) {
              if (j >= check_dec_key && cipher[i] == chars[j]) {
                  cipher[i] = chars[j - check_dec_key];
                  break;
              }
              if (cipher[i] == chars[j] && j < check_dec_key) {
                  cipher[i] = chars[(chars.length - check_dec_key +1) + j];
                  break;
              }
          }
      }
      return String.valueOf(cipher);
  }

  public String readFile(String fileName) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      try {
          StringBuilder sb = new StringBuilder();
          String line = br.readLine();

          while (line != null) {
              sb.append(line);
              line = br.readLine();
          }
          return sb.toString();
      } finally {
          br.close();
      }
  }
	
	
	
	
	public static void secretkey2_generate() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					caeser frame = new caeser();
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
	
	public caeser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEntersecretkey2 = new JLabel("Enter key");
		lblEntersecretkey2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEntersecretkey2.setBounds(40, 87, 157, 36);
		contentPane.add(lblEntersecretkey2);
		
		secretkey22 = new JPasswordField();
		secretkey22.setBounds(207, 97, 157, 20);
		contentPane.add(secretkey22);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try{
					//	System.out.print(secretkey2.getText().toString());
				   
					String test = secretkey22.getText().toString();
					enc_key = Integer.parseInt(test);
					ciper_encrypt(spath);
					
					//System.out.print(enc_key);
					//dispose();
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
						Statement stmt=con.createStatement();
						//System.out.println(Login.user.getText());
						String sql="Select * from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
						//System.out.print(sql);
						
						//PreparedStatement statement =con.prepareStatement("Select * from login");
						ResultSet rs=stmt.executeQuery(sql);
						
						
						
						
						
						if(rs.next()){
							
						
									
							String IQuery = "UPDATE usertable SET  caeser = '"+enc_key+"' WHERE user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
							stmt.execute(IQuery);
									
							
			            }
						else
							JOptionPane.showMessageDialog(null, "username and password didn't match");
	                    
					 con.close();
							
					}catch(Exception e){System.out.print(e);}
					
					
					dispose();
					
					
					
					
				    }catch (Exception e){System.out.print(e);}
					
			
				
				
			}
		});
		btnEncrypt.setBounds(182, 187, 89, 23);
		contentPane.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					Statement stmt=con.createStatement();
					//System.out.println(Login.user.getText());
					String sql="Select caeser from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
					//System.out.print(sql);
					
					//PreparedStatement statement =con.prepareStatement("Select * from login");
					ResultSet rs=stmt.executeQuery(sql);
					rs.next();
					
			        String dec=rs.getString("caeser");
			        
			    
			  	    
			        //System.out.println(dec);
				 con.close();
						
		
				
				
			   String test = secretkey22.getText().toString();
			   
				check_dec_key = Integer.parseInt(test);
				int dec_key = Integer.parseInt(dec);
			     
				if(dec_key==check_dec_key){
				
					ciper_decryption(spath);
				
				dispose();
				}
				
				else{
					JOptionPane.showMessageDialog(null, "invalid key!");
					
				}
				
				
				
			    }catch (Exception e){System.out.print(e);}
				
			}
		});
		btnDecrypt.setBounds(315, 187, 89, 23);
		contentPane.add(btnDecrypt);
		
		JButton btnNewButton = new JButton("Choose file");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				  	JButton open =new JButton();
			 		
			 		fc.setCurrentDirectory(new java.io.File("C:/Users/Shishir/Desktop"));
			 		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			 		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
			 			//
			 		}
			 		System.out.println("You choose: "+fc.getSelectedFile().getAbsolutePath());
			 		spath = fc.getSelectedFile().getAbsolutePath();
			 		//System.out.println(spath);
			 		
			 		//secret s1=new secret();
			 		//s1.elgamal_encryption(secretkey2);
			 		//s2.rsa_encrypt(fc);
			 		//s3.caeser_encrypt(fc);

			 		
			 		
					
				}catch (Exception e){System.out.print(e);}
			}
		});
		btnNewButton.setBounds(151, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnShowKey = new JButton("show key");
		btnShowKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
try{
					
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					Statement stmt=con.createStatement();
					//System.out.println(Login.user.getText());
					String sql="Select caeser from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
				//	System.out.print(sql);
					
					//PreparedStatement statement =con.prepareStatement("Select * from login");
					ResultSet rs=stmt.executeQuery(sql);
					rs.next();
					
			        String dec=rs.getString("caeser");
			        
				
				
				JOptionPane.showMessageDialog(null, "Your key is "+dec);
			    }catch (Exception e){System.out.print(e);}
				
			}
		});
		btnShowKey.setBounds(22, 187, 89, 23);
		contentPane.add(btnShowKey);
	}
}
