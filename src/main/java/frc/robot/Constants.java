package frc.robot;

public final class Constants {

    public static final class Controller {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
        public static final int LEFT_X_AXIS = 0;
        public static final int LEFT_Y_AXIS = 1;
        public static final int RIGHT_X_AXIS = 4;
    }

    public static final class Intake {
        //port numbers for intake
        public static final int LEFT_INTAKE = 5; 
        public static final int RIGHT_INTAKE = 9; 
        public static final double SPEED = 1;
        public static final double SPEED_REVERSE = 1;
        public static final int DIRECTION = 1;
        public static final int REVERSE_DIRECTION = -1;

        public static final double AUTON_SPEED = 1;
    }

    public static final class Feeder {
        public static final int FEEDER = 18;
        public static final double SPEED = 1;
        public static final double SPEED_SLOW = 0.4;
        public static final double SPEED_REVERSE = 1;
        public static final int DIRECTION = 1;
        public static final int REVERSE_DIRECTION = -1;
    }

    public static final class Shooter {
        //port numbers for intake
        public static final int UPPER_LEFT_SHOOTER = 15;
        public static final int UPPER_RIGHT_SHOOTER = 19;
        public static final int LOWER_LEFT_SHOOTER = 1;
        public static final int LOWER_RIGHT_SHOOTER = 8;
        public static final double SPEED = 1;
        public static final double SPEED_SLOW = 0.4;
        public static final double SPEED_REVERSE = 1;
        public static final int DIRECTION = 1;
        public static final int REVERSE_DIRECTION = -1;
    }

    public static final class Arm {
        public static final int MOTOR = 6;
        public static final double SPEED = 1;
        public static final double SPEED_REVERSE = 1;
        public static final int DIRECTION = 1;
        public static final int REVERSE_DIRECTION = -1;
    }


    public static final class LimeLight {
        public static final double AUTO_DRIVE_SPEED = 0.1;
        public static final double AUTO_ROTATE_SPEED = 0.1;
        public static final double DESIRED_TARGET_AREA = 0.9; // area of the target when the robot reaches desired distance

        //VALUES LISTED BELOW ARE NOT REAL VALUES TO USE!!!!
        // how many degrees back is your limelight rotated from perfectly vertical?
        public static final double MOUNT_ANGLE_DEGREES = 0.0; // According to CAD, rotation is 0.  25.0; 

        // distance from the center of the Limelight lens to the floor
        public static final double LENS_HEIGHT_INCHES = 16.77; // Inches is correct. 20.0; 

        // THESE VALUES ARE CORRECT BELOW :D
        // aprilTag distance from the target to the floor, in inches
        public static final double SPEAKER_HEIGHT = 51.875; 
        public static final double AMP_HEIGHT = 48.125; 
        public static final double STAGE_HEIGHT = 47.5; 
        public static final double SOURCE_HEIGHT = 48.125;
    }

    public static final class Actuator {
        public static final int SERVO = 0; //PWM port number of actuator
    }
      
}
