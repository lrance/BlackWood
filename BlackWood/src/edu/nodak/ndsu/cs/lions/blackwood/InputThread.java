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
import javax.swing.JOptionPane;

class InputThread extends Thread implements Runnable {

  private Socket input1;
  private BufferedReader in = null;
  public boolean stopped = false;

  MooConnection Dad;

  public InputThread(Socket input,MooConnection dad){
    Dad = dad;
    input1 = input;
  }

  public void run(){

    try{
      in = new BufferedReader(new InputStreamReader(input1.getInputStream()));
      while(true) {
        Dad.nextLine( in.readLine() );
        if (stopped == true) break;
      }
    }
    catch (IOException e){JOptionPane.showMessageDialog(null,"IO Error in inputThread: "+e);}
    catch (Exception e) {JOptionPane.showMessageDialog(null,"Error in inputThread"+e);}
  }

  public void KillThread(){
    stopped = true;
  }

}