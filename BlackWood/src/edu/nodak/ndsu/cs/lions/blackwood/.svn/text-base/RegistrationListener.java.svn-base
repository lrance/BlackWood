package edu.nodak.ndsu.cs.lions.blackwood;

/**

 */
import edu.nodak.ndsu.cs.lions.blackwood.GUI.RegistrationFrame;
import java.util.StringTokenizer;

/** The main directive handler for the client. */
public class RegistrationListener implements MooListener
{
	RegistrationFrame registrationFrame;

	public RegistrationListener(RegistrationFrame registrationFrame)
	{
		this.registrationFrame = registrationFrame;
	}

	public void newLine(String line)
	{
		System.out.println("line = " + line);

		String myTokens[];
		StringTokenizer temp = new StringTokenizer(line, "|\n");
		int Counter = 0;
		myTokens = new String[temp.countTokens()];
		while (temp.hasMoreTokens())
		{
			myTokens[Counter] = temp.nextToken();
			Counter++;
		}
		if (Counter > 0)
		{
			if(myTokens[0].equals("#CREATE_PLAYER_FAIL"))
			{
				this.registrationFrame.registrationStatus = RegistrationFrame.RegistrationStatus.FAILED;
				this.registrationFrame.errorMessage = myTokens[1];
			}
			else if (myTokens[0].equals("#CREATE_PLAYER_SUCCESS"))
			{
				this.registrationFrame.registrationStatus = RegistrationFrame.RegistrationStatus.SUCCESS;
			}else if(myTokens[0].indexOf("<HOODS>")>=0){
                            this.registrationFrame.hoods(myTokens[0]);
                        }else if(myTokens[0].indexOf("<STORES>")>=0){
                            this.registrationFrame.stores(myTokens[0]);
                        }else if(myTokens[0].indexOf("<CONSUMERPOPULATION>")>=0){
                            this.registrationFrame.consumerPop(myTokens[0]);
                        }
		}
	}
}
