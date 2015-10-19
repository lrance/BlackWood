package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.borland.jbcl.layout.*;

import java.util.Vector;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

  public class PicturePad extends JDialog {

  private JPanel panel1 = new JPanel();
  private static final long serialVersionUID = -2216276219179107707L; 
  private Container con;
  private ZPanel zPanel;
  private JScrollPane imgSp;
  private JScrollPane imgSp_Note;
  
  private String noteID;
  private Vector notes = new Vector();

  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JTextArea jList1 = new JTextArea();
  private JLabel spacer1 = new JLabel();
  private JLabel spacer2 = new JLabel();
  
  
  private static String imagePath = "/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/objects/";
  

  public PicturePad(Frame frame, String title, boolean modal) {

	super(frame, "Rejoinder: " + title, modal);
	
	
 
    try{
      
    	jbInit();
    	pack();
    
        }catch(Exception ex) {
        ex.printStackTrace();
    }
  }

  public PicturePad(String id, String title) {
	  
    this(null, title, false);
    noteID = id;
  }


  class ImagePanel extends JPanel {
	  
	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}

  
  private void jbInit() throws Exception {
	
	 panel1.setLayout(xYLayout1);
	 
	 JPanel buttonPane = new JPanel();
	 JButton jButton1 = new JButton("Close"); 
	 buttonPane.add(jButton1); 
	 buttonPane.setBackground(new Color(251, 236, 175));
	
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    
  
  
    panel1.setBackground(new Color(251, 236, 175));
  

    getContentPane().add(panel1);
 
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
   

    getContentPane().setBackground(new Color(251, 236, 175));
    
    
	
    panel1.add(jList1, new XYConstraints(6, 6, 320, 320));
    
    //panel1.add(imgSp_Note, new XYConstraints(6, 6, 320, 320));
    //panel1.add(spacer1,new XYConstraints(340, 340, 8, 22));
    //panel1.add(spacer2, new XYConstraints(340, 349, 38, 6));
   
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public void addLine(String id, String lineText){
    notes.addElement(lineText);
    
  }

  public void addPictureName(String picNode){
	
	   con = getContentPane();
	   zPanel = new ZPanel();
	   String trimmedFilename  = picNode.trim();
	   String addFilename = imagePath + trimmedFilename;
	   StringBuffer fullFileName = new StringBuffer(addFilename);
	   fullFileName.append(".gif");
	   String fileName = fullFileName.toString();
	   zPanel.setImagePath(fileName);
	   
	   zPanel.setPreferredSize(new Dimension(zPanel.getImgWidth(), zPanel  
               .getImgHeight()));  
 
       imgSp = new JScrollPane();  
       imgSp.setViewportView(zPanel);  
       imgSp.setBackground(new Color(251, 236, 175));
      
       con.add(imgSp, BorderLayout.NORTH);
       
	 
  }
 
  public void endNotes(String id){
	
	
	  jList1.setBackground(new Color(251, 236, 175));
	  jList1.setBorder(BorderFactory.createLineBorder(Color.black));
	 
	  jList1.setLineWrap(true);
	  jList1.setWrapStyleWord(true);
	  jList1.setText(notes.toString());
	 
	  //imgSp_Note = new JScrollPane(jList1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	  
      this.setVisible(true);
  }
  

  void jButton1_actionPerformed(ActionEvent e) {
	setVisible(false); 
    this.dispose();
  }
}