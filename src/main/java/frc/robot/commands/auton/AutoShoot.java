package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends Command{
    Feeder feeder;
    Shooter shooter;
    double time;
    private boolean finish = false;
    Timer timer;

    /** Creates a new AutoShoot. */
    public AutoShoot(Feeder f, Shooter s, double t) {
    feeder = f;
    addRequirements(feeder);
    shooter = s;
    addRequirements(shooter);
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
        shooter.shoot(Constants.Shooter.SPEED, Constants.Shooter.DIRECTION);
        if (timer.get() > 1.5)
        {
            feeder.feed(Constants.Feeder.SPEED, Constants.Feeder.DIRECTION);
        }
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
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
