/*
 * LoginFrameSwing.java
 *
 * Created on Mar 26, 2010, 7:47:22 PM
 */
package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import edu.nodak.ndsu.cs.lions.blackwood.CommandListener;
import edu.nodak.ndsu.cs.lions.blackwood.DebugPane;
//import edu.nodak.ndsu.cs.lions.blackwood.GenericListener;
import edu.nodak.ndsu.cs.lions.blackwood.Main;
import edu.nodak.ndsu.cs.lions.blackwood.MooConnection;

/**
 *
 * @author foertsch
 */
public class LoginFrame extends javax.swing.JFrame
{

	public static Logger logger = Logger.getLogger(LoginFrame.class);
	/** Text file for the top part of the Login Frame. */
	public static final URL VERSION_FILE_PATH = LoginFrame.getURL("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Text/version.txt");
	public static MooConnection moo;
	public enum LoginStatus {SUCCESS, FAILED, NOT_ATTEMPTED}
	public LoginStatus loginStatus = LoginStatus.NOT_ATTEMPTED;

	public PrimaryGameFrame gameFrame = null;
	
	static DebugPane debugger;

	/**
	 * The constructor for the Login Frame.
	 * @param moo
	 * @param genericListener
	 * @param debugger
	 * @param host
	 * @param userName
	 * @param port
	 * @param password
	 */
	public LoginFrame(){
		
			this(moo, debugger, "lions.cs.ndsu.nodak.edu", "7779", "", "");
	}
	public LoginFrame(MooConnection moo, DebugPane debugger, String host, String port, String userName, String password)
	{
		this.moo = moo;
		this.debugger = debugger;
		initComponents();

		this.hostTextField.setText(host);
		this.portTextField.setText(port);
		this.userNameTextField.setText(userName);
		this.passwordTextField.setText(password);

		gameFrame = new PrimaryGameFrame(moo, debugger);
		CommandListener commandListener = new CommandListener(gameFrame, moo, this);
		moo.addListener(commandListener);

	}

	public static URL getURL(String path)
	{
		return LoginFrame.class.getResource(path);
	}

	public String initializeVersionString()
	{
		String results = new String();
		InputStream inputFileStream = null;
		try
		{
			inputFileStream = VERSION_FILE_PATH.openStream();
		}
		catch (IOException e)
		{
			logger.error("Failed read of Version File.", e);
			System.exit(0);
		}
		catch (NullPointerException e) // occurs when the VERSION_FILE_PATH doesn't point to a file.
		{
			logger.error("Failed read of Version File.", e);
			System.exit(0);
		}

		Scanner scan = new Scanner(new InputStreamReader(inputFileStream));
		while (scan.hasNextLine())
		{
			results += scan.nextLine() + "\n";
		}

		scan.close();

		try
		{
			inputFileStream.close();
		}
		catch (IOException e)
		{
			logger.error("IOException", e);
		}

		return results;
	}

	public class KeyListen implements KeyListener
	{

		public void keyTyped(KeyEvent e)
		{
		}

		public void keyPressed(KeyEvent e)
		{
		}

		public void keyReleased(KeyEvent e)
		{
			int code = e.getKeyCode();
			if (KeyEvent.getKeyText(code).equals("Enter"))
			{
				loginButton.doClick();
			}
		}
	}

	public void appendToVersionPane(String message)
	{
		versionTextArea.append(message + "\n");
	}

