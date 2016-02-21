package Systems;

import Systems.JoyDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;

public class CANDriveAssembly {
	
	private static boolean inited = false;
	
	//talon info
	private static final int talon_arm_id = 2;
	
	//talon drive
	private static CANTalon talon_arm;
	
	//value
	private static double BusVoltage[];
	private static double OutputVoltage[];
	
	private static final int device_num = 1;
	
	

	private static int PulseWidthpos,PulseWidthus,periodus,PulseWidthVel ;


	private static boolean s;
	
	public static void init(){
		
		if(!inited){
			talon_arm = new CANTalon(talon_arm_id);
			talon_arm.enableControl();
			talon_arm.setSafetyEnabled(true);
			talon_arm.changeControlMode(CANTalon.TalonControlMode.Current);
			talon_arm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
			JoyDrive.init();
			BusVoltage = new double[device_num];
			OutputVoltage = new double[device_num];
			inited = true;
		}
	}
	
	public static void teleopPreiodic(){
		
		JoyDrive.Joystickvalue();
		talon_arm.set(0);
		
		
		getfeedback();
		Dashboard();
	}
	
	
	@SuppressWarnings("deprecation")
	public static void Dashboard(){
		SmartDashboard.putNumber("arm_value", talon_arm.get());
		SmartDashboard.putInt("PulseWidthpos", PulseWidthpos);
		SmartDashboard.putInt("PulseWidthus", PulseWidthus);
		SmartDashboard.putInt("periodus", periodus);
		SmartDashboard.putInt("PulseWidthVel", PulseWidthVel);
		
		SmartDashboard.putNumber("arm_value",talon_arm.get());
		//SmartDashboard.getNumber("val");
		SmartDashboard.putBoolean("sensorstaus", s);
	}
	
	public static void getencoder(){
		PulseWidthpos = talon_arm.getPulseWidthPosition();
		PulseWidthus = talon_arm.getPulseWidthRiseToFallUs();
		periodus = talon_arm.getPulseWidthRiseToRiseUs();
		PulseWidthVel = talon_arm.getPulseWidthVelocity();
		FeedbackDeviceStatus sensorstaus = talon_arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
		s = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorstaus);
	}
	
	public static void getfeedback(){
		getencoder();
		BusVoltage[0] = talon_arm.getBusVoltage();
		OutputVoltage[0] = talon_arm.getOutputVoltage();
		
	}
}
