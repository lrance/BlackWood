package edu.nodak.ndsu.cs.lions.blackwood;

//Edited by Anthony Mitchell
//Spring 2012 CSCI 345

//import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrameDeprec;
import java.awt.event.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;

import java.lang.ClassLoader;


public class HelpWindow extends javax.swing.JFrame {
    int count = 0;
    int lines = 0;
    String location = "";
    String pictureName = "";
    String line = "";
    String lineRead = "";
    String result = "";
    URL picture = null;

//    public ImageIcon getPicture(String path)
//    {
//
//    }

    //Return step picture
    public ImageIcon stepPic(String path){
//        ImageIcon step = new ImageIcon(path);
//        return step;
        ClassLoader cldr = this.getClass().getClassLoader();
        
        java.net.URL imageURL = cldr.getResource(path);
        ImageIcon step = new ImageIcon(imageURL);
        
        return step;
    }

    //Return topic steps
    public String stepReturn(int topicNum, int subTopicNum, int stepNum) {

    	//Text for the left panel
    	
    	//"User Interface" 
        if(topicNum == 0 & subTopicNum == 0 & stepNum == 0){
        	result = "For more help, click on one of the\n" +
            		"subtopics below.";
        }
        
        //"Main Window"
        else if(topicNum == 0 & subTopicNum == 1 & stepNum == 0){
            result = "This is the graphical user interface for\n" +
            		"Blackwood.  ";
        }
        
      //"Advertising"
        else if(topicNum == 0 & subTopicNum == 2 & stepNum == 0){
            result = "By using the 'Advertise' button, you can gain\n" +
            		"customers to your store.  ";
        }
        
      //"Communication"
        else if(topicNum == 0 & subTopicNum == 3 & stepNum == 0){
            result = "This is the communication panel.  By typing in\n" +
            		"the text box, you can talk with other players.\n" +
            		"You can also see other activities like who has\n" +
            		"entered and exited a room by looking at the\n" +
            		"larger text box.";
        }
        
      //"Hire/Fire"
        else if(topicNum == 0 & subTopicNum == 4 & stepNum == 0){
            result = "Using this option, you can hire employees\n" +
            		"and fire them.";
        }
        
      //"Ledger"
        else if(topicNum == 0 & subTopicNum == 5 & stepNum == 0){
            result = "Use the ledger option to view your cash,\n" +
            		"net worth, debt, and profit.";
        }
        
      //"Ordering"
        else if(topicNum == 0 & subTopicNum == 6 & stepNum == 0){
            result = "Page 1 of 4\n\n" +
            		"Use the order button to purchase supplies\n" +
            		"for your store.";
            count = 000601;
        }
        
        //"Ordering" part 2
        else if(topicNum == 0 & subTopicNum == 6 & stepNum == 1){
            result = "Page 2 of 4\n\n" +
            		"Select the vender from the list and click\n" +
            		"\"View Selected Vender's Catalog\".";
            count = 000602;
        }
        
      //"Ordering" part 3
        else if(topicNum == 0 & subTopicNum == 6 & stepNum == 2){
            result = "Page 3 of 4\n\n" +
            		"Select the item you wish to purchase and\n" +
            		"enter the quantity, then click \"Add item to\n" +
            		"order\".";
            count = 000603;
        }
        
      //"Ordering" part 4
        else if(topicNum == 0 & subTopicNum == 6 & stepNum == 3){
            result = "Page 4 of 4\n\n" +
            		"Here you can review the order and to finish\n" +
            		"the order, click \"Place Order\".";
            count = 000604;
        }
        
      //"Stock"
        else if(topicNum == 0 & subTopicNum == 7 & stepNum == 0){
            result = "The stock option allows you to check the amount\n" +
            		"of items in your store.  ";
        }
        
      //"News"
        else if(topicNum == 0 & subTopicNum == 8 & stepNum == 0){
            result = "Page 1 of 2\n\n" +
            		"Using the \"News\" option, you can view\n" +
            		"information on current events in Blackwood.";
            count = 000606;
        }
        
      //"News" part 2
        else if(topicNum == 0 & subTopicNum == 8 & stepNum == 1){
            result = "Page 2 of 2\n\n" +
            		"Using the tabs at the bottom you can view\n" +
            		"different sections of news.";
            count = 000607;
        }
        
      //"Scores"
        else if(topicNum == 0 & subTopicNum == 9 & stepNum == 0){
            result = "Using the \"Scores\" option allows you to\n" +
            		"see the code being processed and what is\n" +
            		"returned inside the MUD.  ";
        }
        
      //"Options"
        else if(topicNum == 0 & subTopicNum == 10 & stepNum == 0){
            result = "This feature is not yet implemented.  ";
        }
        
      //"Compass"
        else if(topicNum == 0 & subTopicNum == 11 & stepNum == 0){
            result = "See the \"Compass\" section under \"City\n" +
            		"Navigation\" for more help.  ";
        }
        
      //"Map"
        else if(topicNum == 0 & subTopicNum == 12 & stepNum == 0){
            result = "See the \"Map\" section under \"City\n" +
            		"Navigation\" for more help.  ";
        }
        
      //"Game Time"
        else if(topicNum == 0 & subTopicNum == 13 & stepNum == 0){
            result = "The \"Game Time\" clock shows what time it\n" +
            		"is inside the Blackwood world.  ";
        }
        

        //"City Navigation"
        if(topicNum == 1 & subTopicNum == 0 & stepNum == 0){
            result = "This topic is about the various areas around\n" +
                    "and in Blackwood and the ways you can move\n" +
                    "around. Please select one of the subtopics below\n" +
                    "for more help.";
        }
        
      //"Compass"
        if(topicNum == 1 & subTopicNum == 1 & stepNum == 0){
            result = "By using the compass tool, you can navigate\n" +
            		"your character through the Blackwood world.\n" +
            		"There are two ways you can move your character\n" +
            		"from one area to one adjacent from you.  You\n" +
            		"can either click on the button that contains the\n" +
            		"name of the area you want to move to (ie. Park,\n" +
            		"Blacksmith, News Office, Stockade).  Or you can\n" +
            		"click on the compass points that are highlighted in\n" +
            		"red.  The points that are gray are directions that\n" +
            		"are not available in the area that you are in.";
        }
        
      //"Map"
        if(topicNum == 1 & subTopicNum == 2 & stepNum == 0){
            result = "This feature is not yet implemented.";
        }
        
        //"Districts" part 1
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 0){
            result = "Page 1 of 7\n\n" +
            		"There are many areas in Blackwood with many\n" +
                    "districts inside those areas.";
            count = 010301;
        }
        
