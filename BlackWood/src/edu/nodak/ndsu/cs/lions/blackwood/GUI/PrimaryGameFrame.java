package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import edu.nodak.ndsu.cs.lions.blackwood.AdDialog;
import edu.nodak.ndsu.cs.lions.blackwood.HelpWindow;
import edu.nodak.ndsu.cs.lions.blackwood.HireFireDialog;
import edu.nodak.ndsu.cs.lions.blackwood.Main;
import edu.nodak.ndsu.cs.lions.blackwood.MapFrame;
import edu.nodak.ndsu.cs.lions.blackwood.MooConnection;
import edu.nodak.ndsu.cs.lions.blackwood.Newspaper;
import edu.nodak.ndsu.cs.lions.blackwood.Stage;
import edu.nodak.ndsu.cs.lions.blackwood.StockDialog;
import edu.nodak.ndsu.cs.lions.blackwood.ChatDialog;
import edu.nodak.ndsu.cs.lions.blackwood.DebugPane;
import edu.nodak.ndsu.cs.lions.blackwood.SimpleImage;
import edu.nodak.ndsu.cs.lions.blackwood.LedgerFrame;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.VerticalFlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 * <p>
 * Title: Blackwood
 * </p>
 * <p>
 * Description: Blackwood UI and Lambda Moo Interface
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: NDSU
 * </p>
 * 
 * @author Bradley Vender
 * @email Bradley.Vender@ndsu.edu
 * @version 1.1
 */
public class PrimaryGameFrame extends JFrame {
	private static Logger logger = Logger.getLogger(PrimaryGameFrame.class);
	/** An array of JButtons to use for the eight navigation buttons. */
	private JButton[] navigationButtons = new JButton[8];
	/** The names of the eight navigation buttons to use. */
	private static final String[] navigationButtonNames = { "northwest", "north", "northeast", "west", "east",
			"southwest", "south", "southeast" };
	/** The action commands for the eight navigation buttons. */
	private static final String[] navigationButtonActionCommands = { "jButtonNW", "jButtonN", "jButtonNE", "jButtonW",
			"jButtonE", "jButtonSW", "jButtonS", "jButtonSE" };
	/** The connection to send/receive information with the server. */
	private MooConnection mooConnection;
	/**
	 * The text describing the room you are currently in -- a class variable
	 * because it needs to be updated when you change rooms.
	 */
	private JLabel roomLabel;
	/** The entity holding the avatars and locations of the player. */
	private Stage mainStage;
	/**
	 * This is the text area displaying your current status -- a class variable
	 * because it needs to be edited as your money changes.
	 */
	private JTextArea financialTextArea;
	/** This is the game clock on the frame. */
	private GameClock gameclock;
	/**
	 * The JPanel holding the navigational list within the directional buttons.
	 * Needs confirmation -- not positive....
	 */
	private JPanel internalNavigationListPanel;
	/** The text pane in the middle showing moo log information. */
	private JTextPane informationTextPane;
	/** This window houses the debugging information. */
	private SimpleImage simple;
	private DebugPane debugPane;
	// // TODO
	// These three objects are slated to be removed eventually since public
	// objects are bad objects. They are placed here as a temporary fix to a
	// terrible problem.
	/** */
	public StockDialog StockMan;
	/** */
	public ChatDialog ChatMan;
	/** */
	public Newspaper Newspaper;
	/** */
	public AdDialog AdMan;
	/** */
	public HireFireDialog HireFire;
	/** */
	public MapFrame Map;
	/** */
    public LedgerFrame Ledger;
	
    /**
	 * The constructor handles the creation of the JFrame and assigning the moo
	 * connection to the proper variables.
	 * 
	 * @param mooConnection
	 *            the object used to communicate with the moo.
	 */
	/*
	 * public PrimaryGameFrame(MooConnection mooConnection) { this.mooConnection
	 * = mooConnection; this.setTitle("Blackwood");
	 * this.enableEvents(AWTEvent.WINDOW_EVENT_MASK); initialize(); pack(); }
	 */

