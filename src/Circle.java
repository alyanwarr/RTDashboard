import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class Circle extends JLabel{
	Dimension dim = new Dimension(19,19);
	public Circle()
	{
		setOpaque(false);
		setLayout(null);		
		//setLocation(138, 152);
		setSize(dim);
	}
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.YELLOW);
		g2.setRenderingHints(hints);
		g2.fillOval(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
	}
}
