import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Pointer extends JLabel {
	Dimension dim = new Dimension(4, 80);
	private float[] testArray = new float[361];

	int counter = 0;
	int angle = -150;
	public int speed;
	String pointerType;
	
	public Pointer(String type) {
		pointerType = type;
		setOpaque(false);
		setLayout(null);
		// setLocation(138-71, 152-71);
		setSize(160, 160);
		setBackground(new Color(0, 0, 100, 100));

		for (int i = 0; i <= 360; i++) {
			testArray[i] = i;
		}

		// This is the part responsible for the current animation
		/*
		 * By using a Timer with a specific interval, the action listener's
		 * action is invoked changing the counter value which picks an angle
		 * from testArray so changing the angle with which the pointer should be
		 * drawn and then calling repaint() to repaint the pointer
		 */
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				/*
				 * counter++; if(counter >= 360) { counter = 0; }
				 */
				angle = (int) (-150 + 1.5 * Integration.speed);
				switch (pointerType)
				{
				case "speed": angle = (int) (-150 + 1.5 * Integration.speed); break;
				case "rpm": angle = (int) (-150 + 0.06 * Integration.RPM); break;
				default: angle = -150;
				}
				repaint();
			}
		};

		Timer timer = new Timer(10, listener);
		timer.start();
	}

	RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.YELLOW);
		g2.setRenderingHints(hints);
		// Line2D line = new Line2D.Float(start, end);
		g2.translate(78, 0);
		g2.rotate(Math.toRadians(angle), (int) dim.getWidth() / 2, (int) dim.getHeight());
		g2.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

	}
}
