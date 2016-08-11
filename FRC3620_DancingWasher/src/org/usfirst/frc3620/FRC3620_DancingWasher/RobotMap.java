// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC3620_DancingWasher;
    

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static SpeedController driveSpeedController1;
    public static SpeedController driveSpeedController0;

    public static RobotDrive driveRobotDrive;
    public static DigitalInput driveKillSwitch;
    public static DoubleSolenoid shooterSubsystemFillValve;
    public static Relay shooterSubsystemTank1Shoot;
    public static AnalogInput shooterSubsystemPressureSensor;
    public static DoubleSolenoid shooterSubsystemTank1Lid;
    public static AnalogInput shooterSubsystema0;
    public static DoubleSolenoid shooterSubsystemDirectionValve;
    public static Relay shooterSubsystemTank2Shoot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        //driveSpeedController3 = new Talon(3);
       // LiveWindow.addActuator("Drive", "Speed Controller 3", (Talon) driveSpeedController3);
        
        driveSpeedController1 = new Talon(1);
        LiveWindow.addActuator("Drive", "Speed Controller 1", (Talon) driveSpeedController1);
        
        driveSpeedController0 = new Talon(0);
        LiveWindow.addActuator("Drive", "Speed Controller 0", (Talon) driveSpeedController0);
        
        //driveSpeedController2 = new Talon(2);
       // LiveWindow.addActuator("Drive", "Speed Controller 2", (Talon) driveSpeedController2);
        
       driveRobotDrive = new RobotDrive(driveSpeedController0, driveSpeedController1);
       driveRobotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        
        driveRobotDrive.setSafetyEnabled(false);
        driveRobotDrive.setExpiration(0.1);
        driveRobotDrive.setSensitivity(0.5);
        driveRobotDrive.setMaxOutput(1.0);

      //  driveRobotDriveMecanum4.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
       // driveRobotDriveMecanum4.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveKillSwitch = new DigitalInput(0);
        LiveWindow.addSensor("Drive", "KillSwitch", driveKillSwitch);
        
        shooterSubsystemFillValve = new DoubleSolenoid(0, 0, 1);      
        LiveWindow.addActuator("ShooterSubsystem", "Fill Valve", shooterSubsystemFillValve);
        
        shooterSubsystemTank1Shoot = new Relay(0);
        LiveWindow.addActuator("ShooterSubsystem", "Tank1Shoot", shooterSubsystemTank1Shoot);
        
        shooterSubsystemPressureSensor = new AnalogInput(1);
        LiveWindow.addSensor("ShooterSubsystem", "Pressure Sensor", shooterSubsystemPressureSensor);
        
        shooterSubsystemTank1Lid = new DoubleSolenoid(0, 2, 3);      
        LiveWindow.addActuator("ShooterSubsystem", "Tank1Lid", shooterSubsystemTank1Lid);
        
        shooterSubsystema0 = new AnalogInput(0);
        LiveWindow.addSensor("ShooterSubsystem", "a0", shooterSubsystema0);
        
        shooterSubsystemDirectionValve = new DoubleSolenoid(0, 4, 5);      
        LiveWindow.addActuator("ShooterSubsystem", "Direction Valve", shooterSubsystemDirectionValve);
        
        shooterSubsystemTank2Shoot = new Relay(1);
        LiveWindow.addActuator("ShooterSubsystem", "Tank2Shoot", shooterSubsystemTank2Shoot);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
