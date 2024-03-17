package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;


public class Actuator extends SubsystemBase{
    Servo actuator;
  
    /** Creates a new Actuator. */
    public Actuator() {
        actuator = new Servo(Constants.Actuator.SERVO);
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      
      public void open()
      {
        actuator.setSpeed(1);
      }

      public void close()
      {
        actuator.setSpeed(-1);
      }
    
    
      public void stop()
      {
        
      }
    
}