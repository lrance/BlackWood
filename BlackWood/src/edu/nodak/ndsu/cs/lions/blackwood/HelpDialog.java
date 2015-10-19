package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class HelpDialog extends JDialog {
  private JPanel panel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();

  public HelpDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public HelpDialog() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    getContentPane().add(panel1);
  }
}