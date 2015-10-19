package edu.nodak.ndsu.cs.lions.blackwood;
import java.awt.Graphics;  
import java.awt.Image;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import javax.imageio.ImageIO;  

public class ZPanel  extends javax.swing.JPanel {
	
	 private static final long serialVersionUID = 1L;  
	    private Image image;  
	    private int imgWidth;  
	    private int imgHeight;  
	  
	    public int getImgWidth() {  
	        return imgWidth;  
	    }  
	  
	    public void setImgWidth(int imgWidth) {  
	        this.imgWidth = imgWidth;  
	    }  
	  
	    public int getImgHeight() {  
	        return imgHeight;  
	    }  
	  
	    public void setImgHeight(int imgHeight) {  
	        this.imgHeight = imgHeight;  
	    }  
	  
	    public ZPanel() {  
	    }  
	  
	    public void setImagePath(String imgPath) {  
	        
	        try {  
	           
	            //image = ImageIO.read(new FileInputStream(imgPath));  
	        	image = ImageIO.read(getClass().getResourceAsStream(imgPath));
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        setImgWidth(image.getWidth(this));  
	        setImgHeight(image.getHeight(this));  
	    }  
	  
	    @Override  
	    public void paintComponent(Graphics g1) {  
	        int x = 0;  
	        int y = 0;  
	        Graphics g = (Graphics) g1;  
	        if (null == image) {  
	            return;  
	        }  
	  
	        g.drawImage(image, x, y, image.getWidth(this), image.getHeight(this),  
	                this);  
	        g = null;  
	    }  

}
