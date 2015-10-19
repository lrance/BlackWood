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

import javax.swing.JOptionPane;

import java.io.*;
import java.util.Vector;

public class MooConnection {

  public Socket GameSocket = null;
  public OutputThread out = null;
  public InputThread in = null;
  public String email;
  public String username;
  
  Vector registry = new Vector();

  public MooConnection (){

  }

  /**
   * Opens a connection to the Moo server for both input and output
   * @param password
   * @param username
   * @throws Exception
   */
  public void Connect(String host, int port, String username, String password) throws Exception {

    try {
            GameSocket = new Socket(host, port);
    } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,"Don't know about host:  " + host);
    } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Couldn't get I/O for the connection to: lambdaMoo.");
    } catch (Exception e) {e.printStackTrace(); throw e;}

    in = new InputThread(GameSocket,this);
    in.start();

    out = new OutputThread(GameSocket);
    out.send("connect "+username+" "+password+"\n");
  }

  public void Connect(String host, int port)
	{
		try
		{
			GameSocket = new Socket(host, port);
		}
		catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(null,"Don't know about host:  " + host);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,"Couldn't get I/O for the connection to: lambdaMoo.");
	    }
		catch (Exception e)
		{
			e.printStackTrace();
		}

		in = new InputThread(GameSocket,this);
		in.start();

		out = new OutputThread(GameSocket);
	}

  /**
   * Register a MooListener
   * @param listener
   */
  public void addListener(MooListener listener){
    if ( listener != null )
    {
      System.out.println("Adding Listener #"+(registry.size()+1));
      registry.addElement(listener);
    }
  }

  /**
   * This function is invoked by InputThread whenever it receives a new line of
   * text data from the Moo.  It notifies all registered MooListeners of the
   * newline data.
   * @param newLine
   */
  public void nextLine(String newLine) {
    // this function can be called from a seperate thread so
    // we must make sure it does attempt to execute in the middle of
    // adding a listener.
    int j=-1;
    int numToNotify = registry.size();
    try{
      for (j = 0; j < numToNotify && newLine != null; j++ ){
          ((MooListener)registry.elementAt(j)).newLine(newLine);
      }
    }
    catch ( Exception e){
      System.out.println("nextLine error in MooConnection class: j="+j+", "+e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Simply relay txtData to the Moo OutputThread
   * @param txtData - data to be relayed
   */
  public void send(String txtData){
    System.out.println("sending: "+txtData);
    out.send(txtData);
  }
  
  public String getPassword(String email, String username){
	  this.email = email;
	  this.username = username;
	  
	  return email + username;
  }

}