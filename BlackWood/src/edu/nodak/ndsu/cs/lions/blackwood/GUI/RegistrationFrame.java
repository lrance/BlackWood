/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrationFrame.java
 *
 * Created on Jan 11, 2011, 1:57:13 PM
 */

package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import edu.nodak.ndsu.cs.lions.blackwood.Main;
import edu.nodak.ndsu.cs.lions.blackwood.MooConnection;
import edu.nodak.ndsu.cs.lions.blackwood.RegistrationListener;
import edu.nodak.ndsu.cs.lions.blackwood.SendEmail;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author mooadmin
 */
public class RegistrationFrame extends JFrame {

    private BarChart chart;
    private String[][] hoods,stores;
    private String[] storelist, hoodlist;
    private Boolean wait=true;
    private Boolean check=false;
    private Boolean storeAvailable=false;
    private MooConnection moo = new MooConnection();
    
    
    
    
    /** Creates new form RegistrationFrame */
    public RegistrationFrame() {
        initComponents();
        chart=new BarChart();
        hoods=new String[0][];
        stores=new String[0][];
        storelist=new String[0];
        hoodlist=new String[0];
        moo.Connect("lions.cs.ndsu.nodak.edu", 7779);
        moo.addListener(new RegistrationListener(this));
        moo.send("getHoodsWithStores");
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void hoods(String string) {
        int a=0;
        String temp=string;
        ArrayList<String> temp1=new ArrayList<String>();
        ArrayList<String> temp2=new ArrayList<String>();
        while(true){
            if(temp.indexOf("<HOOD>")>=0){
                temp1.add(temp.substring(temp.indexOf("<NUMBER>")+8,temp.indexOf("</NUMBER>")));
                temp2.add(temp.substring(temp.indexOf("<NAME>")+6,temp.indexOf("</NAME>")));
                temp=temp.substring(temp.indexOf("</HOOD>")+7);
                a++;
            }else{
                break;
            }
        }
        hoodlist=new String[a+1];
        hoodlist[0]=" ";
        hoods=new String[a][2];
        for(int i=0;i<a;i++){
            hoods[i][1]=temp1.get(i);
            hoods[i][0]=temp2.get(i);
            hoodlist[i+1]=temp2.get(i);
        }
        
        neighborhoodComboBox.setEnabled(true);
        neighborhoodComboBox.setModel(new javax.swing.DefaultComboBoxModel(hoodlist));
        neighborhoodComboBox.setSelectedIndex(0);
        wait=false;
    }

    public void consumerPop(String string) {
        int a=0;
        String temp=string;
        ArrayList<String> temp1=new ArrayList<String>();
        ArrayList<String> temp2=new ArrayList<String>();
        while(true){
            if(temp.indexOf("<INFORMATION>")>=0){
                temp1.add(temp.substring(temp.indexOf("<NUMBER>")+8,temp.indexOf("</NUMBER>")));
                temp2.add(temp.substring(temp.indexOf("<TYPE>")+6,temp.indexOf("</TYPE>")));
                temp=temp.substring(temp.indexOf("</INFORMATION>")+14);
                a++;
            }else{
                break;
            }
        }
        String[][] s=new String[a][2];
        for(int i=0;i<a;i++){
            s[i][1]=temp1.get(i);
            s[i][0]=temp2.get(i);
        }
        chart= new BarChart(400,75,225,100,s);
        repaint();
    }

    public void stores(String string) {
        int a=0;
        String temp=string;
        ArrayList<String> temp1=new ArrayList<String>();
        ArrayList<String> temp2=new ArrayList<String>();
        ArrayList<String> temp3= new ArrayList<String>();
        while(true){
            if(temp.indexOf("<STORE>")>=0){
                temp2.add(temp.substring(temp.indexOf("<NUMBER>")+8,temp.indexOf("</NUMBER>")));
                temp1.add(temp.substring(temp.indexOf("<NAME>")+6,temp.indexOf("</NAME>")));
                if(check){
                    if(temp.substring(temp.indexOf("<NAME>")+6,temp.indexOf("</NAME>")).equals(storeComboBox.getSelectedItem().toString())){
                        storeAvailable=true;
                        wait=false;
                        check=false;
                        return;
                    }
                }
                temp3.add(temp.substring(temp.indexOf("<TYPE>")+6, temp.indexOf("</TYPE>")));
                temp=temp.substring(temp.indexOf("</STORE>")+8);
                a++;
            }else{
                break;
            }
        }
        if(check){
            wait=false;
            check=false;
        }
        storelist=new String[a+1];
        storelist[0]=" ";
        stores=new String[a][3];
        for(int i=0;i<a;i++){
        	System.out.println("loading stores");
            stores[i][1]=temp1.get(i);
            stores[i][0]=temp2.get(i);
            stores[i][2]=temp3.get(i);
            storelist[i+1]=temp1.get(i);
        }

        storeComboBox.setEnabled(true);
        storeComboBox.setModel(new javax.swing.DefaultComboBoxModel(storelist));
        storeComboBox.setSelectedIndex(0);
    }
	
	public enum RegistrationStatus {SUCCESS, FAILED, NOT_ATTEMPTED}
	public RegistrationStatus registrationStatus = RegistrationStatus.NOT_ATTEMPTED;
	public String errorMessage = "";



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        neighborhoodLabel = new javax.swing.JLabel();
        passwordHintLabel = new javax.swing.JLabel();
        storeLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        neighborhoodComboBox = new javax.swing.JComboBox();
        //passwordhint = new javax.swing.JComboBox();
        passwordhint = new javax.swing.JTextField();
        storeComboBox = new javax.swing.JComboBox();
        registerButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        confirmPasswordTextField = new javax.swing.JPasswordField();
        emailTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        errorTextPanel = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        
        previousButton = new javax.swing.JButton();
        storeImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Blackwood");
        setBackground(Main.COLOR_YELLOW);
        setForeground(Main.COLOR_YELLOW);
        setResizable(false);

        jPanel1.setBackground(Main.COLOR_YELLOW);
        jPanel1.setForeground(Main.COLOR_YELLOW);
        jPanel1.setEnabled(true);

        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setText("Requested User Name:");

        passwordLabel.setLabelFor(passwordTextField);
        passwordLabel.setText("Password:");

        confirmPasswordLabel.setLabelFor(confirmPasswordTextField);
        confirmPasswordLabel.setText("Confirmation Password:");

        emailLabel.setLabelFor(emailTextField);
        emailLabel.setText("Email Address:");
        
        passwordHintLabel.setLabelFor(passwordhint);
        passwordHintLabel.setText("Choose a Password Hint: ");
        
        //passwordhint.setModel( new javax.swing.DefaultComboBoxModel(new String[] { "MyBirthday", "My Mom middle name", "My high school name", "My pet's name"}));
        //passwordhint.setText();
        passwordhint.setEnabled(true);
        passwordhint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordhintActionPerformed(evt);
            }
        });
        
        
        
        
        neighborhoodLabel.setLabelFor(neighborhoodComboBox);
        neighborhoodLabel.setText("Choose a Neighborhood:");

        storeLabel.setLabelFor(storeComboBox);
        storeLabel.setText("Choose a Store:");

        neighborhoodComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blackwood Mine", "The Hill Country", "The North Side", "The Farm Country", "The Western Outpost", "The Open Range", "Riverside", "The Forest", "The South Side", "The West Side", "Lumber Town", "Fort Wood Trading Post" }));
        neighborhoodComboBox.setEnabled(true);
        neighborhoodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neighborhoodComboBoxActionPerformed(evt);
            }
        });
        
        

        storeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blacksmith Owner", "CartWright Owner", "Dry Goods Owner", "Leather Maker Owner", "Lumber Owner", "Stable Owner", "Tailor Owner", "Wheelwright Owner ", "Wood Lot Owner" }));
        storeComboBox.setEnabled(true);
        storeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeComboBoxActionPerformed(evt);
            }
        });
        
        

        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgRegister.gif"))); // NOI18N
        registerButton.setBorder(null);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgRegisterPressed.gif"))); // NOI18N
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgCancel.gif"))); // NOI18N
        cancelButton.setBorder(null);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgCancelPressed.gif"))); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        errorTextPanel.setBackground(Main.COLOR_YELLOW);
        errorTextPanel.setBorder(null);
        errorTextPanel.setEditable(false);
        errorTextPanel.setEnabled(false);
        jScrollPane1.setViewportView(errorTextPanel);

        jPanel2.setBackground(Main.COLOR_YELLOW);

      
        nextButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgNext.gif"))); // NOI18N
        nextButton.setBorder(null);
        nextButton.setContentAreaFilled(false);
        nextButton.setEnabled(true);
        nextButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgNextPressed.gif"))); // NOI18N
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        previousButton.setFont(new java.awt.Font("Tahoma", 0, 12));
        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgPrevious.gif"))); // NOI18N
        previousButton.setBorder(null);
        previousButton.setContentAreaFilled(false);
        previousButton.setEnabled(true);
        previousButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgPreviousPressed.gif"))); // NOI18N
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Neighborhood Information");

        jLabel3.setText("Store Information");
       
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storeImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nextButton)
                    .addComponent(previousButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(storeImg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(confirmPasswordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passwordHintLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(emailLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(neighborhoodLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(storeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(neighborhoodComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(storeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 176, Short.MAX_VALUE)
                            .addComponent(passwordhint, javax.swing.GroupLayout.Alignment.LEADING, 0, 176, Short.MAX_VALUE )
                            .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(registerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(33, 33, 33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                	.addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) 
                    .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
               	.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordHintLabel)
                    .addComponent(passwordhint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
               	.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(neighborhoodLabel)
                    .addComponent(neighborhoodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeLabel)
                    .addComponent(storeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerButton)
                    .addComponent(cancelButton))
                .addContainerGap(83, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void passwordhintActionPerformed(java.awt.event.ActionEvent evt){
    	//String temp = passwordhint.getSelectedItem().toString();
    	//passwordhint.setEditable(true);
    }

	private void neighborhoodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neighborhoodComboBoxActionPerformed
		// TODO add your handling code here:
            if(neighborhoodComboBox.getSelectedIndex()==0){
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
                storeComboBox.removeAllItems();
                storeComboBox.setEnabled(true);
                chart = new BarChart();
            }else{
            if(hoodlist.length>0){
            for(int i=0;i<hoodlist.length;i++){
                if(hoodlist[i].equals(neighborhoodComboBox.getSelectedItem().toString())){
                    moo.send("getConsumerPopulation \""+hoods[i-1][1]+"\"");
                    moo.send("getStores \""+hoods[i-1][1]+"\"");
                }
            }
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
            }
           }
            repaint();
	}//GEN-LAST:event_neighborhoodComboBoxActionPerformed

	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
		String temp = neighborhoodComboBox.getSelectedItem().toString();
                String storeNum="";
                for(int i=0;i<hoods.length;i++){
                    if(temp.equalsIgnoreCase(hoods[i][0])){
                        storeNum=hoods[i][1];
                    }
                }
                if(!storeNum.equals("")){
                    wait=true;
                    check=true;
                moo=new MooConnection();
                moo.Connect("lions.cs.ndsu.nodak.edu", 7779);
                moo.addListener(new RegistrationListener(this));
                moo.send("getStores "+storeNum);
                while (wait)
			{
				try
				{
					Thread.sleep(300);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
                if(!storeAvailable){
                    errorTextPanel.setText("This Store Has Already Been Taken");
                    return;
                }
            // Is the user name valid?

		String username = usernameTextField.getText();
		String password = new String(passwordTextField.getPassword());
		String confirmPassword = new String(confirmPasswordTextField.getPassword());
		String email = emailTextField.getText();
		String password_hint = passwordhint.getText();

		errorTextPanel.setText("");
		errorTextPanel.setEnabled(false);

		usernameLabel.setForeground(new Color(51,51,51));
		passwordLabel.setForeground(new Color(51, 51, 51));
		confirmPasswordLabel.setForeground(new Color(51,51,51));
		emailLabel.setForeground(new Color(51,51,51));

		boolean isUserValid = validateUsername();
		boolean isPasswordValid = validatePassword();
		boolean isEmailValid = validateEmail();

		if (isUserValid && isPasswordValid && isEmailValid)
		{
			moo = new MooConnection();
			moo.Connect("lions.cs.ndsu.nodak.edu", 7777);

			moo.addListener(new RegistrationListener(this));
			//moo.send("create " + username + " " + password);
			moo.send("make_player" + " " + username + " " + password + " " + email + " " + password_hint);
			
			while (registrationStatus == RegistrationStatus.NOT_ATTEMPTED)
			{
				try
				{
					Thread.sleep(300);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

			if (registrationStatus == RegistrationStatus.SUCCESS)
			{
				errorTextPanel.setText("Connecting.  Please Wait.");

				SendEmail sendmail = new SendEmail(email, username, password);
				try
				{
					sendmail.sendMail();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


				//need to add something that will assign the store to the player registering                               
				JOptionPane.showMessageDialog(null, "Your account has been created and is ready to use.\nAn email has been sent to you with your username and password.");
				this.setVisible(false);
				moo.send("home");
				moo.send("@quit");

				
                moo.Connect("lions.cs.ndsu.nodak.edu", 7777);	
				moo.send("make_player" + "\t" + username + "\t" + password + "\t" + email);
				moo.send("@quit");
				
			}
			else if (registrationStatus == RegistrationStatus.FAILED)
			{
				errorTextPanel.setText(this.errorMessage);
				errorTextPanel.setEnabled(true);
				usernameLabel.setForeground(Color.RED);
				registrationStatus = RegistrationStatus.NOT_ATTEMPTED;
			}
			// try a moo connection

			// create a user

			// email the user

			// print message and close
		}
            }
	}//GEN-LAST:event_registerButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		LoginFrame logFrame = new LoginFrame();
		logFrame.setLocationRelativeTo(null);
		logFrame.setVisible(true);
		this.setVisible(false);
	}//GEN-LAST:event_cancelButtonActionPerformed

        private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
            // TODO add your handling code here:
            chart.previous();
            repaint();
        }//GEN-LAST:event_previousButtonActionPerformed

        private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
            // TODO add your handling code here:
            chart.next();
            repaint();
        }//GEN-LAST:event_nextButtonActionPerformed

        private void storeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeComboBoxActionPerformed
            // TODO add your handling code here:
            
            if(storeComboBox.getSelectedIndex() == 0||storeComboBox.getItemCount() == 0){
                storeImg.setIcon(new javax.swing.ImageIcon());
            }else{
            	System.out.println("Store combo box index = " + storeComboBox.getSelectedIndex());
            	System.out.println("stores length =");
            	System.out.println(stores.length);
            	System.out.println("stores contents =");
            	System.out.println(stores.toString());
            	String storeSelection = stores[storeComboBox.getSelectedIndex()-1][2];
            	System.out.println("store selection = " +storeSelection);
            	//String storeSelection = stores[storeComboBox.getSelectedIndex()][0];
            
            if(storeSelection.equals("Blacksmith Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/blacksmith_register.jpg")));
            else if(storeSelection.equals("Cartwright Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/cartwright_register.jpg")));
            else if(storeSelection.equals("Dry Goods Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/drygoods_register.jpg")));
            else if(storeSelection.equals("Leather Products Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/leathermaker_register.jpg")));
            else if(storeSelection.equals("Woodlot Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/woodlotoperator_register.jpg")));
            else if(storeSelection.equals("Stable Products Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/stableoperator_register.jpg")));
            else if(storeSelection.equals("Tailoring Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/tailor_register.jpg")));
            else if(storeSelection.equals("Wheelwright Merchant"))
                storeImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/wheelwright_register.jpg")));
            }
        }//GEN-LAST:event_storeComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPasswordField confirmPasswordTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextPane errorTextPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox neighborhoodComboBox;
    private javax.swing.JTextField passwordhint;
    private javax.swing.JLabel neighborhoodLabel;
    private javax.swing.JButton nextButton;
    
    private javax.swing.JLabel passwordHintLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JComboBox storeComboBox;
    private javax.swing.JLabel storeImg;
    private javax.swing.JLabel storeLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

	private boolean validateUsername()
	{
		String username = usernameTextField.getText();
		boolean isValid = true;
		if (username.indexOf('<') == -1 && username.indexOf('>') == -1 && username.indexOf(' ') == -1)
		{
			usernameLabel.setForeground(new Color(51,51,51));
			isValid = true;
		}

		else
		{
			usernameLabel.setForeground(Color.RED);
			errorTextPanel.setText(errorTextPanel.getText() + "The username cannot contain <, >, or spaces.\n");
			errorTextPanel.setEnabled(true);
			isValid = false;
		}

		return isValid;
	}

	private boolean validatePassword()
	{
		String password = new String(passwordTextField.getPassword());
		String confirmPassword = new String(confirmPasswordTextField.getPassword());
		boolean isValid = true;

		if (password.equals(confirmPassword))
		{
			if (password.equals(""))
			{
				passwordLabel.setForeground(Color.RED);
				confirmPasswordLabel.setForeground(Color.RED);
				errorTextPanel.setText(errorTextPanel.getText() + "The password field is blank.\n");
				errorTextPanel.setEnabled(true);
				isValid = false;
			}
			else
			{
				passwordLabel.setForeground(new Color(51,51,51));
				confirmPasswordLabel.setForeground(new Color(51,51,51));
				isValid = true;
			}
		}

		else
		{
			passwordLabel.setForeground(Color.RED);
			confirmPasswordLabel.setForeground(Color.RED);
			errorTextPanel.setText(errorTextPanel.getText() + "The passwords do not match.\n");
			JOptionPane.showConfirmDialog(null, "Username/password invaild", 
					"Username or password was invalid, please try again.", JOptionPane.YES_NO_CANCEL_OPTION);
			errorTextPanel.setEnabled(true);
			isValid = false;
		}

		return isValid;
	}

	private boolean validateEmail()
	{
		String email = emailTextField.getText();
		boolean isValid = true;
		if (email.lastIndexOf("@") < email.lastIndexOf("."))
		{
			emailLabel.setForeground(new Color(51,51,51));
			isValid = true;
		}
		else
		{
			emailLabel.setForeground(Color.RED);
			errorTextPanel.setText(errorTextPanel.getText() + "Your email address is incorrect.\n");
			errorTextPanel.setEnabled(true);
			isValid = false;
		}

		return isValid;
	}
        @Override
        public void paint(Graphics g){
            super.paint(g);
            Color old= g.getColor();
            g.setColor(Color.BLACK);
            chart.paint(g);
            g.setColor(old);
        }
}