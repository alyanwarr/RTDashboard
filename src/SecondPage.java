import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SecondPage {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	BackgroundPanel dashboard;
	private javax.swing.JLabel CLTtemp = new JLabel();
	private javax.swing.JLabel carSpeed = new JLabel();
	private javax.swing.JLabel RPM = new JLabel();
	private javax.swing.JLabel Gear = new JLabel();
	// End of variables declaration//GEN-END:variables

	public void DisplayGUI() {
		JFrame frame = new JFrame();
		dashboard = new BackgroundPanel("/SecondPage/Background with Text and Bar.png");
		frame.setSize(777, 449);
		frame.setContentPane(dashboard);
		frame.setResizable(false);
		frame.setVisible(true);
		Font displayFont = new Font("digital-7", Font.BOLD, 60);
		// ---

		// label for coolant temp reading
		CLTtemp.setFont(displayFont); // NOI18N
		CLTtemp.setForeground(new Color(190, 30, 45));
		dashboard.add(CLTtemp, 1, 0);
		CLTtemp.setSize(200, 200);
		CLTtemp.setLocation(388, 175);

		// label for car speed
		carSpeed.setFont(displayFont); // NOI18N
		carSpeed.setForeground(new java.awt.Color(190, 30, 45));
		dashboard.add(carSpeed);
		carSpeed.setSize(300, 200);
		carSpeed.setLocation(299, -50);

		// label for RPM
		RPM.setFont(displayFont); // NOI18N
		RPM.setForeground(new java.awt.Color(190, 30, 45));
		dashboard.add(RPM);
		RPM.setSize(300, 200);
		RPM.setLocation(298, 28);

		// label for gear
		Gear.setFont(displayFont); // NOI18N
		Gear.setForeground(new java.awt.Color(190, 30, 45));
		dashboard.add(Gear);
		Gear.setSize(200, 200);
		Gear.setLocation(323, 102);

		// Fuel LED Indicator
		Indicator fuel = Integration.secFuel;
		dashboard.add(fuel, 7);
		fuel.setLocation(588, 55);

		// ADK LED Indicator
		Indicator ADK = Integration.secADK;
		dashboard.add(ADK, 7, 0);
		ADK.setLocation(579, 237);

		// TPS Progress Bar
		TPSMeter throttle = Integration.secthrottle;
		dashboard.add(throttle, 8, 0);
		throttle.setLocation(121, 335);
		throttle.throttleRatio.setLocation(145, 12);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				CLTtemp.setText(Integration.clt + " C");
				carSpeed.setText(Integration.speed + " KM/H");
				RPM.setText(Integration.RPM + " RPM");
				Gear.setText("" + (Integration.gear+1));
			}
		};

		Timer timer = new Timer(10, listener);
		timer.start();

		PageIcon pi = new PageIcon();
		pi.toggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dashboard db = new Dashboard();
				frame.dispose();
				db.DisplayGUI();
			}
		});
		dashboard.add(pi.toggle, 9, 0);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SecondPage().DisplayGUI();
			}
		});

	}
	/*
	 * class backgroundLabel extends JLayeredPane { private BufferedImage image;
	 * 
	 * public backgroundLabel() { try { image = ImageIO.read(getClass().
	 * getResource("/SecondPage/Background with Text and Bar.png")); } catch
	 * (IOException e) { // TODO Auto-generated catch block
	 * System.out.println("Unable to fetch image."); e.printStackTrace(); } }
	 * 
	 * @Override public Dimension getPreferredSize() { return image == null ?
	 * new Dimension(400, 300): new Dimension(image.getWidth(),
	 * image.getHeight()); }
	 * 
	 * @Override protected void paintComponent(Graphics g) {
	 * super.paintComponent(g); g.drawImage(image, 0, 0, this); }
	 * 
	 * }
	 */
}

