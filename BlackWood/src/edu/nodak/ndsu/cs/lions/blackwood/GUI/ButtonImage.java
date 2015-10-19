package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import edu.nodak.ndsu.cs.lions.blackwood.Utilities;
import javax.swing.ImageIcon;

/**
 * Class ButtonImage is a simple wrapper class.  Most of the buttons in Blackwood have a normal image and a 'pressed' image (the image that appears when the
 * button is clicked).  To make things a tad bit easier, ButtonImage objects contain three ImageIcons, one for the normal state, one for the pressed state, and
 * one for the disabled state of a button.
 *
 * Last modified:  2010-03
 * @author foertsch
 */
public class ButtonImage
{
	/** The standard image */
	private ImageIcon image;

	/** The image that should be used for a pressed button. */
	private ImageIcon imagePressed;

	/** The image that should be used for a disabled button. */
	private ImageIcon imageDisabled;
	
	/**
	 * Constructor to make the Button Image.
	 * @param image the path to the image file.  Note, these are probably in Jars, so like /Images/Interface/ should do.
	 * @param imagePressed the path to the pressed version of the image.
	 */
	public ButtonImage(String image, String imagePressed)
	{
		this.setImage(new ImageIcon(Utilities.getURL(image)));
		this.setImagePressed(new ImageIcon(Utilities.getURL(imagePressed)));
		this.imageDisabled = null;
	}

	/**
	 * Constructor to make a button image
	 * @param image the path to the image file.  Note, these are probably in Jars, so like /Images/Interface/ should do.
	 * @param imagePressed the path to the pressed image file.  Note, these are probably in Jars, so like /Images/Interface/ should do.
	 * @param imageDisabled the path to the disabled image file.  Note, these are probably in Jars, so like /Images/Interface/ should do.
	 */
	public ButtonImage(String image, String imagePressed, String imageDisabled)
	{
		this.setImage(new ImageIcon(Utilities.getURL(image)));
		this.setImagePressed(new ImageIcon(Utilities.getURL(imagePressed)));
		this.setImageDisabled(new ImageIcon(Utilities.getURL(imageDisabled)));
	}
	
	/**
	 * Accessor method for the image value.
	 * 
	 * @return the ImageIcon object.
	 */
	public ImageIcon getImage()
	{
		return this.image;
	}
	
	/**
	 * Accessor method for the pressed image value.
	 * 
	 * @return the pressed version of the ImageIcon object.
	 */
	public ImageIcon getImagePressed()
	{
		return this.imagePressed;
	}


	/**
	 * Accessor method for the diabled image value
	 *
	 * @return the disabled version of the ImageIcon object.
	 */
	public ImageIcon getImageDisabled()
	{
		return this.imageDisabled;
	}
	
	/**
	 * Mutator method for the image object.
	 * 
	 * @param image the new image value.
	 */
	private void setImage(ImageIcon image)
	{
		this.image = image;
	}
	
	/**
	 * Mutator method for teh image pressed object.
	 * 
	 * @param imagePressed the new pressed image value.
	 */
	private void setImagePressed(ImageIcon imagePressed)
	{
		this.imagePressed = imagePressed;
	}

	/**
	 * Mutator method for the image disabled object.
	 *
	 * @param imageDisabled the new disabled image value.
	 */
	private void setImageDisabled(ImageIcon imageDisabled)
	{
		this.imageDisabled = imageDisabled;
	}
}
