package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.nodak.ndsu.cs.lions.blackwood.Main;
import edu.nodak.ndsu.cs.lions.blackwood.SendEmail;


public class ForgetPasswordFrame extends javax.swing.JFrame{
	
	 		javax.swing.JTextPane errorTextPanel;
		  	javax.swing.JPanel jpanelforget;
		  	javax.swing.JButton  comButton;
		    javax.swing.JLabel confirmEmail;
		    javax.swing.JTextField emailTextField;
		    SendEmail sendmail = new SendEmail();
		    
		    public ForgetPasswordFrame() {
				initComponents();
			}
		    
		    @SuppressWarnings("unchecked")
		    private void initComponents() {
		    	jpanelforget = new JPanel();
		    	comButton = new JButton();
		    	confirmEmail = new JLabel();
		    	emailTextField = new JTextField();
		    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        setTitle("Get your Password and Username");
		        setResizable(true);
		        setPreferredSize(new Dimension(500,200));
		        
		        
		       
		        jpanelforget.setEnabled(false);
		        jpanelforget.setPreferredSize(new Dimension(500,200));
		        getContentPane().add(jpanelforget);
		        jpanelforget.setBackground(Main.COLOR_YELLOW);
		        jpanelforget.setForeground(Main.COLOR_YELLOW);
		  
		       // confirmEmail.setLabelFor(emailTextField);
		        confirmEmail.setText("Give your Email: ");
		        confirmEmail.setBounds(10, 10, 80, 25);
		        emailTextField.setBounds(100, 10, 160, 25);
		        
		        jpanelforget.add(confirmEmail);
		        jpanelforget.add(emailTextField);
		       
		       // jpanelforget.add(comButton, BorderLayout.SOUTH);
		        
		        comButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/passwordfinder.gif"))); // NOI18N
		        comButton.setBorder(null);
		        comButton.setBorderPainted(false);
		        comButton.setContentAreaFilled(false);
		        comButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/passwordfinder.gif"))); // NOI18N
		        comButton.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                comButtonActionPerformed(evt);
		            }
					
		        });
		       
		        pack();
		    	
		    }

		    private void comButtonActionPerformed(ActionEvent evt) {

		    	final String email = emailTextField.getText();
		    	 
				if(validateEmail() && (email == (sendmail.getAddresses()).toString())){
					try
					{
						  
							try
							{
								sendmail.sendMail();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Sorry, I could not connect to LambdaMoo using that user name and password.\n" + " Exception: " + ex.getMessage());
						return;
					}
				}
			}  
	


	 boolean validateEmail(){
		
		String email = emailTextField.getText();
		boolean isValid = true;
		if (email.lastIndexOf("@") < email.lastIndexOf("."))
		{
			emailTextField.setForeground(new Color(51,51,51));
			isValid = true;
		}
		else
		{
			emailTextField.setForeground(Color.RED);
			errorTextPanel.setText(errorTextPanel.getText() + "Your email address is incorrect.\n");
			errorTextPanel.setEnabled(true);
			isValid = false;
		}

		return isValid;
	}
	 

}
