package edu.nodak.ndsu.cs.lions.blackwood;
//
//  CustomizeFrame.java
//  DollarBay Client
//
//  Created by Harold Chaput on Thu Jul 24 2003.
//  Copyright (c) 2003 __MyCompanyName__. All rights reserved.
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.Vector;
import java.io.*;
import java.net.*;

public class SimpleImage extends JFrame
{
    // Data Members
    protected Vector		playerImages;
    protected Vector		storeImages;

    protected JTabbedPane	tabbed = new JTabbedPane();
    protected JTextField	playerDescField = new JTextField();
    protected JLabel		playerImage = new JLabel();
    protected int		origPlayerPos = -1;
    protected int		playerPos = -1;

    protected JTextField	storeDescField = new JTextField();
    protected JLabel		storeImage = new JLabel();
    protected int		origStorePos = -1;
    protected int		storePos = -1;
    protected JSlider           playerSlider;
    protected JSlider           storeSlider;

    protected Action		applyAction;
    protected Action		revertAction;
    protected Action		previousAction;
    protected Action		nextAction;
    protected Action            doneAction;
    protected ChangeListener    pictChange;

    // Public Methods
    public SimpleImage()
    {
        super( "Customize" );
        
      // setIconImage((new ImageIcon(getClass().getResource("images/logo.jpg"))).getImage());
      //  playerImages = getImageList( "PeopleImages" );
      // storeImages = getImageList( "StoreImages" );

        String	pname = "hhh";//RetailerClientApp.loginObject.playerImage;
        if(pname.indexOf( '.' ) > 0)
            pname = pname.substring(0, pname.indexOf( '.' ));

       

       //playerPos = playerImages.indexOf( pname );
       
       //if(RetailerClientApp.loginObject.playerDesc != null)
        //  playerDescField.setText(RetailerClientApp.loginObject.playerDesc);
       // else  playerDescField.setText("Enter your description here.");
       // if ( playerPos == -1 ) { playerPos = 0; }
       // origPlayerPos = playerPos;
       
        buildActions();
        build();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/Applet/blackwood_icon.gif"));
        pack();
        
        setLocationRelativeTo( null );
    }

