import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;

/*import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;*/

import jssc.*;

public class PortGlobals {
	public static Boolean found = false;
	public static Boolean connected = false;
	public static Boolean dataRead = false;
	public static String currentPort;
	public static Enumeration ports = null;
	//public static HashMap<String, CommPortIdentifier> portMap = new HashMap<String, CommPortIdentifier>();
	public static PortDetector serialDetector = new PortDetector();
	//public static CommPortIdentifier selectedPortIdentifier = null;
	//public static SerialPort serialPort = null;
	public static InputStream input = null;
	public static OutputStream output = null;
	public static SerialReader serialEvent = new SerialReader();
	public static Boolean stopSearch = false;
	
	// JSSC Library Implementation
	public static String[] portNames;
	public static SerialPort serialPort = new SerialPort("COM3");
	/*
	public static void main(String[] args) {

	}*/
}
