/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nodak.ndsu.cs.lions.blackwood;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Chris Imdieke
 */
/**
 * Converted from DollarBay for Blackwood
 * @author William Harris and Wesley Rogers
 */
import com.borland.jbcl.layout.XYLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.StringTokenizer;



public class LedgerFrame extends JFrame
{  
   private XYLayout xYLayout = new XYLayout();
   public String salesString;
   public String costString;
   public String ledgerString;
   public JEditorPane textSales;
   public JEditorPane textCost;
   public JEditorPane textLedger;

   MooConnection moo;
   LedgerMooListener listen;
   public JTabbedPane tabbedPane;
   public static final int DEF_X=800, DEF_Y=600;
/**
 * Constructor
 * @param moo
 */
   public LedgerFrame(MooConnection moo)
   {

		super("Blackwood Ledger");
		this.moo = moo;
		try
                {
			jbInit();
		} catch (Exception ex)
                {
			ex.printStackTrace();
		}
    }

/**
 * Initializes the JFrame tabbed.  Sets up the MOOListener and sends the
 * verb comment to the LamdaMOO.
 * @throws Exception
 */
	private void jbInit() throws Exception
        {
		this.setLayout(xYLayout);
		this.setVisible(true);
		// this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800, 600));
                


                this.moo.addListener(new LedgerMooListener());
                this.moo.send(";$g.ledger:shipment_location(#10799)");
                //Sends the verb that tests the object to see if its a shipment
                //then calls the MooListener.

                
                
                tabbedPane = new JTabbedPane();

                tabbedPane.addTab("Sales", null, createSalesPanel(),"View the sales ledger");
                tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

                tabbedPane.addTab("Cost", null, createCostsPanel(),"View the cost of items");
                tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

                tabbedPane.addTab("Ledger", null, createLedgerPanel(),"View your personal objects and locations");
                tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


                tabbedPane.setPreferredSize(new Dimension(getWidth(),getHeight()));
                JPanel overallPanel = new JPanel();
                overallPanel.add(tabbedPane);
                getContentPane().add(tabbedPane);

                pack();
                tabbedPane.setVisible(true);
                
  }

  public void setTab(int index)
  {  tabbedPane.setSelectedIndex(index);
  }
/**
 * Creates the Sales Panel for the Sales Tab.
 * @return
 */
  public JPanel createSalesPanel()
  {  JPanel panel = new JPanel(new BorderLayout());
     //panel.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));

     textSales = new JEditorPane("text/html","");
     textSales.setEditable(false);
     //text.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));
     textSales.getInsets(new Insets(10,25,10,25));
     textSales.setText(salesString);

     JScrollPane scroll = new JScrollPane(textSales);
     //scroll.setPreferredSize(new Dimension(DEF_X,DEF_Y));
     textSales.setCaretPosition(0);

     panel.add(scroll,BorderLayout.CENTER);
     return panel;
  }
/**
 * Creates the Ledger Panel for the Ledger Tab.
 * @return
 */
  public JPanel createLedgerPanel()
  {  JPanel panel = new JPanel(new BorderLayout());
     //panel.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));

     textLedger = new JEditorPane("text/html","");
     textLedger.setEditable(false);
     //text.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));
     textLedger.getInsets(new Insets(10,25,10,25));
     textLedger.setText("<TABLE BORDER><TH>Quantity</TH><TH>Product</TH><TH>"+
                      "Location</TH>");

     JScrollPane scroll = new JScrollPane(textLedger);
     //scroll.setPreferredSize(new Dimension(DEF_X,DEF_Y));
     textLedger.setCaretPosition(0);

     panel.add(scroll,BorderLayout.CENTER);
     return panel;
  }
/**
 * Creates the Cost Panel for the Cost Tab.
 * @return
 */
  public JPanel createCostsPanel()
  {  JPanel panel = new JPanel(new BorderLayout());
     //panel.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));

     textCost = new JEditorPane("text/html","");
     textCost.setEditable(false);
     //text.setPreferredSize(new Dimension(DEF_X-50,DEF_Y));
     textCost.getInsets(new Insets(10,25,10,25));
     textCost.setText(costString);

     JScrollPane scroll = new JScrollPane(textCost);
     //scroll.setPreferredSize(new Dimension(DEF_X,DEF_Y));
     textCost.setCaretPosition(0);

     panel.add(scroll,BorderLayout.CENTER);
     return panel;
  }

  public void setCostPanel(String text)
  {
     textCost.setText(text);
  }

  public void setSalesPanel(String text)
  {
     textSales.setText(text);
  }
  /**
   * The method that puts the info into the LedgerPanel.  Normally the
   * ledgerString is passed to it.  It sets up the Table header and then adds
   * the additional data.
   * @param text = The HTML string that makes up the data in the table.
   */
  public void setLedgerPanel(String text)
  {
      String intialText;
      intialText = "<TABLE BORDER><TH>Quantity</TH><TH>Product</TH><TH>"+
                      "Location</TH>" + text;
      textLedger.setText(intialText);
  }
/**
 * This method is used to add to the table that gets displayed on the Ledger
 * tab in LedgerFrame.  It adds to the ledgerString, which is a long string
 * of HTML code, and updates the LedgerPanel. 
 * @param quantity = Quantity of Shipment
 * @param product = Name of Shipment
 * @param store = Location of Shipment
 */
  public void setLedgerString(String quantity, String product, String store)
    {
        String tempQuantity, tempProduct, tempStore;
        tempQuantity = quantity;
        tempProduct = product;
        tempStore = store;

        ledgerString += "<TR><TD>" + tempQuantity + "</TD><TD>" + tempProduct +
                    "</TD><TD>" + tempStore + "</TD></TR>";
        setLedgerPanel(ledgerString);
    }

    /**
     * This is the MooListener for the LedgerFrame class.
     */

    private class LedgerMooListener implements MooListener
    {
	public void newLine(String line)
	{
        System.out.println("line = "+line);

	try {
				String lineArray[] = line.split(" ", 2);
				if (lineArray.length == 1) {
					if (lineArray[0].equalsIgnoreCase("#LEDGER_CLEAR")) {
						ledgerString = "";
                                                //Clears the ledgerString.
					}
					
					}
				else if (lineArray.length == 2) {
					if (lineArray[0].equalsIgnoreCase("#LEDGER_OBJECT")) {
						String a[] = lineArray[1].split("\\|");
						if (a.length == 4) {
							setLedgerString(a[1], a[2], a[3]);
						}
					} 
				} 
			} catch (Exception e) {/* This listener is not that important */
			}
		}
    }

}



