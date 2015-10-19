package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;
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

public class HireFireDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JList jList1 = new JList();
  private JTextPane jTextPane2 = new JTextPane();
  private picPanel jPanel1 = new picPanel();
  private JToggleButton jToggleButton5 = new JToggleButton();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();

  private MooConnection Moo;
  private LoginFrame Dad;

  private Vector appList = new Vector();
  private ImageIcon currPicture;
  private JButton HireFire = new JButton();
  private JButton jButtonAdvertise = new JButton();
  private JTextField jTextFieldWage = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  
  String packageID;				// makes sure packets are all from the same message
  boolean inProgress = false;	// makes sure a message doesn't get canceled by a lost start packet

  public HireFireDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public HireFireDialog(MooConnection moo, LoginFrame dad) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    Dad = dad;
  }
  public HireFireDialog(MooConnection moo) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    //Dad = dad;
  }

  public void ApplicantStart(String messageSeqNum){
    // A new Applicant list is about to be sent one item at a time
    // So initialize the list, and delete old list if there is one.
	  if (!inProgress)		// if another applicantStart has not finished
	  {
		  appList.removeAllElements();
		  jList1.repaint();
		  inProgress = true;
	  }
    /*
    for testing only
    appList.add(new applicant("1234",  "Stubborn Mule",  "mule.gif","Mule bread specifically for its stubborn nature."));
    appList.add(new applicant("1235",  "Work Horse",  "wrkhorse.gif","Very strong and hard working horse."));
    appList.add(new applicant("1235",  "Riding Horse",  "ridehors.gif","Very fast and well tempered."));
     */
  }

  public void AddApplicant(String messageSeqNum, String applicantID, String applicantName, String applicantDescription, String hired, String picture){
    /* add items to the Applicant list
     * messageSeqNum uniquely identifies the message, applicantID is the applicant's object number, applicantName/Description are displayed to user
     * hired marks applicants who work for the current store, picture is used to build the path to the image file of applicant
	*/
	  
	  System.out.println("rjf says this never happens.");
	 if (appList.isEmpty() && inProgress)
		 packageID = messageSeqNum;			// packageID is set here so false applicantStarts don't lock out new applicant lists
	 if (packageID.equals(messageSeqNum) && inProgress)
		 appList.addElement(new applicant(messageSeqNum, applicantID, applicantName, applicantDescription, hired, picture));
  }

  public void ApplicantEnd(String messageSeqNum){
    // Now that all Applicants have been recieved
    // this function should takes care of displaying them.
	  
	  System.out.println("rjf PackageID: " + packageID);
	if (packageID.equals(messageSeqNum) && inProgress)
	{
		inProgress = false;
		jList1.setListData(appList);
	}
  }

  private class applicant {

    private String ApplicantID;				// object number
    private String ApplicantName;			// name
    private String MessageSeqNum;			// message identifier
    private String ApplicantDescription;	// description
    private String Hired;					// determines if employed by current store
    private String Picture;					// used to build path to app's image file

    public applicant(String messageSeqNum, String applicantID, String applicantName, String applicantDescription, String hired, String picture){
      ApplicantID = applicantID;
      ApplicantName = applicantName;
      MessageSeqNum = messageSeqNum;
      ApplicantDescription = applicantDescription;
      Hired = hired;
      Picture = picture;
      if (Hired.equals("1"))
    	  ApplicantName = applicantName.concat(" (enslaved)");
      System.out.println("Created " + applicantName);
    }

/*********************************** 
      public String getImgFile(){
      return ApplicantPicture;   // appPic is the former name of messageSeqNum
    }                            // it is useless for this function
 ***********************************/

    public String getDescription(){
      return ApplicantDescription;
    }

    public String toString(){
      return ApplicantName;
    }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    panel1.setBackground(new Color(251, 236, 175));
    jToggleButton5.setText("Done");
    jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jToggleButton5_actionPerformed(e);
      }
    });
    jLabel1.setToolTipText("");
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jList1_mouseClicked(e);
      }
    });
    HireFire.setText("Hire/Fire");
    HireFire.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        HireFire_actionPerformed(e);
      }
    });
    jButtonAdvertise.setText("Advertise");
    jButtonAdvertise.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonAdvertise_actionPerformed(e);
      }
    });
    jTextFieldWage.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTextFieldWage_actionPerformed(e);
      }
    });
    jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel3.setText("Amount");
    jLabel5.setText("jLabel5");
    jTextFieldWage.setText("100");
    panel1.add(jList1,      new XYConstraints(9, 10, 206, 250));
    panel1.add(jTextPane2,        new XYConstraints(9, 262, 467, 109));
    panel1.add(jLabel1, new XYConstraints(476, 9, 10, 226));
    panel1.add(jLabel2, new XYConstraints(2, 454, 58, 12));
    panel1.add(jToggleButton5,  new XYConstraints(385, 397, 75, 26));
    panel1.add(HireFire, new XYConstraints(9, 429, 134, 26));
    panel1.add(jPanel1,  new XYConstraints(217, 10, 259, 250));
    this.getContentPane().add(panel1, BorderLayout.SOUTH);
    panel1.add(jButtonAdvertise,  new XYConstraints(183, 401, 116, -1));
    panel1.add(jTextFieldWage, new XYConstraints(69, 403, 102, 24));
    panel1.add(jLabel3,  new XYConstraints(19, 400, 46, 29));
    panel1.add(jLabel4,  new XYConstraints(155, 460, 44, 6));
    panel1.add(jLabel5,  new XYConstraints(507, 92, 5, 47));
    this.setVisible(true);
  }

  void jList1_mouseClicked(MouseEvent e) {
    int indx = jList1.getLeadSelectionIndex();
    jTextPane2.setText(((applicant)appList.elementAt(indx)).getDescription());
    try {
     currPicture = new ImageIcon("../Blackwood Client/src/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/people/" + ((applicant) jList1.getSelectedValue()).Picture + ".gif");
     repaint();
    } catch ( Exception ex) {ex.printStackTrace();}
  }

  private class picPanel extends JPanel{

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if ( currPicture != null )
        currPicture.paintIcon(this,g,100,50);
    }
  }

  void jToggleButton5_actionPerformed(ActionEvent e) {
    setVisible(false);
  }

  void HireFire_actionPerformed(ActionEvent e) {
	  applicant selectedEmp;	// employee selected by user from jList
	  
	  if(!appList.isEmpty())
	  {
		  //Do what you wish the selected applicant
		  selectedEmp = (applicant) jList1.getSelectedValue();
		  if (selectedEmp.Hired.equals("0"))
			  Moo.send("hire " + selectedEmp.ApplicantID + " " + jTextFieldWage.getText());
		  else
			  Moo.send("fire");
		  Moo.send("get_applicants " + jTextFieldWage.getText());   // refreshes list after changes
	  }
		  
  }

  void jTextFieldWage_actionPerformed(ActionEvent e) {

  }

  void jButtonAdvertise_actionPerformed(ActionEvent e) {
    Moo.send("get_applicants "+jTextFieldWage.getText());
  }
}
