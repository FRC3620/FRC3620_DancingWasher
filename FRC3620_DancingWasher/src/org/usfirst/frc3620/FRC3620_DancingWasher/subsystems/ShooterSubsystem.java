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

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid fillValve = RobotMap.shooterSubsystemFillValve;
    Relay tank1Shoot = RobotMap.shooterSubsystemTank1Shoot;
    AnalogInput pressureSensor = RobotMap.shooterSubsystemPressureSensor;
    DoubleSolenoid tank1Lid = RobotMap.shooterSubsystemTank1Lid;
    AnalogInput a0 = RobotMap.shooterSubsystema0;
    DoubleSolenoid directionValve = RobotMap.shooterSubsystemDirectionValve;
    Relay tank2Shoot = RobotMap.shooterSubsystemTank2Shoot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // THIS IS HOW FAR WE FILL THE TANK!
    double fillPressure = edu.wpi.first.wpilibj.Preferences.getInstance().getDouble("fillPressure", 40);

	private ShootingSystemState currentState = ShootingSystemState.IDLE;
	private double tank1Pressure = -1;
	private double tank2Pressure = -1;
	
	public boolean isLidUp = false;
	public boolean[] safetiesPressed = new boolean[2];

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	// TODO
	// add methods to start and stop shoowing2
	// add methods to set direction value to tank1 or tank2

	public void startShooter2()
	{
		tank2Shoot.set(Relay.Value.kForward);
	}

	public void stopShooter2()
	{
		tank2Shoot.set(Relay.Value.kOff);
	}

	public void openFillValve()
	{
		fillValve.set(Value.kForward);
	}

	public void closeFillValve()
	{
		fillValve.set(Value.kReverse);
	}

	public void setDirectionValve1()
	{
		directionValve.set(Value.kForward);
	}

	public void setDirectionValve2()
	{
		directionValve.set(Value.kReverse);
	}

	public void startShooter1()
	{
		tank1Shoot.set(Relay.Value.kForward);
	}

	public void stopShooter1()
	{
		tank1Shoot.set(Relay.Value.kOff);
	}

	public double getShootingTankPressure()
	{
		double voltage = pressureSensor.getVoltage();
		double pressure;

		if (voltage <= 1.7)
		{
			// 0.5 v = 00 lb
			// 1.3 v = 30 lb
			pressure = (voltage - 0.5) * (10 / 0.3);
		} else
		{
			// 1.7 v = 40 lb
			// 2.0 v = 50 lb
			// 2.3 v = 60 lb
			// 2.6 v = 70 lb
			pressure = 40 + (voltage - 1.7) * (10 / 0.3);
		}
		return pressure;
	}

	public void liftLid()
	{
		tank1Lid.set(Value.kForward);
		isLidUp = true;
	}

	public void dropLid()
	{
		tank1Lid.set(Value.kReverse);
		isLidUp = false;
	}

	Timer shootingTimer = null;
	Timer switchingTimer = null;
	Timer fillTimer = null;

	public void makeTheShooterWork()
	{
		System.out.println ("current state = " + currentState + ", pressiure = " + getShootingTankPressure());
		switch (currentState) {
		case IDLE:
			closeFillValve();
			setDirectionValve1();
			stopShooter1();
			stopShooter2();

			break;

		case FILLING1:
			openFillValve();
			setDirectionValve1();
			stopShooter1();
			stopShooter2();
			
			if (fillTimer == null) {
				fillTimer = new Timer();
				fillTimer.reset();
				fillTimer.start();
				fillPressure = edu.wpi.first.wpilibj.Preferences.getInstance().getDouble("fillPressure", 40);
			}

			// check the tank pressure, change state to SWITCHING when we
			// are at pressure
			tank1Pressure = Robot.shooterSubsystem.getShootingTankPressure();
			updateDashboardWithTank1Pressure();
			if (tank1Pressure > fillPressure && fillTimer.get() > 2)
			{
				fillTimer = null;
				setShootingSystemState(ShootingSystemState.SWITCHING);
			}
			break;

		case SWITCHING:
			closeFillValve();
			setDirectionValve2();
			stopShooter1();
			stopShooter2();

			// TODO start a timer the first time we are here
			if (switchingTimer == null)
			{
				switchingTimer = new Timer();
				switchingTimer.reset();
				switchingTimer.start();
			}

			// TODO move to state FILLING2 when the timer is expired
			if (switchingTimer.get() > 1)
			{
				switchingTimer = null;
				setShootingSystemState(ShootingSystemState.FILLING2);
			}
			break;

		case FILLING2:
			openFillValve();
			setDirectionValve2();
			stopShooter1();
			stopShooter2();

			if (fillTimer == null) {
				fillTimer = new Timer();
				fillTimer.reset();
				fillTimer.start();
				fillPressure = edu.wpi.first.wpilibj.Preferences.getInstance().getDouble("fillPressure", 20);
			}

			// check the tank pressure, change state to ALLREADY when we
			// are at pressure
			tank2Pressure = getShootingTankPressure();
			updateDashboardWithTank2Pressure();
			if (tank2Pressure > fillPressure && fillTimer.get() > 2) {
				fillTimer = null;
				setShootingSystemState(ShootingSystemState.ALLREADY);
			}

			break;

		case ALLREADY:
			closeFillValve();
			setDirectionValve2();
			stopShooter1();
			stopShooter2();
			break;

		case SHOOTING1:
			startShooter1();
			stopShooter2();
			closeFillValve();

			// TODO start a timer the first time we are here
			if (shootingTimer == null)
			{
				shootingTimer = new Timer();
				shootingTimer.reset();
				shootingTimer.start();
			}

			// move to state T2ONLYREADY when the timer is expired
			if (shootingTimer.get() > .5)
			{
				shootingTimer = null;
				setShootingSystemState(ShootingSystemState.T2ONLYREADY);
				tank1Pressure = -1;
				updateDashboardWithTank1Pressure();
			}

			break;

		case T2ONLYREADY:
			stopShooter1();
			stopShooter2();
			closeFillValve();
			break;

		case SHOOTING2:
			startShooter2();
			stopShooter1();
			closeFillValve();
			// start a timer the first time we are here
			if (shootingTimer == null)
			{
				shootingTimer = new Timer();
				shootingTimer.reset();
				shootingTimer.start();
			}

			// move to state IDLE when the timer is expired
			if (shootingTimer.get() > .5)
			{
				shootingTimer = null;
				setShootingSystemState(ShootingSystemState.IDLE);
				tank2Pressure = -1;
				updateDashboardWithTank2Pressure();
			}
			break;

		default:
			break;
		}

	}
	
	public ShootingSystemState getShootingSystemState() {
		return currentState;
	}
	
	public void setShootingSystemState (ShootingSystemState newState) {
		currentState = newState;
		updateDashboardWithCurrentState();
	}
	
	private void updateDashboardWithCurrentState() {
		SmartDashboard.putString("Shooter", currentState.toString());
		Robot.writeToDS("Shooter is in state " + currentState.toString());
	}
	
	private void updateDashboardWithTank1Pressure() {
		SmartDashboard.putNumber("tank 1 pressure", tank1Pressure);
	}
	
	private void updateDashboardWithTank2Pressure() {
		SmartDashboard.putNumber("tank 2 pressure", tank2Pressure);
	}
	
	public void initializeDashboard() {
		updateDashboardWithCurrentState();
		updateDashboardWithTank1Pressure();
		updateDashboardWithTank2Pressure();
		updateDashboardFromSensors();
	}

    public void updateDashboardFromSensors()
    {
        SmartDashboard.putNumber("PressureSensorPSI", getShootingTankPressure());
        SmartDashboard.putNumber("PressureSensorVoltage", pressureSensor.getVoltage());
    }
}
