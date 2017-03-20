import java.util.Enumeration;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

//import gnu.io.CommPortIdentifier;
import jssc.*;

public class PortDetector extends Timer {
	public String currentPort = PortGlobals.currentPort;
	public Enumeration ports = PortGlobals.ports;
	//public HashMap<String, CommPortIdentifier> portMap = PortGlobals.portMap;
	
	public String[] portNames = PortGlobals.portNames;
	
	private TimerTask portCapture = new TimerTask()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
			     System.out.println("START!");
				 
				 if(PortGlobals.stopSearch == false)
				 {
					 portNames = SerialPortList.getPortNames();
					 
					 /*
					 ports = CommPortIdentifier.getPortIdentifiers();
					 while (ports.hasMoreElements())
				     {
				         CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
				         System.out.println("Has More Elements.");

				         //get only serial ports
				         if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
				         {
				             portMap.put(curPort.getName(), curPort);
				             currentPort = curPort.getName();
				             System.out.println("Port = " + currentPort);
				         }
				     }
				     */
				 }
			        
			     PortController.connect();
			     System.out.println("END!");
			}
		};
		
	public PortDetector() {
		this.scheduleAtFixedRate(portCapture,0,500);
	}
}
