package edu.nodak.ndsu.cs.lions.blackwood;
/** SpeechBalloon class.  Handles the ever fun task of text wrap, drawing
 * of the balloon, and all of that other fun stuff for the MOOSprites.
 */
import java.awt.*;
import java.util.*;

public class SpeechBalloon extends Object {
	public int BalloonX;
	public int BalloonY;
	public int BalloonHeight;
	public int BalloonWidth;
	/** MinX, MinY, MaxX and MaxY constitute the bounding box for the
         * speech balloon.  The balloon will be drawn with an arc near
         * BalloonX, BalloonY.  The assumption is that the bounding box is
         * large enough for the text. */
	public int MinX;
	public int MinY;
	public int MaxX;
	public int MaxY;
	protected Vector Lines;
	protected boolean PreprocessedGraphics;
	protected int LineTextHeight;
	protected String BalloonText;

	public final boolean IsHit(int x,int y) {
//		System.out.print("Hit test on balloon.\n");
		return ( (x>=(BalloonX-BalloonWidth/2)) && (x<=(BalloonX+BalloonWidth/2)) &&
		     (y>=(BalloonY-BalloonHeight)) && (y<=BalloonY) );
	}

	public SpeechBalloon(String Text, int minX, int minY, int maxX, int maxY, int x, int y) {
		MinX=minX;
		MinY=minY;
		MaxX=maxX;
		MaxY=maxY;
		BalloonWidth=0;
		BalloonHeight=0;
		PreprocessedGraphics=false;
		BalloonX=x;
		BalloonY=y;
		SetText(Text);
	}

	/* When the sprite this thing is attatched to moves, it has to tell this what its
         * new center is */
	public final void SetXY(int NewX, int NewY) {
		if (NewX<(MinX+BalloonWidth/2))
			NewX=MinX+BalloonWidth/2;
		else if ( (NewX+BalloonWidth/2) > MaxX)
			NewX=MaxX-BalloonWidth/2;
		if (NewY<(MinY+BalloonHeight) )
			NewY=MinY+BalloonHeight;
		else if ( (NewY+BalloonHeight) > MaxY)
			NewY=MaxY-BalloonHeight;
		BalloonX=NewX;
		BalloonY=NewY;
	}

	public final synchronized void SetText(String Text) {
//		System.out.print("SetText on balloon.\n");
		BalloonText=Text;
		PreprocessedGraphics=false;
	}

	public final synchronized void FormatBalloon(Graphics g) {
		/* This may not be the best assumption in the world, but let's assume
		 * that we only need to compute our current graphics state once.
  		 */
//		System.out.print("FormatBalloon.\n");
		final int w=150;
		Lines=new Vector();
		StringTokenizer t=new StringTokenizer(BalloonText);
		String CurrentString="";
		String NextString;
		Font f=g.getFont();
		FontMetrics m=g.getFontMetrics(f);
		LineTextHeight=m.getHeight();
		int LineTextWidth=0;
		boolean done=false;
		while(!done) {
			if (t.hasMoreTokens()) {
				NextString=t.nextToken();
				if (m.stringWidth(CurrentString+" "+NextString)<=w)
					if (CurrentString.length()>0)
						CurrentString=CurrentString+" "+NextString;
					else
						CurrentString=NextString;
				else {
					if (CurrentString.length()>0)
						Lines.addElement(CurrentString);
					if (m.stringWidth(CurrentString)>LineTextWidth)
						LineTextWidth=m.stringWidth(CurrentString);
					CurrentString=NextString;
				}
			} else {
				if (m.stringWidth(CurrentString)>LineTextWidth)
					LineTextWidth=m.stringWidth(CurrentString);
				Lines.addElement(CurrentString);
				done=true;
			}
		}
		BalloonHeight=LineTextHeight*Lines.size()+LineTextHeight/2;
		BalloonWidth=LineTextWidth+4*m.charWidth('W');
		SetXY(BalloonX,BalloonY);
		PreprocessedGraphics=true;
	}

	// ShowText tries to get the text balloon centered around
	// x, and the bottom at y.
        // Width is the suggested with of the text balloon, which may be
	// exceeded if need be.
	public final void ShowText(Graphics g) {
		//System.out.println("ShowText: "+BalloonText);

		if (!PreprocessedGraphics)
			FormatBalloon(g);
		int TextHeight=LineTextHeight*Lines.size();

		Font f=g.getFont();
		FontMetrics m=g.getFontMetrics(f);
		int ArcWidth, ArcHeight;

		ArcWidth=2*m.charWidth('W');
		ArcHeight=TextHeight/2;
		g.setColor(Color.white);
		g.fillArc( BalloonX -15,
			BalloonY-5, 30,60,60,60);
		g.setColor(Color.black);
		g.drawLine( BalloonX -10, BalloonY-5, BalloonX,
				BalloonY+25);
		g.drawLine( BalloonX, BalloonY + 25, BalloonX +10, BalloonY-5);
		/* g.drawArc( BalloonX -15,
			BalloonY-5, 30,60,60,60);
		*/
		g.setColor(Color.white);
		g.fillRoundRect( BalloonX-(BalloonWidth/2),
			BalloonY-BalloonHeight,
			BalloonWidth, BalloonHeight, ArcWidth, ArcHeight);
		g.setColor(Color.black);
		g.drawRoundRect( BalloonX-(BalloonWidth/2),
			BalloonY-BalloonHeight,
			BalloonWidth, BalloonHeight, ArcWidth, ArcHeight);
		g.setColor(Color.white);
		g.drawLine( BalloonX-7, BalloonY, BalloonX+7,BalloonY);
		g.setColor(Color.black);
		Enumeration i=Lines.elements();
		int LineX=BalloonX-(BalloonWidth-ArcWidth)/2;
		int LineY=BalloonY-BalloonHeight+LineTextHeight;
		while (i.hasMoreElements()) {
			g.drawString( (String)i.nextElement(), LineX, LineY);
			LineY+=LineTextHeight;
		}
	}
}
