package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.text.DecimalFormat;


/**
 * <p>Title: Order Form </p>
 * <p>Description: This form lists vendors and items available for order  </p>
 * @author Carson Berger
 * @version 1.0
 */

public class OrderForm extends JFrame {
//public class OrderForm extends JDialog {

  private double orderTotal = 0;
  
  private Vector orderdata = new Vector();
  
  private MooConnection Moo;
  
  private String CatId;

  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel1 = new JLabel();
  
  private JLabel jLabel_Total = new JLabel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private String[] col = {"Item Description","Quantity","Price","Total"};
  private DefaultTableModel defaultTableModel = new DefaultTableModel(col, 0);
  private JTable jTable1 = new JTable(defaultTableModel);
  private JLabel jLabel2 = new JLabel();
  private JLabel spacer3 = new JLabel();
  private OrderDialog Parent;

 //public OrderForm(Frame frame, String title, boolean modal) {
  //  super(frame, title, modal);
   // try {
   //   jbInit();
   //   pack();
   // }
   // catch(Exception ex) {
   //   ex.printStackTrace();
  //  }
  //}

  public OrderForm(MooConnection moo,String id,OrderDialog parent) {
   
    Moo = moo;
    CatId = id;
    
    
    Parent = parent;
    this.setTitle(" Order Form ");
    this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    setSize(520,500);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
   }


  private void jbInit() throws Exception {


    panel1.setLayout(xYLayout1);
    this.setLocation(500,100);
    panel1.setBackground(new Color(251, 236, 175));
    jButton1.setText("Place Order");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Cancel");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel1.setText("Order Total: $");
    jLabel_Total.setText("Price");
    jTable1.selectAll();
    jLabel2.setToolTipText("");
    getContentPane().add(panel1);
    panel1.add(jLabel1,       new XYConstraints(308, 302, 92, 22));
    panel1.add(jScrollPane1,   new XYConstraints(5, 7, 482, 296));
    panel1.add(jButton1, new XYConstraints(9, 326, 152, 30));
    panel1.add(jButton2, new XYConstraints(234, 327, 86, 29));
    panel1.add(jLabel2, new XYConstraints(5, 360, 109, 11));
    panel1.add(jLabel_Total,    new XYConstraints(403, 303, 84, 23));
    panel1.add(spacer3,    new XYConstraints(486, 241, 8, 29));
    jScrollPane1.getViewport().add(jTable1, null);
    jTable1.getColumnModel().getColumn(0).setMaxWidth(250);
    jTable1.getColumnModel().getColumn(0).setMinWidth(250);
  }

  public void addItem(String Description, String Name, int Qty, double price){
    
    Vector newRow = new Vector();
    newRow.addElement(Name);
    newRow.addElement(Qty+"");
    newRow.addElement(price+"");
    newRow.addElement(Qty*price+"");
   
    orderdata.addElement(newRow);
    
    
    
    //defaultTableModel.getRowCount();
    
    
    System.out.println("ROWS: "+defaultTableModel.getRowCount());
    defaultTableModel.addRow(newRow);

    orderTotal += price*Qty;
    DecimalFormat myformat2 = new DecimalFormat("0.00");
    jLabel_Total.setText(myformat2.format(orderTotal));
    
    this.setVisible(true);
    this.repaint();
  }

void jButton1_actionPerformed(ActionEvent e) {
// place the order
	
  String item = "";
  String qty = "";
  for ( int j = 0 ; j < orderdata.size(); j++ ) {
    item = ((Vector)orderdata.elementAt(j)).elementAt(0).toString();
    qty = ((Vector)orderdata.elementAt(j)).elementAt(1).toString();
    Moo.send("order "+qty+" \""+item+"\" once from "+CatId);
  }
  this.dispose();
}

void jButton2_actionPerformed(ActionEvent e) {
// cancel order
	
  orderdata.removeAllElements();
  Parent.cancelOrder();
  this.dispose();
}

}

