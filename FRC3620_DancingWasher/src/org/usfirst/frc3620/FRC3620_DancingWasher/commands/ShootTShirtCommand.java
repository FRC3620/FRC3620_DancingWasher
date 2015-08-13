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

import org.usfirst.frc3620.FRC3620_DancingWasher.Robot;

/**
 *
 */
public class  ShootTShirtCommand extends Command {
Timer timer = new Timer();
    public ShootTShirtCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooterSubsystem);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

   
    	 // Called just before this Command runs the first time
        protected void initialize() {
        	timer.reset();
        	timer.start();
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {
        	
        	Robot.shooterSubsystem.startShooter1();
        }

        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {
        	if (timer.get() >= .5 ) {
        		return true;
        	}
            return false;
        }

        // Called once after isFinished returns true
        protected void end() {
        	Robot.shooterSubsystem.stopShooter1();
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
        	Robot.shooterSubsystem.stopShooter1();
    }

    
    }
    
