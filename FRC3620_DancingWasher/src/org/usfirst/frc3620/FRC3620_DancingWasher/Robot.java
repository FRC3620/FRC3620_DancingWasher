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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.communication.HALControlWord;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3620.FRC3620_DancingWasher.commands.*;
import org.usfirst.frc3620.FRC3620_DancingWasher.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    RobotState currentState = RobotState.UNKNOWN;
    RobotState previousState = RobotState.UNKNOWN;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drive drive;
    public static ShooterSubsystem shooterSubsystem;
    public static RumblerSubsystem rumblerSubsystem;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drive = new Drive();
        shooterSubsystem = new ShooterSubsystem();
        rumblerSubsystem = new RumblerSubsystem();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        shooterSubsystem.initializeDashboard();
    }

    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    public void disabledInit() {
        setState(RobotState.DISABLED);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void autonomousInit() {
        setState (RobotState.AUTONOMOUS);
        
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();

        // TODO set shooter state here
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void teleopInit() {
        setState(RobotState.TELEOP);
        
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

        shooterSubsystem.initializeDashboard();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        shooterSubsystem.makeTheShooterWork();
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    @Override
    public void testInit() {
        setState(RobotState.TEST);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        updateDashboard();
    }
    
    Timer dashboardTimer;

    void updateDashboard() {
        boolean update = false;
        if (dashboardTimer == null) {
            update = true;
            dashboardTimer = new Timer();
            dashboardTimer.start();
        }
        if (dashboardTimer.hasPeriodPassed(0.1)) {
            update = true;
        }
        if (update) {
            shooterSubsystem.updateDashboardFromSensors();
            drive.updateDashboard();
            SmartDashboard.putNumber("voltage", DriverStation.getInstance().getBatteryVoltage());
        }
    }

    public static final void writeToDS(String message) {
        final HALControlWord controlWord = FRCNetworkCommunicationsLibrary
                .HALGetControlWord();
        if (controlWord.getDSAttached()) {
            FRCNetworkCommunicationsLibrary.HALSetErrorData(message + "\n");
        }
    }
    
    void setState (RobotState r) {
        previousState = currentState;
        currentState = r;
        SmartDashboard.putString("currentState", currentState.toString());
        SmartDashboard.putString("previousState", previousState.toString());
    }
    
}