/*
 * class Indicator2 extends JLabel { private BufferedImage Safe; private
 * BufferedImage Warning; private String safePath; private String warningPath;
 * private Boolean isSafe = true; public Indicator2(String mfinePath, String
 * mWarningPath) { safePath = mfinePath; warningPath = mWarningPath;
 * 
 * setOpaque(false); setLayout(null); setBackground(new Color(0, 0, 100, 100));
 * 
 * try { Safe = ImageIO.read(getClass().getResource(safePath)); Warning =
 * ImageIO.read(getClass().getResource(warningPath)); } catch (IOException e) {
 * // TODO Auto-generated catch block
 * System.out.println("Unable to fetch image."); e.printStackTrace(); }
 * 
 * ActionListener listener = new ActionListener() { public void
 * actionPerformed(ActionEvent ae) { isSafe = !isSafe; repaint(); } };
 * 
 * Timer timer = new Timer(500, listener); timer.start();
 * 
 * setSize(new Dimension(Safe.getWidth(), Safe.getHeight())); }
 * 
 * RenderingHints hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING,
 * RenderingHints.VALUE_ANTIALIAS_ON );
 * 
 * @Override protected void paintComponent(Graphics g) {
 * super.paintComponent(g); Graphics2D g2 = (Graphics2D) g; if(isSafe) {
 * g2.drawImage(Safe, 0, 0, null); } else if(!isSafe) { g2.drawImage(Warning, 0,
 * 0, null); }
 * 
 * } }
 */
// Class for combining the TPS bar and ratio
class secondTPSMeter extends JLayeredPane {
	private TPSBar throttleBar = new TPSBar("/SecondPage/Progress Bar.png");
	private ProgressRatio throttleRatio = new ProgressRatio(30);

	public secondTPSMeter() {
		this.setSize(500, 500);
		this.setLocation(121, 335);
		this.setOpaque(false);
		this.setBackground(new Color(0, 100, 0, 100));
		this.add(throttleBar, 1, 0);
		this.setLayout(null);
		throttleBar.setLocation(0, 0);

		this.add(throttleRatio, 2, 0);
		throttleRatio.setLocation(145, 12);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if ((throttleBar.counter <= throttleBar.maxBarWidth) && !throttleBar.isEnd) {
					throttleBar.counter++;
					throttleBar.ratio = (throttleBar.counter / (float) throttleBar.maxBarWidth) * 100;
					if (throttleBar.counter == throttleBar.progressBar.getWidth())
						throttleBar.isEnd = true;
				}

				if ((throttleBar.counter >= 0) && throttleBar.isEnd) {
					throttleBar.counter--;
					throttleBar.ratio = (throttleBar.counter / (float) throttleBar.maxBarWidth) * 100;
					if (throttleBar.counter == 0)
						throttleBar.isEnd = false;
				}

				throttleRatio.value = (int) throttleBar.ratio;

				if (throttleRatio.value < 50) {
					throttleRatio.setForeground(Color.BLACK);
				} else if (throttleRatio.value > 50) {
					throttleRatio.setForeground(Color.WHITE);
				}

				throttleRatio.setText(throttleRatio.value + "%");
				throttleBar.setSize(new Dimension(throttleBar.progressWidth[throttleBar.counter],
						throttleBar.progressBar.getHeight()));
			}
		};

		Timer timer = new Timer(10, listener);
		timer.start();
	}

	// Class for the TPS Bar
	class secondTPSBar extends JLabel {
		public BufferedImage progressBar;
		public int counter = 0;
		public Boolean isEnd = false;
		public float ratio = 0;
		public int maxBarWidth;
		public int maxBarHeight;
		public int[] progressWidth = new int[500];

		public secondTPSBar() {
			setOpaque(false);
			setLayout(null);
			setLocation(245, 310);
			setBackground(new Color(0, 0, 100, 100));

			try {
				progressBar = ImageIO.read(getClass().getResource("/SecondPage/Progress Bar.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}

			maxBarWidth = progressBar.getWidth();
			maxBarHeight = progressBar.getHeight();
			for (int i = 0; i <= maxBarWidth; i++) {
				progressWidth[i] = i;
			}

		}

		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(progressBar, 0, 0, null);
		}

	}
}

// Class for the TPS Ratio number
class secondProgressRatio extends JLabel {
	public int value;
	private Font displayFont = new Font("digital-7", Font.BOLD, 30);

	public secondProgressRatio() {
		this.setFont(displayFont);
		this.setVerticalAlignment(TOP);
		this.setHorizontalAlignment(CENTER);
		this.setOpaque(false);
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 100, 100));
		this.setLocation(121, 150);
		this.setSize(50, 50);
	}

	RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

}
