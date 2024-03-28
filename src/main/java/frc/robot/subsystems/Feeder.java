package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Feeder extends SubsystemBase{
    VictorSPX motor;
  
    /** Creates a new Feeder. */
    public Feeder() {
        motor = new VictorSPX(Constants.Feeder.FEEDER);
        motor.configOpenloopRamp(0.5, 500);
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      
      //direction should be either -1 or 1 to set intake forward or reverse
      public void feed(double speed, int direction)
      {
        motor.set(ControlMode.PercentOutput, speed * direction);
      }
    
    
      public void stop()
      {
        motor.set(ControlMode.PercentOutput, 0);
      }
    
}