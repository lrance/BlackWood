/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import edu.nodak.ndsu.cs.lions.blackwood.Utilities;
import javax.swing.ImageIcon;

/**
 * Class Image is a place to consolidate the need for ImageIcon variables into one place.  Instead of having a large
 * list of ImageIcon variables in the LoginFrame class and the PrimaryGameFrame class, they can be defined here.  That makes
 * maintenance easier, and will help to make the other classes a little easier to look at.  You can thank me later.
 *
 * Last modified:  2010-03
 * @author foertsch
 */
public class Images
{

	/** The string path to the Interface subpackage */
	private static final String INTERFACE_PATH = new String("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/interface/");

	/** The Help interface button. */
	protected static final ButtonImage HELP_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgHelp.gif", INTERFACE_PATH + "imgHelpPressed.gif");

	/** The Login interface button. */
	protected static final ButtonImage LOGIN_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgLogin.gif", INTERFACE_PATH + "imgLoginPressed.gif");

	/** The Register interface button. */
	protected static final ButtonImage REGISTER_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgRegister.gif", INTERFACE_PATH + "imgRegisterPressed.gif");

	/** The Cancel interface button. */
	protected static final ButtonImage CANCEL_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgCancel.gif", INTERFACE_PATH + "imgCancelPressed.gif");

	/** The Home interface button. */
	protected static final ButtonImage HOME_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgHome.gif", INTERFACE_PATH + "imgHomePressed.gif");

	/** The Map interface button. */
	protected static final ButtonImage MAP_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgMap.gif", INTERFACE_PATH + "imgMapPressed.gif");

	/** The Stock interface button. */
	protected static final ButtonImage STOCK_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgStock.gif", INTERFACE_PATH + "imgStockPressed.gif");

	/** The Order interface button. */
	protected static final ButtonImage ORDER_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgOrder.gif", INTERFACE_PATH + "imgOrderPressed.gif");

	/** The News interface button. */
	protected static final ButtonImage NEWS_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgNews.gif", INTERFACE_PATH + "imgNewsPressed.gif");

	/** The Scores interface button. */
	protected static final ButtonImage SCORES_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgScores.gif", INTERFACE_PATH + "imgScoresPressed.gif");

	/** The Ledger interface button. */
	protected static final ButtonImage LEDGER_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgLedger.gif", INTERFACE_PATH + "imgLedgerPressed.gif");

	/** The Advertise interface button. */
	protected static final ButtonImage ADVERTISE_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgAdvertise.gif", INTERFACE_PATH + "imgAdvertisePressed.gif");

	/** The Hire/Fire interface button. */
	protected static final ButtonImage HIREFIRE_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgHireFire.gif", INTERFACE_PATH + "imgHireFirePressed.gif");

	/** The Quit interface button. */
	protected static final ButtonImage QUIT_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgQuit.gif", INTERFACE_PATH + "imgQuitPressed.gif");

	/** The Options interface button. */
	protected static final ButtonImage OPTIONS_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgOptions.gif", INTERFACE_PATH + "imgOptionsPressed.gif");
        /** The Chat interface button. */
	protected static final ButtonImage CHAT_BUTTON_ICON = new ButtonImage(INTERFACE_PATH + "imgChat.gif", INTERFACE_PATH + "imgChatPressed.gif");

	/** The Register interface button. */
	protected static final ImageIcon imgBackDrop = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "MainBackground.jpg"));//create the imageicon
	//protected static final ImageIcon imgHelp = new ImageIcon(getURL(INTERFACE_PATH + "imgHelp.gif"));//creates a new image

	/** An array for the eight naviagional buttons. */
	protected static final ButtonImage[] NAVIGATION_BUTTONS =
	{
		new ButtonImage(INTERFACE_PATH + "nav/nw.gif", INTERFACE_PATH + "nav/disnw.gif", INTERFACE_PATH + "nav/disnw.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/n.gif", INTERFACE_PATH + "nav/disn.gif", INTERFACE_PATH + "nav/disn.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/ne.gif", INTERFACE_PATH + "nav/disne.gif", INTERFACE_PATH + "nav/disne.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/w.gif", INTERFACE_PATH + "nav/disw.gif", INTERFACE_PATH + "nav/disw.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/e.gif", INTERFACE_PATH + "nav/dise.gif", INTERFACE_PATH + "nav/dise.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/sw.gif", INTERFACE_PATH + "nav/dissw.gif", INTERFACE_PATH + "nav/dissw.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/s.gif", INTERFACE_PATH + "nav/diss.gif", INTERFACE_PATH + "nav/diss.gif"),
		new ButtonImage(INTERFACE_PATH + "nav/se.gif", INTERFACE_PATH + "nav/disse.gif", INTERFACE_PATH + "nav/disse.gif"),
	};

	//protected static final ImageIcon imgHelpPressed = new ImageIcon(getURL(INTERFACE_PATH + "imgHelpPressed.gif"));
	/** The icon for the application window */
	protected static final ImageIcon windowIcon = new ImageIcon(Utilities.getURL("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.ico"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGBar = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgbar.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGSign = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgsign.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGTime = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgtime.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGStageT = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgstaget.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGStageL = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgstagel.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGStageR = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgstager.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGStageB = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgstageb.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGTalk = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bgtalkl.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGInfoT = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bginfot.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGInfoR = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bginfor.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGInfoL = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bginfol.jpg"));

	/** Background image for the GUI */
	protected static final ImageIcon imgBGInfoB = new ImageIcon(Utilities.getURL(INTERFACE_PATH + "bginfob.jpg"));

	/**
	 * Private Default Constructor -- prevents creating an object of this class.
	 */
	private Images()
	{
		// cannot instanciate an object of type Images.
	}
}
