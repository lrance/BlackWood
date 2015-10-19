package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author unascribed
 * @version 1.0
 */

public class AdDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private MooConnection Moo;

  public AdDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AdDialog(MooConnection moo) {
    this(null, "", false);
    Moo = moo; // use Moo.send(String); to talk to moo
  }
  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Advertising");
    getContentPane().add(panel1);
    panel1.add(jLabel1,  new XYConstraints(28, 20, 284, 32));
    this.show();
  }
}