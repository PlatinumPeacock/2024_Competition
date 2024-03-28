// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.CommandSwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Blue2NoteSourceExit extends SequentialCommandGroup {
  /** Creates a new Blue2NoteExit. */
  public Blue2NoteSourceExit(Feeder f, Intake i, Shooter s, CommandSwerveDrivetrain dt, SwerveRequest.FieldCentric d) {
    addCommands(new AutoShoot(f, s)); //shoot loaded note
    addCommands(new DriveOnly(dt, d, 0.1, 0, 0, 0)); //set wheels to zero so hopefully it will drive straight
    addCommands(new DriveOnly(dt, d, 1, 0.15, -0.03, 0.5));
    addCommands(new DriveOnly(dt, d, 0.05, 0, 0, 0)); //set wheels to zero so hopefully it will drive straight
    addCommands(new DriveIntake(f, i, dt, d, 1.2, 0.5, 0.05, 0)); //pick up 2nd note
    addCommands(new AutoIntake(f, i, 0.3));
    addCommands(new AutoDrive(dt, d, f, 1.2, -0.5, -0.05, 0));
    addCommands(new DriveOnly(dt, d, 0.05, 0, 0, 0)); //set wheels to zero so hopefully it will drive straight
    addCommands(new DriveOnly(dt, d, 1, -0.15, 0.025, -0.5));
    addCommands(new AutoShoot(f, s)); //shoot 2nd note
    addCommands(new DriveOnly(dt, d, 0.1, 0, 0, 0)); //set wheels to zero to drive straight
    addCommands(new DriveOnly(dt, d, 1, 0.15, -0.03, 0.5));
    addCommands(new DriveOnly(dt, d, 0.05, 0, 0, 0)); //set wheels to zero to drive straight
    addCommands(new DriveOnly(dt, d, 2, 0.5, 0.05, 0)); //leave the wing
  }
}
