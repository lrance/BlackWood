package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import java.awt.*;
import javax.swing.*;
/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 */

public class GameClock extends JPanel {

  private int curT=100;
  private int maxT=100;

  public GameClock() {
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Dimension d=getSize();
    int ClockHand;
    int border = 00;
    int w = d.width-border;
    int h = d.height-border;
    int x = border/2;
    int y = x;
    g.setColor(Color.gray);
    g.fillOval(x,y, w, h);
    g.setColor(Color.lightGray);
    g.fillOval(x+1,y+1, w-2, h-2);
    ClockHand=((360*curT)/maxT);
    g.setColor(Color.black);
    g.fillArc(x,y, w, h, (450-ClockHand), ClockHand);
  }

  void setTime(int currtime, int maxtime){
    curT = currtime;
    maxT = maxtime;
    System.out.println(curT + " " + maxT);
    this.setBackground(this.getParent().getBackground());
    this.setToolTipText(((curT * 100)/ maxT) + "% completed.");
    repaint();
  }
}