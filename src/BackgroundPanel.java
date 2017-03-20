import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BackgroundPanel extends JLayeredPane {
	 private BufferedImage image;
		
		public BackgroundPanel(String path) {
        try {
				image = ImageIO.read(getClass().getResource(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}
    }
		
		 @Override
	     public Dimension getPreferredSize() {
	         return image == null ? new Dimension(400, 300): new Dimension(image.getWidth(), image.getHeight());
	     }
		 
		 @Override
	     protected void paintComponent(Graphics g) {
	         super.paintComponent(g);
	         g.drawImage(image, 0, 0, this);
	     }
}