	/**
	 * The constructor handles the creation of the JFrame and assigning the moo
	 * connection to the proper variables.
	 * 
	 * @param mooConnection
	 *            the object used to communicate with the moo.
	 * @param debugPane
	 *            used to show the moo connection output.
	 */
	public PrimaryGameFrame(MooConnection mooConnection, DebugPane debugPane) {
		this.debugPane = debugPane;
		this.mooConnection = mooConnection;
		this.setTitle("Blackwood");
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		initialize();
		pack();
	}

	/**
	 * Utility method used to create all of the components of the panel, add it
	 * to the panel, and eventually add the panel to the JFrame.
	 */
	private void initialize() {
		JPanel primaryPanelForFrame = initializePrimaryPanel();
		addMenuButtonsToPanel(primaryPanelForFrame);
		addNavigationButtonsToPanel(primaryPanelForFrame);
		addLabelsToPanel(primaryPanelForFrame);
		addInformationTextPaneToPanel(primaryPanelForFrame);
		addTalkTextFieldToPanel(primaryPanelForFrame);
		addGameClockToPanel(primaryPanelForFrame);
		addMainStageToPanel(primaryPanelForFrame);
		addTextAreaStatusToPanel(primaryPanelForFrame);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.gif"));
		this.getContentPane().add(primaryPanelForFrame, BorderLayout.CENTER);
		addWindowListener(new WindowHandler());
	}

