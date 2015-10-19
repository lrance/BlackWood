package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;
import java.util.Vector;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.event.*;
import java.text.DecimalFormat;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author unascribed
 * @version 1.0
 */

public class OrderDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JList jList1 = new JList();
  private JTextPane jTextPane2 = new JTextPane();
  private JTextField jTextField_Qty = new JTextField();
  private picPanel jPanel1 = new picPanel();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabelSalePrice = new JLabel();
  private JLabel jLabelOrderSize = new JLabel();
  private JLabel jLabel2 = new JLabel();

  private MooConnection Moo;
  private LoginFrame Dad;
  private String CatId;

  private Vector itemList = new Vector();
  private ImageIcon currPicture;
  private JLabel jLabel_ItemPrice = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();

  public catItem currItem;

  private OrderForm MyOrderForm;

  public OrderDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
      this.repaint();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public OrderDialog(MooConnection moo, LoginFrame dad, String id, String title) {
    this(null, title, false);
    System.out.println("\nCreating OrderDialog for " + id + "-" + title);
    Moo = moo; // use Moo.send(String); to talk to moo
    Dad = dad;
    CatId = id;
    MyOrderForm = null;
  }

  public OrderDialog(MooConnection moo, String id, String title) {
    this(null, title, false);
    System.out.println("\nCreating OrderDialog for " + id + "-" + title);
    Moo = moo; // use Moo.send(String); to talk to moo
    //Dad = dad;
    CatId = id;
    MyOrderForm = null;
  }

  public void addItem(String itemID, String itemName, String itemDescription, String itemPicture, String q1, String p1, String q2, String p2){
    // add items to the inventory list
    itemList.addElement(new catItem(itemID,  itemName,  itemPicture, itemDescription, Integer.parseInt(q1), Double.parseDouble(p1),Integer.parseInt(q2),Double.parseDouble(p2)));
  }

  public void endList(String id){
    // Now that all item of inventory have been recieved
    // this function should take care of displaying them.
    jList1.setListData(itemList);
    jList1.setSelectedIndex(0);
    itemSelected();
    this.setVisible(true);
  }

  public void cancelOrder(){
    MyOrderForm = null;
  }

  private class catItem {

    public String ItemID;
    private String ItemName;
    private String ItemPicture;
    private String ItemDescription;
    private int Q1,Q2;
    private double P1,P2;

    public catItem(String itemID, String itemName, String itemPicture, String itemDescription, int q1, double p1, int q2, double p2){
      System.out.println("Adding catItem " + itemID + ", " + itemName + ", " + itemDescription + ", " + itemPicture + ", " + q1 + ", " + p1 + ", " + q2 + ", " + p2);
      ItemID = itemID;
      ItemName = itemName;
      ItemPicture = itemPicture;
      ItemDescription = itemDescription;
      Q1 = q1; Q2 = q2;
      P1 = p1; P2 = p2;
    }

    public double getPrice(){
      String sQty = jTextField_Qty.getText();
      double price = 0;
      int qty = Integer.parseInt(sQty);
      if ( qty > Q2 ){
        price = P2;
      } else {
        price = P1;
      }
      return price;
    }

    public String getImgFile(){
      return ItemPicture+".gif";
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
    jLabel1.setToolTipText("");
    jLabelSalePrice.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelSalePrice.setText("Price $");
    jLabelOrderSize.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelOrderSize.setText("Quantity");
    jLabel_ItemPrice.setToolTipText("");
    jLabel_ItemPrice.setText("Price");
    jButton1.setEnabled(false);
    jButton1.setText("Add Item To Order");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Close");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jList1_valueChanged(e);
      }
    });
    jTextField_Qty.setText("1");
    panel1.add(jList1,      new XYConstraints(9, 10, 206, 250));
    panel1.add(jTextPane2,        new XYConstraints(9, 262, 467, 109));
    panel1.add(jLabel1, new XYConstraints(476, 9, 10, 226));
    panel1.add(jButton1, new XYConstraints(242, 378, 135, 23));
    panel1.add(jButton2, new XYConstraints(387, 378, 88, 24));
    panel1.add(jLabelOrderSize, new XYConstraints(8, 398, 52, 21));
    panel1.add(jTextField_Qty, new XYConstraints(66, 399, 44, 20));
    panel1.add(jLabel2,    new XYConstraints(1, 419, 58, 11));
    panel1.add(jLabelSalePrice, new XYConstraints(16, 375, 41, 22));
    panel1.add(jLabel_ItemPrice, new XYConstraints(59, 372, 60, 26));
    panel1.add(jPanel1,  new XYConstraints(217, 10, 259, 250));
    this.getContentPane().add(panel1, BorderLayout.SOUTH);
    this.show();
  }

  private void itemSelected(){
    int indx = jList1.getLeadSelectionIndex();
    if ( ! ( indx >= 0 ) ) indx = 0;
    currItem = (catItem)itemList.elementAt(indx);
    if ( currItem == null ) {
      jButton1.setEnabled(false);
      jPanel1.picName = "No item selected.";
      return;
    }
    jTextPane2.setText(currItem.getDescription());
    DecimalFormat myformat2 = new DecimalFormat("0.00");
    jLabel_ItemPrice.setText(myformat2.format(currItem.getPrice())+" each");
    try {
     jPanel1.currPicture = new ImageIcon(getScaledImage((createImageIcon("Resources/Image/objects/"+currItem.getImgFile(), currItem.getImgFile())).getImage(),240,220));
     jPanel1.repaint();
     jPanel1.picName = currItem.getImgFile();
    } catch ( Exception ex) {ex.printStackTrace();}
    jButton1.setEnabled(true);
  }

  protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

  private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

  private class picPanel extends JPanel{

    public ImageIcon currPicture;
    public String picName;

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      System.out.print("\nshowing: ");
      System.out.println(picName);
      if ( picName != null ) g.drawString(picName,20,20); // x,y
      if ( currPicture != null ) currPicture.paintIcon(this,g,10,10);
    }
  }

  void jToggleButton5_actionPerformed(ActionEvent e) {
    setVisible(false);
  }

  void jList1_valueChanged(ListSelectionEvent e) {
    itemSelected();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    // add order item
    if ( MyOrderForm == null )
        MyOrderForm = new OrderForm(Moo,CatId,this);
    int indx = jList1.getLeadSelectionIndex();
    catItem item = ((catItem)itemList.elementAt(indx));
    String description = item.getDescription();
    String name = item.ItemName;
    int qty = Integer.parseInt(jTextField_Qty.getText());
    double price = Double.parseDouble(item.getPrice()+"");
    MyOrderForm.addItem(description,name,qty,price);
  }
}