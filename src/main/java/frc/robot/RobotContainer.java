  // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Actuator;
import frc.robot.commands.IntakeNote;
import frc.robot.commands.Shoot;
import frc.robot.commands.Feed;
import frc.robot.commands.Climb;
import frc.robot.commands.ExtendArm;
import frc.robot.commands.CloseActuator;

import frc.robot.commands.auton.AutonIntakeShoot;
import frc.robot.commands.auton.AutonDriveIntake;
import frc.robot.commands.auton.CenterAuton;
import frc.robot.commands.auton.CenterAutonLeave;
import frc.robot.commands.auton.AutonShootOnly;
import frc.robot.commands.auton.BiggieCheese;
import frc.robot.commands.auton.CenterAutonDelay;
import frc.robot.commands.auton.Blue2NoteSourceExit;

public class RobotContainer {
  
  private double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final Telemetry logger = new Telemetry(MaxSpeed);






  //operator controller
  public static CommandXboxController operatorController = new CommandXboxController(Constants.Controller.OPERATOR_CONTROLLER);

  //create LimeLight
  public static final LimeLight limeLight = new LimeLight();
    
    
  //create all subsystem objects
  private final Intake intake;
  private final Feeder feeder;
  private final Shooter shooter;
  private final Arm arm;
  private final Actuator actuator;


  //create all repeatCommands (because the whileHeld() method no longer exists)
  private final RepeatCommand intakeNote;
  private final RepeatCommand intakeReverse;
  private final RepeatCommand feed;
  private final RepeatCommand feedSlow;
  private final RepeatCommand feedReverse;
  private final RepeatCommand shoot;
  private final RepeatCommand shootSlow;
  private final RepeatCommand shootReverse;
  private final RepeatCommand climb;
  private final RepeatCommand climbReverse;
  private final RepeatCommand extendArm;
  private final RepeatCommand closeActuator;
  private final RepeatCommand pinPull;

    

  SendableChooser<Command> chooser = new SendableChooser<>();







  private void configureBindings() {
    
    //swerve drive button config
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    joystick.rightBumper().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))));
    joystick.leftBumper().whileTrue(drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * 0.2) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * 0.2) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * 0.5) // Drive counterclockwise with negative X (left)
        ));

    // reset the field-centric heading on left bumper press
    joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);







    //intake buttons
    Trigger intakeButton = operatorController.b();
    intakeButton.whileTrue(intakeNote);

    Trigger intakeReverseButton = operatorController.a();
    intakeReverseButton.whileTrue(intakeReverse);

    //feeder buttons
    Trigger feederButton = operatorController.b();
    feederButton.whileTrue(feed);

    Trigger feedOnlyButton = operatorController.y();
    feedOnlyButton.whileTrue(feed);

    Trigger feedReverseButton = operatorController.leftBumper();
    feedReverseButton.whileTrue(feedReverse);

    Trigger feedReversewithIntakeButton = operatorController.a();
    feedReversewithIntakeButton.whileTrue(feedReverse);

    //shooter buttons
    Trigger shootButton = operatorController.rightBumper();
    shootButton.whileTrue(shoot);
