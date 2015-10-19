package edu.nodak.ndsu.cs.lions.blackwood;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 * 
 * Interface for objects which wish to be notified as each new line of
 * text arrives from the server.
 */

public interface MooListener {

  /** Notification that a new line of text has arrived from the server.
   * @param line The newly arrived text.
   */
  public abstract void newLine(String line);

}