package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;

public class AutoIntake extends Command{
    Feeder feeder;
    Intake intake;
    double time;
    private boolean finish = false;
    Timer timer;

    /** Creates a new AutoIntake. */
    public AutoIntake(Feeder f, Intake i, double t) {
    feeder = f;
    addRequirements(feeder);
    intake = i;
    addRequirements(intake);
    time = t;
    timer = new Timer();
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
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
