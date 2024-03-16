package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter extends SubsystemBase{
    VictorSPX upperLeftMotor;
    VictorSPX upperRightMotor;
    VictorSPX lowerLeftMotor;
    VictorSPX lowerRightMotor;
  
    /** Creates a new Shooter. */
    public Shooter() {
        upperLeftMotor = new VictorSPX(Constants.Shooter.UPPER_LEFT_SHOOTER);
        upperRightMotor = new VictorSPX(Constants.Shooter.UPPER_RIGHT_SHOOTER);
        lowerLeftMotor = new VictorSPX(Constants.Shooter.LOWER_LEFT_SHOOTER);
        lowerRightMotor = new VictorSPX(Constants.Shooter.LOWER_RIGHT_SHOOTER);
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      //left speeds are positive, right are negative to spin motors in opposite directions
      //direction should be either -1 or 1 to set intake forward or reverse
      public void shoot(double speed, int direction)
      {
        upperLeftMotor.set(ControlMode.PercentOutput, speed * direction);
        upperRightMotor.set(ControlMode.PercentOutput, -speed * direction);
        lowerLeftMotor.set(ControlMode.PercentOutput, speed * direction);
        lowerRightMotor.set(ControlMode.PercentOutput, -speed * direction);
      }
    
    
      public void stop()
      {
        upperLeftMotor.set(ControlMode.PercentOutput, 0);
        upperRightMotor.set(ControlMode.PercentOutput, 0);
        lowerLeftMotor.set(ControlMode.PercentOutput, 0);
        lowerRightMotor.set(ControlMode.PercentOutput, 0);
      }
    
}