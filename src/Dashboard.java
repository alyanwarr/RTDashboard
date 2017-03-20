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


public class Dashboard {
	BackgroundPanel dashboard;
	float[] angleArray = new float[361];
	
	public void DisplayGUI()
	{
		// build the frame and set the dashboard background to be the content pane.
		JFrame frame = new JFrame();
		dashboard = new BackgroundPanel("/Images/DashboardBackground.png");
		frame.setSize(777, 449);		
		frame.setContentPane(dashboard);
		frame.setResizable(false);
		frame.setVisible(true);
		
		// create the speed pointer
		/*pointer = new labelPointer();
		pointer.setBackground(new Color(0,80,0,100));
		dashboard.add(pointer, 1, 0);*/
	/*	pointer.setLocation(20, 30);
		pointer.setSize(200, 200);*/
		
		// Speed Pointer Construction and Testing
		Circle speedCircle = new Circle();
		dashboard.add(speedCircle, 3, 0);
		speedCircle.setLocation(138, 152);
		
		Pointer speedPointer = Integration.speedPointer;
		dashboard.add(speedPointer, 2, 0);
		speedPointer.setLocation(138-71, 152-71);
		
		// RPM Pointer Construction and Testing
		Circle RPMCircle = new Circle();
		dashboard.add(RPMCircle, 5, 0);
		RPMCircle.setLocation(138+474, 152);
		
		Pointer RPMPointer = Integration.RPMPointer;
		dashboard.add(RPMPointer, 4, 0);
		RPMPointer.setLocation(138-71+474, 152-71);
		
		// Gear Level Number
		Gear gearLvl = Integration.gearLvl;
		dashboard.add(gearLvl, 6, 0);
		
		// Temp LED Indicator
		Indicator temp = Integration.temp;
		dashboard.add(temp, 7, 0);
		temp.setLocation(280, 253);
		
		// Fuel LED Indicator
		Indicator fuel = Integration.fuel;
		dashboard.add(fuel, 7);
		fuel.setLocation(363, 285);
				
		// ADK LED Indicator
		Indicator ADK = Integration.ADK;
		dashboard.add(ADK, 7, 0);
		ADK.setLocation(439, 265);
		
		// TPS Progress Bar
		TPSMeter throttle = Integration.throttle;
		dashboard.add(throttle, 8, 0);
		throttle.setLocation(245,310);
		throttle.throttleRatio.setLocation(110, 61);
		
		// Toggle Button for switching pages
		PageIcon pi = new PageIcon();
		pi.toggle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SecondPage sc = new SecondPage();
				frame.dispose();
				sc.DisplayGUI();
			}
		});
		dashboard.add(pi.toggle, 9, 0);
		
		Power connectionState = Integration.connection;
		dashboard.add(connectionState, 10, 0);
		
	}
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Dashboard().DisplayGUI();
                new PortGlobals();
            }
        });
		
	}
		
}
/*
class BackgroundPanel extends JLayeredPane {
	 private BufferedImage image;
		
		public BackgroundPanel() {
           try {
				image = ImageIO.read(getClass().getResource("/Images/DashboardBackground.png"));
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

// class containing the pointer image - NO LONGER USED -
class labelPointer extends JLabel {
	 private BufferedImage image;
		
		public labelPointer() {
			setOpaque(false);
			setLayout(null);
			
			setLocation(47+474, 54);
          try {
				image = ImageIO.read(getClass().getResource("/Images/Pointer2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}
         setSize(200, 200);
      }
		
	 
		 @Override
	     protected void paintComponent(Graphics g) {
	         super.paintComponent(g);
	         Graphics2D g2 = (Graphics2D) g;
	         g2.rotate(Math.toRadians(0), 0, image.getHeight());
	         g2.drawImage(image, 90, 30, null);
	         //g.drawImage(image, 0, 0, this);       
	     }

}

// class for the circle of the pointer's center
class Circle extends JLabel {
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

// class for the pointer itself
class Pointer extends JLabel {
	
	Dimension dim = new Dimension(4,80);
	private float rotAngle = 0;
	private float[] testArray = new float[361];

	int counter = 0;
	
	public void setAngle(float angle)
	{
		rotAngle = angle;
	}
	
	public float getAngle()
	{
		return rotAngle;
	}
	
	public void setTestAngle(float[] angle)
	{
		for(int i = 0; i<=360; i++)
		{
			testArray[i] = angle[i];
		}
	}
	
	public Pointer()
	{
		setOpaque(false);
		setLayout(null);		
		//setLocation(138-71, 152-71);
		setSize(160,160);
		setBackground(new Color(0, 0, 100, 100));

		ActionListener listener = new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	           counter++;
	           if(counter >= 360)
	           {
	    	       counter = 0;
	           }
	           repaint();
	        }
	    };
	    
	    Timer timer = new Timer(10, listener);
	    timer.start();
	}
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.YELLOW);
		g2.setRenderingHints(hints);
		//Line2D line = new Line2D.Float(start, end);
		g2.translate(78, 0);
		g2.rotate(Math.toRadians(testArray[counter]),(int)dim.getWidth()/2, (int)dim.getHeight());
		g2.fillRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());	
	
	}
}

// Class for the Gear Level display
class Gear extends JLabel {
	private BufferedImage[] imageArr = new BufferedImage[5];
	private int counter = 0;
	public Gear()
	{
		setOpaque(false);
		setLayout(null);
		setLocation(348,101);
		setBackground(new Color(0, 0, 100, 100));
		
		 try {
				for(int i = 0; i<=4; i++)
				{
					switch (i)
					{
						case 0: imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/1.png"));
							break;
						case 1: imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/2.png"));
							break;
						case 2: imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/3.png"));
							break;
						case 3: imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/4.png"));
							break;
						case 4: imageArr[i] = ImageIO.read(getClass().getResource("/Images/Gear/5.png"));
							break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch image.");
				e.printStackTrace();
			}
		 
		 ActionListener listener = new ActionListener() {
		        public void actionPerformed(ActionEvent ae) {
		           counter++;
		           if(counter >= 5)
		           {
		    	       counter = 0;
		           }
		           repaint();
		        }
		    };
		    
		    Timer timer = new Timer(750, listener);
		    timer.start();
		    
		setSize(new Dimension(imageArr[4].getWidth(), imageArr[4].getHeight()));			
	}	
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(imageArr[counter], 0, 0, null);       
    }
	
}

// Class for the various LED indicators
class Indicator extends JLabel {
	private BufferedImage Safe;
	private BufferedImage Warning;
	private String safePath;
	private String warningPath;
	private Boolean isSafe = true;
	public Indicator(String mfinePath, String mWarningPath)
	{
		safePath = mfinePath;
		warningPath = mWarningPath;
		
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

// Class for combining the TPS bar and ratio
class TPSMeter extends JLayeredPane {	
	private TPSBar throttleBar = new TPSBar();
	private ProgressRatio throttleRatio = new ProgressRatio();
	
	public TPSMeter()
	{
		this.setSize(throttleBar.maxBarWidth, throttleBar.maxBarHeight);
		this.setLocation(245,310);
		this.setOpaque(false);
		this.setBackground(new Color(0, 100, 0, 100));
		this.add(throttleBar, 1, 0);
		this.setLayout(null);
		throttleBar.setLocation(0, 0);
		
		this.add(throttleRatio, 2, 0);
		throttleRatio.setLocation(120, 61);
		
		ActionListener listener = new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	        	
	        	if((throttleBar.counter <= throttleBar.maxBarWidth) && !throttleBar.isEnd)
	        	{
	        		throttleBar.counter++;
	        		throttleBar.ratio = (throttleBar.counter/(float)throttleBar.maxBarWidth)*100;
	        		if(throttleBar.counter == throttleBar.progressBar.getWidth())
	        			throttleBar.isEnd = true;
	        	}
	        	
	        	if((throttleBar.counter >= 0) && throttleBar.isEnd)
	        	{
	        		throttleBar.counter--;
	        		throttleBar.ratio = (throttleBar.counter/(float)throttleBar.maxBarWidth)*100;
	        		if(throttleBar.counter == 0)
	        			throttleBar.isEnd = false;
	        	}
	        	
        		throttleRatio.value = (int)throttleBar.ratio;
        		
	        	if(throttleRatio.value < 50)
	        	{
	        		throttleRatio.setForeground(Color.BLACK);
	        	}
	        	else if(throttleRatio.value > 50)
	        	{
	        		throttleRatio.setForeground(Color.WHITE);
	        	}
	        	
	    		throttleRatio.setText(throttleRatio.value + "%");	
	        	throttleBar.setSize(new Dimension(throttleBar.progressWidth[throttleBar.counter], throttleBar.progressBar.getHeight()));	
	        }
	    };
	    
	    Timer timer = new Timer(10, listener);
	    timer.start();
}

// Class for the TPS Bar 
class TPSBar extends JLabel {
	public BufferedImage progressBar;
	public int counter = 0;
	public Boolean isEnd = false;
	public float ratio = 0;
	public ProgressRatio progressRatio = new ProgressRatio();		
	public int maxBarWidth;
	public int maxBarHeight;
	public int[] progressWidth = new int[281];
	public TPSBar()
	{
		setOpaque(false);
		setLayout(null);
		setLocation(245, 310);
		setBackground(new Color(0, 0, 100, 100));
		this.add(progressRatio);
			
		try {
			progressBar = ImageIO.read(getClass().getResource("/Images/TPS Bar.png"));
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
}

// Class for the TPS Ratio number
class ProgressRatio extends JLabel {
	public int value;	
	private Font displayFont = new Font("digital-7", Font.BOLD, 20);
	
	public ProgressRatio()
	{			
		this.setFont(displayFont);	
		this.setVerticalAlignment(TOP);
		this.setHorizontalAlignment(CENTER);
		this.setOpaque(false);
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 100, 100));	
		this.setLocation(368, 370);
		this.setSize(40, 20);		
	}
	
	RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
		);
	
}
*/