/*
    Trigger shootSlowButton = operatorController.y();
    shootSlowButton.whileTrue(shootSlow);*/

    Trigger shootReverseButton = operatorController.leftBumper();
    shootReverseButton.whileTrue(shootReverse);

    //climb buttons
    Trigger extendButton = operatorController.back();
    extendButton.whileTrue(extendArm);

    Trigger closeActuatorButton = operatorController.start();
    closeActuatorButton.whileTrue(closeActuator);

    /*Trigger climbButton = operatorController.povUp();
    climbButton.whileTrue(climb);*/

    Trigger climbReverseButton = operatorController.povDown();
    climbReverseButton.whileTrue(climbReverse);

    Trigger pinPullButton = operatorController.povLeft();
    pinPullButton.whileTrue(pinPull);
    
  }

  public RobotContainer() {
    //new intake object and all intake commands
    intake = new Intake();
    intakeNote = new RepeatCommand(new IntakeNote(intake, Constants.Intake.SPEED, Constants.Intake.DIRECTION));
    intakeNote.addRequirements(intake);
    intakeReverse = new RepeatCommand(new IntakeNote(intake, Constants.Intake.SPEED_REVERSE, Constants.Intake.REVERSE_DIRECTION));
    intakeReverse.addRequirements(intake);

    //new feeder object and all feeder commands
    feeder = new Feeder();
    feed = new RepeatCommand(new Feed(feeder, Constants.Feeder.SPEED, Constants.Feeder.DIRECTION));
    feed.addRequirements(feeder);
    feedSlow = new RepeatCommand(new Feed(feeder, Constants.Feeder.SPEED_SLOW, Constants.Feeder.DIRECTION));
    feedSlow.addRequirements(feeder);
    feedReverse = new RepeatCommand(new Feed(feeder, Constants.Feeder.SPEED_REVERSE, Constants.Feeder.REVERSE_DIRECTION));
    feedReverse.addRequirements(feeder);

    //new shooter object and all shoot commands
    shooter = new Shooter();
    shoot = new RepeatCommand(new Shoot(shooter, Constants.Shooter.SPEED, Constants.Shooter.DIRECTION));
    shoot.addRequirements(shooter);
    shootSlow = new RepeatCommand(new Shoot(shooter, Constants.Shooter.SPEED_SLOW, Constants.Shooter.DIRECTION));
    shootSlow.addRequirements(shooter);
    shootReverse = new RepeatCommand(new Shoot(shooter, Constants.Shooter.SPEED_REVERSE, Constants.Shooter.REVERSE_DIRECTION));
    shootReverse.addRequirements(shooter);

    //new arm object and all climb commands
    arm = new Arm();
    climb = new RepeatCommand(new Climb(arm, Constants.Arm.SPEED, Constants.Arm.DIRECTION));
    climb.addRequirements(arm);
    climbReverse = new RepeatCommand(new Climb(arm, Constants.Arm.SPEED_REVERSE, Constants.Arm.REVERSE_DIRECTION));
    climbReverse.addRequirements(arm);
    actuator = new Actuator();
    extendArm = new RepeatCommand(new ExtendArm(actuator));
    extendArm.addRequirements(actuator);
    closeActuator = new RepeatCommand(new CloseActuator(actuator));
    closeActuator.addRequirements(actuator);
    pinPull = new RepeatCommand(new Climb(arm, Constants.Arm.SPEED_SLOW, Constants.Arm.REVERSE_DIRECTION));
    pinPull.addRequirements(arm);

    AutonIntakeShoot auton1 = new AutonIntakeShoot(feeder, shooter, intake, 3);
    AutonDriveIntake driveIntake = new AutonDriveIntake(feeder, intake, drivetrain, drive, 2);
    CenterAuton centerAuton = new CenterAuton(feeder, intake, shooter, drivetrain, drive);
    CenterAutonLeave centerAutonLeave = new CenterAutonLeave(feeder, intake, shooter, drivetrain, drive);
    AutonShootOnly shootOnly = new AutonShootOnly(feeder, shooter);
    BiggieCheese biggieCheese = new BiggieCheese(feeder, intake, shooter, drivetrain, drive);
    CenterAutonDelay centerAutonDelay = new CenterAutonDelay(feeder, intake, shooter, drivetrain, drive);
    Blue2NoteSourceExit blueSource = new Blue2NoteSourceExit(feeder, intake, shooter, drivetrain, drive);

    chooser.setDefaultOption("Intake Shoot Auto", auton1);
    chooser.addOption("Drive and Intake", driveIntake);
    chooser.addOption("Center Auton, In Zone", centerAuton);
    chooser.addOption("Center Auton, Exit Zone", centerAutonLeave);
    chooser.addOption("Shoot Only", shootOnly);
    chooser.addOption("Biggie Cheese", biggieCheese);
    chooser.addOption("Center, In Zone, With 4sec Delay", centerAutonDelay);
    chooser.addOption("BLUE 2 Source Exit", blueSource);
 
    //Add choice to smart dashboard
    SmartDashboard.putData("Autonomous", chooser);


    configureBindings();
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