        //"Districts" part 2
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 1){
            result = "Page 2 of 7\n\n" +
            		"In the center of Blackwood is the Town Square and\n" +
            		"Town Square Park. Directly North of the Town\n" +
            		"Square is the Old Business District and Middle\n" +
            		"Class Neighborhood. Directly south of the Town\n" +
            		"Square is the Government-Financial Districts and\n" +
            		"the Wealthy Neighborhood.";
            count = 010302;
        }
        
        //"Districts" part 3
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 2){
            result = "Page 3 of 7\n\n"
                        +"East-northeast of the center of town is the Railroad\n" +
                        "Depot, Riverside Neighborhood, and the Riverboat\n" +
                        "Landing.";
            count = 010303;
        }
        
        //"Districts" part 4
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 3){
            result = "Page 4 of 7\n\n"
                        +"Southeast of the center of town is mostly dense\n" +
                        "forest area where lumber country is.";
            count = 010304;
        }
        
        //"Districts" part 5
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 4){
            result = "Page 5 of 7\n\n"
                        +"Northwest of the center of town is the Wagon Train\n"
			+"Staging Area, and Newer Business District.";
            count = 010305;
        }
        
        //"Districts" part 6
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 5){
            result = "Page 6 of 7\n\n"
                        +"Directly west of the center of town is the\n" +
                        "Slaughterhouse District, North Shanty Town, South\n" +
                        "Shanty Town, and Cattle Pens.";
            count = 010306;
        }
        
        //"Districts" part 7
        else if(topicNum == 1 & subTopicNum == 3 & stepNum == 6){
            result = "Page 7 of 7\n\n"
                        +"Southwest of the center of town is mostly open\n" +
                        "range where the ranch country is.";
            count = 010307;
        }
        
        //"Town Sections" part 1
        else if(topicNum == 1 & subTopicNum == 4 & stepNum == 0){
            result = "Page 1 of 5\n\n"
                        +"There are 4 main areas in Blackwood with specific\n"
			+"productions. The productions are farming, ranch,\n"
			+"lumber, and hill countries.";
            count = 010401;
        }
        
        //"Town Sections" part 2
        else if(topicNum == 1 & subTopicNum == 4 & stepNum == 1){
            result = "Page 2 of 5\n\n"
                        +"The farm country is northwest of Blackwood,\nlocated"
			+" in between the Black River and Western\nTrail.";
            count = 010402;
        }
        
        //"Town Sections" part 3
        else if(topicNum == 1 & subTopicNum == 4 & stepNum == 2){
            result = "Page 3 of 5\n\n"
                        +"Ranch country is southwest of Blackwood, located\n"
			+"between the Black River and Wester Trail. This\narea is"
			+"used for cattle ranching. The western\noutpost is also"
			+"located in this area.";
            count = 010403;
        }
        
        //"Town Sections" part 4
        else if(topicNum == 1 & subTopicNum == 4 & stepNum == 3){
            result = "Page 4 of 5\n\n"
                        +"Lumber country is located southeast of Blackwood\non the"
			+"other side of the Black River and south of the\nWood"
			+"Creek river.";
            count = 010404;
        }
        
        //"Town Sections" part 5
        else if(topicNum == 1 & subTopicNum == 4 & stepNum == 4){
            result = "Page 5 of 5\n\n"
                        +"Hill Country is located northeast of Blackwood on\nthe"
			+"other side of the Black River and north of the\nWood "
			+"Creek river. The Fort Wood Trading Post is\nlocated in"
			+"this area.";
            count = 010405;
        }

        //"Blackwood"
        if(topicNum == 2 & subTopicNum == 0 & stepNum == 0){
            result = "This topic will go over the history and geography of\n"
                    +"Blackwood. Select one of the subtopics below for\nmore help.";
        }
        
        //"Geography" part 1
        else if(topicNum == 2 & subTopicNum == 1 & stepNum == 0){
            result = "Page 1 of 2\n\n" +
            		"The mythical town of Blackwood is situated\n" +
            		"somewhere in the American West. It's location\n" +
            		"is not precisely known, but it lies somewhere in\n" +
            		"the Great Plains region west of the Mississippi\n" +
            		"River, south of the Canadian border, east of the\n" +
            		"Rocky Mountains, and north of the Mason-Dixon\n" +
            		"line; i.e. somewhere in the Montana, Colorado,\n" +
            		"Dakota, Wyoming vicinity (see the Maps of\n" +
            		"Blackwood).";
            count = 020101;
        }
        
        //"Geography" part 2
        else if(topicNum == 2 & subTopicNum == 1 & stepNum == 1){
            result = "Page 2 of 2\n\n" +
            		"Blackwood is uniquely situated in a convergence\n" +
            		"of geological and historical influences. The\n" +
            		"Black river flows northward and meets Wood\n" +
            		"Creek just to the north of town, and glacial\n" +
            		"movement in the Ice Ages has left rich soil\n" +
            		"for farming to the north and west. The same\n" +
            		"glacial activity created a hilly moraine region\n" +
            		"to the northeast, and there is some evidence of\n" +
            		"mineral riches, particularly native silver, in\n" +
            		"the hills in that direction. To the south of\n" +
            		"town, and east of the Black river, there is a\n" +
            		"dense mixed forest that provides much of the\n" +
            		"lumber used for building in the area.\n" +
            		"Meanwhile, to the south and west the land is\n" +
            		"flat and rocky - well-suited for cattle\n" +
            		"ranching. ";
            count = 020102;
        }
        
        //"History" part 1
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 0){
            result = "Page 1 of 6\n\n" +
            		"The first settlers in the Blackwood region were\n" +
            		"pioneers who either came across country,\n" +
            		"following oxcart trails, and crossed the\n" +
            		"Black River at the Blackwood ford, or who\n" +
            		"floated up the Black River on rafts or in\n" +
            		"small boats to the point, at the Blackwood\n" +
            		"ford, where it becomes rocky, shallow, and\n" +
            		"impassible. The first homes and businesses\n" +
            		"were built near the ford and the first\n" +
            		"settlers started farms in the rich soil\n" +
            		"northwest of there.";
            count = 020201;
        }
        
        //"History" part 2
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 1){
            result = "Page 2 of 6\n\n" +
            		"Soon river boats were reaching the town of\n" +
            		"Blackwood, bringing more settlers; some who\n" +
            		"stayed in the region, and some heading further\n" +
            		"west seeking opportunities in the American\n" +
            		"expansion of the mid-1800s. The river boats\n" +
            		"stopped at Blackwood, delivered supplies and\n" +
            		"travelers, and returned back upstream with mail\n" +
            		"and raw materials such as lumber and farm\n" +
            		"products.";
            count = 020202;
        }
        
        //"History" part 3
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 2){
            result = "Page 3 of 6\n\n" +
            		"At about this time, the U.S. army decided to\n" +
            		"send a cavalry detachment to the region. Fort\n" +
            		"Wood was built, north of Blackwood, near the\n" +
            		"site of an old trading post, and a small community\n" +
            		"formed around the fort. The Fort Wood area\n" +
            		"was mostly populated by Army hangers-on, family\n" +
            		"members, and other federal government\n" +
            		"employees.";
            count = 020203;
        }
        
      //"History" part 4
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 3){
            result = "Page 4 of 6\n\n" +
            		"By 1880, Blackwood had a population of 2,500,\n" +
            		"but the Northwestern Railroad was in the process\n" +
            		"of building a line across the region and planned\n" +
            		"to cross the Black River at the Blackwood ford,\n" +
            		"very near town. By the Spring of that year, a\n" +
            		"lumber town had sprung up in advance of the\n" +
            		"railroads farthest progress, and the arrival\n" +
            		"of the railroad promises to bring an even greater\n" +
            		"influx of people to the greater Blackwood area.";
            count = 020204;
        }
        
        //"History" part 5
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 4){
            result = "Page 5 of 6\n\n" +
            		"When the railroad arrives in 1881, the population\n" +
            		"of Blackwood, which had been steadily growing,\n" +
            		"explodes with railroad workers and passengers\n" +
            		"from the east. Then, when silver is discovered\n" +
            		"in the area in 1882, another large increase in\n" +
            		"population occurs. However, the North West\n" +
            		"Railroad encounters financing difficulties and\n" +
            		"westward construction is halted for five years.\n" +
            		"Nonetheless, the population of Blackwood\n" +
            		"continues to grow by 50% a year as more and\n" +
            		"more families settle in the area and business\n" +
            		"continues to expand with the influx of westward\n" +
            		"travelers, small land owners, and miners. The\n" +
            		"population peaks at 25,000 in 1884, but then\n" +
            		"the silver mines are exhausted and the crest\n" +
            		"of the boom has ended.";
            count = 020205;
        }
        
      //"History" part 6
        else if(topicNum == 2 & subTopicNum == 2 & stepNum == 5){
            result = "Page 6 of 6\n\n" +
            		"The final chapter of Blackwood development\n" +
            		"occurs in 1886, the year of the \"Great White Ruin\",\n" +
            		"a terrible winter that left 300 people dead on\n" +
            		"the Great Plains (along with thousands of cattle).\n" +
            		"The combination of a heavy winter snow and an\n" +
            		"extremely wet spring causes the Black River to\n" +
            		"slowly overflow its banks and inundate the town.\n" +
            		"Virtually every home and business is damaged by\n" +
            		"the flood, and few of the townsfolk remain to\n" +
            		"rebuild. The call of the west is strong, and\n" +
            		"most people pack up and venture into the new\n" +
            		"frontier. By the fall of 1886 Blackwood is once\n" +
            		"again a small and remote town; a mere whistle\n" +
            		"stop on the rail line heading west.";
            count = 020206;
        }
        
        
        //"Video"
        else if(topicNum == 2 & subTopicNum == 3 & stepNum == 0){
            result = "";
            
            URL myUrl = null;
            
              	
        	//File myFile = new File("C:\Users\Tony\workspace\Blackwood Client\src\edu\nodak\ndsu\cs\lions\blackwood\Resources\Image\HelpWindow\Trailer.MOV");
            File myFile = new File("Trailer.MOV");
            try
            {
         	   myUrl = myFile.getAbsoluteFile().toURI().toURL();
            	//myUrl  = new URL("https://lions.cs.ndsu.nodak.edu/svn/blackwood/branches/CS345_Spring2012/src/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/Trailer.MOV");
            } 
            catch ( MalformedURLException malformedURLException )
            {
         	   
            }

            //result = myUrl.toString();
            
           JFrame mediaTest = new JFrame( "Blackwood Introduction" );
           //mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
           //mediaTest.setDefaultCloseOperation(mediaTest.EXIT_ON_CLOSE);
           mediaTest.setDefaultCloseOperation(EXIT_ON_CLOSE);
           
           MediaPanel mediaPanel = new MediaPanel( myUrl );
           mediaTest.add( mediaPanel );
           
           mediaTest.setSize( 470, 360 );
           mediaTest.setVisible( true );
           
           
           
           
            
        }
        
        return result;
    }

    
    //This is a listener for topicList (JList) to read the text from a TXT file
    //I wasn't able get this code to work to read a specific step
