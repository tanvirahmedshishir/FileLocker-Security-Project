package Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.*;
import java.util.*;
import java.security.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Menu extends JFrame {
	
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/* public String getPathname() {
	        return spath;
	    }
	    public void setPathname(String spath) {
	        this.spath = this.fc.getSelectedFile().getAbsolutePath();
	    }*/
	
	
	public void chooseOption() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnElgamal = new JButton("ElGamal ");
		btnElgamal.setBounds(170, 28, 153, 36);
		btnElgamal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ElgamalEncryption secretkey=new ElgamalEncryption();
				  	secretkey.secretkey_generate();
				  	
					
				}catch(Exception e){System.out.print(e);}
				
			}
		});
		contentPane.setLayout(null);
		btnElgamal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(btnElgamal);
		
		JButton btnCaeserCipher = new JButton("Caeser Cipher");
		btnCaeserCipher.setBounds(170, 86, 153, 36);
		btnCaeserCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					caeser secretkey2=new caeser();
				  	caeser.secretkey2_generate();
				  	
					
				}catch(Exception e){System.out.print(e);}
				
			}
		});
		btnCaeserCipher.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnCaeserCipher);
		
		JButton btnRsa = new JButton("RSA");
		btnRsa.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnRsa.setBounds(170, 133, 153, 36);
		contentPane.add(btnRsa);
	}
}
