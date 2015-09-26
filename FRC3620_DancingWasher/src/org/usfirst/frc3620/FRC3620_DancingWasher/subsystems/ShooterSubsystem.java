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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
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

	public ShootingSystemState currentState = ShootingSystemState.IDLE;
	boolean shootingTank1IsFull = false;
	boolean shootingTank2IsFull = false;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public boolean isLidUp;
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

	public void updateDashboard()
	{
		SmartDashboard.putNumber("Tank1Pressure", getShootingTankPressure());
		SmartDashboard.putNumber("Tank1Voltage", pressureSensor.getVoltage());
	}

	Timer shootingTimer = null;

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

			// TODO check the tank pressure, change state to SWITCHING when we
			// are at pressure
			getShootingTankPressure();
			double pressure1 = Robot.shooterSubsystem.getShootingTankPressure();
			if (pressure1 > 60)
			{
				currentState = ShootingSystemState.SWITCHING;
				shootingTank1IsFull = true;
			}
			break;

		case SWITCHING:
			closeFillValve();
			setDirectionValve2();
			stopShooter1();
			stopShooter2();
			currentState = ShootingSystemState.FILLING2;
			break;

		case FILLING2:
			openFillValve();
			setDirectionValve2();
			stopShooter1();
			stopShooter2();

			// TODO check the tank pressure, change state to ALLREADY when we
			// are at pressure
			getShootingTankPressure();
			double pressure2 = Robot.shooterSubsystem.getShootingTankPressure();
			if (pressure2 > 60) {
				currentState = ShootingSystemState.ALLREADY;
				shootingTank2IsFull = true;
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

			// TODO move to state T2ONLYREADY when the timer is expired
			if (shootingTimer.get() > 1)
			{
				shootingTimer = null;
				currentState = ShootingSystemState.T2ONLYREADY;
				shootingTank1IsFull = false;
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
			// TODO start a timer the first time we are here
			if (shootingTimer == null)
			{
				shootingTimer = new Timer();
				shootingTimer.reset();
				shootingTimer.start();
			}

			// TODO move to state IDLE when the timer is expired
			if (shootingTimer.get() > 1)
			{
				shootingTimer = null;
				currentState = ShootingSystemState.IDLE;
				shootingTank2IsFull = false;
			}
			break;

		default:
			break;
		}

	}
}
