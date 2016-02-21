package Systems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoyDrive {
	
	//joystick
	public final static int joy_id = 1;
	public final static int joy3d_id = 0;
	public final static int joy_ly = 1;
	public final static int joy_ry = 5;
	public final static int joy3d_y = 1;
	
	public final static int joy_a_id = 1;
	public final static int joy_b_id = 2;
	public final static int joy_x_id = 3;
	public final static int joy_y_id = 4;
	
	
	//joystick dirve
	public static Joystick  joy,joy3d;
	
	//value
	public static double RY;
	public static double LY;
	public static double val;
	public static double arm_speedlimit;
	
	public static boolean joy_a,joy_b,joy_x,joy_y;
	
	
	
	
	public static void init(){
		joy = new Joystick(joy_id);
		joy3d = new Joystick(joy3d_id);
		SmartDashboard.putNumber("arm_speedlimit",0.3);
	}
	
	public static void Joystickvalue(){
		
		//Arm
		if(-joy3d.getRawAxis(joy3d_y)>0.1||-joy3d.getRawAxis(joy3d_y)<-0.1){
			if(-joy3d.getRawAxis(joy3d_y)>-arm_speedlimit){
				val = -arm_speedlimit;
			}
			else if(joy3d.getRawAxis(joy3d_y)>arm_speedlimit){
				val = arm_speedlimit;
			}
			else{
	        	val = -joy3d.getRawAxis(joy3d_y);
			}

        }	
		else{
			val = 0.0;
		}
		
		//Drivetrain
		if(joy.getRawAxis(joy_ly)>0.1 || joy.getRawAxis(joy_ly)<-0.1){		
        	LY = joy.getRawAxis(joy_ly);
        }	
        else{
        	LY = 0.0 ;	
        }	
        if(joy.getRawAxis(joy_ry)>0.1 || joy.getRawAxis(joy_ry)<-0.1){		
        	RY = joy.getRawAxis(joy_ry);
        }	
        else{
        	RY = 0.0 ;	
        }	
		
		joy_a = joy.getRawButton(joy_a_id);
        joy_b = joy.getRawButton(joy_b_id);
		joy_x = joy.getRawButton(joy_x_id);
        joy_y = joy.getRawButton(joy_y_id);
        Dashboard();
	}
	
	public static void Dashboard(){
    	SmartDashboard.putNumber("LY", JoyDrive.LY);
    	SmartDashboard.putNumber("RY", JoyDrive.RY);
    	arm_speedlimit = SmartDashboard.getNumber("arm_speedlimit",0.3);
	}
}
