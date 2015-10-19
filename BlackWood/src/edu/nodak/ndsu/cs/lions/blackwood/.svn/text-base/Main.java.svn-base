package edu.nodak.ndsu.cs.lions.blackwood;

import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;
import java.awt.Color;
import java.net.URL;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Class Main simply contains the main method of the Java application.
 *
 * It will initialize the login screen.
 *
 * @author foertsch
 */
public class Main
{
	private static Logger logger = Logger.getLogger(Main.class);
	
	public static final Color COLOR_YELLOW = new Color(251, 236, 175);
	public static final Color COLOR_RED = new Color(51, 32, 35);

	private DebugPane debugPane = null;
	private CommandListener commandListener = null;

	/**
	 *
	 * @return
	 */
	private MooConnection initializeMooConnection()
	{
		MooConnection moo = null;
		moo = new MooConnection();
		debugPane = new DebugPane(moo, "Score");
		moo.addListener(debugPane);
		return moo;
	}

	private void runApplication(String host, String port, String userName, String password)
	{
		LoginFrame mainapp = new LoginFrame(initializeMooConnection(), debugPane, host, port, userName, password);
		mainapp.setLocationRelativeTo(null);
		mainapp.setVisible(true);
	}

	/**
	 * The main method of the application.
	 *
	 * @param args no command line arguments are implemented yet.
	 */
	public static void main(String[] args) throws java.net.MalformedURLException
	{
		System.out.println("Possible arguments are --host --port --username --password --debug");
//		org.apache.log4j.PropertyConfigurator.configure(new URL("http://wwwic.ndsu.edu/~foertsch/log4j.properties"));
		Logger.getRootLogger().setLevel(Level.INFO);
		logger.info("Application started.");
		String host = "lions.cs.ndsu.nodak.edu";
		String port = "7779";
		String userName = "";
		String password = "";
		for (int argsIndex = 0; argsIndex < args.length; argsIndex++)
		{
			String currentArg = args[argsIndex];
			if (currentArg.equals("-h") || currentArg.equals("--help"))
			{
				System.out.println("Possible arguments are --host --port --username --password --debug");
				System.out.println("CLI Arguments aren't really a feature.  Don't depend on them.");
				return;
			}
			
			else if(currentArg.equals("--host") && argsIndex + 1 != args.length)
			{
				host = args[argsIndex + 1];
			}
			
			else if(currentArg.equals("--port") && argsIndex + 1 != args.length)
			{
				port = args[argsIndex + 1];
			}
			
			else if(currentArg.equals("--username") && argsIndex + 1 != args.length)
			{
				userName = args[argsIndex + 1];
			}

			else if(currentArg.equals("--password") && argsIndex + 1 != args.length)
			{
				password = args[argsIndex + 1];
			}
			
			else if(currentArg.equals("--debug"))
			{
				Logger.getRootLogger().setLevel(Level.DEBUG);
				logger.debug("Debug logging enabled.");
			}
		}
		System.out.println("Possible arguments are --host --port --username --password --debug");
		Main main = new Main();
		main.runApplication(host, port, userName, password);System.out.println("Possible arguments are --host --port --username --password --debug");
	}

}