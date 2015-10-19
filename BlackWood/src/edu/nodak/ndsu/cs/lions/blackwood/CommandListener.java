package edu.nodak.ndsu.cs.lions.blackwood;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 */
import edu.nodak.ndsu.cs.lions.blackwood.GUI.PrimaryGameFrame;
import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;

import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** The main directive handler for the client. */
public class CommandListener implements MooListener
{
	LoginFrame loginFrame;
	PrimaryGameFrame Game;
	DialogBox DBox;
	QuizMachine QMachine;
	QuizMachine QMachine1;
	VenderDialog VBox;
	MooConnection Moo;

	PicturePad picBox;
	OrderDialog PriceBox;
	String roomType = "";

	public CommandListener()
	{
	}

	public CommandListener(PrimaryGameFrame game, MooConnection moo, LoginFrame loginFrame)
	{
		// the game object is the mainFrame used to interact with the user
		// this CommandListener class interacts directly with the user
		// through the game object.
		Game = game;
		Moo = moo;
		this.loginFrame = loginFrame;
	}

	public void newLine(String line)
	{
        System.out.println("line = "+line);
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
			//populate StatusArea with Status info from Moo Server
			if (myTokens[0].equals("#STATUS"))
			{
				String Status = "User: \t" + myTokens[2] + "\n";
				Status += "Cash: \t" + myTokens[3] + "\n";
				Status += "Net Worth: \t" + myTokens[4] + "\n";
				Status += "Debt: \t" + myTokens[5] + "\n";
				Status += "Profit: \t" + myTokens[6] + "\n";
				Game.setFinancialAreaText(Status);
				Game.setGameClock(Integer.parseInt(myTokens[7]), Integer.parseInt(myTokens[8]));
			}
			else if (myTokens[0].equals("#ROOM") || myTokens[0].equals("#MAP"))
			{
				roomType = myTokens[0];
				Game.clearExits();
				Game.getStage().setRoom(myTokens[0], myTokens[1], myTokens[2]);
				Game.setRoomLabel(myTokens[3]);
			}
			else if (myTokens[0].equals("#PLAYER"))
			{
				String name = new String();
				if(myTokens[2].equals("teacher1")){
				//QMachine = new QuizMachine(myTokens[9].trim().toString());
				QMachine1 = new QuizMachine(myTokens[9].trim().toString());
				QMachine1.setNewNameTitle(myTokens[9].trim().toString());
				System.out.println("this is QMachine head title: " + myTokens[9].trim().toString());
				}
				Game.getStage().setObject(myTokens[0], myTokens[1], myTokens[2], Integer.parseInt(myTokens[3]), Integer.parseInt(myTokens[4]), Integer.parseInt(myTokens[5]), Integer.parseInt(myTokens[6]) == 1, Integer.parseInt(myTokens[7]) == 1, Integer.parseInt(myTokens[8]) == 1, myTokens[9], "", name);
				
			}
			
			else if(myTokens[0].equals("#OBJECT") || myTokens[0].equals("#SELF")){
				String name = new String();
				if (myTokens.length == 11)
				{
					name = myTokens[10];
				}
				Game.getStage().setObject(myTokens[0], myTokens[1], myTokens[2], Integer.parseInt(myTokens[3]), Integer.parseInt(myTokens[4]), Integer.parseInt(myTokens[5]), Integer.parseInt(myTokens[6]) == 1, Integer.parseInt(myTokens[7]) == 1, Integer.parseInt(myTokens[8]) == 1, myTokens[9], "", name);
			}
			else if (myTokens[0].equals("#EXIT"))
			{
				Game.addExit(myTokens[1], myTokens[2], myTokens[10]);
				if (roomType.equals("#MAP"))
				{
					Game.getStage().setObject(myTokens[0], myTokens[1], myTokens[3], Integer.parseInt(myTokens[4]), Integer.parseInt(myTokens[5]), Integer.parseInt(myTokens[6]), Integer.parseInt(myTokens[7]) == 1, Integer.parseInt(myTokens[8]) == 1, Integer.parseInt(myTokens[9]) == 1, myTokens[2], myTokens[2], "");
				}
			}
			else if (myTokens[0].equals("#REMOVE"))
			{
				Game.getStage().removeObject(myTokens[1]);
			}
			else if (myTokens[0].equals("#END_ROOM") || myTokens[0].equals("#END_MAP"))
			{
				//Game.setExits(); this was an empty method in the PrimaryGameFrame Class....
				// either this case never occured, or it was simply not implemented and it should have been....
			}
			else if (myTokens[0].equals("#SPEAK"))
			{
				Game.talk(myTokens[1], myTokens[2]);
			}
			else if (myTokens[0].equals("#INV_START"))
			{
				Game.StockMan.InventoryStart(myTokens[1]);
			}
			else if (myTokens[0].equals("#INV_END"))
			{
				Game.StockMan.InventoryEnd();
			}
			else if (myTokens[0].equals("#INV_ITEM"))
			{
				Game.StockMan.InventoryAddItem(myTokens[1], myTokens[2], myTokens[3], myTokens[4]);
			}
			else if (myTokens[0].equals("#newspaper"))
            {
                if(myTokens[1].equals("date"))
                {
                    Game.Newspaper.addDate(myTokens[2], myTokens[3], myTokens[4]);
                    Game.Newspaper.newsStart();
                }
                else if(myTokens[1].equals("article"))
                {
                    if(myTokens.length==9)
                        Game.Newspaper.addNewsItem(myTokens[2], myTokens[3], myTokens[4], myTokens[5], myTokens[6], myTokens[7], myTokens[8]);
                }
                else if(myTokens[1].equals("ad"))
                {

                }
                else if(myTokens[1].equals("end"))
                {
                    Game.Newspaper.newsShow(null);
                }
            }
			else if (myTokens[0].equals("#APPLICANTS"))
			{
				Game.HireFire.ApplicantStart(myTokens[1]);
			}
			else if (myTokens[0].equals("#END_APPLICANTS"))
			{
				Game.HireFire.ApplicantEnd(myTokens[1]);
			}
			else if (myTokens[0].equals("#APPLICANTS_TEXT"))
			{
				Game.HireFire.AddApplicant(myTokens[1], myTokens[2], myTokens[3], myTokens[4], myTokens[5], myTokens[6]);
			}
			else if (myTokens[0].equals("#DIALOG"))
			{
				if (DBox != null)
				{
					DBox.clear(myTokens[2]);
				}
				else
				{
					DBox = new DialogBox(myTokens[2], Moo);
				}
				DBox.setVisible(true);
			}
			else if (myTokens[0].equals("#DIALOG_PHRASE"))
			{
				DBox.addPhrase(myTokens[2]);
			}
			else if (myTokens[0].equals("#END_DIALOG"))
			{
				DBox.endDialog();
			}
			else if (myTokens[0].equals("#SUPPLIER_LIST"))
			{
				if (VBox != null)
				{
					VBox.clear();
				}
				else
				{
					VBox = new VenderDialog(Moo);
				}
				VBox.setVisible(true);
			}
			else if (myTokens[0].equals("#SUPPLIER"))
			{
				VBox.addVender(myTokens[1], myTokens[2]);
			}
			else if (myTokens[0].equals("#END_SUPPLIER_LIST"))
			{
				VBox.endList();
			}
			/*else if (myTokens[0].equals("#NOTE"))
			{
				NoteBox = new NotePad(myTokens[1], myTokens[2]);
				ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
				s.schedule(new Runnable() {
				    public void run() {
				    	NoteBox.setVisible(false); //should be invoked on the EDT
				    	NoteBox.dispose();
				    }
				}, 35, TimeUnit.SECONDS);
			}
			else if (myTokens[0].equals("#NOTE_TEXT"))
			{
				NoteBox.addLine(myTokens[1], myTokens[2]);
			}
			else if (myTokens[0].equals("#END_NOTE"))
			{
				NoteBox.endNotes(myTokens[1]);
			}*/
			
			else if(myTokens[0].equals("#PICTURE_NOTE")){
				
				picBox = new PicturePad(myTokens[1], myTokens[2]);
				ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
				s.schedule(new Runnable() {
				    public void run() {
				    	picBox.setVisible(false); //should be invoked on the EDT
				    	picBox.dispose();
				    }
				}, 35, TimeUnit.SECONDS);
			}
			
			else if(myTokens[0].equals("#PICTURE_NOTE_PICTURE")){
				picBox.addPictureName(myTokens[1]);
			}
			
			else if(myTokens[0].equals("#PICTURE_NOTE_TEXT")){
				picBox.addLine(myTokens[1], myTokens[2]);
			}
			
			else if(myTokens[0].equals("#PICTURE_NOTE_END")){
				
				picBox.endNotes(myTokens[1]);
			}
			else if (myTokens[0].equals("#PRICE_LIST"))
			{
				
				PriceBox = new OrderDialog(Moo, myTokens[1], myTokens[2]);
			}
			else if (myTokens[0].equals("#PRICING"))
			{
				PriceBox.addItem(myTokens[1], myTokens[2], myTokens[3], myTokens[4], myTokens[5], myTokens[6], myTokens[7], myTokens[8]);
			}
			else if (myTokens[0].equals("#PRICE_END"))
			{
				PriceBox.endList(myTokens[1]);
			}
			else if (myTokens[0].equals("#QUIZ_START"))
			{
				
				
				QMachine = new QuizMachine(myTokens[1], Moo);				
				QMachine.setPlayerID(myTokens[1]);
				QMachine.setAgent(myTokens[2]);
				QMachine.setVisible(true);
				
				ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
				s.schedule(new Runnable() {
				    public void run() {
				    	QMachine.setVisible(false); //should be invoked on the EDT
				    	QMachine.dispose();
				    }
				}, 35, TimeUnit.SECONDS);

			}
			else if (myTokens[0].equals("#QUIZ_QUESTION"))
			{
				//token 1 = date
				//token 2 = question
				//String date = myTokens[1].toString();
				String origin = myTokens[0].toString();
				String fiveW = myTokens[1].toString();
				//int quenum = (int)Math.random()*10;
				int quenum = 1;
				
				String origin1 = origin.replace("#QUIZ_QUESTION", "Question "+ quenum);
				//String fiveW1 = fiveW.replace("---???---", "What ");
				
				//QMachine.addQuestion( myTokens[0].toString()+": " +  myTokens[1].toString());
				QMachine.addQuestion( origin1 +": " + myTokens[1].toString());
				quenum ++;
			}
			else if (myTokens[0].equals("#QUIZ_ANSWER"))
			{
				//token 1 = answer
				QMachine.addAnswer(myTokens[1]);
			}
			else if (myTokens[0].equals("#QUIZ_CORRECT"))
			{
				QMachine.setCorrectAnswer(myTokens[1]);
			}
			else if(myTokens[0].equals("#QUIZ_TICKER"))
			{
				QMachine.setQuizTicker(myTokens[1]);
			}
			else if(myTokens[0].equals("#QUIZ_STORYID"))
			{
				QMachine.setStoryNumber(myTokens[1]);
				QMachine.setPersonOrPlace(myTokens[2]);
			}
			else if (myTokens[0].equals("#QUIZ_END"))
			{
				QMachine.setPlayerIDForQuizEnd(myTokens[1]);
				//QMachine.setMachineID(myTokens[2]);
				QMachine.endDialog();
			}

			else if (myTokens[0].equals("#LOGIN_SUCCESS"))
			{
				loginFrame.setLoginStatus(LoginFrame.LoginStatus.SUCCESS);
			}

			else if (myTokens[0].equals("#LOGIN_ERROR"))
			{
				loginFrame.setLoginStatus(LoginFrame.LoginStatus.FAILED);
			}

			else if (myTokens[0].equals("#CREATE_PLAYER_SUCCESS"))
			{

			}
			else if (myTokens[0].equals("#CREATE_PLAYER_FAILURE"))
			{

			}
			else if (myTokens[0].charAt(0) != '#')
			{
				Game.appendToInformationTextPane(line);
				System.out.println(line);
			}


		}

	}
}
