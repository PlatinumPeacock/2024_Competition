package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.PWM;


public class Actuator extends SubsystemBase{
    Servo actuator;
  
    /** Creates a new Actuator. */
    public Actuator() {
        actuator = new Servo(Constants.Actuator.SERVO);
        //actuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0); 
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
        actuator.setSpeed(0);
      }
    
}