	/**
	 * Commands to initialize the primary panel (where the primary panel is the
	 * core underlying panel of the JFrame).
	 * 
	 * @return the resulting panel.
	 */
	private JPanel initializePrimaryPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new XYLayout());
		panel.setBackground(Main.COLOR_YELLOW);
		panel.setForeground(Main.COLOR_RED);
		return panel;
	}

	/**
	 * Creates and adds the menu button panels onto the panel parameter.
	 * 
	 * @param panel
	 *            the panel to add the buttons to.
	 */
	private void addMenuButtonsToPanel(JPanel panel) {
		JButton homeJButton = initializeButton(Images.HOME_BUTTON_ICON, new HomeButtonActionListener());
		panel.add(homeJButton, new XYConstraints(10, 30, 125, 50));

		JButton mapJButton = initializeButton(Images.MAP_BUTTON_ICON, new MapButtonActionListener());
		panel.add(mapJButton, new XYConstraints(10, 85, 125, 50));

		JButton stockJButton = initializeButton(Images.STOCK_BUTTON_ICON, new StockButtonActionListener());
		panel.add(stockJButton, new XYConstraints(10, 140, 125, 50));

		JButton orderJButton = initializeButton(Images.ORDER_BUTTON_ICON, new OrderButtonActionListener());
		panel.add(orderJButton, new XYConstraints(10, 195, 125, 50));

		JButton newsJButton = initializeButton(Images.NEWS_BUTTON_ICON, new NewsButtonActionListener());
		panel.add(newsJButton, new XYConstraints(10, 250, 125, 50));

		JButton scoresJButton = initializeButton(Images.SCORES_BUTTON_ICON, new ScoresButtonActionListener());
		panel.add(scoresJButton, new XYConstraints(10, 305, 125, 50));

		JButton ledgerJButton = initializeButton(Images.LEDGER_BUTTON_ICON, new LedgerButtonActionListener());
		panel.add(ledgerJButton, new XYConstraints(7, 360, 126, 50));

		JButton hireFireJButton = initializeButton(Images.HIREFIRE_BUTTON_ICON, new HireFireButtonActionListener());
		panel.add(hireFireJButton, new XYConstraints(134, 358, 190, 51));

		JButton advertiseJButton = initializeButton(Images.ADVERTISE_BUTTON_ICON, new AdvertiseButtonActionListener());
		panel.add(advertiseJButton, new XYConstraints(217, 476, 197, 54));

		JButton optionsJButton = initializeButton(Images.OPTIONS_BUTTON_ICON, new OptionsButtonActionListener());
		panel.add(optionsJButton, new XYConstraints(218, 530, 194, 51));

		JButton helpJButton = initializeButton(Images.HELP_BUTTON_ICON, new HelpButtonActionListener());
		panel.add(helpJButton, new XYConstraints(413, 475, 137, 57));

		JButton quitJButton = initializeButton(Images.QUIT_BUTTON_ICON, new QuitButtonActionListener());
		panel.add(quitJButton, new XYConstraints(413, 530, 132, 51));

		JButton chatJButton = initializeButton(Images.CHAT_BUTTON_ICON, new ChatButtonActionListener());
		panel.add(chatJButton, new XYConstraints(520, 365, 25, 25));
	}

	/**
	 * Used to initialize each of the individual option buttons on the main
	 * frame.
	 * 
	 * @param image
	 *            the image to use for the button icon(s)
	 * @param actionListener
	 *            the action to be performed when this button is clicked
	 * @return the completed button
	 */
	private JButton initializeButton(ButtonImage image, ActionListener actionListener) {
		JButton button = new JButton();
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setIcon(image.getImage());
		button.setPressedIcon(image.getImagePressed());
		button.addActionListener(actionListener);

		return button;
	}

	/**
	 * Adds the navigational (directional) buttons to the panel, along with the
	 * panel inside the navigation, a list of places that aren't really in any
	 * direction.
	 * 
	 * @param panel
	 *            the panel to add it to.
	 */
	private void addNavigationButtonsToPanel(JPanel panel) {
		NavigationButtonActionListener navigationListener = new NavigationButtonActionListener();
		for (int i = 0; i < navigationButtons.length; i++) {
			navigationButtons[i] = initializeNavigationalButton(Images.NAVIGATION_BUTTONS[i], navigationListener,
					navigationButtonActionCommands[i]);
		}

		panel.add(navigationButtons[0], new XYConstraints(563, 369, 43, 41));
		panel.add(navigationButtons[1], new XYConstraints(601, 361, 108, 36));
		panel.add(navigationButtons[2], new XYConstraints(710, 370, 39, 36));
		panel.add(navigationButtons[3], new XYConstraints(556, 402, 39, 121));
		panel.add(navigationButtons[4], new XYConstraints(718, 402, 35, 115));
		panel.add(navigationButtons[5], new XYConstraints(565, 518, 37, 39));
		panel.add(navigationButtons[6], new XYConstraints(602, 527, 109, 31));
		panel.add(navigationButtons[7], new XYConstraints(714, 516, 38, 35));

		JPanel navigationExtrasPanel = new JPanel();
		navigationExtrasPanel.setLayout(new XYLayout());
		internalNavigationListPanel = new JPanel();
		internalNavigationListPanel.setLayout(new VerticalFlowLayout());
		JScrollPane NavScroller = new JScrollPane(internalNavigationListPanel);
		NavScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panel.add(navigationExtrasPanel, new XYConstraints(593, 396, -1, 133));
		navigationExtrasPanel.add(NavScroller, new XYConstraints(2, 1, 124, 131));
	}

	/**
	 * Used to initialize the navigational buttons (sw, ne, etc.) on the frame.
	 * 
	 * @param image
	 *            the image to use for the button icon(s)
	 * @param actionListener
	 *            the action to be performed when clicked
	 * @param actionCommand
	 *            the actionCommand is the identifier used to tell the moo which
	 *            direction the user is going.
	 * @return the completed button
	 */
	private JButton initializeNavigationalButton(ButtonImage image, ActionListener actionListener, String actionCommand) {
		JButton button = new JButton();
		button.setEnabled(false);
		button.setOpaque(false);
		button.setIcon(image.getImage());
		button.setDisabledIcon(image.getImageDisabled());
		button.setPressedIcon(image.getImagePressed());
		button.setActionCommand(actionCommand);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.addActionListener(actionListener);

		return button;
	}

	/**
	 * Adds the labels and the image labels to the panel.
	 * 
	 * @param panel
	 *            the main panel of the frame, that the labels are added to.
	 */
	private void addLabelsToPanel(JPanel panel) {
		JLabel gameTimeLabel = new JLabel(Images.imgBGTime);
		panel.add(gameTimeLabel, new XYConstraints(628, 22, 86, 25));

		JLabel blackwoodSignLabel = new JLabel(Images.imgBGSign);
		panel.add(blackwoodSignLabel, new XYConstraints(172, 5, 435, 92));

		JLabel rightBarForBlackwoodSignLabel = new JLabel(Images.imgBGBar);
		panel.add(rightBarForBlackwoodSignLabel, new XYConstraints(603, 4, 160, -1));

		JLabel leftBarForBlackwoodSignLabel = new JLabel(Images.imgBGBar);
		panel.add(leftBarForBlackwoodSignLabel, new XYConstraints(1, 6, 173, 22));

		JLabel stageTopBorderLabel = new JLabel(Images.imgBGStageT);
		panel.add(stageTopBorderLabel, new XYConstraints(134, 89, 611, 22));

		JLabel stageRightBorderLabel = new JLabel(Images.imgBGStageR);
		panel.add(stageRightBorderLabel, new XYConstraints(739, 88, 14, 275));

		JLabel stageBottomBorderLabel = new JLabel(Images.imgBGStageB);
		panel.add(stageBottomBorderLabel, new XYConstraints(137, 346, 617, 19));

		JLabel stageLeftBorderLabel = new JLabel(Images.imgBGStageL);
		panel.add(stageLeftBorderLabel, new XYConstraints(134, 89, 17, 274));

		JLabel informationTopBorderLabel = new JLabel(Images.imgBGInfoT);
		panel.add(informationTopBorderLabel, new XYConstraints(4, 408, 216, 23));

		JLabel informationLeftBorderLabel = new JLabel(Images.imgBGInfoL);
		panel.add(informationLeftBorderLabel, new XYConstraints(2, 417, 21, -1));

		JLabel informationRightBorderLabel = new JLabel(Images.imgBGInfoR);
		panel.add(informationRightBorderLabel, new XYConstraints(208, 416, 13, 163));

		JLabel informationBottomBorderLabel = new JLabel(Images.imgBGInfoB);
		panel.add(informationBottomBorderLabel, new XYConstraints(12, 565, 198, 16));

		roomLabel = new JLabel("room label");
		roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(roomLabel, new XYConstraints(335, 363, 211, 23));

		JLabel talkLabel = new JLabel(Images.imgBGTalk);
		panel.add(talkLabel, new XYConstraints(224, 440, 72, 29));
	}

	/**
	 * Creates and adds the information Text Pane (which is the text field in
	 * the middle with moo connection output).
	 * 
	 * @param panel
	 *            the panel to add the information Text Pane to.
	 */
	private void addInformationTextPaneToPanel(JPanel panel) {
		informationTextPane = new JTextPane();
		informationTextPane.setEnabled(true);
		informationTextPane.setEditable(false);
		informationTextPane.setFont(new java.awt.Font("SansSerif", 1, 10));
		informationTextPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		informationTextPane.setToolTipText("");
		panel.add(informationTextPane, new XYConstraints(336, 392, 211, 45));
	}

	/**
	 * Creates and adds the text field used to chat.
	 * 
	 * @param panel
	 *            the panel to add it to.
	 */
	private void addTalkTextFieldToPanel(JPanel panel) {
		JTextField jTextFieldTalk = new JTextField();
		jTextFieldTalk.addActionListener(new TalkFieldActionListener());
		panel.add(jTextFieldTalk, new XYConstraints(297, 445, 238, 22));
	}

	/**
	 * Creates and adds the game cycle image.
	 * 
	 * @param panel
	 *            the panel to add it to.
	 */
	private void addGameClockToPanel(JPanel panel) {
		gameclock = new GameClock();
		panel.add(gameclock, new XYConstraints(634, 45, 74, 41));
	}

	/**
	 * Creates and adds the 'Stage' component to the panel.
	 * 
	 * @param panel
	 *            the panel to add the stage to.
	 */
	private void addMainStageToPanel(JPanel panel) {
		mainStage = new Stage(mooConnection);
		mainStage.setBackground(Main.COLOR_YELLOW);
		panel.add(mainStage, new XYConstraints(142, 104, 600, 250));
	}

	/**
	 * Creates and adds the financialTextArea to the bottom of the panel.
	 * 
	 * @param panel
	 *            the panel to add it to.
	 */
	private void addTextAreaStatusToPanel(JPanel panel) {
		financialTextArea = new JTextArea();
		financialTextArea.setText("");
		panel.add(financialTextArea, new XYConstraints(22, 428, 187, 141));
	}

	/**
	 * Accessor method for the Stage used to display current users.
	 * 
	 * @return the Stage object.
	 */
	public Stage getStage() {
		return mainStage;
	}

	/**
	 * Appends text to the text pane in the middle of the interface.
	 * 
	 * @param line
	 *            the line to be appended.
	 */
	public void appendToInformationTextPane(String line) {
		informationTextPane.setText(line);
	}

	/**
	 * Removes all of the buttons in the navigation block, and disables all of
	 * the directional buttons.
	 */
	public void clearExits() {
		internalNavigationListPanel.removeAll();
		internalNavigationListPanel.repaint();
		for (int i = 0; i < navigationButtons.length; i++) {
			JButton currentButton = navigationButtons[i];
			currentButton.setEnabled(false);
			currentButton.setToolTipText("");
		}
	}

	/**
	 * Command called to add an exit to the navigation interface.
	 * 
	 * @param mooDirectionExitGoesTo
	 *            the moo identifier that the button will go to.
	 * @param name
	 *            the name of the exit (Park, southwest)
	 * @param description
	 *            used for the tool tip of the room to enter.
	 */
	public void addExit(String mooDirectionExitGoesTo, String name, String description) {
		boolean isDirection = false;
		for (int i = 0; i < navigationButtons.length; i++) {
			if (navigationButtonNames[i].equalsIgnoreCase(name)) {
				isDirection = true;
				resetNavigationalButton(navigationButtons[i], mooDirectionExitGoesTo, description);
				break;
			}
		}

		if (!isDirection) {
			JButton newButton = new JButton();
			if (name.length() > 12) {
				newButton.setText(name.substring(0, 10) + "...");
			} else {
				newButton.setText(name);
			}
			newButton.setToolTipText(description);
			newButton.setActionCommand(mooDirectionExitGoesTo);
			newButton.addActionListener(new NavigationButtonActionListener());
			// newButton.addActionListener(new
			// NavButtonListener(mooDirectionExitGoesTo));
			internalNavigationListPanel.add(newButton, null);
		}
	}

	/**
	 * Used to reset any of the eight directional buttons -- commonly used when
	 * entering a new room.
	 * 
	 * @param navigationButton
	 *            the button to modify
	 * @param name
	 *            the name of the exit (Park, southwest)
	 * @param description
	 *            used for the tool tip of the room to enter.
	 */
	private void resetNavigationalButton(JButton navigationButton, String name, String description) {
		navigationButton.setEnabled(true);
		navigationButton.setActionCommand(name);
		navigationButton.setToolTipText(description);
		navigationButton.setContentAreaFilled(false);
	}

	/**
	 * Sets the game clock to a certain time.
	 * 
	 * @param current
	 *            the current time of the period.
	 * @param maxtime
	 *            the maximum time in a period
	 */
	public void setGameClock(int current, int maxtime) {
		gameclock.setTime(current, maxtime);
	}

	/**
	 * Assigns the text to be the value in the text are in the bottom left of
	 * the interface.
	 * 
	 * @param text
	 *            the new value in the text area.
	 */
	public void setFinancialAreaText(String text) {
		financialTextArea.setText(text);
	}

	/**
	 * Modifies the label under the stage to describe the current room you are
	 * in.
	 * 
	 * @param text
	 *            the new name/description of the room.
	 */
	public void setRoomLabel(String text) {
		roomLabel.setText(text);
	}

	/**
	 * Simulates your character speaking in the moo
	 * 
	 * @param who
	 *            the object number of the character speaking
	 * @param what
	 *            the text entered
	 */
	public void talk(String who, String what) {
		mainStage.talk(who, what);
	}

	/**
	 * The action listener used by the Home JButton
	 */
	private class HomeButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			mooConnection.send("home");
		}
	}

	/**
	 * The action listener used by the Map JButton
	 */
	private class MapButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			Map = new MapFrame(mooConnection);
		}
	}

	/**
	 * The action listener used by the Stock JButton
	 */
	private class StockButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			StockMan = new StockDialog(mooConnection);
		}
	}

	/**
	 * The action listener used by the Chat JButton
	 */
	private class ChatButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			ChatMan = new ChatDialog(mooConnection);
		}
	}

	/**
	 * The action listener used by the Order JButton
	 */
	private class OrderButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			mooConnection.send("supplier_list");
		}
	}

	/**
	 * The action listener used by the News JButton
	 */
	private class NewsButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			try {
				Newspaper = new Newspaper(mooConnection);
				// JOptionPane.showMessageDialog(null,
				// "The news button has not been implemented.");
			} catch (Exception ex) {
				java.util.logging.Logger.getLogger(PrimaryGameFrame.class.getName()).log(
						java.util.logging.Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * The action listener used by the Scores JButton
	 */
	private class ScoresButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent event) {
			debugPane.setVisible(true);
			// JOptionPane.showMessageDialog(null,
			// "Scores Button Listener is not presently working.");
		}
	}

	/**
	 * The action listener used by the Ledger JButton
	 */
	private class LedgerButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			//JOptionPane.showMessageDialog(null, "Ledger button is not implemented.");
                        Ledger = new LedgerFrame(mooConnection);
		}
	}

	/**
	 * The action listener used by the HireFire JButton
	 */
	private class HireFireButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			// HireFire = new HireFireDialog(mooConnection, loginFrame);
			HireFire = new HireFireDialog(mooConnection);
		}
	}

	/**
	 * The action listener used by the Advertise JButton
	 */
	private class AdvertiseButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			AdMan = new AdDialog(mooConnection);
		}
	}

	/**
	 * The action listener used by the Options JButton
	 */
	private class OptionsButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			//simple = new SimpleImage();
			//simple.setVisible(true);
			// simple.build();
			 JOptionPane.showMessageDialog(null, "Option Button Listener is not yet implemented.  Sorry. :)");
		}
	}

	/**
	 * The action listener used by the Help JButton
	 */
	private class HelpButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			new HelpWindow().setVisible(true);
		}
	}

	/**
	 * The action listener used by the Quit JButton
	 */
	private class QuitButtonActionListener implements ActionListener {

		/**
		 * The method called by the action listener
		 * 
		 * @param event
		 *            the event that occured
		 */
		public void actionPerformed(ActionEvent event) {
			setVisible(false);
			mooConnection.send("@quit");
			System.exit(0);
		}
	}

	/**
	 * The action perfored within the talk text field.
	 */
	private class TalkFieldActionListener implements ActionListener {

		/**
		 * Sends the 'say' command to the moo and resets the text in the field
		 * 
		 * @param event
		 *            the event sent
		 */
		public void actionPerformed(ActionEvent event) {
			JTextField textField = (JTextField) event.getSource();
			mooConnection.send("Say " + textField.getText());
			textField.setText("");
		}
	}

	/**
	 * The action performed by the navigation buttons.
	 */
	private class NavigationButtonActionListener implements ActionListener {

		/**
		 * The action performed by the navigation buttons, sending the user to
		 * that 'room'.
		 * 
		 * @param event
		 *            the event fired
		 */
		public void actionPerformed(ActionEvent event) {
			JButton button = (JButton) event.getSource();
			mooConnection.send("double_click #" + button.getActionCommand());
		}
	}

	/**
	 * The window handler for this PrimaryGame Class
	 */
	private class WindowHandler extends WindowAdapter {

		/**
		 * Sends the quit command to the moo and exits the application.
		 * 
		 * @param e
		 *            the window event.
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			mooConnection.send("@quit");
			System.exit(0);
		}
	}
}
