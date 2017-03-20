import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;

import javax.swing.*;

public class ProgressRatio extends JLabel {
	public int value;
	
	public ProgressRatio(int fontSize)
	{			
		Font displayFont = new Font("digital-7", Font.BOLD, fontSize);
		this.setFont(displayFont);	
		this.setVerticalAlignment(TOP);
		this.setHorizontalAlignment(CENTER);
		this.setOpaque(false);
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 100, 100));	
		this.setLocation(368, 370);
		this.setSize(70, 50);		
	}
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
}
