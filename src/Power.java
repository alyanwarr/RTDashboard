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
import javax.swing.JLabel;
import javax.swing.Timer;

public class Power extends JLabel {
	private BufferedImage ON;
	private BufferedImage OFF;
	private String onPath;
	private String offPath;
	private Boolean powerState = false;

	public Power(String mfinePath, String mWarningPath) {
		onPath = mfinePath;
		offPath = mWarningPath;

		setOpaque(false);
		setLayout(null);
		setBackground(new Color(0, 0, 100, 100));
		setLocation(10, 353);
		setSize(52,52);

		try {
			ON = ImageIO.read(getClass().getResource(onPath));
			OFF = ImageIO.read(getClass().getResource(offPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to fetch image.");
			e.printStackTrace();
		}

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (Integration.power == true) {
					powerState = true;
				} else if (Integration.power == false) {
					powerState = false;
				}

				repaint();
			}
		};

		Timer timer = new Timer(500, listener);
		timer.start();

		setSize(new Dimension(ON.getWidth(), ON.getHeight()));
	}

	RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (powerState) {
			g2.drawImage(ON, 0, 0, null);
		} else if (!powerState) {
			g2.drawImage(OFF, 0, 0, null);
		}

	}
}
