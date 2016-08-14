package org.usfirst.frc3620.FRC3620_DancingWasher.commands;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC3620_DancingWasher.Robot;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FillCommand extends Command {
    Logger logger;
    int barrel;

    public FillCommand(int _barrel) {
        super();
        barrel = _barrel;
        logger = EventLogging.getLogger(getClass(), "" + _barrel, Level.INFO);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("initialized");
        logger.info("initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooterSubsystem.fill(barrel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        logger.info("ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        logger.info("interrupted");
    }
}
