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

import org.usfirst.frc3620.FRC3620_DancingWasher.commands.*;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton fillTankButton1;
    public JoystickButton shootCannon1;
    public JoystickButton fillTankButton2;
    public JoystickButton shootCannon2;
    public JoystickButton liftLidButton;
    public JoystickButton dropLidButton;
    public JoystickButton shootSafetyLB;
    public JoystickButton shootSafetyRB;
    public Joystick driveJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        driveJoystick = new Joystick(0);
        
        shootSafetyRB = new JoystickButton(driveJoystick, 7);
        shootSafetyRB.whileHeld(new EnableShootRB());
        shootSafetyLB = new JoystickButton(driveJoystick, 6);
        shootSafetyLB.whileHeld(new EnableShootLB());
        dropLidButton = new JoystickButton(driveJoystick, 6);
        dropLidButton.whenPressed(new DropLidCommand());
        liftLidButton = new JoystickButton(driveJoystick, 7);
        liftLidButton.whenPressed(new LiftLidCommand());
        shootCannon2 = new JoystickButton(driveJoystick, 3);
        shootCannon2.whenPressed(new ShootTShirtCommand2());
        fillTankButton2 = new JoystickButton(driveJoystick, 4);
        fillTankButton2.whenPressed(new FillTankCommand2());
        shootCannon1 = new JoystickButton(driveJoystick, 1);
        shootCannon1.whenPressed(new ShootTShirtCommand1());
        fillTankButton1 = new JoystickButton(driveJoystick, 2);
        fillTankButton1.whenPressed(new FillTankCommand1());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("Command 1", new Command1());

        SmartDashboard.putData("driveMecanumCommand", new driveMecanumCommand());

        SmartDashboard.putData("ShootTShirtCommand1", new ShootTShirtCommand1());

        SmartDashboard.putData("ShootTShirtCommand2", new ShootTShirtCommand2());

        SmartDashboard.putData("FillTankCommand1", new FillTankCommand1());

        SmartDashboard.putData("FillTankCommand2", new FillTankCommand2());

        SmartDashboard.putData("LiftLidCommand", new LiftLidCommand());

        SmartDashboard.putData("DropLidCommand", new DropLidCommand());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

