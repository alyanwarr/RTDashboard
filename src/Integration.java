
public class Integration {
	// Main Dashboard
	public static Pointer speedPointer = new Pointer("speed");
	public static Pointer RPMPointer = new Pointer("rpm");
	public static Gear gearLvl = new Gear();
	public static Indicator temp = new Indicator("/Images/Temp Safe.png", "/Images/Temp Warning.png", "temp");
	public static Indicator fuel = new Indicator("/Images/Fuel Safe.png", "/Images/Fuel Warning.png", "fuel");
	public static Indicator ADK = new Indicator("/Images/ADK Safe.png", "/Images/ADK Warning.png", "adk");
	public static TPSMeter throttle = new TPSMeter("/Images/TPS Bar.png", 20);
	public static Power connection = new Power("/Images/PowerON.png", "/Images/PowerOFF.png");
	
	// Secondary Dashboard
	public static Indicator secFuel = new Indicator("/SecondPage/Fuel Safe.png", "/SecondPage/Fuel Warning.png", "fuel");
	public static Indicator secADK = new Indicator("/SecondPage/ADK Safe.png", "/SecondPage/ADK Warning.png", "adk");
	public static TPSMeter secthrottle = new TPSMeter("/SecondPage/Progress Bar.png", 30);
	public static double speed = 0;
	public static double clt = 0;
	public static int gear = 1;
	public static double RPM = 0;
	public static int tps = 0;
	public static int ffuel = 0;
	public static int adk = 0;
	
	// For the power Indicator
	public static Boolean power = false;
}
