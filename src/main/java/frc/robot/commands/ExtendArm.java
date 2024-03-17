package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Actuator;

public class ExtendArm extends Command{
    Actuator actuator;

  /** Creates a new ExtendArm. */
  public ExtendArm(Actuator a) {
    actuator = a;
    addRequirements(actuator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    actuator.close();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    actuator.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
