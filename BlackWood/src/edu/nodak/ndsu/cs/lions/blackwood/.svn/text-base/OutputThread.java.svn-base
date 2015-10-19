package edu.nodak.ndsu.cs.lions.blackwood;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 */

import java.net.*;
import java.io.*;


/** The OutputThread handles queuing and sending output to the server.
 *
 */

class OutputThread extends Thread implements Runnable
{
	private Socket Output1;
	private PrintWriter out = null;


	public OutputThread(Socket Output)
	{

		Output1 = Output;
		try
		{
			out = new PrintWriter(Output1.getOutputStream(), true);
		}catch (IOException e)
		{}

	}

	public void run()
	{

	}

	public void send(String command)
	{ //command is constructed by the caller...
		out.println(command);//sending out to the server
	}


}//end of class OutputThread