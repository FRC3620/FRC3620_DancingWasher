package org.usfirst.frc3620.FRC3620_DancingWasher.subsystems;

import org.slf4j.Logger;
import org.usfirst.frc3620.FRC3620_DancingWasher.Robot;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Barrel {
    private Logger logger;
    
    private String shooterName;
    private Solenoid fillvalve, shootValve;
    private AnalogInput pressureSensor;

    private BarrelState currentBarrelState;

    private BarrelState idleState, waitingToFillState, fillingState, readyState, firingState;

    private boolean fillIsRequested = false;
    private boolean shootIsRequested = false;

    public Barrel(String _shooterName,
            Solenoid _fillValve, Solenoid _shootValve,
            AnalogInput _pressureSensor) {
        super();
        
        logger = EventLogging.getLogger(Barrel.class, _shooterName, Level.INFO);
        
        this.shooterName = _shooterName;
        this.fillvalve = _fillValve;
        this.shootValve = _shootValve;
        this.pressureSensor = _pressureSensor;

        idleState = new IdleState();
        waitingToFillState = new WaitingToFillState();
        fillingState = new FillingState();
        readyState = new ReadyState();
        firingState = new FiringState();

        currentBarrelState = idleState;
        currentBarrelState.enter();
    }

    public void periodic() {
        BarrelState desiredBarrelState = currentBarrelState.execute();
        if (desiredBarrelState != null
                && desiredBarrelState != currentBarrelState) {
            currentBarrelState.exit();
            currentBarrelState = desiredBarrelState;
            currentBarrelState.enter();
        }
    }

    void startShooter() {
        logger.info("start shoot");
        shootValve.set(true);
    }

    void stopShooter() {
        logger.info("stop shoot");
        shootValve.set(false);
    }

    void startFill() {
        logger.info("start fill");
        fillvalve.set(true);
    }

    void stopFill() {
        logger.info("stop fill");
        fillvalve.set(false);

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



    public void updateDashboard() {
    }

    abstract class BarrelState {
        Logger logger = EventLogging.getLogger(getClass(), shooterName, EventLogging.Level.INFO);
        public void enter() {
            logger.info ("entering state");
        }

        abstract BarrelState execute();

        public void exit() {
            logger.info("leaving state");
        }
    }

    class IdleState extends BarrelState {

        @Override
        public void enter() {
            super.enter();
            fillIsRequested = false;
        }

        @Override
        public BarrelState execute() {
            if (fillIsRequested) {
                return waitingToFillState;
            }
            return null;
        }
    }

    class WaitingToFillState extends BarrelState {
        @Override
        public BarrelState execute() {
            return fillingState;
        }
    }

    class FillingState extends BarrelState {
        Timer timer = new Timer();

        @Override
        public void enter() {
            super.enter();
            startFill();
            timer.reset();
            timer.start();
        }

        @Override
        public BarrelState execute() {
            if (timer.hasPeriodPassed(5.0)) {
                return readyState;
            }
            return null;
        }

        @Override
        public void exit() {
            super.exit();
            stopFill();
        }

    }

    class ReadyState extends BarrelState {
        @Override
        public void enter() {
            super.enter();
            shootIsRequested = false;
        }

        @Override
        public BarrelState execute() {
            if (shootIsRequested) {
                return firingState;
            }
            return null;
        }
    }

    class FiringState extends BarrelState {
        Timer timer = new Timer();

        @Override
        public void enter() {
            super.enter();
            startShooter();
            ;
            timer.reset();
            timer.start();
        }

        @Override
        public BarrelState execute() {
            if (timer.hasPeriodPassed(1.0)) {
                return idleState;
            }
            return null;
        }

        @Override
        public void exit() {
            super.exit();
            stopShooter();
        }

    }
    
    public boolean fill() {
        fillIsRequested = true;
        return true;
    }

    public boolean shoot() {
        shootIsRequested = true;
        return true;
    }
}