	public void login()
	{
		boolean isValid = true;
		int portNumber = 0;
		final String host = hostTextField.getText();
		final String port = portTextField.getText();
		final String username = userNameTextField.getText();
		final String password = new String(passwordTextField.getPassword()); // necessary because getText() is depreciated
		final String email = passwordTextField.getText();
		
		
		if (host.length() == 0)
		{
			appendToVersionPane("Error");
			JOptionPane.showConfirmDialog(null, "Please enter a name", 
					"Error", JOptionPane.YES_OPTION);
			
			isValid = false;
		}
		if (port.length() == 0)
		{
			appendToVersionPane("Error");
			JOptionPane.showConfirmDialog(null, "Please enter a port number", 
					"Error", JOptionPane.YES_OPTION);
			isValid = false;
		}
		if (username.length() == 0)
		{
			appendToVersionPane("Error");
			JOptionPane.showMessageDialog(null, "Please enter a user name", "Please enter a user name", JOptionPane.YES_OPTION);
			isValid = false;
		}
		if (password.length() == 0)
		{
			appendToVersionPane("Error");
			JOptionPane.showConfirmDialog(null, "Please enter a password", 
					"Error", JOptionPane.YES_OPTION);
			isValid = false;
		}

		if(username.length() == 0 && password.length() == 0)
		{
			appendToVersionPane("Error");
			JOptionPane.showConfirmDialog(null, "Please enter a username and password", 
					"Error", JOptionPane.YES_OPTION);
			
			isValid = false;
		}
		try
		{
			portNumber = Integer.parseInt(port);
		}
		catch (NumberFormatException e)
		{
			appendToVersionPane("Error");
			JOptionPane.showConfirmDialog(null, "Please enter a valid port number", 
					"Error", JOptionPane.YES_OPTION);
			isValid = false;
		}

		if (isValid)
		{
			try
			{
				
				moo.Connect(host, portNumber, username, password);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Sorry, I could not connect to LambdaMoo using that user name and password.\n" + " Exception: " + ex.getMessage());
				return;
			}
			//Check to see if the login was valid
			while (loginStatus == LoginStatus.NOT_ATTEMPTED)
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
			if (loginStatus == LoginStatus.SUCCESS)
			{
				logger.info("Login SUCCESSFUL for " + username + " on host " + host + ":" + port);
				gameFrame.setLocationRelativeTo(null);  // sets frame location in middle of screen
				gameFrame.setVisible(true);
				logger.info("Opening GameFrame Window");
				this.setVisible(false);
			}
			else
			{
				logger.info("Login FAILED for " + username + " on host " + host + ":" + port);
				appendToVersionPane("Username or password was invalid, or not found.");
				JOptionPane.showConfirmDialog(null, "Username/password invaild", 
						"Username or password was invalid, or not found.", JOptionPane.ERROR_MESSAGE);
				loginStatus = LoginStatus.NOT_ATTEMPTED;
			}
		}
	}

	public void setLoginStatus(LoginStatus status)
	{
		this.loginStatus = status;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        portTextField = new javax.swing.JTextField();
        hostTextField = new javax.swing.JTextField();
        hostLabel = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        versionTextArea = new javax.swing.JTextArea();
        wwwicLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        forgetButton = new javax.swing.JButton();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome to Blackwood");
        setBackground(Main.COLOR_YELLOW);
        setForeground(Main.COLOR_YELLOW);
        setMinimumSize(new java.awt.Dimension(650, 350));
        setResizable(false);

        jPanel1.setBackground(Main.COLOR_YELLOW);
        jPanel1.setForeground(Main.COLOR_YELLOW);
        jPanel1.setEnabled(false);

        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgLogin.gif"))); // NOI18N
        loginButton.setToolTipText("Click this button to login as a previously registered user.");
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setMaximumSize(new java.awt.Dimension(130, 43));
        loginButton.setMinimumSize(new java.awt.Dimension(130, 43));
        loginButton.setPreferredSize(new java.awt.Dimension(130, 43));
        loginButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgLoginPressed.gif"))); // NOI18N
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgRegister.gif"))); // NOI18N
        registerButton.setToolTipText("Click this button to register as a new player.");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setMaximumSize(new java.awt.Dimension(130, 43));
        registerButton.setMinimumSize(new java.awt.Dimension(130, 43));
        registerButton.setPreferredSize(new java.awt.Dimension(130, 43));
        registerButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgRegisterPressed.gif"))); // NOI18N
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        
        forgetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgforgetpassword.gif"))); // NOI18N
        forgetButton.setToolTipText("Click this button to get previous password.");
        forgetButton.setBorderPainted(false);
        forgetButton.setContentAreaFilled(false);
        forgetButton.setMaximumSize(new java.awt.Dimension(150, 50));
        forgetButton.setMinimumSize(new java.awt.Dimension(150, 50));
        forgetButton.setPreferredSize(new java.awt.Dimension(150, 50));
        forgetButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgforgetpassword.gif"))); // NOI18N
        forgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	forgetButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgCancel.gif"))); // NOI18N
        cancelButton.setToolTipText("Click this button to exit.");
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setMaximumSize(new java.awt.Dimension(130, 43));
        cancelButton.setMinimumSize(new java.awt.Dimension(130, 43));
        cancelButton.setPreferredSize(new java.awt.Dimension(130, 43));
        cancelButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/imgCancelPressed.gif"))); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password:");

        userNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldkeyReleased(evt);
            }
        });

        portTextField.setColumns(20);
        portTextField.setText("7779");
        portTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldkeyReleased(evt);
            }
        });

        hostTextField.setColumns(20);
        hostTextField.setText("lions.cs.ndsu.nodak.edu");
        hostTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldkeyReleased(evt);
            }
        });

        hostLabel.setText("Host:");

        portLabel.setText("Port:");

        userNameLabel.setText("User Name:");

        versionTextArea.setColumns(1);
        versionTextArea.setEditable(false);
        versionTextArea.setRows(1);
        versionTextArea.setText(initializeVersionString());
        versionTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(versionTextArea);

        wwwicLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/wwwic_sm.gif"))); // NOI18N

        passwordTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldkeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hostLabel)
                                .addComponent(portLabel)
                                .addComponent(userNameLabel)
                                .addComponent(passwordLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(passwordTextField)
                                .addComponent(portTextField, 0, 0, Short.MAX_VALUE)
                                .addComponent(hostTextField)
                                .addComponent(userNameTextField))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wwwicLabel))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(forgetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {hostTextField, passwordTextField, portTextField, userNameTextField});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {hostLabel, passwordLabel, portLabel, userNameLabel});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, loginButton, registerButton,forgetButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hostLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel)))
                    .addComponent(wwwicLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(forgetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hostTextField, passwordTextField, portTextField, userNameTextField});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hostLabel, passwordLabel, portLabel, userNameLabel});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

	private void textFieldkeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textFieldkeyReleased
	{//GEN-HEADEREND:event_textFieldkeyReleased
		int code = evt.getKeyCode();
		if (KeyEvent.getKeyText(code).equals("Enter"))
		{
			loginButton.doClick();
		}
	}//GEN-LAST:event_textFieldkeyReleased

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loginButtonActionPerformed
	{//GEN-HEADEREND:event_loginButtonActionPerformed
		logger.debug("Login Button Pressed Event");
		login();
	}//GEN-LAST:event_loginButtonActionPerformed

	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_registerButtonActionPerformed
	{//GEN-HEADEREND:event_registerButtonActionPerformed
		logger.debug("Register Button Pressed Event");
		RegistrationFrame registerFrame = new RegistrationFrame();
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setVisible(true);
		this.setVisible(false);


	}//GEN-LAST:event_registerButtonActionPerformed

	private void forgetButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_registerButtonActionPerformed
	{//GEN-HEADEREND:event_forgetButtonActionPerformed
		logger.debug("ForgetPassword Button Pressed Event");
		ForgetPasswordFrame forgetFrame = new ForgetPasswordFrame();
		forgetFrame.setLocationRelativeTo(null);
		forgetFrame.setVisible(true);


	}//GEN-LAST:event_registerButtonActionPerformed
	public void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
	{//GEN-HEADEREND:event_cancelButtonActionPerformed
		logger.debug("Cancel Button Pressed Event");
		appendToVersionPane("Quit the Game");
		//pop up a dialog window to confirm quit game or not
		int n = JOptionPane.showConfirmDialog(null, "Are you sure to quit this Game", 
				"QUIT GAME", JOptionPane.OK_CANCEL_OPTION);
		if(n == JOptionPane.OK_OPTION){
			System.exit(0);
		}
		else if (n == JOptionPane.CANCEL_OPTION){
			setDefaultCloseOperation(JOptionPane.CLOSED_OPTION);
		}
		
	}//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel userNameLabel;
    public static javax.swing.JTextField userNameTextField;
    private javax.swing.JTextArea versionTextArea;
    private javax.swing.JLabel wwwicLabel;
    private javax.swing.JButton forgetButton;
    // End of variables declaration//GEN-END:variables
}
