/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nodak.ndsu.cs.lions.blackwood;

import edu.nodak.ndsu.cs.lions.blackwood.GUI.Images;
import java.net.URL;

/**
 *
 * @author foertsch
 */
public class Utilities {

	public static URL getURL(final String path)
	{
		return Images.class.getResource(path);
	}
}
