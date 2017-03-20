import java.io.IOException;
import java.util.TooManyListenersException;
/*
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
*/
import jssc.*;

public class PortController {
	private static int TIMEOUT = 2000;
	public static SerialPort serialPort = PortGlobals.serialPort;
	
	public static void connect()
    {
		/*PortGlobals.selectedPortIdentifier = (CommPortIdentifier)PortGlobals.portMap.get("COM8");
    	CommPort commPort = null;
    	
        try
        {
        	
            //the method below returns an object of type CommPort
		   //System.out.println("Preparing to Open!");
            commPort = PortGlobals.selectedPortIdentifier.open("Dashboard", TIMEOUT);
		    //System.out.println("OPEN!");
            //the CommPort object can be casted to a SerialPort object
            PortGlobals.serialPort = (SerialPort) commPort;
            PortGlobals.connected = true;
            PortGlobals.serialDetector.cancel();
        	PortGlobals.serialDetector.purge();
		    //System.out.println("Timer Canceled!");
            PortGlobals.stopSearch = true;

            PortGlobals.serialPort.setSerialPortParams(115200 , 8, 1, 0);
		    //System.out.println("Parameters Set!");
            initIOStream();
		    //System.out.println("Connected!");
        }
        catch (PortInUseException e)
        {
        	System.out.print("Port is in Use.\n");
        }
        catch (Exception e)
        {
        	System.out.print("GENERIC EXCEPTION\n");
        }
        */
		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_115200,
                     SerialPort.DATABITS_8,
                     SerialPort.STOPBITS_1,
                     SerialPort.PARITY_NONE);
			//sendCommand(10);
			PortGlobals.connected = true;
            PortGlobals.serialDetector.cancel();
        	PortGlobals.serialDetector.purge();
            PortGlobals.stopSearch = true;      
            
            serialPort.addEventListener(PortGlobals.serialEvent);
            //initIOStream();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   
		
    }
	
	/*public static boolean initIOStream()
    {
        //return value for whether opening the streams is successful or not
        boolean successful = false;

        try {
            // SPECIFIC CASE FOR ARDUINO 
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	PortGlobals.input = PortGlobals.serialPort.getInputStream();
        	PortGlobals.output = PortGlobals.serialPort.getOutputStream();
            //writeData(0, 0);
            sendCommand(10);
            
        	initListener();
            successful = true;
            return successful;
        }
        catch (IOException e) {
        	System.out.print("Failed to open IO Stream.\n");
            return successful;
        }
    }
    
	public static void initListener()
    {
        try
        {
        	PortGlobals.serialPort.addEventListener(PortGlobals.serialEvent);
        	PortGlobals.serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
        	System.out.print("Too many listeners EXCEPTION.\n");
        }
    }*/
	
	public static void disconnect()
    {
        //close the serial port
        try
        {
        	System.out.println("Disconnect Starts");
        	PortGlobals.serialPort.removeEventListener();     
        	System.out.println("Listener Removed");
        	PortGlobals.input.close();
        	PortGlobals.output.close();
        	PortGlobals.serialPort.closePort();
            System.out.print("Port Closed.\n");
           // PortGlobals.serialDetector = new PortDetector();
        }
        catch (Exception e)
        {
        	System.out.print("Failed to close Port.\n");
        }
    }
	
	public static void sendCommand(int mcommand)
	{
		//byte[] command = mcommand.getBytes();
		/*try {
			PortGlobals.output.write("A".getBytes());
			PortGlobals.output.flush();
		
			//System.out.println("Sent Command!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to send Command!");
		}*/
		
		try {
			PortGlobals.serialPort.writeBytes("A".getBytes());
			//System.out.println("Command Sent!");
			
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to send Command!");
		}
	}
}
