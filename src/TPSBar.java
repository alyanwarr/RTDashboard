import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TPSBar extends JLabel {
	public BufferedImage progressBar;
	public int counter = 0;
	public Boolean isEnd = false;
	public float ratio = 0;
	public int maxBarWidth;
	public int maxBarHeight;
	public int[] progressWidth = new int[500];
	public TPSBar(String path)
	{
		setOpaque(false);
		setLayout(null);
		setLocation(245, 310);
		setBackground(new Color(0, 0, 100, 100));
			
		try {
			progressBar = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to fetch image.");
			e.printStackTrace();
		}
		
		maxBarWidth = progressBar.getWidth();
		maxBarHeight = progressBar.getHeight();
		for(int i = 0; i <= maxBarWidth; i++ )
		{
			progressWidth[i] = i;
		}			
		    
	}
		
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
			
	@Override
	    protected void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       Graphics2D g2 = (Graphics2D) g;
	       g2.drawImage(progressBar, 0, 0, null);       
	   }
		
}
