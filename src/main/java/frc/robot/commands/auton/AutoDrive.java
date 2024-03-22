package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.CommandSwerveDrivetrain;
import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Feeder;

public class AutoDrive extends Command{
    CommandSwerveDrivetrain drivetrain;
    SwerveRequest.FieldCentric drive;
    Feeder feeder;
    double time;
    private boolean finish = false;
    Timer timer;
    double xSpeed;
    double ySpeed;
    double rSpeed;

    /** Creates a new AutoDrive. */
    public AutoDrive(CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d, Feeder f, double t, double x, double y, double r) {
    drivetrain = dt;
    drive = d;
    feeder = f;
    time = t;
    timer = new Timer();
    xSpeed = x;
    ySpeed = y;
    rSpeed = r;
    }

  // Called when the command is initially scheduled.
  //"Some things are ugly, not me, you." - Meta 3-23-23
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < time)
    {
        drivetrain.setControl(drive.withVelocityX(xSpeed * TunerConstants.kSpeedAt12VoltsMps) // Drive forward 
            .withVelocityY(ySpeed * TunerConstants.kSpeedAt12VoltsMps) // Drive left
            .withRotationalRate(rSpeed * 1.5 * Math.PI) // Drive counterclockwise
        );

        if (timer.get() < 0.5) 
          feeder.feed(Constants.Feeder.SPEED, Constants.Feeder.REVERSE_DIRECTION); //put a loaded note into position
    }
    finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
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
