// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC3620_DancingWasher.subsystems;

import org.usfirst.frc3620.FRC3620_DancingWasher.Robot;
import org.usfirst.frc3620.FRC3620_DancingWasher.RobotMap;
import org.usfirst.frc3620.FRC3620_DancingWasher.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Drive extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController speedController3 = RobotMap.driveSpeedController3;
    SpeedController speedController1 = RobotMap.driveSpeedController1;
    SpeedController speedController0 = RobotMap.driveSpeedController0;
    SpeedController speedController2 = RobotMap.driveSpeedController2;
    RobotDrive robotDriveMecanum4 = RobotMap.driveRobotDriveMecanum4;
    DigitalInput killSwitch = RobotMap.driveKillSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new driveMecanumCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	System.out.println("Drive Init");
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getJoystickY(GenericHID hid) {
    	return hid.getRawAxis(1);
    }
    
    public double getJoystickX(GenericHID hid) {
    	return hid.getRawAxis(4);
    }
    
    public double getJoystickLT(GenericHID hid)
    {
    	return hid.getRawAxis(2);
    }
    
    public double getJoystickRT(GenericHID hid)
    {
    	return hid.getRawAxis(3);
    }
    
    public void mecanumDrive_Polar(GenericHID hid)
    {
    	 double yMove = getJoystickY(hid);
         double  xMove = getJoystickX(hid);
         double lSpin = getJoystickLT(hid);
         double rSpin = getJoystickRT(hid);
         double rotate = rSpin - lSpin;
         if(System.currentTimeMillis() % 1000  < 10)
         {
        	 System.out.println("Y: " + yMove + "\nX: " + xMove + "\nLS: " + lSpin);
        	 System.out.println("RS: " + rSpin + "\nRotate: " + rotate);
         }
         if(yMove < 0.2 && yMove > -0.2)
         {
        	 yMove = 0;
         }
         if(xMove < 0.2 && xMove > -0.2)
         {
        	 xMove = 0;
         }
         if(rotate < 0.2 && rotate > -0.2)
         {
        	 rotate = 0;
         }
         int gyro = 0;
         if (ifSafeCommand()) {
         robotDriveMecanum4.mecanumDrive_Cartesian(xMove, yMove, rotate, gyro);
         }
         else { 
        	 robotDriveMecanum4.stopMotor();
     
         }
    }
    
    public void setDrive(double moveX, double moveY, double rotate)
    {
    	//robotDriveMecanum4.mecanumDrive_Polar(moveX, moveY, rotate);   
    }
    public boolean ifSafeCommand() {
    	if (killSwitch.get()) {
    		return false;
    	}
    	else { 
    		return true; 
    	}
    	
    }
}

