import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Indicator extends JLabel {
	private BufferedImage Safe;
	private BufferedImage Warning;
	private String safePath;
	private String warningPath;
	private Boolean isSafe = false;
	private String indicatorType;
	public int rTemp = 0;
	public int rFuel = 0;
	public int rADK = 0;
	
	public Indicator(String mfinePath, String mWarningPath, String type)
	{
		safePath = mfinePath;
		warningPath = mWarningPath;
		indicatorType = type;
		
		setOpaque(false);
		setLayout(null);
		setBackground(new Color(0, 0, 100, 100));
		
		 try {
			 Safe = ImageIO.read(getClass().getResource(safePath));
			 Warning = ImageIO.read(getClass().getResource(warningPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}
		 
		 ActionListener listener = new ActionListener() {
		        public void actionPerformed(ActionEvent ae) {
		        	isSafe = !isSafe;
		        	
		        	switch(type)
		        	{
		        	case "temp": 
		        		if (Integration.clt < 20 || Integration.clt >70) {isSafe=false;}
		        		else {isSafe=true;} break;
		        		
		        	case "fuel": 
		        		if (Integration.ffuel < 10) {isSafe=false;}
		        		else {isSafe=true;} break;
		        		
		        	case "adk": 
		        		if (Integration.adk > 10) {isSafe=false;}
		        		else {isSafe=true;} break;	
		        		
		        	default: isSafe = true;
		        		
		        	}
		        	
		           repaint();
		        }
		    };
		    
		    Timer timer = new Timer(500, listener);
		    timer.start();
		    
		setSize(new Dimension(Safe.getWidth(), Safe.getHeight()));			
	}	
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(isSafe)
        {
        	g2.drawImage(Safe, 0, 0, null);  
        }
        else if(!isSafe)
        {
        	g2.drawImage(Warning, 0, 0, null);  
        }
            
    }
}
