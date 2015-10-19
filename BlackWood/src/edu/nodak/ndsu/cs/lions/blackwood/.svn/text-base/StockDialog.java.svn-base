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
 * @author Ross Melby
 * @version 1.0
 */

public class StockDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JList jList1 = new JList();
  private JTextPane jTextPane2 = new JTextPane();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JToggleButton jToggleButton1 = new JToggleButton();
  private JToggleButton jToggleButton2 = new JToggleButton();
  private JToggleButton jToggleButton3 = new JToggleButton();
  private JToggleButton jToggleButton4 = new JToggleButton();
  private picPanel jPanel1 = new picPanel();
  private JToggleButton jToggleButton5 = new JToggleButton();
  private JToggleButton jToggleButton6 = new JToggleButton();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabelSalePrice = new JLabel();
  private JLabel jLabelOrderSize = new JLabel();
  private JLabel jLabel2 = new JLabel();

  private MooConnection Moo;
  private LoginFrame Dad;

  private Vector invList = new Vector();
  private ImageIcon currPicture;

  public StockDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public StockDialog(MooConnection moo, LoginFrame dad) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    Dad = dad;
    Moo.send("store_inventory");
  }

  public StockDialog(MooConnection moo) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
    //Dad = dad;
    Moo.send("store_inventory");
  }

  public void InventoryStart(String userID){
    // A new Inventory list is about to be sent one item at a time
    // So initialize the list, and delete old list if there is one.
    invList.removeAllElements();

    // for testing only
    invList.add(new invItem("1234",  "Stubborn Mule",  "defaultimage","Mule bread specifically for its stubborn nature."));
    invList.add(new invItem("1235",  "Work Horse",  "wrkhorse","Very strong and hard working horse."));
    invList.add(new invItem("1235",  "Riding Horse",  "ridehors","Very fast and well tempered."));
    invList.add(new invItem("1235", " Carson Berger", "Carson", " A very slow work horse that doesn't do anything"));
    
  }

  public void InventoryAddItem(String itemID, String itemName, String itemPicture, String itemDescription){
    // add items to the inventory list
    invList.addElement(new invItem(itemID,  itemName,  itemPicture, itemDescription));
  }

  public void InventoryEnd(){
    // Now that all item of inventory have been recieved
    // this function should take of displaying them.
    jList1.setListData(invList);
  }

  private class invItem {

    private String ItemID;
    private String ItemName;
    private String ItemPicture;
    private String ItemDescription;

    public invItem(String itemID, String itemName, String itemPicture, String itemDescription){
      ItemID = itemID;
      ItemName = itemName;
      ItemPicture = itemPicture;
      ItemDescription = itemDescription;
    }

    public String getImgFile(){
      return ItemPicture;
    }

    public String getDescription(){
      return ItemDescription;
    }

    public String toString(){
      return ItemName;
    }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    panel1.setBackground(new Color(251, 236, 175));
    jToggleButton1.setText("Modify Price");
    jToggleButton2.setText("Update Order ");
    jToggleButton3.setText("Return Stock");
    jToggleButton4.setText("Cancel Order");
    jToggleButton5.setText("Done");
    jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jToggleButton5_actionPerformed(e);
      }
    });
    jToggleButton6.setText("Revert Item");
    jLabel1.setToolTipText("");
    jLabelSalePrice.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelSalePrice.setText("Sale Price");
    jLabelOrderSize.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelOrderSize.setText("Order Size");
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jList1_mouseClicked(e);
      }
    });
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jList1,      new XYConstraints(9, 10, 206, 250));
    panel1.add(jTextPane2,        new XYConstraints(9, 262, 467, 109));
    panel1.add(jTextField1, new XYConstraints(97, 377, 57, 20));
    panel1.add(jTextField2, new XYConstraints(97, 402, 57, 20));
    panel1.add(jPanel1,  new XYConstraints(217, 10, 259, 250));
    panel1.add(jLabel1,  new XYConstraints(476, 9, 10, 226));
    panel1.add(jLabelSalePrice,   new XYConstraints(9, 377, 81, 22));
    panel1.add(jLabelOrderSize,  new XYConstraints(10, 401, 81, 21));
    panel1.add(jToggleButton1,   new XYConstraints(164, 378, 110, 21));
    panel1.add(jToggleButton2, new XYConstraints(164, 402, 110, 21));
    panel1.add(jToggleButton3,    new XYConstraints(357, 382, 107, 24));
    panel1.add(jLabel2, new XYConstraints(2, 454, 58, 12));
    panel1.add(jToggleButton5, new XYConstraints(126, 433, 69, 24));
    panel1.add(jToggleButton6,  new XYConstraints(222, 433, 100, 24));
    panel1.add(jToggleButton4, new XYConstraints(352, 432, 107, 24));
    this.show();
  }

  void jList1_mouseClicked(MouseEvent e) {
    int indx = jList1.getLeadSelectionIndex();
    jTextPane2.setText(((invItem)invList.elementAt(indx)).getDescription());
    try {
     currPicture = new ImageIcon("JGraphics/objects/"+((invItem)invList.elementAt(indx)).getImgFile());
     repaint();
    } catch ( Exception ex) {ex.printStackTrace();}
  }

  private class picPanel extends JPanel{

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if ( currPicture != null )
        currPicture.paintIcon(this,g,10,10);
    }
  }

  void jToggleButton5_actionPerformed(ActionEvent e) {
    setVisible(false);
  }
}