    // Protected Methods
    protected void buildActions()
    {
        applyAction = new AbstractAction( "Apply" )
        {
           public void actionPerformed( ActionEvent evt )
            {
                int			id;
                String			newname = null;
                String			newdesc = null;
                String			newimage = null;

                if ( tabbed.getSelectedIndex() == 0 )
                {
                  //  id = RetailerClientApp.loginObject.playerID;

                   // if ( !playerDescField.getText().equals( RetailerClientApp.loginObject.playerDesc ) )
                    //{
                      //  newdesc = playerDescField.getText();
                    //}

                        newimage = (String) playerImages.get( playerPos ) + ".gif";
                }
       
               // RetailerClientApp.sendMOOEvent( new TangibleChangedEvent( id, newname, newdesc, newimage ) );
            }
        };

        revertAction = new AbstractAction( "Revert" )
        {
            public void actionPerformed( ActionEvent evt )
            {		int id;
            		String image;
                if ( tabbed.getSelectedIndex() == 0 )
                {		//id = RetailerClientApp.loginObject.playerID;

                    playerPos = origPlayerPos;
                    playerSlider.setValue(playerPos);

                    image = (String)playerImages.get(playerPos)+".gif";
                    //playerDescField.setText( RetailerClientApp.loginObject.playerDesc );
                }
               // RetailerClientApp.sendMOOEvent(new TangibleChangedEvent(id,
                 //   RetailerClientApp.loginObject.playerName,
                //RetailerClientApp.loginObject.playerDesc,
//	image));
            }
        };

        previousAction = new AbstractAction( "<-" )
        {
            public void actionPerformed( ActionEvent evt )
            {
                if ( tabbed.getSelectedIndex() == 0 )
                {
                    if ( playerPos == 0 ) { playerPos = playerImages.size(); }
                    playerPos--;
                    playerSlider.setValue(playerPos);
                }
                else
                {
                    if ( storePos == 0 ) { storePos = storeImages.size(); }
                    storePos--;
                    storeSlider.setValue(storePos);
                }
            }
        };

        nextAction = new AbstractAction( "->" )
        {
            public void actionPerformed( ActionEvent evt )
            {
                if ( tabbed.getSelectedIndex() == 0 )
                {
                    playerPos++;
                    if ( playerPos == playerImages.size() ) { playerPos = 0; }
                    playerSlider.setValue(playerPos);
                }
                else
                {
                    storePos++;
                    if ( storePos == storeImages.size() ) { storePos = 0; }
                    storeSlider.setValue(storePos);
                }
            }
        };

        doneAction = new AbstractAction( "Done" )
        {
            public void actionPerformed( ActionEvent evt )
            {
               hide();
            }
        };


        pictChange = new ChangeListener()
        {  public void stateChanged(ChangeEvent e)
           {  JSlider source = (JSlider)e.getSource();
              //if (!source.getValueIsAdjusting())
              {  int pos = source.getValue();
                 if(tabbed.getSelectedIndex() == 0)
                 {  playerPos = pos;
                   // updatePlayerImage();
                 }
                 else
                 {  storePos = pos;
                    //updateStoreImage();
                 }
              }
           }
        };

    }
/*
    protected void updatePlayerImage()
    {
        try
        {
            playerImage.setIcon( RetailerClientApp.getImageIcon(playerImages.get( playerPos ) + ".gif" ) );
            RetailerClientApp.loginObject.playerImage = playerImages.get(playerPos) + ".gif";
        }
        catch( Exception ex ) { ex.printStackTrace(); } // { JOptionPane.showMessageDialog( this, ex.getMessage() ); }
    }

    protected void updateStoreImage()
    {
        try
        {
            storeImage.setIcon( RetailerClientApp.getImageIcon(storeImages.get( storePos ) + ".gif" ) ) ;
        }
        catch( Exception ex ) { ex.printStackTrace(); } // { JOptionPane.showMessageDialog( this, ex.getMessage() ); }
    }
*/
    protected void build()
    {
        JPanel		panel;
        JPanel		panel2;
        JPanel		panel3;

       // updatePlayerImage();
        //updateStoreImage();
       // playerImage.setHorizontalAlignment( JLabel.CENTER );
       // storeImage.setHorizontalAlignment( JLabel.CENTER );
        //playerSlider = new JSlider(JSlider.HORIZONTAL, 0,playerImages.size()-1, playerPos);
       // playerSlider.addChangeListener(pictChange);
       // playerSlider.setPaintTicks(true);
       //playerSlider.setMajorTickSpacing(playerImages.size()/18);

        panel = new JPanel( new BorderLayout() );
        
        //playerDescField.setBorder( BorderFactory.createTitledBorder( "Player Description" ) );
        panel.add( playerDescField, BorderLayout.NORTH );
       // playerImage.setBorder( BorderFactory.createTitledBorder( "Player Image" ) );
        
        panel.add( playerImage, BorderLayout.CENTER );
        panel2 = new JPanel();
        panel2.add( new JButton( previousAction ) );
        //panel2.add(playerSlider);
        panel2.add( new JButton( nextAction ) );
        panel.add( panel2, BorderLayout.SOUTH );
        tabbed.addTab( "Player", panel );

        /*panel = new JPanel( new BorderLayout() );
        storeDescField.setBorder( BorderFactory.createTitledBorder( "Store Description" ) );
        panel.add( storeDescField, BorderLayout.NORTH );
        storeImage.setBorder( BorderFactory.createTitledBorder( "Store Image" ) );
        panel.add( storeImage, BorderLayout.CENTER );
        panel2 = new JPanel();
        //panel2.add( new JButton( previousAction ) );
        //panel2.add( new JButton( nextAction ) );
        storeSlider = new JSlider(JSlider.HORIZONTAL, 0, storeImages.size()-1,storePos);
        storeSlider.addChangeListener(pictChange);
        storeSlider.setPaintTicks(true);
        storeSlider.setMajorTickSpacing(1);
        storeSlider.setSnapToTicks(true);
        panel2.add(storeSlider);
        panel.add( panel2, BorderLayout.SOUTH );
        tabbed.addTab( "Store", panel );*/

        getContentPane().add( tabbed, BorderLayout.CENTER );

        panel = new JPanel();
        panel.add( new JButton( applyAction ) );
        panel.add( new JButton( revertAction ) );
        panel.add( new JButton( doneAction ) );
        getContentPane().add( panel, BorderLayout.SOUTH );
    }

    protected Vector getImageList( String directory )
    {
        Vector		ret = new Vector();
        URL		imageListURL = null;
        BufferedReader 	reader = null;
        boolean		done = false;
        String		temp;

        try
        {
            imageListURL = new URL("ftffg" );//RetailerClientApp.baseURL + RetailerClientApp.baseDir + directory );
            reader = new BufferedReader( new InputStreamReader( imageListURL.openStream() ) );
        }
        catch ( Exception e )
        {
            throw( new RuntimeException("Unable to load player image list.") );
        }

        while (!done)
        {
            try
            {
                temp = reader.readLine();

                if (temp==null || temp=="")
                {
                    done=true;
                }
                else
                {
                    ret.addElement( temp );
                }
            }
            catch (IOException e) { done = true; }
        }

        return( ret );
    }
}


