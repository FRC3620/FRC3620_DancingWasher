// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC3620_DancingWasher.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3620.FRC3620_DancingWasher.Robot;
import org.usfirst.frc3620.logger.EventLogging;

/**
 *
 */
public class  RumbleCommand extends Command {
    Timer timer = new Timer();
	String message = null;
    public RumbleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.rumblerSubsystem);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        message = null;        
    }
    
    public void setMessage (String m) {
    	message = m;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Reason", message);
    	EventLogging.writeToDS("** " + message);
    	Robot.rumblerSubsystem.rumblerOn();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rumblerSubsystem.rumblerOff();
    	SmartDashboard.putString("Reason", "");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
