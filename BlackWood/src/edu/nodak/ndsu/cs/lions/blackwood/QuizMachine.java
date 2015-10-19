package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Vector;

import com.borland.jbcl.layout.*;

import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class QuizMachine extends JDialog {
  private JPanel questionPanel = new JPanel();
  private JList answerList = new JList();
  private JTextArea question = new JTextArea();
  //private JTextArea agents = new JTextArea();
  private JPanel responsePanel = new JPanel();
  private JButton answer = new JButton();
  private JButton cancel = new JButton();
  private XYLayout xYLayout1 = new XYLayout();
  private Vector<String> answerListStrings = new Vector<String>();
  //temporary variables till multiple agents are supported
  private String agentNumber;
  private String playerID;
  private String correctAnswer;
  private String quizTicker;
  private String storyNumber;
  private String personOrPlace;
  private String playerIDForQuizEnd;
  private String machineID;
  private static String title;

public String getPlayerIDForQuizEnd() {
	return playerIDForQuizEnd;
}

public void setPlayerIDForQuizEnd(String playerIDForQuizEnd) {
	this.playerIDForQuizEnd = playerIDForQuizEnd;
}

public String getMachineID() {
	return machineID;
}

public void setMachineID(String machineID) {
	this.machineID = machineID;
}

public String getStoryNumber() {
	return storyNumber;
}

public void setStoryNumber(String storyNumber) {
	this.storyNumber = storyNumber;
}

public String getPersonOrPlace() {
	return personOrPlace;
}

public void setPersonOrPlace(String personOrPlace) {
	this.personOrPlace = personOrPlace;
}

public String getQuizTicker() {
	return quizTicker;
}

public void setQuizTicker(String quizTicker) {
	this.quizTicker = quizTicker;
}

public String getCorrectAnswer() 
  {
	return correctAnswer;
}

public void setCorrectAnswer(String correctAnswer) 
{
	this.correctAnswer = correctAnswer;
}

private MooConnection Moo;

  public QuizMachine(Frame frame, String title1, boolean modal) {
   
    super(frame, title1, modal);
    
    //System.out.println("show the title1: " + title1.toString());
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public QuizMachine(String question, MooConnection moo) {
    this(null, getNewNameTitle(), false);
    Moo = moo;
    //jLabel_Description.setText(descript+", who can understand the following:");
  }
  
  public QuizMachine(String title) {
	   this(null, title.toString(), false);
	   System.out.println("show the title: " + title.toString());
	  }
  
  
  private void jbInit() throws Exception {
   
	//System.out.println("wocaonima: " + getNewNameTitle());
	questionPanel.setLayout(xYLayout1);
	questionPanel.setPreferredSize(new Dimension(570, 300));
	
	answerList.setBorder(BorderFactory.createLineBorder(Color.black));
	question.setToolTipText("Question from the new agent");
	
	question.setBackground(new Color(251, 236, 175));
    answer.setText("Answer");
    question.setLineWrap(true);
    answer.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        answer_actionPerformed(e);
      }
    });
    cancel.setEnabled(true);
    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancel_actionPerformed(e);
      }
    });
    questionPanel.setBackground(new Color(251, 236, 175));
    responsePanel.setBackground(new Color(251, 236, 175));
    answerList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        answerList_mouseClicked(e);
      }
    });
    answerList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        answerList_valueChanged(e);
      }
    });
    
    getContentPane().add(questionPanel);
    questionPanel.add(question, new XYConstraints(100, 0, 350, 50));
    questionPanel.add(responsePanel,  new XYConstraints(0, 263, 450, -1));
    
    responsePanel.add(answer, null);
    responsePanel.add(cancel, null);
    questionPanel.add(answerList,  new XYConstraints(100, 50, 450, 100));
   
    
    
    this.setDefaultCloseOperation(
    	    JDialog.DO_NOTHING_ON_CLOSE);
  }

  public void setNewNameTitle(String title){
	  this.title = title;
  }
  
  public static String getNewNameTitle(){
	  return title;
	  
  }
  public void addQuestion(String q)
  {
	  question.setText(q);
  }
  
  public void addAnswer(String answer){
	 
      
	  answerListStrings.addElement(answer);
  }

  public void endDialog(){
	  answerList.setListData(answerListStrings);
  }

  private void answerQuestion(){
	int indx = answerList.getLeadSelectionIndex();
	String phrase = answerListStrings.elementAt(indx).toString();
    
    Moo.send("say ");
  }

  public void clear(){
	  answerListStrings.clear();
	  question.setText("");
	  this.setVisible(false);
  }

  void answer_actionPerformed(ActionEvent e) {
	  sendQuestion();
  }

  void answerList_mouseClicked(MouseEvent e) {
	  if ( e.getClickCount() == 2 && answerList.getLeadSelectionIndex() > -1 && 
			  answerListStrings.elementAt(0).toString() != "Nothing" ){
		  sendQuestion();
	  }
  }

  void cancel_actionPerformed(ActionEvent e) {
	  clear();
	  this.dispose();
  }
  
  public void setAgent(String a)
  {
	  agentNumber = a;
  }
  
  public void setPlayerID(String ID)
  {
	  playerID = ID;
  }
  
  private void sendQuestion()
  {
	  int indx = answerList.getLeadSelectionIndex();
	  String phrase = answerListStrings.elementAt(indx).toString();
	  //Moo.send("quiz_response " + agentNumber + " " + playerID + " " + phrase);
	  String response = String.format("return_value %s with {%s,%s,%s,%s,%s}",agentNumber,indx+1,correctAnswer,quizTicker,storyNumber,personOrPlace);
	  Moo.send(response);
	  clear();
	  
  }

  void answerList_valueChanged(ListSelectionEvent e) {
 
  }
}
