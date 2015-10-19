package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.AbstractAction;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;
import java.awt.*;
import javax.swing.*;
public class Newspaper extends javax.swing.JFrame {

    private JLabel photographLabel = new JLabel();
    private JEditorPane theLabel = new JEditorPane();
    //private JLabel theLabel = new JLabel();

    private JToolBar buttonBar = new JToolBar();
    
    private String imagedir = "Resources/Image/newspaper/";
    private String pageNews;
    public String loginInfo = LoginFrame.userNameTextField.getText();
    
    private MooConnection Moo;
    private Vector newsList = new Vector();
    private String newspaperDate;

    private MissingIcon placeholderIcon = new MissingIcon();

    /**
     * List of all the descriptions of the image files. These correspond one to
     * one with the image file names
     */
    private String[] imageCaptions = { "Politics", "Business", "Sports", "Arts and Entertainment", "Classifieds", "US/National",
                                        "Science and Technology" };

    /**
     * List of all the image files to load.
     */
    private String[] imageFileNames = { "politics.gif", "business.gif", "sports.gif", "artsAndEntertainment.gif", "classifieds.gif", "national.gif",
                                        "scienceAndTechnology.gif" };

    
    /**
     * Default constructor for the demo.
     */

    public Newspaper(MooConnection moo) throws Exception
    {
        this();
        this.setVisible(true);
        Moo = moo;
        Moo.send("testnews");
        //use Moo.send(String); to talk to moo
        //SwingUtilities.invokeLater(new Runnable() {
            //public void run() {
                //Newspaper app = new Newspaper();
                //app.setVisible(true);
            //}
        //});
    }

    public void newsStart(){
        newsList.removeAllElements();

        //Only for testing purposes
        newsList.addElement(new newsItem("Recent Blackwood Logins", "", "", "", "", loginInfo, "Author: Isaac Stoll"));
        
    }

