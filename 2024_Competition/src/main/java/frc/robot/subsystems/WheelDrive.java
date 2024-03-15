package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_CANCoder;

import com.ctre.phoenix6.hardware.CANcoder;


public class WheelDrive {
    private TalonFX angleMotor;
    private TalonFX speedMotor;
    private CANCoder encoder;
    private PIDController pidController;
    

    public WheelDrive(int aM, int sM, int encoder) {
        angleMotor = new TalonFX(aM);
        speedMotor = new TalonFX(sM);
        this.encoder = new CANCoder(encoder);
        
        
        
        pidController = new PIDController (0.005, 0, 0);
        pidController.enableContinuousInput(-180, 180);
        pidController.setTolerance(1);

    }


    public void drive(double speed, double angle) {
        
        speedMotor.set (speed);
        
        double setpoint = angle;
        pidController.setSetpoint (setpoint);
        double cmd = -1 * pidController.calculate(encoder.getAbsolutePosition(), setpoint);
        
       
        angleMotor.set(MathUtil.clamp(cmd, -1, 1));
        
    }

}
