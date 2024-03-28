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
      }
    
      @Override
      public void periodic() {
        // This method will be called once per scheduler run
      }
    
      
      public void open()
      {
        System.out.println(actuator.getAngle());
        actuator.setAngle(180);
        System.out.println(actuator.getAngle());
      }

      public void close()
      {
        actuator.setAngle(50);
      }
    
}