package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.CommandSwerveDrivetrain;
import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import frc.robot.generated.TunerConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest.SwerveControlRequestParameters;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;

public class DriveIntake extends Command{
    Feeder feeder;
    Intake intake;
    CommandSwerveDrivetrain drivetrain;
    SwerveRequest.FieldCentric drive;
    SwerveControlRequestParameters parameters = new SwerveControlRequestParameters();
    SwerveModuleConstants constants = new SwerveModuleConstants();
    SwerveModule module = new SwerveModule(constants, "");
    double time;
    private boolean finish = false;
    Timer timer;
    double xSpeed;
    double ySpeed;
    double rSpeed;

    /** Creates a new DriveIntake. */
    public DriveIntake(Feeder f, Intake i, CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d, double t, double x, double y, double r) {
    feeder = f;
    addRequirements(feeder);
    intake = i;
    addRequirements(intake);
    drivetrain = dt;
    drive = d;
    time = t;
    timer = new Timer();
    xSpeed = x;
    ySpeed = y;
    rSpeed = r;
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < time)
    {
        intake.intake(Constants.Intake.SPEED, Constants.Intake.DIRECTION);
        feeder.feed(Constants.Feeder.SPEED, Constants.Feeder.DIRECTION);
        drivetrain.setControl(drive.withVelocityX(xSpeed * TunerConstants.kSpeedAt12VoltsMps) // Drive forward 
            .withVelocityY(ySpeed * TunerConstants.kSpeedAt12VoltsMps) // Drive left
            .withRotationalRate(rSpeed * 1.5 * Math.PI) // Drive counterclockwise
        );
    }
    finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.stop();
    intake.stop();
    drivetrain.setControl(drive.withVelocityX(0 * TunerConstants.kSpeedAt12VoltsMps) // Drive forward 
            .withVelocityY(0 * TunerConstants.kSpeedAt12VoltsMps) // Drive left
            .withRotationalRate(0 * 1.5 * Math.PI) // Drive counterclockwise
        );
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