    public void addDate(String year, String month, String day)
    {
        Date date = new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(day));
        Format formatter;
        formatter = new SimpleDateFormat("E, dd MMM yyyy");
        String paperDate=formatter.format(date);
        newspaperDate = "<p align = \"CENTER\"><font size =\"6\"><u>|   The Blackwood Times : Weekly Newspaper   |   " + paperDate + "   |</u></font></p><br>";
    }

    public void addNewsItem(String headline, String story, String primary, String secondary, String tertiary, String people, String location){
    // add items to the news list
        newsList.addElement(new newsItem(headline, story, primary, secondary, tertiary, people, location));
    }

    public void newsShow(String page){
    // End of news. Display to user as per required tab. if not, display all stories.
       pageNews = newspaperDate + "<br>";
       if(newsList.size()!=0)
       {
           if(page == "Politics")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Government")
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else if(page == "Business")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Economics")
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else if(page == "Sports")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Entertainment" && news.getSecondary()=="Sports")
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else if(page == "Arts and Entertainment")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Culture" || (news.getPrimary()=="Entertainment" && news.getSecondary()!="Sports"))
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else if(page == "Classifieds")
           {
               //for(int i=0; i<newsList.size();i++)
               //{
               //     newsItem news = ((newsItem)newsList.elementAt(i));
               //     pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               //}
           }
           else if(page == "US/National")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Birth" || news.getPrimary()=="Death" || news.getPrimary()=="Disaster" || news.getPrimary()=="Law" || news.getPrimary()=="Marriage" || news.getPrimary()=="Military" || news.getPrimary()=="Religion")
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else if(page == "Science and Technology")
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    if(news.getPrimary()=="Science")
                        pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
           else
           {
               for(int i=0; i<newsList.size();i++)
               {
                    newsItem news = ((newsItem)newsList.elementAt(i));
                    pageNews = pageNews+"<p><b><font size=\"6\">"+news.getHeadline()+"</font></b></p><p><i><font size=\"4\" color=\"grey\">"+news.getPeople()+"<br>"+news.getLocation()+"</font></i></p><br><p><font size=\"5\">"+news.getStory()+"</font></p><br><br>";
               }
           }
       }
       theLabel.setText(pageNews);
    }

    private class newsItem {

    private String Headline;
    private String Story;
    private String Primary;
    private String Secondary;
    private String Tertiary;
    private String People;
    private String Location;

    public newsItem(String headline, String story, String primary, String secondary, String tertiary, String people, String location){
      Headline = headline;
      Story = story;
      Primary = primary;
      Secondary = secondary;
      Tertiary = tertiary;
      People = people;
      Location = location;
    }

    public String getHeadline(){
      return Headline;
    }

    public String getStory(){
      return Story;
    }

    public String getPrimary(){
      return Primary;
    }

    public String getSecondary(){
      return Secondary;
    }

    public String getTertiary(){
      return Tertiary;
    }

    public String getPeople(){
      return People;
    }

    public String getLocation(){
      return Location;
    }

    }

    public Newspaper(){
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Newspaper");
        getContentPane().setBackground(new Color(251, 236, 175));

        // A label for displaying the pictures
        photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
        photographLabel.setHorizontalTextPosition(JLabel.CENTER);
        photographLabel.setHorizontalAlignment(JLabel.CENTER);
        photographLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // A label for Displaying the news
        //theLabel.setVerticalTextPosition(JLabel.BOTTOM);
        //theLabel.setHorizontalTextPosition(JLabel.CENTER);
        //theLabel.setHorizontalAlignment(JLabel.CENTER);
        theLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        theLabel.setContentType("text/html"); 
        theLabel.setEditable(false);


        JScrollPane scrollPane = new JScrollPane(theLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // We add two glue components. Later in process() we will add thumbnail buttons
        // to the toolbar inbetween thease glue compoents. This will center the
        // buttons in the toolbar.
        buttonBar.add(Box.createGlue());
        buttonBar.add(Box.createGlue());
        buttonBar.setBackground(new Color(251, 236, 175));
        //buttonBar.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(buttonBar, BorderLayout.SOUTH);
        add(photographLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        Icon header = createImageIcon("Resources/Image/newspaper/header.gif", "Blackwood Times : Weekly Newspaper");
        photographLabel.setIcon(header);

        setSize(700, 525);

        // this centers the frame on the screen
        setLocationRelativeTo(null);

        // start the image loading SwingWorker in a background thread
        loadimages.execute();
    }

    /**
     * SwingWorker class that loads the images a background thread and calls publish
     * when a new one is ready to be displayed.
     *
     * We use Void as the first SwingWroker param as we do not need to return
     * anything from doInBackground().
     */
    private SwingWorker<Void, ThumbnailAction> loadimages = new SwingWorker<Void, ThumbnailAction>() {

        /**
         * Creates full size and thumbnail versions of the target image files.
         */
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i < imageCaptions.length; i++) {
                ImageIcon icon;
                icon = createImageIcon(imagedir + imageFileNames[i], imageCaptions[i]);

                ThumbnailAction thumbAction;
                if(icon != null){

                    ImageIcon thumbnailIcon = new ImageIcon(getScaledImage(icon.getImage(), 80, 60));

                    thumbAction = new ThumbnailAction(icon, thumbnailIcon, imageCaptions[i]);

                }else{
                    // the image failed to load for some reason
                    // so load a placeholder instead
                    thumbAction = new ThumbnailAction(placeholderIcon, placeholderIcon, imageCaptions[i]);
                }
                publish(thumbAction);
            }
            // unfortunately we must return something, and only null is valid to
            // return when the return type is void.
            return null;
        }

        /**
         * Process all loaded images.
         */
        @Override
        protected void process(List<ThumbnailAction> chunks) {
            for (ThumbnailAction thumbAction : chunks) {
                JButton thumbButton = new JButton(thumbAction);
                // add the new button BEFORE the last glue
                // this centers the buttons in the toolbar
                buttonBar.add(thumbButton, buttonBar.getComponentCount() - 1);
            }
        }
    };

    /**
     * Creates an ImageIcon if the path is valid.
     * @param String - resource path
     * @param String - description of the file
     */
    protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Action class that shows the image specified in it's constructor.
     */
    private class ThumbnailAction extends AbstractAction{

        /**
         *The icon if the full image we want to display.
         */
        private Icon displayPhoto;

        /**
         * @param Icon - The full size photo to show in the button.
         * @param Icon - The thumbnail to show in the button.
         * @param String - The descriptioon of the icon.
         */
        public ThumbnailAction(Icon photo, Icon thumb, String desc){
            displayPhoto = photo;

            // The short description becomes the tooltip of a button.
            putValue(SHORT_DESCRIPTION, desc);

            // The LARGE_ICON_KEY is the key for setting the
            // icon when an Action is applied to a button.
            putValue(LARGE_ICON_KEY, thumb);
        }

        /**
         * Shows the news in the main area and sets the application title.
         */
        public void actionPerformed(ActionEvent e) {
            //theLabel.setIcon(displayPhoto);
            setTitle("News: " + getValue(SHORT_DESCRIPTION).toString());
            //addDate("1880", "1", "1");
            //newsStart();
            //newsShow(getValue(SHORT_DESCRIPTION).toString());
            newsShow(getValue(SHORT_DESCRIPTION).toString());
        }
    }
}
