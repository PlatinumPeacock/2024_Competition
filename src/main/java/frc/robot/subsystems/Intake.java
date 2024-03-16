package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake extends SubsystemBase{
    VictorSPX leftMotor;
    VictorSPX rightMotor;
  
    /** Creates a new Intake. */
    public Intake() {
        leftMotor = new VictorSPX(Constants.Intake.LEFT_INTAKE);
        rightMotor = new VictorSPX(Constants.Intake.RIGHT_INTAKE);
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      //one speed is positive, one is negative to spin motors in opposite directions
      //direction should be either -1 or 1 to set intake forward or reverse
      public void intake(double speed, int direction)
      {
        leftMotor.set(ControlMode.PercentOutput, speed * direction);
        rightMotor.set(ControlMode.PercentOutput, -speed * direction);
      }
    
    
      public void stop()
      {
        leftMotor.set(ControlMode.PercentOutput, 0);
        rightMotor.set(ControlMode.PercentOutput, 0);
      }
    
}