//    public class helpListener implements MouseListener
//	{
//	  public void mouseClicked (MouseEvent me) 
//	  {
//	  textArea.setText("");
//	  URL file = getURL("text/" + help.getSelectedValue().toString()+".txt");
//		  try {
//			  InputStream is = file.openStream();
//			  BufferedReader in = new BufferedReader(new InputStreamReader(is));
//			  String str;
//			  while ((str = in.readLine()) != null) {
//				  textArea.append(str+"\n");
//			  }
//			  in.close();
//		  } catch (IOException e) {
//			  e.printStackTrace();
//		  }
//	  }
//	  public void mouseEntered (MouseEvent me) {}
//	  public void mousePressed (MouseEvent me) {}
//	  public void mouseReleased (MouseEvent me) {}
//	  public void mouseExited (MouseEvent me) {}
//	}
    
    //Default text onLoad
    public HelpWindow() {
        initComponents();
        
        stepText.setText("Welcome to Blackwood Help\nPlease selecton one of the topics"
            +"\n\nAlso, check out the Blackwood Trailer on YouTube"
            +"\nhttp://www.youtube.com/watch?v=n_dsw_FW_00"
            +"\nand\nhttp://www.youtube.com/user/blackwoodWoWiWe");

    //Initializing the selected subtopic from the topicList (JList)
    topicList.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting())
                    return;
                if(topicList.getSelectedIndex() == 0){
                    stepText.setText(stepReturn(0,0,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                
                //"Main Window" Picture
                else if((topicList.getSelectedIndex() == 1)){
                    stepText.setText(stepReturn(0,1,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/Map.png"));
                }
                
              //"Advertise" Picture
                else if((topicList.getSelectedIndex() == 2)){
                    stepText.setText(stepReturn(0,2,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/Advertise.png"));
                }
                
              //"Communication" Picture
                else if((topicList.getSelectedIndex() == 3)){
                    stepText.setText(stepReturn(0,3,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/communication.png"));
                }
                
              //"Hire/Fire" Picture
                else if((topicList.getSelectedIndex() == 4)){
                    stepText.setText(stepReturn(0,4,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/hirefire.png"));
                }
                
              //"Ledger" Picture
                else if((topicList.getSelectedIndex() == 5)){
                    stepText.setText(stepReturn(0,5,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/ledger.png"));
                }
                
              //"Ordering" Picture
                else if((topicList.getSelectedIndex() == 6)){
                    stepText.setText(stepReturn(0,6,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order.png"));
                }
                
              //"Stock" Picture
                else if((topicList.getSelectedIndex() == 7)){
                    stepText.setText(stepReturn(0,7,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/stock.png"));
                }
                
              //"News" Picture
                else if((topicList.getSelectedIndex() == 8)){
                    stepText.setText(stepReturn(0,8,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/news.png"));
                }
                
              //"Scores" Picture
                else if((topicList.getSelectedIndex() == 9)){
                    stepText.setText(stepReturn(0,9,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/score.png"));
                }
                
              //"Options" Picture
                else if((topicList.getSelectedIndex() == 10)){
                    stepText.setText(stepReturn(0,10,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/options.png"));
                }
                
              //"Compass" Picture
                else if((topicList.getSelectedIndex() == 11)){
                    stepText.setText(stepReturn(0,11,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/compassbutton.png"));
                }
                
              //"Map" Picture
                else if((topicList.getSelectedIndex() == 12)){
                    stepText.setText(stepReturn(0,12,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/mapbutton.png"));
                }
                
              //"Game time" Picture
                else if((topicList.getSelectedIndex() == 13)){
                    stepText.setText(stepReturn(0,13,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/gametime.png"));
                }
                
                //"City Navigation" Picture
                else if(topicList.getSelectedIndex() == 14){
                    stepText.setText(stepReturn(1,0,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                
              //"Compass" Picture
                else if(topicList.getSelectedIndex() == 15){
                    stepText.setText(stepReturn(1,1,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/Compass.png"));
                }
                
              //"Map" Picture
                else if(topicList.getSelectedIndex() == 16){
                    stepText.setText(stepReturn(1,2,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/MapAreas.png"));
                }
                
                //"Districts" Picture
                else if(topicList.getSelectedIndex() == 17){
                    stepText.setText(stepReturn(1,3,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                
                //"Town sections" Picture
                else if(topicList.getSelectedIndex() == 18){
                    stepText.setText(stepReturn(1,4,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                
                //"Blackwood" Picture
                else if(topicList.getSelectedIndex() == 19){
                    stepText.setText(stepReturn(2,0,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                
                //"Geography" Picture
                else if(topicList.getSelectedIndex() == 20){
                    stepText.setText(stepReturn(2,1,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/geography.png"));
                }
                
                //"History" Picture
                else if(topicList.getSelectedIndex() == 21){
                    stepText.setText(stepReturn(2,2,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history.png"));
                }
                
                //"Video" Picture
                else if(topicList.getSelectedIndex() == 22){
                    stepText.setText(stepReturn(2,3,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
            }
        });

        //Add action listener to nextButton (JButton)
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                if(count == 010301){
                    stepText.setText(stepReturn(1,3,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010302){
                    stepText.setText(stepReturn(1,3,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010303){
                    stepText.setText(stepReturn(1,3,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010304){
                    stepText.setText(stepReturn(1,3,4));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010305){
                    stepText.setText(stepReturn(1,3,5));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010306){
                    stepText.setText(stepReturn(1,3,6));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010401){
                    stepText.setText(stepReturn(1,4,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010402){
                    stepText.setText(stepReturn(1,4,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010403){
                    stepText.setText(stepReturn(1,4,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010404){
                    stepText.setText(stepReturn(1,4,4));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 000601){
                    stepText.setText(stepReturn(0,6,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order2.png"));
                }
                else if(count == 000602){
                    stepText.setText(stepReturn(0,6,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order3.png"));
                }
                else if(count == 000603){
                    stepText.setText(stepReturn(0,6,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order4.png"));
                }
                else if(count == 000606){
                    stepText.setText(stepReturn(0,8,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/news2.png"));
                }


                if(count == 020101){
                    stepText.setText(stepReturn(2,1,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/geography.png"));
                }
     
                else if(count == 020201){
                    stepText.setText(stepReturn(2,2,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history2.png"));
                }
                else if(count == 020202){
                    stepText.setText(stepReturn(2,2,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history3.png"));
                }
                else if(count == 020203){
                    stepText.setText(stepReturn(2,2,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history4.png"));
                }
                else if(count == 020204){
                    stepText.setText(stepReturn(2,2,4));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history5.png"));
                }
                else if(count == 020205){
                    stepText.setText(stepReturn(2,2,5));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history6.png"));
                }
            }
        });

        //Add action listener to previousButton (JButton)
        previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                if(count == 010302){
                    stepText.setText(stepReturn(1,3,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010303){
                    stepText.setText(stepReturn(1,3,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010304){
                    stepText.setText(stepReturn(1,3,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010305){
                    stepText.setText(stepReturn(1,3,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010306){
                    stepText.setText(stepReturn(1,3,4));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010307){
                    stepText.setText(stepReturn(1,3,5));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010402){
                    stepText.setText(stepReturn(1,4,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010403){
                    stepText.setText(stepReturn(1,4,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010404){
                    stepText.setText(stepReturn(1,4,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 010405){
                    stepText.setText(stepReturn(1,4,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/bg.png"));
                }
                else if(count == 000602){
                    stepText.setText(stepReturn(0,6,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order.png"));
                }
                else if(count == 000603){
                    stepText.setText(stepReturn(0,6,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order2.png"));
                }
                else if(count == 000604){
                    stepText.setText(stepReturn(0,6,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/order3.png"));
                }
                else if(count == 000607){
                    stepText.setText(stepReturn(0,8,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/news.png"));
                }


                if(count == 020102){
                    stepText.setText(stepReturn(2,1,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/geography.png"));
                }
                else if(count == 020202){
                    stepText.setText(stepReturn(2,2,0));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history.png"));
                }
                else if(count == 020203){
                    stepText.setText(stepReturn(2,2,1));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history2.png"));
                }
                else if(count == 020204){
                    stepText.setText(stepReturn(2,2,2));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history3.png"));
                }
                else if(count == 020205){
                    stepText.setText(stepReturn(2,2,3));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history4.png"));
                }
                else if(count == 020206){
                    stepText.setText(stepReturn(2,2,4));
                    pictureLabel.setIcon(stepPic("edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/HelpWindow/history5.png"));
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picturePanel = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        topicScrollPane = new javax.swing.JScrollPane();
        topicList = new javax.swing.JList();
        previousButton = new java.awt.Button();
        nextButton = new java.awt.Button();
        textScrollPane = new javax.swing.JScrollPane();
        stepText = new javax.swing.JTextArea();
        
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Blackwood Help");
        setBackground(new java.awt.Color(251, 236, 175));
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.gif"));    
        
        picturePanel.setBackground(new java.awt.Color(251, 236, 175));
        picturePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        picturePanel.setPreferredSize(new java.awt.Dimension(220, 260));

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        topicList.setBackground(new java.awt.Color(251, 236, 175));
        topicList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "User Interface", "      Main Window", "      Advertising", "      Communication", "      Hiring/Firing", "      Ledger", "      Ordering", "      Stock", "      News", "      Scores", "      Options", "      Compass", "      Map", "      Game Time", "City Navigation", "      Compass", "      Map", "      Districts", "      Town Sections", "Blackwood", "      Geography", "      History", "      Video" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        topicScrollPane.setViewportView(topicList);

        previousButton.setActionCommand("button1");
        previousButton.setFont(new java.awt.Font("Dialog", 0, 10));
        previousButton.setLabel("Previous");

        nextButton.setLabel("Next");

        stepText.setBackground(new java.awt.Color(251, 236, 175));
        stepText.setColumns(20);
        stepText.setEditable(false);
        stepText.setRows(5);
        textScrollPane.setViewportView(stepText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topicScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topicScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(textScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button nextButton;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JPanel picturePanel;
    private java.awt.Button previousButton;
    private javax.swing.JTextArea stepText;
    private javax.swing.JScrollPane textScrollPane;
    private javax.swing.JList topicList;
    private javax.swing.JScrollPane topicScrollPane;
    // End of variables declaration//GEN-END:variables
    
    
}
