package edu.nodak.ndsu.cs.lions.blackwood;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.VerticalFlowLayout;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 */

public class DebugPane extends JFrame implements MooListener {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  private JTextField jTextCommand = new JTextField();
  private JTextArea jTextPane1 = new JTextArea();
  private MooConnection Moo;
  private JScrollPane jScrollPane1 = new JScrollPane();

  private Vector<String> Commands = new Vector<String>();
  private int commandIndex = 0;
  

 /* public DebugPane(Frame frame, String title, boolean modal)  {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }*/

  public DebugPane() {
    //this(null, "", false);
    setSize(800,500);
  }

  public DebugPane(MooConnection moo, String title) {
    //this(null, title, false);
    Moo = moo;
    this.setTitle("Score");
    this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    initialize();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.gif"));
    pack();
    setSize(800,500);
  }
  private void initialize()
	{
		JPanel primaryPanelForFrame = initializePrimaryPanel();
                try 
                    {
                    jbInit();
                    pack();
                    }
                catch(Exception ex) 
                {
                ex.printStackTrace();
                }
    }
  private JPanel initializePrimaryPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new XYLayout());
		panel.setBackground(Main.COLOR_YELLOW);
		panel.setForeground(Main.COLOR_RED);
		return panel;
	}

  public void newLine(String line){
	  System.out.println(line + "\n");
    jTextPane1.append(line+"\n");
    jTextPane1.setCaretPosition(jTextPane1.getText().length());
    //scrollBottom();
  }

  public void scrollBottom(){
    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
  }

  private void jbInit() throws Exception
  {
      panel1.setLayout(borderLayout1);
      jTextCommand.addKeyListener(new java.awt.event.KeyListener() {
      public void keyReleased(KeyEvent e)
      {
      jTextCommand_keyReleased(e);
      }
      public void keyPressed(KeyEvent e)
      {
      jTextCommand_keyPressed(e);
      }
      public void keyTyped(KeyEvent e) {
       //
      }
      });
    jTextCommand.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTextCommand_actionPerformed(e);
        }
        });
    jTextPane1.setText("jTextPane1");
    getContentPane().add(panel1);
    panel1.add(jTextCommand, BorderLayout.SOUTH);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextPane1, null);
  }

  void jTextCommand_actionPerformed(ActionEvent e) {
    String command = e.getActionCommand().toString();
    Moo.send(command);
    newLine(">"+ command);
    Commands.addElement(command);
    commandIndex = Commands.size();
    jTextCommand.setText("");
  }
  void  jTextCommand_keyReleased(KeyEvent e){
    //newLine("KeyReleased:"+e.getKeyCode());
  }
  void  jTextCommand_keyPressed(KeyEvent e){
   // newLine("KeyPressed:"+e.getKeyCode());
    if ( e.getKeyCode() == 38 && commandIndex >= 0) {
      if ( commandIndex > 0 ) commandIndex--;
      jTextCommand.setText((String)Commands.elementAt(commandIndex));
    }
    else if ( e.getKeyCode() == 40 && commandIndex < Commands.size()) {
      if ( commandIndex == Commands.size()-1 ){
        jTextCommand.setText("");
        commandIndex = Commands.size();
      }
      else{
        commandIndex++;
        jTextCommand.setText((String)Commands.elementAt(commandIndex));
      }
    }
  }
}