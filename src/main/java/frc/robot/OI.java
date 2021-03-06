/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;


import edu.wpi.first.networktables.NetworkTableInstance;

public class OI {
	private static XboxController DriverController, OperatorController;
	private static Button DriverStart, DriverBack;
	private static Button OperatorX, OperatorY, OperatorA, OperatorB, OperatorlBump,  OperatorBack, OperatorStart;

	public OI() {
		DriverController = new XboxController(0);
		DriverBack = new JoystickButton(DriverController, 7);
		DriverStart = new JoystickButton(DriverController, 8);
		DriverStart.whenPressed(new AbortAll());
		DriverBack.whenPressed(new AbortAll());
		//Driver Left Bump = Slow Speed, value controlled via RobotMap.Slowspeed

		OperatorController = new XboxController(1);
		OperatorA= new JoystickButton(OperatorController, 1);
		OperatorB = new JoystickButton(OperatorController, 2);
		OperatorX = new JoystickButton(OperatorController, 3);
		OperatorY = new JoystickButton(OperatorController, 4);
		OperatorlBump = new JoystickButton(OperatorController, 5);
		//OperatorrBump = new JoystickButton(OperatorController, 6);
		OperatorBack = new JoystickButton(OperatorController, 7);
		OperatorStart = new JoystickButton(OperatorController, 8);
		
		OperatorStart.whenPressed(new ToggleControlMode());
		OperatorBack.whenPressed(new AbortAll());

		computerOI();
	}
	
	public static void manualOI(){
	/*	//Hatch Pistons In/Out
		OperatorX.whenPressed(new HatchEject(true));   
		OperatorX.whenReleased(new HatchEject(false));
		//FourBar in - Out
		OperatorB.whenPressed(new FourBar(true));
		OperatorA.whenPressed(new FourBar(false));
		//Left Operator Stick = Climber (in Climber subsystem)
		//Right Operator Stick = Elevator (in Elevator subsystem)
		OperatorlBump.whenPressed(new ClimbHAB2()); //Climb HAB 2
		OperatorrBump.whenPressed(new ClimbHAB3()); //Climb HAB 3*/

		NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
	}

	public static void computerOI(){
		 //test code
		OperatorA.whenPressed(new PlaceHatch1()); //deploy Hatch level 1
		//OperatorA.whenReleased(new AbortAll());
		OperatorB.whenPressed(new PlaceHatch2()); //deploy Hatch level 2
		//OperatorB.whenReleased(new AbortAll());
		OperatorY.whenPressed(new PlaceHatch3()); //deploy Hatch level 3
		//OperatorY.whenReleased(new AbortAll());
		//OperatorX.whenPressed(new AquireHatch()); //aquire Hatch
		//OperatorX.whenReleased(new FinishGet());
		/*OperatorX.whenPressed(new FourBarIn());
		OperatorlBump.whenPressed(new FourBar());
		
		OperatorrBump.whenPressed(new HatchEject()); 
		OperatorrBump.whenReleased(new HatchRetract());*/
		OperatorlBump.whenPressed(new FourBar());
		OperatorX.whenPressed(new HatchEject()); 
		OperatorX.whenReleased(new HatchRetract());


		NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(2);
	}

	//Driver Code
	public static double DriverLeftJoystick(){
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);	}
	public static double DriverRightJoystick() {
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);	}
	public static int DriverPOV(){
		return DriverController.getPOV();
	}
	public static boolean DriverRightBumper() {return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);}
	public static boolean DriverLeftBumper() {return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);}
	public static boolean DriverX() {return DriverController.getXButton();}
	public static boolean DriverY() {return DriverController.getYButton();}
	public static boolean DriverA() {return DriverController.getAButton();}
	public static boolean DriverB() {return DriverController.getBButton();}
	public static double DriverRightTrigger() {
		return DriverController.getTriggerAxis(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static double DriverLeftTrigger() {
		return DriverController.getTriggerAxis(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}

	public static void DriverRumbleEnhanced(int Duration, double intensity, boolean rLeft, boolean rRight) {
		RumbleEnhanced(DriverController, Duration, intensity, rLeft, rRight);
	}

	//Operator control
	public static double OperatorLeftJoystick(){
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double OperatorRightJoystick() {
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static double OperatorRightTrigger() {
		return OperatorController.getTriggerAxis(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static double OperatorLeftTrigger() {
		return OperatorController.getTriggerAxis(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static int OperatorPOV(){
		return OperatorController.getPOV();
	}
	public static boolean OperatorRightBumper() {return OperatorController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);}
	
	public static void OperatorRumbleEnhanced(int Duration, double intensity, boolean rLeft, boolean rRight) {
		RumbleEnhanced(OperatorController, Duration, intensity, rLeft, rRight);
	}

private static void RumbleEnhanced(XboxController myController, int Duration, double intensity, boolean rLeft, boolean rRight) {
	Thread t1 = new Thread(new Runnable() {
			 public void run() {
			 if (rLeft) {myController.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);}
			 if (rRight) {myController.setRumble(GenericHID.RumbleType.kRightRumble, intensity);}
			try {
				Thread.sleep(Duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (rLeft) {myController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);}
			if (rRight) {myController.setRumble(GenericHID.RumbleType.kRightRumble, 0);}
			 }
	 });
	t1.start();
}
}
