package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;

import javax.swing.*;

// import GUI.PrimaryGameFrame.TalkFieldActionListener;

import com.borland.jbcl.layout.*;
import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;
import java.util.Vector;
import java.awt.event.*;


/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author unascribed
 * @version 1.0
 */

public class ChatDialog extends JDialog {
  
    /**
	 * default serialVersion number added
	 */
	private static final long serialVersionUID = 1L;
	private static final Color COLOR_YELLOW = null;
	/** The text pane in the middle showing moo log information. */
  private JTextPane informationTextPane;
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
    
  private MooConnection Moo;
  private LoginFrame Dad;
  
  /** The entity holding the avatars and locations of the player.  */
  private Stage mainStage;

  
  
  public ChatDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit1();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ChatDialog(MooConnection moo, LoginFrame dad) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    Dad = dad;
    Moo.send("store_inventory");
  }

  public ChatDialog(MooConnection moo) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    //Dad = dad;
    Moo.send("store_inventory");
    this.show();
  }

  

 

//@SuppressWarnings("deprecation")
private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    panel1.setBackground(COLOR_YELLOW );
        
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    informationTextPane = new JTextPane();
    informationTextPane.setEnabled(true);
    informationTextPane.setEditable(false);
    informationTextPane.setFont(new java.awt.Font("SansSerif", 1, 10));
    informationTextPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
    informationTextPane.setText("");
    panel1.add(informationTextPane, new XYConstraints(9, 10, 300, 250));
    
  
    JTextField jTextFieldTalk = new JTextField();
    jTextFieldTalk.addActionListener(new TalkFieldActionListener());
    panel1.add(jTextFieldTalk, new XYConstraints(9, 270, 300, 22));

    this.show(); 
  }
private void jbInit1()  {
    panel1.setLayout(xYLayout1);
    panel1.setBackground(COLOR_YELLOW );
        
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    informationTextPane = new JTextPane();
    informationTextPane.setEnabled(true);
    informationTextPane.setEditable(false);
    informationTextPane.setFont(new java.awt.Font("SansSerif", 1, 10));
    informationTextPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
    
    JTextField jTextFieldTalk = new JTextField();
    panel1.add(informationTextPane, new XYConstraints(9, 10, 300, 250));
    jTextFieldTalk.addActionListener(new TalkFieldActionListener());
    
    panel1.add(jTextFieldTalk, new XYConstraints(9, 270, 300, 22));
}   
    
   class TalkFieldActionListener implements ActionListener
	{

		/**
		 * Sends the 'say' command to the moo and resets the text in the field
		 * @param event: the event sent
		 */
		public void actionPerformed(ActionEvent event)
		{
			JTextField textField = (JTextField) event.getSource();
			String typedtext = textField.getText();
			Moo.send("Say " + typedtext);
			appendToInformationTextPane(typedtext);
			textField.setText("");
			
		}
		
		
		
	}
   
         /**
	 * Appends text to the text pane in the middle of the interface.
	 * @param line the line to be appended.
	 */
	public void appendToInformationTextPane(String line)
	{
		String displayedtext = informationTextPane.getText();
		informationTextPane.setText(displayedtext + "\n" + line);
	}
        
       
        /**
	 * Accessor method for the Stage used to display current users.
	 * @return the Stage object.
	 */
	public Stage getStage()
	{
		return mainStage;
	}
 
        
        /**
	 * Simulates your character speaking in the moo
	 * @param who the object number of the character speaking
	 * @param what the text entered
	 */
	public void talk(String who, String what)
	{
		mainStage.talk(who, what);
	}
    
}