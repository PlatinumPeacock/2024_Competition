package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Arm extends SubsystemBase{
    VictorSPX motor;
  
    /** Creates a new Arm. */
    public Arm() {
        motor = new VictorSPX(Constants.Arm.MOTOR);
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      
      //direction should be either -1 or 1 to set intake forward or reverse
      public void climb(double speed, int direction)
      {
        motor.set(ControlMode.PercentOutput, speed * direction);
      }
    
    
      public void stop()
      {
        motor.set(ControlMode.PercentOutput, 0);
      }
    
}