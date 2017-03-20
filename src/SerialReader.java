import java.util.Timer;
import java.util.TimerTask;

import jssc.*;

public class SerialReader implements SerialPortEventListener {
	private Boolean first = true;
	private int delay;

	@Override
	public void serialEvent(SerialPortEvent evt) {
		if (evt.isRXCHAR()) {
			try {
				Integration.power = true;
				// System.out.println("Attempting to Read!");
				if (first == true) {
					delay = 180;
				}
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// int bufferSize = 20;
				String buffer;
				/*
				 * int dataSize = PortGlobals.input.read(buffer);
				 * System.out.print("Data Size = " + dataSize + "\n");
				 * System.out.print("Data = "); for(int i = 0; i<dataSize; i++)
				 * { System.out.print((char)buffer[i]); }
				 * System.out.print("\n");
				 */
				PortGlobals.dataRead = true;
				// PortController.disconnect();
				buffer = PortGlobals.serialPort.readString();
				System.out.print("Data = ");
				System.out.println(buffer);
				
				//split readings
				
				String[] readings;
				readings = buffer.split("-");
				
				Integration.speed = Double.parseDouble(readings[0]);
				Integration.RPM = Double.parseDouble(readings[1]) * 1000;
				Integration.tps = Integer.parseInt(readings[2]);
				Integration.clt = Double.parseDouble(readings[3]);
				Integration.gear = Integer.parseInt(readings[4]);
				Integration.ffuel = Integer.parseInt(readings[5]);
				Integration.adk  = Integer.parseInt(readings[6].trim());
				
				//-----
				
				// System.out.print("\n");
				first = false;
				delay = 180;
				//PortController.sendCommand(10);
			} catch (Exception e) {
				System.out.print("Failed to read data.\n");
				System.out.println(e);
			}
		}
	}

}
