package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;


public class LimeLight extends SubsystemBase{
    
    NetworkTable table;
    double rotation;

    //limelight variables
    public static double tx; //x of target
    public static double ty; //y of target
    public static double ta; //area of target
    public static double tv; //whether limelight has a target
    public static boolean hasTarget; //whether limelight has a target
    XboxController driver = RobotContainer.driverController;
    public static double angleToGoalDegrees;
    public static double angleToGoalRadians;
    public static double tagID;
    public static double distanceFromLimelightToGoalInches;

    /** Creates a new LimeLight. */
    public LimeLight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    public void updateLimeLightTracking(){
        tv = table.getEntry("tv").getDouble(0);
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        ta = table.getEntry("ta").getDouble(0);
        hasTarget = tv != 0;
      }

    public void adjustToTarget(int pipeline) {
        
        //switch between pipelines. 0 = reflective tape, 1 = apriltag
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);

        updateLimeLightTracking();

        if (hasTarget) {
            tagID = identifyTag();
            
            //offset shelf tags
            if (tagID == 4 || tagID == 5) {
                if (driver.getBButton())
                    tx += 23;
                else
                    tx -= 23;    
            }
            //acceptable amount of error
             if (tx <= -2)
                rotation = -90;
            else if (tx >= 2)
                rotation = 90;
                else 
                rotation = 0;
        }
        else {
            rotation = 0;
        }
    }

    public void estimateDistance() {
        updateLimeLightTracking();

        if (hasTarget) {
            angleToGoalDegrees = Constants.LimeLight.MOUNT_ANGLE_DEGREES + ty;
            angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
            //calculate distance
            distanceFromLimelightToGoalInches = (findTagHeight() - Constants.LimeLight.LENS_HEIGHT_INCHES) / Math.tan(angleToGoalRadians);
        }

    }

    public double identifyTag() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid").getDouble(0);
    }

    public double findTagHeight() {
        tagID = identifyTag();

        if (tagID == 1 || tagID == 2 || tagID == 9 || tagID == 10)
            return Constants.LimeLight.SOURCE_HEIGHT;

        else if (tagID == 3 || tagID == 4 || tagID == 7 || tagID == 8)
            return Constants.LimeLight.SPEAKER_HEIGHT;

        else if (tagID == 5 || tagID == 6)
            return Constants.LimeLight.AMP_HEIGHT;

        else
            return Constants.LimeLight.STAGE_HEIGHT;
    }

    public double getRotation() {
        return rotation;
    }
}
