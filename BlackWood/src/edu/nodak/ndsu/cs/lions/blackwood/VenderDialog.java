package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import com.borland.jbcl.layout.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class VenderDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private JLabel jLabel_Description = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private JButton jButton2 = new JButton();
  private JButton jButton1 = new JButton();
  private XYLayout xYLayout1 = new XYLayout();

  private Vector venderList = new Vector();
  private Vector venderIdList = new Vector();
  private MooConnection Moo;
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jList1 = new JList();

  public String VenderID;

  public VenderDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public VenderDialog( MooConnection moo) {
    this(null, "", false);
    Moo = moo;
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jLabel_Description.setToolTipText("");
    jLabel_Description.setText("Select Vender from list:");
    jButton2.setHorizontalAlignment(SwingConstants.RIGHT);
    jButton2.setText("Close");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton1.setEnabled(false);
    jButton1.setText("View Selected Vender\'s Catalog");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    panel1.setBackground(new Color(251, 236, 175));
    jPanel1.setBackground(new Color(251, 236, 175));
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jList1_valueChanged(e);
      }
    });
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jList1_mouseClicked(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jPanel1,  new XYConstraints(0, 263, 400, -1));
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    panel1.add(jScrollPane1,    new XYConstraints(7, 22, 384, 238));
    panel1.add(jLabel_Description,  new XYConstraints(2, 4, 332, -1));
    jScrollPane1.getViewport().add(jList1, null);
  }

  public void addVender(String id, String vender){
    venderIdList.addElement(id);
    venderList.addElement(vender);
  }

  public void endList(){
    if ( venderList.size() == 0 ) venderList.addElement("No Venders Available");
    jList1.setListData(venderList);
  }

  public void clear(){
    venderList.clear();
    venderIdList.clear();
  }

  private void ViewCatalog(){
    int indx = jList1.getLeadSelectionIndex();
    VenderID = venderIdList.elementAt(indx).toString();
    Moo.send("pricing "+VenderID);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.hide(); // do not dispose because VenderID is referenced when ordering from OrderForm
  }

  void jList1_mouseClicked(MouseEvent e) {
    if ( e.getClickCount() == 2 && jList1.getLeadSelectionIndex() > -1 && venderList.elementAt(0).toString() != "No Venders Available" ){
      ViewCatalog();
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    ViewCatalog();
  }

  void jList1_valueChanged(ListSelectionEvent e) {
    if ( venderList.elementAt(0).toString() != "No Venders Available" ) jButton1.setEnabled(true);
  }
}