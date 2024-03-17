package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;

public class PositionNote extends Command{
    Feeder feeder;
    private boolean finish = false;
    Timer timer;

    /** Creates a new PositionNote. */
    public PositionNote(Feeder f) {
    feeder = f;
    addRequirements(feeder);
    timer = new Timer();
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < 0.5)
    {
        feeder.feed(Constants.Feeder.SPEED, Constants.Feeder.REVERSE_DIRECTION);
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
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
