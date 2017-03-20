import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TPSMeter extends JLayeredPane {	
	public TPSBar throttleBar;
	public ProgressRatio throttleRatio;
	public int progress;
	public TPSMeter(String path, int fontSize)
	{
		throttleBar = new TPSBar(path);
		throttleRatio = new ProgressRatio(fontSize);
		this.setSize(throttleBar.maxBarWidth, throttleBar.maxBarHeight);
		this.setOpaque(false);
		this.setBackground(new Color(0, 100, 0, 100));
		this.add(throttleBar, 1, 0);
		this.setLayout(null);
		throttleBar.setLocation(0, 0);		
		this.add(throttleRatio, 2, 0);

		
		ActionListener listener = new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	        	
	        	/*if((throttleBar.counter <= throttleBar.maxBarWidth) && !throttleBar.isEnd)
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
	        	*/
        		throttleRatio.value = (int)throttleBar.ratio;
        		
	        	if(Integration.tps < 50)
	        	{
	        		throttleRatio.setForeground(Color.BLACK);
	        	}
	        	else if(Integration.tps > 50)
	        	{
	        		throttleRatio.setForeground(Color.WHITE);
	        	}
	        	progress = Integration.tps * throttleBar.maxBarWidth;
	    		throttleRatio.setText(Integration.tps + "%");	
	        	throttleBar.setSize(new Dimension(progress/100, throttleBar.progressBar.getHeight()));	
	        }
	    };
	    
	    Timer timer = new Timer(10, listener);
	    timer.start();
	}
}
