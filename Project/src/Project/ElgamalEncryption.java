package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class ElgamalEncryption extends JFrame {
	JFileChooser fc = new JFileChooser();

	
	private JPanel contentPane;
	private JPasswordField secretkey;
	 BigInteger enc_key = BigInteger.valueOf(0);
	 BigInteger check_dec_key = BigInteger.valueOf(0);
	 BigInteger y1=BigInteger.valueOf(0);
	 BigInteger y2=BigInteger.valueOf(0);
	 BigInteger  alpha, b;
	 BigInteger p=new BigInteger("83158070275398044823462664146203882621921045156636450088535865173160673760397");
	 String str;
	 String spath;
	/**
	 * Launch the application.
	 */
	public void secretkey_generate() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElgamalEncryption frame = new ElgamalEncryption();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public void elgamal_encryption(BigInteger secret_key) {
		
		
        Random sc = new SecureRandom();
		System.out.println("secretKey = " + secret_key);
        //p = BigInteger.probablePrime(512, sc);
      
        alpha = new BigInteger("3");
        b = alpha.modPow(secret_key, p);
      //  System.out.println("p = " + p);
      //  System.out.println("b = " + alpha);
      //  System.out.println("c = " + b);
        
     // Encryption
        
        
    //    JFileChooser f=o1.fc;
      //  System.out.println(f.getSelectedFile().getAbsolutePath());
      
      //  System.out.println("Random access "+spath);
       
        
       
        try{
        	
        	RandomAccessFile f2 = new RandomAccessFile(spath, "r");
            byte [] plaintext = new byte[(int)f2.length()];
            f2.read(plaintext);
        
       
        BigInteger X = new BigInteger(plaintext);
      //
        System.out.println("plaintext = " + X);
        str=X.toString();
       
        BigInteger k = new BigInteger(256, sc);
        y1 = alpha.modPow(k, p);
        y2 = X.multiply(b.modPow(k, p)).mod(p);
        
        
      
               
      //  System.out.println("k = " + k);
      //  System.out.println("y2 = " + y2);
       //
        //System.out.println("alpha^k mod p = " + y1);
        }catch (Exception e){System.out.print("error det");}
        try{
        	PrintWriter writer = new PrintWriter(spath);
            writer.print("");
            writer.print(str);
            writer.close();
        }catch(Exception e){System.out.print(e);}
		
	}
public void elgamal_decryption(BigInteger dec_key)
{
	
	  BigInteger crmodp =  y1.modPow(dec_key, p);
      BigInteger d = crmodp.modInverse(p);
      BigInteger ad = d.multiply(y2).mod(p);
      
      //System.out.println(" decoded msg: " + ad);
      String decoded=new String (ad.toByteArray());
      System.out.println(" decoded msg: " + decoded);
      
      byte[] array =ad.toByteArray();
      if (array[0] == 0) {
          byte[] tmp = new byte[array.length - 1];
          System.arraycopy(array, 1, tmp, 0, tmp.length);
          array = tmp;
      }
      try{
    	  String doc2 = new String(array, "UTF-8");
    	  PrintWriter writer = new PrintWriter(spath);
          writer.print("");
          writer.print(doc2);
          writer.close();
          //System.out.println(doc2);
          
      }catch(Exception e){System.out.println(e);}

}

	/**
	 * Create the frame.
	 */
	public ElgamalEncryption() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(152, 39, 46, 14);
		contentPane.add(label);
		
		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.addActionListener(new ActionListener() {
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
			 		//s1.elgamal_encryption(secretkey);
			 		//s2.rsa_encrypt(fc);
			 		//s3.caeser_encrypt(fc);

			 		
			 		
					
				}catch (Exception e){System.out.print(e);}
			}
		});
		btnChooseFile.setBounds(162, 39, 89, 23);
		contentPane.add(btnChooseFile);
		
		JLabel lblEnterSecretKey = new JLabel("Enter  key");
		lblEnterSecretKey.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterSecretKey.setBounds(27, 117, 113, 36);
		contentPane.add(lblEnterSecretKey);
		
		secretkey = new JPasswordField();
		secretkey.setBounds(173, 127, 134, 20);
		contentPane.add(secretkey);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//	System.out.print(secretkey.getText().toString());
				   
					enc_key = enc_key.add(BigInteger.valueOf(Long.parseUnsignedLong(secretkey.getText().toString())));
					elgamal_encryption(enc_key);
					String spy1=y1.toString();
					String spy2=y2.toString();
					
					//BigInteger enc_key=secretkey.getText().toString();
						
					//System.out.print(enc_key);
					//dispose();
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
						Statement stmt=con.createStatement();
					//	System.out.println(Login.user.getText());
						String sql="Select * from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
						//System.out.print(sql);
						
						//PreparedStatement statement =con.prepareStatement("Select * from login");
						ResultSet rs=stmt.executeQuery(sql);
						
						
						
						
						
						if(rs.next()){
							
						
									
							String IQuery = "UPDATE usertable SET  elgamal_key = '"+secretkey.getText().toString()+"',y1 = '"+spy1+"', y2='"+spy2+"' WHERE user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
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
		btnEncrypt.setBounds(200, 184, 89, 23);
		contentPane.add(btnEncrypt);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
						Statement stmt=con.createStatement();
						//System.out.println(Login.user.getText());
						String sql="Select elgamal_key,y1,y2 from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
						//System.out.print(sql);
						
						//PreparedStatement statement =con.prepareStatement("Select * from login");
						ResultSet rs=stmt.executeQuery(sql);
						rs.next();
						
				        String dec=rs.getString("elgamal_key");
				        
				       String sy1=rs.getString("y1");
				       String sy2=rs.getString("y2");
				  	   
				       y1=y1.add(new BigInteger(sy1));
				       y2=y2.add(new BigInteger(sy2));
				      
						
				        //System.out.println(dec);
					 con.close();
							
			
					
					BigInteger dec_key = BigInteger.valueOf(0);
					check_dec_key = check_dec_key.add(BigInteger.valueOf(Long.parseUnsignedLong(secretkey.getText().toString())));
					dec_key = dec_key.add(BigInteger.valueOf(Long.parseLong(dec)));
				     
					if(dec_key.equals(check_dec_key)){
					
					elgamal_decryption(check_dec_key);
					
					dispose();
					}
					
					else{
						JOptionPane.showMessageDialog(null, "invalid key!");
						
					}
					
					
					
					
					
				    }catch (Exception e){System.out.print(e);}
					
			}
		});
		btnDecrypt.setBounds(315, 184, 89, 23);
		contentPane.add(btnDecrypt);
		
		JButton key = new JButton("show key");
		key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
					Statement stmt=con.createStatement();
					//System.out.println(Login.user.getText());
					String sql="Select elgamal_key from usertable where user='"+Login.user.getText()+"'and pass='"+Login.pass.getText().toString()+"'";
				//	System.out.print(sql);
					
					//PreparedStatement statement =con.prepareStatement("Select * from login");
					ResultSet rs=stmt.executeQuery(sql);
					rs.next();
					
			        String dec=rs.getString("elgamal_key");
			        
				
				
				JOptionPane.showMessageDialog(null, "Your key is "+dec);
			    }catch (Exception e){System.out.print(e);}
			}
		});
		key.setBounds(10, 184, 89, 23);
		contentPane.add(key);
	}

}
