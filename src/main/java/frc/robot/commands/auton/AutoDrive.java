package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.CommandSwerveDrivetrain;
import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import frc.robot.generated.TunerConstants;

public class AutoDrive extends Command{
    CommandSwerveDrivetrain drivetrain;
    SwerveRequest.FieldCentric drive;
    double time;
    private boolean finish = false;
    Timer timer;

    /** Creates a new AutonDrive. */
    public AutoDrive(CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d, double t) {
    drivetrain = dt;
    drive = d;
    time = t;
    timer = new Timer();
    }

  // Called when the command is initially scheduled.
  //"Some things are ugly, not me, you." - Meta 3-23-23
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < time)
    {
        drivetrain.applyRequest(() -> drive.withVelocityX(0.5 * TunerConstants.kSpeedAt12VoltsMps) // Drive forward 
            .withVelocityY(0) // Drive left
            .withRotationalRate(0) // Drive counterclockwise
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
    drivetrain.applyRequest(() -> drive.withVelocityX(0) // Drive forward 
            .withVelocityY(0) // Drive left
            .withRotationalRate(0) // Drive counterclockwise
    );
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
