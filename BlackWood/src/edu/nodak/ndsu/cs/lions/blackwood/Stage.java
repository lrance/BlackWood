package edu.nodak.ndsu.cs.lions.blackwood;

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender 
 * @version 1.0
 */import edu.nodak.ndsu.cs.lions.blackwood.GUI.LoginFrame;

/**
 * <p>Title: Blackwood</p>
 * <p>Description: Blackwood UI and Lambda Moo Interface</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: NDSU</p>
 * @author Bradley Vender
 * @version 1.0
 */
import java.util.Vector;
import java.awt.image.ImageObserver;

public class Stage extends JPanel
{

	private BorderLayout borderLayout1 = new BorderLayout();
	private Vector stageArray = new Vector();
	private LoginFrame Dad;
	public ImageIcon imgRoom;
	private XYLayout xYLayout1 = new XYLayout();
	private String roomImgName;
	private MooConnection Moo;
	public Stage oStage = this;
	private String roomType = "";

	public Stage()
	{

	}

	public Stage(MooConnection moo)
	{
		try
		{
			// Dad = dad;
			//Dad.setToolTipText("REMOVE THIS!");
			Moo = moo;
			jbInit();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Deprecated
	public Stage(LoginFrame dad, MooConnection moo)
	{
		try
		{
			Dad = dad;
			//Dad.setToolTipText("REMOVE THIS!");
			Moo = moo;
			jbInit();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private static URL getURL(String path)
	{
		return Stage.class.getResource("/edu/nodak/ndsu/cs/lions/blackwood/Resources/Image/" + path);
	}


	void jbInit() throws Exception
	{
		this.setLayout(xYLayout1);
	}

	public void paintComponent(Graphics g)
	{
		StageObject sObj;

		// fix all stage moved objects to their current positions
		// by removing all objects and resetting their new positions
		this.removeAll();
		for (int j = 0; j < stageArray.size(); j++)
		{
			sObj = (StageObject) stageArray.elementAt(j);
			this.add(sObj, new XYConstraints(sObj.x, sObj.y, sObj.w, sObj.h));
		}

		super.paintComponent(g);

		if (imgRoom != null)
		{
			// write the name of this room image first
			g.drawString(roomImgName, 5, 10);
			// then attempt to paint over it with the actual image if it exists
			// this way if the image does not load for any reason at least we can
			// see the name of the image that did not load.
			imgRoom.paintIcon(this, g, 0, 0);
		}

		// if anyone is talking then show their balloons
		for (int j = 0; j < stageArray.size(); j++)
		{
			sObj = (StageObject) stageArray.elementAt(j);
			if (sObj.balloon != null)
			{
				sObj.balloon.ShowText(g);
			}
		}
	}

	public void setRoom(String type, String id, String img)
	{
		this.removeAll();
		roomType = type;
		stageArray = new Vector();
		try
		{
			roomImgName = "room #" + id + ": " + img + ".jpg";
			System.out.println("Creating Room: id=" + id + " img=" + img);
			imgRoom = new ImageIcon(getURL("room/" + img + ".jpg"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		repaint();
	}

	public void setObject(String type, String id, String img, int x, int y, int z,
		boolean active, boolean moveable, boolean selectable,
		String descr, String navTo, String name)
	{
		System.out.println("Adding " + type.substring(1) + " to stage: id=" + id + " img=" + img + " x=" + x + " y=" + y + " description=" + descr + " name=" + name);
		StageObject newObj = new StageObject(type, id, img, x, y, z, active, moveable, selectable, descr, this, navTo, name);
		stageArray.addElement(newObj);
		if (type.equals("#PLAYER") || type.equals("#SELF"))
		{
			newObj.y = oStage.getHeight() - newObj.imgPicture.getIconHeight();

		}
		this.add(newObj, new XYConstraints(newObj.x, newObj.y, newObj.w, newObj.h));
		repaint();
	}

	void removeObject(String id)
	{
		System.out.println("Removing object #" + id);
		stageArray.removeElement(getStageObj(id));
		this.repaint();
	}

	public void talk(String who, String what)
	{
		StageObject obj = getStageObj(who);
		if (obj != null)
		{
			obj.balloon = new SpeechBalloon(what, 0, 0, 1000, 1000, obj.x + (obj.w / 2), obj.y - 20);
		}
		this.repaint();
	}

	public StageObject getStageObj(String id)
	{
		StageObject so = null;
		System.out.println("looking for " + id);
		for (int j = 0; j < stageArray.size(); j++)
		{
			// System.out.println("checking " + ((StageObject) stageArray.elementAt(j)).id);
			if (((StageObject) stageArray.elementAt(j)).id.equals(id))
			{
				so = (StageObject) stageArray.elementAt(j);
				System.out.println("found " + so.id);
				break;
			}
		}
		return so;
	}

	class StageObject extends JButton implements ImageObserver
	{
		public String name;
		public String id;
		public String imgName;
		public int x;
		public int y;
		public int z;
		public int h;
		public int w;
		public boolean active;
		public boolean moveable;
		public boolean selectable;
		public String description;
		public String type;
		public ImageIcon imgPicture;
		public SpeechBalloon balloon;
		public String navTarget = "";
		private JPanel parent;
		private String path = "";
		public boolean selected;

		/* constructor */
		StageObject(String obj_type, String new_id, String new_img, int new_x, int new_y, int new_z,
			boolean new_active, boolean new_moveable, boolean new_selectable,
			String new_description, JPanel p, String navTo, String name)
		{
			type = obj_type;
			id = new_id;
			imgName = new_img;
			x = new_x;
			y = new_y;
			z = new_z;
			active = new_active;
			moveable = new_moveable;
			selectable = new_selectable;
			description = new_description;
			navTarget = navTo;

			parent = p;
			this.name = name;

			try
			{
				this.setToolTipText(name + " " + description);
				if (type.equals("#OBJECT") || type.equals("#EXIT"))
				{
					path = "objects/";
				}
				else
				{
					path = "people/";
				}
				imgPicture = new ImageIcon(getURL(path + imgName + ".gif"));
				System.out.println("Setting Item 1: " + path + imgName + ".gif");
				if (imgPicture.getIconHeight() == -1)
				{
					imgPicture = new ImageIcon(getURL(path + "noimg.gif"));
					this.setToolTipText("#" + new_id + ": " + imgName + ".gif - " + description);
				}
				this.setIcon(imgPicture);
				this.setContentAreaFilled(false);
				this.setBorderPainted(false);
				this.setFocusPainted(false);
				this.addMouseMotionListener(new MyMouseListener(this));
				this.addMouseListener(new MyMouseListener(this));
				h = imgPicture.getIconHeight();
				w = imgPicture.getIconWidth();
				System.out.println("Setting Item 2: " + path + imgName + ".gif");
			}
			catch (Exception ex)
			{
				System.out.println(ex);
			}
		}
	}
	private int startX = -1, startY = -1, offX, offY;

	private class MyMouseListener implements MouseMotionListener, MouseListener  //listens for all mouse events
	{

		private StageObject Me;
		private boolean moving = false;

		MyMouseListener(StageObject me)
		{
			Me = me;
		}

		;

		public void mouseEntered(MouseEvent e)
		{
		}

		public void mouseClicked(MouseEvent e)
		{
			if (!Me.navTarget.equals(""))
			{
				Moo.send(Me.navTarget);
			}
			if (!Me.moveable)
			{
				// tell the moo to get dialog phrases for this object, if they exist
				// or if this is an exit then do so
				Moo.send("double_click #" + Me.id);
			}
			if (Me.selectable)
			{
				Me.selected = true;
				//Me.setBorderPainted(false);
			}
		}

		public void mousePressed(MouseEvent e)
		{
			startX = e.getX();
			startY = e.getY();
			Me.balloon = null;
		}

		public void mouseReleased(MouseEvent e)
		{
			if (!Me.type.equals("#EXIT"))
			{
				Me.balloon = null;
				Me.y = oStage.getHeight() - Me.imgPicture.getIconHeight();
				Me.setLocation(Me.x, Me.y);
				if (Me.x != startX || Me.y != startY)
				{
					Moo.send("move #" + Me.id + " to " + Me.x + " " + Me.y);
				}
				Me.parent.repaint();
			}
		}

		public void mouseExited(MouseEvent e)
		{
		}

		public void mouseMoved(MouseEvent e)
		{
		}

		public void mouseDragged(MouseEvent e)
		{
			if (Me.moveable)
			{
				offX = e.getX() - startX;
				offY = e.getY() - startY;
				Me.x = Me.getX() + offX;
				Me.y = Me.getY() + offY;
				Me.setLocation(Me.x, Me.y);
			}
		}
	}
}
