/*package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.borland.jbcl.layout.*;

import java.util.Vector;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

*//**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 *//*

  public class NotePad extends JDialog {

  private JPanel panel1 = new JPanel();
  
  private String noteID;
  private Vector notes = new Vector();

  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JTextArea jList1 = new JTextArea();
  private JLabel spacer1 = new JLabel();
  private JLabel spacer2 = new JLabel();
  
  private String IMAGEPIC = new String("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/");
  
 

  public NotePad(Frame frame, String title, boolean modal) {
    super(frame, "Rejoinder", modal);
    if(frame == null){
    	 Dimension parentSize = frame.getSize(); 
         Point p = frame.getLocation(); 
         setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
    }
   
    try {
      jbInit();
      pack();
      //setVisible(true);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public NotePad(String id, String title) {
	  
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
	
	 //String IMAGE_RESOURCE_PATH = "edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/objects";
	  
	  
	 panel1.setLayout(xYLayout1);
	 //panel1.setSize(600, 600);
	
	 JPanel buttonPane = new JPanel();
	 JButton jButton1 = new JButton("Close"); 
	 buttonPane.add(jButton1); 
	
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    
   
  
    panel1.setBackground(new Color(251, 236, 175));
    JPanel picPane = new JPanel();
	  BufferedImage myPicture = ImageIO.read(new File("edu.nodak.ndsu.cs.lions.blackwood.Resources.Image.room/Bbirth.jpg"));
	  JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	  picPane.add(picLabel);
    
    //ImagePanel picPane = new ImagePanel(new ImageIcon("D:\\2.jpg").getImage());
    
    
    //ImagePanel picPane = new ImagePanel(new ImageIcon("C:\\Users\\john\\workspace\\Blackwood2014\\src\\edu\\nodak\\ndsu\\cs\\lions\\blackwood\\Resources\\Image\\objects\\bird.gif").getImage());
    //ImagePanel picPane = new ImagePanel(new ImageIcon(getClass().getResource("bird.gif")).getImage());
    //Image image = this.getToolkit().getImage(this.getClass().getResource("../bird.gif"));
    
    //ImagePanel picPane = new ImagePanel(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/objects/bird.gif")));
    ImagePanel picPane = new ImagePanel(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMAGEPIC + "imgHelpPressed.gif")));
    getContentPane().add(panel1);
 
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    
    getContentPane().add(picPane, BorderLayout.EAST);
   
    
    getContentPane().setBackground(new Color(251, 236, 175));
    
    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 170, 0, 300);
	JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 190, 0, 300);
	  
    
    
    //panel1.add(jButton1, new XYConstraints(264, 166, 120, 29)); 
    panel1.add(jList1, new XYConstraints(6, 6, 320, 120));
    
    panel1.add(spacer1,new XYConstraints(300, 170, 8, 22));
    panel1.add(spacer2, new XYConstraints(300, 199, 38, 6));
    
    //panel1.add(hbar, BorderLayout.NORTH);
	//panel1.add(vbar, BorderLayout.WEST);
    
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public void addLine(String id, String lineText){
    notes.addElement(lineText);
    
  }

  public void endNotes(String id){
	  
	  hbar.setUnitIncrement(2);
	  hbar.setBlockIncrement(1);
	  
	  hbar.addAdjustmentListener(new MyAdjustmentListener());
	  vbar.addAdjustmentListener(new MyAdjustmentListener());
	  
	  jList1.setBackground(new Color(251, 236, 175));
	  jList1.setBorder(BorderFactory.createLineBorder(Color.black));
	 
	  jList1.setLineWrap(true);
	  jList1.setWrapStyleWord(true);
	  jList1.setText(notes.toString());
	
      this.setVisible(true);
  }
  
  class MyAdjustmentListener implements AdjustmentListener {
	    public void adjustmentValueChanged(AdjustmentEvent e) {
	      //label.setText("    New Value is " + e.getValue() + "      ");
	      repaint();
	    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
	setVisible(false); 
    this.dispose();
  }
}*/