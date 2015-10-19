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

public class DialogBox extends JDialog {
  private JPanel panel1 = new JPanel();
  private JList jList1 = new JList();
  private JLabel jLabel_Description = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private JButton jButton2 = new JButton();
  private JButton jButton1 = new JButton();
  private XYLayout xYLayout1 = new XYLayout();

  private Vector<String> phraseList = new Vector<String>();
  private MooConnection Moo;

  public DialogBox(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogBox(String descript, MooConnection moo) {
    this(null, "", false);
    Moo = moo;
    jLabel_Description.setText(descript+", who can understand the following:");
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jLabel_Description.setToolTipText("");
    jLabel_Description.setText("jLabel_Description");
    jButton2.setHorizontalAlignment(SwingConstants.RIGHT);
    jButton2.setText("Close Window");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton1.setEnabled(false);
    jButton1.setText("Say Selected Phrase");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    panel1.setBackground(new Color(251, 236, 175));
    jPanel1.setBackground(new Color(251, 236, 175));
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jList1_mouseClicked(e);
      }
    });
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jList1_valueChanged(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jLabel_Description,  new XYConstraints(0, 0, 400, -1));
    panel1.add(jPanel1,  new XYConstraints(0, 263, 400, -1));
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    panel1.add(jList1,  new XYConstraints(0, 17, 400, 246));
  }

  public void addPhrase(String phrase){
    phraseList.addElement(phrase);
  }

  public void endDialog(){
    if ( phraseList.size() == 0 ) phraseList.addElement("Nothing");
    jList1.setListData(phraseList);
  }

  private void SayPhrase(){
    int indx = jList1.getLeadSelectionIndex();
    String phrase = phraseList.elementAt(indx).toString();
    Moo.send("say "+phrase);
  }

  public void clear(String descript){
    phraseList.clear();
    jLabel_Description.setText(descript+", who can understand the following:");
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jList1_mouseClicked(MouseEvent e) {
    if ( e.getClickCount() == 2 && jList1.getLeadSelectionIndex() > -1 && phraseList.elementAt(0).toString() != "Nothing" ){
      SayPhrase();
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    SayPhrase();
  }

  void jList1_valueChanged(ListSelectionEvent e) {
    if ( phraseList.elementAt(0).toString() != "Nothing" ) jButton1.setEnabled(true);
  }
}