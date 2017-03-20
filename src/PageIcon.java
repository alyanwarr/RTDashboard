import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javafx.scene.control.Toggle;

public class PageIcon extends JLabel {
	private BufferedImage image;
	public JButton toggle;

	public PageIcon()
	{
		
		toggle = new JButton("");
		toggle.setLocation(707, 359);
		toggle.setSize(52,52);
		toggle.setOpaque(false);
		toggle.setContentAreaFilled(false);
		toggle.setBorderPainted(false);
		 try {
				image = ImageIO.read(getClass().getResource("/Images/Icon.png"));
				toggle.setIcon(new ImageIcon(image));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}
		
	}

	RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}
